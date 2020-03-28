package basics;

import elementary.Gobject;
import elementary.Pin;
import elementary.Segment;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Text extends Gobject {
    static Font font;
    static{
        GraphicsEnvironment ge =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
//        try {
//          // font= Font.createFont(Font.TRUETYPE_FONT, new File("C://Users//gusta//Desktop//fonts//cmunbi.ttf"));
//           font=new Font(Font.DIALOG,Font.BOLD,20);
//            ge.registerFont(font);
//        } catch (FontFormatException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        font=new Font(Font.DIALOG,Font.BOLD,20);
    }

    public Text(String s,double x, double y,int size) {
        double xBasis=x;
        for(Character character: s.toCharArray()){
            Gchar gchar=new Gchar(character,xBasis,y,size);
            chields.add(gchar);
            xBasis+=gchar.getBounds().width;
        }
    }
}
