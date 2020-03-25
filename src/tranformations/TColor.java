package tranformations;

import elementary.Gobject;

import java.awt.*;
import java.util.List;

public class TColor extends Transformation {
    @Override
    public void transform(Gobject go, int milis) {
        List<Color> colors=go.getColors();
    }
}
