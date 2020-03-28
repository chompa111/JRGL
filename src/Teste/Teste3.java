package Teste;

import basics.*;
import elementary.Scene;
import tranformations.TConvert;

import java.awt.*;

public class Teste3 extends Scene {
    @Override
    public void execute() {
       // p(20000);
        add(new FPSmesurer(700,100));
        p(1200);
          Text line =new Text("Guilherme_Bardeli",260,450,40);
        add(line);

        p(1230);
        Matrix circle=new Matrix(250,250,10,10,Color.white);
        line.transform(new TConvert(circle),1000);
        p(1050);
        add(circle);
        remove(line);
        p(2000);
        Text r6 =new Text("Hj_tem_R6?",300,450,40);
        circle.transform(new TConvert(new Text("Hj_tem_R6?",300,450,40)));
        p(520);
        add(r6);
        remove(circle);

    }

    public static void main(String[] args) {
        new Teste3();
    }
}
