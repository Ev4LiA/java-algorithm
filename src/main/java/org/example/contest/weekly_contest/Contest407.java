package org.example.contest.weekly_contest;

public class Contest407 {
    // Number of Bit Changes to Make Two Integers Equal
    public int minChanges(int n, int k) {
        String nBinary = Integer.toBinaryString(n);
        String kBinary = Integer.toBinaryString(k);
        int maxLength = Math.max(nBinary.length(), kBinary.length());
        nBinary = String.format("%" + maxLength + "s", nBinary).replace(' ', '0');
        kBinary = String.format("%" + maxLength + "s", kBinary).replace(' ', '0');

        int changes = 0;
        for (int i = 0; i < maxLength; i++) {
            char nBit = nBinary.charAt(i);
            char kBit = kBinary.charAt(i);
            if (nBit == '1' && kBit == '0') {
                changes++;
            } else if (nBit == '0' && kBit == '1') {
                return -1;
            }
        }
        return changes;
    }

    // Vowels Game in a String
    public boolean doesAliceWin(String s) {
        int evenCount = 1;
        int oddCount = 0;

        int currentParity = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                currentParity = 1 - currentParity;
            }
            if (currentParity == 0) {
                evenCount++;
            } else {
                oddCount++;
            }
        }

        return oddCount > 0;
    }

    // Maximum Number of Operations to Move Ones to the End
    public int maxOperations(String s) {
        int res = 0, oneCount = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '1') {
                oneCount++;
                if (s.charAt(i + 1) == '0') {
                    res += oneCount;
                }
            }
        }
        return res;
    }

    // Minimum Operations to Make Array Equal to Target
    public long minimumOperations(int[] nums, int[] target) {
        int n = nums.length;
        int[] diff = new int[n];

        for (int i = 0; i < n; i++) {
            diff[i] = target[i] - nums[i];
        }

        int operations = 0;
        int current = 0;

        for (int i = 0; i < n; i++) {
            if (diff[i] != current) {
                operations += Math.abs(diff[i] - current);
                current = diff[i];
            }
        }

        return operations;
    }
}
