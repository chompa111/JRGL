package Teste;

import basics.Circle;
import basics.FPSmesurer;
import basics.Line;
import basics.Matrix;
import elementary.Gobject;
import elementary.Scene;
import tranformations.TColor;
import tranformations.TConvert;
import tranformations.TMove;

import java.awt.*;

public class TestConvert extends Scene {
    @Override
    public void execute() {
        add(new FPSmesurer(700,100));
        Matrix matrix = new Matrix(50,50,12,12, Color.black);
        add(matrix);
        p();
        //matrix.transform(new TColor(Color.MAGENTA),1000);
        for (int i=0;i<2;i++) {
            p();
            matrix.transformChields(new TColor(Color.MAGENTA),1000);
            p(1200);
            matrix.transform(new TColor(Color.orange),1000);
            p(1000);
        }
        matrix.transformChields(new TMove(200,200),1000);
        p(2000);
        Line line=this.convert(matrix,new Line(450,0,450,1000,Color.orange),2000);
        p(2000);
        Circle circle=convert(line,new Circle(450,450,200,Color.orange),1000);
        p(2000);
        Matrix matrix2=convert(circle,new Matrix(250,250,12,12, Color.orange),1000);
        p(1200);

        matrix2.transformChields(new TColor(Color.BLACK),1000);

    }

    public static void main(String[] args) {
        new TestConvert();
    }
}
