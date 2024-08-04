package org.example.contest.biweekly_contest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Contest136 {
    // 3238. Find the Number of Winning Players
    public int winningPlayerCount(int n, int[][] pick) {
        int res = 0;
        HashMap<Integer, HashMap<Integer, Integer>> arr = new HashMap<>();
        for (int[] p : pick) {
            int player = p[0];
            int color = p[1];
            HashMap<Integer, Integer> map = arr.computeIfAbsent(player, j -> new HashMap<>());
            map.put(color, map.getOrDefault(color, 0) + 1);
        }

        for (int i = 0; i < n; i++) {
            if (arr.containsKey(i)) {
                for (Map.Entry<Integer, Integer> m : arr.get(i).entrySet()) {
                    if (m.getValue() > i) {
                        res++;
                        break;
                    }
                }
            }
        }
        return res;
    }

    // 3239. Minimum Number of Flips to Make Binary Grid Palindromic I
    public int minFlips(int[][] grid) {
        int res = Integer.MAX_VALUE;
        int rows = grid.length, cols = grid[0].length;
        int count = 0;
        for (int i = 0; i < rows; i++) {
            int[] arr = grid[i];
            int l = 0, r = arr.length - 1;
            while (l < r) {
                if (arr[l] != arr[r]) {
                    count++;
                }
                l++;
                r--;
            }
        }
        res = Math.min(res, count);

        count = 0;
        for (int i = 0; i < cols; i++) {
            int u = 0, l = rows - 1;
            while (u < l) {
                if (grid[u][i] != grid[l][i]) {
                    count++;
                }
                u++;
                l--;
            }
        }
        res = Math.min(res, count);
        return res;
    }

    // 3240. Minimum Number of Flips to Make Binary Grid Palindromic II
    public int minFlipsII(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int ans = 0;

        if (rows % 2 == 1 && cols % 2 == 1) {
            if (grid[rows / 2][cols / 2] == 1) {
                ans += 1;
            }
        }

        int[] shift = new int[3];

        if (rows % 2 == 1) {
            int left = 0, right = cols - 1;
            while (left < right) {
                int sum = grid[rows / 2][left] + grid[rows / 2][right];
                shift[sum] += 1;
                left++;
                right--;
            }
        }

        if (cols % 2 == 1) {
            int up = 0, low = rows - 1;
            while (up < low) {
                int sum = grid[up][cols / 2] + grid[low][cols / 2];
                shift[sum] += 1;
                up++;
                low--;
            }
        }

        if (shift[2] % 2 == 1) {
            if (shift[1] > 0) {
                ans += shift[1];
            } else {
                ans += 2;
            }
        } else {
            ans += shift[1];
        }

        for (int i = 0; i < rows / 2; ++i) {
            for (int j = 0; j < cols / 2; ++j) {
                int a = grid[i][j];
                int b = grid[rows - 1 - i][j];
                int c = grid[i][cols - 1 - j];
                int d = grid[rows - 1 - i][cols - 1 - j];

                int sum = a + b + c + d;

                if (sum != 0 && sum != 4) {
                    ans += Math.min(sum, 4 - sum);
                }
            }
        }

        return ans;
    }

    // 3241. Time Taken to Mark All Nodes
    public int[] timeTaken(int[][] edges) {
        int n = edges.length + 1;
        List<List<Integer>> adj = new ArrayList<>(n);
        int[] res = new int[n];
        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        dfs(0, -1, res, adj, dp);
        findMax(0, -1, 0, res, adj, dp);

        return res;
    }

    private void dfs(int node, int prev, int[] ans, List<List<Integer>> adj, int[][] dp) {
        int curr = 0;
        for (int j : adj.get(node)) {
            if (j != prev) {
                dfs(j, node, ans, adj, dp);

                curr = dp[j][0] + (j % 2 == 1 ? 1 : 2);
                if (curr > dp[node][0]) {
                    dp[node][1] = dp[node][0];
                    dp[node][0] = curr;
                } else if (curr > dp[node][1]) {
                    dp[node][1] = curr;
                }
            }
        }
    }

    public void findMax(int root, int prev, int incoming, int[] ans, List<List<Integer>> adj, int[][] dp) {
        ans[root] = Math.max(dp[root][0], incoming);
        int path = root % 2 == 1 ? 1 : 2;
        int curr, x;
        for (int j : adj.get(root)) {
            if (j != prev) {
                curr = dp[j][0] + (j % 2 == 1 ? 1 : 2);

                if (curr == dp[root][0]) {
                    x = dp[root][1];
                } else {
                    x = dp[root][0];
                }

                findMax(j, root, Math.max(path + incoming, path + x), ans, adj, dp);
            }
        }
    }
}
