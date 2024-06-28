package org.example.daily_challenge;

import org.example.utilities.TreeNode;
import org.example.utilities.TrieNode;

import java.util.*;

public class June2024 {
    // 1. Two Sum
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    // 70. Climbing Stairs
    public int climbStairs(int n) {
        int[] dp = new int[2];
        Arrays.fill(dp, 1);
        for (int i = 2; i <= n; i++) {
            int step = dp[0] + dp[1];
            dp[0] = dp[1];
            dp[1] = step;
        }
        return dp[1];
    }

    // 75. Sort Colors
    public void sortColors(int[] nums) {
        int red = 0, white = 0, blue = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                red++;
            } else if (nums[i] == 1) {
                white++;
            } else if (nums[i] == 2) {
                blue++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (red > 0) {
                nums[i] = 0;
                red--;
            } else if (white > 0) {
                nums[i] = 1;
                white--;
            } else {
                nums[i] = 2;
                blue--;
            }
        }
    }

    // 198. House Robber
    public int rob(int[] nums) {
        int n = nums.length;
        int includeI = 0, excludeI = 0;

        for (int i = 0; i < n; i++) {
            int newExclude = Math.max(includeI, excludeI);
            includeI = excludeI + nums[i];
            excludeI = newExclude;
        }
        return Math.max(includeI, excludeI);
    }

    // 239. Sliding Window Maximum
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length, left = 0, right = 0, max = nums[right];
        int[] res = new int[n - k + 1];
        Deque<Integer> maxQueue = new ArrayDeque<>();
        for (; right < k; right++) {
            max = Math.max(max, nums[right]);
        }
        res[left] = max;

        for (right = 0; right < n; right++) {
            while (!maxQueue.isEmpty() && maxQueue.peekLast() < nums[right]) {
                maxQueue.pollLast();
            }

            maxQueue.add(nums[right]);
            if (right > k - 1) {
                if (right - left + 1 > k) {
                    if (maxQueue.peekFirst() == nums[left]) {
                        maxQueue.pollFirst();
                    }
                    left++;
                }
                res[left] = maxQueue.peekFirst();
            }
        }
        return res;
    }

    // 322. Coin Change
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    // 325.Maximum Size Subarray Sum Equals k
    // Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.
    public int lenOfLongSubarr(int nums[], int k) {
        int n = nums.length, sum = 0, res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                res = i - Math.max(res, map.get(sum - k));
            } else {
                map.put(sum, i);
            }
        }
        return res;
    }

    // 330. Patching Array
    public int minPatches(int[] nums, int n) {
        long miss = 1;
        int added = 0, i = 0;
        while (miss <= n) {
            if (i < nums.length && nums[i] <= miss) {
                miss += nums[i];
                i++;
            } else {
                miss += miss;
                added++;
            }
        }
        return added;
    }

    // 344. Reverse String
    public void reverseString(char[] s) {
        int l = 0, r = s.length - 1;
        while (l < r) {
            char temp = s[l];
            s[l] = s[r];
            s[r] = temp;
            l++;
            r--;
        }
    }

    // 409. Longest Palindrome
    public int longestPalindrome(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        int result = 0;
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) % 2 == 0) {
                result += 2;
            }
        }
        return result == s.length() ? result : result + 1;
    }

    // 502. IPO
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] projects = new int[n][2];
        for (int i = 0; i < n; i++) {
            projects[i] = new int[]{profits[i], capital[i]};
        }

        Arrays.sort(projects, (a, b) -> a[1] - b[1]);

        int j = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < k; i++) {
            while (j < n && projects[j][1] <= w) {
                pq.add(projects[j][0]);
                j++;
            }
            if (pq.isEmpty()) {
                break;
            }

            w += pq.poll();
        }
        return w;
    }

    // 523. Continuous Subarray Sum
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        if (n == 1) return false;
        long sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            int remainder = (int) sum % k;

            if (map.containsKey(remainder)) {
                int left = map.get(remainder);
                if (i - left > 1) {
                    return true;
                }
            } else {
                map.put(remainder, i);
            }
        }
        return false;
    }

    // 560. Subarray Sum Equals K
    public int subarraySum(int[] nums, int k) {
        int sum = 0, res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

    // 633. Sum of Square Numbers
    public boolean judgeSquareSum(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);
            if (b == (int) b) {
                return true;
            }
        }
        return false;
    }

    // 648. Replace Words
    public TrieNode root;

    public String replaceWords(List<String> dictionary, String sentence) {
        root = new TrieNode();
        for (String word : dictionary) {
            insertTrie(word);
        }

        StringBuilder sb = new StringBuilder();
        String[] arr = sentence.split(" ");
        for (String str : arr) {
            String res = searchWord(str);
            sb.append(res).append(" ");
        }
        return sb.toString().trim();
    }

    private void insertTrie(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (node.child[i] == null) {
                node.child[i] = new TrieNode();
            }
            node = node.child[i];
        }
        node.isEnd = true;
    }

    private String searchWord(String word) {
        TrieNode node = root;
        int j = 0;
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            j++;
            if (node.child[i] == null) {
                return word;
            } else if (node.child[i].isEnd) {
                return word.substring(0, j);
            } else {
                node = node.child[i];
            }
        }
        return word;
    }

    // 826. Most Profit Assigning Work
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = profit.length, res = 0;
        int[][] works = new int[n][2];
        for (int i = 0; i < n; i++) {
            works[i] = new int[]{profit[i], difficulty[i]};
        }
        Arrays.sort(works, (a, b) -> a[1] - b[1]);
        Arrays.sort(worker);

        int j = 0, max = 0;
        for (int k : worker) {
            while (j < n && works[j][1] <= k) {
                max = Math.max(max, works[j][0]);
                j++;
            }
            res += max;
        }
        return res;
    }

    // 846. Hand of Straights
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n % groupSize != 0) return false;
        Arrays.sort(hand);
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : hand) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : hand) {
            if (map.get(num) > 0) {
                for (int i = 0; i < groupSize; i++) {
                    if (map.containsKey(num + i) && map.get(num + i) > 0) {
                        map.put(num + i, map.get(num + i) - 1);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // 945. Minimum Increment to Make Array Unique
    /* METHOD 1: SORTING (O(nLog(n))) */
    public int minIncrementForUnique(int[] nums) {
        int res = 0, n = nums.length;
        Arrays.sort(nums);
        for (int i = 1; i < n; i++) {
            if (nums[i] <= nums[i - 1]) {
                res += nums[i - 1] - nums[i] + 1;
                nums[i] = nums[i - 1] + 1;
            }
        }
        return res;
    }

    /* METHOD 2: O(n) */
    public int minIncrementForUnique2(int[] nums) {
        int res = 0, n = nums.length, max = 0;
        for (int num : nums) {
            max = Math.max(num, max);
        }

        int[] freq = new int[n + max + 1];

        for (int num : nums) {
            freq[num]++;
        }


        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 1) {
                int dup = freq[i] - 1;
                res += dup;
                freq[i + 1] += dup;
            }
        }
        return res;
    }

    // 974. Subarray Sums Divisible by K
    public int subarraysDivByK(int[] nums, int k) {
        int remainder = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int res = 0;
        for (int num : nums) {
            remainder = (remainder + num) % k;
            if (remainder < 0) remainder = remainder + k;

            if (map.containsKey(remainder)) {
                res += map.get(remainder);
            }
            map.put(remainder, map.getOrDefault(remainder, 0) + 1);
        }
        return res;
    }

    // 995. Minimum Number of K Consecutive Bit Flips
    public int minKBitFlips(int[] nums, int k) {
        return 0;
    }

    // 1002. Find Common Characters
    public List<String> commonChars(String[] words) {
        List<String> result = new ArrayList<>();
        int[] last = count(words[0]);
        for (int i = 1; i < words.length; i++) {
            last = intersect(last, count(words[i]));
        }

        for (int i = 0; i < 26; i++) {
            while (last[i] > 0) {
                char a = (char) (i + 'a');
                result.add(String.valueOf(a));
                last[i]--;
            }
        }
        return result;
    }

    private int[] intersect(int[] a, int[] b) {
        for (int i = 0; i < a.length; i++) {
            a[i] = Math.min(a[i], b[i]);
        }
        return a;
    }

    private int[] count(String word) {
        int[] arr = new int[26];
        for (char c : word.toCharArray()) {
            arr[c - 'a']++;
        }
        return arr;
    }

    // 1038. Binary Search Tree to Greater Sum Tree
    public int nodeSum;

    public TreeNode bstToGst(TreeNode root) {
        nodeSum = 0;
        inOrderTraversal(root);
        return root;
    }

    private void inOrderTraversal(TreeNode root) {
        if (root == null) return;
        inOrderTraversal(root.right);
        nodeSum += root.val;
        root.val = nodeSum;
        inOrderTraversal(root.left);
    }

    // 1051. Height Checker
    public int heightChecker(int[] heights) {
        int[] expected = heights.clone();
        Arrays.sort(expected);
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != expected[i]) {
                res++;
            }
        }
        return res;
    }

    // 1052. Grumpy Bookstore Owner
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int curSum = 0, max = 0, index = 0;
        for (int i = 0; i < customers.length; i++) {
            curSum += customers[i] * grumpy[i];
            max = Math.max(curSum, max);
            if (i >= minutes - 1) {
                curSum -= customers[index] * grumpy[index];
                index++;
            }
        }

        for (int i = 0; i < customers.length; i++) {
            max += customers[i] * (1 - grumpy[i]);
        }
        return max;
    }

    // 1122. Relative Sort Array
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int j : arr1) {
            map.put(j, map.getOrDefault(j, 0) + 1);
        }

        int[] res = new int[arr1.length];
        int i = 0;
        for (int num : arr2) {
            if (map.containsKey(num)) {
                while (map.get(num) > 0) {
                    res[i] = num;
                    map.put(num, map.get(num) - 1);
                    i++;
                }
                map.remove(num);
            }
        }

        int[] remaining = new int[arr1.length - i];
        int index = 0;
        for (int num : arr1) {
            if (map.containsKey(num)) {
                remaining[index] = num;
                index++;
            }
        }

        Arrays.sort(remaining);
        index = 0;
        while (i < arr1.length) {
            res[i] = remaining[index];
            i++;
            index++;
        }
        return res;
    }

    // 1248. Count Number of Nice Subarrays
    public int numberOfSubarrays(int[] nums, int k) {
        int n = nums.length, res = 0, sum = 0;
        for (int i = 0; i < n; i++) {
            nums[i] = nums[i] % 2;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }

    // 1382. Balance a Binary Search Tree
    /* METHOD 1: REBUILD NEW TREE */
    public List<Integer> arr;

    public TreeNode balanceBST(TreeNode root) {
        arr = new ArrayList<>();
        inOrderTraversal1382(root);
        return sortedArrToTree(0, arr.size());
    }

    private void inOrderTraversal1382(TreeNode root) {
        if (root == null) return;
        inOrderTraversal1382(root.left);
        arr.add(root.val);
        inOrderTraversal1382(root.right);
    }

    private TreeNode sortedArrToTree(int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        TreeNode tree = new TreeNode(arr.get(mid));
        tree.left = sortedArrToTree(start, mid - 1);
        tree.right = sortedArrToTree(mid + 1, end);
        return tree;
    }


    /* METHOD 2: IN PLACE BUILD */
    public TreeNode balanceBSTII(TreeNode root) {
        if (root == null) return null;

        // Step 1: Create the backbone (vine)
        // Temporary dummy node
        TreeNode vineHead = new TreeNode(0);
        vineHead.right = root;
        TreeNode current = vineHead;
        while (current.right != null) {
            if (current.right.left != null) {
                rightRotate(current, current.right);
            } else {
                current = current.right;
            }
        }

        // Step 2: Count the nodes
        int nodeCount = 0;
        current = vineHead.right;
        while (current != null) {
            ++nodeCount;
            current = current.right;
        }

        // Step 3: Create a balanced BST
        int m = (int) Math.pow(2, Math.floor(Math.log(nodeCount + 1) / Math.log(2))) - 1;
        makeRotations(vineHead, nodeCount - m);
        while (m > 1) {
            m /= 2;
            makeRotations(vineHead, m);
        }

        TreeNode balancedRoot = vineHead.right;
        return balancedRoot;
    }

    // Function to perform a right rotation
    private void rightRotate(TreeNode parent, TreeNode node) {
        TreeNode tmp = node.left;
        node.left = tmp.right;
        tmp.right = node;
        parent.right = tmp;
    }

    // Function to perform a left rotation
    private void leftRotate(TreeNode parent, TreeNode node) {
        TreeNode tmp = node.right;
        node.right = tmp.left;
        tmp.left = node;
        parent.right = tmp;
    }

    // Function to perform a series of left rotations to balance the vine
    private void makeRotations(TreeNode vineHead, int count) {
        TreeNode current = vineHead;
        for (int i = 0; i < count; ++i) {
            TreeNode tmp = current.right;
            leftRotate(current, tmp);
            current = current.right;
        }
    }

    // 1438. Longest Continuous Subarray With Absolute Diff Less Than or Equal to Limit
    public int longestSubarray(int[] nums, int limit) {
        int res = 0, left = 0;
        Deque<Integer> maxQueue = new ArrayDeque<>();
        Deque<Integer> minQueue = new ArrayDeque<>();
        for (int right = 0; right < nums.length; right++) {
            while (!maxQueue.isEmpty() && maxQueue.peekLast() < nums[right]) {
                maxQueue.pollLast();
            }

            while (!minQueue.isEmpty() && minQueue.peekLast() > nums[right]) {
                minQueue.pollLast();
            }
            maxQueue.add(nums[right]);
            minQueue.add(nums[right]);

            if (maxQueue.peekFirst() - minQueue.peekFirst() > limit) {
                if (maxQueue.peekFirst() == nums[left]) {
                    maxQueue.pollFirst();
                }

                if (minQueue.peekFirst() == nums[left]) {
                    minQueue.pollFirst();
                }
                left++;
            }
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    // 1482. Minimum Number of Days to Make m Bouquets
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length, left = 1, right = (int) 1e9;
        if ((long) m * k > n) return -1;

        while (left <= right) {
            int mid = (left + right) / 2, flow = 0, bouq = 0;
            for (int flower : bloomDay) {
                if (flower > mid) {
                    flow = 0;
                } else {
                    if (flow >= k) {
                        bouq++;
                        flow = 0;
                    } else {
                        flow++;
                    }
                }
            }
            if (bouq < m) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // 1552. Magnetic Force Between Two Balls
    public int maxDistance(int[] position, int m) {
        return 0;
    }

    // 1827. Minimum Operations to Make the Array Increasing
    public int minOperations(int[] nums) {
        int res = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] <= nums[i - 1]) {
                res += nums[i - 1] - nums[i] + 1;
                nums[i] = nums[i - 1] + 1;
            }
        }
        return res;
    }

    // 2037. Minimum Number of Moves to Seat Everyone
    public int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);
        int res = 0;
        for (int i = 0; i < seats.length; i++) {
            res += Math.abs(seats[i] - students[i]);
        }
        return res;
    }

    // 2285. Maximum Total Importance of Roads
    public long maximumImportance(int n, int[][] roads) {
        long[] degree = new long[n];
        long res = 0;
        for (int[] road : roads) {
            degree[road[0]]++;
            degree[road[1]]++;
        }

        Arrays.sort(degree);
        for (int i = n; i > 0; i--) {
            res += i * degree[i - 1];
        }
        return res;
    }

    // 2486. Append Characters to String to Make Subsequence
    public int appendCharacters(String s, String t) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                j++;
            }
            i++;
        }
        return t.length() - j;
    }

    // 3110. Score of a String
    public int scoreOfString(String s) {
        int res = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            res += Math.abs(s.charAt(i) - s.charAt(i + 1));
        }
        return res;
    }
}
