package org.example;

import org.example.arrays.ArraysAlgorithm;
import org.example.daily_challenge.March2024;
import org.example.string.StringAlgorithm;
import org.example.weekly_contest.Contest388;

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
        char[][] nums = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        int[][] flights = {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
        int[] heights = {0,1,0,0,0,0};
        String order = "cba";
        String s = "abcd";

        int[] arr = {1,2,3,-1,2};
        System.out.println(problem.customSortString(order, s));
    }
}
