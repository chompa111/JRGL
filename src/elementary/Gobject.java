package elementary;

import tranformations.Transformation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Gobject {

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
        for(Gobject child: this.chields){
            child.paint(g);
        }
    }

    public void transform(Transformation transformation,int milis){
        transformation.transform(this,milis);
    }
    public void transform(Transformation transformation){
        transformation.transform(this);
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
    public ArrayList<Color> getColors(){
        ArrayList<Color> colors=new ArrayList<>();
        fillColors(colors);
        return colors;
    }

    public void fillColors(List<Color> colors){
        for (Gobject chield : this.chields) {
            chield.fillColors(colors);
        }
    }

}
