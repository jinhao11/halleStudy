package com.halle.java.base.algorithm.swordoffer;

import java.util.Stack;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 */
/*public class MinStack {
    private Integer min=null;
    private Stack<Integer> mainStack;
    //辅助栈
    private Stack<Integer> assistStack;
    *//** initialize your data structure here. *//*
    public MinStack() {
        mainStack =new Stack();
        assistStack = new Stack();
    }

    public void push(int x) {
        mainStack.push(x);
        if(assistStack.isEmpty()){
            assistStack.push(x);
        }else{
            if(assistStack.peek()>=x){
                assistStack.push(x);
            }
        }
    }

    public void pop() {
        Integer top = mainStack.pop();
        if(top.equals(assistStack.peek())){
            assistStack.pop();
        }
    }

    public int top() {
       return  mainStack.peek();
    }

    public int min() {
        return assistStack.peek();
    }

    public static void main(String[] args) {
        MinStack m =   new MinStack();
        m.push(2147483646);
        m.push(2147483646);
        m.push(2147483647);
        m.pop();
        m.pop();
        m.pop();
        m.push(2147483647);
        m.min();
    }
}*/

public class MinStack {
    // Stack<Integer> stack;
    // Stack<Integer> minVal;
    // /** initialize your data structure here. */
    // public MinStack() {
    //     stack=new Stack<>();
    //     minVal=new Stack<>();
    // }

    // public void push(int x) {
    //     stack.push(x);
    //     if(minVal.empty()||x<=minVal.peek()){
    //         minVal.push(x);
    //     }
    // }

    // public void pop() {
    //     int temp=stack.pop();
    //     if(temp==minVal.peek()){
    //         minVal.pop();
    //     }
    // }

    // public int top() {
    //     return stack.peek();
    // }

    // public int min() {
    //     return minVal.peek();
    // }
// 在元素入栈时判断当前元素是否小于最小元素，如果小于最小元素则先将原最小值入栈，再将当前最小元素入栈，这样当调用 pop 方法时，即使移除的是最小值，只需要将下一个元素取出即为新的最小值了，这样就可以实现调用 min、push 及 pop 方法时的时间复杂度都是 O(1) 了

    private int[] data; // 栈数据
    private int maxSize; // 最大长度
    private int top; // 栈顶指针（下标）
    private int min; // 最小值

    // 构造函数
    public MinStack() {
        // 设置默认值
        maxSize = 10000;
        data = new int[maxSize];
        top = -1;
        min = Integer.MAX_VALUE;
    }

    // 入栈（添加元素）
    public void push(int x) {
        if (min >= x) {
            // 遇到了更小的值，记录原最小值（入栈）
            data[++top] = min;
            min = x;
        }
        // 当前值入栈
        data[++top] = x;
}

    // 出栈（移除栈顶元素）
    public void pop() {
        if (min == data[top]) {
            min = data[--top]; // 拿到原最小值，并（将原最小值）出栈
        }
        --top; // 出栈
    }

    // 查找栈顶元素
    public int top() {
        return data[top];
    }

    // 查询最小值
    public int min() {
        return min;
    }

    public static void main(String[] args) {
        MinStack m =   new MinStack();
        m.push(3);
        m.push(4);
        m.push(5);
        m.push(2);
        m.push(7);
        m.push(1);
        System.out.println("top is:"+m.top()+" min is:"+m.min());
        m.pop();
        System.out.println("top is:"+m.top()+" min is:"+m.min());
        m.pop();
        System.out.println("top is:"+m.top()+" min is:"+m.min());
        m.pop();
        System.out.println("top is:"+m.top()+" min is:"+m.min());
        m.pop();
        System.out.println("top is:"+m.top()+" min is:"+m.min());
        m.pop();
        System.out.println("top is:"+m.top()+" min is:"+m.min());
    }
}
