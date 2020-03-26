package tranformations;

import elementary.ColorHolder;
import elementary.Gobject;
import elementary.Pin;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TColor extends Transformation {
    Color finalColor;
    public TColor(Color color){
        this.finalColor=color;
    }

    @Override
    public void transform(Gobject go, int milis) {
        new Thread(()->{
            int stepTime = milis / STEPS;

            int finalRed=finalColor.getRed();
            int finalGreen=finalColor.getGreen();
            int finalBlue=finalColor.getBlue();

            List<ColorHolder> colors=go.getColors();

            List<Double> acelerationRed=new ArrayList<>();
            List<Double> acelerationGreen=new ArrayList<>();
            List<Double> acelerationBlue=new ArrayList<>();

            for(int i=0;i<colors.size();i++){
                acelerationRed.add((4*(finalRed-colors.get(i).color.getRed()+0.0))/(2*STEPS+(STEPS*STEPS)));
                acelerationGreen.add((4*(finalGreen-colors.get(i).color.getGreen()+0.0))/(2*STEPS+(STEPS*STEPS)));
                acelerationBlue.add((4*(finalBlue-colors.get(i).color.getBlue()+0.0))/(2*STEPS+(STEPS*STEPS)));
            }

            for (int i = 0; i <= STEPS/2; i++) {
                try {
                    Thread.sleep(stepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int j=0;j<colors.size();j++){
                    colors.get(j).change(i*acelerationRed.get(j),i*acelerationGreen.get(j),i*acelerationBlue.get(j));
                }
            }

            for (int i = 0; i <= STEPS/2; i++) {
                try {
                    Thread.sleep(stepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int j=0;j<colors.size();j++){
                    colors.get(j).change(((STEPS/2)-i)*acelerationRed.get(j),((STEPS/2)-i)*acelerationGreen.get(j),((STEPS/2)-i)*acelerationBlue.get(j));
                }
            }
        }).start();

    }
}
