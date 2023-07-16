package com.Draww;

public class Point {
    public float x;
    public float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Point() {
        this.x = 0.0f;
        this.y = 0.0f;
    }

    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public float distance() {
        return this.distance(new Point());
    }

    public float distance(Point p) {
        return (float) Math.sqrt(
                Math.pow(p.x - this.x, 2) +
                        Math.pow(p.y - this.y, 2)
        );
    }


    public Point midPoint(Point that ) {
        return new Point(
                Math.abs(this.x - that.x) / 2.f,
                Math.abs(this.y - that.y) / 2.f
        );
    }

    public Point add(Point that) {
        return new Point(this.x + that.x, this.y + that.y);
    }

    public Point sub(Point that) {
        return new Point(this.x - that.x, this.y - that.y);
    }

    public void addInPlace(Point that) {
        this.x += that.x;
        this.y += that.y;
    }

    public void subInPlace(Point that) {
        this.x -= that.x;
        this.y -= that.y;
    }


    @Override
    public String toString() {
        return "Point: { x: " + this.x + " y: " + this.y + " }";
    }

    public int[] toInt() {
        return new int[]{
                (int) this.x,
                (int) this.y
        };
    }

    public int[] roundedInt() {
        return new int[]{
                Math.round(this.x),
                Math.round(this.y)
        };
    }

    public Point clones() {
        Point p = new Point(this.x, this.y);
        return p;
    }


}
