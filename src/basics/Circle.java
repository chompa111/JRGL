package basics;

import elementary.Pin;
import elementary.Segment;

import java.awt.*;
import java.util.ArrayList;

public class Circle extends ComplexableGobject {

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
            double m=radio/2;
            g.setColor(this.color);
            g.drawOval((int)(centerPin.x-m),(int)(centerPin.y-m),(int)m,(int)m);
        }else{
            for(Segment segment: complexFormSegments){
                segment.paint(g);
            }
        }
    }


    @Override
    public void mill(int nParts){
        complexFormSegments= new ArrayList<>();
        nParts+=30;
        double centeX=centerPin.x;
        double centerY=centerPin.y;
        double radio=centerPin.distanceTo(virtualBorderPin);

        double step=2*Math.PI/nParts;

        Pin lastPin=new Pin(centeX+Math.cos(0)*radio,centerY+Math.sin(0)*radio);
        for(int i=1;i<=nParts;i++){
            Pin newPin=new Pin(centeX+Math.cos(step*i)*radio,centerY+Math.sin(step*i)*radio);
            complexFormSegments.add(new Segment(lastPin,newPin,this.color));
            lastPin=newPin;
        }
        complexForm=true;
    }
}
