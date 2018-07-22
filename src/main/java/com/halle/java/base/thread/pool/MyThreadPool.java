package com.halle.java.base.thread.pool;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class MyThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    //最大线程数
    private static final int MAX_WORKER_NUMBERS = 200;

    //线程池默认数量
    private static final int DEFAULT_WORKER_NUMBERS = 5;

    //线程池最小的数量
    private static final int MIN_WORKER_NUM = 1;


    private LinkedBlockingQueue<Job> taskList = new LinkedBlockingQueue<Job>();

    private ConcurrentLinkedQueue<Worker> workers = new ConcurrentLinkedQueue<Worker>();


    private volatile AtomicInteger currWorkSize = new AtomicInteger(0);



    public MyThreadPool() {
        initializeWorkers(DEFAULT_WORKER_NUMBERS);
    }

    public MyThreadPool(int num) {
        int workSize = num > MAX_WORKER_NUMBERS ? MAX_WORKER_NUMBERS : (num < MIN_WORKER_NUM ? MIN_WORKER_NUM : num);
        currWorkSize = new AtomicInteger( workSize );
        initializeWorkers(workSize);
    }

    /**
     * 初始化线程
     * @param num
     */
    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker w = new Worker("ThreadPool-Worker-"+ currWorkSize.getAndIncrement());
            workers.add(w);
            w.start();
        }
    }


    public void execute(Job job) {
        if(job != null){
            taskList.add(job);
        }
    }

    public void shutdown() {
        synchronized (workers){
            for (Worker worker : workers) {
                worker.shutdown();
            }
        }

    }

    public void addWorkers(int num) {
        synchronized (workers){
            if( currWorkSize.get() + num > MAX_WORKER_NUMBERS){
                num = MAX_WORKER_NUMBERS - currWorkSize.get();
            }
            initializeWorkers(num);

        }

    }

    public void removeWorks(int num) {
        synchronized (workers){
            if( currWorkSize.get() - num < MIN_WORKER_NUM){
                num =  currWorkSize.get();
            }
            for (int i = 0; i < num; i++) {
                Worker w = workers.remove();
                w.shutdown();
                currWorkSize.decrementAndGet();
            }

        }
    }

    public int getJobSize() {
        return taskList.size();
    }


    /**
     * 工作者
     */
    class Worker extends Thread{
        private volatile  boolean running = true;

        Worker(String name){
            super(name);
        }

        public void run() {
            while( running ){
                try {
                    Job job = taskList.take();

                    if(job != null){
                        try {
                            job.run();
                        }catch (Exception e){
                            //忽略Job执行中的异常
                        }
                    }
                } catch (InterruptedException e) {
                    this.interrupt();
                    return;
                }
            }
        }

        public void shutdown(){
            running = false;
            this.interrupt();
        }
    }
}
