package cn.yqy.designpatterntest.dynamicproxy;


import sun.applet.Main;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

public class DynamicProxyP273 {


    public static void main(String[] args) {
        final List<String> list2 = new ArrayList<String>();
        List<String> proxyIstance = (List<String>) Proxy.newProxyInstance(list2.getClass().getClassLoader(), list2.getClass().getInterfaces(),

                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        return method.invoke(list2, args);
                    }
                }
        );
        proxyIstance.add("Hello");
        System.out.println(list2);
    }
}
