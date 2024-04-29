package org.example.contest.weekly_contest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Contest395 {
    // 3131. Find the Integer Added to Array I
    public int addedInteger(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        return nums2[0] - nums1[0];
    }

    // 3132. Find the Integer Added to Array II
    public int minimumAddedInteger(int[] nums1, int[] nums2) {
        int res = Integer.MAX_VALUE;
        if (nums2.length == 1) {
            for (int j : nums1) {
                res = Math.min(nums2[0] - j, res);
            }
            return res;
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int n = nums1.length, m = nums2.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int diff = nums2[j] - nums1[i];
                map.put(diff, map.getOrDefault(diff, 0) + 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= m) {
                int diff = entry.getKey();
                int[] current = new int[n];
                for (int i = 0; i < n; i++) {
                    current[i] = nums1[i] + diff;
                }
                if (isMatch(current, nums2)) {
                    res = Math.min(diff, res);
                }
            }
        }
        return res;
    }

    private boolean isMatch(int[] nums1, int[] nums2) {
        int i = 0, j = 0, step = 2;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                i++; j++;
            } else {
                i++;
                step--;
                if (step < 0) {
                    return false;
                }
            }
        }

        return  j == nums2.length && (i - j) <= 2;
    }

    // 3133. Minimum Array End
    public long minEnd(int n, int x) {
        long a = x;
        while (n > 0) {
            a = (a + 1) | x;
            n--;
        }
        return a;
    }

    // 3134. Find the Median of the Uniqueness Array
    public int medianOfUniquenessArray(int[] A) {
        int n = A.length, left = 1, right = n;
        long total = (long) n * (n + 1) / 2;
        while (left < right) {
            int k = (left + right) / 2;
            if (atmost(A, k) * 2 >= total) {
                right = k;
            } else {
                left = k + 1;
            }
        }
        return left;
    }

    private long atmost(int[] A, int k) {
        long res = 0;
        Map<Integer, Integer> count = new HashMap<>();
        int i = 0;
        for (int j = 0; j < A.length; j++) {
            count.put(A[j], count.getOrDefault(A[j], 0) + 1);
            while (count.size() > k) {
                count.put(A[i], count.get(A[i]) - 1);
                if (count.get(A[i]) == 0) {
                    count.remove(A[i]);
                }
                i++;
            }
            res += j - i + 1;
        }
        return res;
    }
}
