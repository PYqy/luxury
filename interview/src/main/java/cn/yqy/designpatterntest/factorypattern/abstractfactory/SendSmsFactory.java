package cn.yqy.designpatterntest.factorypattern.abstractfactory;

import cn.yqy.designpatterntest.factorypattern.Sender;
import cn.yqy.designpatterntest.factorypattern.SmsSender;

public class SendSmsFactory implements Provide {
    public Sender produce() {
        return new SmsSender();
    }
}
