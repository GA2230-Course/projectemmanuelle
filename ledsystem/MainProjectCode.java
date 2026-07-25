package ledsystem;

import ledsystem.ledssim.LedSim;
import ledsystem.ledssim.LedStrip;

import java.awt.Color;

public class MainProjectCode {
    public static void main(String[] args) throws InterruptedException {
//ADD TEN LED STRIPS
        LedSim strip = LedSim.createRows(10);
        LedController controller = new LedController(strip);

        Animation redSolid = new TimerAnimation(new SolidAnimation(Color.RED),3.0);
        Animation Blinkanimation= new TimerAnimation(new BlinkAnimation(Color.BLUE, Color.CYAN), 8.0);
        Animation Fadeanimationcode = new TimerAnimation(new FadeAnimation(Color.GRAY, Color.GREEN), 10.0);
//CHECKS EACH ANIMATION EVERY TIME THEIR DURATION SECONDS HAS FINISHED
        Animation masterSequence = new SequentialAnimationGroup(redSolid,Blinkanimation,Fadeanimationcode);


        controller.addAnimation(masterSequence);
        System.out.println(" LED ");
//THIS WILL MAKE THE RUN GO 30MS
        while (true) {
            controller.tickNextFrame();
            strip.apply();
            Thread.sleep(30);
        }
    }
}
