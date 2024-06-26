package org.example;

public class LeetCode75 {
    /* STRING */
    // 1768. Merge Strings Alternately
    public String mergeAlternately(String word1, String word2) {
        int a = word1.length(), b = word2.length();
        char[] arr = new char[a + b];
        int i = 0, j = 0, index = 0;
        while (i < a && j < b) {
            if (index % 2 == 0) {
                arr[index] = word1.charAt(i++);
            } else {
                arr[index] = word2.charAt(j++);
            }
            index++;
        }

        while (i < a) {
            arr[index] = word1.charAt(i++);
            index++;
        }
        while (j < b) {
            arr[index] = word2.charAt(j++);
            index++;
        }
        return String.valueOf(arr);
    }

    // 443. String Compression
    public int compress(char[] chars) {
        if (chars.length == 1) return 1;
        int i = 0, res = 0;
        while (i < chars.length) {
            int groupLength = 1;
            while (i + groupLength < chars.length && chars[i + groupLength] == chars[i]) {
                groupLength++;
            }
            chars[res++] = chars[i];
            if (groupLength > 1) {
                for (char c : Integer.toString(groupLength).toCharArray()) {
                    chars[res++] = c;
                }
            }
            i += groupLength;
        }
        return res;
    }

    // 605. Can Place Flowers
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
    }
}
