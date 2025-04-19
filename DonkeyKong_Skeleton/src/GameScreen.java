import bagel.*;

import java.util.Arrays;
import java.util.Properties;
import bagel.util.*;

public class GameScreen {
    private final Platform platform;
    private final Barrel barrels;
    private final Ladder ladder;
    private final Hammer hammer;
    private final Donkey donkey;
    private final Player player;

    private final Timer timer;
    private final Score score;
    private int scorePoints = 0;
    private final Image background = new Image("res/background.png");

    boolean isOnPlatform;
    protected boolean isRunning;
    protected boolean isScoreAdded;
    protected boolean isWon = false;
    protected boolean isLost = false;

    public GameScreen(Properties gameProps, Properties messageProps) {
        this.platform = new Platform(gameProps);
        this.barrels = new Barrel(gameProps);
        this.ladder = new Ladder(gameProps);
        this.hammer = new Hammer(gameProps);
        this.donkey = new Donkey(gameProps);
        this.player = new Player(gameProps);
        this.score = new Score(gameProps);
        this.timer = new Timer(gameProps);

    }

    public void touchLadder() {
        player.isClimbing = false;
        for (Rectangle ladder : ladder.getObjBounds()) {
            if (ladder.intersects(player.getPlayerBounds())) {
                System.out.println("On ladder");
                player.isClimbing = true;
                break;
            }
        }
    }

    public void touchPlatform() {
        isOnPlatform = false;
        for (Rectangle platform : platform.getObjBounds()) {
            if (platform.intersects(player.getPlayerBounds())) {
                System.out.println("Player intersects platform");
                isOnPlatform = true;
                isScoreAdded = false;
                break;
            }
        }
        player.isJumping = !isOnPlatform;
    }

    public void touchBarrel(Input input) {
        Rectangle[] barrelBounds = barrels.getObjBounds();
        for (int i = 0; i < barrels.objCount; i++) {
            Rectangle barrel = barrels.getObjBounds()[i];
            if (player.getPlayerBounds().intersects(barrel)) {
                if (player.hasHammer) {
                    System.out.println("Barrel " + i + " destroyed");
                    barrels.updateBarrelStatus(true, i);
                } else if (!player.hasHammer) {
                    timer.isGameOver = true;
                    isLost = true;
                    isRunning = false;
                }
            }

        }
    }

    public void touchDonkey() {
        if (player.getPlayerBounds().intersects(donkey.getObjBounds()[0])) {
            System.out.println("Player touches donkey");

            isWon = player.hasHammer;
            isLost = !isWon;

            timer.isGameOver = isWon;
            isRunning = false;
        }
    }

    public int calculateScore() {

        for (int i = 0; i < barrels.objCount; i++) {
            if (barrels.isBarrelDestroyed[i] && !barrels.isBarrelScoreAdded[i]) {
                scorePoints += 100;
                barrels.isBarrelScoreAdded[i] = true;
            }
        }

        for (Rectangle area : barrels.getJumpingBarrelBounds()) {
            if (area.intersects(player.getPlayerBounds()) && player.isJumping) {
                if (!isScoreAdded) {
                    scorePoints += 30;
                    isScoreAdded = true;
                }
            }
        }

        scorePoints += timer.getEndTime();
        return scorePoints;
    }

    public void restartGame() {
        player.restartToStart();
        timer.resetTimer();

        hammer.isHammerHeld = false;
        isWon = false;
        isLost = false;

        Arrays.fill(barrels.isBarrelDestroyed, false);
        scorePoints = 0;
    }

    public void touchHammer() {
        if (player.getPlayerBounds().intersects(hammer.getObjBounds()[0])) {
            System.out.println("Player touched hammer");
            player.hasHammer = true;
            hammer.isHammerHeld = true;
        }
    }

    public void ranOutOfTime() {
        if (timer.isGameOver) {
            isLost = true;
            isRunning = false;
        }
    }

    public void renderScreen(Input input) {
        isRunning = true;
        background.draw(Window.getWidth() / 2., Window.getHeight() / 2.);
        platform.renderObj(input);
        barrels.renderObj(input);
        ladder.renderObj(input);
        hammer.renderObj(input);
        donkey.renderObj(input);
        player.renderPlayer(input);
        score.getScore(calculateScore());
        timer.updateTimer();
        timer.renderTimer();

        touchBarrel(input);
        touchPlatform();
        touchLadder();
        touchHammer();
        touchDonkey();
        ranOutOfTime();

    }
}




