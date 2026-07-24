package ledsystem;

import ledsystem.ledssim.LedStrip;
import ledsystem.utils.StopWatch;
import java.util.Objects;

public class SequentialAnimationGroup implements Animation {
    private final Animation[] animations;
    private final double durationPerAnimation;
    private final StopWatch stepTimer;
    private int currentIndex;
    private boolean isStarted;
    // DOWN HERE YOU HAVE ALL THE ILLEGALARGUMENTSEXCEPTIONS THAT TELL WHAT YOU DONT WANT TO HAPPEND,
    // ALSO IT SHOWS WHAT THE MAINPROECT SHOULD RUN
    public SequentialAnimationGroup(double durationPerAnimation, Animation... animations) {
        Objects.requireNonNull(animations, "Animations array cannot be null");
        if (animations.length == 0) {
            throw new IllegalArgumentException("Must provide at least one animation");
        }
        if (durationPerAnimation <= 0.0) {
            throw new IllegalArgumentException("Duration per step must be greater than zero");
        }
        for (Animation anim : animations) {
            Objects.requireNonNull(anim, "Animation elements cannot be null");
        }
        this.animations = animations;
        this.durationPerAnimation = durationPerAnimation;
        this.currentIndex = 0;
        this.stepTimer = new StopWatch();
        this.isStarted = false;
    }
    // WE WANT TO CHECK HERE THAT THE STRIPS ARENT 0
    @Override
    public void apply(LedStrip strip) {
        Objects.requireNonNull(strip, "LED strip cannot be null");
        if (currentIndex >= animations.length) {
            return;
        }
        if (!isStarted) {
            stepTimer.start();
            isStarted = true;
        }
        // THIS BASICALLY GOES TO THE NEXT "i" IN THE ANIMATION ARRAY
        animations[currentIndex].apply(strip);
        if (stepTimer.get() >= durationPerAnimation) {
            currentIndex++;
            stepTimer.start();
        }
    }
}