package com.halle.java.base.algorithm.swordoffer;

import java.util.Stack;

public class ReverseLinkedList {
    private Stack<ListNode> stack;
    public ListNode reverseList(ListNode head) {
        if(head==null){
            return null;
        }
        stack = new Stack<>();
        ListNode pointer = head;
        while(pointer != null) {
            stack.push(pointer);
            pointer = pointer.next;
        }
        ListNode newHead =  stack.pop();
        ListNode newPointer = newHead;
        while(newPointer != null) {
            if(stack.isEmpty()){
                newPointer.next = null;
                break;
            }
            ListNode node = stack.pop();
            newPointer.next=node;
            newPointer=node;
        }
        return newHead;
    }

    public ListNode reverseList2(ListNode head) {
       return null;
    }

    public static void main(String[] args) {
        ListNode l1 =  new ListNode(1);
        l1.next=new ListNode(2);
        l1.next.next = new ListNode(3);
        System.out.println(new ReverseLinkedList().reverseList(l1));
    }
}



class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }

}
