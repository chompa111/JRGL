package external.functions;

import basics.Rectangle;
import elementary.Gobject;
import elementary.Pin;
import elementary.Scene;
import saveStateObjects.ColorSave;
import tranformations.TColor;
import tranformations.TMove;

import java.awt.*;

public class Position {
    Scene scene;
    public Position(Scene scene){
        this.scene=scene;
    }

    //subtitle

   public  void setSubtitle(Gobject gobject, Gobject subtitle){

        Rectangle borderGobject=gobject.getBorders();
        Rectangle borderSubtitle=subtitle.getBorders();

        double gobH=borderGobject.p2.y-borderGobject.p1.y;
        Pin gobCenter=Pin.midPin(borderGobject.p1,borderGobject.p2);

        double subH=borderSubtitle.p2.y-borderSubtitle.p1.y;
        Pin subCenter=Pin.midPin(borderSubtitle.p1,borderSubtitle.p2);

        Pin finalPosition=new Pin(gobCenter.x,gobCenter.y+(gobH/2)+(subH/2)+20);

        double amountx=finalPosition.x-subCenter.x;
        double amounty=finalPosition.y-subCenter.y;

        new TMove(amountx,amounty).set(subtitle);
        gobject.addChield(subtitle);

    }

    public  void addSubtitle(Gobject gobject, Gobject subtitle){

        ColorSave colorSave=ColorSave.getSaveFrom(subtitle);
        new TColor(new Color(0,0,0,0)).set(subtitle);

        Rectangle borderGobject=gobject.getBorders();
        Rectangle borderSubtitle=subtitle.getBorders();


        double gobH=borderGobject.p2.y-borderGobject.p1.y;
        Pin gobCenter=Pin.midPin(borderGobject.p1,borderGobject.p2);

        double subH=borderSubtitle.p2.y-borderSubtitle.p1.y;
        Pin subCenter=Pin.midPin(borderSubtitle.p1,borderSubtitle.p2);

        Pin finalPosition=new Pin(gobCenter.x,gobCenter.y+(gobH/2)+(subH/2)+20);

        double amountx=finalPosition.x-subCenter.x;
        double amounty=finalPosition.y-subCenter.y;

        new TMove(amountx,amounty).set(subtitle);
        gobject.addChield(subtitle);
        colorSave.transformBack(1000);

    }

    public void setMid(Gobject gobject1, Gobject gobject2){

        Pin gobject1Mid=gobject1.midPin();
        Pin gobject2Mid=gobject2.midPin();
        new TMove(gobject1Mid.x-gobject2Mid.x,gobject1Mid.y-gobject2Mid.y).set(gobject2);
    }


}
