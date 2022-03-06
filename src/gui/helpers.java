package gui;

import java.awt.*;
import java.io.*;
import java.util.*;

public class helpers {

    public static Font getFont(String path, float size) {
        try {
            //create the font to use. Specify the size!
            Font font= Font.createFont(Font.LAYOUT_LEFT_TO_RIGHT, new File(path)).deriveFont(size);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(font);
            return font;
        } catch (IOException e) {
            e.printStackTrace();
        } catch(FontFormatException e) {
            e.printStackTrace();
        }
        return null;
    }



}


