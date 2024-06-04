package org.example.contest.weekly_contest.contest_300.contest_390;

import java.util.*;

public class Contest394 {
    // 3120. Count the Number of Special Characters I
    public int numberOfSpecialChars(String word) {
        int[] lower = new int[26];
        int[] upper = new int[26];
        int res = 0;

        for (char c : word.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                lower[c - 'a']++;
            } else {
                upper[c - 'A']++;
            }
        }

        for (int i = 0; i < upper.length; i++) {
            if (upper[i] > 0 && lower[i] > 0) {
                res++;
            }
        }
        return res;
    }

    // 3121. Count the Number of Special Characters II
    public int numberOfSpecialCharsII(String word) {
        int[] lower = new int[26];
        int[] upper = new int[26];
        Arrays.fill(lower, -1);
        Arrays.fill(upper, -1);
        int res = 0;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c >= 'a' && c <= 'z') {
                lower[c - 'a'] = i;
            } else {
                if (upper[c - 'A'] == -1) {
                    upper[c - 'A'] = i;
                }
            }
        }

        for (int i = 0; i < upper.length; i++) {
            if (upper[i] != -1 && lower[i] != -1 && upper[i] > lower[i]) {
                res++;
            }
        }
        return res;
    }

    // 3122. Minimum Number of Operations to Satisfy Conditions
    public int minimumOperations(int[][] grid) {
        int m = grid.length, n = grid[0].length;

        /* For each column make a map to track freq of numbers from [0 - 9] in that column */
        int[][] mp = new int[n][10];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                mp[j][grid[i][j]]++;
            }
        }

        /* preCalculating the number of operations req to make all numbers in column = "targetNum" */
        int[][] pre = new int[n][10];
        for (int j = 0; j < n; j++) {
            for (int targetNum = 0; targetNum <= 9; targetNum++) {
                // total numbers in column = m (number of rows)
                // Numbers which are already equal to "targetNum" = mp[j][targetNum]
                // Numbers which are not equal to "targetNum" = m - mp[j][targetNum] :)
                pre[j][targetNum] = m - mp[j][targetNum];
            }
        }

        int[][] dp = new int[n + 1][10];
        for (int i = 0; i <= n; i++)
            Arrays.fill(dp[i], -1);
        return solve(grid, pre, dp, 0, -1);
    }

    private int solve(int[][] grid, int[][] pre, int[][] dp, int curIndex, int prev) {
        if (curIndex == grid[0].length) return 0;

        if (prev != -1 && dp[curIndex][prev] != -1)
            return dp[curIndex][prev];

        int minOp = Integer.MAX_VALUE;
        for (int targetNum = 0; targetNum <= 9; targetNum++) {
            if (targetNum == prev)
                continue;

            int opReq = pre[curIndex][targetNum] + solve(grid, pre, dp, curIndex + 1, targetNum);
            minOp = Math.min(minOp, opReq);
        }

        if (prev != -1)
            dp[curIndex][prev] = minOp;
        return minOp;
    }

    // 3123. Find Edges in Shortest Paths
    class Node implements Comparable<Node> {
        int id;
        int distance;

        public Node(int id, int distance) {
            this.id = id;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    public boolean[] findAnswer(int n, int[][] edges) {
        Map<Integer, List<Node>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], distance = edge[2];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(new Node(v, distance));
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(new Node(u, distance));
        }

        // Find the shortest Path from 0 to n - 1
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[0] = 0;

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>((a, b) -> a.distance - b.distance);
        priorityQueue.add(new Node(0, 0));

        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            int cur = node.id;
            int dist = node.distance;

            if (dist > distances[cur]) continue;

            List<Node> neighbors = graph.getOrDefault(cur, new ArrayList<>());
            for (Node neighborNode : neighbors) {
                int neighbor = neighborNode.id, d = neighborNode.distance;
                int newDist = dist + d;
                if (newDist < distances[neighbor]) {
                    distances[neighbor] = newDist;
                    priorityQueue.offer(new Node(neighbor, newDist));
                }
            }
        }

        // Traceback from node n-1 to node 0.
        Set<String> shortestPathEdges = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(n - 1);
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            List<Node> neighbors = graph.getOrDefault(cur, new ArrayList<>());
            for (Node neighborNode : neighbors) {
                int neighbor = neighborNode.id, d = neighborNode.distance;
                // For each edge, check whether both the nodes of that edge are part of the shortest path(s).
                if (distances[cur] - d == distances[neighbor]) {
                    String edge = neighbor + ":" + cur;
                    shortestPathEdges.add(edge);
                    queue.add(neighbor);
                }
            }
        }

        boolean[] res = new boolean[edges.length];
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            String eForward = edge[0] + ":" + edge[1];
            String eBackward = edge[1] + ":" + edge[0];
            res[i] = shortestPathEdges.contains(eForward) || shortestPathEdges.contains(eBackward);
        }
        return res;
    }
}
