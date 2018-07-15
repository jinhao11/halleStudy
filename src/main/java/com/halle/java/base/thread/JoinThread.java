package com.halle.java.base.thread;

public class JoinThread {
    public static volatile boolean flag = true;
    public static void main(String[] args) {
        final Thread t1 = new Thread(new Runnable() {
            public void run() {
                int i = 0;
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1");

        try {
            t1.start();
            Thread t2 = new Thread(new Runnable() {
                public void run() {
                    try {
                        /**
                         *
                         */
                        synchronized (t1){
                            t1.wait();
                            System.out.println("t2 is notified");
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            t2.start();
            t1.join();

            System.out.println("main thread is over");


        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
