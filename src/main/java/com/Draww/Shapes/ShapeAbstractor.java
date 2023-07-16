package com.Draww.Shapes;

import com.Draww.Point;

import java.util.Arrays;
import java.util.stream.Collectors;

import static java.lang.System.out;

public class ShapeAbstractor {
    public static Point[] getBoundingReact(Shape s) {
        Point mins = new Point(Float.MAX_VALUE, Float.MAX_VALUE);
        Point maxs = new Point(Float.MIN_VALUE, Float.MIN_VALUE);

        for (Point p : s.points()) {
//            int[] p = pt.toInt();
            mins.x = Math.min(p.x, mins.x);
            mins.y = Math.min(p.y, mins.y);

            maxs.x = Math.max(p.x, maxs.x);
            maxs.y = Math.max(p.y, maxs.y);
        }
        return new Point[]{mins, maxs};
    }

    public static void drawToBuf(int[][] buffer, Shape s) {
        int lenY = buffer.length;
        int lenX = buffer[0].length;
        out.println(lenX);
        out.println(lenY);
        Point[] bouds = ShapeAbstractor.getBoundingReact(s);
        int[] mins = bouds[0].roundedInt();
        int[] maxs = bouds[1].roundedInt();

        if (mins[0] > lenX && mins[1] > lenY) return;


        for (int y = mins[1]; y < maxs[1]; y++) {
            for (int x = mins[0]; x < maxs[0]; x++) {

                if (x < 0 || x >= lenX || y < 0 || y >= lenY) continue;

                if (s.insideShape(new Point((float) x, (float) y))) {
                    buffer[y][x] += 1;
                }
            }
        }

    }
    public static void rotate(Shape s, float angle ) {
        Point[] pts = s.points();
        if (pts == null) return;
        if (pts.length <= 1) return;

        Point center = s.centerPoint();
        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);
        Arrays.stream(pts).forEach(p -> p.subInPlace(center));

        Point[] pts_copy = new Point[pts.length];
        for (int i = 0; i < pts.length; i++) {
            pts_copy[i] = new Point(pts[i]);
        }


//        this.p1.x = p1.x * cos - p1.y * sin;
//        this.p1.y = p1.x * sin + p1.y * cos;

        for (int i = 0; i < pts.length; i++) {
            Point p = pts_copy[i];
            pts[i].x = p.x * cos - p.y * sin;
            pts[i].y = p.x * sin + p.y * cos;
        }

        Arrays.stream(pts).forEach(p -> p.addInPlace(center));


    }

    public static void translate(Shape s, Point value) {
        Arrays.stream(s.points()).forEach(p -> {
            p.addInPlace(value);
        });
    }

}
