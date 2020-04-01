package Teste;

import basics.Circle;
import basics.FPSmesurer;
import elementary.GFunction;
import elementary.Scene;

import java.awt.*;

public class TesteFunction extends Scene {
    @Override
    public void execute() {
        add(new FPSmesurer(500,50));

        GFunction gFunction= new GFunction(300,500, Math::sin, 30,Color.green);
        //GFunction gFunction2= new GFunction(300,500, Math::exp, 30,Color.green);
        //add(gFunction2);
        add(gFunction);
        p(200);

       Circle circle= convert(gFunction,new Circle(200,202,100,Color.green),3000);
       p(6000);
       convert(circle,new GFunction(300,500, Math::sin, 30,Color.green),2000);
    }

    public static void main(String[] args) {
        new TesteFunction();
    }
}
