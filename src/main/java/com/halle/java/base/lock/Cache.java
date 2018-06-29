package com.halle.java.base.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 基于RentrantReadWriteLock实现缓存读写的一致性
 */
public class Cache {
    static Map<String,Object> map = new HashMap<String,Object>();
    static ReentrantReadWriteLock rwlock = new ReentrantReadWriteLock();
    static Lock readLock = rwlock.readLock();
    static Lock writeLock = rwlock.writeLock();


    public static Object getValue(String key){
        readLock.lock();
        try{
           return map.get(key);
        }finally {
            readLock.unlock();
        }

    }

    public static boolean putValue(String key,Object value){
        writeLock.lock();
        try {
            map.put(key,value);
            return true;
        }finally {
            writeLock.unlock();
        }
    }


}
