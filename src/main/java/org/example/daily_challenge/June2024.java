package org.example.daily_challenge;

public class June2024 {
    // 198. House Robber
    public int rob(int[] nums) {
        int n = nums.length;
        int includeI = 0, excludeI = 0;

        for (int i = 0; i < n; i++) {
            int newExclude = Math.max(includeI, excludeI);
            includeI = excludeI + nums[i];
            excludeI = newExclude;
        }
        return Math.max(includeI, excludeI);
    }

    // 344. Reverse String
    public void reverseString(char[] s) {
        int l = 0, r = s.length - 1;
        while (l < r) {
            char temp = s[l];
            s[l] = s[r];
            s[r] = temp;
            l++; r--;
        }
    }

    // 3110. Score of a String
    public int scoreOfString(String s) {
        int res = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            res += Math.abs(s.charAt(i) - s.charAt(i + 1));
        }
        return res;
    }
}
