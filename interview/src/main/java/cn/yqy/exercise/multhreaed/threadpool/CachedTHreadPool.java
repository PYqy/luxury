package cn.yqy.exercise.multhreaed.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 它是一种用来处理大量短时间工作任务的线程池，
 * 具有几个鲜明特点：它会试图缓存线程并重用，
 * 当无缓存线程可用时，就会创建新的工作线程；
 * 如果线程闲置的时间超过 60 秒，则被终止并移出缓存；
 * 长时间闲置时，这种线程池，不会消耗什么资源。
 * 其内部使用 SynchronousQueue 作为工作队列；
 */
public class CachedTHreadPool {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "excuting...");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "excuting...");
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "excuting...");
            }
        });
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "excuting...");
            }
        });

        executorService.execute(t1);
        executorService.execute(t2);
        executorService.execute(t3);
        executorService.execute(t4);
        executorService.shutdown();
    }
}
