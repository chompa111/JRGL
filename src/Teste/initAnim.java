package Teste;

import basics.*;
import elementary.ColorHolder;
import elementary.Gobject;
import elementary.Scene;
import tranformations.TColor;
import tranformations.TConvert;
import tranformations.TMove;

import java.awt.*;

public class initAnim extends Scene {
    @Override
    public void execute() {
        add(new FPSmesurer(200,100));
       Line line=new Line(200,200,600,600,Color.orange);
        add(line);
        line.init();

        p(1600);
        Matrix bandeira=convert(line,new Matrix(200,200,14,10,Color.orange),2000);
        Matrix aste=new Matrix(140,180,1,20,Color.black);
        add(aste);
        aste.transform(new TColor(Color.ORANGE),1100);
        for (int i=0;i<4;i++){
           p(1100);
           bandeira.transformChields(new TColor(Color.green),1000);
           bandeira.transformChields(new TMove(-30,30),1000);
           p(1100);
           bandeira.transformChields(new TColor(Color.ORANGE),700);
           bandeira.transformChields(new TMove(30,-30),1000);
           p(1100);
           bandeira.transformChields(new TColor(Color.blue),1000);
           bandeira.transformChields(new TMove(-30,+30),1000);
           p(1100);
           bandeira.transformChields(new TColor(Color.white),1000);
           bandeira.transformChields(new TMove(30,-30),1000);


       }

        GroupGobject gg1= new GroupGobject();
        GroupGobject gg2= new GroupGobject();
     for (int j=0;j<bandeira.rectagles().length;j++){
         for(int i=0;i<bandeira.rectagles()[5].length;i++){

            if((i+j)%2==0)gg1.add( bandeira.rectagles()[j][i]);
            else gg2.add(bandeira.rectagles()[j][i]);
         }
     }

     p(2200);
     gg1.transform(new TColor(new Color(150,0,0)));
     gg2.transform(new TColor(new Color(0,150,0)));

     p(2000);

     //convert(gg1,new Circle(200+200,200+200,100,new Color(150,0,0)),1000);
     convert(gg2,new Circle(200+100+200,200+200,100,new Color(0,150,0)),1000);


    }

    boolean isPrimo(int n){
        if(n==2)return true;
        for(int i=2;i<=Math.sqrt(n);i++){
            if(n%i==0)return false;
        }
        return true;
    }

    public static void main(String[] args) {
        new initAnim();
    }
}
