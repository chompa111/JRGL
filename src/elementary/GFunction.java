package elementary;

import basics.Line;

import java.awt.*;
import java.util.function.Function;

public class GFunction extends Gobject{
    Function<Double,Double> function;
    double zoom;
    Pin initPin;

    public GFunction(double x, double y, Function<Double,Double> function,double zoom, Color color) {
        initPin=new Pin(x,y);

        Pin auxPin=new Pin(initPin);
        for(int i=0;i<100;i++){
            double truex=auxPin.x-initPin.x;
            double truey=auxPin.y-initPin.y;
            Pin newPin=new Pin(auxPin.x+5,auxPin.y-zoom*function.apply((truex+5)/zoom));
            chields.add(new Line(auxPin.x,auxPin.y,newPin.x,newPin.y,color));
            auxPin=newPin;
        }
    }
}
