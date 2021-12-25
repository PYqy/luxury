package cn.yqy.designpatterntest.decoratorpattern;

public class Source implements Sourceable {
    public void method() {
        System.out.println("the original method!");
    }
}
