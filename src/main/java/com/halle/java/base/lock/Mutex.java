package com.halle.java.base.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 独占锁，同一时刻只能有一个
 */
public class Mutex implements Lock{
    private static final int LOCKED = 1;
    private static final int UNLOCKED = 0;
    private static class Sync extends AbstractQueuedSynchronizer{
        @Override
        protected boolean tryAcquire(int arg) {
            if(compareAndSetState(UNLOCKED,LOCKED)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }

            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
           if(getState() == UNLOCKED){
               throw new IllegalMonitorStateException();
           }else{
               setExclusiveOwnerThread(null);
               setState(0);
               return true;
           }
        }


        @Override
        protected boolean isHeldExclusively() {
            return getState() == LOCKED;
        }


        Condition newCondition(){
            return new ConditionObject();
        }
    }
    private final Sync sync = new Sync();
    public void lock() {
        sync.acquire(LOCKED);
    }

    public void lockInterruptibly() throws InterruptedException {

    }

    public boolean tryLock() {
        return sync.tryAcquire(LOCKED);
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(LOCKED,unit.toNanos(time));
    }

    public void unlock() {
            sync.release(UNLOCKED);
    }

    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked(){
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads(){
        return sync.hasQueuedThreads();
    }






}
