package org.example.contest.weekly_contest;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Contest386 {
    // 3046. Split the Array
    public boolean isPossibleToSplit(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) > 2) {
                return false;
            }
        }
        return true;
    }

    // 3047. Find the Largest Area of Square Inside Two Rectangles
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        long max = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                long x = getIntersection(bottomLeft[i][0], topRight[i][0], bottomLeft[j][0], topRight[j][0]);
                long y = getIntersection(bottomLeft[i][1], topRight[i][1], bottomLeft[j][1], topRight[j][1]);
                max = Math.max(max, Math.max(x, y));
            }
        }

        return max * max;
    }

    private long getIntersection(int l1, int r1, int l2, int r2) {
        if (l2 <= l1 && r2 >= r1) {
            return r1 - l1;
        }
        if (l1 <= l2 && r1 >= r2) {
            return r2 - l2;
        }
        if (l2 <= r1 && r2 >= r1) {
            return r1 - l2;
        }
        if (l2 <= r1 && r2 >= l1) {
            return r2 - l1;
        }
        return 0;
    }

    // 3048. Earliest Second to Mark Indices I
    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        int n = nums.length, m = changeIndices.length, sum = 0;

        for (int i = 0; i < m; i++) {
            changeIndices[i]--;
        }

        for (int num : nums) {
            sum += num;
        }

        int minI = 0, maxI = m - 1, out = 0;


        while(minI <= maxI) {
            int midI = minI + (maxI - minI) / 2;
            if (checkPossible(nums, changeIndices, midI, sum)) {
                out = midI + 1;
                maxI = midI - 1;
            } else {
                minI = midI + 1;
            }
        }
        return out;
    }

    private boolean checkPossible(int[] nums, int[] changeIndices, int outIndex, int sum)  {
        if (outIndex < sum - 1) return false;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i <= outIndex; i++) {
            map.put(changeIndices[i], i);
        }

        if (map.size() != nums.length) return false;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(new int[]{entry.getKey(), entry.getValue()});
        }

        int time = 0;
        while (!pq.isEmpty()) {
            int[] pair = pq.poll();
            int value = nums[pair[0]], pos = pair[1];
            if (pos - time < value) {
                return false;
            } else {
                time += value + 1;
            }
        }
        return true;
    }

    // 3049. Earliest Second to Mark Indices II
    public int earliestSecondToMarkIndicesII(int[] nums, int[] changeIndices) {

    }
}
