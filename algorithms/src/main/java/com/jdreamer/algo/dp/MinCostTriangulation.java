package com.jdreamer.algo.dp;

/**
 * http://www.geeksforgeeks.org/minimum-cost-polygon-triangulation/
 */
public class MinCostTriangulation {
    public static class Point {
        int x, y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static double dist(Point p1, Point p2) {
        final int xDiff = p1.x - p2.x;
        final int yDiff = p1.y - p2.y;

        return Math.sqrt(xDiff * xDiff + yDiff * yDiff);
    }

    public static double cost(Point p1, Point p2, Point p3) {
        return dist(p1, p2) + dist(p2, p3) + dist(p3, p1);
    }

    private static double cost(Point[] points) {
        double[][] cost = new double[points.length][points.length];

        int n = points.length;
        for (int l = 0; l < n; l++) {
            for (int i = 0, j = l; j < n; i++, j++) {
                if (j >= i + 2) {
                    cost[i][j] = Integer.MAX_VALUE;

                    for (int k = i + 1; k < j; k++) {
                        double val = cost[i][k] + cost[k][j] + cost(points[i], points[j], points[k]);
                        cost[i][j] = Math.min(val, cost[i][j]);
                    }
                }
            }
        }

        return cost[0][n-1];
    }

    public static void main(String[] args) {
        Point[] points = {new Point(0, 0), new Point(1, 0),
                new Point(2, 1), new Point(1, 2), new Point(0, 2)};

        System.out.println(cost(points));
    }
}
