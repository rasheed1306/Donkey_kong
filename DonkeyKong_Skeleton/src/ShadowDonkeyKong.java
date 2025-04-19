import bagel.*;
import java.util.Properties;

public class ShadowDonkeyKong extends AbstractGame {

    private final HomePage homePage;
    private final GameScreen gameScreen;
    private final EndGamePage endGamePage;

    private boolean shouldKeepShowing = false;
    private int score = 0;


    public ShadowDonkeyKong(Properties gameProps, Properties messageProps) {
        super(Integer.parseInt(gameProps.getProperty("window.width")),
                Integer.parseInt(gameProps.getProperty("window.height")),
                messageProps.getProperty("home.title"));

        // updates instance variables to reflect properties of game
        homePage = new HomePage(gameProps, messageProps);
        gameScreen = new GameScreen(gameProps);
        endGamePage = new EndGamePage(gameProps, messageProps);

    }


    @Override
    protected void update(Input input) {

        if (input.wasPressed(Keys.ESCAPE)) {
            Window.close();
        }

        if (gameScreen.isRunning) {
            updateGameScreen(input);
        } else {
            handleEndGameState(input);

        }
    }
    protected void updateGameScreen(Input input) {
        gameScreen.renderScreen(input);
        score = gameScreen.calculateScore();
    }

    protected void handleEndGameState(Input input) {
        if (gameScreen.isWon) {
            shouldKeepShowing = endGamePage.renderWonGame(score, input);
        } else if (gameScreen.isLost) {
            shouldKeepShowing = endGamePage.renderLostGame(score, input);
            System.out.println("Lost game page rendered");
        }
        if (!shouldKeepShowing) {
            homePage.renderTitle();
            gameScreen.restartGame();
            if (input.wasPressed(Keys.ENTER)) {
                gameScreen.isRunning = true;
            }
        }
    }


    public static void main (String[] args) {
        Properties gameProps = IOUtils.readPropertiesFile("res/app.properties");
        Properties messageProps = IOUtils.readPropertiesFile("res/message_en.properties");
        ShadowDonkeyKong game = new ShadowDonkeyKong(gameProps, messageProps);
        game.run();
    }
        }
