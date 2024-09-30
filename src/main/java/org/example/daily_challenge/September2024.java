package org.example.daily_challenge;

import org.example.utilities.TwoWayNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class September2024 {
    // 641. Design Circular Deque
    class MyCircularDeque {
        TwoWayNode front;
        TwoWayNode rear;
        int size;
        int capacity;

        public MyCircularDeque(int k) {
            size = 0;
            capacity = k;
        }

        public boolean insertFront(int value) {
            if (isFull()) return false;

            if (front == null) {
                TwoWayNode head = new TwoWayNode(value, null, null);
                rear = head;
                front = head;
            } else {
                front.prev = new TwoWayNode(value, front, null);
                front = front.prev;
            }
            size++;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) return false;

            if (rear == null) {
                TwoWayNode node = new TwoWayNode(value, null, null);
                rear = node;
                front = node;
            } else {
                rear.next = new TwoWayNode(value, null, rear);
                rear = rear.next;
            }
            size++;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) return false;

            if (size == 1) {
                front = null;
                rear = null;
            } else {
                front = front.next;
            }
            size--;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) return false;

            if (size == 1) {
                front = null;
                rear = null;
            } else {
                rear = rear.prev;
            }
            size--;
            return true;
        }

        public int getFront() {
            if (isEmpty()) return -1;
            return front.val;
        }

        public int getRear() {
            if (isEmpty()) return -1;
            return rear.val;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public boolean isFull() {
            return size == capacity;
        }
    }

    // 731. My Calendar II
    class MyCalendarTwo {
        private List<int[]> bookings;
        private List<int[]> overlapBookings;

        public MyCalendarTwo() {
            bookings = new ArrayList<>();
            overlapBookings = new ArrayList<>();
        }

        public boolean book(int start, int end) {
            for (int[] booking : overlapBookings) {
                if (doesOverlap(start, end, booking[0], booking[1])) {
                    return false;
                }
            }

            for (int[] booking : bookings) {
                if (doesOverlap(booking[0], booking[1], start, end)) {
                    overlapBookings.add(getOverlapped(booking[0], booking[1], start, end));
                }
            }

            bookings.add(new int[] {start, end});
            return true;
        }

        private int[] getOverlapped(int start1, int end1, int start2, int end2) {
            return new int[]{Math.max(start1, start2), Math.min(end1, end2)};
        }

        private boolean doesOverlap(int start1, int end1, int start2, int end2) {
            return Math.max(start1, start2) < Math.min(end1, end2);
        }
    }

    // 1371. Find the Longest Substring Containing Vowels in Even Counts
    public int findTheLongestSubstring(String s) {
        int prefixXor = 0;
        int[] charMap = new int[26];
        charMap[0] = 1;
        charMap['e' - 'a'] = 2;
        charMap['i' - 'a'] = 4;
        charMap['o' - 'a'] = 8;
        charMap['u' - 'a'] = 16;
        int[] mp = new int[32];
        Arrays.fill(mp, -1);
        int longestSubstring = 0;
        for (int i = 0; i < s.length(); i++) {
            prefixXor ^= charMap[s.charAt(i) - 'a'];
            if (mp[prefixXor] == -1 && prefixXor != 0) mp[prefixXor] = i;
            longestSubstring = Math.max(longestSubstring, i - mp[prefixXor]);
        }
        return longestSubstring;
    }

    // 1381. Design a Stack With Increment Operation
    class CustomStack {
        private int[] stackArray;
        private int topIndex;

        public CustomStack(int maxSize) {
            stackArray = new int[maxSize];
            topIndex = -1;
        }

        public void push(int x) {
            if (topIndex < stackArray.length - 1) {
                stackArray[++topIndex] = x;
            }
        }

        public int pop() {
            if (topIndex >= 0) {
                return stackArray[--topIndex];
            }
            return -1;
        }

        public void increment(int k, int val) {
            int limit = Math.min(k, topIndex + 1);
            for (int i = 0; i < limit; i++) {
                stackArray[i] += val;
            }
        }
    }

    // 1684. Count the Number of Consistent Strings
    public int countConsistentStrings(String allowed, String[] words) {
        int[] alphabets = new int[26];
        int count = 0;
        for (char c : allowed.toCharArray()) {
            alphabets[c - 'a']++;
        }
        for (String word : words) {
            boolean flag = true;
            for (char c : word.toCharArray()) {
                if (alphabets[c - 'a'] == 0) {
                    flag = false;
                    break;
                }
            }
            count += flag ? 1 : 0;
        }
        return count;
    }
}
