package org.example.arrays;

import java.util.Arrays;

public class ArraysAlgorithm {
    // 189 Rotate Array
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    // 274. H-Index
    public int hIndex(int[] citations) {
        int n = citations.length;
        int[] bucket = new int[n + 1];
        for (int cite : citations) {
            if (cite >= n) {
                bucket[n]++;
            } else {
                bucket[cite]++;
            }
        }
        int count = 0;
        for (int i = n; i >= 0; i--) {
            count += bucket[n];
            if (count >= i) return i;
        }
        return 0;
    }
}
