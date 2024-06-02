package org.example.contest.weekly_contest;

import java.util.*;

public class Contest400 {
    // Maximum Number of Chairs in a Waiting Room
    public int minimumChairs(String s) {
        int res = 0, chair = 0;
        for (char c : s.toCharArray()) {
            if (c == 'E') {
                chair++;
            } else {
                chair--;
            }
            res = Math.max(res, chair);
        }
        return res;
    }

    // Count Days Without Meetings
    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] != b[0]) {
                    return Integer.compare(a[0], b[0]);
                } else {
                    return Integer.compare(a[1], b[1]);
                }
            }
        });
        int res = 0;
        int minStart = meetings[0][0], maxEnd = meetings[0][1];
        for (int[] meeting : meetings) {
            int start = meeting[0], end = meeting[1];
            if (start > maxEnd) {
                res += start - maxEnd - 1;
                maxEnd = end;
            } else {
                if (end > maxEnd) {
                    maxEnd = end;
                }
            }
        }

        res += minStart - 1;
        res += days - maxEnd;
        return res;
    }

    // Lexicographically Minimum String After Removing Stars
    public String clearStars(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            } else {
                return b[1] - a[1];
            }
        });

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) != '*') {
                queue.add(new int[]{arr[i] - 'a', i});
            } else {
                arr[i] = '_';
                int[] remove = queue.poll();
                arr[remove[1]] = '_';
            }
        }
        StringBuilder sb = new StringBuilder();
        for (char c : arr) {
            if (c != '_') {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    // Find Subarray With Bitwise AND Closest to K
    private static int[] bitArr;
    public int minimumDifference(int[] nums, int k) {
        bitArr = new int[32];

        int minAbs = Integer.MAX_VALUE, n = nums.length;
        int index = 0;

        for (int i = 0; i < n; i++) {
            plus(nums[i]);
            int curTotal = eval();
            minAbs = Math.min(minAbs, Math.abs(curTotal - k));
            while (index < i && curTotal < k) {
                minus(nums[index]);
                ++index;
                curTotal = eval();
                minAbs = Math.min(minAbs, Math.abs(curTotal - k));
            }
        }

        return minAbs;
    }

    private void plus(int x) {
        for (int i = 0; i < 32; ++i) {
            if (((x >> i) & 1) == 0) {
                bitArr[i] += 1;
            }
        }
    }

    private void minus(int x) {
        for (int i = 0; i < 32; ++i) {
            if (((x >> i) & 1) == 0) {
                bitArr[i] -= 1;
            }
        }
    }

    private int eval() {
        int res = 0;
        for (int i = 0; i < 32; ++i) {
            if (bitArr[i] == 0) {
                res ^= (1 << i);
            }
        }
        return res;
    }
}
