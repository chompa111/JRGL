package basics;

import elementary.ColorHolder;
import elementary.Pin;
import elementary.Segment;

import java.awt.*;
import java.util.ArrayList;

public class Circle extends SegmentableGobject {

    public Pin centerPin;
    public Pin virtualBorderPin;

    public Circle(double x, double y,double radio,Color color) {
        centerPin=new Pin(x,y);
        virtualBorderPin= new Pin(x+radio,y);
        this.color = new ColorHolder(color);
        addPositionalPins(centerPin,virtualBorderPin);
    }


    @Override
    public void paint(Graphics g) {
        if(!complexForm){
            double radio=centerPin.distanceTo(virtualBorderPin);
            double m=radio;
            g.setColor(this.color.color);
            g.drawOval((int)(centerPin.x-m),(int)(centerPin.y-m),(int)(2*m),(int)(2*m));
         //   centerPin.paint(g);
          //  virtualBorderPin.paint(g);
        }else{

            for(int i=0;i<segments.size();i++){
                segments.get(i).paint(g);
            }
        }
        super.paint(g);
    }

    @Override
    public int disassemble() {
        segments = new ArrayList<>();
        int nParts=50;
        double centeX=centerPin.x;
        double centerY=centerPin.y;
        double radio=centerPin.distanceTo(virtualBorderPin);

        double step=2*Math.PI/nParts;

        Pin lastPin=new Pin(centeX+Math.cos(0)*radio,centerY+Math.sin(0)*radio);
        for(int i=1;i<=nParts;i++){
            Pin newPin=new Pin(centeX+Math.cos(step*i)*radio,centerY+Math.sin(step*i)*radio);
            segments.add(new Segment(lastPin,newPin,this.color.color));
            lastPin=newPin;
        }
        complexForm=true;
        return 50+super.disassemble();
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
            segments.add(new Segment(lastPin,newPin,this.color.color));
            lastPin=newPin;
        }
        complexForm=true;
    }

    @Override
    public void getsimpleBack() {
        super.getsimpleBack();
        segments=new ArrayList<>();
    }
}
