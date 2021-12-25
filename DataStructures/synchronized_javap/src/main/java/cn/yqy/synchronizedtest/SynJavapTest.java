package cn.yqy.synchronizedtest;

public class SynJavapTest {
    public synchronized void test1() {

    }
    public void test2() {
        synchronized (this) {

        }
    }
}
