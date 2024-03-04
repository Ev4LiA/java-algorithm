package org.example.string;

import java.util.Arrays;
import java.util.HashMap;

public class StringAlgorithm {
    // 13. Roman to Integer
    public int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int res = 0, prevValue = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int a = map.get(s.charAt(i));
            if (a >= prevValue) {
                res += a;
            } else {
                res -= a;
            }
            prevValue = a;
        }
        return res;
    }

    // 14. Longest Common Prefix
    public String longestCommonPrefix(String[] strs) {
        String result = "";
        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length - 1];
        for (int i = 0; i < Math.min(first.length(), last.length()); i++) {
            if (first.charAt(i) != last.charAt(i)) {
                return result;
            } else {
                result = result + first.charAt(i);
            }
        }
        return result;
    }

    // 28. Find the Index of the First Occurrence in a String
    public int strStr(String haystack, String needle) {
        char a = needle.charAt(0);
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.charAt(i) == a) {
                String s = haystack.substring(i, i + needle.length());
                if (s.equals(needle)) {
                    return i;
                }
            }
        }
        return -1;
    }

    // 58. Length of Last Word
    public int lengthOfLastWord(String s) {
        boolean flag = false;
        int count = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ' && flag) {
                return count;
            } else if (s.charAt(i) == ' ') {
                continue;
            } else {
                count++;
                flag = true;
            }
        }
        return count;
    }

    // 125. Valid Palindrome
    public boolean isPalindrome(String s) {
        if (s.isEmpty()) return true;
        int start = 0;
        int end = s.length() - 1;
        while (start <= end) {
            Character first = s.charAt(start);
            Character last = s.charAt(end);
            if (!Character.isLetterOrDigit(first)) {
                start++;
            } else if (!Character.isLetterOrDigit(last)) {
                end--;
            } else {
                if (Character.toLowerCase(first) == Character.toLowerCase(last)) {
                    start++;
                    end--;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    // 977. Squares of a Sorted Array
    public int[] sortedSquares(int[] nums) {
        int i = 0, j = nums.length - 1, k = nums.length - 1;
        int[] res = new int[nums.length];
        while (k >= 0) {
            if (nums[j] > Math.abs(nums[i])) {
                res[k] = nums[j] * nums[j];
                j--;
            } else {
                res[k] = nums[i] * nums[i];
                i++;
            }
            k--;
        }
        return res;
    }


    // 1929. Concatenation of Array
    public int[] getConcatenation(int[] nums) {
        int[] result = new int[nums.length * 2];
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[i];
            result[i + nums.length] = nums[i];
        }
        return result;
    }

    public int[] getConcatenation2ndMethod(int[] nums) {
        int[] ans = new int[nums.length * 2];
        System.arraycopy(nums, 0, ans, 0, nums.length);
        System.arraycopy(nums, 0, ans, nums.length, nums.length);
        return ans;
    }
}
