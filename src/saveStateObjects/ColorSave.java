package saveStateObjects;

import elementary.ColorHolder;
import elementary.Gobject;
import tranformations.TColor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ColorSave {
    List<ColorHolder> colorHolders=new ArrayList<>();
    Gobject gobject;

    public static ColorSave getSaveFrom(Gobject gobject) {
        ColorSave colorSave= new ColorSave();
        colorSave.gobject=gobject;
        colorSave.colorHolders=gobject.getColors().stream().map(ColorHolder::new).collect(Collectors.toList());
        return colorSave;
    }

    public void transformBack(){
        TColor.transformLists(gobject.getColors(),colorHolders,1000);
    }
    public void transformBack(int milis){
        TColor.transformLists(gobject.getColors(),colorHolders,milis);
    }
}
