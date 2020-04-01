package tranformations;

import basics.Line;
import elementary.ColorHolder;
import elementary.Gobject;
import elementary.Pin;
import elementary.Segment;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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


        Pin minPinFromGobject=Pin.midPin(Pin.getPinsFromSegments(fromSeg));
        Pin minPinToGobject=Pin.midPin(Pin.getPinsFromSegments(toSeg));

//        fromSeg.sort((seg1,seg2)->{
//            Double V1=seg1.p1.x+seg1.p1.y+seg1.p2.x+seg1.p2.y;
//            Double V2=seg2.p1.x+seg2.p1.y+seg2.p2.x+seg2.p2.y;
//            return V1.compareTo(V2);
//        });
//        toSeg.sort((seg1,seg2)->{
//            Double V1=seg1.p1.x+seg1.p1.y+seg1.p2.x+seg1.p2.y;
//            Double V2=seg2.p1.x+seg2.p1.y+seg2.p2.x+seg2.p2.y;
//            return V1.compareTo(V2);
//        });
        fromSeg.sort(Comparator.comparing(seg -> ang(minPinFromGobject, Pin.midPin(seg.p1, seg.p2))));
        toSeg.sort(Comparator.comparing(seg -> ang(minPinToGobject, Pin.midPin(seg.p1, seg.p2))));
        //fromSeg.sort(Comparator.comparing(Object::hashCode));

        List<ColorHolder> colors=fromSeg.stream().map(segment ->segment.color).collect(Collectors.toList());

        List<Double> acelerationRed=new ArrayList<>();
        List<Double> acelerationGreen=new ArrayList<>();
        List<Double> acelerationBlue=new ArrayList<>();

        for(int i=0;i<colors.size();i++){
            acelerationRed.add((4*(toSeg.get(i).color.color.getRed()-colors.get(i).color.getRed()+0.0))/(2*STEPS+(STEPS*STEPS)));
            acelerationGreen.add((4*(toSeg.get(i).color.color.getGreen()-colors.get(i).color.getGreen()+0.0))/(2*STEPS+(STEPS*STEPS)));
            acelerationBlue.add((4*(toSeg.get(i).color.color.getBlue()-colors.get(i).color.getBlue()+0.0))/(2*STEPS+(STEPS*STEPS)));
        }

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

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
            for (int i = 0; i <= STEPS/2; i++) {
                try {
                    Thread.sleep(stepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int j=0;j<size;j++){
                    fromPins.get(j).x+=i*acelerationX.get(j);
                    fromPins.get(j).y+=i*acelerationY.get(j);
                }
                for(int j=0;j<size/2;j++){
                    colors.get(j).change(i*acelerationRed.get(j),i*acelerationGreen.get(j),i*acelerationBlue.get(j));
                }
            }

            for (int i = 0; i <= STEPS/2; i++) {
                try {
                    Thread.sleep(stepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int j=0;j<size;j++){
                    fromPins.get(j).x+=((STEPS/2)-i)*acelerationX.get(j);
                    fromPins.get(j).y+=((STEPS/2)-i)*acelerationY.get(j);

                }
                for(int j=0;j<size/2;j++){
                    colors.get(j).change(((STEPS/2)-i)*acelerationRed.get(j),((STEPS/2)-i)*acelerationGreen.get(j),((STEPS/2)-i)*acelerationBlue.get(j));
                }
            }
            //resolving aproxproblems
            try {
                Thread.sleep(stepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for(int i=0;i<size;i++){
                fromPins.get(i).x=toPins.get(i).x;
                fromPins.get(i).y=toPins.get(i).y;
            }

        }).start();
    }



    Double ang(Pin p1, Pin p2){
        double deltay=p1.y-p2.y;
        double deltax=-(p1.x-p2.x)+0.0002;

        double ratio=(deltay/deltax);
        if(deltax>=0 && deltay>0)
            return Math.atan(ratio);
        if(deltax<=0 && deltay>0)
            return (Math.PI+Math.atan(ratio));
        if(deltax<=0 && deltay<0)
            return (Math.PI+Math.atan(ratio));
        if(deltax>=0 && deltay<0)
            return ((Math.PI*2)+Math.atan(ratio));
       return 0d;
    }



}
