import java.awt.Color;
import java.awt.Graphics;

/**
 * TEACHER-PROVIDED VISUAL CLASS.
 * Draws the ground at the bottom of the game.
 */
public class Foreground extends Sprite {
    private static final int PICTURE_HEIGHT = 343;
    private static final int PICTURE_TOP = GameWorld.GROUND_TOP - 183;
    private static final Color GRASS = new Color(34, 177, 76);

    public Foreground() {
        super("ground.png", 0, PICTURE_TOP, GameWorld.WORLD_WIDTH, PICTURE_HEIGHT);
    }

    @Override
    public void paint(Graphics g) {
        int pictureBottom = y + height;
        if (pictureBottom < GameWorld.WORLD_HEIGHT) {
            g.setColor(GRASS);
            g.fillRect(0, pictureBottom, GameWorld.WORLD_WIDTH,
                    GameWorld.WORLD_HEIGHT - pictureBottom);
        }

        super.paint(g);
    }
}
