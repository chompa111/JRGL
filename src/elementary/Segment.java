package elementary;

import java.awt.*;

public class Segment {

    public Pin p1;
    public Pin p2;

    Color color;

    public  Segment(double x1, double y1,double x2, double y2){
        p1= new Pin(x1,y1);
        p2= new Pin(x2,y2);
        color=Color.black;
    }

    public  Segment(double x1, double y1,double x2, double y2,Color color){
        p1= new Pin(x1,y1);
        p2= new Pin(x2,y2);
        this.color=color;
    }

    public void paint(Graphics g){
        g.setColor(this.color);
        g.drawLine((int)p1.x,(int)p1.y,(int)p2.x,(int)p2.y);
    }

}
