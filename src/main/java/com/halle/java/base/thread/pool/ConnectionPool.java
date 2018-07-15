package com.halle.java.base.thread.pool;

import java.sql.Connection;
import java.util.LinkedList;

public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<Connection>();
    public ConnectionPool(int initSize){
        if(initSize>0){
            for (int i = 0; i < initSize; i++) {
                pool.addLast(ConnectionDriver.newConnection());
            }
        }

    }



    public void releaseConnection(Connection connection){
        synchronized (pool){
            pool.addLast(connection);
            pool.notifyAll();
        }
    }


    /**
     * 从线程池获取连接，若超时返回null
     * @param mills mills<0 若线程池没有可用连接则一直阻塞
     * @return
     */
    public Connection getConnection(long mills) throws InterruptedException{
        synchronized (pool){
            if(mills < 0){
                while(pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else{
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while(pool.isEmpty() && remaining > 0){
                    pool.wait(mills);
                    remaining = future - System.currentTimeMillis();
                }

                /**
                 * 在41行pool.wait时，线程释放了锁，有可能线程池又拥有了线程
                 * 再进行一次尝试获取操作
                 */
                if(!pool.isEmpty())
                    return pool.removeFirst();

                return null;
            }

        }
    }

}
