package elementary;

import java.awt.*;

public class ColorHolder {
    public Color color;
    double red=0;
    double green=0;
    double blue=0;

    public void change(double r, double g,double b){
        if(color==null){
            color=Color.black;
        }
        red+=r;
        green+=g;
        blue+=b;

        if(red<0)red=0;
        if(green<0)green=0;
        if(blue<0)blue=0;

        if(red>255)red=255;
        if(green>255)green=255;
        if(blue>255)blue=255;

        color=new Color((int)red,(int)green,(int)blue);
    }

    public ColorHolder(Color color){
        this.color=color;
        this.red=color.getRed();
        this.green=color.getGreen();
        this.blue=color.getBlue();
    }

    public ColorHolder(int r,int g, int b){
        color=new Color(r,g,b);
        this.red=r;
        this.green=g;
        this.blue=b;
    }

    public void set(Color color){
        this.color=color;
        this.red=color.getRed();
        this.green=color.getGreen();
        this.blue=color.getBlue();
    }

    static Color randomColor(){
        return new Color((int)(240*Math.random()),(int)(240*Math.random()),(int)(240*Math.random()));
    }
}
