package backend_Models.tetris;

import javafx.scene.paint.Color;
import java.util.Random;

public class TetrominoSpawner {
	// Reference to the game that this spawner is a part of
	Game game;

	// This index used to pick the shape to spawn (Won't spawn the same shape until all of the others have been picked)
	int spawnIndex = 7;

	// The x coordinate on the game grid that the blocks should be spawned at or around.
	int spawnX;

	// The y coordinate on the game grid that the blocks should be spawned at or around.
	int spawnY;

	// A 3D array rerpresenting the different block shapes (1D: x,y | 2D: {x,y}, {x,y}, {x,y} | 3D: { { {x,y}, ... }, { {x,y}, ... }, ... })
	int[][][] shapes;

	// Used to randomize block order
	Random random = new Random();


	/**
	 * Sets reference to the game, sets coordinates for all the different shapes based on the game's spawnX and spawnY
	 *
	 * @param game
	 */
	public TetrominoSpawner(Game game) {
		this.game = game;
		this.spawnX = this.game.getBlockSpawnX();
		this.spawnY = this.game.getBlockSpawnY();

		this.shapes = new int[][][] { { {this.spawnX, this.spawnY}, {this.spawnX-1, this.spawnY}, {this.spawnX+1, this.spawnY}, {this.spawnX, this.spawnY+1} }, // T
								   { {this.spawnX, this.spawnY}, {this.spawnX-1, this.spawnY}, {this.spawnX+1, this.spawnY}, {this.spawnX-1, this.spawnY+1} }, // L
								   { {this.spawnX, this.spawnY}, {this.spawnX-1, this.spawnY}, {this.spawnX+1, this.spawnY}, {this.spawnX+1, this.spawnY+1} }, // J
								   { {this.spawnX, this.spawnY}, {this.spawnX+1, this.spawnY}, {this.spawnX, this.spawnY+1}, {this.spawnX-1, this.spawnY+1} }, // S
								   { {this.spawnX, this.spawnY}, {this.spawnX-1, this.spawnY}, {this.spawnX, this.spawnY+1}, {this.spawnX+1, this.spawnY+1} }, // Z
								   { {this.spawnX, this.spawnY}, {this.spawnX-1, this.spawnY}, {this.spawnX, this.spawnY+1}, {this.spawnX-1, this.spawnY+1} }, // O
								   { {this.spawnX, this.spawnY}, {this.spawnX-1, this.spawnY}, {this.spawnX+1, this.spawnY}, {this.spawnX+2, this.spawnY} } // I
							     };
	}

	/**
	 * Creates a new Tetromino piece. The piece will take the form of a randomly selected shape. 
	 * The same shape won't be chosen until all the other shapes have also been chosen. 
	 * Checks if the Tetromino is being created in the same place as another Tetromino.
	 * 
	 * @return Tetromino
	 *
	 * @param c
	 */
	public Tetromino spawnTetromino(Color c) {
		// If the index needs to be reset/has reached the end of the array.
		if (this.spawnIndex == this.shapes.length) {
			this.spawnIndex = 0;

			// Randomize shape order
			for (int i = 0; i < 20; i++) {
				int swapOne = random.nextInt(this.shapes.length);
				int swapTwo = random.nextInt(this.shapes.length);
				int[][] shapeToSwap = this.shapes[swapOne];

				this.shapes[swapOne] = this.shapes[swapTwo];
				this.shapes[swapTwo] = shapeToSwap;
			}
		}

		// New Block[] used to store the blocks of the soon to be created Tetromino.
		Block[] blocks = new Block[4];

		// Whether the Tetromino piece is straight (An I shape)
		boolean straight = true;

		// Adds a block from the shape in the current order to the Tetromino Block[]
		for (int i = 0; i < 4; i++) {
			// The straight piece doesn't have any pieces not on spawnY
			if (this.shapes[spawnIndex][i][1] != this.spawnY) {
				straight = false;
			}
			blocks[i] = new Block(this.game, c, this.shapes[spawnIndex][i][0], this.shapes[spawnIndex][i][1]);
		}

		this.spawnIndex += 1;

		Tetromino t = new Tetromino(blocks, straight);

		// Checks if the new Tetromino has been placed in any existing blocks.
		if (t.move(0, 0)) {
			return t;
		} else {
			return null;
		}


	}


}