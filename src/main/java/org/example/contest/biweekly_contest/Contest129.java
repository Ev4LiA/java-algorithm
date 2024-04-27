package org.example.contest.biweekly_contest;

import java.util.*;

public class Contest129 {
    //  Make a Square with the Same Color
    public boolean canMakeSquare(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 0; j < n - 1; j++) {
                int black = 0, white = 0;
                for (int r = i; r <= i + 1; r++) {
                    for (int c = j; c <= j + 1; c++) {
                        if (grid[r][c] == 'B') {
                            black++;
                        } else {
                            white++;
                        }
                    }
                }

                if (black >= 3 || white >= 3) return true;
            }
        }
        return false;
    }

    //  Right Triangles
    public long numberOfRightTriangles(int[][] grid) {
        long res = 0;
        int m = grid.length, n = grid[0].length;
        int[] arr = new int[m];
        HashMap<Integer, Set<Integer>> mapCol = new HashMap<>();

        for (int i = 0; i < m; i++) {
            int oneCount = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    mapCol.computeIfAbsent(j, key -> new HashSet<>()).add(i);
                    oneCount++;
                }
            }
            arr[i] = oneCount;
        }

        for (Map.Entry<Integer, Set<Integer>> entry : mapCol.entrySet()) {
            Set<Integer> set = entry.getValue();
            int numberOfOneCol = set.size();
            for (Integer row : set) {
                int numberOfOneRow = arr[row];
                if (numberOfOneRow > 1) {
                    res += (long) (numberOfOneCol - 1) * (numberOfOneRow - 1);
                }
            }
        }

        return res;
    }

    // Find All Possible Stable Binary Arrays I
    public int numberOfStableArrays(int zero, int one, int limit) {
        return 0;
    }
}
