package java0.conc0303.homework;

/**
 * <p>TODO
 * </p>
 *
 * @author xingpeng
 * @date 2020/11/8 4:12 下午
 **/

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HomeWork2 {

    private static int result = 0;

    public static void main(String[] args) {

        long start = System.currentTimeMillis();

        // 异步执行 下面方法
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread t = new Thread(new MyTask(countDownLatch));
        t.start();

        //int result = sum();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 确保  拿到result 并输出
        System.out.println("异步计算结果为：" + result);

        System.out.println("使用时间：" + (System.currentTimeMillis() - start) + " ms");

        // 然后退出main线程
    }

    private static int sum() {
        return fibo(36);
    }

    private static int fibo(int a) {
        if (a < 2) {
            return 1;
        }
        return fibo(a - 1) + fibo(a - 2);
    }
    private static int getResult(){
        return result;
    }

    static class MyTask implements Runnable{

        private CountDownLatch countDownLatch;

        public MyTask(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            result = sum();
            countDownLatch.countDown();
        }
    }

}

