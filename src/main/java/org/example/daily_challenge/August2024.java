package org.example.daily_challenge;

public class August2024 {
    // 2134. Minimum Swaps to Group All 1's Together II
    public int minSwaps(int[] nums) {
        int n = nums.length, ones = 0, max = 0, count = 0;
        for (int num : nums) {
            ones += num;
        }

        for (int i = 0; i < ones; i++) {
            count+= nums[i];
        }

        max = count;
        for  (int i = ones; i < n + ones; i++) {
            count = count - nums[i - ones] + nums[i % n];
            max = Math.max(max, count);
        }
        return ones - max;
    }

    // 2678. Number of Senior Citizens
    public int countSeniors(String[] details) {
        int res = 0;
        for (String detail : details) {
            int age = Integer.parseInt(detail.substring(11, 13));
            if (age > 60) res++;
        }
        return res;
    }
}
