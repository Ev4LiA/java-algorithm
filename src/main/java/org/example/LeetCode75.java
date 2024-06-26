package org.example;

import java.util.ArrayList;
import java.util.List;

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
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 0) {
                boolean emptyLeft = i == 0 || flowerbed[i - 1] == 0;
                boolean emptyRight = i == flowerbed.length - 1 || flowerbed[i + 1] == 0;

                if (emptyLeft && emptyRight) {
                    flowerbed[i] = 1;
                    count++;
                }
            }
        }
        return count >= n;
    }

    // 1431. Kids With the Greatest Number of Candies
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        List<Boolean> res = new ArrayList<>();
        for (int candy : candies) {
            max = Math.max(max, candy);
        }
        for (int candy : candies) {
            if (candy + extraCandies >= max) {
                res.add(true);
            } else {
                res.add(false);
            }
        }
        return res;
    }

    // 345. Reverse Vowels of a String
    public String reverseVowels(String s) {
        String vowels = "aeiou";
        char[] arr = s.toCharArray();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && vowels.indexOf(arr[i]) < 0) {
                i++;
            }

            while (i < j && vowels.indexOf(arr[j]) < 0) {
                j--;
            }
            if (i < j) {
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;j--;
            }
        }
        return String.valueOf(arr);
    }

    /* PREFIX SUM */
    // 1732. Find the Highest Altitude
    public int largestAltitude(int[] gain) {
        int max = 0, peak = 0;
        for (int j : gain) {
            peak += j;
            max = Math.max(max, peak);
        }
        return max;
    }

    // 724. Find Pivot Index
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            if (left * 2 == sum - nums[i]) {
                return i;
            } else {
                left += nums[i];
            }
        }
        return -1;
    }
}
