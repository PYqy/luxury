package cn.yqy.test;

import cn.yqy.domain.Person;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class JsonTest {

    //Java对象转为JSON字符串
    @Test
    public void test() throws IOException {
        //1、创建Person对象
        Person p = new Person("zhangsan", 13, "nan");
        //创建jackson核心对象
        ObjectMapper mapper = new ObjectMapper();
        //转换
        /**
         * 转换方法：
         *  writeValue(参数1，obg)：
         *      参数1：
         *          File：
         *          Writer
         *          OutputStream
         *  writeValueAsString(obj)：将对象转为json字符串
         */
        // String json = mapper.writeValueAsString(p);
        // System.out.println(json);//{"name":"zhangsan","age":13,"gender":"nan"}
        //mapper.writeValue(new File("/Users/hehe/Desktop/a.txt"),p);
        //mapper.writeValue(new FileWriter("/Users/hehe/Desktop/b.txt"),p);
        mapper.writeValue(new FileOutputStream("/Users/hehe/Desktop/c.txt"), p);


    }

    @Test
    public void test2() throws IOException {
        //1、创建Person对象
        Person p = new Person("zhangsan", 13, "nan");
        p.setBirthday(new Date());
        //创建jackson核心对象
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(p);
        System.out.println(json);//{"name":"zhangsan","age":13,"gender":"nan"}


    }

    @Test
    public void test3() throws IOException {
        //1、创建Person对象
        Person p = new Person("zhangsan", 13, "nan");
        p.setBirthday(new Date());
        Map ps = new HashMap();
        ps.put("p", p);
        ps.put("p2", p);
        //创建jackson核心对象
        ObjectMapper mapper = new ObjectMapper();

        String json = mapper.writeValueAsString(ps);
        System.out.println(json);//{"name":"zhangsan","age":13,"gender":"nan"}


    }

    @Test
    public void test4() throws IOException {
        String json = "{\"name\":\"zhangsan\",\"age\":13,\"gender\":\"nan\"}";
        ObjectMapper mapper = new ObjectMapper();
        Person person = mapper.readValue(json, Person.class);
        System.out.println(person);


    }
}
