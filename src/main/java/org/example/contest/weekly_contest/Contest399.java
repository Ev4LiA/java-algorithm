package org.example.contest.weekly_contest;

import java.util.HashMap;

public class Contest399 {
    // 3162. Find the Number of Good Pairs I
    public int numberOfPairs(int[] nums1, int[] nums2, int k) {
        int res = 0;
        for (int value : nums1) {
            if (value % k != 0) continue;
            for (int i : nums2) {
                if (value % (i * k) == 0) res++;
            }
        }
        return res;
    }

    // 3163. String Compression III
    public String compressedString(String word) {
        StringBuilder sb = new StringBuilder();
        int count = 1, i = 0;
        while (i < word.length() - 1) {
            if (word.charAt(i) == word.charAt(i + 1) && count < 9) {
                count++;
            } else {
                sb.append(count);
                sb.append(word.charAt(i));
                count = 1;
            }
            i++;
        }

        if (count != 1) {
            sb.append(count);
            sb.append(word.charAt(i));
        } else {
            sb.append(1);
            sb.append(word.charAt(i));
        }
        return sb.toString();
    }

    // 3164. Find the Number of Good Pairs II
    public long numberOfPairsII(int[] nums1, int[] nums2, int k) {
        long res = 0L;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums2) {
            map.put(num * k, map.getOrDefault(num * k, 0) + 1);
        }

        for (int num : nums1) {
            if (num % k != 0) continue;
            for (int i = 1; i * i <= num; i++) {
                if (num % i == 0) {
                    if (i != num / i) {
                        if (map.containsKey(i)) res += map.get(i);
                        if (map.containsKey(num / i)) res += map.get(num / i);
                    } else {
                        if (map.containsKey(i)) res += map.get(i);
                    }
                }
            }
        }
        return res;
    }

    // 3165. Maximum Sum of Subsequence With Non-adjacent Elements
    public static int MOD = 1_000_000_007;
    /* METHOD 1: DP PROGRAMING */
    public int maximumSumSubsequence(int[] nums, int[][] queries) {
        int n = nums.length;
        long totalSum = 0;

        long[] dpInclude = new long[n];
        long[] dpExclude = new long[n];

        long includeI = 0, excludeI = 0;
        for (int i = 0; i < n; i++) {
            long newExclude = Math.max(includeI, excludeI);
            includeI = (excludeI + nums[i]) % MOD;
            excludeI = newExclude;

            dpInclude[i] = includeI;
            dpExclude[i] = excludeI;
        }

        for (int[] querry : queries) {
            int pos = querry[0];
            nums[pos] = querry[1];

            long includePos = 0, excludePos = 0;
            if (pos != 0) {
                includePos = dpInclude[pos - 1];
                excludePos = dpExclude[pos - 1];
            }

            for (int i = pos; i < n; i++) {
                long newExclude = Math.max(includePos, excludePos);
                includePos = (excludePos + nums[i]) % MOD;
                excludePos = newExclude;

                dpInclude[i] = includePos;
                dpExclude[i] = excludePos;
            }

            long maxSum = Math.max(includePos, excludePos);
            totalSum = (totalSum + maxSum) % MOD;
        }
        return (int) totalSum;
    }

    /* METHOD 2: SEGMENT TREE */
    public static class SegmentTreeNode {
        SegmentTreeNode left, right;
        int lo, hi;
        long[][] selected = new long[2][2];

        SegmentTreeNode(int[] nums, int lo, int hi) {
            this.lo = lo;
            this.hi = hi;
            if (lo < hi) {
                int mid = (lo + hi) / 2;
                left = new SegmentTreeNode(nums, lo, mid);
                right = new SegmentTreeNode(nums, mid + 1, hi);
                combine();
            } else {
                selected[1][1] = nums[lo];
            }
        }

        void combine() {
            selected[0][0] = Math.max(left.selected[0][0] + right.selected[0][0],
                    Math.max(left.selected[0][1] + right.selected[0][0],
                            left.selected[0][0] + right.selected[1][0]));
            selected[0][1] = Math.max(left.selected[0][0] + right.selected[0][1],
                    Math.max(left.selected[0][1] + right.selected[0][1],
                            left.selected[0][0] + right.selected[1][1]));
            selected[1][0] = Math.max(left.selected[1][0] + right.selected[0][0],
                    Math.max(left.selected[1][1] + right.selected[0][0],
                            left.selected[1][0] + right.selected[1][0]));
            selected[1][1] = Math.max(left.selected[1][0] + right.selected[0][1],
                    Math.max(left.selected[1][1] + right.selected[0][1],
                            left.selected[1][0] + right.selected[1][1]));
        }

        void update(int pos, int x) {
            if (pos < lo || pos > hi) {
                return;
            }

            if (lo == hi) {
                selected[1][1] = x;
                return;
            }

            left.update(pos, x);
            right.update(pos, x);

            combine();
        }
    }

    public int maximumSumSubsequenceST(int[] nums, int[][] queries) {
        SegmentTreeNode segmentTree = new SegmentTreeNode(nums, 0, nums.length - 1);
        long res = 0;
        for (int[] querry : queries) {
            segmentTree.update(querry[0], querry[1]);
            res += Math.max(segmentTree.selected[0][0], Math.max(segmentTree.selected[0][1], Math.max(segmentTree.selected[1][0], segmentTree.selected[1][1])));
            res %= MOD;
        }
        return (int) res;
    }

}
