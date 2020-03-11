package com.halle.java.base.algorithm.leecode;

public class MyCircularQueue {
    private Integer innerArr[];
    private int arrSize;
    private int head = 0 ;
    private int end = 0 ;
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        innerArr = new Integer[k];
        arrSize = k;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if( isFull() ) return false;
        if( isEmpty() ){
            innerArr[end] = value;
            return true;
        }else{
            int mod = (end+1) %arrSize;
            end = mod;
            innerArr[end] = value;
            return  true;
        }
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if( isEmpty() ) return false;
        int mod = (head+1)%arrSize;
        innerArr[head] = null;
        head = mod;
        return  true;

    }

    /** Get the front item from the queue. */
    public int Front() {
        return  innerArr[head];

    }

    /** Get the last item from the queue. */
    public int Rear() {
        return  innerArr[end];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        if(end == head
                && innerArr[head] == null) return true;
        else return false;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        int mod = (end + 1)%arrSize;
        if(mod == head) return true;
        else return false;
    }

    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(3); // 设置长度为 3

        circularQueue.enQueue(1);  // 返回 true

        circularQueue.enQueue(2);  // 返回 true

        circularQueue.enQueue(3);  // 返回 true

        circularQueue.enQueue(4);  // 返回 false，队列已满

        circularQueue.Rear();  // 返回 3

        circularQueue.isFull();  // 返回 true

        circularQueue.deQueue();  // 返回 true

        circularQueue.enQueue(4);  // 返回 true

        circularQueue.Rear();  // 返回 4
    }
}
