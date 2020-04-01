package tranformations;

import elementary.Gobject;
import elementary.Pin;

import java.util.ArrayList;
import java.util.List;

public class TRotate extends Transformation {
    Pin pivot;
    double ang;

    public TRotate (Pin pivot,double ang){
        this.pivot=pivot;
        this.ang=ang;
    }

    @Override
    public void transform(Gobject go, int milis) {
        int stepTime = milis / STEPS;

        List<Pin> positionalPins = go.getPositionalPins();
        List<Pin> segmentPins = Pin.getPinsFromSegments(go.getSegments());
        List<Pin> surfacePinsPins = go.getSurfacePins();

        List<Pin> allPins = new ArrayList<>();
        allPins.addAll(positionalPins);
        allPins.addAll(segmentPins);
        allPins.addAll(surfacePinsPins);


    }
}
