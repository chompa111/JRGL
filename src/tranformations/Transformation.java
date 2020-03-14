package tranformations;

import elementary.Gobject;

public abstract class Transformation {

    public static final int STEPS=100;

    public void transform(Gobject go){
        transform(go,500);
    }
     public abstract void transform(Gobject go, int milis);
}