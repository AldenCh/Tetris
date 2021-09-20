package Blocks;

import java.awt.Color;
import java.util.ArrayList;

import TetrisGUI.*;

public class O_Block extends Block{
	// Position is based on the right side of the O
	// **
	// **
	//  ^
	
	// Orientations:
	// 1. **	2. **	3. **	4. **
	//	  **	   **	   **	   **
	
	int[] position = {0,0};
	ArrayList<Integer[]> tiles = new ArrayList<Integer[]>();
	int orientation;
	String name = "O";
	
	public O_Block(boolean isGame){
		if (isGame) {
			orientation = 1;
			position[0] = 4;
			position[1] = 1;
			
			for (int i = 0; i < 2; i++) {
				for(int j = 3; j < 5; j++) {
					Integer[] temps = {j,i};
					tiles.add(temps);
				}
			}
		}
		else {
			orientation = 1;
			position[0] = 3;
			position[1] = 2;
			
			for (int i = 1; i < 3; i++) {
				for(int j = 2; j < 4; j++) {
					Integer[] temps = {j,i};
					tiles.add(temps);
				}
			}
		}
	}
	
	@Override
	public void resetPosition(boolean isGame) {
		orientation = 1;
		if (isGame) {
			tiles.clear();
			position[0] = 4;
			position[1] = 1;
			
			for (int i = 0; i < 2; i++) {
				for(int j = 3; j < 5; j++) {
					Integer[] temps = {j,i};
					tiles.add(temps);
				}
			}
		}
		else {
			tiles.clear();
			position[0] = 3;
			position[1] = 2;
			
			for (int i = 1; i < 3; i++) {
				for (int j = 2; j < 4; j++) {
					Integer[] temp = {j,i};
					tiles.add(temp);
				}
			}
		}
	}
	
	public ArrayList<Integer[]> getTiles(){
		return tiles;
	}
	
	@Override
	public void rotateLeft() {
	}

	@Override
	public void rotateRight() {
	}
	
	@Override
	public boolean canRotateLeft(GamePanel board) {
		return true;
	}
	
	@Override
	public boolean canRotateRight(GamePanel board) {
		return true;
	}
	
	@Override
	public void left() {
		for (int i = 0; i < tiles.size(); i++) {
			Integer[] temp = {tiles.get(i)[0]-1, tiles.get(i)[1]};
			tiles.set(i, temp);
		}
		position[0] = position[0]-1;
	}
	
	@Override
	public void right() {
		for (int i = 0; i < tiles.size(); i++) {
			Integer[] temp = {tiles.get(i)[0]+1, tiles.get(i)[1]};
			tiles.set(i, temp);
		}
		position[0] = position[0]+1;
	}
	
	@Override
	public void down() {
		for (int i = 0; i < tiles.size(); i++) {
			Integer[] temp = {tiles.get(i)[0], tiles.get(i)[1]+1};
			tiles.set(i, temp);
		}
		position[1] = position[1]+1;
	}

	@Override
	public boolean canMoveDown(GamePanel board) {
		if (position[1] == 17) {
			return false;
		}
		else if (board.getTile(tiles.get(2)[0], tiles.get(2)[1]+1) == Color.black || 
				 board.getTile(tiles.get(3)[0], tiles.get(3)[1]+1) == Color.black) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean canMoveRight(GamePanel board) { 
		if (tiles.get(3)[0] == 8) {
			return false;
		}
		else if (board.getTile(tiles.get(1)[0]+1, tiles.get(1)[1]) == Color.black || 
				 board.getTile(tiles.get(3)[0]+1, tiles.get(3)[1]) == Color.black) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean canMoveLeft(GamePanel board) {
		if (tiles.get(0)[0] == 0) {
			return false;			
		}
		else if (board.getTile(tiles.get(0)[0]-1, tiles.get(0)[1]) == Color.black || 
				 board.getTile(tiles.get(2)[0]-1, tiles.get(2)[1]) == Color.black) {
			return false;
		}
		return true;
	}

	@Override
	public void update(GamePanel board) {
		for(int i = 0; i < 4; i++) {
			board.setTile(tiles.get(i)[0], tiles.get(i)[1], Color.black);
		}
	}
	
	@Override
	public void update(HoldPanel board) {
		for(int i = 0; i < 4; i++) {
			board.setTile(tiles.get(i)[0], tiles.get(i)[1], Color.black);
		}
	}

	@Override
	public void clear(GamePanel board) {
		for(int i = 0; i < 4; i++) {
			board.clearTile(tiles.get(i)[0], tiles.get(i)[1]);
		}
	}
	
	public void clear(HoldPanel board) {
		for (int i = 0; i < 4; i++) {
			board.clearTile(tiles.get(i)[0], tiles.get(i)[1]);
		}
	}
	
	public String getName() {
		return name;
	}
}
