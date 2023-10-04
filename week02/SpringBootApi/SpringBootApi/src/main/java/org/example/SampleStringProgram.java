package org.example;

import java.util.Arrays;

class StringPermutation {
    private static String sortStr(String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        return new String(arr);
    }

    public static boolean isPermutation(String s1, String s2) {
        if (sortStr(s1).compareTo(sortStr(s2)) == 0) {
            return true;
        }
        return false;
    }
}


public class SampleStringProgram {

    public static void main(String[] args) {
        System.out.println(StringPermutation.isPermutation("cat", "tac"));
        System.out.println(StringPermutation.isPermutation("cattac", "tacca"));
        System.out.println(StringPermutation.isPermutation("battaa", "atatab"));
        System.out.println(StringPermutation.isPermutation("abb", "baa"));
    }
}
