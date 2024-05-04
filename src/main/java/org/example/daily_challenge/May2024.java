package org.example.daily_challenge;

import java.util.Arrays;

public class May2024 {
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

    // 881. Boats to Save People
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0, j = people.length - 1;
        for (; i <= j; j--) {
            if (people[i] + people[j] <= limit) i++;
        }
        return people.length - 1 - j;
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
}
