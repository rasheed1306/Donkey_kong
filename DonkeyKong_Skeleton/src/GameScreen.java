import bagel.*;
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
        for (Rectangle ladder : ladder.getLadderBounds()) {
            if (ladder.intersects(player.getPlayerBounds())) {
                System.out.println("On ladder");
                player.isClimbing = true;
                break;
            }
        }
    }

    public void touchPlatform() {
        isOnPlatform = false;
        for (Rectangle platform : platform.getPlatformBounds()) {
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
        Rectangle[] barrelBounds = barrels.getBarrelBounds();
        for (int i = 0; i < barrels.barrelCount; i++) {
            Rectangle barrel = barrelBounds[i];
            if (player.getPlayerBounds().intersects(barrel)) {
                if (player.hasHammer) {
                    System.out.println("Barrel " + i + " destroyed");
                    barrels.barrelDestroyed[i] = true;
                } else if (!player.hasHammer) {
                    timer.isGameOver = true;
                    isLost = true;
                    isRunning = false;
                }
            }

        }
    }

    public void touchDonkey() {
        if ((player.getPlayerBounds().intersects(donkey.getDonkeyBounds()))) {
            System.out.println("Player touches donkey");

            isWon = player.hasHammer;
            isLost = !isWon;

            timer.isGameOver = isWon;
            isRunning = false;
        }
    }

    public int calculateScore() {

        for (int i = 0; i < barrels.barrelCount; i++) {
            if (barrels.barrelDestroyed[i] && !barrels.isBarrelScoreAdded[i]) {
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

        for (int i = 0; i < barrels.barrelDestroyed.length; i++) {
            barrels.barrelDestroyed[i] = false;
        }
        scorePoints = 0;
    }

    public void touchHammer() {
        if (player.getPlayerBounds().intersects(hammer.getHammerBounds())) {
            System.out.println("Player touched hammer");
            hammer.isHammerHeld = true;
            player.hasHammer = true;
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
        platform.renderPlatform();
        barrels.renderBarrel();
        ladder.renderLadder();
        hammer.renderHammer();
        donkey.renderDonkey();
        player.renderPlayer(input);
//        System.out.println("Player position: " + player.getPlayerPosition());
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




