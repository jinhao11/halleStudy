package com.halle.java.base.lock;

public class ConcurrentLinkedQueue<E> {
    volatile Node tail;
    volatile Node head;
    private static class Node<E>{
        volatile Node next;
        boolean casNext(Node<E> old,Node<E> newNode){
            return true;
        }
    }
    public boolean offer(E e){
        if(null == e) throw  new NullPointerException();
        Node<E> n  = new Node<E>();
        for(Node<E> t = tail,p=t;;){
            //p表示尾节点
            Node<E> q = p.next;
            if(q == null){
                //将新创建的节点插入p后
                if(p.casNext(null,n)){
                    //p!=t 证明有另外的线程进行了插入操作
                    if(p != t){
                        //让t指向p
                        casTail(t,p);
                    }

                    return true;
                }
            }else if( p == q){ //p.next = p p为首节点
                p = (t != (t = tail)) ? t : head;
            }else{

            }
        }

    }


    private boolean casTail(Node<E> old,Node<E> newNode){
        return true;
    }

    private Node succ(Node e){
        return null;
    }
}
