package basics;

import elementary.Gobject;
import elementary.Pin;
import elementary.Segment;

import java.awt.*;
import java.util.List;

public class ComplexableGobject extends Gobject {

    public Color color;
    List<Segment> complexFormSegments;
    List<Pin>simpleFormPositionalPins;

    @Override
    public Color getColor() {
        if(!complexForm){
            return color;
        }else {
            return null;
        }
    }

    @Override
    protected void fillPositinalPins(List<Pin> pins) {
        if (!complexForm) {
           pins.addAll(simpleFormPositionalPins);
        }
    }
    @Override
    protected void fillSegmentPins(List<Pin> pins){
        for(Segment segment:complexFormSegments){
            pins.add(segment.p1);
            pins.add(segment.p2);
        }
    }
}
