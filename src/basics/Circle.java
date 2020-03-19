package basics;

import elementary.Pin;
import elementary.Segment;

import java.awt.*;
import java.util.ArrayList;

public class Circle extends SegmentableGobject {

    Pin centerPin;
    Pin virtualBorderPin;

    public Circle(double x, double y,double radio,Color color) {
        centerPin=new Pin(x,y);
        virtualBorderPin= new Pin(x+radio,y);

        this.color = color;
    }


    @Override
    public void paint(Graphics g) {
        if(!complexForm){
            double radio=centerPin.distanceTo(virtualBorderPin);
            double m=radio;
            g.setColor(this.color);
            g.drawOval((int)(centerPin.x-m),(int)(centerPin.y-m),(int)(2*m),(int)(2*m));
        }else{
            for(Segment segment: segments){
                segment.paint(g);
            }
        }
    }

    @Override
    public int disassemble() {
        segments = new ArrayList<>();
        int nParts=30;
        double centeX=centerPin.x;
        double centerY=centerPin.y;
        double radio=centerPin.distanceTo(virtualBorderPin);

        double step=2*Math.PI/nParts;

        Pin lastPin=new Pin(centeX+Math.cos(0)*radio,centerY+Math.sin(0)*radio);
        for(int i=1;i<=nParts;i++){
            Pin newPin=new Pin(centeX+Math.cos(step*i)*radio,centerY+Math.sin(step*i)*radio);
            segments.add(new Segment(lastPin,newPin,this.color));
            lastPin=newPin;
        }
        complexForm=true;
        return 30;
    }

    @Override
    public void decompose(int nParts){
        segments = new ArrayList<>();
        double centeX=centerPin.x;
        double centerY=centerPin.y;
        double radio=centerPin.distanceTo(virtualBorderPin);

        double step=2*Math.PI/nParts;

        Pin lastPin=new Pin(centeX+Math.cos(0)*radio,centerY+Math.sin(0)*radio);
        for(int i=1;i<=nParts;i++){
            Pin newPin=new Pin(centeX+Math.cos(step*i)*radio,centerY+Math.sin(step*i)*radio);
            segments.add(new Segment(lastPin,newPin,this.color));
            lastPin=newPin;
        }
        complexForm=true;
    }
}
