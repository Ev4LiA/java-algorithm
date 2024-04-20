package org.example.contest.biweekly_contest;

public class Contest127 {
    private static final int MAX_BITS = 63;

    // 3095. Shortest Subarray With OR at Least K I
    // 3097. Shortest Subarray With OR at Least K II
    public int minimumSubarrayLength(int[] nums, int k) {
        int n = nums.length, curOr = 0, min = Integer.MAX_VALUE;
        int[] freq = new int[32];

        return 0;
    }

    // 3096. Minimum Levels to Gain More Points
    public int minimumLevels(int[] possible) {
        int sum = 0;
        for (int i = 0; i < possible.length; i++) {
            if (possible[i] == 0) {
                possible[i] = -1;
                sum--;
            } else {
                sum++;
            }
        }

        int player1 = 0;
        for (int i = 0; i < possible.length - 1; i++) {
            player1 += possible[i];
            int player2 = sum - player1;
            if (player1 > player2) {
                return i + 1;
            }
        }


        return -1;
    }
}
