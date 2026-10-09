import java.util.Random;

/**
 * STUDENT FILE: the target (this is the "Duck" from the guide, remixed as a
 * pumpkin).
 *
 * Sprite provides the graphics code. The Pumpkin moves, bounces off the
 * edges, falls when it is shot, and resets when its turn begins.
 */
public class Pumpkin extends Sprite {
    private static final String FLYING_PICTURE = "Pumkin AIR.png";
    private static final int FLYING_SIZE = 100;

    // The smashed picture is shown when a shot Pumpkin hits the ground.
    private static final String SMASHED_PICTURE = "pumpkin_smashed.png";
    private static final int SMASHED_WIDTH = 134;
    private static final int SMASHED_HEIGHT = 102;

    // ===============================
    // STUDENT SETTINGS
    // ===============================
    private int dx;
    private int dy;
    private final int speedX;
    private final int speedY;

    // ===============================
    // GAME STATE - mostly provided
    // ===============================
    private final Random random = new Random();
    private int homeX;
    private int homeY;
    private int fallSpeed = 2;
    private boolean active = false;
    private boolean falling = false;
    private boolean landed = false;
    private boolean smashed = false;

    public Pumpkin() {
        this(150, 120);
    }

    public Pumpkin(int startX, int startY) {
        this(startX, startY, 4, 2);
    }

    /** Same as above, but with your own speed (pixels per frame) for this Pumpkin. */
    public Pumpkin(int startX, int startY, int speedX, int speedY) {
        super(FLYING_PICTURE, startX, startY, FLYING_SIZE, FLYING_SIZE);

        homeX = startX;
        homeY = startY;
        this.speedX = speedX;
        this.speedY = speedY;
        dx = speedX;
        dy = speedY;
    }

    /**
     * Moves the Pumpkin each frame. A waiting Pumpkin does nothing, a shot
     * Pumpkin falls until it lands, and a flying Pumpkin moves and bounces.
     */
    public void update() {
        if (!active) {
            return;
        }

        if (falling) {
            if (!landed) {
                y = y + fallSpeed;
                fallSpeed = fallSpeed + 1;
                if (y + height >= GameWorld.GROUND_TOP) {
                    y = GameWorld.GROUND_TOP - height;
                    landed = true;
                    smash();
                }
            }
            return;
        }

        x = x + dx;
        y = y + dy;

        // Bounce off the left and right edges of the window.
        if (x <= 0) {
            x = 0;
            dx = Math.abs(dx);
        } else if (x + width >= GameWorld.WORLD_WIDTH) {
            x = GameWorld.WORLD_WIDTH - width;
            dx = -Math.abs(dx);
        }

        // Bounce off the top of the window and the top of the ground.
        if (y <= 0) {
            y = 0;
            dy = Math.abs(dy);
        } else if (y + height >= GameWorld.GROUND_TOP) {
            y = GameWorld.GROUND_TOP - height;
            dy = -Math.abs(dy);
        }
    }

    /** A successful click starts the falling behavior. */
    public void startFalling() {
        if (active && !falling) {
            falling = true;
            fallSpeed = 2;
        }
    }

    public boolean hasLanded() {
        return active && landed;
    }

    public boolean isFalling() {
        return falling;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public boolean wasClicked(int mouseX, int mouseY) {
        return active && !falling && super.wasClicked(mouseX, mouseY);
    }

    // These methods are provided so the GameWorld can manage progression.
    public void activate() {
        active = true;
        reset();
    }

    public void deactivate() {
        active = false;
    }

    /** Swaps in the smashed picture, centered where the Pumpkin landed. */
    private void smash() {
        int centerX = x + width / 2;
        changePicture(SMASHED_PICTURE);
        width = SMASHED_WIDTH;
        height = SMASHED_HEIGHT;
        x = Math.max(0, Math.min(centerX - width / 2, GameWorld.WORLD_WIDTH - width));
        y = GameWorld.GROUND_TOP - height;
        smashed = true;
    }

    /**
     * Returns the Pumpkin to its starting position, stops any falling, and
     * picks a random direction so every round is a little different.
     */
    public void reset() {
        if (smashed) {
            changePicture(FLYING_PICTURE);
            width = FLYING_SIZE;
            height = FLYING_SIZE;
            smashed = false;
        }
        x = homeX;
        y = homeY;
        fallSpeed = 2;
        falling = false;
        landed = false;
        dx = random.nextBoolean() ? speedX : -speedX;
        dy = random.nextBoolean() ? speedY : -speedY;
    }
}
