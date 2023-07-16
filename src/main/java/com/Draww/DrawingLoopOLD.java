package com.Draww;

public class DrawingLoopOLD {
    private final TerminalView view;
    private final int FPS;
    private final float frameTime;

    DrawingLoopOLD(TerminalView view) {
        this.view = view;
        this.FPS = 10;
        this.frameTime = 1000.f / (float) this.FPS;
    }

    DrawingLoopOLD(TerminalView view, int FPS) {
        this.view = view;
        this.FPS = FPS;
        this.frameTime = 1000.f / (float) FPS;
    }

    void startLoop(GameLoopFn Fn) {
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
            Fn.GameLoopFunction(view.getShapes());
            view.DrawFrame();
            t = System.currentTimeMillis();
        }
    }
}
