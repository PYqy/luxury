package cn.yqy.eureka.custominterface;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysTest {
    String test2() default "";
}
