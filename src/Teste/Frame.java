package Teste;

import basics.*;
import elementary.Gobject;
import tranformations.TConvert;
import tranformations.TMove;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Frame extends JFrame {

    List<Gobject> gobjects = new ArrayList<>();
    Image image = new BufferedImage(900, 900, BufferedImage.TYPE_INT_RGB);


    public Frame() throws IOException, FontFormatException {

        setSize(900, 900);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        new Thread(() -> {
            while (true) {
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        gobjects.add(new FPSmesurer(550, 70));
        p(500);

        GText batata= new GText("bman",200,200,25);
        gobjects.add(batata);
        p(600);
        GText frita= new GText("brunoYuji",200,200,25);
        GText frita2= new GText("brunoYuji",200,200,25);
        batata.transform(new TConvert(frita));
        p(600);
        gobjects.add(frita2);
        gobjects.remove(batata);
        p(1000);
        Circle circleN= new Circle(250,190,50,Color.white);
        frita2.transform(new TConvert(circleN),500);
        p(600);
        gobjects.add(circleN);
        gobjects.remove(frita2);
        p(1000);

        circleN.transform(new TConvert(new GText("chompinha",200,200,25)),500);
        p(600);
        GText chompinha=new GText("chompinha",200,200,25);
        gobjects.add(chompinha);
        gobjects.remove(circleN);
        p(1000);
        chompinha.transform(new TConvert(new Matrix(100, 100, 6, 6, Color.MAGENTA)));
        p(600);
        Matrix matrix2=new Matrix(100, 100, 6, 6, Color.white);
        gobjects.add(matrix2);
        gobjects.remove(chompinha);
        p(2000);
        matrix2.transform(new TConvert(new GText("f(x)=sin(x)+cos(3x)+ln(x)+log(8)",200,200,25)),500);
        p(600);
        gobjects.remove(matrix2);
        GText formula=new GText("f(x)=sin(x)+cos(3x)+ln(x)+log(10)",200,200,25);
        gobjects.add(formula);

        GroupGobject grupo= new GroupGobject(new Circle(300,100,69,Color.white),
                new Circle(500,300,169,Color.white),
                new Circle(200,200,30,Color.white),
                new Circle(600,500,80,Color.white)

                );

        p(1000);
        formula.transform(new TConvert(grupo),2000);
        p(2050);
        formula.transform(new TMove(200,30));





        p(22000);
        gobjects.add(new FPSmesurer(550, 70));
        p(500);


        Matrix matrix = new Matrix(100, 100, 4, 4, Color.MAGENTA);
        gobjects.add(matrix);
        List<Circle> circles = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            circles.add(new Circle(450, 100 + (i * 45), 20, Color.MAGENTA));
        }
        p(600);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrix.rectagles[i][j].transform(new TConvert(circles.get((i * 4) + j)));
                p();
            }
        }

        p(3000);
        gobjects.remove(matrix);
        GroupGobject gg = new GroupGobject(circles);
        gobjects.add(gg);
        Circle circle = new Circle(450, 450, 200, Color.MAGENTA);
        gg.transform(new TConvert(circle));

        p(900);

        gobjects.remove(gg);
        gobjects.add(circle);

        p(500);
        circle.transform(new TConvert(matrix));
        p(600);
        matrix = new Matrix(100, 100, 4, 4, Color.MAGENTA);
        gobjects.add(matrix);
        gobjects.remove(circle);

        p(700);
        matrix.transform(new TConvert(new Line(600, 200, 600, 700, Color.magenta)));
        p(600);
        gobjects.remove(matrix);
        gobjects.add(new Line(600, 200, 600, 700, Color.magenta));

    }

    void p() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void p(int milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        ((Graphics2D) g).setStroke(new BasicStroke(1.5f));
        g.setColor(new Color(20, 10, 50));
        g.fillRect(0, 0, 1700, 900);
        for (int i = 0; i < gobjects.size(); i++) {
            gobjects.get(i).paint(g);
        }
    }

    @Override
    public void paint(Graphics g) {

        paintComponent(image.getGraphics());
        g.drawImage(image, 0, 0, this);
    }


    public static void main(String[] args) throws IOException, FontFormatException {
        new Frame();
    }
}
