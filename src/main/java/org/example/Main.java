package org.example;

import org.example.arrays.ArraysAlgorithm;
import org.example.biweekly_contest.Contest126;
import org.example.daily_challenge.March2024;
import org.example.sliding_window.SlidingWindow;
import org.example.string.StringAlgorithm;
import org.example.weekly_contest.Contest388;
import org.example.weekly_contest.Contest389;
import org.example.weekly_contest.Contest390;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        March2024 problem = new March2024();
//        List<List<Integer>> nums = new ArrayList<>();
//        nums.add(Arrays.asList(2));
//        nums.add(Arrays.asList(3, 4));
//        nums.add(Arrays.asList(6, 5, 7));
//        nums.add(Arrays.asList(4, 1, 8, 3));
        int[][] flights = {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
        int[] heights = {0,1,0,0,0,0};
        String order = "cba";
        String s = "abcd";

        int[] nums = {1,2,3,4,5,6,7,8,9,10,11,12,23,20};
        int[] freq = {3,2,-3,1};
        String s1 = "aaaa";
        char c = 't';
        System.out.println(problem.firstMissingPositive( nums));

        // System out Array
//        System.out.print("Array elements: ");
//        long[] res = problem.mostFrequentIDs(nums, freq);
//        for (int i = 0; i < res.length; i++) {
//            System.out.print(res[i] + " ");
//        }
    }
}
