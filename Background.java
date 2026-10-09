import java.awt.Color;
import java.awt.Graphics;

/**
 * TEACHER-PROVIDED VISUAL CLASS.
 * Draws the sky behind the game objects.
 */
public class Background {
    public void paint(Graphics g) {
        g.setColor(new Color(145, 205, 245));
        g.fillRect(0, 0, GameWorld.WORLD_WIDTH, GameWorld.WORLD_HEIGHT);
    }
}
