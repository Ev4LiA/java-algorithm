package org.example.daily_challenge;

import org.example.utilities.TreeNode;

import java.util.*;

public class Feb2024 {
    // 2971. Find Polygon With the Largest Perimeter
    public long largestPerimeter(int[] nums) {
        // Sort the array
        Arrays.sort(nums);

        int n = nums.length;
        if (n <= 2) return -1;

        long[] sum = new long[n];
        sum[0] = nums[0];

        for (int i = 1; i < n; i++) {
            sum[i] = nums[i] + sum[i - 1];
        }

        for (int i = n - 1; i >= 2; i--) {
            if (nums[i] < sum[i - 1]) {
                return sum[i];
            }
        }
        return 0;
    }

    // 1642. Furthest Building You Can Reach
    public int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a);
        int i = 0;
        for (i = 0; i < heights.length - 1; i++) {
            int diff = heights[i + 1] - heights[i];
            if (diff > 0) {
                bricks -= diff;
                pq.offer(diff);
                if (bricks < 0) {
                    ladders--;
                    bricks += pq.poll();
                }
                if (ladders < 0) {
                    break;
                }
            }
        }
        return i;
    }

    // 2402. Meeting Rooms III
    public int mostBooked(int n, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        int[] roomCount = new int[n];
        // Used Priority Queue - {endTime - Room Number}
        PriorityQueue<long[]> used = new PriorityQueue<>(
                (a, b) -> a[0] == b[0] ? (int)(a[1] - b[1]) : (int)(a[0] - b[0]));

        // initial used room, with end time is 0
        for (int i = 0; i < n; i++) {
            used.add(new long[] { 0, i });
        }

        int result = 0;

        for (int i = 0; i < meetings.length; i++) {
            int startTime = meetings[i][0];
            int endTime = meetings[i][1];

            // Add the start meeting when all room is empty (because not all meeting start at 0
            while(used.peek()[0] < (long) startTime) {
                used.add(new long[] { startTime, used.poll()[1] });
            }

            long[] cur = used.poll();
            int curRoom = (int)cur[1];
            long meetingEndTime = cur[0] + (long)(endTime - startTime);
            roomCount[(int)cur[1]]++;

            used.add(new long[] { meetingEndTime, curRoom });

            if (roomCount[curRoom] > roomCount[result]) {
                result = curRoom;
            } else if (roomCount[curRoom] == roomCount[result]) {
                result = Math.min(curRoom, result);
            }
        }

        return result;
    }

    // 268. Missing Number
    public int missingNumber(int[] nums) {
        int sum = 0;
        int n = nums.length;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }
        return n*(n + 1)/2 - sum;
    }

    // 787. Cheapest Flights Within K Stops
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> adj = new HashMap<>();
        for (int[] flight : flights) {
            adj.computeIfAbsent(flight[0], key -> new ArrayList<>()).add(new int[] {flight[1], flight[2]});
        }

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {src, 0});
        int stops = 0;

        while (!q.isEmpty() && stops <= k) {
            int sz = q.size();
            while (sz-- > 0) {
                int[] curr = q.poll();
                int node = curr[0];
                int distance = curr[1];

                if (!adj.containsKey(node)) continue;

                for (int[] next : adj.get(node)) {
                    int neighbour = next[0];
                    int price = next[1];
                    if (price + distance >= dist[neighbour]) continue;
                    dist[neighbour] = price + distance;
                    q.offer(new int[] {neighbour, dist[neighbour]});
                }
            }
            stops++;
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];

    }

    // 2709. Greatest Common Divisor Traversal
    public boolean canTraverseAllPairs(int[] nums) {
        if(nums.length == 1) return true;
        int n = nums.length;
        int maxElement = Arrays.stream(nums).max().getAsInt();
        if(Arrays.stream(nums).min().getAsInt() == 1) return false;
        int[] factorArray = factorsCalculator(maxElement);

        int[] parent = new int[maxElement + 1];
        int[] rank = new int[maxElement + 1];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        for (int num : nums) {
            int x = num;
            while (x > 1) {
                int p = factorArray[x];
                union(parent, rank, p, num);
                while (x % p == 0) {
                    x = x / p;
                }
            }
        }

        int p = find(parent, nums[0]);
        for (int i = 1; i < nums.length; i++) {
            if (find(parent, nums[i]) != p) return false;
        }

        return true;
    }

    private int[] factorsCalculator(int n) {
        int[] dp = new int[n + 2];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = i;
        }
        for (int i = 2; i <= n; i++) {
            if (dp[i] == i) {
                for (int j = i * 2; j <= n; j += i) {
                    if (dp[j] == j) dp[j] = i;
                }
            }
        }
        return dp;
    }

    private int find(int[] parent, int a) {
        return parent[a] = parent[a] == a ? a : find(parent, parent[a]);
    }

    private void union(int[] parent, int[] rank, int a, int b) {
        a = find(parent, a);
        b = find(parent, b);
        if (a == b) return;
        if (rank[a] < rank[b]) {
            int temp = a;
            a = b;
            b = temp;
        }
        parent[b] = a;
        rank[a] += rank[b];
    }

    // 513. Find Bottom Left Tree Value
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int result = root.val;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (i == 0) result = curr.val;

                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
        }
        return result;
    }
}
