package tranformations;

import basics.Line;
import elementary.Gobject;
import elementary.Pin;
import elementary.Segment;

import java.util.ArrayList;
import java.util.List;

public class TConvert extends Transformation {

    Gobject toGobject;
    public TConvert(Gobject toGobject) {
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

        List<Segment> fromSeg=go.getSegments();
        List<Segment> toSeg=toGobject.getSegments();

        fromSeg.sort((seg1,seg2)->{
            Double V1=seg1.p1.x+seg1.p1.y+seg1.p2.x+seg1.p2.y;
            Double V2=seg2.p1.x+seg2.p1.y+seg2.p2.x+seg2.p2.y;
            return V1.compareTo(V2);
        });
        toSeg.sort((seg1,seg2)->{
            Double V1=seg1.p1.x+seg1.p1.y+seg1.p2.x+seg1.p2.y;
            Double V2=seg2.p1.x+seg2.p1.y+seg2.p2.x+seg2.p2.y;
            return V1.compareTo(V2);
        });
        List<Pin> fromPins= Pin.getPinsFromSegments(fromSeg);
        List<Pin> toPins= Pin.getPinsFromSegments(toSeg);

        int size= Math.min(fromPins.size(), toPins.size());

        List<Double> acelerationX=new ArrayList<>(size);
        List<Double> acelerationY=new ArrayList<>(size);

        for(int i=0;i<size;i++){
            acelerationX.add(0d);
            acelerationY.add(0d);
        }

        for(int i=0;i<size;i++){
            acelerationX.set(i,(4*(toPins.get(i).x-fromPins.get(i).x))/(2*STEPS+(STEPS*STEPS)));
            acelerationY.set(i,(4*(toPins.get(i).y-fromPins.get(i).y))/(2*STEPS+(STEPS*STEPS)));
        }

        new Thread(() -> {

            int stepTime = milis / STEPS;
            for (int i = 0; i < STEPS/2; i++) {
                try {
                    Thread.sleep(stepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int j=0;j<size;j++){
                    fromPins.get(j).x+=i*acelerationX.get(j);
                    fromPins.get(j).y+=i*acelerationY.get(j);
                }
            }

            for (int i = 0; i < STEPS/2; i++) {
                try {
                    Thread.sleep(stepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int j=0;j<size;j++){
                    fromPins.get(j).x+=((STEPS/2)-i)*acelerationX.get(j);
                    fromPins.get(j).y+=((STEPS/2)-i)*acelerationY.get(j);
                }
            }

        }).start();
    }
}
