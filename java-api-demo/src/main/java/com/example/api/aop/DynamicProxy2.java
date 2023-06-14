package proxytest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lwj
 * @version 1.00
 * @time 2020/11/10 0010  下午 9:48
 */
public class DynamicProxy2 {


    public static Calculator createProxy(Calculator calculator){

        ClassLoader classLoader = calculator.getClass().getClassLoader();
        Class<?>[] interfaces = calculator.getClass().getInterfaces();
        InvocationHandler handler = new InvocationHandler(){
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("方法开始执行");
                Object invokeResult = method.invoke(calculator, args);
                System.out.println("方法执行结束");
                return invokeResult;
            }
        };
        Calculator proxyInstance = (Calculator) Proxy.newProxyInstance(classLoader, interfaces, handler);
        return proxyInstance;
    }
}
