package org.example.contest.biweekly_contest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Contest126 {
    // 3079. Find the Sum of Encrypted Integers
    public int sumOfEncryptedInt(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res += encrypt(num);
        }
        return res;
    }

    public int encrypt(int num) {
        String str = Integer.toString(num);
        char maxDigit = '0';

        for (char c : str.toCharArray()) {
            if (c > maxDigit) {
                maxDigit = c;
            }
        }
        String res = "";
        for (int i = 0; i < str.length(); i++) {
            res = res + maxDigit;
        }

        return Integer.parseInt(res);
    }

    // 3080. Mark Elements on Array by Performing Queries
    public long[] unmarkedSumArray(int[] nums, int[][] queries) {
        long sum = 0;
        long[] res = new long[queries.length];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        });

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            pq.add(new int[]{nums[i], i});
        }

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int k = queries[i][1];
            if (nums[index] >= 0) {
                // Mark Visited
                sum -= nums[index];
                nums[index] = -1;
            }

            // Get smallest Var
            while (k > 0 && !pq.isEmpty()) {
                int[] smallest = pq.poll();
                if (nums[smallest[1]] < 0) {
                    continue;
                } else {
                    sum -= nums[smallest[1]];
                    nums[smallest[1]] = -1;
                    k--;
                }
            }

            res[i] = sum;
        }
        return res;
    }

    // 3081. Replace Question Marks in String to Minimize Its Value
    public String minimizeStringValue(String s) {
        char[] sCharArray = s.toCharArray();

        int[] word = new int[26];
        for (char c : s.toCharArray()) {
            if (c != '?') {
                word[c - 'a']++;
            }
        }

        ArrayList<Integer> add = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '?') {
                int min = 0;
                for (int j = 0; j < 26; j++) {
                    if (word[j] < word[min]) min = j;
                }

                word[min]++;
                add.add(min);
            }
        }
        Collections.sort(add);
        for (int i = 0, p = 0; i < s.length(); i++) {
            if (sCharArray[i] == '?') {
                sCharArray[i] = (char) (add.get(p++) + 'a');
            }
        }

        return String.valueOf(sCharArray);
    }

    // 3082. Find the Sum of the Power of All Subsequences
    public int sumOfPower(int[] nums, int target) {
        final int MOD = 1000000007;

        int size = nums.length + 1;
        int[][] dp = new int[size + 1][target + 1];
        dp[0][0] = 1;

        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= target; j++) {
                dp[i][j] = (dp[i - 1][j] * 2) % MOD;

                int diff = j - nums[i - 1];
                if (diff >= 0) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][diff]) % MOD;
                }
            }
        }
        return dp[size][target];
    }
}
