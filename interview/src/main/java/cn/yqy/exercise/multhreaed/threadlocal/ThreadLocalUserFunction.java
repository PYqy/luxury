package cn.yqy.exercise.multhreaed.threadlocal;

public class ThreadLocalUserFunction {
    private static int nextserialNum = 0;
    private static ThreadLocal serialNum = new ThreadLocal() {
        @Override
        protected Object initialValue() {
            return new Integer(nextserialNum++);
        }
    };

    public static int get() {
        return ((Integer) serialNum.get()).intValue();
    }
}
