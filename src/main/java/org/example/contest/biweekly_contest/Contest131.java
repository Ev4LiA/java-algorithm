package org.example.contest.biweekly_contest;

import java.util.*;

public class Contest131 {
    // 3158. Find the XOR of Numbers Which Appear Twice
    public int duplicateNumbersXOR(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int res = 0;
        for (int num : nums) {
            if (set.contains(num)) {
                res ^= num;
            } else {
                set.add(num);
            }
        }
        return res;
    }

    // 3159. Find Occurrences of an Element in an Array
    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        ArrayList<Integer> occur = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == x) {
                occur.add(i);
            }
        }

        for (int i = 0; i < queries.length; i++) {
            if (queries[i] > occur.size()) {
                queries[i] = -1;
            } else {
                queries[i] = occur.get(queries[i] - 1);
            }
        }
        return queries;
    }

    // 3160. Find the Number of Distinct Colors Among the Balls
    public int[] queryResults(int limit, int[][] queries) {
        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> colorMap = new HashMap<>();
        int[] res = new int[queries.length];
        int diffColor = 0;

        for (int i = 0; i < queries.length; i++) {
            int pos = queries[i][0], newColor = queries[i][1];
            if (map.containsKey(pos)) {
                int oldColor = map.get(pos);
                if (colorMap.get(oldColor) == 1) {
                    colorMap.remove(oldColor);
                    diffColor--;
                } else {
                    colorMap.put(oldColor, colorMap.get(oldColor) - 1);
                }
            }
            if (!colorMap.containsKey(newColor)) {
                diffColor++;
            }
            colorMap.put(newColor, colorMap.getOrDefault(newColor, 0) + 1);
            map.put(pos, newColor);
            res[i] = diffColor;
        }
        return res;
    }

    // 3161. Block Placement Queries
    public List<Boolean> getResults(int[][] queries) {
        List<Boolean> res = new ArrayList<>();
        int n = 0;
        for (int[] querry : queries) {
            n = Math.max(n, querry[1]);
        }

        int[] coor = new int[n];
        return res;
    }
}
