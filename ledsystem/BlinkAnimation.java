package ledsystem;

import ledsystem.ledssim.LedStrip;
import ledsystem.utils.StopWatch;
import java.awt.Color;
import java.util.Objects;

public class BlinkAnimation implements Animation {
    private final Color firstColor;
    private final Color secondColor;
    private final StopWatch blinkTimer;
    private boolean useFirstColor;

    public BlinkAnimation(Color firstColor, Color secondColor) {
        this.firstColor = Objects.requireNonNull(firstColor, "Primary blink color cannot be null");
        this.secondColor = Objects.requireNonNull(secondColor, "Secondary blink color cannot be null");
        this.blinkTimer = new StopWatch();
        this.blinkTimer.start();
        this.useFirstColor = true;
    }
//CHANGES THE COLOR OF THE STRIP EVERY TWO SECONDS WHILE BEING ON A SPECIFIC DURATION TIME
    @Override
    public void apply(LedStrip strip) {
        Objects.requireNonNull(strip, "LED strip cannot be null");
        if (blinkTimer.get() >= 0.2) {
            useFirstColor = !useFirstColor;
            blinkTimer.start();
        }
        strip.setAll(useFirstColor ? firstColor : secondColor);
    }
}