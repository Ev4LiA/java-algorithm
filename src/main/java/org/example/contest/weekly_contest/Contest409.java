package org.example.contest.weekly_contest;

import java.util.*;

public class Contest409 {
    // Design Neighbor Sum Service
    class neighborSum {
        public int[][] g;
        public int n;
        public HashMap<Integer, int[]> position;
        public neighborSum(int[][] grid) {
            position = new HashMap<>();
            g = grid;
            n = g.length;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    position.put(g[i][j], new int[]{i, j});
                }
            }
        }

        public int adjacentSum(int value) {
            int[] pos = position.get(value);
            int sum = 0;
            int[][] directions = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

            for (int[] dir : directions) {
                int i = pos[0] + dir[0], j = pos[1] + dir[1];

                if (i >= 0 && j >= 0 && i < n && j < n) {
                    sum += g[i][j];
                }
            }
            return sum;
        }

        public int diagonalSum(int value) {
            int[] pos = position.get(value);
            int sum = 0;
            int[][] directions = new int[][]{{-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

            for (int[] dir : directions) {
                int i = pos[0] + dir[0], j = pos[1] + dir[1];

                if (i >= 0 && j >= 0 && i < n && j < n) {
                    sum += g[i][j];
                }
            }
            return sum;
        }
    }

    // Shortest Distance After Road Addition Queries I
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        int[] res = new int[queries.length];
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            List<Integer> adj = map.computeIfAbsent(i, k -> new ArrayList<>());
            adj.add(i + 1);
            map.put(i, adj);
        }

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            List<Integer> adj = map.get(query[0]);
            adj.add(query[1]);
            res[i] = bfs(n, map);
        }
        return res;
    }

    private int bfs(int n, HashMap<Integer, List<Integer>> map) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        while (!queue.isEmpty()) {
            int point = queue.poll();
            if (point == n - 1) {
                break;
            }
            List<Integer> adj = map.get(point);

            for (int i = 0; i < adj.size(); i++) {
                int v = adj.get(i);
                if (dist[v] > dist[point] + 1) {
                    dist[v] = dist[point] + 1;
                    queue.add(v);
                }
            }
        }
        return dist[n - 1];
    }

    // Shortest Distance After Road Addition Queries II
    public int[] shortestDistanceAfterQueriesII(int n, int[][] queries) {
        int[] res = new int[queries.length];
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n - 1; i++) {
            map.put(i, i + 1);
        }

        for (int i = 0; i < queries.length; i++) {
            int[] q = queries[i];
            int start = q[0], end = q[1];
            if (map.containsKey(start) && end > map.get(start)) {
                map.put(start, end);
                for (int j = start + 1; j < end; j++) {
                    map.remove(j);
                }
                int dist = i == 0 ? n - 1 : res[i - 1];
                dist -= (end - start - 1);
                res[i] = dist;
            } else {
                res[i] = res[i - 1];
            }
        }
        return res;
    }
}
