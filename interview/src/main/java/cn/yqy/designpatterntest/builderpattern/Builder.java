package cn.yqy.designpatterntest.builderpattern;

import cn.yqy.designpatterntest.factorypattern.MailSender;
import cn.yqy.designpatterntest.factorypattern.Sender;
import cn.yqy.designpatterntest.factorypattern.SmsSender;

import java.util.ArrayList;
import java.util.List;

public class Builder {
    private List<Sender> list = new ArrayList<Sender>();

    public void produceMailSender(int count) {
        for (int i = 0; i < count; i++) {
            list.add(new MailSender());
        }
    }

    public void produceSmsSender(int count) {
        for (int i = 0; i < count; i++) {
            list.add(new SmsSender());
        }
    }
}
