package com.halle.java.base.thread.pool;

public interface ThreadPool<Job extends Runnable> {
    /**
     * 执行Job任务
     * @param job
     */
    void execute(Job job);

    /**
     * 关闭线程池
     */
    void shutdown();

    /**
     * 增加工作线程数量
     * @param num
     */
    void addWorkers(int num);

    /**
     * 减少工作线程数量
     * @param num
     */
    void removeWorks(int num);

    /**
     * 得到当前正在执行的线程数量
     * @return
     */
    int getJobSize();
}
