package com.xp.kid.cachedemo.redis;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>TODO
 * </p>
 *
 * @author xingpeng
 * @date 2020/12/29 8:30 下午
 **/
@Component
@Slf4j
public class XpRedisTemplate <K,V>{


    @Autowired
    private RedisTemplate<K,V> redisTemplate;




    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public V get(K key) {
        return key == null ? null : (V) redisTemplate.opsForValue().get(key);
    }


    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public Boolean set(K key, V value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }

    }


    public Set<K> keys(K key) {
        return redisTemplate.keys(key);
    }

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public Boolean expire(K key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    /**
     * 指定缓存在时间点失效
     *
     * @param key  key 键不能为空
     * @param date 时间戳不能为空
     * @return
     */
    public Boolean expireAt(K key, Date date) {
        try {
            if (null != date) {
                redisTemplate.expireAt(key, date);
                return true;
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void del(K... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete((K) CollectionUtils.arrayToList(key));
            }
        }
    }


    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public Boolean hasKey(K key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }



    /**
     * set not exist, 当 key 不存在，设置成功 (简陋的分布式锁)
     *
     * @param key     键
     * @param value   值
     * @return
     */
    public Boolean setNX(K key, V value, long time) {
        try {
            Boolean result = redisTemplate.opsForValue().setIfAbsent(key, value);
            if (result && time > 0) {
                expire(key, time);
            }
            return result;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }


    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return
     */
    public Long incr(K key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public Long incr(K key, long delta, long time) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        Long l = incr(key, delta);
        if (time > 0) {
            expire(key, time);
        }
        return l;
    }


    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return
     */
    public Long decr(K key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return redisTemplate.opsForValue().increment(key, -delta);
    }



}
