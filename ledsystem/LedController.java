package ledsystem;

import ledsystem.ledssim.LedStrip;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LedController {
    private final LedStrip strip;
    private final List<Animation> animations;
    private int currentAnimationIndex;

    public LedController(LedStrip strip) {

        this.strip = Objects.requireNonNull(strip, "LED strip layout cant be null");
        this.animations = new ArrayList<>();
        this.currentAnimationIndex = 0;
    }

    public void addAnimation(Animation animation) {
        this.animations.add(Objects.requireNonNull(animation, "Cant add a null animation instance"));
    }

    public void tickNextFrame() {
        if (this.animations.isEmpty()) {
            return;
        }

        Animation current = this.animations.get(currentAnimationIndex);
        current.apply(strip);

    }

    public LedStrip getStrip() {
        return this.strip;
    }
}
