package Teste;

import basics.Circle;
import basics.FPSmesurer;
import basics.Rectangle;
import elementary.Scene;
import basics.GText;
import tranformations.TColor;
import tranformations.TMove;

import java.awt.*;

public class Texto extends Scene {

    @Override
    public void execute() {
        add(new FPSmesurer(500,120));
       Circle circle= new Circle(500,500,120,Color.CYAN);
       GText text=new GText("2πr",500-30,650,30);
       circle.chields.add(text);

       add(circle);

       p(2000);
       text.chields.get(1).transform(new TColor(Color.green));
        text.chields.get(2).transform(new TColor(Color.orange));



       p(2000);
       circle.transform(new TMove(-300,-100),1000);
       p(5000);

       Rectangle rectangle=convert(circle,new Rectangle(300,300,500,500,Color.red),2000);
       p(3000);
        Circle circle2= new Circle(400,400,120,Color.green);
        GText text2=new GText("batata",400-30,550,30);
        circle2.chields.add(text2);

       convert(rectangle,circle2,1000);
       p(3000);
       convert(circle2,circle,1000);
    }

    public static void main(String[] args) {
        new Texto();
    }
}
