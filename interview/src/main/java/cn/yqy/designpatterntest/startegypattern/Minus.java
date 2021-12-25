package cn.yqy.designpatterntest.startegypattern;

import javax.swing.*;

public class Minus extends AbstracrCalculator implements ICalculator {
    public int calculate(String exp) {
        int arrayInt[] = split(exp, "-");

        return arrayInt[0] - arrayInt[1];
    }
}
