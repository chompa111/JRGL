package elementary;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public abstract class Scene extends JFrame {

    List<Gobject> gobjects = new ArrayList<>();
    Image image = new BufferedImage(900, 900, BufferedImage.TYPE_INT_RGB);


    public Scene() {

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
        execute();
    }

    public void add(Gobject gobject) {
        gobjects.add(gobject);
    }

    public void remove(Gobject gobject) {
        gobjects.remove(gobject);
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

}
