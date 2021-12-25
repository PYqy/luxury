package cn.yqy.test;

import cn.yqy.domain.Customer;
import cn.yqy.utils.JpaUtils;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaTest {

    public static void main(String[] args) {
        /**
         * 测试jpa保存
         * 1、加载配置文件创建工厂（实体管理器工厂）对象
         * 2、通过实体管理器工厂获得实体管理器
         * 3、获取事务对象，开始事务
         * 4、完成增删改查操作
         * 5、提交事务
         * 6、释放资源
         */
//        EntityManagerFactory myJpa = Persistence.createEntityManagerFactory("myJpa");
//        EntityManager em = myJpa.createEntityManager();
        EntityManager em = JpaUtils.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Customer customer = new Customer();
        customer.setCustAddress("黑龙江省");
        customer.setCustIndustry("银行副行长");
        customer.setCustPhone("13492019433");
        customer.setCustName("yqy2");
        em.persist(customer);
        transaction.commit();
        em.close();


    }

    /**
     * 根据id查询客户
     * 使用find方法：
     * 查询对象就是当前对象本身
     * 在调用find方法的时候，就会发送sql语句查询数据库
     * 立即加载
     */
    @Test
    public void testfind() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = entityManager.find(Customer.class, 1l);
        System.out.println(customer);
        transaction.commit();
        entityManager.close();

    }

    /**
     * 根据id查询客户
     * getReference：
     * 查询对象是代理对象
     * 在调用find方法的时候，什么时候使用，什么时候发送sql
     * 延时加载
     */
    @Test
    public void testgetReference() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = entityManager.getReference(Customer.class, 1l);
        System.out.println(customer);
        transaction.commit();
        entityManager.close();

    }

    @Test
    public void testRemove() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = entityManager.getReference(Customer.class, 1l);
        entityManager.remove(customer);
        transaction.commit();
        entityManager.close();

    }

    @Test
    public void testMerge() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Customer customer = entityManager.getReference(Customer.class, 2l);
        customer.setCustIndustry("it");
        entityManager.merge(customer);
        transaction.commit();
        entityManager.close();

    }
}
