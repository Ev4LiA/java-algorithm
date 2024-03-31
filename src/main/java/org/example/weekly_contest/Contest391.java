package org.example.weekly_contest;

import java.util.ArrayList;
import java.util.List;

public class Contest391 {
    // Harshad Number
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int sum = 0, tempx = x;
        while (tempx != 0) {
            sum += tempx % 10;
            tempx /= 10;
        }
        return x % sum == 0 ? sum : -1;
    }

    // Water Bottles II
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int res = numBottles, emptyBottles = numBottles;

        while (emptyBottles >= numExchange) {
            emptyBottles = emptyBottles - numExchange + 1;
            res++;
            numExchange++;
        }
        return res;
    }

    //  Count Alternating Subarrays
    public long countAlternatingSubarrays(int[] nums) {
        long res = 0;
        int l = 0, r = 1, n = nums.length;
        while (r < nums.length) {
            if (nums[r] != nums[r - 1]) {
                if (r == nums.length - 1) {
                    long length = r - l + 1;
                    res += length * (length + 1) / 2 - length;
                    break;
                } else {
                    r++;
                }
            } else {
                long length = r - l;
                res += length * (length + 1) / 2 - length;
                l = r;
                r++;
            }
        }
        return res += n;
    }

    //  Minimize Manhattan Distances
    public int minimumDistance(int[][] points) {
        int n = points.length;
        List<List<Integer>> dp = new ArrayList<>();
        int index = 0;
        int max = Integer.MIN_VALUE;
        int farthest1 = 0, farthest2 = 0;

        for (int j = 0; j < n - 1; j++) {
            List<Integer> list = new ArrayList<>();
            for (int k = j + 1; k < n; k++) {
                int distance = Math.abs(points[j][0] - points[k][0]) + Math.abs(points[j][1] - points[k][1]);
                list.add(distance);
                if (distance >= max) {
                    farthest1 = j;
                    farthest2 = k;
                    max = distance;
                }
            }
            dp.add(list);
        }
        int min = max;

        int maxTemp1 = 0, maxTemp2 = 0;

        min = Math.min(min, Math.min(maxTemp1, maxTemp2));

        return min;
    }
}
