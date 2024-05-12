package org.example.contest.biweekly_contest;

import java.util.*;

public class Contest130 {
    // Check if Grid Satisfies Conditions
    public boolean satisfiesConditions(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        if (rows == 1 && cols == 1) return true;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (j < cols - 1 && grid[i][j] == grid[i][j + 1]) {
                    return false;
                }

                if (i < rows - 1 && grid[i][j] != grid[i + 1][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // Maximum Points Inside the Square
    public int maxPointsInsideSquare(int[][] points, String s) {
        HashMap<Integer, List<Character>> map = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int[] alpha = new int[26];
        for (int i = 0; i < points.length; i++) {
            int newEdge = Math.max(Math.abs(points[i][0]), Math.abs(points[i][1]));
            if (!map.containsKey(newEdge)) {
                pq.add(newEdge);
            }
            map.computeIfAbsent(newEdge, k -> new ArrayList<>()).add(s.charAt(i));
        }

        int count = 0;
        while (!pq.isEmpty()) {
            int i = pq.poll();
            List<Character> list = map.get(i);
            for (Character c : list) {
                if (alpha[c - 'a'] == 1) {
                    return count;
                } else {
                    alpha[c - 'a'] = 1;
                }
            }
            count += list.size();
        }
        return count;
    }

    // Minimum Substring Partition of Equal Character Frequency
    public int[] dp;
    public int findSubStr(int newIndex, String s) {
        if (newIndex < 0) return 0;
        if (dp[newIndex] == -1) return dp[newIndex];
        int[] freq = new int[26];
        int minSubStr = 5005;
        for (int j = newIndex; j >= 0; j--) {
            freq[s.charAt(j) - 'a']++;
            int maxFreq = 1, minFreq = 5005;
            for (int k = 0; k < 26; k++) {
                if (freq[k] != 0) {
                    minFreq = Math.min(minFreq, freq[k]);
                    maxFreq = Math.max(maxFreq, freq[k]);
                }
            }

            if (minFreq == maxFreq) {
                minSubStr = Math.min(minSubStr, 1 + findSubStr(j - 1, s));
            }
        }
        dp[newIndex] = minSubStr;
        return dp[newIndex] = minSubStr;
    }
    public int minimumSubstringsInPartition(String s) {
        dp = new int[s.length()];
        Arrays.fill(dp, -1);
        return findSubStr(s.length() - 1, s);
    }
}
