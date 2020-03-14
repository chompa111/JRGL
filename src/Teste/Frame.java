package Teste;

import basics.*;
import elementary.Gobject;
import tranformations.MoveTransform;
import tranformations.PinPinSegmentTransfomation;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Frame extends JFrame {

    List<Gobject> gobjects= new ArrayList<>();
    Image image= new BufferedImage(700,700,BufferedImage.TYPE_INT_RGB);



    public Frame(){

        setSize(700,700);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        new Thread(()->{
            while (true){
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        gobjects.add(new FPSmesurer(550,70));
        Circle circle= new Circle(200,200,80,Color.orange);

        Circle circle3= new Circle(230,230,60,Color.orange);
        circle3.mill(0);
        Circle circle4= new Circle(210,210,50,Color.orange);
        circle4.mill(0);
        circle.chields.add(circle3);
        circle.chields.add(circle4);

        GroupGobject gg= new GroupGobject(circle3,circle4);

        Circle circle2= new Circle(200,200,80,Color.orange);
        circle2.mill(0);
        circle.mill(30);
        gobjects.add(circle);
        Line line= new Line(400,420,400,100,Color.blue);
        //gobjects.add(line);

        Rectagle rectagle = new Rectagle(300,300,500,500,Color.pink);
        rectagle.mill(10);
        //circle.transform(new MoveTransform(200,0));
        circle.mill(0);
        line.mill(70);
        p(900);
        circle.transform(new PinPinSegmentTransfomation(line),450);
        p(1900);
        circle.transform(new PinPinSegmentTransfomation(gg),500);
        p(1900);
        circle.transform(new PinPinSegmentTransfomation(gg),500);

    }

    void p(){
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void p(int milis){
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void paintComponent(Graphics g){

        ((Graphics2D)g).setStroke(new BasicStroke(3));
        g.setColor(new Color(40,10,20));
        g.fillRect(0,0,700,700);
       for(int i=0;i<gobjects.size();i++){
           gobjects.get(i).paint(g);
       }
    }

    @Override
    public void paint(Graphics g){
        paintComponent(image.getGraphics());
        g.drawImage(image,0,0,this);
    }


    public static void main(String[] args) {
        new Frame();
    }
}
