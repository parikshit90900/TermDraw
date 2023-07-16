package com.Draww;


import com.Draww.Shapes.*;

import java.util.*;


public class Main {

    public static void main(String[] args) throws Exception{

        StateInitalizationFn initFn = () -> {
            GameState state = new GameState("TerminalGame", new ArrayList<>());

            Point p = new Point(35.0f, 34.0f);
            Point p1 = new Point(70.0f, 70.2f);
            Point p2 = new Point(120.6f, 35.4f);
            Triangle tri1 =  new Triangle(p, p1,p2);

            Circle ci = new Circle(new Point(500.f, 50.f), 30.f);

            state.shapes().add(tri1);

            state.shapes().add(ci);

//            Rectangle r = new Rectangle(p, p1);
//            state.shapes().add(r);


            return state;
        } ;
        ShapeGetter getShapeFn = (S) -> {
            // state = (GameState) S;
            // @SuppressWarnings("unchecked")
            @SuppressWarnings(value = "unchecked") var state = (GameState) S;
            return state.shapes();
        };

        GameLoopFunction Fn = (S) -> {

            @SuppressWarnings("unchecked") GameState state = (GameState) S;

            var shapes = state.shapes();
            int tri1Id = 0;
            Triangle tr =  (Triangle)shapes.get(tri1Id);
//             tr.translate(new Point(2.f, 2.f));
            tr.rotate((float)Math.toRadians(10.f));
            Circle ci =  (Circle)shapes.get(1);
            ci.translate(new Point(2.f, 1.f));

//            int rId = 0;
//            Rectangle r =  (Rectangle)shapes.get(rId);
////            r.rotate((float)Math.toRadians(10.f));
//            ShapeAbstractor.rotate(r, (float)Math.toRadians(10.f) );
        };




        DrawLoop loop = new DrawLoop(initFn, getShapeFn, 30);

        // if (true) return;
        loop.startLoop(Fn);



    }


    // void main_ager(String[] args) throws Exception{
    // 	int[] size = Terminal.getTermSize();
    // 	int	Width = size[0];
    // 	int	Height = size[1];
    // 	out.println("W: "+ Width + "\n" + "H: "+ Height);

    // 	TermPainter painter = new TermPainter(Width, Height);
    // 	// painter.Paint();
    // 	TerminalView view = new TerminalView(painter);

    // 	Point p = new Point(5.0f, 4.0f);
    // 	Point p1 = new Point(30.0f, 30.2f);
    // 	Point p2 = new Point(80.6f, 5.4f);

    // 	Triangle tri1 =  new Triangle(p, p1,p2);
    // 	// Circle ci = new Circle(new Point(100.f, 50.f), 30.f);
    // 	// Thread.sleep(1000);
    // 	int tri1Id = view.addShape(tri1);
    // 	// int cirId = view.addShape(ci);
    // 	DrawLoop loop = new DrawLoop(view);

    // 	GameLoopFn Fn = (s) -> {};

    // 	loop.startLoop(Fn);



    // }



}

 record GameState(String name, ArrayList<Shape> shapes) {}


