package elementary;

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


}
