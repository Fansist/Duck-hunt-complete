import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * TEACHER-PROVIDED FRAMEWORK CODE.
 *
 * GameWorld stores three named Ducks, draws scenery and objects in layers,
 * and coordinates the Dog. Students should not edit this file for the core
 * assignment.
 */
public class GameWorld {
    public static final int WORLD_WIDTH = 900;
    public static final int WORLD_HEIGHT = 600;
    public static final int GROUND_TOP = 370;

    private Pumpkin duck1;
    private Pumpkin duck2;
    private Pumpkin duck3;
    private Pumpkin currentDuck;
    private Kid dog;
    private Background background = new Background();
    private Foreground foreground = new Foreground();
    private Tree tree = new Tree();
    private Bush bush1 = new Bush(90, GROUND_TOP - 44, 130, 55);
    private Bush bush2 = new Bush(510, GROUND_TOP - 38, 120, 49);

    private int stars = 5;
    private boolean finished = false;
    private boolean won = false;

    public GameWorld(Kid dog) {
        this.dog = dog;
    }

    public void addDuck(Pumpkin duck) {
        if (duck1 == null) {
            duck1 = duck;
        } else if (duck2 == null) {
            duck2 = duck;
        } else if (duck3 == null) {
            duck3 = duck;
        }
    }

    public void start() {
        currentDuck = duck1;
        if (currentDuck != null) {
            currentDuck.activate();
        }
    }

    /** Updates each named Duck, then the Dog and game progression. */
    public void update() {
        if (finished) {
            return;
        }

        if (duck1 != null) {
            duck1.update();
        }
        if (duck2 != null) {
            duck2.update();
        }
        if (duck3 != null) {
            duck3.update();
        }

        dog.update();

        if (currentDuck != null && currentDuck.hasLanded()
                && !dog.isRetrieving() && !dog.hasRetrievedDuck()) {
            dog.startRetrieving(currentDuck.getX());
        }

        if (currentDuck != null && dog.hasRetrievedDuck()) {
            currentDuck.deactivate();
            activateNextDuck();
        }
    }

    public void paint(Graphics g) {
        background.paint(g);
        tree.paint(g);
        foreground.paint(g);
        bush1.paint(g);
        bush2.paint(g);

        if (duck1 != null && duck1.isActive()) {
            duck1.paint(g);
        }
        if (duck2 != null && duck2.isActive()) {
            duck2.paint(g);
        }
        if (duck3 != null && duck3.isActive()) {
            duck3.paint(g);
        }

        dog.paint(g);

        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        g.drawString("Stars / lives: " + stars, 20, 30);

        if (finished) {
            g.setFont(new Font("SansSerif", Font.BOLD, 32));

            if (won) {
                g.drawString("You retrieved every duck!", 245, 80);
            } else {
                g.drawString("Out of stars!", 350, 80);
            }
        }
    }

    public void handleClick(int mouseX, int mouseY) {
        if (finished) {
            return;
        }

        if (currentDuck == null || currentDuck.isFalling()
                || dog.isRetrieving()) {
            return;
        }

        if (currentDuck.wasClicked(mouseX, mouseY)) {
            currentDuck.startFalling();
        } else {
            stars = stars - 1;

            if (stars <= 0) {
                finished = true;
                won = false;
            }
        }
    }

    private void activateNextDuck() {
        dog.reset();
        if (currentDuck == duck1) {
            currentDuck = duck2;
        } else if (currentDuck == duck2) {
            currentDuck = duck3;
        } else {
            currentDuck = null;
        }

        if (currentDuck == null) {
            finished = true;
            won = true;
            return;
        }

        currentDuck.activate();
    }
}
