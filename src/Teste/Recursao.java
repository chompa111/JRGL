package Teste;

import basics.Circle;
import basics.GroupGobject;
import elementary.Scene;
import tranformations.TColor;
import tranformations.TConvert;
import tranformations.TMove;

import java.awt.*;

public class Recursao extends Scene {
    @Override
    public void execute() {

        for(int i=0;i<10;i++){
            Circle circle= new Circle(450,450,250, Color.MAGENTA);
            add(circle);
            GroupGobject gg= new GroupGobject();
            mitose(circle,5,gg);
            gg.add(circle);

            p(7000);
            gg.transform(new TConvert(new Circle(450,450,250,Color.MAGENTA)),1000);
            p(1100);
            gg.chields.forEach(this::remove);
        }
        Circle circle= new Circle(450,450,250, Color.MAGENTA);
        add(circle);
        p();
        circle.transform(new TColor(Color.black));


    }

    public static void main(String[] args) {
        new Recursao();
    }

    void mitose(Circle circle, int n, GroupGobject gg){
        new Thread(()->{
            double fator=2;
            double raioPai=circle.centerPin.distanceTo(circle.virtualBorderPin);
            if(n<0)return;
            Circle filho1= new Circle(circle.centerPin.x+raioPai/2,circle.centerPin.y,raioPai/2, Color.black);
            add(filho1);
            filho1.transform(new TColor(Color.MAGENTA));
            Circle filho2= new Circle(circle.centerPin.x-raioPai/2,circle.centerPin.y,raioPai/2, Color.black);
            add(filho2);
            filho2.transform(new TColor(Color.MAGENTA));
            //filho1.transform(new TMove(((raioPai*fator)/2)-Math.random()*fator*raioPai,((raioPai*fator)/2)-Math.random()*fator*raioPai));
            //filho2.transform(new TMove(((raioPai*fator)/2)-Math.random()*fator*raioPai,((raioPai*fator)/2)-Math.random()*fator*raioPai));
            gg.add(filho1,filho2);
            p(300);
           // circle.transform(new TColor(Color.black));
            p(400);
            mitose(filho1,n-1,gg);
            mitose(filho2,n-1,gg);
        }).start();

    }
}
