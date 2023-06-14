package proxytest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author lwj
 * @version 1.00
 * @time 2020/11/10 0010  下午 9:24
 */
public class DynamicProxy {

    private Object object;

    //有参构造器给object进行初始化
    public DynamicProxy(Object object){
        this.object = object;
    }

    public Object createProxyObject() {
        //类加载器
        ClassLoader classLoader = object.getClass().getClassLoader();
        //实现的接口
        Class<?>[] interfaces = object.getClass().getInterfaces();
        //invocationHandler接口的实现类
        InvocationHandler handler = new InvocationHandler(){
            //实现此方法
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                //核心是method的invoca方法 object是被代理对象 args是调用方法的参数
                System.out.println("现在调用的方法是"+ method.getName() + ", 参数是"+ args.length);
                Object invoke = method.invoke(object, args);
                //返回的invoke是方法的返回结果
                System.out.println("现在调用的方法结果是"+ invoke);
                return invoke;
            }
        };

        Object newProxyInstance = Proxy.newProxyInstance(classLoader, interfaces, handler);

        return newProxyInstance;
    }
}
