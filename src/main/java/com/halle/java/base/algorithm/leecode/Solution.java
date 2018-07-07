package com.halle.java.base.algorithm.leecode;

import java.awt.datatransfer.Transferable;

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        /**
         * 获取两个集合的大小
         * 将两个集合转换成int类型的数组
         */
        int l1Size=1,l2Size =1;
        ListNode l1Next=l1;
        while (l1Next.next!=null){
            l1Size++;
            l1Next=l1Next.next;
        }
        ListNode l2Next=l2;
        while (l2Next.next!=null){
            l2Size++;
            l2Next=l2Next.next;
        }

        int[] l1Arr = transfer(l1, l1Size);
        int[] l2Arr = transfer(l2, l2Size);

        /**
         * 遍历数组，根据数字代表的位数*10
         *  注意特殊情况的处理
         */
        int l1Num=0;
        l1Next=l1;
        for (int i =l1Size-1,j=0; i >= 0 ; i--,j++) {
            int fact =  new Double( java.lang.Math.pow((double) 10,(double)i) ).intValue();
            if(l2Next.val == 0){
                if(i==0){
                    l1Num = l1Num+i;
                }else{
                    l1Num = l1Num+fact;
                }
            }else{
                l1Num = l1Num+l1Arr[j]*fact;
            }
            l1Next = l1Next.next;
        }

        int l2Num=0;

        l2Next=l2;
        for (int i =l2Size-1,j=0; i >= 0 ; i--,j++) {
            int fact =  new Double( java.lang.Math.pow((double) 10,(double)i) ).intValue();
            if(l2Next.val == 0){
                if(i==0){
                    l2Num = l2Num+i;
                }else{
                    l2Num = l2Num+fact;
                }
            }else{
                l2Num = l2Num+l2Arr[j]*fact;
            }
            l2Next = l2Next.next;
        }
        int retNum = l1Num+l2Num;

        return transferArrToListNode(intToIntArr(retNum));
        
    }

    /*int l1Num=0;
    l1Next=l1;
        for (int i =l1Size-1,j=0; i >= 0 ; i--,j++) {
        int fact =  new Double( java.lang.Math.pow((double) 10,(double)i) ).intValue();
        if(l2Next.val == 0){
            if(i==0){
                l1Num = l1Num+i;
            }else{
                l1Num = l1Num+fact;
            }
        }else{
            l1Num = l1Num+l1Arr[j]*fact;
        }
        l1Next = l1Next.next;
    }

    int l2Num=0;

    l2Next=l2;
        for (int i =l2Size-1,j=0; i >= 0 ; i--,j++) {
        int fact =  new Double( java.lang.Math.pow((double) 10,(double)i) ).intValue();
        if(l2Next.val == 0){
            if(i==0){
                l2Num = l2Num+i;
            }else{
                l2Num = l2Num+fact;
            }
        }else{
            l2Num = l2Num+l2Arr[j]*fact;
        }
        l2Next = l2Next.next;
    }*/

    int[] intToIntArr(int retNum){
        String newString = Integer.toString(retNum);
        char[] cArr = newString.toCharArray();
        int[] iArr = new int[cArr.length];
        for (int i = 0; i < cArr.length; i++) {
            iArr[i] = (Integer.valueOf( Character.toString(cArr[i]) ));
        }
        return iArr;
    }
    /**
     *
     * @param arr
     * @return
     */
    ListNode transferArrToListNode(int[] arr){
        ListNode list = new ListNode(-1);
        ListNode currNode = list;

        for (int i : arr) {
            if(currNode == null)currNode = new ListNode(i);
            else if(currNode.val == -1) currNode.val=i;

            currNode = currNode.next;
        }
        return list;
    }
    int[] transfer(ListNode l,int size){
        int[] lArr = new int[size];
        ListNode tempNode=l;
        for (int i = size -1; i >= 0; i--,tempNode=tempNode.next) {
            lArr[i] = tempNode.val;
        }
        return lArr;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);


        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        System.out.println( s.addTwoNumbers(l1,l2) );
    }

}


    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
