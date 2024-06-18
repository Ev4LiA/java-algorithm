package org.example.daily_challenge;

import org.example.utilities.TrieNode;

import java.util.*;

public class June2024 {
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
    /* METHOD 1: SORTING (O(nLogn) */
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
