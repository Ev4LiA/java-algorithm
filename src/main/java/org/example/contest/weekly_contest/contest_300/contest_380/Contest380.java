package org.example.contest.weekly_contest.contest_300.contest_380;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Contest380 {
    // 3005. Count Elements With Maximum Frequency
    public int maxFrequencyElements(int[] nums) {
        int maxFreq = 0, count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
            if (map.get(num) > maxFreq) {
                maxFreq = map.get(num);
                count = 1;
            } else if (map.get(num) == maxFreq) {
                count++;
            }
        }
        return maxFreq * count;
    }

    // 3006. Find Beautiful Indices in the Given Array I
    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        List<Integer> res = new ArrayList<>();
        int i = s.indexOf(a), j = s.indexOf(b);
        if (i == -1 || j == -1) {
            return res;
        }

        while (i != -1 && j != -1) {
            if (Math.abs(i - j) <= k) {
                res.add(i);
                i = s.indexOf(a, i + 1);
            } else {
                if (i > j) {
                    j = s.indexOf(b, j + 1);
                } else {
                    i = s.indexOf(a, i + 1);
                }
            }
        }

        return res;
    }

    // 3007. Maximum Number That Sum of the Prices Is Less Than or Equal to K
    public long findMaximumNumber(long k, int x) {
        return 0L;
    }
}
