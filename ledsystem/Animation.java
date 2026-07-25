package ledsystem;

import ledsystem.ledssim.LedStrip;

public interface Animation {
    boolean apply(LedStrip strip);
}