package backend_Models.tetris;

import javafx.scene.paint.Color;

/**
 * Representation of a single cube in tetris. Can be moved, as well as check for
 * collisions with other blocks.
 *
 * @author jacob-huber
 */
public class Block {
    // X and Y Position on the game grid

    private int positionX;
    private int positionY;

    private boolean falling = true;

    // Reference for the game it's a part of.
    private Game game;

    // Reference for the tetromino it's a part of.
    private Tetromino tetromino;

    private Color color;

    /**
     * Creates a new block and assigns it a given game. Position x and y is set
     * based on game.
     *
     * @param game
     */
    public Block(Game game) {
        this.game = game;
        this.setPositionX(this.game.getBlockSpawnX());
        this.setPositionY(this.game.getBlockSpawnY());
    }

    /**
     * Creates a shallow copy of another block.
     *
     * @param block
     */
    public Block(Block block) {
        this.game = block.game;
        this.tetromino = block.getTetromino();
        this.positionX = block.getPositionX();
        this.positionY = block.getPositionY();
        this.color = block.getColor();
    }

    public Block(Game game, Color c, int x, int y) {
        this.game = game;
        this.positionX = x;
        this.positionY = y;
        this.color = c;
    }

    // Getters for x and y coordinates, the game, if it's falling, and its tetromino. 
    public int getPositionX() {
        return this.positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }

    public boolean getFalling() {
        return this.falling;
    }

    public Game getGame() {
        return this.game;
    }

    public Tetromino getTetromino() {
        return this.tetromino;
    }

    /**
     * Sets the block's X position as long as it is not colliding with another
     * block or a wall
     *
     * @return boolean
     *
     * @param positionX
     */
    public boolean setPositionX(int positionX) {
        if (positionX >= 0 && positionX < game.gridWidth) {
            if (this.checkColliding(positionX, this.getPositionY()) == null) {
                this.positionX = positionX;
                return true;
            }
        }
        return false;
    }

    /**
     * Sets the block's Y position as long as it is not colliding with another
     * block or a wall
     *
     * @return boolean
     *
     * @param positionY
     */
    public boolean setPositionY(int positionY) {
        if (positionY >= 0 && positionY < game.gridHeight) {
            if (this.checkColliding(this.getPositionX(), positionY) == null) {
                this.positionY = positionY;

                return true;
            }
        }
        return false;
    }

    // Setters for falling, and tetromino
    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public void setTetromino(Tetromino tetromino) {
        this.tetromino = tetromino;
    }

    /**
     * Moves the block down, if it collides set its falling to false.
     */
    public void moveDown() {
        if (!this.setPositionY(this.getPositionY() + 1)) {
            this.setFalling(false);
        }
    }

    /**
     * Moves the block left.
     */
    public void moveLeft() {
        this.setPositionX(this.getPositionX() - 1);
    }

    /**
     * Moves the block right.
     */
    public void moveRight() {
        this.setPositionX(this.getPositionX() + 1);
    }

    /**
     * Moves the block continuously down until it collides with something.
     */
    public void placeBlock() {
        while (this.getFalling()) {
            this.moveDown();
        }
    }

    /**
     * Checks whether there is a collision at the given position with another
     * block.
     *
     * @return block
     *
     * @param positionX
     * @param positionY
     */
    public Block checkColliding(int positionX, int positionY) {
        Block block = this.game.getArrayBlocks()[positionX + (this.getGame().getGridWidth()*positionY)];
		if (block != this && block != null) {
			return block;
		}
		return null;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color c) {
        this.color = c;
    }
}
