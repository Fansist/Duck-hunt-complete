import java.util.Random;
/**
 * STUDENT FILE: Duck behavior.
 *
 * Sprite provides the graphics code. Your job is to make the Duck move,
 * bounce, fall, and reset.
 */
public class Pumpkin extends Sprite {
    // ===============================
    // STUDENT SETTINGS
    // ===============================
    private int dx = 4;
    private int dy = 2;

    // ===============================
    // GAME STATE - mostly provided
    // ===============================
    private int homeX;
    private int homeY;
    private int fallSpeed = 2;
    private boolean active = false;
    private boolean falling = false;
    private boolean landed = false;

    public Pumpkin() {
        this(150, 120);
        
    }

    public Pumpkin(int startX, int startY) {
        // Change duck.gif to your own Halloween or fall image later.
        super("Pumkin AIR.png", startX, startY, 100, 100);

        homeX = startX;
        homeY = startY;
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
        	if (x <= 0) {
    			x = 0;
    			dx *= -1;
    		} else if (x >= 900) {
    			x = 900-50;
    			dx *= -1;
    		}

    		if (y <= 0) {
    			y = 50;
    			dy *= -1;
    		} else if (y>= 600) {
    			y = 600;
    			dy *= -1;
    		}
        	return;
        }

        if (falling) {
             y = y + fallSpeed;
             fallSpeed = fallSpeed + 1;
             if (y + height >= GameWorld.GROUND_TOP) {
                 y = GameWorld.GROUND_TOP - height;
                 landed = true;
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
        x = homeX;
        y = homeY;
        fallSpeed = 2;
        falling = false;
        landed = false;
    }
}
