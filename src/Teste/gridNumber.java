package Teste;

import basics.*;
import complex.other.Text.GText;
import complex.other.Text.Gchar;
import elementary.Pin;
import elementary.Scene;
import tranformations.TColor;
import tranformations.TSize;

import java.awt.*;

public class gridNumber extends Scene {
    @Override
    public void execute() {
        add(new FPSmesurer(100,100));
        GText gText=new GText("t",300,600,700);
        Matrix matrix= new Matrix(300,100,23,28,new Color(0,130,0));
        add(gText);
        add(matrix);
        Gchar gchar= (Gchar) gText.chields.get(0);
        for(int i=0;i<matrix.rectagles.length;i++){
            for(int j=0;j<matrix.rectagles[i].length;j++){
                Pin midPin= matrix.rectagles[i][j].midPin();
                if(gchar.shape.contains(midPin.x-gchar.centerPin.x+90,midPin.y-gchar.centerPin.y)) {
                    matrix.rectagles[i][j].transform(new TColor(Color.white));
                }
            }
        }
        p(2000);
        gText.transform(new TColor(Color.black),2000);
        GroupGobject gg= new GroupGobject();
        gg.add(new Circle(150,150,40,Color.magenta)
        ,new Circle(150,250,40,Color.magenta)
                ,new Circle(150,350,40,Color.magenta)
                ,new Circle(150,450,40,Color.green)
                ,new Circle(150,550,40,Color.yellow)
                ,new Circle(150,650,40,Color.white)
                ,new Circle(150,750,40,Color.pink));

        p(3000);
        convert(matrix,gg,1000);
        p(3000);
        convert(gg,matrix,1000);
        p(3000);
        matrix.transform(new TSize(0.1),1000);
        p(3000);
        matrix.transform(new TSize(10),1000);

    }

    public static void main(String[] args) {
        new gridNumber();
    }
}
