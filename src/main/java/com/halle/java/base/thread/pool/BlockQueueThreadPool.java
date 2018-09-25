package com.halle.java.base.thread.pool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class BlockQueueThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    //最大线程数
    private static final int MAX_WORKER_NUMBERS = 10;

    //线程池默认数量
    private static final int DEFAULT_WORKER_NUMBERS = 5;

    //线程池最小的数量
    private static final int MIN_WORKER_NUM = 1;

    //任务集合
    private final LinkedBlockingQueue<Job> jobs = new LinkedBlockingQueue();

    //工作者集合
    private final List<Worker> workers =     Collections.synchronizedList(new ArrayList<Worker>());

    //工作者线程数
    private int workerNum = DEFAULT_WORKER_NUMBERS;

    //线程编号
    private AtomicInteger threadNum = new AtomicInteger();

    public BlockQueueThreadPool() {
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public BlockQueueThreadPool(int num) {
        workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : (num < MIN_WORKER_NUM ? MIN_WORKER_NUM : num);
    }

    /**
     * 初始化线程
     * @param num
     */
    private void initializeWorkers(int num) {

    }

    public void execute(Job job) {

    }

    public void shutdown() {

    }

    public void addWorkers(int num) {

    }

    public void removeWorks(int num) {

}

    public int getJobSize() {
        return jobs.size();
    }

    /**
     * 工作者
     */
    class Worker implements Runnable{
        private volatile  boolean running = true;
        public void run() {
            while( running ){

            }
        }

        public void shutdown(){
            running = false;
        }
    }
}
