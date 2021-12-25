package cn.yqy.designpatterntest.factorypattern;

public class SmsSender implements Sender {
    public void Send() {
        System.out.println("this is sms sender!");
    }
}
