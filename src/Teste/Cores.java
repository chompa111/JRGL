package Teste;

import basics.Circle;
import basics.FPSmesurer;
import elementary.ColorHolder;
import elementary.Scene;
import tranformations.TColor;

import java.awt.*;

public class Cores extends Scene {
    @Override
    public void execute() {
        add(new FPSmesurer(100,100));
        p(1000);
        Circle circle= new Circle(450,450,150,Color.green);
        include(circle);
        p(1000);
        emphasize(circle);
        p(2000);
        takeOut(circle);
        p(1000);
        include(circle);
    }

    public static void main(String[] args) {
       new Cores();
    }
}
