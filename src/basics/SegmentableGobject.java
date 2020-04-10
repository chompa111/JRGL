package basics;

import elementary.ColorHolder;
import elementary.Gobject;
import elementary.Pin;
import elementary.Segment;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SegmentableGobject extends Gobject {

    public ColorHolder color;
    List<Segment> segments=new ArrayList<>();
    List<Pin> positionalPins;

    @Override
    public void fillColors(List<ColorHolder>colors) {

        if (!complexForm) {
            colors.add(color);
        } else {
            for (Segment segment:segments){
                colors.add(segment.color);
            }
        }
        super.fillColors(colors);
    }

    @Override
    protected void fillPositinalPins(List<Pin> pins) {
        if (!complexForm) {
            pins.addAll(positionalPins);
            super.fillPositinalPins(pins);
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

    public void addPositionalPins(Pin... pins) {
        if (positionalPins == null) positionalPins = new ArrayList<>();
        positionalPins.addAll(Arrays.asList(pins));
    }

}
