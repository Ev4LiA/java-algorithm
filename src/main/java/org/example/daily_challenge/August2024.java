package org.example.daily_challenge;

public class August2024 {
    // 2678. Number of Senior Citizens
    public int countSeniors(String[] details) {
        int res = 0;
        for (String detail : details) {
            int age = Integer.parseInt(detail.substring(11, 13));
            if (age > 60) res++;
        }
        return res;
    }
}
