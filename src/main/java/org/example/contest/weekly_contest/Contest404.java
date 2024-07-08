package org.example.contest.weekly_contest;

import java.util.ArrayList;
import java.util.List;

public class Contest404 {
    // Maximum Height of a Triangle
    public int maxHeightOfTriangle(int red, int blue) {
        return Math.max(height(red, blue), height(blue, red));
    }

    private int height(int root1, int root2) {
        int res = 0;
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                if (root1 >= i + 1) {
                    root1 -= i + 1;
                } else {
                    return res;
                }
            } else {
                if (root2 >= i + 1) {
                    root2 -= i + 1;
                } else {
                    return res;
                }
            }
        }
        return res;
    }

    // Find the Maximum Length of Valid Subsequence I
    /*
        1, 1, 1, 1
        2, 2, 2, 2
        1, 2, 1, 2
        2, 1, 2, 1
     */
    public int maximumLength(int[] nums) {
        int[] counter = new int[4];
        boolean[] flags = new boolean[]{true, false};
        for (int num : nums) {
            int v = num % 2;
            if ((v == 1 && !flags[1]) || (v == 0 && flags[1])) {
                flags[1] = !flags[1];
                counter[1]++;
            }
            if ((v == 1 && !flags[0]) || (v == 0 && flags[0])) {
                flags[0] = !flags[0];
                counter[0]++;
            }

            if (v == 1) {
                counter[2]++;
            }
            if (v == 0) {
                counter[3]++;
            }
        }

        int max = 0;
        for (int num : counter) {
            max = Math.max(num, max);
        }
        return max;
    }

    //  Find the Maximum Length of Valid Subsequence II
    public int maximumLength(int[] nums, int k) {
        return 0;
    }
}


