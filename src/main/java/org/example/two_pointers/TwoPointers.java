package org.example.two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoPointers {
    // 11. Container With Most Water
    public int maxArea(int[] height) {
        int start = 0, end = height.length - 1;
        int max = 0;

        while (start < end) {
            int col = Math.min(height[start], height[end]);
            max = Math.max(max, (end - start) * col);
            if (height[start] < height[end]) {
                start++;
                while (height[start] < col) {
                    start++;
                }
            } else {
                end--;
                while (height[end] < col) {
                    end--;
                }
            }
        }
        return max;
    }

    // 15. 3Sum
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int start = i + 1, end = nums.length - 1;
            while (start < end) {

                int sum = nums[start] + nums[end] + nums[i];
                if (sum > 0) {
                    end--;
                } else if (sum < 0) {
                    start++;
                } else {
                    List<Integer> arr = new ArrayList<>();
                    arr.add(nums[i]);
                    arr.add(nums[start]);
                    arr.add(nums[end]);
                    res.add(arr);

                    while (start < end && nums[end] == nums[end - 1]) end--;

                    while (start < end && nums[start] == nums[start + 1]) start++;
                    start++; end--;
                }
            }
        }
        return res;
    }

    // 125. Valid Palindrome
    public boolean isPalindrome(String s) {
        if (s.isEmpty()) return true;

        int start = 0, end = s.length() - 1;
        while (start < end) {
            if (!Character.isLetterOrDigit(s.charAt(start))) {
                start++;
            } else if (!Character.isLetterOrDigit(s.charAt(end))) {
                end--;
            } else {
                if (Character.toLowerCase(s.charAt(start)) != Character.toLowerCase(s.charAt(end))) {
                    return false;
                } else {
                    start++;
                    end--;
                }
            }
        }
        return true;
    }

    // 167. Two Sum II - Input Array Is Sorted
    public int[] twoSum(int[] numbers, int target) {
        int start = 0, end = numbers.length - 1;
        while (start < end) {
            if (numbers[start] + numbers[end] == target) {
                return new int[]{start + 1, end + 1};
            } else if (numbers[start] + numbers[end] > target) {
                end--;
            } else {
                start++;
            }
        }
        return new int[]{-1, -1};
    }

    // 392. Is Subsequence
    public boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) return true;
        if (t.isEmpty() && !s.isEmpty()) {
            return false;
        }
        int sIdx = 0, tIdx = 0;
        while (sIdx < s.length() && tIdx < t.length()) {
            if (s.charAt(sIdx) == t.charAt(tIdx)) {
                sIdx++;
                tIdx++;
            } else {
                tIdx++;
            }
        }
        return sIdx == s.length();
    }
}
