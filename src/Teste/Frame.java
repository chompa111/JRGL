package Teste;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Frame extends JFrame {
    Image image= new BufferedImage(700,700,BufferedImage.TYPE_INT_RGB);
    public Frame(){

        setSize(700,700);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        new Thread(()->{
            while (true){
                repaint();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    void paintComponent(Graphics g){

    }

    @Override
    public void paint(Graphics g){

    }
}
