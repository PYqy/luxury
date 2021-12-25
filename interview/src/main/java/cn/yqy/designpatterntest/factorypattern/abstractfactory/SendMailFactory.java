package cn.yqy.designpatterntest.factorypattern.abstractfactory;

import cn.yqy.designpatterntest.factorypattern.MailSender;
import cn.yqy.designpatterntest.factorypattern.Sender;

public class SendMailFactory implements Provide {
    public Sender produce() {
        return new MailSender();
    }
}
