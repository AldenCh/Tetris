package TetrisGUI;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Blocks.*;

public class HoldPanel extends JPanel{
	Block currentBlock;
	ArrayList<ArrayList<JLabel>> board = new ArrayList<ArrayList<JLabel>>();
	
	HoldPanel(boolean isEvenWidth){
		if (!isEvenWidth) {
			for (int i = 0; i < 4; i++) {
				ArrayList<JLabel> row = new ArrayList<JLabel>();
				for (int j = 0; j < 5; j++) {
					JLabel label = new JLabel();
					label.setOpaque(false);
					this.add(label);
					row.add(label);
				}
				board.add(row);
			}
			
			this.setLayout(new GridLayout(4, 5, 3, 3));
		}
		else {
			for (int i = 0; i < 4; i++) {
				ArrayList<JLabel> row = new ArrayList<JLabel>();
				for (int j = 0; j < 6; j++) {
					JLabel label = new JLabel();
					label.setOpaque(false);
					this.add(label);
					row.add(label);
				}
				board.add(row);
			}
			
			this.setLayout(new GridLayout(4, 6, 3, 3));
		}
	}
	
	HoldPanel(Block block, boolean isEvenWidth){
		if (!isEvenWidth) {
			for (int i = 0; i < 4; i++) {
				ArrayList<JLabel> row = new ArrayList<JLabel>();
				for (int j = 0; j < 5; j++) {
					JLabel label = new JLabel();
//					label.setBorder(BorderFactory.createLineBorder(Color.white));
					label.setOpaque(false);
					this.add(label);
					row.add(label);
				}
				board.add(row);
			}
			
			this.setLayout(new GridLayout(4, 5, 3, 3));
			
			currentBlock = block;
		}
		else {
			for (int i = 0; i < 4; i++) {
				ArrayList<JLabel> row = new ArrayList<JLabel>();
				for (int j = 0; j < 6; j++) {
					JLabel label = new JLabel();
//					label.setBorder(BorderFactory.createLineBorder(Color.white));
					label.setOpaque(false);
					this.add(label);
					row.add(label);
				}
				board.add(row);
			}
			
			this.setLayout(new GridLayout(4, 6, 3, 3));
			
			currentBlock = block;
		}
	}
	
	public void setTile(int x, int y, Color colour) {
		board.get(y).get(x).setOpaque(true);
		board.get(y).get(x).setBackground(colour);
	}
	
	public void clearTile(int x, int y) {
		board.get(y).get(x).setOpaque(false);
		board.get(y).get(x).setBackground(Color.white);
	}
	
	public Block getBlock() {
		return currentBlock;
	}
}
