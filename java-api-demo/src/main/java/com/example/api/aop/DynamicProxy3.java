package proxytest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lwj
 * @version 1.00
 * @time 2020/11/10 0010  下午 10:06
 */
public class DynamicProxy3 implements InvocationHandler {

    private Object object;

    public DynamicProxy3(Object object){
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法名"+ method.getName() + "---" + args[0] + "---" + args[1]);
        Object invoke = method.invoke(object, args);
        System.out.println("方法结果"+ invoke);
        return invoke;
    }



    public Object createProxy(){
        ClassLoader classLoader = object.getClass().getClassLoader();
        Class<?>[] interfaces = object.getClass().getInterfaces();
        Object proxyInstance = Proxy.newProxyInstance(classLoader, interfaces, new DynamicProxy3(object));
        return proxyInstance;
    }

}
