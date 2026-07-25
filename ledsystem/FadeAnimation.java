package ledsystem;

import ledsystem.ledssim.LedStrip;
import ledsystem.utils.ColorUtils;
import ledsystem.utils.StopWatch;
import java.awt.Color;
import java.util.Objects;

public class FadeAnimation implements Animation {
    private final Color startColor;
    private final Color endColor;
    private final StopWatch progressTimer;
    private boolean goingForward;
//CONTINUING TO HERE WILL THE ARGUMENTS AND WHAT THE FADEANIMATION WANTS TO SHOW WHILE THE ANIMATIONS ARE RUNNING
    public FadeAnimation(Color startColor, Color endColor) {
        this.startColor = Objects.requireNonNull(startColor, "Start color cantt be null");
        this.endColor = Objects.requireNonNull(endColor, "End color cant be null");
        this.goingForward = true;
        this.progressTimer = new StopWatch();
        this.progressTimer.start();
    }
//CHANGES EVERY TWO SECONDS THE COLOR OF THE STRIP WHILE IT HAS A SPECIFIC DURATION TIME
    @Override
    public boolean apply(LedStrip strip) {
        Objects.requireNonNull(strip, "LED strip cant be null");
        if (progressTimer.get() >= 2.0) {
            goingForward = !goingForward;
            progressTimer.start();
        }

        double t = progressTimer.get() / 2.0;
        t = ColorUtils.clamp(0.0, 1.0, t);
        if (!goingForward) {
            t = 1.0 - t;
        }

        Color blendedColor = ColorUtils.lerp(startColor, endColor, t);
        strip.setAll(blendedColor);
        return false;
    }
}