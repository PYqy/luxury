package cn.yqy.elasticsearch.repository;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
/**
 * 包含所有field属性并设为可见（@JsonAutoDetect(fieldVisibility=Visibility.ANY)）；
 * 或者取消getter方法的自动检测(@JsonAutoDetect(getterVisibility=Visibility.NONE)),也可以联合使用
// */
//@JsonAutoDetect(fieldVisibility= JsonAutoDetect.Visibility.ANY,getterVisibility= JsonAutoDetect.Visibility.NONE)
public class Demo {
    private String ABC;
    private String CVB;
    private String CPB;
    private Demo2 d;

    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String abc = new Demo().ABC;
        Demo demo = new Demo("1", "1", "1",Demo2.builder().DEMO2("demo2").DonjCi("demo2").build());
        String s = objectMapper.writeValueAsString(demo);
        System.out.println(s);
        System.out.println();
        //Map readValue = objectMapper.readValue(demo, Map.class);
        //System.out.println(readValue);
        System.out.println("-----------");
        Gson gson = new Gson();
        System.out.println(gson.toJson(demo));


    }

}
