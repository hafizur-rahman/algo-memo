package com.jdreamer.algo;

/**
 * Dynamic programming
 *
 * http://www.cc.gatech.edu/~ninamf/Algos11/lectures/lect0311.pdf
 */
public class LCS {

    public static void lcs(String s, String t) {
        int M = s.length();
        int N = t.length();

        // opt[i][j] = length of LCS of x[i..M] and y[j..N]
        int[][] opt = new int[M + 1][N + 1];

        // compute length of LCS and all subproblems via dynamic programming
        for (int i = M - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    opt[i][j] = opt[i + 1][j + 1] + 1;
                } else {
                    opt[i][j] = Math.max(opt[i + 1][j], opt[i][j + 1]);
                }
            }
        }

        // recover LCS itself and print it to standard output
        int i = 0, j = 0;
        while (i < M && j < N) {
            if (s.charAt(i) == t.charAt(j)) {
                System.out.print(s.charAt(i));
                i++;
                j++;
            } else if (opt[i + 1][j] >= opt[i][j + 1]){
                i++;
            } else {
                j++;
            }
        }
    }

    public static void main(String[] args) {
        String s = "ABAZDCB";
        String t = "BACBADC";

        lcs(s, t);
    }
}
