package Teste;

import basics.*;
import basics.Rectangle;
import complex.other.Text.GText;
import elementary.Gobject;
import elementary.Scene;
import tranformations.TColor;
import tranformations.TMove;

import java.awt.*;

public class Subtitle extends Scene {
    @Override
    public void execute() {

        add(new FPSmesurer(100,100));

        Circle circle= new Circle(450,450,170, Color.orange);
        include(circle);
        p(2000);
        position.addSubtitle(circle,new GText("Circle",200,200,40));

        p(2300);

       Rectangle rectangle =convert(circle,new Rectangle(300,300,400,400,Color.red),1000);

       position.addSubtitle(rectangle,new GText("Rectangle",200,200,40));

        position.addSubtitle(rectangle,new GText("sqrt(2+1)",200,200,40));


        p(2000);

        Matrix matrix=convert(rectangle,new Matrix(100,300,10,10,Color.green),1000);

        p(2000);

        GroupGobject gg= new GroupGobject();

        for(int i=0;i<5;i++){
            for(int j=0;j<10;j++){
                matrix.rectagles[i][j].transform(new TMove(300+i*50,j*50-100),700);
                p(30);
                gg.add(matrix.rectagles[i][j]);
            }
        }

        for(int i=0;i<5;i++){
            for(int j=0;j<10;j++){

                p(30);
                position.addSubtitle(matrix.rectagles[i][j],new GText("["+j+","+i+"]",200,200,20));
            }
        }
        p(1000);
        emphasize(gg);
        p(1000);
        GroupGobject gg2=new GroupGobject();
        for(int i=0;i<5;i++){
            for(int j=0;j<10;j++){

                p(50);
                int finalJ = j;
                int finalI = i;
                new Thread(() -> {
                    Circle circle1=new Circle(30,30,35,Color.magenta);
                    gg2.add(circle1);
                    position.setMid(matrix.rectagles[finalI][finalJ],circle1);
                    convert(matrix.rectagles[finalI][finalJ],circle1,700);
                }).start();
            }
        }

        p(3000);

        convert(gg2,gg,1000);

        p(1000);

       Rectangle pontinho= convert(gg,new Rectangle(450,450,451,451,Color.orange),700);
        position.addSubtitle(matrix,new GText("Sobras",200,200,40));
        p(500);
        position.addSubtitle(pontinho,new GText("pontinho",200,200,40));

        p(1500);
        new TColor(new Color(255,0,0,0)).transform(new GroupGobject(pontinho,matrix));




    }

    public static void main(String[] args) {
        new Subtitle();
    }
}
