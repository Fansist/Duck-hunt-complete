# Duck Hunt Remix — Student Guide

You are remixing Duck Hunt into your own Halloween or fall-themed game.
Your target could be a duck, pumpkin, leaf, bat, apple, ghost, or anything
else you can draw or find an image for.

If you are starting by yourself, follow the steps in order. Run the game
before you change anything, make one small change at a time, and use the
graphic organizer to record what you expected and what you actually saw.

## Project status: completed

This copy of the project is finished and playable. Shoot the three pumpkins
one at a time. Each shot pumpkin falls and smashes, and the Kid runs out to
pick it up. You lose a life (a little pumpkin) for every miss. Retrieve all
three pumpkins to win. After the game ends, wait a second and click to play
again.

What is different from the starter:

- **Class names.** The guide below says `Duck` and `Dog`. In this project the
  target is `Pumpkin.java` and the retriever is `Kid.java`. Everything the
  guide says about the Duck and the Dog applies to those two classes.
- **Folder layout.** The files sit in the project root instead of
  `src/imgs`. `Sprite` (images) and `Music` (sounds) look in `/imgs` or
  `/sounds`, then the project root, then the working directory, so either
  layout works.
- **Steps 1-7 are done.** The Pumpkin moves, bounces off the sides, the top of
  the window, and the top of the ground, falls when shot, and resets. The Kid
  walks to the fallen Pumpkin. `Frame.java` declares and adds all three
  Pumpkins; each one is faster than the last and starts in a random direction.
- **The Kid.** The Dog is replaced by a trick-or-treater in a ghost costume
  carrying a pumpkin bucket (`kid_stand.png`, `kid_right1.png`,
  `kid_right2.png`, `kid_left1.png`, `kid_left2.png`). It faces the way it is
  running, swaps between two running pictures, and stops centered on the
  fallen Pumpkin.
- **Hand-drawn fall art.** `ground.png` (autumn trees and grass),
  `pumpkin_smashed.png` (shown when a shot Pumpkin lands), and `life.png` /
  `life_lost.png` (the lives display) come from the hand-drawn art sheet.
  The starter's green tree, bushes, and dog pictures were removed.
- **Sound.** `Music.java` plays the four sound files: `Gun.wav` on every shot,
  `Pumpkin Fall.wav` on a hit, `Missed.wav` on a miss, and `Lose.wav` on the
  miss that uses up the last life. If a computer has no audio device, the game
  runs silently.
- **Halloween theme.** A purple dusk sky with a moon (`Background.java`), the
  pumpkin target, and the window title "Pumpkin Hunt".

### Build and run without an IDE

Java 8 or newer. From the project folder:

```
javac *.java
java Frame
```

### Changes to the teacher-provided files

These were needed to finish the project, so they are listed here:

- `Sprite.java` - added the missing `Graphics` and `Image` imports (the
  starter did not compile without them) and `getWidth()` / `getHeight()`.
- `GameWorld.java` - `GROUND_TOP` is now 440 so the Kid and the Pumpkins stand
  on the grass in front of the trees. It plays the sound effects, draws the
  lives as little pumpkins, supports click-to-play-again, and tells the Kid
  where the middle of the fallen Pumpkin is. The tree and bushes are gone.
- `Foreground.java` - draws the new `ground.png` (the trees and the grass
  field), placed so the tree trunks sit just behind `GROUND_TOP`.
- `Background.java` - the dusk sky and moon.
- `Tree.java` and `Bush.java` - removed; the trees are part of `ground.png`.

## Before you begin

1. Open this project in Eclipse. If it is not already in your workspace, use
   `File > Import > Existing Projects into Workspace`, then select this
   project folder.
2. In Package Explorer, expand `src` and open `Frame.java`.
3. Run `Frame.java` as a Java Application. The Run button may also say
   `Run Frame`.
4. If you add a new image file while Eclipse is open, select the project and
   press `F5` to refresh it.

The game window is 900 pixels wide and 600 pixels tall. The ground begins at
`GameWorld.GROUND_TOP`, which is y = 440. You do not need to memorize these
numbers; they are provided so your if statements and images can line up with
the game world.

## Your files

You will mainly edit:

- `Duck.java` — movement, bouncing, falling, and resetting
- `Dog.java` — retrieving the fallen Duck
- `Frame.java` — declaring and adding more Duck objects

All image files belong in `src/imgs`. The spelling and capitalization of an
image filename must match the filename in your Java code exactly.

Use `Duck Hunt Remix Graphic Organizer.pdf` while you work. It shows how
`Frame`, `GameWorld`, `Duck`, and `Dog` are related and gives you a place to
record what you changed and what you saw when you tested it.

Do not edit these files for the core assignment:

- `Sprite.java` — image loading and drawing framework
- `GameWorld.java` — three named Duck fields, direct update and drawing calls, and game rules
- `Background.java` — draws the sky
- `Foreground.java` — draws the trees and the ground

The visual classes are drawn in layers: background, tree and ground scenery,
moving Ducks and Dog, then the lives display. You can read these classes
to see how each object has a job, but keep your coding attention on the
student files.

## What each class does

- `Frame` creates the window, starts the timer, and declares the Duck objects.
- `GameWorld` stores `duck1`, `duck2`, and `duck3`. It calls each Duck's
  `update()` and `paint()` methods, and manages stars, the Dog, and the next Duck.
- `Duck` controls movement, bouncing, falling, and resetting.
- `Dog` moves toward the fallen Duck and reports when retrieval is complete.
- `Background` and `Foreground` draw the sky, the trees, and the ground. They
  are framework classes for the core assignment.

## The most important habit: run and check

Do not write the whole game before running it. Work in small steps:

1. Save your file.
2. Run `Frame.java` as a Java Application in Eclipse.
3. Test only the behavior you just changed.
4. If it works, write down what you observed.
5. If it does not work, fix it before adding another feature.

You should be running the game after every numbered step below.

## Step 0: Run the starter

Run the program before changing anything.

You should see:

- A game window
- The background and ground
- Autumn trees and a grass field
- One Duck
- A Dog at the bottom
- A stars/lives display

The Duck is intentionally incomplete. It will not have all of its behavior
until you complete the steps below.

## Step 1: Make the Duck move

Open `Duck.java` and find `update()`.

Uncomment the two movement lines:

```java
x = x + dx;
y = y + dy;
```

Run the game. The Duck should move in a straight line.

If it moves too quickly or slowly, change `dx` and `dy`, then run again.

## Step 2: Make the Duck bounce

Add `if` statements in `Duck.update()` that reverse `dx` or `dy` when the
Duck reaches an edge.

Test each edge. The Duck should remain on the screen instead of disappearing.

## Step 3: Make the Duck fall when clicked

In `startFalling()`, uncomment the lines that set:

```java
falling = true;
fallSpeed = 2;
```

Then uncomment and complete the falling code in `update()`.

Run the game and check that:

- Clicking the Duck makes it fall.
- The Duck stops at the ground.
- The Duck does not continue moving through the ground.

## Step 4: Check resetting

Read `Duck.reset()`. `activate()` calls it when that Duck's turn begins.
Run the game again and check that the first Duck appears at its starting
position. Later, when you add the second and third Ducks, check that each
one starts at the position you gave it in `Frame.java`.

## Step 5: Make the Dog retrieve the Duck

Open `Dog.java` and complete `update()`.

Use `if` statements to:

- Move the Dog right when it is left of `targetX`.
- Move the Dog left when it is right of `targetX`.
- Decide when the Dog is close enough.
- Set `retrievedDuck` to `true` when the Dog arrives.
- Set `retrieving` to `false` when the retrieval is complete.

Run the game after each change. The next Duck will not activate until the Dog
reports that the retrieval is complete.

## Step 6: Add more Duck objects

In `Frame.java`, uncomment or create more Duck declarations:

```java
private Duck duck2 = new Duck(380, 180);
private Duck duck3 = new Duck(620, 100);
```

Then add each one to the GameWorld:

```java
world.addDuck(duck2);
world.addDuck(duck3);
```

Run the game. The Ducks should become active one at a time after the Dog
retrieves the previous Duck.

`GameWorld` has a separate field for each of the three Ducks. Read its
`update()` and `paint()` methods to see the direct calls for `duck1`, `duck2`,
and `duck3`. There is no collection to learn for this assignment.

## Step 7: Remix the theme

First make the game work with the original images. Then replace the images
with your own Halloween or fall theme.

### Replacing the Duck image

Change the image filename in the Duck constructor:

```java
super("your-image.png", startX, startY, 90, 90);
```

Put the new file inside `src/imgs`, then save, refresh Eclipse if needed, and
run the game. The last two numbers are the display width and height in
pixels. The original file's dimensions do not automatically determine its
size on the screen.

### Choosing a good size

- Start with `90, 90` for a Duck or other target.
- If the target is too small, increase both numbers. If it is too large,
  decrease both numbers.
- Try to keep the same width-to-height ratio as the original image. For
  example, a very wide image might use `120, 60` instead of `90, 90`.
- The display rectangle is also the click rectangle. If the rectangle is
  much larger than the visible picture, clicking may feel surprising.
- If the picture still looks tiny inside its rectangle, the file may have a
  large transparent border. Crop the image before using it.

### Replacing the Dog images

The Dog uses two filenames: one while waiting and one while retrieving.
Change both names in `Dog.java`:

```java
super("myDogStanding.png", 40, GameWorld.GROUND_TOP - DOG_HEIGHT,
        DOG_WIDTH, DOG_HEIGHT);
```

and inside `startRetrieving()`:

```java
changePicture("myDogRunning.png");
```

Both files must be inside `src/imgs`. Keep the two Dog images close to the
same size and shape so the Dog does not jump when its picture changes.

### PNG, GIF, and transparency hints

- PNG is usually the best choice for a still character because it can have a
  transparent background.
- GIF can also have transparency and can be animated. An animated GIF can
  make a target or Dog feel more alive without adding an animation loop to
  your Java code. Make sure the GIF really contains multiple frames.
- JPG is fine for a solid rectangular picture, but it usually does not have
  transparency, so a white or colored box may appear around the character.
- Use simple filenames such as `pumpkin.png` or `fall_target.gif`. Avoid
  spaces and make sure uppercase and lowercase letters match your code.

### Optional scenery remix

For the core assignment, do not edit the scenery classes. If your teacher
approves an extension, you can change the image in `Foreground.java` or change
the sky colors in `Background.java`. The same rule applies: make one change,
run the game, and check what changed.

### Other easy settings

You may also change:

- Duck speed
- Dog speed
- Starting positions
- Window title

## Core requirements

Your finished game should have:

- A moving target object
- At least two bouncing directions or edge rules
- A target that falls when clicked
- A Dog object that retrieves it
- At least three Duck/target objects declared and instantiated
- The objects added to the GameWorld
- Stars/lives that decrease when the player misses
- A reset or next-object behavior
- A Halloween or fall theme

## Image sizing cheat sheet

| Object | Main code location | Starting display size |
| --- | --- | --- |
| Duck / target | `Pumpkin.java` constructor | `100 x 100` |
| Dog | `Kid.java` constants | `70 x 94` |
| Ground and trees | `Foreground.java` | `900 x 343` |

These are display sizes, not required file sizes. Your source image can be
larger or smaller; the `Sprite` class scales it to the numbers in the code.

## If something goes wrong

- **The console says it cannot find an image:** check that the file is in
  `src/imgs`, that the spelling and capitalization match, then press `F5`
  and run again.
- **The image has a box around it:** use a transparent PNG or GIF instead of
  a JPG.
- **The image looks stretched:** change the width and height so they match
  the image's shape more closely.
- **The image is too tiny even after changing the size:** crop away extra
  transparent space around the character.
- **The Dog changes to a blank picture:** check both Dog filenames. The first
  name is used at the start; the second name is used during retrieval.
- **The new Duck never appears:** check that you declared it and also called
  `world.addDuck(...)` in `Frame.java`. The Ducks appear one at a time after
  the Dog retrieves the previous one. This starter supports three Ducks.

## Extension ideas

- Give each of the three targets a different start position or movement rule.
- Activate two targets at the same time.
- Add a special target worth extra stars.
- Add a game-over restart button. (Done: click to play again.)
- Add sound. (Done: `Music.java`.)
- Make the Dog use different images while moving. (Done: the Kid swaps running pictures.)
- Make each target move differently.
