import bagel.util.*;
import bagel.*;
import java.util.Properties;


public class Timer {
    private final Font font;
    private final int MAX_FRAMES;
    private final static int FRAMES_PER_SECOND = 60;
    private final Point timerPosition;


    private int remainingTime;
    private int currentFrame;
    protected boolean isGameOver = false;

    public Timer(Properties gameProps) {
        font = new Font(gameProps.getProperty("font"), Integer.parseInt(gameProps.getProperty("gamePlay.score.fontSize")));
        MAX_FRAMES = Integer.parseInt(gameProps.getProperty("gamePlay.maxFrames"));
        timerPosition = new Point( Double.parseDouble(gameProps.getProperty("gamePlay.score.x")), Double.parseDouble(gameProps.getProperty("gamePlay.score.y")) + 30);
    }

    public void updateTimer() {
        if (!isGameOver && currentFrame < MAX_FRAMES)
            currentFrame++;
    }

    public int getRemainingTime() {
        remainingTime = ((MAX_FRAMES - currentFrame) / FRAMES_PER_SECOND);
        remainingTime = Math.max(remainingTime, 0);

        if (remainingTime == 0) {
            isGameOver = true;
        }
        return remainingTime;
    }

    public void renderTimer() {
        remainingTime = getRemainingTime();
        font.drawString("Time Left " + remainingTime, timerPosition.x, timerPosition.y);
    }

    public void resetTimer() {
        currentFrame = 0;
        isGameOver = false;
    }

    public int getEndTime() {
        if (isGameOver) {
            return remainingTime;
        } else {
            return 0;
        }
    }

}
