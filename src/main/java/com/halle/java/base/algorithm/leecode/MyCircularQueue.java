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
        //队列满
        if( isFull() ) return false;
        //队列即将满
        if((end + 1)%arrSize == head){
            innerArr[end] = value;
            return true;
        }
        //队列空
        if(isEmpty()){
            innerArr[end] = value;
            return true;
        }
        int mod = (end+1) %arrSize;
        end = mod;
        innerArr[end] = value;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if( isEmpty() ) return false;
        if(head == end){
            innerArr[head] = null;
            return true;
        }
        int mod = (head+1)%arrSize;
        innerArr[head] = null;
        head = mod;
        return  true;

    }

    /** Get the front item from the queue. */
    public int Front() {
        if(isEmpty()) return -1;
        return  innerArr[head];

    }

    /** Get the last item from the queue. */
    public int Rear() {
        if(isEmpty()) return -1;
        return  innerArr[end %arrSize];
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
        if(mod == head
                && innerArr[end]!=null) return true;
        else return false;
    }

    public static void main(String[] args) {
        /*
        ["MyCircularQueue","enQueue","Rear","Rear","deQueue","enQueue","Rear","deQueue","Front","deQueue","deQueue","deQueue"]
[[6],[6],[],[],[],[5],[],[],[],[],[],[]]
         */
        MyCircularQueue circularQueue = new MyCircularQueue(6); // 设置长度为 3

        circularQueue.enQueue(6);  // 返回 true
        circularQueue.Rear();  // 返回 6
        circularQueue.deQueue();  // 返回 true

        circularQueue.enQueue(5);  // 返回 true

        circularQueue.Rear();  // 返回 5

        circularQueue.deQueue();  // 返回 5

        circularQueue.Front();  // -1

        circularQueue.deQueue();  // 返回 false;

        circularQueue.deQueue();  // 返回 false

        circularQueue.deQueue();  // 返回 false


         /*MyCircularQueue obj = new MyCircularQueue(3);
          boolean param_1 = obj.enQueue(1);
         boolean param_2 = obj.enQueue(2);
        int param_fr = obj.Front();
        int param_re = obj.Rear();
        boolean param_3 = obj.enQueue(3);
        boolean param_4 = obj.enQueue(4);
        boolean param_5 = obj.deQueue();
        boolean param_6 = obj.enQueue(4);
        obj.Rear();*/

    }
}
