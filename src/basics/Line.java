package basics;

import elementary.Gobject;
import elementary.Pin;
import elementary.Segment;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Line extends Gobject {

    private Segment segment;

    private List<Segment> complexSegments;

    public Line(double x1, double y1, double x2, double y2, Color color) {
        this.segment = new Segment(x1, y1, x2, y2, color);
    }
    public Line(double x1, double y1, double x2, double y2) {
        this.segment = new Segment(x1, y1, x2, y2,Color.black);
    }


    @Override
    public void mill(int nParts) {
        this.complexForm = true;
        complexSegments = new ArrayList<>();

        double deltaX = (segment.p2.x - segment.p1.x)/nParts;
        double deltaY = (segment.p2.y - segment.p1.y)/nParts;
        Pin initialPin= new Pin(segment.p1.x,segment.p1.y);

        for (int i = 0; i < nParts; i++) {
            Pin atualPin=new Pin(initialPin.x+deltaX,initialPin.y+deltaY);
            complexSegments.add(new Segment(initialPin,atualPin,segment.color));
            initialPin=atualPin;
        }
    }

    @Override
    public void getsimpleBack() {
        complexSegments=null;
        complexForm=false;
    }

    @Override
    public void disassemble() {

    }

    @Override
    protected void fillPositinalPins(List<Pin> pins) {
    }

    @Override
    protected void fillSegmentPins(List<Pin> pins) {
        if (complexForm) {
            for(Segment segment: complexSegments){
                pins.add(segment.p1);
                pins.add(segment.p2);
            }
        } else {
            pins.add(segment.p1);
            pins.add(segment.p2);
        }
    }

    @Override
    protected void fillSurfacePins(List<Pin> pins) {
    }

    @Override
    public void paint(Graphics g) {
        if(complexForm){
            for(Segment segment: complexSegments){
                segment.paint(g);
            }
        }else{
            this.segment.paint(g);
        }
    }
}
