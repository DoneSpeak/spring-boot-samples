package io.github.donespeak.springbootsamples.jpa;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author DoneSpeak
 */
public class CallerRunsPolicyDemo {

    private static final int THREADS_SIZE = 1;
    private static final int CAPACITY = 1;

    public static void main(String[] args) throws Exception {
        ThreadLocal<Integer> threadLocal = new ThreadLocal<>();
        threadLocal.remove();
        threadLocal.remove();
    }

    public void haveATry() {

        System.out.println("currentThread: " + Thread.currentThread());
        // 创建线程池。线程池的"最大池大小"和"核心池大小"都为1(THREADS_SIZE)，"线程池"的阻塞队列容量为1(CAPACITY)。
        ThreadPoolExecutor pool = new ThreadPoolExecutor(THREADS_SIZE, THREADS_SIZE, 0, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(CAPACITY));
        // 设置线程池的拒绝策略为"CallerRunsPolicy"
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());


        // 新建10个任务，并将它们添加到线程池中。
        for (int i = 0; i < 10; i++) {
            MyRunnable myrun = new MyRunnable("task-"+i);
            pool.execute(myrun);
            System.out.println(myrun.name + " execute.");
        }

        // 关闭线程池
        pool.shutdown();
    }

    static class MyRunnable implements Runnable {
        public String name;
        public MyRunnable(String name) {
            this.name = name;
        }
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread() + ": " + this.name + " is running: " + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println(Thread.currentThread() + ": " + this.name + " finish to run: " + System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}