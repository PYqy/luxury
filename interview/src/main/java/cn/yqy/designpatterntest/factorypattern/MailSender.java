package cn.yqy.designpatterntest.factorypattern;

public class MailSender implements Sender {
    public void Send() {
        System.out.println("this is mail Sender");
    }
}
