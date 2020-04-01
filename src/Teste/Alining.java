package Teste;

import basics.*;
import elementary.Scene;
import tranformations.TConvert;

import java.awt.*;

public class Alining extends Scene {
    @Override
    public void execute() {

        add(new FPSmesurer(700,100));
        Line line=new Line (450,0,450,1000,Color.orange);
        add(line);
        p(1000);
        line.transform(new TConvert(new Matrix(200,200,20,20,Color.orange)),1000);
        p(1100);
        Matrix matrix=new Matrix(200,200,20,20,Color.orange);
        add(matrix);
        remove(line);

        p(2000);
        matrix.transform(new TConvert(new Circle(450,450,200,Color.orange)),15000);

    }

    public static void main(String[] args) {
        new Alining();
    }
}
