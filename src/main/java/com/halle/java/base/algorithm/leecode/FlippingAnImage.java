package com.halle.java.base.algorithm.leecode;

import org.junit.Test;

/**
 * Given a binary matrix A, we want to flip the image horizontally, then invert it, and return the resulting image.
 *
 * To flip an image horizontally means that each row of the image is reversed.  For example, flipping [1, 1, 0] horizontally results in [0, 1, 1].
 *
 * To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0. For example, inverting [0, 1, 1] results in [1, 0, 0].
 *
 * Example 1:
 *
 * Input: [[1,1,0],[1,0,1],[0,0,0]]
 * Output: [[1,0,0],[0,1,0],[1,1,1]]
 * Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
 * Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]
 * Example 2:
 *
 * Input: [[1,1,0,0],[1,0,0,1],[0,1,1,1],[1,0,1,0]]
 * Output: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * Explanation: First reverse each row: [[0,0,1,1],[1,0,0,1],[1,1,1,0],[0,1,0,1]].
 * Then invert the image: [[1,1,0,0],[0,1,1,0],[0,0,0,1],[1,0,1,0]]
 * Notes:
 *
 * 1 <= A.length = A[0].length <= 20
 * 0 <= A[i][j] <= 1
 *
 * https://leetcode.com/problems/flipping-an-image/
 */
public class FlippingAnImage {
    class Solution {
        public int[][] flipAndInvertImage(int[][] A) {
            int[][] resultA = new int[A.length][];

            for (int i = 0; i < A.length; i++) {
                int[] flipAndInvert = new int[A[i].length];
                for (int k = A[i].length-1, j = 0; k >= 0; k--,j++) {
                    int old = A[i][k];
                    if(old == 0){
                        flipAndInvert[j]=1;
                    }else{
                        flipAndInvert[j]=0;
                    }
                }
                resultA[i] = flipAndInvert;
            }
            return resultA;
        }

        //0与1互换可以采用异或运算来优化实现
        public int[][] flipAndInvertImage2(int[][] A) {
            int[][] resultA = new int[A.length][];

            for (int i = 0; i < A.length; i++) {
                int[] flipAndInvert = new int[A[i].length];
                for (int k = A[i].length-1, j = 0; k >= 0; k--,j++) {
                    flipAndInvert[j]=A[i][k] ^ 1;
                }
                resultA[i] = flipAndInvert;
            }
            return resultA;
        }
    }

    @Test
    public void testFlipAndInvertImage(){
        int[][] A = {{1,1,0},{1,0,1},{0,0,0}};
        int[][] A1 =  new Solution().flipAndInvertImage(A) ;
    }

    @Test
    public void test(){
        System.out.println(0^1);
        System.out.println(1^1);

    }


}
