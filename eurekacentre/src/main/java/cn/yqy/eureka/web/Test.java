package cn.yqy.eureka.web;

import cn.yqy.eureka.custominterface.SysTest;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.internal.util.stereotypes.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import javax.lang.model.element.AnnotationMirror;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller

public class Test {
    Map<String, String> methodMap = new HashMap();

    @RequestMapping(value = "test/{code}", method = RequestMethod.GET)
    public void test(@PathVariable String code) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String s = methodMap.get(code);
        if (StringUtils.isNotBlank(s)) {
            System.out.println(s);
            Method method = Test.class.getMethod(s, null);
            Object invoke = method.invoke(new Test(), null);

        }

    }

    @SysTest(test2 = "123")
    public void test3() {
        System.out.println("test3 invoke...");
    }


    @PostConstruct
    private void getAllMethodMap() {
        Method[] methods = Test.class.getMethods();
        for (Method m: methods) {
            SysTest annotation = m.getAnnotation(SysTest.class);
            if (annotation != null) {
                String s = annotation.test2();
                if (StringUtils.isNotBlank(s)) {
                    methodMap.put(s, m.getName());
                }


            }
        }
    }

}
