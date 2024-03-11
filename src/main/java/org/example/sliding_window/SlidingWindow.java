package org.example.sliding_window;

public class SlidingWindow {
    // 209. Minimum Size Subarray Sum
    public int minSubArrayLen(int target, int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0] >= target ? 1 : 0;

        int ans = Integer.MAX_VALUE;
        int left = 0, sum = 0;

        for (int i = 0; i < n; i++) {
            sum = sum + nums[i];
            while (sum >= target) {
                ans = Math.min(ans, i - left + 1);
                sum -= nums[left];
                left++;
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
