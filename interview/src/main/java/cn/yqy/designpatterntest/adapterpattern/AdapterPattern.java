package cn.yqy.designpatterntest.adapterpattern;

public class AdapterPattern {
    public static void main(String[] args) {
        Targetable table = new Adapter();
        table.method1();
        table.method2();
    }
}
