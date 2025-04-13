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
    private final EndGamePage endGamePage;
    private int scorePoints;
    public static final double SCORE_DISTANCE = 20;
    public static final double LADDER_DISTANCE = 30;
    private final Image background = new Image("res/background.png");

    boolean isOnLadder;
    boolean isOnPlatform;
    protected boolean isRunning;
    private boolean scoreAdded = false;
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
        this.endGamePage = new EndGamePage(gameProps, messageProps);
        this.timer = new Timer(gameProps);

    }

    public void touchLadder() {
        isOnLadder = false;
        for (Rectangle ladder : ladder.getLadderBounds()) {
            if (ladder.intersects(player.getPlayerBounds())) {
//                System.out.println("On ladder");
                isOnLadder = true;
                break;
            }
        }
        player.isOnLadder = isOnLadder;

    }

    public void touchPlatform() {
        isOnPlatform = false;
        for (Rectangle platform : platform.getPlatformBounds()) {
            if (platform.intersects(player.getPlayerBounds())) {
//                System.out.println("Player intersects platform");
                isOnPlatform = true;
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
//                    scorePoints += 100;
                } else if (!player.hasHammer) {
                    timer.isGameOver = true;
                    isLost = true;
//                    System.out.println(timer.getEndTime());
//                    scorePoints += timer.getEndTime() * 30;
//                    scoreAdded = true;
//                    endGamePage.renderLostGame(scorePoints, input);
                    isRunning = false;
                }
            }

        }
    }

    public void touchDonkey() {
        if ((player.getPlayerBounds().intersects(donkey.getDonkeyBounds()))) {
            System.out.println("Player touches donkey");
            isWon = player.hasHammer;
            isRunning = false;
        }
    }

    public int calulateScore() {
        scorePoints = 0;
        for ( Boolean destroyed : barrels.barrelDestroyed) {
            if (destroyed) {
                scorePoints+=100;
            }
        }
        scorePoints += timer.getEndTime();
        return scorePoints;
    }

    public void restartGame() {
        player.restartToStart();
        timer.resetTimer();

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

    public void renderScreen(Input input) {
        isRunning = true;
        background.draw(Window.getWidth() / 2., Window.getHeight() / 2.);
        platform.renderPlatform();
        barrels.renderBarrel();
        ladder.renderLadder();
        hammer.renderHammer();
        donkey.renderDonkey();
        player.renderPlayer(input);
        System.out.println("Player position: " + player.getPlayerPosition());
        score.getScore(calulateScore());
        timer.updateTimer();
        timer.renderTimer();

        touchBarrel(input);
        touchPlatform();
        touchLadder();
        touchHammer();
        touchDonkey();
    }
}




