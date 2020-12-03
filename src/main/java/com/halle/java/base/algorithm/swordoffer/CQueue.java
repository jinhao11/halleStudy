package com.halle.java.base.algorithm.swordoffer;

import java.util.Iterator;
import java.util.Stack;

/**
 * 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 * 使用辅助栈完成对头元素出栈
 */

public class CQueue {
    Stack<Integer> stack1 = new Stack();
    Stack<Integer> stack2 = new Stack();

    public CQueue() {

    }

    public void appendTail(int value) {
        synchronized (CQueue.class){
            if(stack1.isEmpty()){
                stack2.push(value);
            }else{
                stack1.push(value);
            }
        }
    }

    public int deleteHead() {
        int head = -1;
        synchronized (CQueue.class){
            if(!stack1.isEmpty()){
                Iterator<Integer> iterator = stack1.iterator();
                Integer mem = -1;
                for (int i = 0; i < stack1.size(); i++) {
                    mem =  stack1.get(i);
                    if(i+1 != stack1.size()){
                        stack2.push(mem);
                    }
                }
               /* while (iterator.hasNext()){
                    mem =  iterator.next();
                    iterator.remove();
                    stack2.push(mem);
                }*/
                return mem;

            }else if(!stack2.isEmpty()){
                Integer mem = -1;
                for (int i = 0; i < stack2.size(); i++) {
                    mem =  stack2.get(i);
                    if(i+1 != stack2.size()){
                        stack1.push(mem);
                    }
                }
              /*  while (iterator.hasNext()){
                    mem =  iterator.next();
                    iterator.remove();
                    stack1.push(mem);
                }*/
                return mem;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        cQueue.appendTail(1);
        cQueue.appendTail(2);
        System.out.println(cQueue.deleteHead()+":"+cQueue.deleteHead()+":"+cQueue.deleteHead());
    }
}

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
