package org.example;

import org.example.biweekly_contest.Contest127;
import org.example.weekly_contest.Contest391;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Contest391 problem = new Contest391();
//        List<List<Integer>> nums = new ArrayList<>();
//        nums.add(Arrays.asList(2));
//        nums.add(Arrays.asList(3, 4));
//        nums.add(Arrays.asList(6, 5, 7));
//        nums.add(Arrays.asList(4, 1, 8, 3));
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        int[] heights = {0, 1, 0, 0, 0, 0};
        String order = "cba";
        String s = "abcd";

//        int[] nums = {16, 1, 2, 20, 32, 40};
        int[] freq = {3, 2, -3, 1};
        String s1 = "aaaa";
        char c = 't';
        int[] nums = {0,1,1,1,0,1};
        int[][] arrays = {{3,10},{5,15},{10,2},{4,4}};
        System.out.println(problem.minimumDistance(arrays));
//        System.out.println(3 | 3);

        // System out Array
//        System.out.print("Array elements: ");
//        long[] res = problem.mostFrequentIDs(nums, freq);
//        for (int i = 0; i < res.length; i++) {
//            System.out.print(res[i] + " ");
//        }
    }
}
