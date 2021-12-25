package cn.yqy.interviewprogram;

import sun.security.jca.GetInstance;

public class LazySingletionP491 {
    private static a instance = null;

    /**
     * 单例懒加载
     */
    private LazySingletionP491() {
    }

    private static class SingletonHolder {
        private static LazySingletionP491 l = new LazySingletionP491();
    }

    public static LazySingletionP491 genInstance() {
        return SingletonHolder.l;
    }

    //double check locking
    public static a getInstance2() {
        if (instance == null) {
            synchronized (instance) {
                if (instance == null) {
                    instance = new a();
                }
            }
        }
        return instance;
    }
}

class a {

}
