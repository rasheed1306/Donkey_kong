import bagel.util.*;
import bagel.*;
import java.util.Properties;


public class Timer {
    private static final int START_TIME = 166;
    private final Font font;
    private final int MAX_FRAMES;
    private final static int FRAMES_PER_SECOND = 60;
    private final Point timerPosition;


    protected int remainingTime;
    private int currentFrame;

    public Timer(Properties gameProps) {
        font = new Font(gameProps.getProperty("font"), Integer.parseInt(gameProps.getProperty("gamePlay.score.fontSize")));
        MAX_FRAMES = Integer.parseInt(gameProps.getProperty("gamePlay.maxFrames"));
        timerPosition = new Point( Double.parseDouble(gameProps.getProperty("gamePlay.score.x")), Double.parseDouble(gameProps.getProperty("gamePlay.score.y")) + 30);
    }

    public void updateTimer() {
        currentFrame++;
    }

    public int getRemainingTime() {
        remainingTime = ((MAX_FRAMES - currentFrame) / FRAMES_PER_SECOND);
        remainingTime = Math.max(remainingTime, 0);
        return remainingTime;
    }

    public void renderTimer() {
        remainingTime = getRemainingTime();
        font.drawString("Time Left " + remainingTime, timerPosition.x, timerPosition.y);
    }


}
