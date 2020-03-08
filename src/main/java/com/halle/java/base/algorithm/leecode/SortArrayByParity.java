package com.halle.java.base.algorithm.leecode;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SortArrayByParity {
    static class Solution {
        public int[] sortArrayByParity(int[] A) {
            Set<Integer> evenIndexSet = new HashSet<>();
            for (int i = 0; i < A.length; i++) {
                if(A[i] % 2 == 0){
                    evenIndexSet.add(i);
                }
            }
            int[] aCopy = new int[A.length];
            Iterator<Integer> iterator = evenIndexSet.iterator();
            for (int i = 0; i < evenIndexSet.size(); i++) {
                aCopy[i] = A[iterator.next()];
            }

            for (int i = 0,aCopyIndex=evenIndexSet.size(); i < A.length; i++) {
                if(!evenIndexSet.contains(i)){
                    aCopy[aCopyIndex]=A[i];
                    aCopyIndex++;
                }
            }
            return aCopy;
        }

        public int[] sortArrayByParity2(int[] A) {
            int i = 0,oddIndex = A.length-1;
            while(i<oddIndex){
                if(A[i] % 2 != 0){
                    swap(A,i,oddIndex);
                    oddIndex--;
                }else{
                    i++;
                }
            }
            return A;
        }

        public void swap(int[] A,int i,int oddIndex){
            int temp = A[oddIndex];
            A[oddIndex] = A[i];
            A[i] = temp;
        }

    }

    public static void main(String[] args) {
        new Solution().sortArrayByParity2(new int[]{3,1,2,4});
    }
}
