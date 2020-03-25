package basics;

import elementary.Gobject;
import elementary.Pin;
import elementary.Segment;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SegmentableGobject extends Gobject {

    public Color color;
    List<Segment> segments;
    List<Pin> positionalPins;

    @Override
    public void fillColors(List<Color>colors) {
        if (!complexForm) {
            colors.add(color);
        } else {
            for (Segment segment:segments){
                colors.add(segment.color);
            }
            for(Gobject child:chields){
                child.fillColors(colors);
            }
        }
    }

    @Override
    protected void fillPositinalPins(List<Pin> pins) {
        if (!complexForm) {
            pins.addAll(positionalPins);
        }
    }

    @Override
    public void fillSegments(List<Segment> segments) {
        if (this.segments != null) {
            segments.addAll(this.segments);
        }
        for (Gobject child : chields) {
            child.fillSegments(segments);
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

    void addPositionalPins(Pin... pins) {
        if (positionalPins == null) positionalPins = new ArrayList<>();
        positionalPins.addAll(Arrays.asList(pins));
    }

}
