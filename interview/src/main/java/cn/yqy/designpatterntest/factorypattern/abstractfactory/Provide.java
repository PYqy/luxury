package cn.yqy.designpatterntest.factorypattern.abstractfactory;

import cn.yqy.designpatterntest.factorypattern.Sender;

public interface Provide {
    public Sender produce();
}
