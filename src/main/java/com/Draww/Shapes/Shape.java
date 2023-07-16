package com.Draww.Shapes;

import com.Draww.Point;

import static java.lang.System.out;

public interface Shape {
    public void drawToBuf(int[][] buffer);

    public Point[] points();        // necessary one

    public void rotate(float angle);
    public void translate(Point p);
    public boolean insideShape(Point p) ;   // necessary one

    public Point centerPoint();    // necessary one

}

