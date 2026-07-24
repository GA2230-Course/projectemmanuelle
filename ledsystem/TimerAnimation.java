package ledsystem;

import ledsystem.ledssim.LedStrip;
import ledsystem.utils.StopWatch;
import java.util.Objects;

public class TimerAnimation implements Animation{
    private final Animation wrappedAnimation;
    private final double durationSeconds;
    private final StopWatch timer;
    private boolean isstarted;
// HERE ARE THE ARGUMENTS YOU DONT WANT THEM TO HAPPEN LIKE THE DURATION MUST BE ABOVE 0
// AND THAT THE LEDSTRIPS CANT BE 0
    public TimerAnimation(Animation wrappedAnimation, double durationSeconds) {
        this.wrappedAnimation = Objects.requireNonNull(wrappedAnimation, "Wrapped animation cannot be null");
        if (durationSeconds <= 0.0) {
            throw new IllegalArgumentException("Duration must be better than zero: " + durationSeconds);
        }
        this.durationSeconds = durationSeconds;
        this.timer = new StopWatch();
        this.isstarted=false;
    }

    @Override
    public void apply(LedStrip strip) {
        Objects.requireNonNull(strip, "LED strip cannot be null");
        if(!isstarted) {
            timer.start();
            isstarted = true;
        }
        if (timer.get() < durationSeconds) {
            wrappedAnimation.apply(strip);
        }

    }
}