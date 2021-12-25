package cn.yqy.designpatterntest.startegypattern;

public class Plus extends AbstracrCalculator implements ICalculator {
    public int calculate(String exp) {
        int arrayInt[] = split(exp, "\\+");
        return arrayInt[0] + arrayInt[1];
    }
}
