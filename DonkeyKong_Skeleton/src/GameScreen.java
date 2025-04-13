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
    protected static int scorePoints = 0;
    public static final double SCORE_DISTANCE = 20;
    public static final double LADDER_DISTANCE = 30;
    private final Image background = new Image("res/background.png");

    boolean isOnLadder;
    boolean isOnPlatform;
    protected boolean isRunning;

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
                System.out.println("On ladder");
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
                System.out.println("Player intersects platform");
                isOnPlatform = true;
                break;
            }
        }
        player.isJumping = !isOnPlatform;
    }

    public void touchBarrel(Input input) {
        for (Rectangle barrel : barrels.getBarrelBounds()) {
            if (player.getPlayerBounds().intersects(barrel)) {
//                scorePoints += timer.remainingTime * 3;
                endGamePage.renderLostGame(scorePoints, input);
                if (input.isDown(Keys.SPACE)) {
                    this.isRunning = false;
                }
            }
        }
    }

    public void restartGame() {
        player.restartToStart();
        timer.resetTimer();
        scorePoints = 0;
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
        score.getScore(scorePoints);
        timer.updateTimer();
        timer.renderTimer();

        touchBarrel(input);
//        touchPlatform();
//        touchLadder();

    }
}




