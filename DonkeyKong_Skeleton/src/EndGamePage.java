import bagel.*;
import java.util.Properties;
import bagel.util.Point;

/*
Handles the display of end-game screen
 */
public class EndGamePage {

    // Game end messages
    private final String won;
    private final String score;
    private final String _continue;
    private final String lost;

    // Fonts for different elements
    private final Font statusFont;
    private final Font scoresFont;

    // Location of different elements
    private final Point statusPoint;
    private final Point scoresPoint;

    // Background Images
    private final Image background = new Image("res/background.png");


    public EndGamePage(Properties gameProps, Properties messageProps) {
        // Renders messages, fonts, and location from game properties and message properties
        won = messageProps.getProperty("gameEnd.won");
        lost = messageProps.getProperty("gameEnd.lost");
        _continue = messageProps.getProperty("gameEnd.continue");
        score = messageProps.getProperty("gameEnd.score");

        statusFont = new Font(gameProps.getProperty("font"), Integer.parseInt(gameProps.getProperty("gameEnd.status.fontSize")));
        scoresFont = new Font(gameProps.getProperty("font"), Integer.parseInt(gameProps.getProperty("gameEnd.scores.fontSize")));

        statusPoint = new Point( ((Window.getWidth() - statusFont.getWidth(lost)) / 2), Double.parseDouble(gameProps.getProperty("gameEnd.status.y")));
        scoresPoint = new Point( ((Window.getWidth() - scoresFont.getWidth(score))/ 2), Double.parseDouble(gameProps.getProperty("gameEnd.scores.y")));
    }

    // Renders lost game screen with player score
    public boolean renderLostGame(int score, Input input) {
        background.draw(Window.getWidth() / 2., Window.getHeight() / 2.);
        statusFont.drawString(lost, statusPoint.x, statusPoint.y);
        scoresFont.drawString((this.score + " " + score), scoresPoint.x, scoresPoint.y);
        statusFont.drawString(_continue, ((Window.getWidth() - statusFont.getWidth(_continue))/ 2), Window.getHeight() - 100);

        return !input.isDown(Keys.SPACE);
    }

    // Renders won game with player score
    public boolean renderWonGame(int score, Input input) {
        background.draw(Window.getWidth() / 2., Window.getHeight() / 2.);
        statusFont.drawString(won, statusPoint.x, statusPoint.y);
        scoresFont.drawString((this.score + " " + score), scoresPoint.x, scoresPoint.y);
        statusFont.drawString(_continue, ((Window.getWidth() - statusFont.getWidth(_continue))/ 2), Window.getHeight() - 100);

        return !input.isDown(Keys.SPACE);
    }
}
