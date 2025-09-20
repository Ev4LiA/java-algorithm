public class March2025 {

    // 3169. Count Days Without Meetings
    public int countDays(int days, int[][] meetings) {
        Arrays.sort(meetings, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        int count = meetings[0][0] - 1;
        int currentDay = meetings[0][1];
        for (int j = 1; j < meetings.length; j++) {
            int[] nextMeeting = meetings[j];
            if (currentDay < nextMeeting[0]) {
                count += nextMeeting[0] - currentDay - 1;
                currentDay = nextMeeting[1];
            } else {
                currentDay = Math.max(currentDay, nextMeeting[1]);
            }
        }
        return count + days - currentDay;
    }
}
