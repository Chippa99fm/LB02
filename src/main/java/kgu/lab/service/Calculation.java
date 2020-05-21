package kgu.lab.service;

import org.springframework.stereotype.Service;

@Service
public class Calculation {

    /**
     * distance to lightning
     * @param interval
     * @return distance
     */
    public String distanceToLightning(double interval) {
        return String.valueOf(interval * 330.0);
    }

    /**
     * Search winner
     * @param names array of member names
     * @param times array of member results
     * @return best guy
     */
    public String winnerSearch(String[] names, int[] times) {
        int minTime = Integer.MAX_VALUE, minNum = 0;
        for (int i = 1; i < times.length; i++) {
            if (times[i] < minTime) {
                minTime = times[i];
                minNum = i;
            }
        }
        String nm = names[minNum];
        return (nm + " - " + minTime);
    }

    /**
     * recursively flips a string
     * @param line
     * @param flippedLine
     * @return flip line
     */
    public String flipLine(String line, String flippedLine) {
        if (!line.isEmpty()) {
            flippedLine += line.charAt(line.length() - 1);
            line = line.substring(0, line.length() - 1);
            flippedLine = flipLine(line, flippedLine);
        }
        return flippedLine;
    }

    /**
     * Checking that the number is a palindrome
     * @param num number
     * @return true if palindrome, else false
     */
    public boolean isPalindrome(String num) {
        return num.replaceAll("\\D", "")
                .equalsIgnoreCase(new StringBuilder(num.replaceAll("\\D", ""))
                        .reverse().toString());
    }

    /**
     * Method calculate the count horses, minutes and seconds
     * @param countDays - count days
     * @return mass in witch 0el - hours, 1el - minutes, 2el - seconds
     */
    public int[] hoursMinutesSecondsInDays(int countDays) {
        int[] time = new int[3];
        time[0] = countDays * 24;
        time[1] = time[0] * 60;
        time[2] = time[1] * 60;
        return time;
    }
}
