package proxytest;




/**
 * @author lwj
 * @version 1.00
 * @time 2020/11/10 0010  下午 9:28
 */
public class CalculatorImpl implements Calculator {

    @Override
    public Integer add(Integer a, Integer b) {
        return a + b;
    }

    @Override
    public Integer sub(Integer a, Integer b) {
        return a - b;
    }

    @Override
    public Integer divide(Integer a, Integer b) {
        return a / b;
    }

    @Override
    public Integer mul(Integer a, Integer b) {
        return a * b;
    }
}
