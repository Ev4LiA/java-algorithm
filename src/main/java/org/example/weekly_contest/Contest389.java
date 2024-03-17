package org.example.weekly_contest;

import java.util.Arrays;

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

        return n * (n - 1) / 2;
    }

    // Minimum Deletions to Make String K-Special
    public int minimumDeletions(String word, int k) {
        int[] alpha = new int[26];
        int res = 0;

        for (char c : word.toCharArray()) {
            alpha[c - 'a']++;
        }

        Arrays.sort(alpha);
        int start = 0, end = 25;
        while (alpha[start] == 0) {
            start++;
        }


        int[] subArr = new int[end - start + 1];
        for (int i = subArr.length - 1; i >= 0; i--) {
            if (i == 0) {
                subArr[i] = 0;
            } else {
                subArr[i] = alpha[start] - alpha[start - 1];
            }
        }

        while (alpha[end] - alpha[start] > k) {
            int min = alpha[start];
            int max = alpha[end];
            int sub = max - min;
            if (sub > k) {
                if (sub - k > min) {
                    res += min;
                    alpha[start] -= min;
                    start++;
                } else if (sub - k == min) {
                    res += sub - k;
                    int maxCount = 0, minCount = 0, i = start, j = end;
                    while (alpha[i] == min) {
                        minCount++;
                        i++;
                    }
                    while (alpha[j] == max) {
                        maxCount++;
                        j--;
                    }
                    if (maxCount > minCount) {
                        alpha[start] -= min;
                        start++;
                    } else {
                        alpha[end] = max - (sub - k);
                        Arrays.sort(alpha);
                    }
                } else {
                    alpha[end] = max - (sub - k);
                    res += sub - k;
                    Arrays.sort(alpha);
                }
            }
        }
        return res;
    }
}
