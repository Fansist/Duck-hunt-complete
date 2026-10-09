import java.util.Random;
/**
 * STUDENT FILE: Duck behavior.
 *
 * Sprite provides the graphics code. Your job is to make the Duck move,
 * bounce, fall, and reset.
 */
public class Pumpkin extends Sprite {
    private static final String FLYING_PICTURE = "Pumkin AIR.png";
    private static final int FLYING_SIZE = 100;

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

    public Pumpkin(int startX, int startY, int speedX, int speedY) {
        // Change duck.gif to your own Halloween or fall image later.
        super(FLYING_PICTURE, startX, startY, FLYING_SIZE, FLYING_SIZE);

        homeX = startX;
        homeY = startY;
        this.speedX = speedX;
        this.speedY = speedY;
        dx = speedX;
        dy = speedY;
    }

    /**
     * STEP 1: make the Duck move.
     * STEP 2: add the bouncing rules.
     * STEP 3: add the falling rules.
     */
    public void update() {
        if (!active) {
        	//900 600 is the frame size
        	
        	
        	
        	// bouncy walls
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

        // STEP 1: Uncomment these lines to move the Duck.
         x = x + dx;
         y = y + dy;

        // STEP 2: Add if statements that bounce the Duck off the edges.
        // Hint: reverse a direction by changing dx to -dx or dy to -dy.
        // Hint: GameWorld.WORLD_WIDTH is the width of the game.
        // Hint: GameWorld.GROUND_TOP is the top of the ground.
        if (x <= 0) {
            x = 0;
            dx = Math.abs(dx);
        } else if (x + width >= GameWorld.WORLD_WIDTH) {
            x = GameWorld.WORLD_WIDTH - width;
            dx = -Math.abs(dx);
        }

        if (y <= 0) {
            y = 0;
            dy = Math.abs(dy);
        } else if (y + height >= GameWorld.GROUND_TOP) {
            y = GameWorld.GROUND_TOP - height;
            dy = -Math.abs(dy);
        }
    }

    /**
     * STEP 3: Uncomment the two lines below so a successful click starts the
     * falling behavior.
     */
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

    /** STEP 4: verify that reset returns the Duck to its starting position. */
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

    private void smash() {
        int centerX = x + width / 2;
        changePicture(SMASHED_PICTURE);
        width = SMASHED_WIDTH;
        height = SMASHED_HEIGHT;
        x = Math.max(0, Math.min(centerX - width / 2, GameWorld.WORLD_WIDTH - width));
        y = GameWorld.GROUND_TOP - height;
        smashed = true;
    }
}
