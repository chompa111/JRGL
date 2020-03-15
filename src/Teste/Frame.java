package Teste;

import basics.*;
import elementary.Gobject;
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

        Rectagle rectagle = new Rectagle(200,200,400,400,Color.orange);
        Circle circle= new Circle(300,300,100,Color.orange);
        gobjects.add(rectagle);

//        p(700);
//        rectagle.transform(new PinPinSegmentTransfomation(circle),6000);
       p(1000);
        Matrix matrix = new Matrix(200,200,4,4,Color.orange);
        rectagle.transform(new PinPinSegmentTransfomation(matrix));
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
