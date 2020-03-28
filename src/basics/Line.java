package basics;

import elementary.ColorHolder;
import elementary.Gobject;
import elementary.Pin;
import elementary.Segment;
import tranformations.TMove;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Line extends Gobject {

    public Segment segment;

    private List<Segment> complexSegments;

    public Line(double x1, double y1, double x2, double y2, Color color) {
        this.segment = new Segment(x1, y1, x2, y2, color);
    }

    public Line(double x1, double y1, double x2, double y2) {
        this.segment = new Segment(x1, y1, x2, y2, Color.black);
    }


    @Override
    public void decompose(int nParts) {
        this.complexForm = true;
        complexSegments = new ArrayList<>();

        double deltaX = (segment.p2.x - segment.p1.x) / nParts;
        double deltaY = (segment.p2.y - segment.p1.y) / nParts;
        Pin initialPin = new Pin(segment.p1.x, segment.p1.y);

        for (int i = 0; i < nParts; i++) {
            Pin atualPin = new Pin(initialPin.x + deltaX, initialPin.y + deltaY);
            complexSegments.add(new Segment(initialPin, atualPin, segment.color.color));
            initialPin = atualPin;
        }
    }

    @Override
    public void getsimpleBack() {
        complexSegments = null;
        complexForm = false;
    }

    @Override
    public int disassemble() {
        return 1;
    }

    @Override
    protected void fillPositinalPins(List<Pin> pins) {
    }

    @Override
    public void fillSegments(List<Segment> segments) {
        if (complexForm) {
            segments.addAll(complexSegments);
        } else {
            segments.add(segment);
        }
    }

    @Override
    public void fillSurfacePins(List<Pin> pins) {
    }

    @Override
    public void paint(Graphics g) {
        if (complexForm) {
            for (int i=0;i<complexSegments.size();i++){
                complexSegments.get(i).paint(g);
            }
//            for (Segment segment : complexSegments) {
//                segment.paint(g);
//            }
        } else {
            this.segment.paint(g);
        }
    }

    @Override
    public void fillColors(List<ColorHolder> colors) {
        colors.add(segment.color);
    }

    @Override
    public void init() {
        Pin finalPin= new Pin(segment.p2);
        segment.p2.x=segment.p1.x;
        segment.p2.y=segment.p1.y;

        new TMove(finalPin.x-segment.p1.x,finalPin.y-segment.p1.y).transform(Collections.singletonList(segment.p2),600);

    }
}
