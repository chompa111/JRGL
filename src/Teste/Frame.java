package Teste;

import basics.Line;
import basics.Matrix;
import basics.Rectagle;
import elementary.Gobject;
import tranformations.MoveTransform;

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
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Matrix matrix= new Matrix(50,50,10,10,Color.pink);
       // gobjects.add(matrix);
        matrix.transform(new MoveTransform(200,100),2000);
        p(2100);

        matrix.rectagles()[5][2].transform(new MoveTransform(-200,-100),2000);


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
        g.setColor(Color.white);
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
