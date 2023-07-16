package com.Draww;

import com.Draww.Shapes.Shape;

import java.util.ArrayList;


@FunctionalInterface
interface GameLoopFn {
    void GameLoopFunction(ArrayList<Shape> shapes);
}


@FunctionalInterface
interface GameLoopFunction<T> {
    void GameLoopFunction(T userDefinedObject);
}


@FunctionalInterface
interface StateInitalizationFn<T> {
    T getUserDefinedObject();
}

@FunctionalInterface
interface ShapeGetter<T> {
    ArrayList<Shape> shapesGetterFn(T userdefinedIntialValue);
}


public class DrawLoop<T> {
    private final TerminalView view;
    private final int FPS;
    private final float frameTime;
    private final T userDefinedObject;
    private final ShapeGetter getShapeFn;

    public DrawLoop(StateInitalizationFn initFn, ShapeGetter getShapeFn, int FPS) {

        this.view = new TerminalView();
        this.userDefinedObject = (T) initFn.getUserDefinedObject();
        this.FPS = FPS;
        this.frameTime = 1000 / FPS;
        this.getShapeFn = getShapeFn;

        ArrayList<Shape> shapes = this.getShapeFn.shapesGetterFn(this.userDefinedObject);

        // out.println(	shapes);

        for (int i = 0; i < shapes.size(); i++) {
            this.view.addShape(shapes.get(i));
        }


    }

    DrawLoop(StateInitalizationFn initFn, ShapeGetter getShapeFn) {
        this(initFn, getShapeFn, 10);
    }


    void startLoop(GameLoopFunction<T> Fn) {
        long t = System.currentTimeMillis();
        view.DrawFrame();
        while (true) {
            long cur = System.currentTimeMillis();
            ;
            if (cur - t < frameTime) {
                try {
                    Thread.sleep(2);
                } catch (Exception e) {
                } finally {
                    continue;
                }
            }
            try {
                Thread.sleep(100);
            } catch (Exception e) {
            }
            Fn.GameLoopFunction(this.userDefinedObject);
            view.DrawFrame();
            t = System.currentTimeMillis();
        }
    }


}
