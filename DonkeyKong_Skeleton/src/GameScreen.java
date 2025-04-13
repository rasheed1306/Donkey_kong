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
    private final EndGamePage lost;
    protected static int scorePoints = 0;
    public static final double SCORE_DISTANCE = 20;
    public static final double LADDER_DISTANCE = 30;
    private final Image background = new Image("res/background.png");

    boolean isOnLadder;
    boolean isOnPlatform;

    public GameScreen(Properties gameProps, Properties messageProps) {
        this.platform = new Platform(gameProps);
        this.barrels = new Barrel(gameProps);
        this.ladder = new Ladder(gameProps);
        this.hammer = new Hammer(gameProps);
        this.donkey = new Donkey(gameProps);
        this.player = new Player(gameProps);
        this.score = new Score(gameProps);
        this.lost = new EndGamePage(gameProps, messageProps);
        this.timer = new Timer(gameProps);

    }

    public void renderScreen(Input input) {
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

        for (Point barrel : barrels.getBarrelPositions()) {
            if (player.getPlayerPosition().distanceTo(barrel) <= SCORE_DISTANCE) {
                lost.renderLostGame(scorePoints);
                score.getScore(scorePoints+=20);
            }
        }
        isOnLadder = false;
        isOnPlatform = false;

        for (Rectangle ladder : ladder.getLadderBounds()) {
            if (ladder.intersects(player.getPlayerBounds())) {
                System.out.println("On ladder");
                isOnLadder = true;
                break;
            }
        }

        for (Rectangle platform : platform.getPlatformBounds()) {
            if (platform.intersects(player.getPlayerBounds())) {
                System.out.println("Player intersects platform");
                isOnPlatform = true;
                break;
            }
        }
        player.isOnLadder = isOnLadder;
        player.isJumping = !isOnPlatform;

    }
}




