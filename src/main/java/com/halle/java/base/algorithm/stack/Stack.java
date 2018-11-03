package com.halle.java.base.algorithm.stack;

public class Stack<T> {
    private String[] stackArray;
    private int top=-1;
    public Stack(int size){
        stackArray =new String[size];
    }

    /**
     * 入栈
     * @param i
     */
    public synchronized void push(String i){
        if(top >= stackArray.length-1) throw new RuntimeException("stack is full");
        top = top + 1;
        stackArray[top] = i;

    }

    /**
     * 出栈
     * @return
     */
    public synchronized String pop(){
        if(top <= -1) {
            String ret =  stackArray[0];
            stackArray[0] = null;
            return ret;
        }else{
            String ret =  stackArray[top];
            stackArray[top] = null;
            top = top - 1;
            return ret;
        }
    }

    /**
     * 返回当前栈顶元素
     * @return
     */
    public synchronized String peek(){
        if(top == 0) {
            return  stackArray[0];
        }else{
            return  stackArray[top];
        }
    }

}
