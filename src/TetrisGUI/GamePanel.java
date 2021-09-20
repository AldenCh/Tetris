package TetrisGUI;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Blocks.Block;

public class GamePanel extends JPanel{
	ArrayList<ArrayList<JLabel>> board = new ArrayList<ArrayList<JLabel>>();
	
	GamePanel(){
		for (int i = 0; i < 18; i++) {
			ArrayList<JLabel> row = new ArrayList<JLabel>();
			for (int j = 0; j < 9; j++) {
				JLabel label = new JLabel();
				label.setBorder(BorderFactory.createLineBorder(Color.white));
				label.setOpaque(false);
				label.setBackground(Color.white);
				this.add(label);
				row.add(label);
			}
			board.add(row);
		}
		this.setLayout(new GridLayout(18, 9, 3, 3));
	}
	
	public void move(int direction) {
		if (direction == 0) {		// If moving forward
			
		}
		else if (direction == 1) {	// If moving backward
			
		}
		else if (direction == 2) {	// If moving right
			
		}
		else if (direction == 3) {	// If moving left
			
		}
	}
	
	public Color getTile(int x, int y){
		return board.get(y).get(x).getBackground();
	}
	
	public void clearTile(int x, int y) {
		board.get(y).get(x).setOpaque(false);
		board.get(y).get(x).setBackground(Color.white);
	}
	
	public void setTile(int x, int y, Color colour) {
		board.get(y).get(x).setOpaque(true);
		board.get(y).get(x).setBackground(colour);
	}
	
	
}
