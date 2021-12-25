package cn.yqy.test;

import cn.yqy.util.JedisPoolUtils;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class JedisTest {
    @Test
    public void test() {
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.set("username", "zhangsan");
        jedis.close();
    }

    //String操作
    @Test
    public void test2() {
        Jedis jedis = new Jedis();
        jedis.set("username", "zhangsan");
        String username = jedis.get("username");
        String activecode = jedis.setex("activecode", 20, "1234");
        jedis.close();
    }

    //hash操作
    @Test
    public void test3() {
        Jedis jedis = new Jedis();
        jedis.hset("user", "name", "lisi");
        jedis.hset("user", "age", "19");
        jedis.hset("user", "gender", "male");
        Map<String, String> user = jedis.hgetAll("user");
        Set<String> strings = user.keySet();
        for (String key : strings) {
            String value = user.get(key);
            System.out.println(key + ":" + value);

        }
        jedis.close();
    }

    //list操作
    @Test
    public void test4() {
        Jedis jedis = new Jedis();
        jedis.lpush("mylist", "a", "b", "c");
        jedis.rpush("mylist", "a", "b", "c");
        List<String> mylist = jedis.lrange("mylist", 0, -1);
        jedis.lpop("mylist");
        jedis.rpop("mylist");
        jedis.close();
    }

    //set操作
    @Test
    public void test5() {
        Jedis jedis = new Jedis();
        jedis.sadd("myset", "a", "b");
        Set<String> myset = jedis.smembers("myset");
        jedis.srem("myset", "a");


        jedis.close();
    }

    //sortedset操作
    @Test
    public void test6() {
        Jedis jedis = new Jedis();
        jedis.zadd("mysort", 20, "lisi");
        jedis.zadd("mysort", 23, "wangwu");
        jedis.zadd("mysort", 40, "zhaoliu");
        jedis.zrem("mysort", "lisi");
        Set<String> mysort = jedis.zrange("mysort", 0, -1);

        jedis.close();
    }

    @Test
    public void test7() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(50);
        jedisPoolConfig.setMaxIdle(10);
        JedisPool JedisPool = new JedisPool(jedisPoolConfig, "locaohost", 6379);
        Jedis jedis = JedisPool.getResource();
        jedis.set("username", "zhangsan");
        jedis.close();
    }

    @Test
    public void test8() {
        Jedis jedis = JedisPoolUtils.getJedis();

        jedis.close();
    }

    @Test
    public void test9() {
        lableB:
        for (int i = 1; i < 1000; i++) {
            if (i == 2) {
                System.out.println(i);
                break lableB;
            }
        }
    }
}
