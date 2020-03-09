package basics;

import elementary.Gobject;
import elementary.Pin;
import elementary.Segment;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Rectagle extends Gobject {

    private boolean complexForm;
    private Segment segment;

    Pin p1;
    Pin p2;

    Line rightLine;
    Line leftLine;
    Line topLine;
    Line bottomLine;

    Color color;

    public Rectagle(double x1, double y1, double x2, double y2) {
        p1 = new Pin(x1, y1);
        p2 = new Pin(x2, y2);
    }

    public Rectagle(double x1, double y1, double x2, double y2, Color color) {
        p1 = new Pin(x1, y1);
        p2 = new Pin(x2, y2);
        this.color = color;
    }

    @Override
    public void disassemble() {
        complexForm = true;
        rightLine = new Line(p2.x, p1.y, p2.x, p2.y, this.color);
        leftLine = new Line(p1.x, p2.y, p1.x, p1.y, this.color);
        topLine = new Line(p1.x, p1.y, p2.x, p1.y, this.color);
        bottomLine = new Line(p1.x, p2.y, p2.x, p2.y, this.color);

        chields.add(rightLine);
        chields.add(leftLine);
        chields.add(topLine);
        chields.add(bottomLine);
    }

    @Override
    protected void fillPositinalPins(List<Pin> pins) {
        if(!complexForm){
            pins.add(p1);
            pins.add(p2);
        }
    }

    @Override
    protected void fillSegmentPins(List<Pin> pins) {
        if (complexForm) {
            topLine.fillSegmentPins(pins);
            leftLine.fillSegmentPins(pins);
            rightLine.fillSegmentPins(pins);
            leftLine.fillSegmentPins(pins);
        }
    }

    @Override
    protected void fillSurfacePins(List<Pin> pins) {
    }

    @Override
    public void mill(int nParts) {
        if (complexForm) {
            genericMill(nParts);
        } else {
            disassemble();
            genericMill(nParts);
        }
    }


    @Override
    public void paint(Graphics g) {
        if (complexForm) {
            for (Gobject chield : this.chields) {
                chield.paint(g);
            }
        } else {
            g.setColor(this.color);
            g.drawRect((int) p1.x, (int) p1.y, (int) (p2.x - p1.x), (int) (p2.y - p1.y));
        }
    }

    public Line getRightLine() {
        if(!complexForm)disassemble();
        return rightLine;
    }

    public Line getLeftLine() {
        if(!complexForm)disassemble();
        return leftLine;
    }

    public Line getTopLine() {
        if(!complexForm)disassemble();
        return topLine;
    }

    public Line getBottomLine() {
        if(!complexForm)disassemble();
        return bottomLine;
    }
}
