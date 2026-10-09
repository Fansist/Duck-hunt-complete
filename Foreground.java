/**
 * TEACHER-PROVIDED VISUAL CLASS.
 * Draws the ground at the bottom of the game.
 */
public class Foreground extends Sprite {
    public Foreground() {
        super("ground.png", 0, GameWorld.GROUND_TOP,
                GameWorld.WORLD_WIDTH, GameWorld.WORLD_HEIGHT - GameWorld.GROUND_TOP);
    }
}
