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
    private final Font TITLE_FONT;
    private final Font PROMPT_FONT;
    private final String TITLE;
    private final String PROMPT;
    private final double TITLE_Y;
    private final double PROMPT_Y;


    public ShadowDonkeyKong(Properties gameProps, Properties messageProps) {
        super(Integer.parseInt(gameProps.getProperty("window.width")),
                Integer.parseInt(gameProps.getProperty("window.height")),
                messageProps.getProperty("home.title"));

        int TITLE_SIZE = Integer.parseInt(gameProps.getProperty("home.title.fontSize"));
        int PROMPT_SIZE = Integer.parseInt(gameProps.getProperty("home.prompt.fontSize"));
        this.TITLE_FONT = new Font(gameProps.getProperty("font"), TITLE_SIZE);
        this.PROMPT_FONT = new Font(gameProps.getProperty("font"), PROMPT_SIZE);
        this.TITLE = messageProps.getProperty("home.title");
        this.PROMPT = messageProps.getProperty("home.prompt");
        this.TITLE_Y = Double.parseDouble(gameProps.getProperty("home.title.y"));
        this.PROMPT_Y = Double.parseDouble(gameProps.getProperty("home.prompt.y"));

        // updates instance variables to reflect properties of game
        this.GAME_PROPS = gameProps;
        this.MESSAGE_PROPS = messageProps;
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
        TITLE_FONT.drawString(TITLE, (Window.getWidth() - TITLE_FONT.getWidth(TITLE)) / 2 , TITLE_Y);
        PROMPT_FONT.drawString(PROMPT, (Window.getWidth() - PROMPT_FONT.getWidth(PROMPT)) / 2, PROMPT_Y);
    }


    /**
     * The main entry point of the Shadow Donkey Kong game.
     *
     * This method loads the game properties and message files, initializes the game,
     * and starts the game loop.
     *
     * @param args Command-line arguments (not used in this game).
     */
    public static void main(String[] args) {
        Properties gameProps = IOUtils.readPropertiesFile("res/app.properties");
        Properties messageProps = IOUtils.readPropertiesFile("res/message_en.properties");
        ShadowDonkeyKong game = new ShadowDonkeyKong(gameProps, messageProps);
        game.run();
    }


}
