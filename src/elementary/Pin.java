package elementary;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pin {
    public double x;
    public double y;

    public Pin(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Pin(Pin pin){
        x=pin.x;
        y=pin.y;
    }

    public double distanceTo(Pin pin) {
        return Math.sqrt(((this.x - pin.x) * (this.x - pin.x)) + (this.y - pin.y) * (this.y - pin.y));
    }

    public double distanceTo(double x, double y) {
        return Math.sqrt(((this.x - x) * (this.x - x)) + (this.y - y) * (this.y - y));
    }

    public static List<Pin> getPinsFromSegments(List<Segment> segments) {
        List<Pin> pins = new ArrayList<>();
        for (Segment segment : segments) {
            pins.add(segment.p1);
            pins.add(segment.p2);
        }
        return pins;
    }

    public static  Pin midPin(List<Pin> listPin){
        Pin maxPin= maxPin(listPin);
        Pin minPin= minPin(listPin);
        return midPin(maxPin,minPin);
    }

    public static Pin midPin(Pin p1, Pin p2){
        return new Pin((p1.x+p2.x)/2,(p1.y+p2.y)/2);
    }

    public static Pin minPin(List<Pin> list){
        double minx=Double.MAX_VALUE;
        double miny=Double.MAX_VALUE;
        for(Pin pin :list){
            minx=Math.min(minx,pin.x);
            miny=Math.min(miny,pin.y);
        }

        return new Pin(minx,miny);
    }
    public static Pin maxPin(List<Pin> list){
        double minx=Double.MIN_VALUE;
        double miny=Double.MIN_VALUE;
        for(Pin pin :list){
            minx=Math.max(minx,pin.x);
            miny=Math.max(miny,pin.y);
        }

        return new Pin(minx,miny);
    }

    public void paint(Graphics g){
        g.setColor(Color.white);
        g.fillOval((int)x-2,(int)y-2,4,4);
        //g.setFont(new Font(Font.DIALOG, Font.ITALIC,11));
       // g.drawString("("+(int)x+","+(int)y+")",(int)x+10,(int)y);
    }
}
