package Blocks;

import java.util.ArrayList;

import TetrisGUI.*;

public abstract class Block {
	// Position of all Blocks will be based on either the centermost
	// tile in the block or the right center block on blocks with an
	// even width
	
	abstract public ArrayList<Integer[]> getTiles();
	abstract public void resetPosition(boolean isGame);
	abstract public void rotateLeft();
	abstract public void rotateRight();
	abstract public boolean canRotateLeft(GamePanel board);
	abstract public boolean canRotateRight(GamePanel board);
	abstract public void left();
	abstract public void right();
	abstract public void down();
	abstract public boolean canMoveDown(GamePanel board);
	abstract public boolean canMoveRight(GamePanel board);
	abstract public boolean canMoveLeft(GamePanel board);
	abstract public void update(GamePanel board);
	abstract public void update(HoldPanel board);
	abstract public void clear(GamePanel board);
	abstract public void clear(HoldPanel board);
	abstract public String getName();
}
