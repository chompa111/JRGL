package basics;

import elementary.Gobject;

import java.util.Arrays;

public class GroupGobject extends Gobject {

    public GroupGobject(Gobject... gobjects) {
        chields.addAll(Arrays.asList(gobjects));
    }

}
