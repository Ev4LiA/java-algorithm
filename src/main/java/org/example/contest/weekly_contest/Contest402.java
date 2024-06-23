package org.example.contest.weekly_contest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class Contest402 {
    // Count Pairs That Form a Complete Day I
    public int countCompleteDayPairs(int[] hours) {
        int res = 0;
        int[] arr = new int[24];
        for (int hour : hours) {
            int remainder = hour % 24;
            if (remainder == 0 && arr[0] > 0) {
                res += arr[0];
            } else if (remainder != 0 && arr[24 - remainder] > 0) {
                res += arr[24 - remainder];
            }
            arr[remainder]++;
        }
        return res;
    }

    // Count Pairs That Form a Complete Day II
    public long countCompleteDayPairsII(int[] hours) {
        long res = 0;
        int[] arr = new int[24];
        for (int hour : hours) {
            int remainder = hour % 24;
            if (remainder == 0 && arr[0] > 0) {
                res += arr[0];
            } else if (remainder != 0 && arr[24 - remainder] > 0) {
                res += arr[24 - remainder];
            }
            arr[remainder]++;
        }
        return res;
    }

    // Maximum Total Damage With Spell Casting
    public long maximumTotalDamage(int[] power) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        TreeSet<Integer> set = new TreeSet<>();
        for (int pow : power) {
            freq.put(pow, freq.getOrDefault(pow, 0) + 1);
            set.add(pow);
        }

        ArrayList<Integer> sortedSet = new ArrayList<>(set);
        int n = set.size();

        if (n == 1) {
            return (long) sortedSet.get(0) * freq.get(sortedSet.get(0));
        }

        long[] dp = new long[n + 1];

        for (int i = 1; i <= n; i++) {
            long include = (long) sortedSet.get(i - 1) * freq.get(sortedSet.get(i - 1));

            for (int j = i - 1; j >= 0; j--) {
                if (sortedSet.get(i - 1) - sortedSet.get(j) > 2) {
                    include += dp[j + 1];
                    break;
                }
            }
            long exclude = dp[i - 1];
            dp[i] = Math.max(exclude, include);
        }
        return dp[n];
    }

    // Peaks in Array
    public List<Integer> countOfPeaks(int[] nums, int[][] queries) {
//        int n = nums.length, sum = 0;
//        int[] peaks = new int[n], prefixSum = new int[n];
//        List<Integer> res = new ArrayList<>();
//        peaks[0] = 0;
//        for (int i = 1; i < n; i++) {
//            if (i == n - 1) {
//                peaks[i] = 0;
//                continue;
//            }
//            if (peaks[i] > peaks[i - 1]) {
//                peaks[i] = 1;
//            } else {
//                peaks[i] = 0;
//            }
//            sum += peaks[i];
//            prefixSum[i] = sum;
//        }
//
//        for (int[] querry : queries) {
//            if (querry[0] == 2) {
//                int pos = querry[1], val = querry[2];
//                nums[pos] = val;
//                if (nums[pos] > nums[pos - 1] && nums[pos] == 0) {
//                    nums[pos] = 1;
//                    prefixSum[pos]++;
//                }
//
//                if (nums[pos + 1] > nums[pos] && nums[pos + 1] == 0) {
//                    nums[pos + 1] = 1;
//                    prefixSum[pos + 1]++;
//                }
//            }
//        }
        return null;
    }
}
