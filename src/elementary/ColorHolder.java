package elementary;

import java.awt.*;

public class ColorHolder {
    public Color color;
    double red=0;
    double green=0;
    double blue=0;
    double alpha=255;

    public void change(double r, double g,double b,double a){
        if(color==null){
            color=Color.black;
        }
        red+=r;
        green+=g;
        blue+=b;
        alpha+=a;

        if(red<0)red=0;
        if(green<0)green=0;
        if(blue<0)blue=0;
        if(alpha<0)alpha=0;

        if(red>255)red=255;
        if(green>255)green=255;
        if(blue>255)blue=255;
        if(alpha>253)alpha=255;

        color=new Color((int)red,(int)green,(int)blue,(int)alpha);
    }
    public ColorHolder(ColorHolder colorHolder){
        this.color=new Color(colorHolder.color.getRed(),colorHolder.color.getGreen(),colorHolder.color.getBlue(),colorHolder.color.getAlpha());
        this.red=colorHolder.red;
        this.green=colorHolder.green;
        this.blue=colorHolder.blue;
        this.alpha=colorHolder.alpha;
    }
    public ColorHolder(Color color){
        this.color=color;
        this.red=color.getRed();
        this.green=color.getGreen();
        this.blue=color.getBlue();
        this.alpha=color.getAlpha();
    }

    public ColorHolder(int r,int g, int b){
        color=new Color(r,g,b);
        this.red=r;
        this.green=g;
        this.blue=b;
    }
    public ColorHolder(int r,int g, int b,int a){
        color=new Color(r,g,b,a);
        this.red=r;
        this.green=g;
        this.blue=b;
    }

    public void set(Color color){
        this.color=color;
        this.red=color.getRed();
        this.green=color.getGreen();
        this.blue=color.getBlue();
        this.alpha=color.getAlpha();
    }

    static Color randomColor(){
        return new Color((int)(240*Math.random()),(int)(240*Math.random()),(int)(240*Math.random()));
    }
}
