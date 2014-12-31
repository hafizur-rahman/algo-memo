package com.jdreamer.algo;

/**
 * http://leetcode.com/2011/05/determine-if-two-rectangles-overlap.html
 * http://gamemath.com/2011/09/detecting-whether-two-boxes-overlap/
 * http://en.wikibooks.org/wiki/Algorithm_Implementation/Geometry/Rectangle_difference
 */
public class RectangleOverlap {
    static class Rectangle {
        int x1, x2, y1, y2;

        Rectangle(int x1, int y1, int w, int h) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x1 + w;
            this.y2 = y1 + h;
        }

        public static boolean isOverlap(Rectangle r1, Rectangle r2) {
            return !(
                       r1.x2 <= r2.x1 /* r1 is completely to the left of r2 */
                    || r1.x1 >= r2.x2 /* r1 is completely to the right of r2 */
                    || r1.y2 <= r2.y1 /* r1 is completely above of r2 */
                    || r1.y1 >= r2.y2 /* r1 is completely below of r2 */
            );
        }
    }

    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(0, 10, 10, 5);
        Rectangle r2 = new Rectangle(0, 10, 10, 5);

        System.out.println(Rectangle.isOverlap(r1, r2));
    }
}
