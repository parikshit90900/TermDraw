package com.Draww.Shapes;

import com.Draww.Point;
import com.Draww.Shapes.Shape;
import com.Draww.TerminalView;

public class Line implements Shape {

    Point start;
    Point end;
    public int thickness;

    public Line(Point start, Point end) {
        this(start, end, (TerminalView.scaleX + TerminalView.scaleY) / 2);
    }

    public Line(Point start, Point end, int thickness) {
        this.start = start;
        this.end = end;
        this.thickness = thickness;
    }


    public void drawToBuf(int[][] buffer) {

    }

    public Point[] points() {
        return new Point[]{this.start, this.end};
    }

    public void rotate(float angle) {
    }

    public void translate(Point p) {
        this.start.addInPlace(p);
        this.end.addInPlace(p);

    }

    @Override
    public boolean insideShape(Point p) {
        //TODO: umimplemented unsure
        return false;
    }

    @Override
    public Point centerPoint() {
        return new Point( (start.x+end.x)/2, (start.y+end.y)/2 );
    }


}
