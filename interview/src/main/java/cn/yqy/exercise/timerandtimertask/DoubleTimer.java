package cn.yqy.exercise.timerandtimertask;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 使用定时器，间隔4喵执行一次，在间隔两秒执行一次，依此类推
 */
public class DoubleTimer {
    public static volatile int count = 0;

    class TimerTaskCus extends TimerTask {

        @Override
        public void run() {
            count = (count + 1) % 2;
            System.err.println("Boom boom");
            new Timer().schedule(new TimerTaskCus(), 2000 + 2000 * count);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();
        timer.schedule(new DoubleTimer().new TimerTaskCus(), 2000 + 2000 * count);
        while (true) {
            System.out.println(new Date().getSeconds());
            Thread.sleep(1000);
        }
    }
}
