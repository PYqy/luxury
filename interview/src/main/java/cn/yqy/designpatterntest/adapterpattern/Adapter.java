package cn.yqy.designpatterntest.adapterpattern;

public class Adapter extends Source implements Targetable {
    //对象适配器 只需要将此类修改成 不继承 Source类 ，而是持有Source对象即可
//    Source source = new Source()
    public void method2() {
        System.out.println("this is ther targetable method!");
    }
}
