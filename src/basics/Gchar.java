package basics;

import elementary.ColorHolder;
import elementary.Gobject;
import elementary.Pin;
import elementary.Segment;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.PathIterator;
import java.util.ArrayList;
import java.util.List;

public class Gchar extends SegmentableGobject {
    int size;
    char aChar;
    Pin centerPin;
    Shape shape;


    void completeShape(){
        FontRenderContext frc = new FontRenderContext(null, true, true);
        GlyphVector glyphVector = Text.font.deriveFont((float) size).createGlyphVector(frc, aChar+"");
         this.shape = glyphVector.getGlyphOutline(0);
    }

    public int disassemble() {
        chields = new ArrayList<>();
        float[] coords = new float[6];

        PathIterator i = shape.getPathIterator(null);
        i.currentSegment(coords);
        Pin firstPin = new Pin(coords[0] + centerPin.x, coords[1] + centerPin.y);
        for (; !i.isDone(); i
                .next()) {
            i.currentSegment(coords);
            Pin nextPin = new Pin(coords[0] + centerPin.x, coords[1] + centerPin.y);
            chields.add(new Line(firstPin.x, firstPin.y, nextPin.x, nextPin.y, color.color));
            firstPin = nextPin;
        }
        complexForm=true;
        return chields.size();
    }

    public Gchar(char aChar, double x, double y, int size) {
        centerPin = new Pin(x, y);
        this.aChar = aChar;
        this.size=size;
        completeShape();
        color=new ColorHolder(Color.white);
        addPositionalPins(centerPin);
    }

    public Gchar(String s, double x, double y, int size) {
        centerPin = new Pin(x, y);
        aChar = s.charAt(0);
        completeShape();
        color=new ColorHolder(Color.white);
        addPositionalPins(centerPin);

    }



    public Rectangle getBounds(){
        return shape.getBounds();
    }

    @Override
    public void paint(Graphics g) {
        Rectangle rectangle=getBounds();

        if (complexForm) {
            for (Gobject gobject:chields){
                gobject.paint(g);
            }
        } else {
            g.setColor(color.color);
            g.setFont(Text.font.deriveFont((float) size));
            g.drawString(aChar + "", (int) centerPin.x-rectangle.x, (int) centerPin.y);
        }
    }
}
