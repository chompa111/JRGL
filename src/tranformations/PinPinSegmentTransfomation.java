package tranformations;

import elementary.Gobject;
import elementary.Pin;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class PinPinSegmentTransfomation extends Transformation {

    Gobject toGobject;
    public PinPinSegmentTransfomation(Gobject toGobject) {
        this.toGobject=toGobject;
    }

    @Override
    public void transform(Gobject go, int milis) {

        int toGobjectSegments = toGobject.disassemble();
        int fromGobjectSegments=go.disassemble();

        if(toGobjectSegments<fromGobjectSegments){
            toGobject.decompose(fromGobjectSegments);
        }else{
            go.decompose(toGobjectSegments);
        }

        List<Pin> fromPins=go.getSegmentPins();
        List<Pin> toPins=toGobject.getSegmentPins();


        int size= Math.min(fromPins.size(), toPins.size());
        List<Double> doubleX=new ArrayList<>(size);
        List<Double> doubleY=new ArrayList<>(size);

        for(int i=0;i<size;i++){
            doubleX.add((toPins.get(i).x-fromPins.get(i).x)/STEPS);
            doubleY.add((toPins.get(i).y-fromPins.get(i).y)/STEPS);
        }

        new Thread(() -> {

            int stepTime = milis / STEPS;
            for (int i = 0; i < STEPS; i++) {
                try {
                    Thread.sleep(stepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int j=0;j<size;j++){
                    fromPins.get(j).x+=doubleX.get(j);
                    fromPins.get(j).y+=doubleY.get(j);
                }
            }
        }).start();
    }
}
