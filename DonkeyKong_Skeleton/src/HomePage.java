import bagel.*;
import java.util.Properties;

/**
 * Handles the title page rendering for the game.
 * Contains common functionality for displaying title screens.
 */
public class HomePage {
    private final Font TITLE_FONT;
    private final Font PROMPT_FONT;
    private final String TITLE;
    private final String PROMPT;
    private final double TITLE_Y;
    private final double PROMPT_Y;
    private int TITLE_SIZE;
    private int PROMPT_SIZE;
    private final Image background = new Image("res/background.png");

    public HomePage(Properties gameProps, Properties messageProps) {
        this.TITLE_SIZE = Integer.parseInt(gameProps.getProperty("home.title.fontSize"));
        this.PROMPT_SIZE = Integer.parseInt(gameProps.getProperty("home.prompt.fontSize"));
        this.TITLE_FONT = new Font(gameProps.getProperty("font"), TITLE_SIZE);
        this.PROMPT_FONT = new Font(gameProps.getProperty("font"), PROMPT_SIZE);
        this.TITLE = messageProps.getProperty("home.title");
        this.PROMPT = messageProps.getProperty("home.prompt");
        this.TITLE_Y = Double.parseDouble(gameProps.getProperty("home.title.y"));
        this.PROMPT_Y = Double.parseDouble(gameProps.getProperty("home.prompt.y"));

    }

    /**
     * Renders the title screen
     */
    public void renderTitle() {
        background.draw(Window.getWidth() / 2., Window.getHeight() / 2.);
        TITLE_FONT.drawString(TITLE, (Window.getWidth() - TITLE_FONT.getWidth(TITLE)) / 2, TITLE_Y);
        PROMPT_FONT.drawString(PROMPT, (Window.getWidth() - PROMPT_FONT.getWidth(PROMPT)) / 2, PROMPT_Y);
    }
}
