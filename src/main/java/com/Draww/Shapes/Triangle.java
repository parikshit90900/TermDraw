package com.Draww.Shapes;

import com.Draww.Point;
import com.Draww.Shapes.Shape;

import static java.lang.System.out;

public class Triangle implements Shape {
    Point p1;
    Point p2;
    Point p3;

    public Triangle(Point p1, Point p2, Point p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public Point[] points() {
        return new Point[]{p1, p2, p3};

    }

    public void drawToBuf(int[][] buffer) {
        int lenY = buffer.length;
        int lenX = buffer[0].length;
        out.println(lenX);
        out.println(lenY);
        Point[] bouds = this.getBoundingRect();
        int[] mins = bouds[0].roundedInt();
        int[] maxs = bouds[1].roundedInt();

        if (mins[0] > lenX && mins[1] > lenY) return;


        for (int y = mins[1]; y < maxs[1]; y++) {
            for (int x = mins[0]; x < maxs[0]; x++) {

                if (x < 0 || x >= lenX || y < 0 || y >= lenY) continue;

                if (this.inside_tringle(new Point((float) x, (float) y))) {
                    buffer[y][x] += 1;
                }
            }
        }

    }

    public void rotate(float angle) {
        Point center = this.centeroid();
        this.p1.subInPlace(center);
        this.p2.subInPlace(center);
        this.p3.subInPlace(center);


        Point p1 = new Point(this.p1); //this.p1.clones();
        Point p3 = new Point(this.p3);// this.p3.clones();
        Point p2 = new Point(this.p2);// this.p2.clones();


        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);

        this.p1.x = p1.x * cos - p1.y * sin;
        this.p1.y = p1.x * sin + p1.y * cos;


        this.p2.x = p2.x * cos - p2.y * sin;
        this.p2.y = p2.x * sin + p2.y * cos;

        this.p3.x = p3.x * cos - p3.y * sin;
        this.p3.y = p3.x * sin + p3.y * cos;

        this.p1.addInPlace(center);
        this.p2.addInPlace(center);
        this.p3.addInPlace(center);
    }

    public Point centeroid() {
        return new Point(
                (p1.x + p2.x + p3.x) / 3.f,
                (p1.y + p2.y + p3.y) / 3.f

        );
    }

    public Point cennter() {
        return this.centeroid();
    }


    public void translate(Point p) {
        this.p1.addInPlace(p);
        this.p2.addInPlace(p);
        this.p3.addInPlace(p);
    }

    @Override
    public boolean insideShape(Point p) {
        return this.inside_tringle(p);
    }

    @Override
    public Point centerPoint() {
        return this.centeroid();
    }

    public static float sign(Point p1, Point p2, Point p3) {
        return (p1.x - p3.x) * (p2.y - p3.y) - (p2.x - p3.x) * (p1.y - p3.y);
    }

    public Point[] getBoundingRect() {
        float min_x = Math.min(this.p1.x, Math.min(this.p2.x, this.p3.x));
        float max_x = Math.max(this.p1.x, Math.max(this.p2.x, this.p3.x));

        float min_y = Math.min(this.p1.y, Math.min(this.p2.y, this.p3.y));
        float max_y = Math.max(this.p1.y, Math.max(this.p2.y, this.p3.y));

        return new Point[]{
                new Point(min_x, min_y),
                new Point(max_x, max_y)
        };
    }

    public boolean inside_tringle(Point p) {
//        float d1 = sign(p, this.p1, this.p2);
//        float d2 = sign(p, this.p2, this.p3);
//        float d3 = sign(p, this.p3, this.p1);
//
//        boolean has_neg = (d1 < 0.0f) || (d2 < 0.0f) || (d3 < 0.0f);
//        boolean has_pos = (d1 > 0.0f) || (d2 > 0.0f) || (d3 > 0.0f);
//        return !(has_neg && has_pos);
        return Triangle.inside_triangle(p, this.p1, this.p2, this.p3);
    }

    public static boolean inside_triangle(Point p, Point side1, Point side2, Point side3 ) {
        float d1 = sign(p, side1, side2);
        float d2 = sign(p, side2, side3);
        float d3 = sign(p, side3, side1);

        boolean has_neg = (d1 < 0.0f) || (d2 < 0.0f) || (d3 < 0.0f);
        boolean has_pos = (d1 > 0.0f) || (d2 > 0.0f) || (d3 > 0.0f);
        return !(has_neg && has_pos);
    }
}
