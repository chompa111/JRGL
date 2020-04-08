package tranformations;

import elementary.Gobject;
import elementary.Pin;

import java.util.List;

public class TMove extends Transformation {
    double l;
    double h;

    public TMove(double l, double h) {
        this.l = l;
        this.h = h;
    }

    @Override
    public void transform(Gobject go, int milis) {

        new Thread(() -> {

            int stepTime = milis / STEPS;

            List<Pin> positionalPins = go.getPositionalPins();
            List<Pin> segmentPins = Pin.getPinsFromSegments(go.getSegments());
            List<Pin> surfacePinsPins = go.getSurfacePins();

            double aL=(4*l)/(2*STEPS+(STEPS*STEPS));
            double aH=(4*h)/(2*STEPS+(STEPS*STEPS));
            double deltaL = 0;
            double deltaH = 0;
            for (int i = 0; i < STEPS/2; i++) {
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
                deltaL+=aL;
                deltaH+=aH;

            }
            for (int i = 0; i < STEPS/2; i++) {
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
                deltaL-=aL;
                deltaH-=aH;

            }
        }).start();
    }

    @Override
    public void set(Gobject go) {

        List<Pin> positionalPins = go.getPositionalPins();
        List<Pin> segmentPins = Pin.getPinsFromSegments(go.getSegments());
        List<Pin> surfacePinsPins = go.getSurfacePins();

            for (Pin pin : positionalPins) {
                pin.x += l;
                pin.y += h;
            }
            for (Pin pin : segmentPins) {
                pin.x += l;
                pin.y += h;
            }
            for (Pin pin : surfacePinsPins) {
                pin.x += l;
                pin.y += h;
            }
    }

    public void transform(List<Pin> pins, int milis) {

        new Thread(() -> {

            int stepTime = milis / STEPS;

            double aL=(4*l)/(2*STEPS+(STEPS*STEPS));
            double aH=(4*h)/(2*STEPS+(STEPS*STEPS));
            double deltaL = 0;
            double deltaH = 0;
            for (int i = 0; i < STEPS/2; i++) {
                try {
                    Thread.sleep(stepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (Pin pin : pins) {
                    pin.x += deltaL;
                    pin.y += deltaH;
                }

                deltaL+=aL;
                deltaH+=aH;
            }
            for (int i = 0; i < STEPS/2; i++) {
                try {
                    Thread.sleep(stepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (Pin pin : pins) {
                    pin.x += deltaL;
                    pin.y += deltaH;
                }
                deltaL-=aL;
                deltaH-=aH;

            }
        }).start();
    }
}
