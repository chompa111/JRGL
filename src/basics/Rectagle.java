package basics;

import elementary.ColorHolder;
import elementary.Pin;

import java.awt.*;
import java.util.ArrayList;

public class Rectagle extends SegmentableGobject {

    Pin p1;
    Pin p2;

    Line rightLine;
    Line leftLine;
    Line topLine;
    Line bottomLine;



    public Rectagle(double x1, double y1, double x2, double y2) {
        p1 = new Pin(x1, y1);
        p2 = new Pin(x2, y2);
        addPositionalPins(p1,p2);
    }

    public Rectagle(double x1, double y1, double x2, double y2, Color color) {
        p1 = new Pin(x1, y1);
        p2 = new Pin(x2, y2);
        this.color = new ColorHolder(color);
        addPositionalPins(p1,p2);
    }

    @Override
    public int disassemble() {
        complexForm = true;
        rightLine = new Line(p2.x, p1.y, p2.x, p2.y, this.color.color);
        leftLine = new Line(p1.x, p2.y, p1.x, p1.y, this.color.color);
        topLine = new Line(p1.x, p1.y, p2.x, p1.y, this.color.color);
        bottomLine = new Line(p1.x, p2.y, p2.x, p2.y, this.color.color);

        chields.add(rightLine);
        chields.add(topLine);
        chields.add(leftLine);
        chields.add(bottomLine);
        return 4;
    }


    @Override
    public void paint(Graphics g) {
        if (complexForm) {
           for(int i=0;i<chields.size();i++){
               chields.get(i).paint(g);
           }
        } else {
            g.setColor(this.color.color);
            g.drawRect((int) p1.x, (int) p1.y, (int) (p2.x - p1.x), (int) (p2.y - p1.y));
        }
    }

    public Line getRightLine() {
        if (!complexForm) disassemble();
        return rightLine;
    }

    public Line getLeftLine() {
        if (!complexForm) disassemble();
        return leftLine;
    }

    public Line getTopLine() {
        if (!complexForm) disassemble();
        return topLine;
    }

    public Line getBottomLine() {
        if (!complexForm) disassemble();
        return bottomLine;
    }


}
