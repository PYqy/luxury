package com.yqy.test;
/*入门mybatis案例*/

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.yqy.dao.IUserDao;
import com.yqy.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    public static void main(String[] args) throws Exception {
//        读取配置文件
        InputStream is = Resources.getResourceAsStream("SqlMapConfig.xml");
//        创建sqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
//        使用工厂创建sqlsession对象
        SqlSession session = factory.openSession();
        IUserDao iUserDao = session.getMapper(IUserDao.class);
//        使用代理对象执行方法
        List<User> users = iUserDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
//        释放资源
        session.close();
        is.close();


    }
}
