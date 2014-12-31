package com.jdreamer.algo;

/**
 * https://www.cs.duke.edu/courses/fall08/cps230/Lectures/L-04.pdf
 * http://www.cs.jhu.edu/~langmea/resources/lecture_notes/dp_and_edit_dist.pdf
 * http://www.programcreek.com/2013/12/edit-distance-in-java/
 */
public class EditDistance {
    private static int editDistance(String s, String t) {
        int[][] cost = new int[s.length() + 1][t.length() + 1];

        for (int i=0; i<s.length(); i++) {
            cost[i][0] = i;
        }
        for (int j = 0; j < t.length(); j++) {
            cost[0][j] = j;
        }

        for (int j = 1; j <= t.length(); j++) {
            final char y = t.charAt(j - 1);

            for (int i=1; i <= s.length(); i++) {
                final char x = s.charAt(i - 1);

                if (x == y) {
                    cost[i][j] = cost[i - 1][j - 1];
                } else {
                    int insertion = 1 + cost[i][j - 1];
                    int deletion = 1 + cost[i - 1][j];
                    int replace = 1 + cost[i - 1][j - 1];

                    cost[i][j] = Math.min(Math.min(insertion, deletion), replace);
                }
            }
        }

        return cost[s.length()][t.length()];
    }

    public static void main(String[] args) {
        String s = "GCGTATGAGGCTAACGC";
        String t = "GCTATGCGGCTATACGC";

        System.out.println(editDistance(s, t));
    }
}
