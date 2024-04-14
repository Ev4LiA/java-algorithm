package org.example.weekly_contest;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Contest393 {
    //  Latest Time You Can Obtain After Replacing Characters
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

    //  Maximum Prime Difference
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

    // Kth Smallest Amount With Single Denomination Combination
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        Arrays.sort(coins);
        PriorityQueue<Long> pq = new PriorityQueue<>();
        int[] mul = new int[n];
        pq.offer((long) coins[0]);
        mul[0] = coins[0];
        k--;

        while (k > 0) {
            int index = findMin(coins, mul, 0);
            long min = mul[index];
            if (!pq.isEmpty() && min != pq.peek()) {
                pq.remove();
                pq.offer(min);
                k--;
            }
        }
        return pq.peek();
    }

    public int findMin(int[] coins, int[] mul, int index) {
        long min = mul[index] + coins[index];
        for (int i = 0; i < coins.length; i++) {
            if (min >= mul[i] + coins[i] && i != index) {
                index = i;
                break;
            }
        }

        mul[index] += coins[index];
        return index;
    }
}
