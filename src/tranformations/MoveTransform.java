package tranformations;

import elementary.Gobject;
import elementary.Pin;

import java.util.List;

public class MoveTransform extends Transformation {
    double l;
    double h;

    public MoveTransform(double l, double h) {
        this.l = l;
        this.h = h;
    }

    @Override
    public void transform(Gobject go, int milis) {

        new Thread(() -> {

            int stepTime = milis / STEPS;

            List<Pin> positionalPins = go.getPositionalPins();
            List<Pin> segmentPins = go.getSegmentPins();
            List<Pin> surfacePinsPins = go.getSurfacePins();

            double deltaL = l / STEPS;
            double deltaH = h / STEPS;
            for (int i = 0; i < STEPS; i++) {
                try {
                    Thread.sleep(stepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (Pin pin : positionalPins) {
                    pin.x += deltaL;
                    pin.y += deltaH;
                }
                for (Pin pin : segmentPins) {
                    pin.x += deltaL;
                    pin.y += deltaH;
                }
                for (Pin pin : surfacePinsPins) {
                    pin.x += deltaL;
                    pin.y += deltaH;
                }

            }
        }).start();
    }
}
