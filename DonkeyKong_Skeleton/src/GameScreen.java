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
    private final Score score;
    private final EndGamePage lost;
    protected static int scorePoints = 0;
    public static final double SCORE_DISTANCE = 20;
    public static final double LADDER_DISTANCE = 30;
    private final Image background = new Image("res/background.png");


    public GameScreen(Properties gameProps, Properties messageProps) {
        this.platform = new Platform(gameProps);
        this.barrels = new Barrel(gameProps);
        this.ladder = new Ladder(gameProps);
        this.hammer = new Hammer(gameProps);
        this.donkey = new Donkey(gameProps);
        this.player = new Player(gameProps);
        this.score = new Score(gameProps);
        this.lost = new EndGamePage(gameProps, messageProps);

    }

    public void renderScreen(Input input) {
        background.draw(Window.getWidth() / 2., Window.getHeight() / 2.);
        platform.renderPlatform();
        barrels.renderBarrel();
        ladder.renderLadder();
        hammer.renderHammer();
        donkey.renderDonkey();
        player.renderPlayer(input);
        score.getScore(scorePoints);

        for (Point barrel : barrels.getBarrelPositions()) {
            if (player.getPlayerPosition().distanceTo(barrel) <= SCORE_DISTANCE) {
                lost.renderLostGame(scorePoints);
//                score.getScore(scorePoints+=20);
            }
        }
        for (Rectangle ladder : ladder.getLadderBounds()) {
            if (ladder.intersects(player.getPlayerPosition())) {
                player.isClimbing = true;
            }
        }
//ladder.intersects(player.getPlayerPosition().x, player.getPlayerPosition().y, 1, 1)
    }
}




