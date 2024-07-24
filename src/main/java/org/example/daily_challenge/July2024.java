package org.example.daily_challenge;

import org.example.utilities.ListNode;
import org.example.utilities.TreeNode;

import java.util.*;

public class July2024 {
    // 726. Number of Atoms
    public String countOfAtoms(String formula) {
        Stack<HashMap<String, Integer>> stack = new Stack<>();
        stack.push(new HashMap<>());
        int index = 0;
        while (index < formula.length()) {
            if (formula.charAt(index) == '(') {
                stack.push(new HashMap<>());
                index++;
            } else if (formula.charAt(index) == ')') {
                HashMap<String, Integer> map = stack.pop();
                index++;
                StringBuilder multiplier = new StringBuilder();
                while (index < formula.length() && Character.isDigit(formula.charAt(index))) {
                    multiplier.append(formula.charAt(index));
                    index++;
                }

                if (!multiplier.isEmpty()) {
                    int mult = Integer.parseInt(multiplier.toString());
                    for (String atom : map.keySet()) {
                        map.put(atom, map.get(atom) * mult);
                    }
                }

                for (Map.Entry<String, Integer> entry : map.entrySet()) {
                    stack.peek().put(entry.getKey(), stack.peek().getOrDefault(entry.getKey(), 0) + entry.getValue());
                }
            } else {
                StringBuilder atom = new StringBuilder();
                atom.append(formula.charAt(index));
                index++;
                while (index < formula.length() && Character.isLowerCase(formula.charAt(index))) {
                    atom.append(formula.charAt(index));
                    index++;
                }

                StringBuilder currCount = new StringBuilder();
                while (index < formula.length() && Character.isDigit(formula.charAt(index))) {
                    currCount.append(formula.charAt(index));
                    index++;
                }
                int count = currCount.length() > 0 ? Integer.parseInt(currCount.toString()) : 1;
                stack.peek()
                        .put(atom.toString(), stack.peek().getOrDefault(atom.toString(), 0) + count);
            }
        }

        TreeMap<String, Integer> finalMap = new TreeMap<>(stack.peek());

        StringBuilder ans = new StringBuilder();
        for (String atom : finalMap.keySet()) {
            ans.append(atom);
            if (finalMap.get(atom) > 1) {
                ans.append(finalMap.get(atom));
            }
        }
        return ans.toString();
    }

    // 735. Asteroid Collision
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int asteroid : asteroids) {
            if (stack.isEmpty()) {
                stack.push(asteroid);
            } else {
                while (!stack.isEmpty() && (stack.peek() > 0 && asteroid < 0)) {
                    if (Math.abs(stack.peek()) >= Math.abs(asteroid)) {
                        if (Math.abs(stack.peek()) == Math.abs(asteroid)) {
                            stack.pop();
                        }
                        asteroid = 0;
                        break;
                    } else {
                        stack.pop();
                    }
                }
                if (asteroid != 0) {
                    stack.push(asteroid);
                }
            }
        }
        int[] res = new int[stack.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        return res;
    }

    // 1110. Delete Nodes And Return Forest
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        List<TreeNode> res = new ArrayList<>();
        HashSet<Integer> setToDelete = new HashSet<>();
        for (int value : to_delete) {
            setToDelete.add(value);
        }

        root = postOrder(root, setToDelete, res);
        if (root != null) {
            res.add(root);
        }
        return res;
    }

    private TreeNode postOrder(TreeNode root, HashSet<Integer> setToDelete, List<TreeNode> res) {
        if (root == null) return null;

        root.left = postOrder(root.left, setToDelete, res);
        root.right = postOrder(root.right, setToDelete, res);

        if (setToDelete.contains(root.val)) {
            if (root.left != null) {
                res.add(root.left);
            }

            if (root.right != null) {
                res.add(root.right);
            }
            return null;
        }
        return root;
    }

    // 1190. Reverse Substrings Between Each Pair of Parentheses
    public String reverseParentheses(String s) {
        Stack<StringBuilder> stack = new Stack<>();
        stack.push(new StringBuilder());
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(new StringBuilder());
            } else if (s.charAt(i) == ')') {
                StringBuilder topBuilder = stack.pop().reverse();
                stack.push(stack.pop().append(topBuilder));
            } else {
                stack.push(stack.pop().append(s.charAt(i)));
            }
        }

        return stack.pop().toString();
    }

    // 1380. Lucky Numbers in a Matrix
    public List<Integer> luckyNumbers(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int[] minArray = new int[rows];
        List<Integer> res = new ArrayList<>();
        Arrays.fill(minArray, Integer.MAX_VALUE);

        int[] maxArray = new int[cols];
        Arrays.fill(maxArray, Integer.MIN_VALUE);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                minArray[i] = Math.min(minArray[i], matrix[i][j]);
                maxArray[j] = Math.max(maxArray[j], matrix[i][j]);
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == minArray[i] && matrix[i][j] == maxArray[j]) {
                    res.add(matrix[i][j]);
                }
            }
        }
        return res;
    }

    // 1509. Minimum Difference Between Largest and Smallest Value in Three Moves
    public int minDifference(int[] nums) {
        Arrays.sort(nums);
        int max = Integer.MAX_VALUE, n = nums.length;
        if (n <= 3) return 0;
        max = Math.min(max, nums[n - 1] - nums[3]);
        max = Math.min(max, nums[n - 2] - nums[2]);
        max = Math.min(max, nums[n - 3] - nums[1]);
        max = Math.min(max, nums[n - 4] - nums[0]);
        return max;
    }

    // 1518. Water Bottles
    public int numWaterBottles(int numBottles, int numExchange) {
        int res = 0;
        while (numBottles >= numExchange) {
            numBottles -= numExchange;
            numBottles += 1;
            res += numExchange;
        }
        res += numBottles;
        return res;
    }

    // 1530. Number of Good Leaf Nodes Pairs
    public int countPairs(TreeNode root, int distance) {
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        Set<TreeNode> leaves = new HashSet<>();

        traverseTree(root, null, graph, leaves);
        int res = 0;

        for (TreeNode leaf : leaves) {
            Queue<TreeNode> bfsQueue = new LinkedList<>();
            Set<TreeNode> seen = new HashSet<>();
            bfsQueue.add(leaf);
            seen.add(leaf);

            for (int i = 0; i <= distance; i++) {
                int size = bfsQueue.size();
                for (int j = 0; j < size; j++) {
                    TreeNode curNode = bfsQueue.remove();

                    if (leaves.contains(curNode) && curNode != leaf) {
                        res++;
                    }
                    if (graph.containsKey(curNode)) {
                        for (TreeNode neighbor : graph.get(curNode)) {
                            if (!seen.contains(neighbor)) {
                                bfsQueue.add(neighbor);
                                seen.add(neighbor);
                            }
                        }
                    }
                }
            }
        }
        return res / 2;
    }

    private void traverseTree(TreeNode currentNode, TreeNode prevNode, Map<TreeNode, List<TreeNode>> graph, Set<TreeNode> leaves) {
        if (currentNode == null) return;

        if (currentNode.left == null && currentNode.right == null) {
            leaves.add(currentNode);
        }

        if (prevNode != null) {
            graph.computeIfAbsent(prevNode, k -> new ArrayList<>()).add(currentNode);
            graph.computeIfAbsent(currentNode, k -> new ArrayList<>()).add(prevNode);
        }

        traverseTree(currentNode.left, currentNode, graph, leaves);
        traverseTree(currentNode.right, currentNode, graph, leaves);
    }

    // 1550. Three Consecutive Odds
    public boolean threeConsecutiveOdds(int[] arr) {
        int count = 0;
        for (int num : arr) {
            if (num % 2 == 1) {
                count++;
            } else {
                count = 0;
            }
            if (count == 3) return true;
        }
        return false;
    }

    // 1598. Crawler Log Folder
    public int minOperations(String[] logs) {
        int depth = 0;
        for (String log : logs) {
            if (log.charAt(0) == '.') {
                if (log.charAt(1) == '.') {
                    if (depth > 0) {
                        depth--;
                    }
                }
            } else {
                depth++;
            }
        }
        return depth;
    }

    // 1605. Find Valid Matrix Given Row and Column Sums
    public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
        int[][] res = new int[rowSum.length][colSum.length];
        for (int i = 0; i < rowSum.length; i++) {
            for (int j = 0; j < colSum.length; j++) {
                res[i][j] = Math.min(rowSum[i], colSum[j]);
                rowSum[i] -= res[i][j];
                colSum[j] -= res[i][j];
            }
        }
        return res;
    }

    // 1636. Sort Array by Increasing Frequency
    public int[] frequencySort(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Integer[] numsObj = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsObj[i] = nums[i];
        }

        Arrays.sort(numsObj, (a, b) -> {
            int valueComparison = map.get(a).compareTo(map.get(b));
            if (valueComparison != 0) {
                return valueComparison;
            } else {
                return b.compareTo(a);
            }
        });

        for (int i = 0; i < nums.length; i++) {
            nums[i] = numsObj[i];
        }
        return nums;
    }

    // 1701. Average Waiting Time
    public double averageWaitingTime(int[][] customers) {
        double sum = 0;
        int endTime = -1;
        for (int[] customer : customers) {
            endTime = Math.max(endTime, customer[0]);
            endTime += customer[1];
            sum += endTime - customer[0];
        }
        return sum / customers.length;
    }

    // 1717. Maximum Score From Removing Substrings
    /* METHOD 1: USE STACK */
    public int maximumGain(String s, int x, int y) {
        Stack<Character> stack = new Stack<>();
        int res = 0;

        if (x < y) {
            // exchange x and y, reverse string s
            int temp = x;
            x = y;
            y = temp;
            s = new StringBuilder(s).reverse().toString();
        }
        // Remove ab first
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'b') {
                if (stack.isEmpty() || stack.peek() != 'a') {
                    stack.push(s.charAt(i));
                } else {
                    stack.pop();
                    res += x;
                }
            } else {
                stack.push(s.charAt(i));
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        s = sb.reverse().toString();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                if (stack.isEmpty() || stack.peek() != 'b') {
                    stack.push(s.charAt(i));
                } else {
                    stack.pop();
                    res += y;
                }
            } else {
                stack.push(s.charAt(i));
            }
        }
        return res;
    }

    /* METHOD 2: COUNTING */
    public int maximumGainII(String s, int x, int y) {
        int res = 0, aCount = 0, bCount = 0;

        if (x < y) {
            // exchange x and y, reverse string s
            int temp = x;
            x = y;
            y = temp;
            s = new StringBuilder(s).reverse().toString();
        }
        // Remove ab first
        for (char c : s.toCharArray()) {
            if (c == 'a') {
                aCount++;
            } else if (c == 'b') {
                if (aCount > 0) {
                    aCount--;
                    res += x;
                } else {
                    bCount++;
                }
            } else {
                res += Math.min(bCount, aCount) * y;
                aCount = bCount = 0;
            }
        }

        res += Math.min(bCount, aCount) * y;
        return res;
    }

    // 1823. Find the Winner of the Circular Game
    public int findTheWinner(int n, int k) {
        int res = 0;
        for (int i = 1; i <= n; i++) {
            res = (res + k) % i;
        }
        return res + 1;
    }

    // 2058. Find the Minimum and Maximum Number of Nodes Between Critical Points
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        ListNode dummy = head.next;
        int prevVal = head.val, index = 1;
        int[] res = new int[2];
        List<Integer> critPoints = new ArrayList<>();
        while (dummy.next != null) {
            if (dummy.val < prevVal && dummy.val < dummy.next.val) {
                critPoints.add(index);
            } else if (dummy.val > prevVal && dummy.val > dummy.next.val) {
                critPoints.add(index);
            }
            prevVal = dummy.val;
            dummy = dummy.next;
            index++;
        }
        int min = Integer.MAX_VALUE, max = -1;
        for (int i = 1; i < critPoints.size(); i++) {
            int dist = critPoints.get(i) - critPoints.get(i - 1);
            min = Math.min(min, dist);
        }

        if (min == Integer.MAX_VALUE) {
            res[0] = -1;
        } else {
            res[0] = min;
        }
        if (critPoints.size() > 1) {
            max = critPoints.get(critPoints.size() - 1) - critPoints.get(0);
        }
        res[1] = max;
        return res;
    }

    // 2096. Step-By-Step Directions From a Binary Tree Node to Another
    public String getDirections(TreeNode root, int startValue, int destValue) {
        StringBuilder pathToStart = new StringBuilder();
        StringBuilder pathToDest = new StringBuilder();
        findPath(root, startValue, pathToStart);
        findPath(root, destValue, pathToDest);

        int commonPathLength = 0;
        while (commonPathLength < pathToStart.length()
                && commonPathLength < pathToDest.length()
                && pathToStart.charAt(commonPathLength) ==
                pathToDest.charAt(commonPathLength)
        ) {
            commonPathLength++;
        }

        StringBuilder res = new StringBuilder();
        res.append("U".repeat(pathToStart.length() - commonPathLength));

        for (int i = commonPathLength; i < pathToDest.length(); i++) {
            res.append(pathToDest.charAt(i));
        }
        return res.toString();
    }

    private boolean findPath(TreeNode root, int targetValue, StringBuilder path) {
        if (root == null) return false;
        if (root.val == targetValue) return true;

        path.append("L");
        if (findPath(root.left, targetValue, path)) return true;
        path.setLength(path.length() - 1);

        path.append("R");
        if (findPath(root.right, targetValue, path)) return true;
        path.setLength(path.length() - 1);

        return false;
    }

    // 2181. Merge Nodes in Between Zeros
    public ListNode mergeNodes(ListNode head) {
        ListNode dummy = head.next;
        while (dummy != null) {
            int sum = 0;
            ListNode temp = dummy;
            while (temp.val != 0) {
                sum += temp.val;
                temp = temp.next;
            }
            dummy.val = sum;
            dummy.next = temp.next;
            dummy = dummy.next;
        }
        return head.next;
    }

    // 2191. Sort the Jumbled Numbers
    public int[] sortJumbled(int[] mapping, int[] nums) {
        List<Integer[]> newNums = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int place = 1;
            int newNum = 0, temp = nums[i];
            if (nums[i] == 0) {
                newNums.add(new Integer[]{mapping[0], i});
                continue;
            }

            while (temp != 0) {
                newNum = place * mapping[temp % 10] + newNum;
                place *= 10;
                temp /= 10;
            }
            newNums.add(new Integer[]{newNum, i});
        }

        Collections.sort(newNums, (a, b) -> {
            if (Objects.equals(a[0], b[0])) {
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        });

        int[] res = new int[nums.length];
        for (int i = 0; i < newNums.size(); i++) {
            res[i] = nums[newNums.get(i)[1]];
        }
        return res;
    }

    // 2196. Create Binary Tree From Descriptions
    public TreeNode createBinaryTree(int[][] descriptions) {
        HashMap<Integer, TreeNode> map = new HashMap<>();
        Set<Integer> childSet = new HashSet<>();
        for (int[] description : descriptions) {
            int parentValue = description[0];
            int childValue = description[1];
            TreeNode childNode = map.computeIfAbsent(childValue, k -> new TreeNode(childValue));

            TreeNode parentNode = map.computeIfAbsent(parentValue, k -> new TreeNode(parentValue));
            if (description[2] == 1) {
                parentNode.left = childNode;
            } else {
                parentNode.right = childNode;
            }
            childSet.add(childValue);
        }

        for (int[] d : descriptions) {
            if (!childSet.contains(d[0])) {
                return map.get(d[0]);
            }
        }
        return null;
    }

    // 2418. Sort the People
    public String[] sortPeople(String[] names, int[] heights) {
        int n = heights.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(heights[i], i);
        }

        String[] res = new String[names.length];
        Arrays.sort(heights);
        for (int i = n - 1; i >= 0; i--) {
            res[n - 1 - i] = names[map.get(heights[i])];
        }
        return res;
    }

    // 2582. Pass the Pillow
    public int passThePillow(int n, int time) {
        int round = time / (n - 1);
        if (round % 2 == 0) {
            return time % (n - 1) + 1;
        } else {
            return n - time % n;
        }
    }

    // 2751. Robot Collisions
    static class Robot {
        int position;
        int health;
        char direction;
        int index;

        Robot(int position, int health, char direction, int index) {
            this.position = position;
            this.health = health;
            this.direction = direction;
            this.index = index;
        }
    }

    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        List<Robot> list = new ArrayList<>();

        for (int i = 0; i < positions.length; i++) {
            Robot robot = new Robot(positions[i], healths[i], directions.charAt(i), i);
            list.add(robot);
        }
        list.sort((a, b) -> a.position - b.position);

        Stack<Robot> stack = new Stack<>();
        for (Robot robot : list) {
            if (stack.isEmpty()) {
                stack.push(robot);
            } else {
                while (!stack.isEmpty() && (stack.peek().direction == 'R' && robot.direction == 'L')) {
                    if (stack.peek().health >= robot.health) {
                        if (stack.peek().health == robot.health) {
                            stack.pop();
                        } else {
                            Robot top = stack.pop();
                            top.health--;
                            stack.push(top);
                        }
                        robot.health = 0;
                        break;
                    } else {
                        stack.pop();
                        robot.health--;
                    }
                }
                if (robot.health != 0) {
                    stack.push(robot);
                }
            }
        }

        stack.sort((a, b) -> b.index - a.index);
        List<Integer> res = new ArrayList<>();
        int n = stack.size();
        for (int i = 0; i < n; i++) {
            res.add(stack.pop().health);
        }
        return res;
    }


}
