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

        while (!sb.isEmpty() && sb.charAt(0) == '0'){
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
}
