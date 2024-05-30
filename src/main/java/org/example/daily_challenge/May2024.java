package org.example.daily_challenge;

import org.example.utilities.ListNode;
import org.example.utilities.TreeNode;

import java.util.*;

public class May2024 {
    final int MOD = 1000000007;

    // 78. Subsets
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());

        for (int i = 1; i < (1 << n); i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }
            res.add(subset);
        }
        return res;
    }

    // 131. Palindrome Partitioning
    public List<List<String>> res;

    public List<List<String>> partition(String s) {
        res = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>());
        return res;
    }

    private void backtrack(String s, int start, List<String> path) {
        if (start == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int end = start + 1; end <= s.length(); end++) {
            if (isPalindrome(s.substring(start, end + 1))) {
                path.add(s.substring(start, end));
                backtrack(s, end, path);
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

    // 140. Word Break II
    public List<String> wordBreak(String s, List<String> wordDict) {
        HashSet<String> dict = new HashSet<>(wordDict);
        HashMap<Integer, List<String>> map = new HashMap<>();
        return wordBreakerRecursion(s, 0, dict, map);
    }

    private List<String> wordBreakerRecursion(String s, int startIndex, HashSet<String> dict, HashMap<Integer, List<String>> map) {
        if (map.containsKey(startIndex)) {
            return map.get(startIndex);
        }
        List<String> validSubStr = new ArrayList<>();
        if (startIndex == s.length()) {
            validSubStr.add("");
        }

        for (int end = startIndex + 1; end <= s.length(); end++) {
            String prefix = s.substring(startIndex, end);
            if (dict.contains(prefix)) {
                List<String> suffixes = wordBreakerRecursion(s, end, dict, map);
                for (String suffix : suffixes) {
                    validSubStr.add(prefix + (suffix.isEmpty() ? "" : " ") + suffix);
                }
            }
        }
        map.put(startIndex, validSubStr);
        return validSubStr;
    }

    // 165. Compare Version Numbers
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        for (int i = 0; i < Math.max(v1.length, v2.length); i++) {
            int num1 = i < v1.length ? Integer.parseInt(v1[i]) : 0;
            int num2 = i < v2.length ? Integer.parseInt(v2[i]) : 0;
            if (num1 < num2) {
                return -1;
            }
            if (num1 > num2) {
                return 1;
            }
        }
        return 0;
    }

    // 237. Delete Node in a Linked List
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    // 552. Student Attendance Record II
    /* METHOD 1: RECURSION */
    public int checkRecord(int n) {
        int[][][] dp = new int[n][2][3];

        for (int i = 0; i < n; i++) {
            // No of Absent
            for (int j = 0; j < 2; j++) {
                // No of Late
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }
        return checkRecordRecursion(0, 0, 0, n, dp);
    }

    private int checkRecordRecursion(int index, int absentCount, int lateCount, int n, int[][][] dp) {
        if (index == n) {
            return 1;
        }

        if (dp[index][absentCount][lateCount] != -1) {
            return dp[index][absentCount][lateCount];
        }

        int nextAbsent = absentCount < 1 ? checkRecordRecursion(index + 1, absentCount + 1, 0, n, dp) : 0;
        int nextLate = lateCount < 2 ? checkRecordRecursion(index + 1, absentCount, lateCount + 1, n, dp) : 0;
        int nextPresent = checkRecordRecursion(index + 1, absentCount, 0, n, dp);
        int totalWay = ((nextAbsent + nextLate) % MOD + nextPresent) % MOD;
        dp[index][absentCount][lateCount] = totalWay;
        return totalWay;
    }

    /* METHOD 2: DP
        We only care about state at index - 1 and current state, so instead we use a n * 2 * 3 arrays to cover all n state, we only need 2 * 2 * 3 arrays, or we can say we need 2 arrays 2 * 3, one for last state, and one for current state;
     */
    public int checkRecord2(int n) {
        int[][] last = new int[2][3];
        int[][] current = new int[2][3];
        last[0][0] = 1;

        for (int i = 0; i < n; i++) {
            // No of Absent
            for (int countAbsent = 0; countAbsent < 2; countAbsent++) {
                // No of Late
                for (int countLate = 0; countLate < 3; countLate++) {
                    current[countAbsent][0] = (current[countAbsent][0] + last[countAbsent][countLate]) % MOD;

                    if (countAbsent == 0) {
                        current[countAbsent + 1][0] = (current[countAbsent + 1][0] + last[countAbsent][countLate]) % MOD;
                    }

                    if (countLate < 2) {
                        current[countAbsent][countLate + 1] = (current[countAbsent][countLate + 1] + last[countAbsent][countLate]) % MOD;
                    }
                }
            }
            last = current;
            current = new int[2][3];
        }

        int res = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                res = (res + last[i][j]) % MOD;
            }
        }
        return res;
    }

    // 506. Relative Ranks
    public String[] findRelativeRanks(int[] score) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> b[1] - a[1]);

        for (int i = 0; i < score.length; i++) {
            queue.add(new int[]{i, score[i]});
        }
        int count = 0;
        String[] res = new String[score.length];
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            if (count == 0) {
                res[arr[0]] = "Gold Medal";
            } else if (count == 1) {
                res[arr[0]] = "Silver Medal";
            } else if (count == 2) {
                res[arr[0]] = "Bronze Medal";
            } else {
                res[arr[0]] = String.valueOf(count + 1);
            }
            count++;
        }
        return res;
    }

    // 786. K-th Smallest Prime Fraction
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        double left = 0, right = 1, mid;
        int[] res = new int[2];

        while (left <= right) {
            mid = left + (right - left) / 2;
            int j = 1, total = 0, num = 0, den = 0;
            double maxFrac = 0;
            for (int i = 0; i < n; ++i) {
                while (j < n && arr[i] >= arr[j] * mid) {
                    ++j;
                }

                total += n - j;

                if (j < n && maxFrac < arr[i] * 1.0 / arr[j]) {
                    maxFrac = arr[i] * 1.0 / arr[j];
                    num = i;
                    den = j;
                }
            }

            if (total == k) {
                res[0] = arr[num];
                res[1] = arr[den];
                break;
            }

            if (total > k) {
                right = mid;
            } else {
                left = mid;
            }
        }

        return res;
    }

    // 857. Minimum Cost to Hire K Workers
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        return 0.0;
    }

    // 861. Score After Flipping Matrix
    public int matrixScore(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int res = 0;
        res += (1 << (cols - 1)) * rows;

        for (int j = 1; j < cols; j++) {
            int val = 1 << (cols - 1 - j);
            int numOfOne = 0;
            for (int i = 0; i < rows; i++) {
                if (grid[i][j] == grid[i][0]) {
                    numOfOne++;
                }
            }

            res += Math.max(numOfOne, rows - numOfOne) * val;
        }

        return res;
    }

    // 881. Boats to Save People
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0, j = people.length - 1;
        for (; i <= j; j--) {
            if (people[i] + people[j] <= limit) i++;
        }
        return people.length - 1 - j;
    }

    // 979. Distribute Coins in Binary Tree
    int ans;

    public int distributeCoins(TreeNode root) {
        ans = 0;
        dfsCoin(root);
        return ans;
    }

    private int dfsCoin(TreeNode node) {
        if (node == null) return 0;
        int leftDist = dfsCoin(node.left);
        int rightDist = dfsCoin(node.right);
        ans += Math.abs(leftDist) + Math.abs(rightDist);
        return node.val - 1 + leftDist + rightDist;
    }

    // 1208. Get Equal Substrings Within Budget
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length(), max = 0, left = 0, right = 0, sum = 0;
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(s.charAt(i) - t.charAt(i));
        }

        for (; right < n; right++) {
            sum += diff[right];
            if (sum > maxCost) {
                sum -= diff[left];
                left++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

    // 1219. Path with Maximum Gold
    public int getMaximumGold(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int max = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] != 0) {
                    max = Math.max(max, dfs(i, j, grid));
                }
            }
        }
        return max;
    }

    private int dfs(int row, int col, int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        int[] dir = new int[]{0, 1, 0, -1, 0};
        if (row < 0 || col < 0 || row >= rows || col >= cols || grid[row][col] == 0) return 0;

        int currentGold = grid[row][col];
        int localMaxGold = 0;
        grid[row][col] = 0;

        for (int i = 0; i < 4; i++) {
            int newX = row + dir[i];
            int newY = col + dir[i + 1];
            localMaxGold = Math.max(localMaxGold, currentGold + dfs(newX, newY, grid));
        }
        grid[row][col] = currentGold;
        return localMaxGold;
    }

    // 1255. Maximum Score Words Formed by Letters
    public int maxScoreWordsRes;

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        // count is used to count number of each char in letters;
        // letterCount is used to count number of each char in a word in words array;
        int[] count = new int[26];
        int[] letterCount = new int[26];
        maxScoreWordsRes = 0;

        for (char c : letters) {
            count[c - 'a']++;
        }

        maxScoreWordsBackTrack(words, score, count, letterCount, 0, 0);
        return maxScoreWordsRes;
    }

    private void maxScoreWordsBackTrack(String[] words, int[] score, int[] count, int[] letterCount, int pos, int temp) {
        for (int i = 0; i < 26; i++) {
            if (letterCount[i] > count[i]) return;
        }

        maxScoreWordsRes = Math.max(maxScoreWordsRes, temp);

        for (int i = pos; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                letterCount[c - 'a']++;
                temp += score[c - 'a'];
            }
            maxScoreWordsBackTrack(words, score, count, letterCount, i + 1, temp);

            for (char c : words[i].toCharArray()) {
                letterCount[c - 'a']--;
                temp -= score[c - 'a'];
            }
        }
    }

    // 1325. Delete Leaves With a Given Value
    public TreeNode removeLeafNodes(TreeNode root, int target) {
        root = delete(root, target);
        return root;
    }

    private TreeNode delete(TreeNode node, int target) {
        if (node == null) return null;
        if (node.val == target) {
            if (node.left == null && node.right == null) {
                return null;
            } else {
                if (node.left != null) {
                    node.left = delete(node.left, target);
                }
                if (node.right != null) {
                    node.right = delete(node.right, target);
                }
                if (node.left == null && node.right == null) {
                    return null;
                }
            }
        } else {
            if (node.left != null) {
                node.left = delete(node.left, target);
            }
            if (node.right != null) {
                node.right = delete(node.right, target);
            }
        }
        return node;
    }

    // 1404. Number of Steps to Reduce a Number in Binary Representation to One
    public int numSteps(String s) {
        int n = s.length(), res = 0;
        if (n == 1 && s.equals("1")) return 0;

        while (!s.equals("1")) {
            n = s.length();
            if (s.charAt(n - 1) == '0') {
                s = s.substring(0, n - 1);
            } else {
                int carry = 1;
                char[] arr = s.toCharArray();
                for (int i = arr.length - 1; i >= 0; i--) {
                    if (carry == 0) break;
                    if (arr[i] == '0') {
                        arr[i] = '1';
                        carry = 0;
                    } else {
                        arr[i] = '0';
                    }
                }
                s = String.valueOf(arr);
                if (carry == 1) {
                    s = "1" + s;
                }
            }
            res++;
        }
        return res;
    }

    // 1442. Count Triplets That Can Form Two Arrays of Equal XOR
    public int countTriplets(int[] arr) {
        int n = arr.length, res = 0;
        for (int i = 0; i < n; i++) {
            int val = arr[i];
            for (int j = i + 1; j < n; j++) {
                val = val ^ arr[j];
                if (val == 0 && j - i + 1 >= 2) {
                    res += j - i;
                }
            }
        }
        return res;
    }

    // 1608. Special Array With X Elements Greater Than or Equal X
    public int specialArray(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        if (nums[0] >= n) return n;

        for (int i = 1; i < n; i++) {
            if (nums[n - i] >= i && nums[n - i - 1] < i) {
                return i;
            }
        }
        return -1;
    }

    // 1863. Sum of All Subset XOR Totals
    /*
        Use another array and bit 0, 1 to mark which element is in the subset;
        int[] arr = {1, 2, 3, 4}
        int[] subset = {0, 0, 0, 0}
        The subset of arr can be:
         - 0001 {4}
         - 0010 {3}
         - 0011 {3, 4}
         - ....
         - 1111 {1, 2, 3, 4} = 15
        1 << n = 1 << 4 = 10000 = 16
    */
    public int subsetXORSum(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int i = 1; i < (1 << n); i++) {
            int curTotal = 0;
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    curTotal ^= nums[j];
                }
            }
            res += curTotal;
        }
        return res;
    }

    // 2000. Reverse Prefix of Word
    public String reversePrefix(String word, char ch) {
        int index = word.indexOf(ch);
        if (index == -1) {
            return word;
        } else {
            StringBuilder sb = new StringBuilder();
            String sub = sb.append(word, 0, index + 1).reverse().toString();
            return sub + word.substring(index + 1);
        }
    }

    // 2331. Evaluate Boolean Binary Tree
    public boolean evaluateTree(TreeNode root) {
        if (root.left == null && root.right == null) return root.val == 1;

        if (root.val == 2) {
            return evaluateTree(root.left) || evaluateTree(root.right);
        } else {
            return evaluateTree(root.left) && evaluateTree(root.right);
        }
    }

    // 2373. Largest Local Values in a Matrix
    public int[][] largestLocal(int[][] grid) {
        int n = grid.length;
        int[][] res = new int[n - 2][n - 2];
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < n - 1; j++) {
                int max = Integer.MIN_VALUE;

                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        max = Math.max(grid[k][l], max);
                    }
                }
                res[i - 1][j - 1] = max;
            }
        }
        return res;
    }

    // 2441. Largest Positive Integer That Exists With Its Negative
    public int findMaxK(int[] nums) {
        Arrays.sort(nums);
        int i = 0, j = nums.length - 1;
        while (nums[i] < 0 && i < j) {
            if (nums[j] == -nums[i]) {
                return nums[j];
            } else if (nums[j] > -nums[i]) {
                j--;
            } else {
                i++;
            }
        }
        return -1;
    }

    // 2487. Remove Nodes From Linked List
    public ListNode removeNodes(ListNode head) {
        List<Integer> valList = new ArrayList<>();
        ListNode curr = head;
        while (curr != null) {
            valList.add(curr.val);
            curr = curr.next;
        }

        boolean[] deleted = new boolean[valList.size()];
        int maxValue = Integer.MIN_VALUE;
        for (int j = valList.size() - 1; j >= 0; j--) {
            if (valList.get(j) >= maxValue) {
                maxValue = valList.get(j);
                deleted[j] = false;
            } else {
                deleted[j] = true;
            }
        }

        ListNode fakeHead = new ListNode(10000, head);
        ListNode prev = fakeHead;
        curr = head;
        int i = 0;
        while (curr != null) {
            if (deleted[i]) {
                curr = curr.next;
                prev.next = curr;
            } else {
                prev = prev.next;
                curr = curr.next;
            }
            i++;
        }
        return fakeHead.next;
    }

    // 2597. The Number of Beautiful Subsets
    /* METHOD 1: BACKTRACKING */
    public int beautifulSubsetsRes;
    private Map<Integer, Integer> beautifulSubsetsVisited;

    public int beautifulSubsets(int[] nums, int k) {
        int n = nums.length;
        beautifulSubsetsRes = 0;
        beautifulSubsetsVisited = new HashMap<>();

        beautifulSubsetsBacktrack(nums, k, 0);
        return beautifulSubsetsRes - 1;
    }

    private void beautifulSubsetsBacktrack(int[] nums, int k, int index) {
        if (index == nums.length) {
            beautifulSubsetsRes++;
            return;
        }

        int num = nums[index];
        if (!beautifulSubsetsVisited.containsKey(num - k) && !beautifulSubsetsVisited.containsKey(num + k)) {
            beautifulSubsetsVisited.put(num, beautifulSubsetsVisited.getOrDefault(num, 0) + 1);
            beautifulSubsetsBacktrack(nums, k, index + 1);
            beautifulSubsetsVisited.put(num, beautifulSubsetsVisited.get(num) - 1);
            if (beautifulSubsetsVisited.get(num) == 0) {
                beautifulSubsetsVisited.remove(num);
            }
        }

        beautifulSubsetsBacktrack(nums, k, index + 1);
    }


    private boolean isBeautiful(ArrayList<Integer> arr, int k) {
        if (arr.isEmpty()) return false;
        if (arr.size() == 1) return true;
        for (int i = 0; i < arr.size() - 1; i++) {
            if (arr.get(i + 1) - arr.get(i) == k) {
                return false;
            }
        }
        return true;
    }

    // 2812. Find the Safest Path in a Grid
    private final int[] direction = new int[]{0, 1, 0, -1, 0};

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size();
        if (grid.get(0).get(0) == 1 || grid.get(n - 1).get(n - 1) == 1) return 0;

        int[][] score = new int[n][n];
        int[][] visited = new int[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        for (int i = 0; i < n; i++) {
            Arrays.fill(score[i], Integer.MAX_VALUE);
        }

        bfs(grid, score, n);
        pq.offer(new int[]{score[0][0], 0, 0});
        visited[0][0] = 1;

        while (!pq.isEmpty()) {
            int[] cell = pq.poll();
            int safe = cell[0], row = cell[1], col = cell[2];

            if (row == n - 1 && col == n - 1) return safe;

            for (int i = 0; i < 4; i++) {
                int newRow = row + direction[i];
                int newCol = col + direction[i + 1];
                if (newRow >= 0 && newCol >= 0 && newRow < n && newCol < n && visited[newRow][newCol] == 0) {
                    int s = Math.min(safe, score[newRow][newCol]);
                    pq.offer(new int[]{s, newRow, newCol});
                    visited[row][col] = 1;
                }
            }
        }
        return -1;
    }

    // BFS from the thief to other cell
    private void bfs(List<List<Integer>> grid, int[][] score, int n) {
        Queue<int[]> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid.get(i).get(j) == 1) {
                    score[i][j] = 0;
                    q.offer(new int[]{i, j});
                }
            }
        }

        while (!q.isEmpty()) {
            int[] cell = q.poll();
            int row = cell[0], col = cell[1];
            int s = score[row][col];

            for (int i = 0; i < 4; i++) {
                int newRow = row + direction[i];
                int newCol = col + direction[i + 1];
                if (newRow >= 0 && newCol >= 0 && newRow < n && newCol < n && score[newRow][newCol] > 1 + s) {
                    score[newRow][newCol] = 1 + s;
                    q.offer(new int[]{newRow, newCol});
                }
            }
        }
    }

    // 2816. Double a Number Represented as a Linked List
    public ListNode doubleIt(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode curr = head;
        int val = 0;
        while (curr != null) {
            stack.push(curr.val);
            curr = curr.next;
        }

        ListNode newTail = null;
        while (!stack.isEmpty() || val != 0) {
            newTail = new ListNode(0, newTail);

            if (!stack.isEmpty()) {
                val += stack.pop() * 2;
            }
            newTail.val = val % 10;
            val /= 10;
        }
        return newTail;
    }

    // 3068. Find the Maximum Sum of Node Values
    public long maximumValueSum(int[] nums, int k, int[][] edges) {
        long totalSum = 0;
        int count = 0;
        int positiveMin = Integer.MAX_VALUE;
        int negativeMax = Integer.MIN_VALUE;

        for (int nodeValue : nums) {
            int nodeValAfterOperation = nodeValue ^ k;
            totalSum += nodeValue;
            int netChange = nodeValAfterOperation - nodeValue;

            if (netChange > 0) {
                positiveMin = Math.min(positiveMin, netChange);
                totalSum += netChange;
                count += 1;
            } else {
                negativeMax = Math.max(negativeMax, netChange);
            }
        }

        if (count % 2 == 0) {
            return totalSum;
        }
        return Math.max(totalSum - positiveMin, totalSum + negativeMax);
    }

    // 3075. Maximize Happiness of Selected Children
    public long maximumHappinessSum(int[] happiness, int k) {
        long res = 0;
        int c = 0, n = happiness.length;
        Arrays.sort(happiness);

        for (int i = n - 1; i >= n - k; i--) {
            int value = happiness[i] - c++;
            if (value > 0) {
                res += value;
            }
        }
        return res;
    }

}
