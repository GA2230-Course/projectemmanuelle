package ledsystem;

import ledsystem.ledssim.LedStrip;
import ledsystem.utils.StopWatch;
import java.util.Objects;

public class SequentialAnimationGroup implements Animation {
    private final Animation[] animations;
    private final StopWatch stepTimer;
    private int currentIndex;
    private boolean isStarted;
    // DOWN HERE YOU HAVE ALL THE ILLEGALARGUMENTSEXCEPTIONS THAT TELL WHAT YOU DONT WANT TO HAPPEND,
    // ALSO IT SHOWS WHAT THE MAINPROECT SHOULD RUN
    public SequentialAnimationGroup(Animation... animations) {
        Objects.requireNonNull(animations, "Animations array cannot be null");
        if (animations.length == 0) {
            throw new IllegalArgumentException("Must provide at least one animation");
        }
        for (Animation anim : animations) {
            Objects.requireNonNull(anim, "Animation elements cannot be null");
        }
        this.animations = animations;
        this.currentIndex = 0;
        this.stepTimer = new StopWatch();
        this.isStarted = false;
    }
    // WE WANT TO CHECK HERE THAT THE STRIPS ARENT 0
    @Override
    public boolean apply(LedStrip strip) {
        Objects.requireNonNull(strip, "LED strip cannot be null");
        if (currentIndex >= animations.length) {
            return true;
        }

        // THIS BASICALLY GOES TO THE NEXT "i" IN THE ANIMATION ARRAY
        boolean current= animations[currentIndex].apply(strip);
        if (current){
            currentIndex++;


        }
        return currentIndex>= animations.length;
    }
}