package cn.yqy.designpatterntest.decoratorpattern;

/**
 * 装饰模式 就是给一个对象增加一些新的功能，而且是动态的， 要求 装饰对象和被装饰对象实现同一个接口，装饰对象持有被装饰对象的实例
 */
public class DecoratorTest {

    public static void main(String[] args) {
        Sourceable sourceable = new Source();
        Sourceable obj = new Decorator(sourceable);
        obj.method();
    }
}
