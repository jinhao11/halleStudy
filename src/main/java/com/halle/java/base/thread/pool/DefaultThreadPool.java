package com.halle.java.base.thread.pool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    //最大线程数
    private static final int MAX_WORKER_NUMBERS = 200;

    //线程池默认数量
    private static final int DEFAULT_WORKER_NUMBERS = 5;

    //线程池最小的数量
    private static final int MIN_WORKER_NUM = 1;

    //任务集合
    private final LinkedList<Job> jobs = new LinkedList();

    //工作者集合
    private final List<Worker> workers =     Collections.synchronizedList(new ArrayList<Worker>());

    //工作者线程数
    private int workerNum = DEFAULT_WORKER_NUMBERS;

    //线程编号
    private AtomicInteger threadNum = new AtomicInteger();

    public DefaultThreadPool() {
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public DefaultThreadPool(int num) {
        workerNum = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : (num < MIN_WORKER_NUM ? MIN_WORKER_NUM : num);
        initializeWorkers(workerNum);
    }

    /**
     * 初始化线程
     * @param num
     */
    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker("ThreadPool-Worker-"+threadNum.getAndIncrement());
            workers.add(worker);
            worker.start();
        }
    }

    public void execute(Job job) {
        if(job != null){
            synchronized (jobs){
                jobs.addLast(job);
                jobs.notify();
            }
        }
    }

    public void shutdown() {
        synchronized (jobs){
            for (Worker worker : workers) {
                worker.shutdown();
            }
        }
    }

    public void addWorkers(int num) {
        synchronized (jobs){
            //保证新增的Worker数量不能超过最大值
            if( num + this.workerNum > MAX_WORKER_NUMBERS){
                num = MAX_WORKER_NUMBERS - this.workerNum;
            }
            initializeWorkers(num);
            this.workerNum+=num;
        }
    }

    public void removeWorks(int num) {
        synchronized (jobs){
            if( num >= this.workerNum){
                throw new IllegalArgumentException("beyond workNum");
            }
            //按照给定的数量停止Worker
            int count = 0;
            while (count < num){
                Worker worker = workers.get(count);
                if(workers.remove(worker)){
                    worker.shutdown();
                    count ++;
                }
            }

            this.workerNum = workerNum - count;
        }
    }

    public int getJobSize() {
        return jobs.size();
    }

    /**
     * 工作者
     */
    class Worker extends Thread{
        private volatile  boolean running = true;

        public Worker(String name) {
            super(name);
        }

        public void run() {
            while( running ){
                Job job = null;
                synchronized (jobs){
                    //如果工作者列表是空的，就wait
                    while(jobs.isEmpty()){
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            //感知到外部对WorkerThread的中断操作，返回
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    job = jobs.remove();
                }

                if(job != null){
                    try {
                        job.run();
                    }catch (Exception e){
                        //忽略Job执行中的异常
                    }
                }
            }
        }

        public void shutdown(){
            running = false;
            this.interrupt();
        }
    }
}
