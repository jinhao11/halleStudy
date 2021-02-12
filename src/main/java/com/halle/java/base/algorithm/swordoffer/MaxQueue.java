package com.halle.java.base.algorithm.swordoffer;
/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
public class MaxQueue {
    int queueArr[];
    int head=0;
    int tail=0;
    int maxIndex=0;
    int arrSize = 5000;
    int defaultNum=0;
    public MaxQueue() {
        queueArr=new int[arrSize];
    }
    
    public int max_value() {
        if(head==tail && queueArr[head]==0) return -1;
        return queueArr[maxIndex];
    }
    
    public void push_back(int value) {
        /**
         * 设计循环队列
         * 初始化时：头指针不变，尾指针不变，maxIndex赋值为初始值
         * 继续增长：头指针不变，尾指针加1，maxIndex与新入队的值进行比较
         * 超过队列长度时，提示入队失败。判断条件尾指针+1等于头指针位置
         *
         * 数据移出时判断是否是移出最大元素，若移出最大元素则重新遍历判断最大元素位置
         */

        if(head==tail && queueArr[head]==defaultNum){
            //System.out.println("队列空,第一次数据插入");
            //初始插入时
            queueArr[head]=value;
            maxIndex=head;
        }else if(head==tail && queueArr[head]!=defaultNum){
            //队列中存在一个元素
            //System.out.println("队列存在值,第二次数据插入");
            tail=tail+1;
            queueArr[tail]=value;
            if(queueArr[maxIndex]<value){
                maxIndex=tail;
            }
        } else {
            //队列已经满了
            if( (tail+1)%arrSize == head){
               // System.out.println("队列已满");
                return ;
            }else{
                tail= (tail+1)%arrSize;
                queueArr[tail]=value;
                if(queueArr[maxIndex]<value){
                    maxIndex=tail;
                }
            }

        }

    }
    
    public int pop_front() {
        if(head==tail && queueArr[head]==0){
            //System.out.println("队列空");
            return -1;
        }else{
            int retVal = queueArr[head];
            queueArr[head]=defaultNum;
            if(head == tail){
                //队列还剩一个元素
               // System.out.println("队列还剩一个元素");
                maxIndex = tail;
                return retVal;
            }else{
                if(maxIndex==head){
                    //最大值被移除,需要重新获取最大值
                    int arrHead = (head+1)%arrSize;
                    if(arrHead==tail) {
                        //队列中还剩两个元素,一个将要被移除,故直接将tail赋值给maxIndex
                        maxIndex=tail;
                    }else{
                        maxIndex=arrHead;
                        for (int i = arrHead+1; i <= tail; i++) {
                            if(queueArr[i]>queueArr[maxIndex]){
                                maxIndex=i;
                            }
                        }
                    }
                }
                head=(head+1)%arrSize;
                return retVal;
            }

        }
    }

    public static void main(String[] args) {
        MaxQueue maxQueue = new MaxQueue();
        System.out.println(maxQueue.max_value());
        maxQueue.push_back(1);
        System.out.println(maxQueue.pop_front()+""+maxQueue.pop_front());

        MaxQueue maxQueue2 = new MaxQueue();
        maxQueue2.push_back(4);
        maxQueue2.push_back(3);
        maxQueue2.push_back(2);

        System.out.println(maxQueue2.pop_front());
        System.out.println(maxQueue2.pop_front()+""+ maxQueue2.pop_front());

    }
}

