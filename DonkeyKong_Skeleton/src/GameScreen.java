import bagel.*;
import java.util.Properties;

public class GameScreen {
    private final Platform platform;
    private final Barrel barrel;
    private final Ladder ladder;
    private final Image background = new Image("res/background.png");

    public GameScreen(Properties gameProps) {
        this.platform = new Platform(gameProps);
        this.barrel = new Barrel(gameProps);
        this.ladder = new Ladder(gameProps);
    }

    public void renderScreen() {
        background.draw(Window.getWidth() / 2., Window.getHeight() / 2.);
        platform.renderPlatform();
        barrel.renderBarrel();
        ladder.renderLadder();
    }
}




