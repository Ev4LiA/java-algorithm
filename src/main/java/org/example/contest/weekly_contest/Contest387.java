package org.example.contest.weekly_contest;

import java.util.ArrayList;

public class Contest387 {
    // 3069. Distribute Elements Into Two Arrays I
    public int[] resultArray(int[] nums) {
        if (nums.length <= 2) return nums;
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        list1.add(nums[0]);
        list2.add(nums[1]);
        for (int i = 2; i < nums.length; i++) {
            if (list1.get(list1.size() - 1) > list2.get(list2.size() - 1)) {
                list1.add(nums[i]);
            } else {
                list2.add(nums[i]);
            }
        }
        int[] res = new int[nums.length];
        int j = 0;
        for (Integer value : list1) {
            res[j] = value;
            j++;
        }
        for (Integer integer : list2) {
            res[j] = integer;
            j++;
        }
        return res;
    }

    // 3070. Count Submatrices with Top-Left Element and Sum Less Than k
    public int countSubmatrices(int[][] grid, int k) {
        int res = 0, sum = grid[0][0];
        int[][] map = new int[grid.length][grid[0].length];
        map[0][0] = sum;
        if (map[0][0] <= k) {
            res = 1;
        } else {
            return 0;
        }
        for (int i = 1; i < grid[0].length; i++) {
            map[0][i] = map[0][i - 1] + grid[0][i];
            if (map[0][i] <= k) res++;
        }

        for (int i = 1; i < grid.length; i++) {
            map[i][0] = map[i - 1][0] + grid[i][0];
            if (map[i][0] <= k) res++;
        }

        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                map[i][j] = map[i - 1][j] + map[i][j - 1] + grid[i][j] - map[i - 1][j - 1];
                if (map[i][j] <= k) res++;
            }
        }
        return res;
    }

    // 3071. Minimum Operations to Write the Letter Y on a Grid
    public int minimumOperationsToWriteY(int[][] grid) {
        int n = grid.length;
        int min = Integer.MAX_VALUE;
        for (int y = 0; y <= 2; y++) {
            for (int z = 0; z <= 2; z++) {
                if (y == z) continue;
                int move = 0;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        // Check the Y
                        if (i == j && i <= n / 2) {
                            if (grid[i][j] != y) move++;
                        } else if ((i + j == n - 1) && i <= n / 2) {
                            if (grid[i][j] != y) move++;
                        } else if (i >= n / 2 && j == n / 2) {
                            if (grid[i][j] != y) move++;
                        } else if (grid[i][j] != z) {
                                move++;
                        }
                    }
                }
                min = Math.min(min, move);
            }
        }
        return min;
    }

    // 3072. Distribute Elements Into Two Arrays II
//    public int[] resultArray(int[] nums) {
//        return new int[];
//    }
}
