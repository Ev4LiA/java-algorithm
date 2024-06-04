package org.example.contest.weekly_contest.contest_300.contest_390;

public class Contest391 {
    // 3099. Harshad Number
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int sum = 0, tempx = x;
        while (tempx != 0) {
            sum += tempx % 10;
            tempx /= 10;
        }
        return x % sum == 0 ? sum : -1;
    }

    // 3100. Water Bottles II
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        int res = numBottles, emptyBottles = numBottles;

        while (emptyBottles >= numExchange) {
            emptyBottles = emptyBottles - numExchange + 1;
            res++;
            numExchange++;
        }
        return res;
    }

    // 3101. Count Alternating Subarrays
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

    // 3102. Minimize Manhattan Distances
    public int minimumDistance(int[][] points) {
        int[] m = maximumManhattanDistance(points, -1);
        int[] m1 = maximumManhattanDistance(points, m[0]);
        int[] m2 = maximumManhattanDistance(points, m[1]);

        return Math.min(manhattanDistance(points, m1[0], m1[1]), manhattanDistance(points, m2[0], m2[1]));
    }

    private int manhattanDistance(int[][] points, int i, int j) {
        return Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
    }

    private int[] maximumManhattanDistance(int[][] points, int remove) {
        int maxSum = Integer.MIN_VALUE, minSum = Integer.MAX_VALUE, maxDiff = Integer.MIN_VALUE, minDiff = Integer.MAX_VALUE;
        int maxSumIndex = -1, minSumIndex = -1, maxDiffIndex = -1, minDiffIndex = -1;

        for (int i = 0; i < points.length; i++) {
            if (i != remove) {
                int sum = points[i][0] + points[i][1];
                int diff = points[i][0] - points[i][1];

                if (maxSum < sum) {
                    maxSum = sum;
                    maxSumIndex = i;
                }

                if (minSum > sum) {
                    minSum = sum;
                    minSumIndex = i;
                }

                if (maxDiff < diff) {
                    maxDiff = diff;
                    maxDiffIndex = i;
                }

                if (minDiff > diff) {
                    minDiff = diff;
                    minDiffIndex = i;
                }
            }
        }

        return (maxSum - minSum) > (maxDiff - minDiff) ? new int[]{maxSumIndex, minSumIndex} : new int[]{maxDiffIndex, minDiffIndex};
    }
}
