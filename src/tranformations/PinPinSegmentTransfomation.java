package tranformations;

import elementary.Gobject;
import elementary.Pin;
import elementary.Segment;
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

        List<Segment> fromSeg=go.getSegmentPins();
        List<Segment> toSeg=toGobject.getSegmentPins();

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
        List<Pin> fromPins= new ArrayList<>();
        List<Pin> toPins= new ArrayList<>();;

        for (Segment segment:fromSeg){
            fromPins.add(segment.p1);
            fromPins.add(segment.p2);
        }

        for (Segment segment:toSeg){
            toPins.add(segment.p1);
            toPins.add(segment.p2);
        }




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
