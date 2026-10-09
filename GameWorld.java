import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 * TEACHER-PROVIDED FRAMEWORK CODE.
 *
 * GameWorld stores three named Ducks (the Pumpkins), draws scenery and
 * objects in layers, and coordinates the Dog (the Kid). Students should not
 * edit this file for the core assignment.
 *
 * It also plays the sound effects (shot, miss, fall, game over) and lets the
 * player click to play again once the game has ended.
 */
public class GameWorld {
    public static final int WORLD_WIDTH = 900;
    public static final int WORLD_HEIGHT = 600;
    public static final int GROUND_TOP = 440;

    private static final int START_STARS = 5;

    /** Frames to wait after the game ends before a click restarts it (about 1 second). */
    private static final int RESTART_DELAY_FRAMES = 60;

    private Pumpkin duck1;
    private Pumpkin duck2;
    private Pumpkin duck3;
    private Pumpkin currentDuck;
    private Kid dog;
    private Background background = new Background();
    private Foreground foreground = new Foreground();

    // Lives are shown as little pumpkins: a whole one for each life left,
    // a smashed one for each life lost.
    private Sprite lifeLeft = new Sprite("life.png", 0, 0, 46, 52);
    private Sprite lifeLost = new Sprite("life_lost.png", 0, 0, 50, 30);

    private Music soundGun = new Music("Gun.wav", false);
    private Music soundFall = new Music("Pumpkin Fall.wav", false);
    private Music soundMissed = new Music("Missed.wav", false);
    private Music soundLose = new Music("Lose.wav", false);

    private int stars = START_STARS;
    private boolean finished = false;
    private boolean won = false;
    private int framesSinceFinished = 0;

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
            if (framesSinceFinished < RESTART_DELAY_FRAMES) {
                framesSinceFinished++;
            }
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
            dog.startRetrieving(currentDuck.getX() + currentDuck.getWidth() / 2);
        }

        if (currentDuck != null && dog.hasRetrievedDuck()) {
            currentDuck.deactivate();
            activateNextDuck();
        }
    }

    public void paint(Graphics g) {
        background.paint(g);
        foreground.paint(g);

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

        paintLives(g);

        if (finished) {
            g.setFont(new Font("SansSerif", Font.BOLD, 32));

            if (won) {
                drawCenteredText(g, "You retrieved every pumpkin!", 110);
            } else {
                drawCenteredText(g, "Out of lives!", 110);
            }

            if (framesSinceFinished >= RESTART_DELAY_FRAMES) {
                g.setFont(new Font("SansSerif", Font.BOLD, 20));
                drawCenteredText(g, "Click to play again", 150);
            }
        }
    }

    /** Draws one little pumpkin per star: whole if it is left, smashed if it is lost. */
    private void paintLives(Graphics g) {
        int iconTop = 12;
        int slotWidth = 58;

        for (int i = 0; i < START_STARS; i++) {
            Sprite icon = i < stars ? lifeLeft : lifeLost;
            int iconX = 14 + i * slotWidth + (slotWidth - icon.getWidth()) / 2;
            int iconY = iconTop + lifeLeft.getHeight() - icon.getHeight();
            icon.setLocation(iconX, iconY);
            icon.paint(g);
        }
    }

    public void handleClick(int mouseX, int mouseY) {
        if (finished) {
            if (framesSinceFinished >= RESTART_DELAY_FRAMES) {
                restart();
            }
            return;
        }

        if (currentDuck == null || currentDuck.isFalling()
                || dog.isRetrieving()) {
            return;
        }

        soundGun.play();

        if (currentDuck.wasClicked(mouseX, mouseY)) {
            currentDuck.startFalling();
            soundFall.play();
        } else {
            stars = stars - 1;

            if (stars <= 0) {
                finish(false);
            } else {
                soundMissed.play();
            }
        }
    }

    /** Puts every object back to its starting state and begins a new game. */
    private void restart() {
        stars = START_STARS;
        finished = false;
        won = false;
        framesSinceFinished = 0;

        if (duck1 != null) {
            duck1.deactivate();
        }
        if (duck2 != null) {
            duck2.deactivate();
        }
        if (duck3 != null) {
            duck3.deactivate();
        }

        dog.reset();
        start();
    }

    private void finish(boolean playerWon) {
        finished = true;
        won = playerWon;
        framesSinceFinished = 0;

        if (!playerWon) {
            soundLose.play();
        }
    }

    /** Draws text with a dark shadow so it can be read against the sky. */
    private void drawText(Graphics g, String text, int textX, int textY) {
        g.setColor(new Color(0, 0, 0, 170));
        g.drawString(text, textX + 2, textY + 2);
        g.setColor(new Color(255, 230, 170));
        g.drawString(text, textX, textY);
    }

    private void drawCenteredText(Graphics g, String text, int textY) {
        FontMetrics metrics = g.getFontMetrics();
        drawText(g, text, (WORLD_WIDTH - metrics.stringWidth(text)) / 2, textY);
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
            finish(true);
            return;
        }

        currentDuck.activate();
    }
}
