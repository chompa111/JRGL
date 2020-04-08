package Teste;

import basics.*;
import complex.other.Text.GText;
import elementary.Scene;
import tranformations.TConvert;

import java.awt.*;

public class Teste3 extends Scene {
    @Override
    public void execute() {
       // p(20000);
        add(new FPSmesurer(700,100));
        p(1200);
          GText line =new GText("Guilherme_Bardeli",260,450,40);
        add(line);

        p(1230);
        Matrix circle=new Matrix(250,250,10,10,Color.white);
        line.transform(new TConvert(circle),8000);
        p(8050);
        add(circle);
        remove(line);
        p(2000);
        GText r6 =new GText("Hj_tem_R6?",300,450,40);
        circle.transform(new TConvert(new GText("Hj_tem_R6?",300,450,40)),8000);
        p(8020);
        add(r6);
        remove(circle);

    }

    public static void main(String[] args) {
        new Teste3();
    }
}
