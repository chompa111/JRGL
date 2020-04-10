package basics;

import elementary.Gobject;
import elementary.Scene;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class GroupGobject extends Gobject {

    public GroupGobject(Gobject... gobjects) {
        chields.addAll(Arrays.asList(gobjects));
    }
    public GroupGobject(List gobjects) {
        chields.addAll(gobjects);
    }

    public void add(Gobject... gobjects){
        chields.addAll(Arrays.asList(gobjects));
    }

    public void add(Collection<Gobject> gobjects){
        chields.addAll(gobjects);
    }


    public void unviculate(Scene scene) {
        super.unviculate();
        for(Gobject gobject:chields){
            if(gobject instanceof GroupGobject){
                ((GroupGobject) gobject).unviculate(scene);
            }else {
                gobject.unviculate();
                scene.remove(gobject);
            }
        }
    }

}
