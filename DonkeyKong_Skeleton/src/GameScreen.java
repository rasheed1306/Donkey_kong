import bagel.*;

import java.util.Arrays;
import java.util.Properties;
import bagel.util.*;

/**
 * The main game screen manages all game objects, interactions and game states
 * Handles rendering objects, collision detection and scoring
 */
public class GameScreen {

    // Game objects
    private final Platform platform;
    private final Barrel barrels;
    private final Ladder ladder;
    private final Hammer hammer;
    private final Donkey donkey;
    private final Player player;

    // Game systems
    private final Timer timer;
    private final Score score;
    private final Image background = new Image("res/background.png");
    private int scorePoints = 0; // This keeps track of the current score

    // Game state flags
    boolean isOnPlatform;
    protected boolean isRunning;
    protected boolean isScoreAdded;
    protected boolean isWon = false;
    protected boolean isLost = false;

    // Initialises all instance variables from game properties
    public GameScreen(Properties gameProps) {
        this.platform = new Platform(gameProps);
        this.barrels = new Barrel(gameProps);
        this.ladder = new Ladder(gameProps);
        this.hammer = new Hammer(gameProps);
        this.donkey = new Donkey(gameProps);
        this.player = new Player(gameProps);
        this.score = new Score(gameProps);
        this.timer = new Timer(gameProps);

    }

    // Check if player is touching ladder
    public void touchLadder() {
        player.isClimbing = false;
        for (Rectangle ladder : ladder.getObjBounds()) {
            if (ladder.intersects(player.getObjBounds()[0])) {
                System.out.println("On ladder");
                player.isClimbing = true;
                break;
            }
        }
    }

    // Checks if player is touching platform
    public void touchPlatform() {
        isOnPlatform = false;
        for (Rectangle platform : platform.getObjBounds()) {
            if (platform.intersects(player.getObjBounds()[0])) {
                System.out.println("On platform");
                isOnPlatform = true;
                isScoreAdded = false;
                break;
            }
        }
        player.isJumping = !isOnPlatform;
    }

    // Checks if player touches barrel. Handles whether game is ended or score is handed depending on whether
    // hammer is held
    public void touchBarrel() {
        for (int i = 0; i < barrels.objCount; i++) {
            Rectangle barrel = barrels.getObjBounds()[i];
            if (player.getObjBounds()[0].intersects(barrel)) {
                if (GameScreenObject.isHammerHeld) {
                    System.out.println("Barrel " + i + " destroyed");
                    barrels.updateBarrelStatus(true, i);
                } else if (!GameScreenObject.isHammerHeld) {
                    timer.isGameOver = true;
                    isLost = true;
                    isRunning = false;
                }
            }

        }
    }

    // checks whether player touches donkey - win with hammer, lose without
    public void touchDonkey() {
        if (player.getObjBounds()[0].intersects(donkey.getObjBounds()[0])) {
            System.out.println("Player touches donkey");

            isWon = GameScreenObject.isHammerHeld;
            isLost = !isWon;

            timer.isGameOver = isWon;
            isRunning = false;
        }
    }

    /**
     * Calculates current score based on:
     * Destorying barrels (100+) points
     * Jumping over barrels (30+) points
     * Remaining time bonus (3 * remaining time) points if game has ended
     * @return current score
     */
    public int calculateScore() {

        // Adds points for barrels destroyed
        for (int i = 0; i < barrels.objCount; i++) {
            if (Barrel.isBarrelDestroyed[i] && !Barrel.isBarrelScoreAdded[i]) {
                scorePoints += 100;
                Barrel.isBarrelScoreAdded[i] = true;
            }
        }

        // Add points based on barrels jumped over
        for (Rectangle area : barrels.getJumpingBarrelBounds()) {
            if (area.intersects(player.getObjBounds()[0]) && player.isJumping) {
                if (!isScoreAdded) {
                    scorePoints += 30;
                    isScoreAdded = true;
                }
            }
        }

        // Remaining time bonus
        scorePoints += timer.getEndTime();
        return scorePoints;
    }

    /**
     * Restarts game state for a new game
     */
    public void restartGame() {
        // Resets player position and timer
        player.restartToStart();
        timer.resetTimer();

        // Reset game flags
        GameScreenObject.isHammerHeld = false;
        isWon = false;
        isLost = false;
        Arrays.fill(Barrel.isBarrelDestroyed, false);

        // Reset score points to 0
        scorePoints = 0;
    }

    /**
     * Handles player picking up hammer
     */
    public void touchHammer() {
        if (player.getObjBounds()[0].intersects(hammer.getObjBounds()[0])) {
            System.out.println("Player touched hammer");
            GameScreenObject.isHammerHeld = true;
        }
    }

    /**
     * Checks whether time has run out and if so ends game
     */
    public void ranOutOfTime() {
        if (timer.isGameOver) {
            isLost = true;
            isRunning = false;
        }
    }

    /**
     * Renders all game screen elements
     * @param input User input
     */
    public void renderScreen(Input input) {
        isRunning = true;
        background.draw(Window.getWidth() / 2., Window.getHeight() / 2.);
        platform.renderObj();
        barrels.renderObj();
        ladder.renderObj();
        hammer.renderObj();
        donkey.renderObj();
        player.renderObj(input);
        score.getScore(calculateScore());
        timer.updateTimer();
        timer.renderTimer();

        touchBarrel();
        touchPlatform();
        touchLadder();
        touchHammer();
        touchDonkey();
        ranOutOfTime();

    }
}



