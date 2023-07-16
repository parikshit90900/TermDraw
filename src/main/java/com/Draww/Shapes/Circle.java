package com.Draww.Shapes;

import com.Draww.Point;
import com.Draww.Shapes.Shape;

import static java.lang.System.out;

public class Circle implements Shape {

    public Point center;
    public float radius;


    public Circle(Point p, float r) {
        this.center = p;
        radius = r;

    }

    public Point[] getBoundingRect() {
        return new Point[]{
                new Point(center.x - radius, center.y - radius),
                new Point(center.x + radius, center.y + radius)
        };
    }

    public void drawToBuf(int[][] buffer) {
        Point[] bouds = this.getBoundingRect();


        int[] mins = bouds[0].roundedInt();
        int[] maxs = bouds[1].roundedInt();

        int lenX = buffer[0].length;
        int lenY = buffer.length;

        // if ( mins[0] > lenX && mins[1] > lenY ) return;
        out.println(lenX);
        out.println(lenY);

        for (int y = mins[1]; y < maxs[1]; y++) {
            for (int x = mins[0]; x < maxs[0]; x++) {

                if (x < 0 || x >= lenX || y < 0 || y >= lenY) continue;

                if (insidCircle(new Point((float)x, (float)y))) {
                    buffer[y][x] += 1;
                }
            }
        }


    }

    public boolean insidCircle(Point p) {

        double dist = Math.pow((double) center.x - (double) p.x, 2.) +
                Math.pow((double) center.y - (double) p.y, 2.);

        // int dist = (((int)center.x - x) << 2) + ( ((int)center.y - y ) << 2);
        // out.println(dist);
        if ((double) (this.radius * this.radius) >= dist) return true;
        return false;

    }


    public Point[] points() {
        return new Point[]{center};
    }

    public void rotate(float angle) {
    }

    public void translate(Point p) {
        center.addInPlace(p);
    }

    @Override
    public boolean insideShape(Point p) {
        return this.insidCircle(p);
    }

    @Override
    public Point centerPoint() {
        return this.center;
    }

}
