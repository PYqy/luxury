package cn.yqy.collectiontest.maptest;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String, String>();//HashMap对象
        Map<String, String> tableMap = new Hashtable<String, String>();//HashTable对象


        map.put(null, null);
        System.out.println("hashMapde[Key]和[value]均可以为null：" + map.get(null));
        try {
            tableMap.put(null, "3");
            System.out.println(tableMap.get(null));
        } catch (Exception e) {
            System.out.println("[ERROR]:hashTable的[key]不能为null");
        }
        try {
            tableMap.put("3", null);
            System.out.println(tableMap.get("3"));
        } catch (Exception e) {
            System.out.println("[ERROR]:hashTable的[value]不能为null");
        }
    }
}
