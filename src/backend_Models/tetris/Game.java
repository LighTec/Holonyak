package backend_Models.tetris;

import app_Controller.Kaizen_85;
import javafx.scene.paint.Color;

/**
 * Controls the game logic of Tetris via Block objects.
 *
 * @see Block.java
 *
 */
public class Game {

    // Width of game field
    public int gridWidth;

    // Height of game field
    public int gridHeight;

    // The array of blocks representing the backend of the tetris grid
    private Block[] arrayBlocks;

    //Score and timer integers for keeping score
    private int score = 0;
    private int time = 0;

    private int blockSpawnX;
    private int blockSpawnY;

    private boolean gameRunning = true;

    // Used to control the next color iterated over for the blocks.
    private int colorInt = 0;
    
    // The current falling block.
    private Tetromino tetrominoFalling;

    // The block that is stored/held (This feature has yet to be implemented).
    private Tetromino tetrominoHold;

    /**
     * Prints to terminal with the game grid each turn if true.
     */
    public final boolean PRINT_TO_TERMINAL = false;

    private TetrominoSpawner tetrominoSpawner;

    //Getters for the width, height, blocks, spawn coordinates, player and running the game.
    public int getGridWidth() {
        return this.gridWidth;
    }

    public int getGridHeight() {
        return this.gridHeight;
    }

    /**
     * Gets the array of Blocks that represents the Tetris grid.
     * @return 
     */
    public Block[] getArrayBlocks() {
        return this.arrayBlocks;
    }
    
    public int getBlockSpawnX() {
        return this.blockSpawnX;
    }

    public int getBlockSpawnY() {
        return this.blockSpawnY;
    }

    public boolean getGameRunning() {
        return this.gameRunning;
    }

    /**
     * Sets the X position for the block to spawn on.
     * @param blockSpawnX 
     */
    public void setBlockSpawnX(int blockSpawnX) {
        if (blockSpawnX >= 0 && blockSpawnX < this.getGridWidth()) {
            this.blockSpawnX = blockSpawnX;
        }
    }

    /**
     * Sets the Y position for the block to spawn on.
     * @param blockSpawnY 
     */
    public void setBlockSpawnY(int blockSpawnY) {
        if (blockSpawnY >= 0 && blockSpawnY < this.getGridHeight()) {
            this.blockSpawnY = blockSpawnY;
        }
    }

    /**
     * Creates the game with an inputted grid, height, and milliseconds per tick
     * the block fall automatically.
     */
    public Game(int width, int height) {
        //System.out.println(width + "   " + height);
        this.gridWidth = width;
        this.gridHeight = height;

        this.blockSpawnX = this.gridWidth / 2;
        this.blockSpawnY = 0;

        this.arrayBlocks = new Block[this.gridWidth * this.gridHeight];
        this.tetrominoSpawner = new TetrominoSpawner(this);
    }

    /**
     * Creates a new block, and checks if there is a block already existing in
     * the block creation position to tell whether the game has ended or not.
     */
    public void createBlock(Color c) {
        Tetromino newTetromino = tetrominoSpawner.spawnTetromino(c);

        if (newTetromino == null) {
           this.gameRunning = false;
           System.out.println("END GAME");
        } else {
            this.tetrominoFalling = newTetromino;

            for (Block block : this.tetrominoFalling.getBlocks()) {
                updateBlock(block, this.getArrayBlocks());
            }
        }
    }

    /**
     * Steps the game, creating a block if needed, printing the game screen,
     * getting user input to move the falling block or set the block in place,
     * then moving it down. Pass the isUserInput boolean as true if it is a user
     * move, and pass the mapped int as a move. Otherwise pass as false for a
     * falldown tick.
     *
     * @param isUserInput
     * @param userInput
     */
    public void tick(boolean isUserInput, int userInput) {
        System.out.println("Tick!");
        // If no falling block exists or the current falling block has stopped falling (Collided), create a new block
        if (this.tetrominoFalling == null || !this.tetrominoFalling.getFalling()) {
            this.createBlock(getNextColor());
        }

        // Clear the reference from the previous array spot to the falling block)
        for (Block block : this.tetrominoFalling.getBlocks()) {
            removeBlock(block, this.getArrayBlocks());
        }
        
        if (!isUserInput || userInput == 0) {
            this.tetrominoFalling.move(0, 1);
        }

        // If the method was called with user input, parse it and then do the respective move.
        if (isUserInput) {
            System.out.println("User input detected: " + userInput);
            switch (userInput) {
                case 1:
                    this.tetrominoFalling.move(-1, 0);
                    break;
                case 2:
                    this.tetrominoFalling.move(1, 0);
                    break;
                case 3:
                    // CCW
                    this.tetrominoFalling.rotate(false);
                    break;
                case 4:
                    // CW
                    this.tetrominoFalling.rotate(true);
                    break;
            }
        }

        // Set a new reference to the falling block in its new position
        for (Block block : this.tetrominoFalling.getBlocks()) {
            updateBlock(block, this.getArrayBlocks());
        }
        
        // If true, call the printScreen method. Used for debugging.
        if (this.PRINT_TO_TERMINAL) {
            this.printScreen();
        }
        
        if (!this.tetrominoFalling.getFalling()){
            this.score += clearLines();
        }
    }
    
    /**
     * Checks if any lines need to be cleared (a horizontal line on the game grid is completely filled with blocks),
     * if so then those lines are cleared and the rest of the blocks are moved down. 
     * The return is the amount of lines cleared.
     *
     * @return int
     */
    public int clearLines() {
        int linesCleared = 0;
        
        for (int line = this.getGridHeight() - 1; line >= 0; line--) {
            boolean solid = true;
            
            // Checks if the current line is a solid line of blocks.
            for (int col = 0; col < this.getGridWidth(); col++) {
                if (this.getArrayBlocks()[col + line * this.getGridWidth()] == null) {
                    solid = false;
                    break;
                }
            }

            if (solid) {
                Block[] manipBlocks = this.getArrayBlocks();

                // Clear reference for any blocks to remove any collision problems.
                this.arrayBlocks = new Block[this.getGridWidth()*this.getGridHeight()];

                // Removes all the blocks in the line.
                for (int i = line * this.getGridWidth(); i < (line+1) * this.getGridWidth(); i++) {
                    manipBlocks[i] = null;
                }

                // Moves all blocks above the cleared line down.
                for (int i = line * this.getGridWidth() - 1; i >= 0; i--) {
                    Block b = manipBlocks[i];
                    if (b != null) {
                        removeBlock(b, manipBlocks);
                        b.moveDown();
                        updateBlock(b, manipBlocks);
                    }
                }

                linesCleared += 1;
                line += 1;
                this.arrayBlocks = manipBlocks;
            }

        }
        return linesCleared;
    }
    
    public void updateBlock(Block block, Block[] arrayBlocks) {
        if (block != null) {
            arrayBlocks[block.getPositionX() + (this.getGridWidth()*block.getPositionY())] = block;
        }
    }

    public void removeBlock(Block block, Block[] arrayBlocks) {
        if (block != null) {
            arrayBlocks[block.getPositionX() + (this.getGridWidth()*block.getPositionY())] = null;
        }
    }

    /**
     * Prints a representation of the current game board.
     */
    public void printScreen() {
        String screen = "";

        int count = 0;
        for (Block block : this.getArrayBlocks()) {
            if (block == null) {
                screen += ".";
            } else {
                screen += "x";
            }
            count += 1;
            if (count == this.getGridWidth()) {
                count = 0;
                screen += "\n";
            }
        }

        System.out.println(screen);
    }

    /**
     * Returns the width and height assigned to this game object.
     * @return 
     */
    @Override
    public String toString() {
        return this.gridHeight + "  " + this.gridWidth;
    }

    /**
     * Used to iterate over 7 different colors in a sequential fashion. Returns a javaFX color object.
     * @return next Color
     */
    public Color getNextColor() {
        switch (this.colorInt) {
            case 0:
                this.colorInt++;
                return Color.CYAN;
            case 1:
                this.colorInt++;
                return Color.BLUE;
            case 2:
                this.colorInt++;
                return Color.ORANGE;
            case 3:
                this.colorInt++;
                return Color.YELLOW;
            case 4:
                this.colorInt++;
                return Color.LIME;
            case 5:
                this.colorInt++;
                return Color.MAGENTA;
            case 6:
                this.colorInt = 0;
                return Color.RED;
        }
        Kaizen_85.newEvent("Color error, picking integer out of bounds.");
        //System.err.println("COLOR ERROR!");
        return null;

    }
    
    public int getScore(){
        return this.score;
    }
}
