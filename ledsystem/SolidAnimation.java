package ledsystem;

import ledsystem.ledssim.LedStrip;
import java.awt.Color;
import java.util.Objects;

public class SolidAnimation implements Animation {
    private final Color color;

    public SolidAnimation(Color color) {
        this.color = Objects.requireNonNull(color, "Solid color cant be null");
    }
// THE RETURN FALSE TREATS THE ANIMATION AS A "THE SOLID ANIMATION STILL NOT FINISHED RUNNING"
    @Override
    public boolean apply(LedStrip strip) {
        Objects.requireNonNull(strip, "LED strip cant be null");
        strip.setAll(this.color);
        return false;
    }
}