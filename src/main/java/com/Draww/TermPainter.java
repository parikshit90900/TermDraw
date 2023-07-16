package com.Draww;

import com.Draww.Shapes.Shape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import static java.lang.System.out;

public class TermPainter {

    static final char[] brightness = {'`', '"', '~', '^', '=', '+', '*', '%', '#'};

    public final int width;
    public final int height;

    char[][] termDisplay;
    ArrayList<Shape> shapes;
    Set freeMap;


    public TermPainter(int width, int height) {
        this.shapes = new ArrayList<>();
        this.width = width;
        this.height = height;
        this.termDisplay = new char[height][width];
        for (char[] row : termDisplay) {
            Arrays.fill(row, ' ');
        }

    }

    public TermPainter() {
        int[] size = new int[]{80, 25};
        try {


            size = Terminal.getTermSize();
        } catch (Exception e) {

        }

        this.width = size[0];
        this.height = size[1];

        this.shapes = new ArrayList<>();
        this.termDisplay = new char[height][width];
        for (char[] row : termDisplay) {
            Arrays.fill(row, ' ');
        }

    }

    public void _Paint_old() {
        for (int y = 0; y < this.termDisplay.length; y++) {
            for (int x = 0; x < this.termDisplay[y].length; x++) {
                out.print(
                        String.format("\033[%d;%dH%s", y, x, this.termDisplay[y][x])
                );
            }
        }
        out.print("\n");
        out.flush();
    }


    public void Paint() {
        for (int y = 0; y < this.termDisplay.length; y++) {
            out.println("\033[" + y + ";0H");
            out.print(
                    String.valueOf(termDisplay[y])
            );
        }
        out.print("\n");
        out.flush();
    }

    // public void calculateNewFrame() {
    // 	for (Shape shape : this.shapes) {
    // 		shape.drawToBuf(this.termDisplay);
    // 	}
    // }

    public int addShape(Shape shape) {
        shapes.add(shape);
        return shapes.size() - 1;
    }

    public boolean removeShape(int i) {
        if (i >= shapes.size()) return false;
        shapes.remove(i);

        return true;
    }

    public void putTermDisplay(char[][] newDisplay) {
        this.termDisplay = newDisplay;

    }

}
