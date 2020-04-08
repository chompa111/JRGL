package tranformations;

import basics.Line;
import elementary.Gobject;
import elementary.Pin;

import java.util.ArrayList;
import java.util.List;

public class TSize extends Transformation{
    double factor=1;
    public TSize(double factor){
        this.factor=1-(1/factor);
    }

    @Override
    public void transform(Gobject go, int milis) {
        new Thread(() -> {

            int stepTime = milis / STEPS;

            List<Pin> positionalPins = go.getPositionalPins();
            List<Pin> segmentPins = Pin.getPinsFromSegments(go.getSegments());
            List<Pin> surfacePinsPins = go.getSurfacePins();

            List<Pin> allPins= new ArrayList<>();
            allPins.addAll(positionalPins);
            allPins.addAll(segmentPins);
            allPins.addAll(surfacePinsPins);

            Pin midPin=Pin.midPin(allPins);
            int size=allPins.size();

            List<Double> acelerationX=new ArrayList<>(size);
            List<Double> acelerationY=new ArrayList<>(size);

            for(int i=0;i<size;i++){
                acelerationX.add(0d);
                acelerationY.add(0d);
            }

            for(int i=0;i<size;i++){
                acelerationX.set(i,(4*factor*(midPin.x-allPins.get(i).x))/(2*STEPS+(STEPS*STEPS)));
                acelerationY.set(i,(4*factor*(midPin.y-allPins.get(i).y))/(2*STEPS+(STEPS*STEPS)));
            }

            for (int i = 0; i <= STEPS/2; i++) {
                try {
                    Thread.sleep(stepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int j=0;j<size;j++){
                    allPins.get(j).x+=i*acelerationX.get(j);
                    allPins.get(j).y+=i*acelerationY.get(j);
                }
            }

            for (int i = 0; i <= STEPS/2; i++) {
                try {
                    Thread.sleep(stepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int j=0;j<size;j++){
                    allPins.get(j).x+=((STEPS/2)-i)*acelerationX.get(j);
                    allPins.get(j).y+=((STEPS/2)-i)*acelerationY.get(j);
                }
            }

        }).start();
    }

    @Override
    public void set(Gobject go) {

    }
}
