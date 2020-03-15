package basics;

import elementary.Gobject;
import elementary.Pin;
import elementary.Segment;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class SegmentableGobject extends Gobject {

    public Color color;
    List<Segment> segments;
    List<Pin> positionalPins;

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
           pins.addAll(positionalPins);
        }
    }
    @Override
    public void fillSegmentPins(List<Pin> pins){
        if(segments!=null) {
            for (Segment segment : segments) {
                pins.add(segment.p1);
                pins.add(segment.p2);
            }
        }
        for(Gobject child:chields){
            child.fillSegmentPins(pins);
        }
    }

    @Override
    public void decompose(int nParts) {
        if (complexForm) {
            genericDecompose(nParts);
        } else {
            disassemble();
            genericDecompose(nParts);
        }
    }

    void addPositionalPins(Pin... pins){
        positionalPins.addAll(Arrays.asList(pins));
    }

}
