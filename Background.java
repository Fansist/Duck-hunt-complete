import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * TEACHER-PROVIDED VISUAL CLASS.
 * Draws the sky behind the game objects.
 */
public class Background {
    private static final Color SKY_TOP = new Color(50, 24, 100);
    private static final Color SKY_HORIZON = new Color(190, 120, 200);

    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(new GradientPaint(0, 0, SKY_TOP, 0, GameWorld.GROUND_TOP, SKY_HORIZON));
        g2.fillRect(0, 0, GameWorld.WORLD_WIDTH, GameWorld.WORLD_HEIGHT);

        g2.setColor(new Color(255, 244, 205));
        g2.fillOval(790, 36, 70, 70);
        g2.setColor(new Color(236, 222, 180));
        g2.fillOval(810, 58, 14, 14);
        g2.fillOval(834, 76, 9, 9);
    }
}
