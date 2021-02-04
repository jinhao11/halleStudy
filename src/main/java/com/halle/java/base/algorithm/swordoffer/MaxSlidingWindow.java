package com.halle.java.base.algorithm.swordoffer;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 *
 * 返回滑动窗口中的最大值。
 例 1：

 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 输出：[3,3,5,5,6,7]
 解释：
 滑动窗口的位置                最大值
 ---------------               -----
 [1  3  -1] -3  5  3  6  7      3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 */
public class MaxSlidingWindow {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0 || k == 0) return new int[0];
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        // 未形成窗口
        for(int i = 0; i < k; i++) {
            //deque中队列尾部为当前窗口最大值
            while(!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);
        }
        res[0] = deque.peekFirst();
        // 形成窗口后
        for(int i = k; i < nums.length; i++) {
            if(deque.peekFirst() == nums[i - k])
                deque.removeFirst();
            while(!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);
            res[i - k + 1] = deque.peekFirst();
        }
        return res;
    }


    public int[] maxSlidingWindowOther(int[] nums, int k) {
            if( k<=0 || nums.length<=0 || k>nums.length ) {
                return new int[]{};
            }
            int index =  0;
            int moveTime = (nums.length-k)+1;
            int retArr[] = new int[moveTime];
            Integer max = null;
            Integer maxIndex = null;
            while(index<=nums.length-k){
                if(max == null){
                    for(int j = index;j<index+k;j++){
                        if(max==null){
                            max=  nums[j];
                            maxIndex = j;
                        }
                        if(nums[j]>max){
                            max=nums[j];
                            maxIndex = j;
                        }
                    }
                    retArr[index]=max;
                    index++;
                }else{
                    //若上一次的最大值还在滑动窗口内，则只要判断扩大的数值是否和最大值相等即可
                    if(maxIndex<=index+k && maxIndex>=index){
                        if(max<=nums[index+k-1]){
                            max = nums[index+k-1];
                            maxIndex = index+k-1;
                        }
                        retArr[index] = max;
                        index++;
                    }else {
                        max=null;
                        maxIndex=null;
                    }


                }
                /*for(int j = index;j<index+k;j++){
                    if(max==null){
                      max=  nums[j];
                    }
                    if(nums[j]>max){
                        max=nums[j];
                    }
                }
                retArr[index]=max;
                index++;*/
            }
            return retArr;

    }
    public static void main(String[] args) {
        MaxSlidingWindow maxSlidingWindow = new MaxSlidingWindow();
        maxSlidingWindow.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3);
    }
}
