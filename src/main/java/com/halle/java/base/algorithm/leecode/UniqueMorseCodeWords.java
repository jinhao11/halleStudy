package com.halle.java.base.algorithm.leecode;

import org.junit.Test;

import java.sql.SQLOutput;
import java.util.HashSet;

/**
 * International Morse Code defines a standard encoding where each letter is mapped to a series of dots and dashes, as follows: "a" maps to ".-", "b" maps to "-...", "c" maps to "-.-.", and so on.
 *
 * For convenience, the full table for the 26 letters of the English alphabet is given below:
 *
 * [".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."]
 * Now, given a list of words, each word can be written as a concatenation of the Morse code of each letter. For example, "cba" can be written as "-.-..--...", (which is the concatenation "-.-." + "-..." + ".-"). We'll call such a concatenation, the transformation of a word.
 *
 * Return the number of different transformations among all words we have.
 *
 * Example:
 * Input: words = ["gin", "zen", "gig", "msg"]
 * Output: 2
 * Explanation:
 * The transformation of each word is:
 * "gin" -> "--...-."
 * "zen" -> "--...-."
 * "gig" -> "--...--."
 * "msg" -> "--...--."
 *
 * There are 2 different transformations, "--...-." and "--...--.".
 *
 * https://leetcode.com/problems/unique-morse-code-words/
 */
public class UniqueMorseCodeWords {
    class Solution {
        final String[] morseCodeTemple = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        public int uniqueMorseRepresentations(String[] words) {
            HashSet<String> wordMorseSet = new HashSet<>();
            for (String word : words) {
                char[] wordChars = word.toCharArray();
                StringBuilder morseCode = new StringBuilder();
                for (char wordChar : wordChars) {
                    morseCode.append(morseCodeTemple[wordChar - 'a']);
                }
                wordMorseSet.add(morseCode.toString());
            }

            return wordMorseSet.size();
        }
    }
    @Test
    public void testUniqueMorseRepresentations(){
        String[] input = {"gin", "zen", "gig", "msg"};
        System.out.println(new Solution().uniqueMorseRepresentations(input));
    }
}
