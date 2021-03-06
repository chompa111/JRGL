package Teste;

import basics.*;
import basics.Rectangle;
import elementary.Scene;
import complex.other.Text.GText;
import tranformations.TColor;
import tranformations.TConvert;
import tranformations.TMove;

import java.awt.*;

public class Texto extends Scene {

    @Override
    public void execute() {
  //    add(new FPSmesurer(100,100));
      GText textoBase=new GText("f(x)=",150,450,50);
      GText  maldito=new GText("sin(x)+tan(i)",50);
      GText apendice= new GText("+cos(z)",50);
      textoBase.append(maldito);
      textoBase.append(apendice);
      include(textoBase);
      p(1000);
  //    convert(textoBase.chields.get(0),new GText("g",150,450,50),300);

      p(3000);
//        add(textoBase.getBorders());
//        textoBase.chields.stream().forEach(c->add(c.getBorders()));
        p(250);
        maldito.transform(new TColor(Color.magenta),1500);
        maldito.transform( new TMove(0,250),1500);
        p(3000);
       // textoBase.chields.remove(maldito);
        //textoBase.reformat();
        GroupGobject gg= new GroupGobject();
        gg.add(new Circle(450,450,120,Color.orange));
        gg.add(new Line(450,310,450,600,Color.white));
        gg.add(new Line(310,450,600,450,Color.white));

        GroupGobject numeros=new GroupGobject();

        GText noventa= new GText("90°",435,305,30,Color.white);
        GText zero= new GText("0°",610,450,30,Color.white);
        GText centoEoitenta=new GText("180°",250,450,30,Color.white);
        GText duzentosEsetenta=new GText("270°",420,630,30,Color.white);

        numeros.add(noventa);
        numeros.add(zero);
        numeros.add(centoEoitenta);
        numeros.add(duzentosEsetenta);

        convert(textoBase,gg,1200);
        include(numeros);

        GroupGobject tudo= new GroupGobject();
        tudo.add(numeros,gg);
        p(2200);
      tudo.transform(new TMove(-200,-200),700);
      p(1000);
      tudo.transform(new TMove(200,200),700);
      p(1000);
      emphasize(tudo);
      p(1000);
     takeOut(tudo);

    }

    public static void main(String[] args) {
        new Texto();
    }
}
