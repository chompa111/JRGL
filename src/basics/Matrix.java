package basics;

import elementary.Gobject;

import java.awt.*;

public class Matrix extends Gobject {
   public Rectangle[][] rectagles;
    public Matrix(double x, double y, int l, int h, Color color) {
        rectagles=new Rectangle[l][h];
        for(int i=0;i<l;i++){
            for(int j=0;j<h;j++){
                Rectangle rectagle= new Rectangle(x+(i*30),y+(j*30),x+(i*30)+20,y+(j*30)+20,color);
                chields.add(rectagle);
                rectagles[i][j]=rectagle;
            }
        }
    }

    public Rectangle[][] rectagles(){
        return rectagles;
    }
}
