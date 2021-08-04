package com.study.datastructure.자료구조;

import java.util.Arrays;
import java.util.HashMap;

public class string {
    /**
     * 문자열 안의 문자들이 고유한지 확인하기
     * @param str
     * @return boolean
     */
    private static boolean isUnique(String str){
        if (str.length() > 128) return false;
        boolean[] char_set = new boolean[128];
        for (int i = 0; i < str.length(); i++){
            int val = str.charAt(i);
            if (char_set[val]){
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }
    private static boolean isUnique2(String str){
        HashMap<Integer, Boolean> hashmap = new HashMap<Integer, Boolean>();
        for (int i = 0; i < str.length(); i++){
            int c = str.charAt(i);
            if (hashmap.containsKey(c)){
                return false;
            }
            hashmap.put(c, true);
        }
        return true;
    }
    private static boolean isUnique3(String str){
        int checker = 0;
        for (int i = 0; i < str.length(); i++){
            int val = str.charAt(i) - 'a';
            if ((checker & (1 << val)) > 0){
                return false;
            }
            checker |= (1 << val);
        }
        return true;
    }

    /**
     * 두개의 문자열이 서로 치환인지 알아내기
     */
    private static String sort(String s){
        char[] content = s.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }
    private static boolean permutation(String s, String t){
        if (s.length() != t.length()) return false;
        return sort(s).equals(sort(t));
    }
    private static boolean permutation2(String s, String t){
        if (s.length() != t.length()) return false;

        int[] letters = new int[128];
        for (int i = 0; i < s.length(); i++){
            letters[s.charAt(i)]++;
        }
        for (int i = 0; i < t.length(); i++){
            letters[t.charAt(i)]--;
            if (letters[t.charAt(i)] < 0) return false;
        }
        return true;
    }

    /**
     * 문자열안의 공백을 URL 인코딩하기
     */
    private static String URLify(String str, int len){
        return URLify(str.toCharArray(), len);
    }
    private static String URLify(char[] str, int len){
        int space = 0;
        for (int i = 0; i < len; i++){
            if (str[i] == ' ') space++;
        }
        int index = len + space * 2 - 1;
        for (int p = len - 1; p >= 0; p--){
            if (str[p] == ' ') {
                str[index--] = '0';
                str[index--] = '2';
                str[index--] = '%';
            } else {
                str[index--] = str[p];
            }
        }
        return new String(str);
    }

    /**
     * 문자열이 회문(palindrome)의 치환(permutation)인지 확인하기
     */
    private static boolean isPermutationOfPalindrome(String s){
        int[] table = buildCharFrequencyTable(s);
        return checkMaxOneOdd(table);
    }
    private static boolean isPermutationOfPalindrome2(String s){
        int countOdd = 0;
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a' + 1)];
        for (char c : s.toCharArray()){
            int x = getCharNumber(c);
            if (x != -1){
                table[x]++;
                if (table[x] % 2 == 1){
                    countOdd++;
                } else {
                    countOdd--;
                }
            }
        }
        return countOdd <= 1;
    }
    private static boolean isPermutationOfPalindrome3(String s){
        int bitVector = createBitVector(s);
        return bitVector == 0 || checkExactlyOneBitSet(bitVector);
    }
    private static int[] buildCharFrequencyTable(String s){
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
        for (char c : s.toCharArray()){
            int x = getCharNumber(c);
            if (x != -1){
                table[x]++;
            }
        }
        return table;
    }
    private static int getCharNumber(Character c){
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(c);
        if (a <= val && val <= z){
            return val - a;
        }
        return -1;
    }
    private static boolean checkMaxOneOdd(int[] table){
        boolean foundOdd = false;
        for (int count : table){
            if (count % 2 == 1){
                if (!foundOdd){
                    foundOdd = true;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
    private static int createBitVector(String s){
        int bitVector = 0;
        for (char c : s.toCharArray()){
            int x = getCharNumber(c);
            bitVector = toggle(bitVector, x);
        }
        return bitVector;
    }
    private static int toggle(int bitVector, int index){
        if (index < 0) return bitVector;
        int mask = 1 << index;
        if ((bitVector & mask) == 0){
            bitVector |= mask;
        } else {
            bitVector &= ~mask;
        }
        return bitVector;
    }
    private static boolean checkExactlyOneBitSet(int bitVector){
        return (bitVector & (bitVector - 1)) == 0;
    }

    /**
     * 단한번만 편집된 문자열인지 알아내기
     */
    private static boolean isOneAway(String s1, String s2){
        String ss, ls;
        if (s1.length() < s2.length()){
            ss = s1;
            ls = s2;
        } else {
            ss = s2;
            ls = s1;
        }
        if (ls.length() - ss.length() > 1) return false;
        Boolean flag = false;
        for (int i = 0, j = 0; i < ss.length(); i++, j++){
            if (ss.charAt(i) != ls.charAt(j)){
                if (flag) {
                    return false;
                }
                flag = true;
                if (ss.length() != ls.length()){
                    j++;
                }
            }
        }
        return true;
    }

    /**
     * 문자열 압축(Compression)하기
     */
    private static String compressString(String str){
        String newStr = compress(str);
        return str.length() < newStr.length() ? str : newStr;
    }
    private static String compress(String str){
        int count = 0;
        java.lang.StringBuilder newString = new java.lang.StringBuilder(getTotal(str));
        for (int i = 0; i < str.length(); i++){
            count++;
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)){
                newString.append(str.charAt(i));
                newString.append(count);
                count = 0;
            }
        }
        return newString.toString();
    }
    private static int getTotal(String str){
        int count = 0;
        int total = 0;
        for (int i = 0; i < str.length(); i++){
            count++;
            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)){
                total += 1 + String.valueOf(count).length();
                count = 0;
            }
        }
        return total;
    }

    /**
     * isSubstring()으로 문자열 회전여부 알아내기
     */
    private static boolean isRotation(String s1, String s2){
        if (s1.length() != s2.length()) return false;
        return isSubstring(s1 + s1, s2);
    }
    private static boolean isSubstring(String s1, String s2){
        return s1.contains(s2);
    }

    /**
     * 주어진 개수의 모든 가능한 문자열 조합중 정렬된것만 출력하기
     */
    public static final int c = 26;
    private static void printSortedStrings(int k){
        printSortedStrings(k, "");
    }
    private static void printSortedStrings(int k, String prefix){
        if (k == 0){
            if (isInOrder(prefix)){
                System.out.println(prefix);
            }
        } else {
            for (int i = 0; i < c; i++){
                char c = ithLetter(i);
                printSortedStrings(k - 1, prefix + c);
            }
        }
    }
    private static boolean isInOrder(String s){
        for (int i = 1; i < s.length(); i++){
            int prev = ithLetter(s.charAt(i - 1));
            int curr = ithLetter(s.charAt(i));
            if (prev > curr){
                return false;
            }
        }
        return true;
    }
    private static char ithLetter(int i){
        return (char) (((int) 'a') + i);
    }

    public static void main(String[] args) {
        System.out.println(isUnique("abcdefgghijk"));
        System.out.println(isUnique("abcdefghijk"));

        System.out.println(isUnique2("abcdefgghijk"));
        System.out.println(isUnique2("abcdefghijk"));

        System.out.println(isUnique3("abcdefgghijk"));
        System.out.println(isUnique3("abcdefghijk"));

        System.out.println(permutation("ABC", "BCA"));
        System.out.println(permutation("ABC", "BDA"));

        System.out.println(permutation2("ABC", "BCA"));
        System.out.println(permutation2("ABC", "BDA"));

        System.out.println(URLify("Mr John Smith          ", 13));

        System.out.println(isPermutationOfPalindrome("aa bb cc dd"));
        System.out.println(isPermutationOfPalindrome("aa bb cc dd e"));
        System.out.println(isPermutationOfPalindrome("aa bb cc dd e fff"));

        System.out.println(isPermutationOfPalindrome2("aa bb cc dd"));
        System.out.println(isPermutationOfPalindrome2("aa bb cc dd e"));
        System.out.println(isPermutationOfPalindrome2("aa bb cc dd e fff"));

        System.out.println(isPermutationOfPalindrome3("aa bb cc dd"));
        System.out.println(isPermutationOfPalindrome3("aa bb cc dd e"));
        System.out.println(isPermutationOfPalindrome3("aa bb cc dd e fff"));

        System.out.println(isOneAway("pal", "pale"));
        System.out.println(isOneAway("pale", "pal"));
        System.out.println(isOneAway("pale", "bale"));

        System.out.println(isOneAway("pal", "pales"));
        System.out.println(isOneAway("pale", "pel"));
        System.out.println(isOneAway("pale", "bake"));

        System.out.println(compressString("aabbbbbcccccdd"));
        System.out.println(compressString("abcd"));

        System.out.println(isRotation("string", "ringst"));
        System.out.println(isRotation("string", "ingstr"));
        System.out.println(isRotation("string", "ingstn"));
        System.out.println(isRotation("string", "ringstr"));

        printSortedStrings(2);
    }
}
