package elementary;

import basics.Rectangle;
import tranformations.Transformation;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Gobject {


    public Gobject dad;
    public boolean complexForm=false;
    long id;

    public List<Gobject> chields= new ArrayList<>();

    public List<Pin> getPositionalPins(){
        List<Pin> pins= new ArrayList<>();
        this.fillPositinalPins(pins);
        return pins;
    }
    protected void fillPositinalPins(List<Pin> pins){
        for(Gobject chield:this.chields){
           chield.fillPositinalPins(pins);
        }
    }

    public List<Segment> getSegments(){
        List<Segment> segments= new ArrayList<>();
        fillSegments(segments);
        return segments;
    }
    public void fillSegments(List<Segment> segments){
        for(Gobject chield:this.chields){
            chield.fillSegments(segments);
        }
    }

    public List<Pin> getSurfacePins(){
        List<Pin> pins= new ArrayList<>();
        fillSurfacePins(pins);
        return pins;
    }
    public void fillSurfacePins(List<Pin> pins){
        for(Gobject chield:this.chields){
            chield.fillSurfacePins(pins);
        }
    }


    public void paint(Graphics g){
        List<Gobject> gobjects = this.chields;
        for (int i = 0; i < gobjects.size(); i++) {
             gobjects.get(i).paint(g);
        }
    }

    public void transform(Transformation transformation,int milis){
        transformation.transform(this,milis);
    }
    public void transform(Transformation transformation){
        transformation.transform(this);
    }
    public void transformChields(Transformation transformation){
        for(Gobject chield:chields){
            chield.transform(transformation);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void transformChields(Transformation transformation,int milis){
        new Thread(()->{
            for(Gobject chield:chields){
                chield.transform(transformation,milis);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void genericDecompose(int nParts){
        int nchield=this.chields.size();
        int ratio=(int)((nParts+0.0)/(nchield+0.0));
        if(chields.size()>0) {
            int remainder = nParts % nchield;

            for (int i = 0; i < remainder; i++) {
                chields.get(i).decompose(ratio + 1);
            }
            for (int i = remainder; i < nchield; i++) {
                chields.get(i).decompose(ratio);
            }
        }
    }

    public void decompose(int nParts){
        genericDecompose(nParts);
    }

    public void getsimpleBack() {
        complexForm = false;
        for (Gobject chield : this.chields) {
            chield.getsimpleBack();
        }
    }
    public int disassemble(){
        int sum=0;
        for (Gobject chield : this.chields) {
            sum+=chield.disassemble();
        }
        return sum;
    }
    public ArrayList<ColorHolder> getColors(){
        ArrayList<ColorHolder> colors=new ArrayList<>();
        fillColors(colors);
        return colors;
    }

    public void fillColors(List<ColorHolder> colors){
        for (Gobject chield : this.chields) {
            chield.fillColors(colors);
        }
    }

    public void init(){
        new Thread(()-> {
            for(Gobject chield: chields){
                chield.init();
            }
        }).start();
    }

    public Pin midPin(){
        List<Pin> positionalPins =getPositionalPins();
        List<Pin> segmentPins = Pin.getPinsFromSegments(getSegments());
        List<Pin> surfacePinsPins = getSurfacePins();

        List<Pin> allPins= new ArrayList<>();
        allPins.addAll(positionalPins);
        allPins.addAll(segmentPins);
        allPins.addAll(surfacePinsPins);

        return Pin.midPin(allPins);
    }

    public Rectangle getBorders(){
        List<Pin> positionalPins =getPositionalPins();
        List<Pin> segmentPins = Pin.getPinsFromSegments(getSegments());
        List<Pin> surfacePinsPins = getSurfacePins();

        List<Pin> allPins= new ArrayList<>();
        allPins.addAll(positionalPins);
        allPins.addAll(segmentPins);
        allPins.addAll(surfacePinsPins);

        Pin minimalPin=Pin.minPin(allPins);
        Pin maxPin=Pin.maxPin(allPins);

        return new Rectangle(minimalPin.x,minimalPin.y,maxPin.x,maxPin.y,Color.orange);
    }

    public void addChield(Gobject gobject){
        gobject.dad=this;
        chields.add(gobject);
    }
    public void addChield(Gobject... gobjects){
        for (Gobject gobject:gobjects){
            gobject.dad=this;
        }
        chields.addAll(Arrays.asList(gobjects));
    }

    public void unviculate(){
        if(dad!=null){
            dad.chields.remove(this);
        }
    }

}
