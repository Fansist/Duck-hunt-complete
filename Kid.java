/**
 * STUDENT FILE: Dog behavior.
 *
 * The GameWorld tells the Dog where the Duck landed. Your job is to make the
 * Dog move toward that location and report when the retrieval is complete.
 */
public class Kid extends Sprite {
    private static final int DOG_WIDTH = 70;
    private static final int DOG_HEIGHT = 94;
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
        super("kid_stand.png", 40, GameWorld.GROUND_TOP - DOG_HEIGHT,
                DOG_WIDTH, DOG_HEIGHT);

        homeX = x;
        homeY = y;
    }

    /** This method is provided so GameWorld can begin a retrieval. */
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
     * STEP 5: make the Dog move toward targetX.
     *
     * Use if statements:
     * - If x is less than targetX, increase x.
     * - If x is greater than targetX, decrease x.
     * - When x is close enough, set retrievedDuck to true and retrieving to
     *   false.
     */
    public void update() {
        if (!retrieving) {
            return;
        }

        // Write your Dog movement code here.
        if (x < targetX) {
            x = x + speed;
        } else if (x > targetX) {
            x = x - speed;
        }

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

    /** After Step 5, run the game several times and verify that reset works. */
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
