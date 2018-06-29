package com.halle.java.base.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundeQueue<T> {
    private Object[] item;
    private volatile int addIndex,removeIndex,count;

    private Lock lock = new ReentrantLock();
    private Condition notEmpty = lock.newCondition();
    private Condition notFull = lock.newCondition();

    public BoundeQueue(int size){
        item = new Object[size];
    }

    public void add(T t) throws InterruptedException{
        lock.lock();
        try{
            while(count == item.length){
                notFull.await();
            }
            item[addIndex] = t;
            //TODO
            if(++addIndex == item.length) addIndex = item.length;
            count++;

            notEmpty.signal();
        }finally {
            //fix bug
            lock.unlock();
        }

    }
    public T remove() throws InterruptedException{
        lock.lock();
        try {
            while (count == 0){
                notEmpty.await();
            }

            T o = (T)item[removeIndex];
            item[removeIndex] = null;
            if(--removeIndex == 0) removeIndex=0;
            count--;
            notFull.signal();
            return o;
        }finally {
            lock.unlock();
        }

    }
}
