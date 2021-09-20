package Blocks;

import java.awt.Color;
import java.util.ArrayList;

import TetrisGUI.*;

public class S_Block extends Block{
	// Position is based on the right half
	// *
	// **
	//  *
	//  ^
	
	// Orientations:
	// 1.  **	2. *	3.  **	4. *
	//	  **	   **	   **	   **
	//				*				*
	
	int[] position = {0,0};
	ArrayList<Integer[]> tiles = new ArrayList<Integer[]>();
	int orientation;
	String name = "S";
	
	public S_Block(boolean isGame){
		if (isGame) {
			orientation = 1;
			position[0] = 4;
			position[1] = 1;
			
			Integer[] temp = {3,1};
			tiles.add(temp);
			Integer[] temp2 = {4,1};
			tiles.add(temp2);
			Integer[] temp3 = {4,0};
			tiles.add(temp3);
			Integer[] temp4 = {5,0};
			tiles.add(temp4);
		}
		else {
			orientation = 1;
			position[0] = 2;
			position[1] = 2;
			
			Integer[] temp = {1,2};
			tiles.add(temp);
			Integer[] temp2 = {2,2};
			tiles.add(temp2);
			Integer[] temp3 = {2,1};
			tiles.add(temp3);
			Integer[] temp4 = {3,1};
			tiles.add(temp4);
		}
	}
	
	@Override
	public void resetPosition(boolean isGame) {
		orientation = 1;
		if (isGame) {
			tiles.clear();
			position[0] = 4;
			position[1] = 1;
			
			Integer[] temp = {3,1};
			tiles.add(temp);
			Integer[] temp2 = {4,1};
			tiles.add(temp2);
			Integer[] temp3 = {4,0};
			tiles.add(temp3);
			Integer[] temp4 = {5,0};
			tiles.add(temp4);
		}
		else {
			tiles.clear();
			position[0] = 2;
			position[1] = 2;
			
			Integer[] temp = {1,2};
			tiles.add(temp);
			Integer[] temp2 = {2,2};
			tiles.add(temp2);
			Integer[] temp3 = {2,1};
			tiles.add(temp3);
			Integer[] temp4 = {3,1};
			tiles.add(temp4);
		}
	}
	
	public ArrayList<Integer[]> getTiles(){
		return tiles;
	}
	
	@Override
	public void rotateLeft() {
		tiles.clear();
		if (orientation%2 == 1) {
			Integer[] temp1 = {position[0]-1, position[1]-1};
			tiles.add(temp1);
			Integer[] temp2 = {position[0]-1, position[1]};
			tiles.add(temp2);
			Integer[] temp3 = {position[0], position[1]};
			tiles.add(temp3);
			Integer[] temp4 = {position[0], position[1]+1};
			tiles.add(temp4);
		}
		else{
			Integer[] temp1 = {position[0]+1, position[1]-1};
			tiles.add(temp1);
			Integer[] temp2 = {position[0], position[1]-1};
			tiles.add(temp2);
			Integer[] temp3 = {position[0], position[1]};
			tiles.add(temp3);
			Integer[] temp4 = {position[0]-1, position[1]};
			tiles.add(temp4);
		}
		if (orientation == 1) {
			orientation = 4;
		}
		else {
			orientation--;
		}
	}

	@Override
	public void rotateRight() {
		tiles.clear();
		if (orientation%2 == 1) {
			Integer[] temp1 = {position[0]-1, position[1]-1};
			tiles.add(temp1);
			Integer[] temp2 = {position[0]-1, position[1]};
			tiles.add(temp2);
			Integer[] temp3 = {position[0], position[1]};
			tiles.add(temp3);
			Integer[] temp4 = {position[0], position[1]+1};
			tiles.add(temp4);
		}
		else{
			Integer[] temp1 = {position[0]+1, position[1]-1};
			tiles.add(temp1);
			Integer[] temp2 = {position[0], position[1]-1};
			tiles.add(temp2);
			Integer[] temp3 = {position[0], position[1]};
			tiles.add(temp3);
			Integer[] temp4 = {position[0]-1, position[1]};
			tiles.add(temp4);
		}
		if (orientation == 4) {
			orientation = 1;
		}
		else {
			orientation++;
		}
	}
	
	@Override
	public boolean canRotateLeft(GamePanel board) {
		if (orientation%2 == 0) {
			if (position[0] == 8) {
				return false;
			}
			else if (board.getTile(position[0], position[1]-1) == Color.black || 
					 board.getTile(position[0]+1, position[1]-1) == Color.black) {
				return false;
			}
		}
		else {
			if (position[1] == 17) {
				return false;
			}
			else if (board.getTile(position[0], position[1]+1) == Color.black || 
					 board.getTile(position[0]-1, position[1]-1) == Color.black) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean canRotateRight(GamePanel board) {
		if (orientation%2 == 0) {
			if (position[0] == 8) {
				return false;
			}
			else if (board.getTile(position[0], position[1]-1) == Color.black || 
					 board.getTile(position[0]+1, position[1]-1) == Color.black) {
				return false;
			}
		}
		else {
			if (position[1] == 17) {
				return false;
			}
			else if (board.getTile(position[0], position[1]+1) == Color.black || 
					 board.getTile(position[0]-1, position[1]-1) == Color.black) {
				return false;
			}
		}
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
		if (orientation%2 == 1){
			if (position[1] == 17) {
				return false;
			}
			else if (board.getTile(position[0]-1, position[1]+1) == Color.black ||
					 board.getTile(position[0], position[1]+1) == Color.black ||
					 board.getTile(position[0]+1, position[1]) == Color.black) {
				return false;
			}
		}
		else {
			if (position[1] == 16) {
				return false;
			}
			else if (board.getTile(position[0]-1, position[1]+1) == Color.black || 
					 board.getTile(position[0], position[1]+2) == Color.black) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean canMoveRight(GamePanel board) { 
		if (orientation%2 == 1) {
			if (position[0] == 7) {
				return false;
			}
			else if (board.getTile(position[0]+1, position[1]) == Color.black || 
					 board.getTile(position[0]+2, position[1]-1) == Color.black) {
				return false;
			}
		}
		else {
			if (position[0] == 8) {
				return false;
			}
			else if (board.getTile(position[0], position[1]-1) == Color.black || 
					 board.getTile(position[0]+1, position[1]) == Color.black || 
					 board.getTile(position[0]+1, position[1]+1) == Color.black) {
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean canMoveLeft(GamePanel board) {
		if (position[0] == 1) {
			return false;
		}
		else if (orientation%2 == 1) {
			if (board.getTile(position[0]-2, position[1]) == Color.black || 
				board.getTile(position[0]-1, position[1]-1) == Color.black) {
				return false;
			}
		}
		else {
			if (board.getTile(position[0]-2, position[1]-1) == Color.black || 
				board.getTile(position[0]-2, position[1]) == Color.black || 
				board.getTile(position[0]-1, position[1]+1) == Color.black) {
				return false;
			}
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
