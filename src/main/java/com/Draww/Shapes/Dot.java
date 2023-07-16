package com.Draww.Shapes;

import com.Draww.Point;
import com.Draww.Shapes.Shape;
import com.Draww.TerminalView;

public class Dot implements Shape {
    Point pt;

    public Dot(Point p) {
        this.pt = p;
    }

    public void drawToBuf(int[][] buffer) {
        int[] pts = this.pt.toInt();
        int scaleXMid = TerminalView.scaleX / 2;
        int scaleYMid = TerminalView.scaleY / 2;

        int lenX = buffer[0].length;
        int lenY = buffer.length;

        int[] ar = this.pt.toInt();
        var x_ = ar[0];
        var y_ = ar[1];

        for (int y = y_ - scaleYMid; y < y_ + scaleYMid; y++) {

            for (int x = x_ - scaleXMid; y < x_ + scaleXMid; x++) {
                if (x < 0 || x >= lenX || y < 0 || y >= lenY) continue;


                buffer[pts[1]][pts[0]] += 1;

            }

        }
    }

    public Point[] points() {
        return new Point[]{this.pt};
    }

    public void rotate(float angle) {
    }

    public void translate(Point p) {
        pt.addInPlace(p);
    }

    @Override
    public boolean insideShape(Point p) {
        // TODO: Unimplemented, unsure
        return false;
    }
    @Override
    public Point centerPoint() {
        // TODO: Unimplemented, unsure
        return this.pt;
    }
}
