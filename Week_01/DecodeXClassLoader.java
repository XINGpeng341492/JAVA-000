import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 *     自定义classloader 加载指定文件
 * </p>
 *
 * @author xingpeng
 * @date 2020/10/17 3:04 下午
 **/
public class DecodeXClassLoader extends ClassLoader{
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> res = null;
        InputStream inputStream = DecodeXClassLoader.class.getClassLoader().getResourceAsStream("Hello.xlass");
        try {
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            for (int i = 0; i < bytes.length; i++) {
                bytes[i] = (byte)(255 - bytes[i]);
            }
            res =  defineClass(name,bytes,0,bytes.length);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //return super.findClass(name);
        return res;
    }

    public static void main(String[] args) {
        try {
            Class cla = new DecodeXClassLoader().findClass("Hello");
            try {
                Object obj = cla.getConstructor().newInstance();
                cla.getMethod("hello").invoke(obj);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
