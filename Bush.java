import java.awt.Color;
import java.awt.Graphics;

/**
 * TEACHER-PROVIDED VISUAL CLASS.
 * Draws a simple bush using Java shapes, so no extra image is needed.
 */
public class Bush {
    private int x;
    private int y;
    private int width;
    private int height;

    public Bush(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void paint(Graphics g) {
        g.setColor(new Color(33, 105, 42));
        g.fillOval(x, y + height / 3, width / 2, height * 2 / 3);
        g.fillOval(x + width / 4, y, width / 2, height);
        g.fillOval(x + width / 2, y + height / 3, width / 2, height * 2 / 3);

        g.setColor(new Color(71, 150, 48));
        g.fillOval(x + width / 5, y + height / 5, width / 3, height / 2);
    }
}
