package proxytest;

/**
 * @author lwj
 * @version 1.00
 * @time 2020/11/10 0010  下午 9:39
 */
public class TestClass {

    /**
     * 为了简单 写个main方法
     */
    public static void main(String[] args) {
        CalculatorImpl calculator = new CalculatorImpl();

//        Calculator proxyObject = (Calculator)new DynamicProxy(calculator).createProxyObject();
//        proxyObject.add(1,2);

        /*Calculator proxy = DynamicProxy2.createProxy(calculator);
        proxy.add(1,3);*/
        Calculator proxy = (Calculator) new DynamicProxy3(calculator).createProxy();
        proxy.add(1, 4);
    }
}
