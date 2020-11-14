package com.halle.java.base.algorithm.swordoffer;

import java.util.ArrayList;
import java.util.List;

public class ReversePrint {
    public int[] reversePrint2(ListNode head) {
            if(head==null)return new int[0];
            List<Integer> list = new ArrayList<>();
            ListNode nowNode = head;
            while(nowNode != null){
                list.add(nowNode.val);
                nowNode = nowNode.next;
            }
                int[] intArr = new int[list.size()];
            for (int i = list.size()-1,j=0; i >=0; i--) {
                intArr[j]=list.get(i);
                j++;
            }
            return intArr;

    }

    public int[] reversePrint(ListNode head) {
        if(head==null)return new int[0];
        int i=-1;
        ListNode nowNode = head;
        while(nowNode != null){
            ++i;
            nowNode = nowNode.next;
        }
        int[] intArr = new int[i+1];
        ListNode turn2 = head;
        int j = i;
        while(turn2 != null){
            intArr[j]=turn2.val;
            turn2 = turn2.next;
            --j;
        }
        return intArr;

    }


     class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }
}
