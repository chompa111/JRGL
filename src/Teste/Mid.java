package Teste;

import basics.Circle;
import basics.GroupGobject;
import basics.Rectangle;
import elementary.Scene;

import java.awt.*;

public class Mid extends Scene {
    @Override
    public void execute() {
        GroupGobject gg= new GroupGobject();

        Rectangle Base= new Rectangle(200,200,800,800, Color.green);
        include(Base);
        gg.add(Base);
        p(1000);
        for(int i=0;i<10;i++){
            double l=Base.p2.x-Base.p1.x;
            Circle circle=new Circle(0,0,l/2,Color.magenta);
            position.setMid(Base,circle);

            include(circle);
            gg.add(circle);
            p(300);
            double radio=circle.centerPin.distanceTo(circle.virtualBorderPin);
            Rectangle newBase= new Rectangle(0,0,radio*Math.sqrt(2),radio*Math.sqrt(2),Color.green);
            include(newBase);
            position.setMid(Base,newBase);
            gg.add(newBase);


            Base=newBase;
            p(300);
        }

        p(2000);

        convert(gg,new Rectangle(200,200,800,800, Color.green),1000);


    }

    public static void main(String[] args) {
        new Mid();
    }
}
