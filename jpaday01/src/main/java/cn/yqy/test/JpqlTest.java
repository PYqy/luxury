package cn.yqy.test;

import cn.yqy.utils.JpaUtils;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class JpqlTest {
    @Test
    public void testFindAll() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String jpq = "from cn.yqy.domain.Customer";
        Query query = entityManager.createQuery(jpq);
        List resultList = query.getResultList();
        for (Object o : resultList
                ) {
            System.out.println(o);

        }

        transaction.commit();
        entityManager.close();
    }

    @Test
    public void testOrderBy() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String jpq = "from Customer order by custId desc";
        Query query = entityManager.createQuery(jpq);
        List resultList = query.getResultList();
        for (Object o : resultList
                ) {
            System.out.println(o);

        }

        transaction.commit();
        entityManager.close();
    }

    @Test
    public void testCount() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String jpq = "select count(custId) from Customer";
        Query query = entityManager.createQuery(jpq);
        Object singleResult = query.getSingleResult();
        System.out.println(singleResult);

        transaction.commit();
        entityManager.close();
    }


    @Test
    public void testPages() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String jpq = "from cn.yqy.domain.Customer";
        Query query = entityManager.createQuery(jpq);
        query.setFirstResult(0);
        query.setMaxResults(1);
        List resultList = query.getResultList();

        for (Object o : resultList
                ) {
            System.out.println(o);

        }

        transaction.commit();
        entityManager.close();
    }

    @Test
    public void testNamte() {
        EntityManager entityManager = JpaUtils.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        String jpq = "from cn.yqy.domain.Customer where custName like ?";
        Query query = entityManager.createQuery(jpq);
        query.setParameter(1, "y%");
        List resultList = query.getResultList();

        for (Object o : resultList
                ) {
            System.out.println(o);

        }

        transaction.commit();
        entityManager.close();
    }
}
