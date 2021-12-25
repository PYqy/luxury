package cn.yqy.designpatterntest.factorypattern;

public class SendeFactory {
    //普通工厂模式

    public Sender produce(String type) {
        if ("mail".equals(type)) {
            return new MailSender();
        } else if ("sms".equals(type)) {
            return new SmsSender();
        } else {
            System.out.println("请输入正确的类型！");
            return null;
        }

    }


    //多个工厂方法模式

//    public Sender produceMail(){
//        return new MailSender();
//
//    }
//
//    public Sender produceSms(){
//        return  new SmsSender();
//    }
//
    //静态工厂方法
//    public static Sender produceMail(){
//        return  new MailSender();
//    }
//    public static Sender produceSms(){
//        return  new SmsSender();
//    }


}
