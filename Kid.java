/**
 * STUDENT FILE: the retriever (this is the "Dog" from the guide, remixed as a
 * trick-or-treater in a ghost costume).
 *
 * The GameWorld tells the Kid where the Pumpkin landed. The Kid runs there,
 * picks it up, and reports that the retrieval is complete.
 */
public class Kid extends Sprite {
    private static final int KID_WIDTH = 70;
    private static final int KID_HEIGHT = 94;

    // The Kid swaps between two running pictures every few frames.
    private static final int FRAMES_PER_STEP = 6;

    // STUDENT SETTING
    private int speed = 6;

    private final int homeX;
    private final int homeY;
    private int targetX;
    private boolean retrieving = false;
    private boolean retrievedDuck = false;

    private boolean facingRight = true;
    private int stepTimer = 0;
    private int stepPicture = 1;

    public Kid() {
        super("kid_stand.png", 40, GameWorld.GROUND_TOP - KID_HEIGHT,
                KID_WIDTH, KID_HEIGHT);

        homeX = x;
        homeY = y;
    }

    /**
     * This method is provided so GameWorld can begin a retrieval.
     * duckCenterX is the middle of the fallen Pumpkin; the Kid stops with its
     * own middle there.
     */
    public void startRetrieving(int duckCenterX) {
        if (!retrieving) {
            int farthestRight = GameWorld.WORLD_WIDTH - width;
            targetX = Math.max(0, Math.min(duckCenterX - width / 2, farthestRight));
            retrieving = true;
            retrievedDuck = false;

            facingRight = targetX >= x;
            stepTimer = 0;
            stepPicture = 1;
            changePicture(runPicture());
        }
    }

    /**
     * Moves the Kid toward targetX one step per frame. When the Kid is close
     * enough it reports that the retrieval is complete.
     */
    public void update() {
        if (!retrieving) {
            return;
        }

        if (x < targetX) {
            x = x + speed;
        } else if (x > targetX) {
            x = x - speed;
        }

        // Within one step of the target counts as arrived.
        if (Math.abs(x - targetX) <= speed) {
            x = targetX;
            retrievedDuck = true;
            retrieving = false;
            return;
        }

        stepTimer = stepTimer + 1;
        if (stepTimer >= FRAMES_PER_STEP) {
            stepTimer = 0;
            stepPicture = 3 - stepPicture;
            changePicture(runPicture());
        }
    }

    public boolean isRetrieving() {
        return retrieving;
    }

    public boolean hasRetrievedDuck() {
        return retrievedDuck;
    }

    /** Puts the Kid back at home, standing still. GameWorld calls this between rounds. */
    public void reset() {
        setLocation(homeX, homeY);
        retrieving = false;
        retrievedDuck = false;
        changePicture("kid_stand.png");
    }

    private String runPicture() {
        return (facingRight ? "kid_right" : "kid_left") + stepPicture + ".png";
    }
}
