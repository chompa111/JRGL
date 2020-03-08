package elementary;

import java.awt.*;

public class Surface {
    Pin p1;
    Pin p2;
    Pin p3;

    Color color;

    public Surface(double x1, double y1, double x2, double y2, double x3, double y3) {

        p1 = new Pin(x1, y1);
        p2 = new Pin(x2, y2);
        p3 = new Pin(x3, y3);

        color = Color.black;

    }

    public Surface(double x1, double y1, double x2, double y2, double x3, double y3, Color color) {

        p1 = new Pin(x1, y1);
        p2 = new Pin(x2, y2);
        p3 = new Pin(x3, y3);

        this.color = color;

    }


    void paint(Graphics g){
        g.setColor(this.color);
        g.drawPolygon(new int[] {(int)p1.x,(int)p2.x,(int)p3.x}, new int[] {(int)p1.y,(int)p2.y,(int)p3.y}, 3);
    }


}
