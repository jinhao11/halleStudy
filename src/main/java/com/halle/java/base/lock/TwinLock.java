package com.halle.java.base.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 同一时刻有两个线程能够访问锁
 */
public class TwinLock implements Lock{

    private final Sync sync = new Sync(2);

    private static final class Sync extends AbstractQueuedSynchronizer{
        Sync(int count){
            if(count<=0){
                throw new IllegalArgumentException("数量应该大于0");
            }
            setState(count);
        }
        @Override
        protected int tryAcquireShared(int arg) {
            while(true){
                int current = getState();
                int newCount = current-arg;
                if(newCount<0 || compareAndSetState(current,newCount)){
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            while (true){
                int current  = getState();
                int newCount = current+arg;
                if(compareAndSetState(current,newCount)){
                    return true;
                }
            }
        }
    }
    public void lock() {
        sync.acquireShared(1);
    }

    public void lockInterruptibly() throws InterruptedException {

    }

    public boolean tryLock() {
        return false;
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    public void unlock() {
        sync.releaseShared(1);
    }

    public Condition newCondition() {
        return null;
    }
}
