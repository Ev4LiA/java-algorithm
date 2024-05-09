package org.example.contest.weekly_contest;

import java.util.HashMap;
import java.util.Map;

public class Contest396 {
    // 3136. Valid Word
    public boolean isValid(String word) {
        String validStr = "AEIUOaeiuo";
        if (word.length() < 3) return  false;
        int v = 0, c = 0;
        for (char ch : word.toCharArray()) {
            if (Character.isLetter(ch)) {
                if (validStr.indexOf(ch) != -1) {
                    v++;
                } else {
                    c++;
                }
            } else if (!Character.isLetterOrDigit(ch)) {
                return false;
            }
        }
        return v > 0 && c > 0;
    }

    // 3137. Minimum Number of Operations to Make Word K-Periodic
    public int minimumOperationsToMakeKPeriodic(String word, int k) {
        int maxFreq = Integer.MIN_VALUE;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < word.length(); i += k) {
            String subStr = word.substring(i, i + k);
            int freq = map.getOrDefault(subStr, 0);
            if (freq + 1 > maxFreq) {
                maxFreq = freq + 1;
            }
            map.put(subStr, freq + 1);
        }

        return word.length() / k - maxFreq;
    }

    // 3138. Minimum Length of Anagram Concatenation
    public int minAnagramLength(String s) {
        int n = s.length();
        for (int i = 1; i < s.length(); i++) {
            if (n % i == 0 && checkSubStrLength(s, i)) {
                return i;
            }
        }
        return n;
    }

    private boolean checkSubStrLength(String word, int k) {
        int n = word.length();
        int[] count = new int[26];
        for (int i = 0; i < k; i++) {
            count[word.charAt(i) - 'a']++;
        }

        for (int i = k; i < n; i += k) {
            int[] count2 = new int[26];
            for (int j = i; j < i + k; j++)
                count2[word.charAt(j) - 'a']++;

            for (int j = 0; j < 26; j++) {
                if (count[j] != count2[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // 3139. Minimum Cost to Equalize Array
    public int minCostToEqualizeArray(int[] nums, int cost1, int cost2) {
        int max = nums[0], min = nums[0], n = nums.length, mod = 1000000007;
        long totalSub = 0;
        for (int a : nums) {
            max = Math.max(max, a);
            min = Math.min(min, a);
            totalSub += a;
        }

        totalSub = (long) n * max - totalSub;

        // case 1
        if (cost1 * 2 < cost2 || n <= 2) {
            return (int) ((totalSub * cost1) % mod);
        }

        // case 2
        long op1 = Math.max(0L, (max - min) * 2L - totalSub);
        long op2 = totalSub - op1;
        long res = (op1 + op2 % 2) * cost1 + op2 / 2 * cost2;

        // case 3
        totalSub += op1 / (n - 2) * n;
        op1 %= (n - 2);
        op2 = totalSub - op1;
        res = Math.min(res, (op1 + op2 % 2) * cost1 + op2 / 2 * cost2);

        // case 4
        for (int i = 0; i < 2; i++) {
            totalSub += n;
            res = Math.min(res, totalSub % 2 * cost1 + totalSub / 2 * cost2);
        }
        return (int) (res % mod);
    }
}
