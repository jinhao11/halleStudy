package com.halle.java.base.thread.pool;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 引入了超时机制，可以很好的缓解负载过重的情况，保证线程池的正常运行
 */
public class ConnectionPoolTest {
    private static ConnectionPool pool = new ConnectionPool(10);
    //保证所有线程同时开始
    private static CountDownLatch start = new CountDownLatch(1);

    //保证main函数在所有线程执行完再执行
    private static CountDownLatch end ;

    public static void main(String[] args) {
        int size = 10000;//初始化10个线程
        AtomicInteger got = new AtomicInteger();
        AtomicInteger unGot = new AtomicInteger();
        long time = System.currentTimeMillis();
        end = new CountDownLatch(size);
        int count = 20;//每个线程执行获取20次线程的操作
        for (int i = 0; i < size; i++) {
            Thread t = new Thread(new Runner(count,got,unGot),"thread"+i);
            t.start();
        }
        start.countDown();


        try {
            end.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("got connection time is "+got);
        System.out.println("nogot connection time is "+unGot);
        System.out.println("spend time is "+(System.currentTimeMillis()-time)/1000);
    }


    static class Runner implements Runnable{
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public Runner(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        public void run() {
            try {
                start.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            while(count > 0){

                try {
                    Connection con = pool.getConnection(500);
                    if(con != null){
                        try{
                            con.commit();
                        }finally {
                            pool.releaseConnection(con);
                            got.incrementAndGet();
                        }
                    }else{
                        notGot.incrementAndGet();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                }finally {
                    count --;
                }
            }

            end.countDown();

        }
    }
}
