package cn.yqy.designpatterntest.factorypattern.abstractfactory;

import cn.yqy.designpatterntest.factorypattern.Sender;

public class AbstarctFactoryTest {
    public static void main(String[] args) {
        Provide provide = new SendMailFactory();
        Sender sender = provide.produce();
        sender.Send();
    }
}
