import bagel.*;
import java.util.Properties;

public class ShadowDonkeyKong extends AbstractGame {

    private final Properties GAME_PROPS;
    private final Properties MESSAGE_PROPS;
    private HomePage homePage;
    private GameScreen gameScreen;
    private EndGamePage endGamePage;
    protected boolean isRunning = false;
    protected boolean isWon = false;
    protected boolean isLost = false;
    protected int score = 0;
    private boolean shouldKeepShowing = false;


    public ShadowDonkeyKong(Properties gameProps, Properties messageProps) {
        super(Integer.parseInt(gameProps.getProperty("window.width")),
                Integer.parseInt(gameProps.getProperty("window.height")),
                messageProps.getProperty("home.title"));

        // updates instance variables to reflect properties of game
        this.GAME_PROPS = gameProps;
        this.MESSAGE_PROPS = messageProps;
        homePage = new HomePage(GAME_PROPS, MESSAGE_PROPS);
        gameScreen = new GameScreen(GAME_PROPS, MESSAGE_PROPS);
        endGamePage = new EndGamePage(GAME_PROPS, MESSAGE_PROPS);

    }


    @Override
    protected void update(Input input) {

        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }

        if (isRunning) {
            gameScreen.renderScreen(input);
            isRunning = gameScreen.isRunning;
            isWon = gameScreen.isWon;
            isLost = gameScreen.isLost;
            score = gameScreen.calulateScore();
        } else {
//            isWon = gameScreen.isWon;
//            isLost = gameScreen.isLost;
//            score = gameScreen.calulateScore();
            if (isWon) {
                shouldKeepShowing = endGamePage.renderWonGame(score, input);
            } else if (isLost) {
                shouldKeepShowing = endGamePage.renderLostGame(score, input);
                System.out.println("Lost game page rendered");
            }
            if (!shouldKeepShowing) {
                isLost = false;
                isWon = false;
                homePage.renderTitle();
                gameScreen.restartGame();
                if (input.wasPressed(Keys.ENTER)) {
                    isRunning = true;
                }
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
