package cn.yqy.exercise.multhreaed.createThread;


public class CreateThread {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        java.lang.Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}

class A extends Thread {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }
}
