package complex.other.Text;

import basics.GroupGobject;
import basics.Rectangle;
import complex.other.Text.Gchar;
import elementary.Gobject;
import elementary.Pin;
import tranformations.TMove;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GText extends Gobject {
    static Font font;
    static{
        GraphicsEnvironment ge =
                GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
           font= Font.createFont(Font.TRUETYPE_FONT, new File("src/source/cmunbi.ttf"));
            ge.registerFont(font);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    Pin basePin;

    @Override
    protected void fillPositinalPins(List<Pin> pins) {
        super.fillPositinalPins(pins);
        pins.add(basePin);
    }
    public  GText(String s, int size) {
        double xBasis=10000;
        double y=10000;
        basePin= new Pin(10000,10000);
        for(Character character: s.toCharArray()){
            Gchar gchar=new Gchar(character,xBasis,y,size);
            addChield(gchar);
            xBasis+=gchar.getBounds().width+2;
        }
    }
    public GText(String s, double x, double y, int size) {
        basePin= new Pin(x,y);

        double xBasis=x;
        for(Character character: s.toCharArray()){
            Gchar gchar=new Gchar(character,xBasis,y,size);
            addChield(gchar);
            xBasis+=gchar.getBounds().width+2;
        }
    }
    public GText(String s, double x, double y, int size,Color color) {
        basePin= new Pin(x,y);

        double xBasis=x;
        for(Character character: s.toCharArray()){
            Gchar gchar=new Gchar(character,xBasis,y,size,color);
            addChield(gchar);
            xBasis+=gchar.getBounds().width+2;
        }
    }

    @Override
    public Rectangle getBorders() {
        return new GroupGobject(chields.stream().map(Gobject::getBorders).collect(Collectors.toList())).getBorders();
    }

    public void append(GText text){
        Rectangle thisBorder=this.getBorders();
        Rectangle newTextBorder=text.getBorders();

        double deltax=basePin.x+(thisBorder.p2.x-thisBorder.p1.x) -text.basePin.x+2;
        double deltay=basePin.y-text.basePin.y;

        new TMove(deltax,deltay).set(text);
        addChield(text);
    }

    public void reformat(){
        Gobject firstChiel=chields.get(0);

        for(int i=1;i<chields.size();i++){
            Rectangle thisBorder=firstChiel.getBorders();
            Rectangle newTextBorder=chields.get(i).getBorders();
            double deltax=0;
            double deltay=0;
            if(chields.get(i) instanceof GText){
                deltax=basePin.x+(thisBorder.p2.x-thisBorder.p1.x) -((GText)chields.get(i)).basePin.x+2;
                deltay=basePin.y-((GText)chields.get(i)).basePin.y;
            }else{
                deltax=basePin.x+(thisBorder.p2.x-thisBorder.p1.x) -((Gchar)chields.get(i)).centerPin.x+2;
                deltay=basePin.y-((Gchar)chields.get(i)).centerPin.y;
            }

            new TMove(deltax,deltay).transform(firstChiel,500);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            firstChiel=chields.get(i);
        }
    }
}
