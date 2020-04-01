package elementary;

import java.awt.*;

public class Segment {

    public Pin p1;
    public Pin p2;

    public ColorHolder color;

    public  Segment(double x1, double y1,double x2, double y2){
        p1= new Pin(x1,y1);
        p2= new Pin(x2,y2);
        color=new ColorHolder(Color.orange);
    }

    public  Segment(double x1, double y1,double x2, double y2,Color color){
        p1= new Pin(x1,y1);
        p2= new Pin(x2,y2);
        this.color=new ColorHolder(color);
    }

    public  Segment(double x1, double y1,double x2, double y2,ColorHolder color){
        p1= new Pin(x1,y1);
        p2= new Pin(x2,y2);
        this.color=color;
    }


    public Segment(Pin p1, Pin p2,Color color){
        this.p1=new Pin(p1.x,p1.y);
        this.p2=new Pin(p2.x,p2.y);
        this.color=new ColorHolder(color);
    }


    public void paint(Graphics g){
        g.setColor(this.color.color);
        g.drawLine((int)p1.x,(int)p1.y,(int)p2.x,(int)p2.y);

        //p1.paint(g);
       // p2.paint(g);
    }

}
