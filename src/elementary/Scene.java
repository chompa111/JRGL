package elementary;

import basics.GroupGobject;
import external.functions.Position;
import javafx.geometry.Pos;
import saveStateObjects.ColorSave;
import tranformations.TColor;
import tranformations.TConvert;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageProducer;
import java.util.ArrayList;
import java.util.List;

import static java.awt.RenderingHints.KEY_RENDERING;
import static java.awt.RenderingHints.VALUE_TEXT_ANTIALIAS_GASP;

public abstract class Scene extends JFrame {

    List<Gobject> gobjects = new ArrayList<>();
    Image image = new BufferedImage(900, 900, BufferedImage.TYPE_INT_ARGB);


    //auxiliar methods
    public Position position= new Position(this);

    public Scene() {

        setUndecorated(true);

        setSize(900, 1900);
        setLocation(800,50);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(new Color(0,0,0,0));

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
        execute();
    }

    public void add(Gobject gobject) {
        gobjects.add(gobject);
    }

    public void remove(Gobject gobject) {
        if(gobject.dad!=null){
            gobject.unviculate();
        }else{
            gobjects.remove(gobject);
        }
        if (gobject instanceof GroupGobject) {
            ((GroupGobject) gobject).unviculate(this);
        }
    }

    abstract public void execute();

    public void p() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void p(int milis) {
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

        ((Graphics2D) g).setBackground(new Color(0, 0, 0));
        ((Graphics2D) g).clearRect(0,0,1000,1000);


        ((Graphics2D) g).setStroke(new BasicStroke(2f));
//        g.setColor(new Color(30, 0, 5));
//        g.fillRect(0, 0, 1700, 900);
        for (int i = 0; i < gobjects.size(); i++) {
            gobjects.get(i).paint(g);
        }
    }

    @Override
    public void paint(Graphics g) {
        ((Graphics2D) g).setBackground(new Color(0, 0, 0));
        ((Graphics2D) g).clearRect(0,0,1000,1000);
        paintComponent(image.getGraphics());
       g.drawImage(image, 0, 0, this);
    }

     public <T extends Gobject> T convert(Gobject a, T b,int milis){
        a.transform(new TConvert(b),milis);
        p(milis+20);
        add(b);
        remove(a);
        p(20);
        b.getsimpleBack();
        a.getsimpleBack();
        return b;
    }

    public void emphasize(Gobject gobject){
        ColorSave colorSave=ColorSave.getSaveFrom(gobject);

        gobject.transform(new TColor(Color.orange),1000);
        p(1100);

        colorSave.transformBack(1000);
        p(1000);
    }

    public void include(Gobject gobject){
        ColorSave colorSave=ColorSave.getSaveFrom(gobject);
        new TColor(new Color(0,0,0,0)).set(gobject);
        add(gobject);
        colorSave.transformBack();
    }

    public void takeOut(Gobject gobject){
        ColorSave colorSave=ColorSave.getSaveFrom(gobject);
        new TColor(new Color(0,0,0,0)).transform(gobject,1000);
        p(1100);
        remove(gobject);
       // colorSave.transformBack();
    }

}
