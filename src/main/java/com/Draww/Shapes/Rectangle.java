package com.Draww.Shapes;

import com.Draww.Point;

import java.util.Arrays;

public class Rectangle implements Shape{

    Point p1;
    Point p2;
    Point p3;
    Point p4;

    public Rectangle(Point p1,Point p3) {
        this.p1 = p1;
        this.p3 = p3;
        this.p2 = new Point(p3.x, p1.y);
        this.p4 = new Point(p1.x, p3.y);
    }


    @Override
    public void drawToBuf(int[][] buffer) {
        int[] mins = { 1 << 30, 1 << 30 };
        int[]  maxs = { -1 * (1 << 30), -1 * (1 << 30)  };

        for (Point pt : this.points()){
            int[] p = pt.toInt();
            mins[0] =  Math.min( p[0], mins[0] );
            mins[1] =  Math.min( p[1], mins[1] );

            maxs[0] =  Math.max( p[0], maxs[0] );
            maxs[1] =  Math.max( p[1], maxs[1] );
        }

        System.out.println(Arrays.toString(mins));

        System.out.println(Arrays.toString(maxs));

        int lenX = buffer[0].length;
        int lenY = buffer.length;
        for (int y = mins[1]; y < maxs[1]; y++) {
            for (int x = mins[0]; x < maxs[0]; x++) {

                if (x < 0 || x >= lenX || y < 0 || y >= lenY) continue;

                if (insidRectagle(new Point((float)x, (float)y))) {
                    buffer[y][x] += 1;
                }
            }
        }
    }

    public boolean insidRectagle(Point givernPoint) {

//        Point givernPoint = new Point((float)x, (float)y);

        boolean first = Triangle.inside_triangle(givernPoint, p1, p2, p3);
        if (first) return true;

        return first || Triangle.inside_triangle(givernPoint, p1, p4, p3);
    }

    @Override
    public Point[] points() {
        return new Point[]{this.p1, this.p2, this.p3, this.p4};
    }

    @Override
    public void rotate(float angle) {
        Point center = this.center();
        this.p1.subInPlace(center);
        this.p2.subInPlace(center);
        this.p3.subInPlace(center);
        this.p4.subInPlace(center);

        Point p1 = new Point(this.p1); //this.p1.clones();
        Point p3 = new Point(this.p3);// this.p3.clones();
        Point p4 = new Point(this.p4);// this.p4.clones();
        Point p2 = new Point(this.p2);// this.p2.clones();


        float cos = (float) Math.cos(angle);
        float sin = (float) Math.sin(angle);

        this.p1.x = p1.x * cos - p1.y * sin;
        this.p1.y = p1.x * sin + p1.y * cos;


        this.p2.x = p2.x * cos - p2.y * sin;
        this.p2.y = p2.x * sin + p2.y * cos;

        this.p3.x = p3.x * cos - p3.y * sin;
        this.p3.y = p3.x * sin + p3.y * cos;

        this.p4.x = p4.x * cos - p4.y * sin;
        this.p4.y = p4.x * sin + p4.y * cos;


        this.p1.addInPlace(center);
        this.p2.addInPlace(center);
        this.p3.addInPlace(center);
        this.p4.addInPlace(center);




    }

    public Point center() {
        return new Point(
                (p1.x+p2.x+p3.x+p4.x)/4.f,
                (p1.y+p2.y+p3.y+p4.y)/4.f
        );
    }

    @Override
    public void translate(Point p) {
        this.p1.addInPlace(p);
        this.p3.addInPlace(p);
        this.p2.addInPlace(p);
        this.p4.addInPlace(p);

        
    }

    @Override
    public boolean insideShape(Point p) {
        return this.insidRectagle(p);
    }

    @Override
    public Point centerPoint() {
        return this.center();
    }


}
