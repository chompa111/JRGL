package elementary;

import tranformations.Transformation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Gobject {

    public boolean complexForm=false;

    public List<Gobject> chields= new ArrayList<>();
    public List<Gobject> dads= new ArrayList<>();

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

    public List<Pin> getSegmentPins(){
        List<Pin> pins= new ArrayList<>();
        fillSegmentPins(pins);
        return pins;
    }
    protected void fillSegmentPins(List<Pin> pins){
        for(Gobject chield:this.chields){
            chield.fillSegmentPins(pins);
        }
    }

    public List<Pin> getSurfacePins(){
        List<Pin> pins= new ArrayList<>();
        fillSurfacePins(pins);
        return pins;
    }
    protected void fillSurfacePins(List<Pin> pins){
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

    public void genericMill(int nParts){
        int nchield=this.chields.size();
        int ratio=(int)(nParts+0.0/nchield+0.0);
        int remainder=nParts%nchield;

        for(int i=0;i<remainder;i++){
            chields.get(i).mill(ratio+1);
        }
        for(int i=remainder;i<nchield;i++){
            chields.get(i).mill(ratio);
        }
    }

    public  void mill(int nParts){
        genericMill(nParts);
    }

    public void getsimpleBack() {
        complexForm = false;
        for (Gobject chield : this.chields) {
            chield.getsimpleBack();
        }
    }
    public void disassemble(){
        for (Gobject chield : this.chields) {
            chield.disassemble();
        }
    }
}
