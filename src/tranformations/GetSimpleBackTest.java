package tranformations;

import basics.Circle;
import basics.Line;
import basics.Rectangle;
import elementary.Scene;

import java.awt.*;

public class GetSimpleBackTest extends Scene {
    @Override
    public void execute() {

        Line line= new Line(200,200,400,400, Color.MAGENTA);
        add(line);
        p(2000);
        Circle circle=convert(line,new Circle(300,300,100,Color.MAGENTA),1500);
        p(2000);
        Rectangle rectagle=convert(circle,new Rectangle(200,200,400,400, Color.MAGENTA),2500);
        p(2000);

        Line line1=convert(rectagle,new Line(50,400,300,400, Color.MAGENTA),800);
        Circle circle1= new Circle(200,400,150,Color.MAGENTA);

        for (int i=0;i<10;i++){
            convert(line1,circle1,3000);
            p(3200);
            convert(circle1,line1,3000);
            p(3200);
        }


    }

    public static void main(String[] args) {
        new GetSimpleBackTest();
    }
}
