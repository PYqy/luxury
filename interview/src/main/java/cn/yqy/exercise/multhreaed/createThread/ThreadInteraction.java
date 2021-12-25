package cn.yqy.exercise.multhreaed.createThread;

import sun.print.SunMinMaxPage;

/**
 * 子线程执行10次后，主线程在运行5次，这样交替执行三遍
 */

public class ThreadInteraction {

    public static void main(String[] args) throws InterruptedException {
        final Bussiness bussiness = new Bussiness();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    try {
                        bussiness.subMethod();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        for (int i = 0; i < 3; i++) {
            bussiness.mainMethod();
        }


    }
}


class Bussiness {
    private boolean subFlag = true;

    public synchronized void mainMethod() throws InterruptedException {

        while (subFlag) {
            wait();
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + ": main thread loop count --" + i);
            Thread.sleep(1000);

        }
        subFlag = true;
        notify();
    }

    public synchronized void subMethod() throws InterruptedException {
        while (!subFlag) {
            wait();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": sub thread loop count --" + i);
            Thread.sleep(1000);

        }
        subFlag = false;
        notify();
    }
}
