package org.example.daily_challenge;

import org.example.utilities.TreeNode;

import java.util.*;

public class April2024 {
    // 58. Length of Last Word
    public int lengthOfLastWord(String s) {
        String[] parts = s.split("\\s+");
        return parts[parts.length - 1].length();
    }

    // 79. Word Search
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (word.charAt(0) == board[i][j] && search(board, i, j, 0, visited, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean search(char[][] board, int i, int j, int index, boolean[][] visited, String word) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j]) {
            return false;
        }

        if (word.charAt(index) != board[i][j]) return false;
        if (index == word.length() - 1) return true;

        visited[i][j] = true;
        if (search(board, i + 1, j, index + 1, visited, word)
                || search(board, i - 1, j, index + 1, visited, word)
                || search(board, i, j + 1, index + 1, visited, word)
                || search(board, i, j - 1, index + 1, visited, word)) {
            return true;
        }
        visited[i][j] = false;
        return false;
    }

    // 85. Maximal Rectangle
    public int maximalRectangle(char[][] matrix) {
        return 0;
    }

    // 129. Sum Root to Leaf Numbers
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int res) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return res * 10 + root.val;

        int left = dfs(root.left, res * 10 + root.val);
        int right = dfs(root.right, res * 10 + root.val);
        return left + right;
    }

    // 200. Number of Islands
    public int numIslands(char[][] grid) {
        Stack<int[]> stack = new Stack<>();
        int count = 0, m = grid.length, n = grid[0].length;
        int[] direction = new int[]{0, 1, 0, -1, 0};

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    stack.push(new int[]{i, j});
                    while (!stack.isEmpty()) {
                        int[] pos = stack.pop();
                        int row = pos[0], col = pos[1];
                        grid[row][col] = '0';

                        for (int k = 0; k < direction.length - 1; k++) {
                            int newRow = row + direction[k], newCol = col + direction[k + 1];
                            if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n || grid[newRow][newCol] == '0') {
                                continue;
                            } else {
                                stack.push(new int[]{newRow, newCol});
                            }
                        }
                    }

                    count++;
                }
            }
        }
        return count;
    }

    // 205. Isomorphic Strings
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                if (!map.containsValue(t.charAt(i))) {
                    map.put(s.charAt(i), t.charAt(i));
                } else {
                    return false;
                }
            } else {
                if (map.get(s.charAt(i)) != t.charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    // 310. Minimum Height Trees
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList<>();
        if (n == 1) {
            res.add(0);
            return res;
        }

        int[] indegree = new int[n];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            indegree[u]++;
            indegree[v]++;
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        // Add Leaf Node into queue
        Queue<Integer> leaves = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 1) leaves.add(i);
        }

        int processed = 0;
        while (n - processed > 2) {
            int size = leaves.size();
            for (int i = 0; i < size; i++) {
                int leaf = leaves.poll();
                List<Integer> neighbors = graph.get(leaf);
                for (Integer neighbor : neighbors) {
                    if (--indegree[neighbor] == 1) {
                        leaves.add(neighbor);
                    }
                }
                processed++;
            }
        }

        res.addAll(leaves);
        return res;
    }

    // 402. Remove K Digits
    public String removeKdigits(String num, int k) {
        if (k == num.length()) return "0";

        Stack<Character> monoStack = new Stack<>();
        for (char c : num.toCharArray()) {
            while (!monoStack.isEmpty() && k > 0 && c < monoStack.peek()) {
                monoStack.pop();
                k--;
            }
            monoStack.push(c);
        }

        while (k > 0 && !monoStack.isEmpty()) {
            monoStack.pop();
            k--;
        }

        StringBuilder sb = new StringBuilder();
        while (!monoStack.isEmpty()) {
            sb.insert(0, monoStack.pop());
        }

        while (!sb.isEmpty() && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return !sb.isEmpty() ? sb.toString() : "0";
    }

    // 404. Sum of Left Leaves
    public int sumOfLeftLeaves(TreeNode root) {
        if (root.left == null && root.right == null) {
            return 0;
        }
        int res = 0;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            if (current.left != null) {
                if (current.left.left == null && current.left.right == null) {
                    res += current.left.val;
                } else {
                    stack.add(current.left);
                }
            }
            if (current.right != null) {
                stack.add(current.right);
            }
        }
        return res;
    }

    // 463. Island Perimeter
    public int islandPerimeter(int[][] grid) {
        int res = 0, n = grid.length, m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    res += 4;
                    if (i - 1 >= 0 && grid[i - 1][j] == 1) {
                        res -= 2;
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == 1) {
                        res -= 2;
                    }
                }
            }
        }
        return res;
    }

    // 514. Freedom Trail
    public int findRotateSteps(String ring, String key) {
        return 0;
    }

    // 623. Add One Row to Tree
    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        return null;
    }

    // 678. Valid Parenthesis String
    /* ---- Stack Method ---- */
    public boolean checkValidString(String s) {
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> star = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if (s.charAt(i) == '*') {
                star.push(i);
            } else {
                if (stack.isEmpty() && star.isEmpty()) {
                    return false;
                } else if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    star.pop();
                }
            }
        }

        while (!stack.isEmpty()) {
            if (star.isEmpty()) return false;
            if (stack.peek() > star.peek()) return false;
            stack.pop();
            star.pop();
        }
        return true;
    }

    /* ---- Two Pointer Method ---- */
    public boolean checkValidStringII(String s) {
        int minLeft = 0, maxLeft = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                minLeft++;
                maxLeft++;
            } else if (c == ')') {
                minLeft--;
                maxLeft--;
            } else {
                minLeft--;
                maxLeft++;
            }

            if (maxLeft < 0) {
                return false;
            } else if (minLeft < 0) {
                minLeft = 0;
            }
        }

        return minLeft == 0;
    }

    // 752. Open the Lock
    class Pair {
        String key;
        int value;

        public Pair(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    public int openLock(String[] deadends, String target) {
        Set<String> set = new HashSet<>(Arrays.asList(deadends));
        if (set.contains("0000") || set.contains(target)) {
            return -1;
        }
        if (target.equals("0000")) {
            return 0;
        }
        Queue<Pair> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(new Pair("0000", 0));
        visited.add("0000");

        while (!queue.isEmpty()) {
            Pair cur = queue.poll();
            String key = cur.key;
            int moves = cur.value;

            if (key.equals(target)) {
                return moves;
            }

            // Create new combination
            for (int i = 0; i < 4; i++) {
                for (int j : new int[]{1, -1}) {
                    int newDigit = (key.charAt(i) - '0' + j + 10) % 10;
                    String newCombination = key.substring(0, i) + newDigit + key.substring(i + 1);
                    if (!visited.contains(newCombination) && !set.contains(newCombination)) {
                        queue.offer(new Pair(newCombination, moves + 1));
                    }
                }
            }
        }
        return -1;
    }

    // 834. Sum of Distances in Tree
    private Map<Integer, List<Integer>> graph834;
    // The number of nodes in the subtree where root is Node ith
    private int[] count;
    // The sum of distances of all nodes in subtree where root is Node ith
    private int[] res;

    private void dfs(int node, int parent) {
        for (int child : graph834.get(node)) {
            if (child != parent) {
                dfs(child, node);
                count[node] += count[child];
                res[node] += res[child] + count[child];
            }
        }
    }

    private void dfs2(int node, int parent) {
        for (int child : graph834.get(node)) {
            if (child != parent) {
                res[child] = res[node] - count[child] + (count.length - count[child]);
                dfs2(child, node);
            }
        }
    }

    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        graph834 = new HashMap<>();
        count = new int[n];
        res = new int[n];

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph834.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph834.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        dfs(0, -1);
        dfs2(0, -1);
        return count;
    }

    // 950. Reveal Cards In Increasing Order
    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        Arrays.sort(deck);
        Deque<Integer> queue = new LinkedList<>();
        queue.addFirst(deck[n - 1]);
        for (int i = n - 2; i >= 0; i--) {
            int last = queue.removeLast();
            queue.addFirst(last);
            queue.addFirst(deck[i]);
        }

        for (int i = 0; i < n; i++) {
            deck[i] = queue.removeFirst();
        }
        return deck;
    }

    // 988. Smallest String Starting From Leaf
    public String smallestFromLeaf(TreeNode root) {
        return null;
    }

    // 1137. N-th Tribonacci Number
    public int tribonacci(int n) {
        if (n == 0) return 0;
        if (n == 1 || n == 2) return 1;

        int[] memo = new int[n + 1];
        memo[1] = 1; memo[2] = 1;

        for (int i = 3; i <= n; i++) {
            memo[i] = memo[i - 1] + memo[i - 2] + memo[i - 3];
        }
        return memo[n];
    }

    // 1249. Minimum Remove to Make Valid Parentheses
    /* ---- Stack Method ---- */
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        char[] arr = s.toCharArray();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                stack.push(i);
            } else if (arr[i] == ')') {
                if (stack.isEmpty()) {
                    arr[i] = '0';
                } else {
                    stack.pop();
                }
            }
        }

        while (!stack.isEmpty()) {
            int i = stack.pop();
            arr[i] = '0';
        }
        StringBuilder sb = new StringBuilder();
        for (char c : arr) {
            if (c != '0') sb.append(c);
        }
        return sb.toString();
    }

    /* ---- Iterator Method ---- */
    public String minRemoveToMakeValidII(String s) {
        int count = 0;
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '(') {
                count++;
            } else if (arr[i] == ')') {
                if (count == 0) {
                    arr[i] = '0';
                } else {
                    count--;
                }
            }
        }
        count = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == ')') {
                count++;
            } else if (arr[i] == '(') {
                if (count == 0) {
                    arr[i] = '0';
                } else {
                    count--;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != '0') sb.append(arr[i]);
        }
        return sb.toString();
    }

    // 1289. Minimum Falling Path Sum II
    public int minFallingPathSum(int[][] grid) {
        return 0;
    }

    // 1544. Make The String Great
    public String makeGood(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && Math.abs(c - stack.peek()) == 32) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        String res = "";
        while (!stack.isEmpty()) {
            res = stack.pop() + res;
        }
        return res;
    }

    // 1614. Maximum Nesting Depth of the Parentheses
    public int maxDepth(String s) {
        int depth = 0, max = Integer.MIN_VALUE;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                depth += 1;
            } else if (c == ')') {
                depth -= 1;
            }
            max = Math.max(depth, max);
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }

    // 1700. Number of Students Unable to Eat Lunch
    public int countStudents(int[] students, int[] sandwiches) {
        int student0 = 0, student1 = 0;
        for (int student : students) {
            if (student == 0) {
                student0++;
            } else {
                student1++;
            }
        }

        for (int sandwich : sandwiches) {
            if (sandwich == 0) {
                if (student0 > 0) {
                    student0--;
                } else {
                    return student1;
                }
            } else {
                if (student1 > 0) {
                    student1--;
                } else {
                    return student0;
                }
            }
        }

        return 0;
    }

    // 1971. Find if Path Exists in Graph
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        Set<Integer> visited = new HashSet<>();
        return validatePathDFS(graph, source, destination, visited);
    }

    /* ---- DFS Method ---- */
    private boolean validatePathDFS(Map<Integer, List<Integer>> graph, int node, int destination, Set<Integer> visited) {
        if (node == destination) return true;

        visited.add(node);
        List<Integer> neighbor = graph.getOrDefault(node, new ArrayList<>());
        for (Integer n : neighbor) {
            if (!visited.contains(n)) {
                if (validatePathDFS(graph, n, destination, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* ---- BFS Method ---- */
    private boolean validatePathBFS(Map<Integer, List<Integer>> graph, int node, int destination, Set<Integer> visited) {
        visited.add(node);
        Queue<Integer> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (cur == destination) return true;
            List<Integer> neighbor = graph.getOrDefault(cur, new ArrayList<>());
            for (Integer n : neighbor) {
                if (!visited.contains(n)) {
                    visited.add(n);
                    queue.offer(n);
                }
            }
        }
        return false;
    }

    /* ---- Standard Graph Algorithms Method ---- */
    private boolean validatePathDijikstra(int n, Map<Integer, List<Integer>> graph, int source, int destination) {
        int[] distances = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[source] = 0;

        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        queue.add(new int[]{source, 0});

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int cur = node[0];
            int distance = node[1];

            if (cur == destination) return true;
            if (distance > distances[cur]) continue;

            List<Integer> neighbors = graph.getOrDefault(cur, new ArrayList<>());
            for (Integer neighbor : neighbors) {
                int newDistance = distance + 1;
                if (newDistance < distances[neighbor]) {
                    distances[neighbor] = newDistance;
                    queue.offer(new int[]{neighbor, newDistance});
                }
            }
        }
        return false;
    }

    /* ---- Union-Find (Disjoint Set Union) Method ---- */
    private int[] parent;
    private int[] rank;

    public boolean validatePathUnionFind(int n, int[][] edges, int source, int destination) {
        parent = new int[n];
        rank = new int[n];

        // Initialize parent pointers and ranks
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        // Union-find operations based on given edges
        for (int[] edge : edges) {
            union(edge[0], edge[1]);
        }

        // Check if source and destination belong to the same set
        return find(source) == find(destination);
    }

    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }

    // 1992. Find All Groups of Farmland
    public int[][] findFarmland(int[][] land) {
        int m = land.length, n = land[0].length;
        List<int[]> res = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (land[i][j] == 1) {
                    int[] farm = new int[4];
                    farm[0] = i;
                    farm[1] = j;

                    int row = i, col = j;
                    while (row < m && land[row][j] == 1) row++;
                    while (col < n && land[i][col] == 1) col++;
                    farm[2] = row - 1;
                    farm[3] = col - 1;
                    res.add(farm);

                    for (int k = i; k < row; k++) {
                        for (int g = j; g < col; g++) {
                            land[k][g] = 0;
                        }
                    }
                }
            }
        }
        int[][] array = new int[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            array[i] = res.get(i);
        }
        return array;
    }

    // 2073. Time Needed to Buy Tickets
    public int timeRequiredToBuy(int[] tickets, int k) {
        int n = tickets.length;
        int ticket = tickets[k];
        int sold = 0, count = 0, countBefore = k + 1;
        for (int i = 0; i < n; i++) {
            if (tickets[i] < ticket) {
                sold += tickets[i];
                count++;
                if (i < k) {
                    countBefore--;
                }
            }

        }
        return (n - count) * (ticket - 1) + sold + countBefore;
    }

    // 2997. Minimum Number of Operations to Make Array XOR Equal to K
    public int minOperations(int[] nums, int k) {
        int finalXor = 0, count = 0;
        for (int num : nums) {
            finalXor = finalXor ^ num;
        }

        while (k > 0 || finalXor > 0) {
            if (k % 2 != finalXor % 2) {
                count++;
            }
            k /= 2;
            finalXor /= 2;
        }
        return count;
    }
}
