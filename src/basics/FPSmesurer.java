package basics;

import elementary.Gobject;

import java.awt.*;

public class FPSmesurer extends Gobject {
    double x;
    double y;

    long lastTime=System.currentTimeMillis();
    double fpsMean=60;

    public FPSmesurer(double x, double y) {
        this.x = x;
        this.y = y;
    }

    void refresh(){
        long now= System.currentTimeMillis();
        long delta=now-lastTime;
        lastTime=now;
        double fps=1000.0/(delta+0.0);

        fpsMean = (fpsMean*19+fps)/20;
    }

    @Override
    public void paint(Graphics g) {
        refresh();
        if(fpsMean<=30)g.setColor(Color.red);
        if(fpsMean>30&& fpsMean<=45)g.setColor(Color.orange);
        if(fpsMean>45)g.setColor(Color.green);
        g.drawString("fps:"+fpsMean,(int)x,(int)y);
    }
}
