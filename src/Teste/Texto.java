package Teste;

import basics.Circle;
import basics.FPSmesurer;
import basics.Rectangle;
import elementary.Scene;
import basics.Text;
import tranformations.TMove;

import java.awt.*;

public class Texto extends Scene {

    @Override
    public void execute() {
        add(new FPSmesurer(500,120));
       Circle circle= new Circle(500,500,120,Color.CYAN);
       circle.chields.add(new Text("circle",500-30,650,25));

       add(circle);

       p(2000);
       circle.transform(new TMove(-300,-100),1000);
       p(5000);

       Rectangle rectangle=convert(circle,new Rectangle(300,300,500,500,Color.red),2000);
       p(3000);
       convert(rectangle,new Circle(400,400,100,Color.orange),1000);
    }

    public static void main(String[] args) {
        new Texto();
    }
}
