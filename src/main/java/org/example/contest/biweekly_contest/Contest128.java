package org.example.contest.biweekly_contest;

import java.util.*;

public class Contest128 {
    // 3110. Score of a String
    public int scoreOfString(String s) {
        int sum = 0;
        for (int i = 1; i < s.length(); i++) {
            sum += Math.abs(s.charAt(i) - s.charAt(i - 1));
        }
        return sum;
    }

    // 3111. Minimum Rectangles to Cover Points
    public int minRectanglesToCoverPoints(int[][] points, int w) {
        int n = points.length, res = 1;
        int[] xPoint = new int[n];
        for (int i = 0; i < n; i++) {
            xPoint[i] = points[i][0];
        }

        Arrays.sort(xPoint);
        int start = xPoint[0];
        for (int i = 1; i < n; i++) {
            if (xPoint[i] <= start + w) {
                continue;
            } else {
                start = xPoint[i];
                res++;
            }
        }
        return res;
    }

    // 3112. Minimum Time to Visit Disappearing Nodes

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

    public int[] minimumTime(int n, int[][] edges, int[] disappear) {
        List<List<Node>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int weight = edge[2];

            graph.get(u).add(new Node(v, weight));
            graph.get(v).add(new Node(u, weight));
        }

        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int u = node.id;
            int dist = node.distance;

            if (distances[u] < dist) continue;

            for (Node neighbor : graph.get(u)) {
                int v = neighbor.id;
                int weight = neighbor.distance;
                if (dist + weight < distances[v] && dist + weight < disappear[v]) {
                    distances[v] = dist + weight;
                    pq.offer(new Node(v, distances[v]));
                }
            }
        }

        int[] res = new int[n];
        res[0] = 0;
        for (int i = 1; i < n; i++) {
            if (distances[i] == Integer.MAX_VALUE || disappear[i] < distances[i]) {
                res[i] = -1;
            } else {
                res[i] = distances[i];
            }
        }
        return res;
    }

    // 3113. Find the Number of Subarrays Where Boundary Elements Are Maximum
    public long numberOfSubarrays(int[] nums) {
        int n = nums.length;
        Stack<Integer> monoStack = new Stack<>();
        long[] totalSub = new long[n];
        long res = 0;

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            while (!monoStack.isEmpty() && nums[monoStack.peek()] <= num) {
                int index = monoStack.peek();
                if (nums[index] == num) totalSub[i] += totalSub[index];
                monoStack.pop();
            }

            totalSub[i]++;
            monoStack.push(i);
        }

        for (long sub : totalSub) {
            res += sub;
        }
        return res;
    }
}
