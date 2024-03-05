package org.example.daily_challenge;

import java.util.Arrays;

public class March2024 {
    // 948. Bag of Tokens
    public static int bagOfTokensScore(int[] tokens, int power) {
        int n = tokens.length;
        if (n == 0) return 0;

        Arrays.sort(tokens);

        if (power < tokens[0]) return 0;
        int start = 0, end = n - 1, score = 0;
        while (start <= end) {
            if (power >= tokens[start]) {
                power -= tokens[start];
                score++;
                start++;
            } else if (score >= 0) {
                if (end == start) {
                    return score;
                } else {
                    power += tokens[end];
                    score--;
                    end--;
                }
            } else {
                return score;
            }
        }
        return score;
    }

    // 1750. Minimum Length of String After Deleting Similar Ends
    public int minimumLength(String s) {
        int n = s.length();
        if (n == 1) return 1;
        int start = 0, end = n - 1;

        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                char same = s.charAt(start);
                while (start <= end && s.charAt(start) == same) {
                    start++;
                }
                while (start <= end && s.charAt(end) == same) {
                    end--;
                }
            } else {
                break;
            }
        }
        return end - start + 1;
    }
}
