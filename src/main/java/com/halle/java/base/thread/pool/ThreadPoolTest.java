package com.halle.java.base.thread.pool;

import com.halle.java.base.tool.Doing;

import java.util.concurrent.CountDownLatch;

public class ThreadPoolTest {
    private  static final int  JOB_SIZE = 2000;

    private  static final int  WORKER_SIZE = 100;
    //工作花费的时间
    private static final int JOB_SPEND_TIME = 500;
    public static void main1() {
        long time = System.nanoTime();
        DefaultThreadPool pool = new DefaultThreadPool(WORKER_SIZE);
        final CountDownLatch latch = new CountDownLatch(JOB_SIZE);
        for (int i = 0; i < JOB_SIZE; i++) {
            pool.execute(new Runnable() {
                public void run() {
                    Doing.doSomethingSpend(JOB_SPEND_TIME);
                    latch.countDown();
                }
            });
        }
        try {
            latch.await();
            pool.shutdown();
            System.out.println("spend time is "+( System.nanoTime() - time)/1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static void main2() {
        long time =  System.nanoTime();
        MyThreadPool pool = new MyThreadPool(WORKER_SIZE);
        final CountDownLatch latch = new CountDownLatch(JOB_SIZE);
        for (int i = 0; i < JOB_SIZE; i++) {
            pool.execute(new Runnable() {
                public void run() {
                    Doing.doSomethingSpend(JOB_SPEND_TIME);
                    latch.countDown();
                }
            });
        }
        try {
            latch.await();
            pool.shutdown();
            System.out.println("spend time is "+( System.nanoTime() - time)/1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        main1();
        main2();
    }

}
