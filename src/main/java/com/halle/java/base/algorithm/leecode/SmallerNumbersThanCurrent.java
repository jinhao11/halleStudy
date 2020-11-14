package com.halle.java.base.algorithm.leecode;

import java.util.Arrays;
import java.util.HashMap;

public class SmallerNumbersThanCurrent {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] numsSmallerNumebers = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int lessSize =0;
            for (int i1 = 0; i1 < nums.length; i1++) {
                if(i!=i1){
                    if(nums[i]>nums[i1]){
                        lessSize++;
                    }
                }
            }
            numsSmallerNumebers[i]=lessSize;
        }
        return numsSmallerNumebers;
    }

    /**
     * other solution less foreach
     * 对数组进行排序，下标号对应就是数量将对应关系存入map实现
     * @param nums
     * @return
     */
    public int[] smallerNumbersThanCurrent2(int[] nums)
    {
        HashMap<Integer,Integer> hm=new HashMap<>();
        int[] org_nums=new int[nums.length];
        for(int i=0;i<nums.length;i++)
        {
            org_nums[i]=nums[i];
        }
        Arrays.sort(nums);
        for(int i=0;i<nums.length;i++)
        {
            if(!hm.containsKey(nums[i]))
            {
                hm.put(nums[i],i);
            }
        }
        for(int i=0;i<nums.length;i++)
        {
            int val=hm.get(org_nums[i]);
            nums[i]=val;
        }
        return nums;
    }


    public static void main(String[] args) {
        SmallerNumbersThanCurrent smallerNumbersThanCurrent = new SmallerNumbersThanCurrent();
        System.out.println( smallerNumbersThanCurrent.smallerNumbersThanCurrent2(new int[]{8,1,2,2,3}) );
        System.out.println( smallerNumbersThanCurrent.smallerNumbersThanCurrent2(new int[]{6,5,4,8}) );
        System.out.println( smallerNumbersThanCurrent.smallerNumbersThanCurrent2(new int[]{7,7,7,7}) );

    }

}
