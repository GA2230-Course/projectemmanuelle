package ledsystem.utils;

import java.awt.Color;

public class ColorUtils {
    /**
     * Clamps a value between a minimum and maximum.
     * This is useful for ensuring that a value stays within a specific range,
     * especially when dealing with color values or other bounded quantities.
     *
     * @param min The minimum value.
     * @param max The maximum value.
     * @param v   The value to clamp.
     * @return The clamped value.
     */
    public static double clamp(double min, double max, double v) {
        return Math.min(max, Math.max(min, v));
    }

    /**
     * lerps between two values based on a factor.
     * @param a The start value.
     * @param b The end value.
     * @param t The factor (0.0 to 1.0).
     * @return The interpolated value.
     */
    private static double lerp(double a, double b, double t) {
        return a + (b - a) * t;
    }

    /**
     * Linearly interpolates between two colors.
     * This is useful for creating gradients or smooth transitions between colors.
     * The interpolation factor `t` should be between 0.0 and 1.0,
     * where 0.0 corresponds to the first color and 1.0 corresponds to the second color.
     *
     * @param c1 The first color.
     * @param c2 The second color.
     * @param t  The interpolation factor (0.0 to 1.0).
     * @return The interpolated color.
     */
    public static Color lerp(Color c1, Color c2, double t) {
        return new Color(
                (float) lerp(c1.getRed() / 255.0, c2.getRed() / 255.0, t),
                (float) lerp(c1.getGreen() / 255.0, c2.getGreen() / 255.0, t),
                (float) lerp(c1.getBlue() / 255.0, c2.getBlue() / 255.0, t));
    }
}