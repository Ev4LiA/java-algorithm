package org.example.contest.weekly_contest;

import java.util.HashMap;

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

    }
}
