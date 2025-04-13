import bagel.*;
import java.util.Properties;

/**
 * The main class for the Shadow Donkey Kong game.
 * This class extends {@code AbstractGame} and is responsible for managing game initialization,
 * updates, rendering, and handling user input.
 *
 * It sets up the game world, initializes characters, platforms, ladders, and other game objects,
 * and runs the game loop to ensure smooth gameplay.
 */
public class ShadowDonkeyKong extends AbstractGame {

    private final Properties GAME_PROPS;
    private final Properties MESSAGE_PROPS;
    private HomePage homePage;
    private GameScreen gameScreen;
    protected boolean isRunning = false;


    public ShadowDonkeyKong(Properties gameProps, Properties messageProps) {
        super(Integer.parseInt(gameProps.getProperty("window.width")),
                Integer.parseInt(gameProps.getProperty("window.height")),
                messageProps.getProperty("home.title"));

        // updates instance variables to reflect properties of game
        this.GAME_PROPS = gameProps;
        this.MESSAGE_PROPS = messageProps;
        homePage = new HomePage(GAME_PROPS, MESSAGE_PROPS);
        gameScreen = new GameScreen(GAME_PROPS, MESSAGE_PROPS);

    }

    /**
     * Render the relevant screen based on the keyboard input given by the user and the status of the gameplay.
     * @param input The current mouse/keyboard input.
     */
    @Override
    protected void update(Input input) {

        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }
        if (isRunning) {
            gameScreen.renderScreen(input);
            isRunning = gameScreen.isRunning;
        } else {
            homePage.renderTitle();
            gameScreen.restartGame();
            if (input.wasPressed(Keys.ENTER)) {
                isRunning = true;
            }
        }

    }


    /**
     * The main entry point of the Shadow Donkey Kong game.
     *
     * This method loads the game properties and message files, initializes the game,
     * and starts the game loop.
     *
     * @param args Command-line arguments (not used in this game).
     */
    public static void main (String[] args) {
        Properties gameProps = IOUtils.readPropertiesFile("res/app.properties");
        Properties messageProps = IOUtils.readPropertiesFile("res/message_en.properties");
        ShadowDonkeyKong game = new ShadowDonkeyKong(gameProps, messageProps);
        game.run();
    }
        }
