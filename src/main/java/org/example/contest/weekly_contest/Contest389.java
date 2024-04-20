package org.example.contest.weekly_contest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Contest389 {
    // Existence of a Substring in a String and Its Reverse
    public boolean isSubstringPresent(String s) {
        if (s.length() == 1) return false;
        StringBuilder revSb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            revSb.append(s.charAt(i));
        }

        String rev = revSb.toString();

        for (int i = 0; i < s.length() - 1; i++) {
            String sub = s.substring(i, i + 2);
            if (rev.contains(sub)) return true;
        }
        return false;
    }

    // Count Substrings Starting and Ending with Given Character
    public long countSubstrings(String s, char c) {
        long n = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) n++;
        }

        if (n == 0) return 0;
        if (n == 1) return 1;

        return n * (n - 1) / 2 + n;
    }

    // Minimum Deletions to Make String K-Special
    public int minimumDeletions(String word, int k) {
        int[] alpha = new int[26];
        int res = Integer.MAX_VALUE;

        for (char c : word.toCharArray()) {
            alpha[c - 'a']++;
        }

        for(int freq1 : alpha) {
            int cur = 0;
            for (int freq2 : alpha) {
                cur += (freq2 < freq1) ? freq2 : Math.max(0, freq2 - (freq1 + k));
            }
            res = Math.min(res, cur);
        }
        return res;
    }

    // 3086. Minimum Moves to Pick K Ones
    public long minimumMoves(int[] nums, int k, int maxChanges) {
        List<Long> A = new ArrayList<>();
        A.add(0L);
        for (int i = 0; i < nums.length; i++)
            if (nums[i] > 0)
                A.add(A.get(A.size() - 1) + i);

        int n = A.size() - 1, m = Math.max(0, k - maxChanges);
        long res = Long.MAX_VALUE;
        for (int l = m; l <= Math.min(n, Math.min(m + 3, k)); l++) {
            for (int i = 0; i <= n - l; i++) {
                int mid1 = i + l / 2, mid2 = i + l - l / 2;
                long cur = A.get(i + l) - A.get(mid2) - (A.get(mid1) - A.get(i));
                res = Math.min(res, cur + (k - l) * 2);
            }
        }
        return res;
    }
}
