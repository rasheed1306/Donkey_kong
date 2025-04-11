import bagel.*;
import java.util.Properties;

public class GameScreen {
    private final Platform platform;
    private final Barrel barrel;
    private final Ladder ladder;
    private final Hammer hammer;
    private final Donkey donkey;
    private final Player player;
    private final Image background = new Image("res/background.png");

    public GameScreen(Properties gameProps) {
        this.platform = new Platform(gameProps);
        this.barrel = new Barrel(gameProps);
        this.ladder = new Ladder(gameProps);
        this.hammer = new Hammer(gameProps);
        this.donkey = new Donkey(gameProps);
        this.player = new Player(gameProps);
    }

    public void renderScreen(Input input) {
        background.draw(Window.getWidth() / 2., Window.getHeight() / 2.);
        platform.renderPlatform();
        barrel.renderBarrel();
        ladder.renderLadder();
        hammer.renderHammer();
        donkey.renderDonkey();
        player.renderPlayer(input);
//        player.renderPlayer();
    }
}




