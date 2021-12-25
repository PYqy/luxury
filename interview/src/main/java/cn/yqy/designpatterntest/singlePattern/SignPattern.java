package cn.yqy.designpatterntest.singlePattern;

public class SignPattern {
    //饿汉式
//    private static SignPattern  signPattern = new SignPattern();
//    private  SignPattern(){
//
//
//    }
//    public static SignPattern getInstance(){
//        return signPattern;
//    }

    //饱汉式
    private static SignPattern signPattern;

    private SignPattern() {


    }

    public static SignPattern getInstance() {
        if (signPattern == null) {
            synchronized (SignPattern.class) {
                if (signPattern == null) {
                    signPattern = new SignPattern();

                }
            }
        }
        return signPattern;

    }
}
