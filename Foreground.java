import java.awt.Color;
import java.awt.Graphics;

/**
 * TEACHER-PROVIDED VISUAL CLASS.
 * Draws the autumn trees and the grass field at the bottom of the game.
 */
public class Foreground extends Sprite {
    // ground.png is 900 x 343. Its top part is see-through sky with the
    // tops of the trees; the tree trunks end 183 pixels down. Placing the
    // picture so its trunks end 55 pixels above GameWorld.GROUND_TOP puts
    // the Kid and the fallen Pumpkins on the grass in front of the trees.
    private static final int PICTURE_HEIGHT = 343;
    private static final int PICTURE_TOP = GameWorld.GROUND_TOP - 183;

    // The flat grass color at the bottom of ground.png.
    private static final Color GRASS = new Color(34, 177, 76);

    public Foreground() {
        super("ground.png", 0, PICTURE_TOP, GameWorld.WORLD_WIDTH, PICTURE_HEIGHT);
    }

    @Override
    public void paint(Graphics g) {
        // If GROUND_TOP is ever changed, the picture can end above the bottom
        // of the window. Fill the gap with the same grass color.
        int pictureBottom = y + height;
        if (pictureBottom < GameWorld.WORLD_HEIGHT) {
            g.setColor(GRASS);
            g.fillRect(0, pictureBottom, GameWorld.WORLD_WIDTH,
                    GameWorld.WORLD_HEIGHT - pictureBottom);
        }

        super.paint(g);
    }
}
