package org.example.daily_challenge;

import org.example.linked_list.ListNode;

import java.util.*;

public class March2024 {
    // 41. First Missing Positive
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        boolean contains1 = false;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                contains1 = true;
            } else if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = 1;
            }
        }
        if (!contains1) return 1;

        for (int num : nums) {
            if (Math.abs(num) == n) {
                nums[0] = -Math.abs(nums[0]);
            } else {
                int position = Math.abs(num);
                nums[position] = -Math.abs(nums[position]);
            }
        }

        for (int i = 1; i < n; i++) {
            if (nums[i] > 0) {
                return i;
            }
        }
        return nums[0] >= 0  ? n : n + 1;
    }

    // 57. Insert Interval
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        if (n == 0) {
            return new int[][]{newInterval};
        }
        ArrayList<int[]> res = new ArrayList<>();

        int start = newInterval[0];
        int end = newInterval[1];
        boolean inserted = false;
        for (int[] inv : intervals) {
            int cstart = inv[0];
            int cend = inv[1];
            if (cend < start || inserted) {
                res.add(new int[]{cstart, cend});
                continue;
            }

            start = Math.min(cstart, start);

            if (end < cstart) {
                res.add(new int[]{start, end});
                res.add(new int[]{cstart, cend});
                inserted = true;
                continue;
            }

            if (end <= cend) {
                res.add(new int[]{start, cend});
                inserted = true;
            }
        }

        if (!inserted) {
            res.add(new int[]{start, end});
        }

        return res.toArray(new int[res.size()][]);
    }

    // 141. Linked List Cycle
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true;
        }
        return false;
    }

    // 143. Reorder List
    public void reorderList(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode prev = null;
        while (slow != null) {
            ListNode temp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = temp;
        }

        slow = prev;

        while (head != null && slow != null) {
            ListNode temp = head.next;
            ListNode temp2 = slow.next;
            head.next = slow;
            slow.next = temp;
            head = temp;
            slow = temp2;
        }

        if (head != null && head.next != null) head.next.next = null;
    }

    // 206. Reverse Linked List
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode prev = null, cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = prev;
            prev = cur;
            cur = temp;
        }
        return prev;
    }

    // 234. Palindrome Linked List
    public boolean isPalindrome(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }
        cur = head;
        while (cur != null && cur.val == stack.pop()) {
            cur = cur.next;
        }
        return cur == null;
    }

    // 238. Product of Array Except Self
    public int[] productExceptSelf(int[] nums) {
        int left = 1, right = 1;
        int[] res = new int[nums.length];
        Arrays.fill(res, 1);
        for (int i = 0; i < nums.length; i++) {
            res[i] = res[i] * left;
            left = left * nums[i];
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] = res[i] * right;
            right = right * nums[i];
        }

        return res;
    }

    // 287. Find the Duplicate Number
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast) {
            fast = nums[nums[fast]];
            slow = nums[slow];
        }
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    // 349. Intersection of Two Arrays
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersection = new HashSet<>();

        for (int num : nums1) {
            set.add(num);
        }

        for (int num : nums2) {
            if (set.contains(num)) {
                intersection.add(num);
            }
        }

        int[] res = new int[intersection.size()];
        int i = 0;
        for (Integer num : intersection) {
            res[i++] = num;
        }
        return res;
    }

    // 442. Find All Duplicates in an Array
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int num : nums) {
            int position = Math.abs(num) - 1;
            if (nums[position] < 0) {
                res.add(Math.abs(num));
            } else {
                nums[position] = -nums[position];
            }
        }
        return res;
    }

    // 452. Minimum Number of Arrows to Burst Balloons
    public int findMinArrowShots(int[][] points) {
        if (points.length == 1) return 1;
        int res = 1;
        Arrays.sort(points, compare);
        long end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            int curStart = points[i][0];
            int curEnd = points[i][1];
            if (curStart <= end) {
                if (end > curEnd) {
                    end = curEnd;
                }
            } else {
                end = curEnd;
                res++;
            }
        }
        return res;
    }

    Comparator<int[]> compare = new Comparator<int[]>() {
        @Override
        public int compare(int[] a, int[] b) {
            // Compare b to a for descending order
            if (a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        }
    };

    // 525. Contiguous Array
    public int findMaxLength(int[] nums) {
        int n = nums.length, res = 0, curSum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                curSum = curSum - 1;
            } else {
                curSum = curSum + 1;
            }

            if (map.containsKey(curSum)) {
                res = Math.max(res, i - map.get(curSum));
            } else {
                map.put(curSum, i);
            }
        }
        return res;
    }

    // 621. Task Scheduler
    public int leastInterval(char[] tasks, int n) {
        int max = Integer.MIN_VALUE, count = 0;
        HashMap<Character, Integer> map = new HashMap<>();

        for (char task : tasks) {
            map.put(task, map.getOrDefault(task, 0) + 1);
            max = Math.max(map.get(task), max);
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                count++;
            }
        }
        return Math.max((n + 1) * (max - 1) + count, tasks.length);
    }

    // 791. Custom Sort String
    public String customSortString(String order, String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        StringBuilder result = new StringBuilder();

        for (char a : s.toCharArray()) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }

        for (char a : order.toCharArray()) {
            if (!map.containsKey(a) || map.get(a) == 0) continue;
            int num = map.get(a);
            result.append(String.valueOf(a).repeat(num));
            map.remove(a);
        }

        for (char a : map.keySet()) {
            result.append(String.valueOf(a).repeat(map.get(a)));
        }
        return result.toString();
    }

    // 876. Middle of the Linked List
    public ListNode middleNode(ListNode head) {
        if (head.next == null) return head;
        ListNode fast = head, slow = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 930. Binary Subarrays With Sum
    public int numSubarraysWithSum(int[] nums, int goal) {
        int sum = 0, count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - goal)) {
                count += map.get(sum - goal);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    // 948. Bag of Tokens
    public static int bagOfTokensScore(int[] tokens, int power) {
        int n = tokens.length;
        if (n == 0) return 0;

        Arrays.sort(tokens);

        if (power < tokens[0]) return 0;
        int start = 0, end = n - 1, score = 0;
        while (start <= end) {
            if (power >= tokens[start]) {
                power -= tokens[start];
                score++;
                start++;
            } else if (score >= 0) {
                if (end == start) {
                    return score;
                } else {
                    power += tokens[end];
                    score--;
                    end--;
                }
            } else {
                return score;
            }
        }
        return score;
    }

    // 1171. Remove Zero Sum Consecutive Nodes from Linked List
    public ListNode removeZeroSumSublists(ListNode head) {
        ListNode start = new ListNode(0);
        start.next = head;
        ListNode cur = start.next;
        int prefixSum = 0;
        HashMap<Integer, ListNode> map = new HashMap<>();
        map.put(0, start);
        while (cur != null) {
            prefixSum += cur.val;
            if (map.containsKey(prefixSum)) {
                ListNode prev = map.get(prefixSum);
                ListNode toRemove = prev.next;
                int p = prefixSum + (toRemove == null ? 0 : toRemove.val);
                while (p != prefixSum) {
                    map.remove(p);
                    toRemove = toRemove.next;
                    p = p + (toRemove == null ? 0 : toRemove.val);
                }
                prev.next = cur.next;
            } else {
                map.put(prefixSum, cur);
            }
            cur = cur.next;
        }
        return start.next;
    }

    // 1669. Merge In Between Linked Lists
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode start = list1;
        for (int i = 0; i < a - 1; i++) {
            start = start.next;
        }
        ListNode end = start;
        for (int i = 0; i <= b - a + 1; i++) {
            end = end.next;
        }

        start.next = list2;
        while (start.next != null) {
            start = start.next;
        }
        start.next = end;
        return list1;
    }

    // 1750. Minimum Length of String After Deleting Similar Ends
    public int minimumLength(String s) {
        int n = s.length();
        if (n == 1) return 1;
        int start = 0, end = n - 1;

        while (start < end) {
            if (s.charAt(start) == s.charAt(end)) {
                char same = s.charAt(start);
                while (start <= end && s.charAt(start) == same) {
                    start++;
                }
                while (start <= end && s.charAt(end) == same) {
                    end--;
                }
            } else {
                break;
            }
        }
        return end - start + 1;
    }

    // 2485. Find the Pivot Integer
    public int pivotInteger(int n) {
        int sum1 = 0, sum2 = 0, i = 1, j = n;
        while (i <= j) {
            if (sum1 < sum2) {
                sum1 += i++;
            } else if (sum1 > sum2) {
                sum2 += j--;
            } else {
                if (i == j) {
                    return i;
                } else {
                    sum1 += i++;
                    sum2 += j--;
                }
            }
        }
        return -1;
    }

    // 2540. Minimum Common Value
    public int getCommon(int[] nums1, int[] nums2) {
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] > nums2[j]) {
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                return nums1[i];
            }
        }
        return -1;
    }

    // 3005. Count Elements With Maximum Frequency
    public int maxFrequencyElements(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int max = Integer.MIN_VALUE;
        int count = 0;

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) > max) {
                count = 1;
                max = map.get(num);
            } else if (map.get(num) == max) {
                count++;
            }
        }
        return count * max;
    }
}
