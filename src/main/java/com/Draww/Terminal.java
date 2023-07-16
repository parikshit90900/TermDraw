package com.Draww;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.lang.System.out;

public class Terminal {
    final static String[] Commands = {"bash", "-c", "tput cols && tput lines"};
    private static int width = -1;
    private static int height = -1;
    private static int[] vals = {
            0, // Width
            0  // height
    };

    public static int[] getSize() {
        return new int[]{width, height};

    }

    public static int[] getTermSize() throws IOException {
        if (vals[0] <= 0 || vals[1] <= 0) {
            Terminal.CalculateSize();
        }
        return new int[]{width, height};
    }

    public static int[] getTermSizeReCalculated() throws IOException {
        Terminal.CalculateSize();
        return new int[]{width, height};
    }

    public static void CalculateSize() throws IOException {
        Process p;
        p = Runtime.getRuntime().exec(Commands, null);
        try {
            p.waitFor();
        } catch (InterruptedException e) {
            out.println("Error occured InterruptedException: \n\n");
            out.println(e);
        }
        out.println(p);

        InputStream stdout = p.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(stdout));


        try {
            width = Integer.parseInt(in.readLine());
            height = Integer.parseInt(in.readLine());
        } catch (Exception e) {
            out.println("ERROR OCCURED while parsing idk why, try using ubuntu linux\n\n");
            out.println(e);
            System.exit(1);

        }
        in.close();
    }
}
