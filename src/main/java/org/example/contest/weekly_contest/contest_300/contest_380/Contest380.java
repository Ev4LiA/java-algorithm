package org.example.contest.weekly_contest.contest_300.contest_380;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Contest380 {
    // 3005. Count Elements With Maximum Frequency
    public int maxFrequencyElements(int[] nums) {
        int maxFreq = 0, count = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
            if (map.get(num) > maxFreq) {
                maxFreq = map.get(num);
                count = 1;
            } else if (map.get(num) == maxFreq) {
                count++;
            }
        }
        return maxFreq * count;
    }

    // 3006. Find Beautiful Indices in the Given Array I
    public List<Integer> beautifulIndices(String s, String a, String b, int k) {
        List<Integer> res = new ArrayList<>();
        int i = s.indexOf(a), j = s.indexOf(b);
        if (i == -1 || j == -1) {
            return res;
        }

        while (i != -1 && j != -1) {
            if (Math.abs(i - j) <= k) {
                res.add(i);
                i = s.indexOf(a, i + 1);
            } else {
                if (i > j) {
                    j = s.indexOf(b, j + 1);
                } else {
                    i = s.indexOf(a, i + 1);
                }
            }
        }

        return res;
    }

    // 3007. Maximum Number That Sum of the Prices Is Less Than or Equal to K
    public long findMaximumNumber(long k, int x) {
        long low = 1, high = 1_000_000_000_000_000L;
        while (low <= high) {
            long mid = low + (high - low) / 2;
            long res = calcPrice(mid, x);
            if (res <= k) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }

    private int countBits(long n) {
        int count = 0;
        while (n != 0) {
            count++;
            n = n << 1;
        }
        return count;
    }

    private long calcPrice(long n, int x) {
        int i = countBits(n);
        long price = 0L;
        n++;
        while (i != 0) {
            if (i % x == 0) {
                price += ((n / (long) Math.pow(2, i)) * ((long) Math.pow(2, i - 1)) + (long) Math.max(0L, (n % (long) Math.pow(2, i)) - (long) Math.pow(2, i - 1)));
            }
            i--;
        }
        return price;
    }

    // 3008. Find Beautiful Indices in the Given Array II
    public List<Integer> beautifulIndicesII(String s, String a, String b, int k) {
        List<Integer> res = new ArrayList<>();

        List<Integer> aIndices = findStringKMP(s, a);
        List<Integer> bIndices = findStringKMP(s, b);

        for (int i : aIndices) {
            int leftLimit = Math.max(i - k, 0);
            int rightLimit = Math.min(i + k, s.length() - 1);

            int lowerBoundIndex = lowerBound(bIndices, leftLimit);

            if (lowerBoundIndex < bIndices.size() && bIndices.get(lowerBoundIndex) <= rightLimit) {
                res.add(i);
            }
        }

        return res;
    }

    private int lowerBound(List<Integer> list, int target) {
        int left = 0, right = list.size() - 1, result = list.size();

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (list.get(mid) >= target) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    private List<Integer> findStringKMP(String s, String pattern) {
        List<Integer> indiceList = new ArrayList<>();

        int[] longestPrefixString = new int[pattern.length()];
        computeLps(pattern, longestPrefixString);
        int i = 0; // Index for text
        int j = 0; // Index for pattern

        while (i < s.length()) {
            if (pattern.charAt(j) == pattern.charAt(i)) {
                i++;
                j++;
            }

            if (j == pattern.length()) {
                indiceList.add(i - j); // Pattern found at index i-j+1 (If you have to return 1 Based indexing, that's why added + 1)
                j = longestPrefixString[j - 1];
            } else if (i < s.length() && pattern.charAt(j) != s.charAt(i)) {
                if (j != 0) {
                    j = longestPrefixString[j - 1];
                } else {
                    i++;
                }
            }
        }

        return indiceList;
    }

    private void computeLps(String pattern, int[] lps) {
        int m = pattern.length(), len = 0;
        lps[0] = 0;

        int i = 1;
        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
    }
}
