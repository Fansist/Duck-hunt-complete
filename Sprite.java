import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

public class Sprite {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    private Image image;

    public Sprite(String imageFileName, int x, int y, int width, int height) {
        image = loadImage(imageFileName);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void paint(Graphics g) {
        if (image != null) {
            g.drawImage(image, x, y, width, height, null);
        }
    }

    protected void moveBy(int changeX, int changeY) {
        x = x + changeX;
        y = y + changeY;
    }

    public boolean wasClicked(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width
                && mouseY >= y && mouseY <= y + height;
    }

    protected void changePicture(String imageFileName) {
        image = loadImage(imageFileName);
    }

    public void setLocation(int newX, int newY) {
        x = newX;
        y = newY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    private Image loadImage(String imageFileName) {
        try {
            URL imageURL = Sprite.class.getResource("/imgs/" + imageFileName);
            if (imageURL == null) {
                imageURL = Sprite.class.getResource("/" + imageFileName);
            }

            if (imageURL != null) {
                return ImageIO.read(imageURL);
            }

            File localFile = new File(imageFileName);
            if (localFile.exists()) {
                return ImageIO.read(localFile);
            }

            File nestedFile = new File("imgs", imageFileName);
            if (nestedFile.exists()) {
                return ImageIO.read(nestedFile);
            }

            System.out.println("Could not find image: " + imageFileName);
            return null;
        } catch (Exception exception) {
            System.out.println("Could not load image: " + imageFileName);
            return null;
        }
    }
}
