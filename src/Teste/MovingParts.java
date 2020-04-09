package Teste;

import basics.*;
import elementary.Gobject;
import elementary.Scene;
import tranformations.TConvert;
import tranformations.TMove;

import java.awt.*;

public class MovingParts extends Scene {

    @Override
    public void execute() {
        add(new FPSmesurer(790, 70));
        Matrix matrix = new Matrix(50, 50, 25, 25, Color.ORANGE);
        add(matrix);


        p(3000);
        for (int i = 24; i > 0; i--) {

            mix(matrix.rectagles()[24][i], 20);
            mix(matrix.rectagles()[23][i], 20);
            p(270);
        }


        //matrix.transform(new TConvert(new Circle(450,450,400,Color.MAGENTA)));

        //circle.transform(new TMove(300,50),1000);
        //p(1100);


    }

    void mix(Gobject gobject, int amount) {
        new Thread(() -> {
            for (int i = 0; i < amount; i++) {
                p(1700);
                gobject.transform(new TMove(35, 0), 1500);
                p(1700);
                gobject.transform(new TMove(-35, 0), 1500);
            }
        }).start();
    }

    public static void main(String[] args) {
        new MovingParts();
    }
}
