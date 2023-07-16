package com.Draww;

import com.Draww.Shapes.Shape;
import com.Draww.Shapes.ShapeAbstractor;

import java.util.ArrayList;
import java.util.Arrays;

public class TerminalView {

    TermPainter painter;
    // to scale up dimensions of canvas to get better quality
    // always power of two so scaling down is easy
    // scaleY is always twice scaleX, terminal cursor is rectangle with length vertically ~ 2x length horizontally
    public static int scaleX = 2;
    public static int scaleY = 4;
    // down scaling factor
    // alwasy a whole number, scale factor is pwoer of two so DownFactor is. i << scaleDownFactor
    int maxBright = scaleX * scaleY;
    byte characterThreashhol = -1;

    int scaleDownFacX = 1;
    int scaleDownFacY = 2;
    int[][] window;
    private ArrayList<Shape> shapes;
    int[][] termDisplay;
    private boolean clean = true;


    public TerminalView(TermPainter painter) {
        this.painter = painter;
        this.window = new int[painter.height * this.scaleY][painter.width * this.scaleX];
        for (int[] row : window) {
            Arrays.fill(row, 0);
        }
        this.termDisplay = new int[painter.height][painter.width];
        for (int[] row : window) {
            Arrays.fill(row, 0);
        }
        this.shapes = new ArrayList();

    }

    public TerminalView() {
        this(new TermPainter());
        // TermPainter painter = new TermPainter();
        // this(painter);
    }

    public int addShape(Shape shape) {
        shapes.add(shape);
        return shapes.size() - 1;
    }

    public ArrayList<Shape> getShapes() {
        return this.shapes;
    }

    // public boolean removeShape(int i) {
    // 	if (i >= shapes.size()) return false;
    // 	shapes.remove(i);

    // 	return true;
    // }

    public void calculateNewFrame() {
        for (Shape shape : this.shapes) {
            ShapeAbstractor.drawToBuf(this.window, shape);
//            shape.drawToBuf(this.window);
        }
    }

    void cleanWindow() {

        if (clean) return;

        for (int[] row : window) {
            Arrays.fill(row, 0);
        }
        this.termDisplay = new int[painter.height][painter.width];
        for (int[] row : window) {
            Arrays.fill(row, 0);
        }
    }

    public void putInTermBuffer() {
        int lumLength = this.painter.brightness.length;
        int min = 1 << 30;
        int max = 0;
        this.clean = false;
        for (int i = 0; i < window.length; i++) {
            for (int j = 0; j < window[i].length; j++) {
                int cur = this.termDisplay[i >> this.scaleDownFacY][j >> this.scaleDownFacX];

                if (cur < this.maxBright && cur >= 0) {
                    this.termDisplay[i >> this.scaleDownFacY][j >> this.scaleDownFacX] += this.window[i][j];

                    if (cur < min) {
                        min = cur;
                    } else if (cur > max) {
                        max = cur;
                    }
                } else if (cur < 0) {
                    this.termDisplay[i >> this.scaleDownFacY][j >> this.scaleDownFacX] = Math.max(cur * -1 * 1000, this.window[i][j]);
                }
            }
        }

        int mid = (max - min);
        float r = (float) mid / lumLength;
        // out.println(max);
        // out.println(min);
        // for (int[] ar : this.termDisplay) {
        // 	out.println(Arrays.toString(ar));
        // }
        // if (true) return;
        for (int i = 0; i < termDisplay.length; i++) {
            for (int j = 0; j < termDisplay[i].length; j++) {
                if (termDisplay[i][j] > 0) {

                    // HERE
                    int diff = (int) Math.floor((
                            (double) termDisplay[i][j]
                                    - (double) min) /
                            (double) r);

                    this.painter.termDisplay[i][j] = this.painter.brightness[
                            Math.min(diff, lumLength - 1)
                            ];
                    // }
                    // else if(termDisplay[i][j] == 1000) {
                    // 	this.painter.termDisplay[i][j] = (char)(termDisplay[i][j] - 1000);

                } else {
                    this.painter.termDisplay[i][j] = ' ';
                }

            }
        }

    }

    public void DrawFrame() {
        this.cleanWindow();
        this.calculateNewFrame();
        this.putInTermBuffer();
        this.painter.Paint();
    }


}
