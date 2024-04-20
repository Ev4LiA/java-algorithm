package org.example.contest.weekly_contest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Contest393 {
    // 3114. Latest Time You Can Obtain After Replacing Characters
    public String findLatestTime(String s) {
        char[] chars = s.toCharArray();
        if (chars[3] == '?') {
            chars[3] = '5';
        }

        if (chars[4] == '?') {
            chars[4] = '9';
        }

        if (chars[0] == '?') {
            if (chars[1] != '?' && chars[1] > '1') {
                chars[0] = '0';
            } else {
                chars[0] = '1';
            }
        }

        if (chars[1] == '?') {
            if (chars[0] == '1') {
                chars[1] = '1';
            } else {
                chars[1] = '9';
            }
        }

        return String.valueOf(chars);
    }

    // 3115. Maximum Prime Difference
    public int maximumPrimeDifference(int[] nums) {
        int left = 0, right = 0;
        for (int i = 0; i < nums.length; i++) {
            if (isPrime(nums[i])) {
                left = i;
                break;
            }
        }

        for (int i = nums.length - 1; i >= left; i--) {
            if (isPrime(nums[i])) {
                right = i;
                break;
            }
        }

        return right - left;
    }

    public boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // 3116. Kth Smallest Amount With Single Denomination Combination
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        List<Integer> temp = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            boolean can = true;
            for (int j = 0; j < n && can; j++) {
                if (i != j) {
                    if (coins[i] % coins[j] == 0) {
                        can = false;
                    }
                }
            }
            if (can) temp.add(coins[i]);
        }

        n = temp.size();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = temp.get(i);
        }
        long l = 0;
        long r = Long.MAX_VALUE;
        boolean qwe = false;
        while (l <= r) {
            long m = (r - l) / 2l + l;
            while (!check(m, arr)) {
                if (qwe) m++;
                else m--;
            }
            qwe = !qwe;

            long cnt = 0;
            for (int i = 1; i < (1 << arr.length); i++) {
                long LCM = 1;
                int bits = 0;
                for (int j = 0; j < arr.length; j++) {
                    if ((i & (1 << j)) == (1 << j)) {
                        bits++;
                        LCM = lcm(LCM, arr[j]);
                    }
                }
                if (bits % 2 == 1) cnt += m / LCM;
                else cnt -= m / LCM;
            }

            if (cnt > k) r = m - 1;
            else if (cnt < k) l = m + 1;
            else return m;
        }

        return l;
    }

    public long gcd(long a, long b) {
        while (b > 0) {
            long c = a;
            a = b;
            b = c % b;
        }
        return a;
    }

    public long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    public boolean check(long x, int[] arr) {
        for (int y : arr) {
            if (x % ((long) y) == 0) return true;
        }
        return false;
    }

    // 3117. Minimum Sum of Values by Dividing Array
    public int minimumValueSum(int[] nums, int[] andValues) {
        HashMap<Integer, Integer>[][] dp = new HashMap[nums.length][andValues.length];

        int val = recur(0, 0, nums, andValues, 131071, dp);

        if (val >= (int) (1e9)) return -1;

        return val;
    }

    int recur(int i, int j, int[] nums, int[] andValues, int runningAnd, HashMap<Integer, Integer>[][] dp) {

        if (i == nums.length && j == andValues.length) {
            return 0;
        }

        if (j == andValues.length || i == nums.length) return (int) 1e9;

        if (dp[i][j] != null && dp[i][j].get(runningAnd) != null) {
            return dp[i][j].get(runningAnd);
        }

        int newRun = runningAnd & nums[i];

        int tryNew = (int) (1e9);
        if (newRun == andValues[j]) {
            tryNew = nums[i] + recur(i + 1, j + 1, nums, andValues, 131071, dp);
        }
        int cont = recur(i + 1, j, nums, andValues, newRun, dp);

        if (dp[i][j] == null) {
            dp[i][j] = new HashMap<>();
        }
        dp[i][j].put(runningAnd, Math.min(tryNew, cont));
        return Math.min(tryNew, cont);
    }
}
