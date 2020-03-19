package basics;

import elementary.Gobject;

import java.awt.*;

public class Matrix extends Gobject {
   public Rectagle[][] rectagles;
    public Matrix(double x, double y, int l, int h, Color color) {
        rectagles=new Rectagle[l][h];
        for(int i=0;i<l;i++){
            for(int j=0;j<h;j++){
                Rectagle rectagle= new Rectagle(x+(i*30),y+(j*30),x+(i*30)+20,y+(j*30)+20,color);
                chields.add(rectagle);
                rectagles[i][j]=rectagle;
            }
        }
    }

    public Rectagle[][] rectagles(){
        return rectagles;
    }
}
