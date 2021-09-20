package TetrisGUI;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import Blocks.*;

public class QueuePanel extends JPanel{
	ArrayList<HoldPanel> queue = new ArrayList<HoldPanel>();
	boolean occupied = false;
	
	QueuePanel(boolean isHold){
		if (!isHold) {
			Random rand = new Random();
			ArrayList<Integer> choices = new ArrayList<Integer>();
			for (int i = 0; i < 5; i++) {
				while(true) {
					Integer choice = Integer.valueOf(rand.nextInt(7));
					boolean unique = true;
					for (Integer k : choices) {
						if (choice == k) {
							unique = false;
						}
					}
					if (unique) {
						choices.add(choice);
						break;
					}
				}
			}
			
			for (Integer i : choices) {
				HoldPanel element;
				switch(i.intValue()) {
				case(0): 
					I_Block IBlock = new I_Block(false);
					element = new HoldPanel(IBlock, true);
					IBlock.update(element);
					element.setOpaque(false);
					queue.add(element);
					break;
				case(1):
					J_Block JBlock = new J_Block(false);
					element = new HoldPanel(JBlock, false);
					JBlock.update(element);
					element.setOpaque(false);
					queue.add(element);
					break;
				case(2):
					L_Block LBlock = new L_Block(false);
					element = new HoldPanel(LBlock, false);
					LBlock.update(element);
					element.setOpaque(false);
					queue.add(element);
					break;
				case(3):
					O_Block OBlock = new O_Block(false);
					element = new HoldPanel(OBlock, true);
					OBlock.update(element);
					element.setOpaque(false);
					queue.add(element);
					break;
				case(4):
					S_Block SBlock = new S_Block(false);
					element = new HoldPanel(SBlock, false);
					SBlock.update(element);
					element.setOpaque(false);
					queue.add(element);
					break;
				case(5):
					T_Block TBlock = new T_Block(false);
					element = new HoldPanel(TBlock, false);
					TBlock.update(element);
					element.setOpaque(false);
					queue.add(element);
					break;
				case(6):
					Z_Block ZBlock = new Z_Block(false);
					element = new HoldPanel(ZBlock, false);
					ZBlock.update(element);
					element.setOpaque(false);
					queue.add(element);
					break;
				}
			}
			
			for(int i = 0; i < queue.size(); i++) {
				this.add(queue.get(i));
			}
			
			this.setLayout(new GridLayout(5, 1, 2, 0));
			this.setOpaque(false);
			this.setBorder(BorderFactory.createLineBorder(Color.white));
		}
		else {
			HoldPanel hold = new HoldPanel(false);
			hold.setOpaque(false);
			this.add(hold);
			
			this.setLayout(new GridLayout(1, 1, 2, 0));
			this.setOpaque(false);
			this.setBorder(BorderFactory.createLineBorder(Color.white));
		}
	}
	
	QueuePanel(Block block){
		occupied = true;
		HoldPanel hold;
		if (block.getName() == "O" || block.getName() == "I") {
			hold = new HoldPanel(block, true);
		}
		else {
			hold = new HoldPanel(block, false);
		}
		hold.setOpaque(false);
		queue.add(hold);
		this.add(hold);
		this.setLayout(new GridLayout(1, 1, 2, 0));
		this.setOpaque(false);
		this.setBorder(BorderFactory.createLineBorder(Color.white));
	}
	
	QueuePanel(ArrayList<HoldPanel> oldQueue){
		queue = oldQueue;
		this.add(getRandomBlock());
		for (HoldPanel blocks : queue) {
			blocks.getBlock().update(blocks);
			blocks.setOpaque(false);
			this.add(blocks);
		}
		
		this.setLayout(new GridLayout(5, 1, 2, 0));
		this.setOpaque(false);
		this.setBorder(BorderFactory.createLineBorder(Color.white));
	}
	
	public HoldPanel getRandomBlock() {
		HoldPanel element = null;
		Random rand = new Random();
		switch(rand.nextInt(7)) {
		case(0): 
			I_Block IBlock = new I_Block(false);
			element = new HoldPanel(IBlock, true);
			IBlock.update(element);
			element.setOpaque(false);
			queue.add(element);
			break;
		case(1):
			J_Block JBlock = new J_Block(false);
			element = new HoldPanel(JBlock, false);
			JBlock.update(element);
			element.setOpaque(false);
			queue.add(element);
			break;
		case(2):
			L_Block LBlock = new L_Block(false);
			element = new HoldPanel(LBlock, false);
			LBlock.update(element);
			element.setOpaque(false);
			queue.add(element);
			break;
		case(3):
			O_Block OBlock = new O_Block(false);
			element = new HoldPanel(OBlock, true);
			OBlock.update(element);
			element.setOpaque(false);
			queue.add(element);
			break;
		case(4):
			S_Block SBlock = new S_Block(false);
			element = new HoldPanel(SBlock, false);
			SBlock.update(element);
			element.setOpaque(false);
			queue.add(element);
			break;
		case(5):
			T_Block TBlock = new T_Block(false);
			element = new HoldPanel(TBlock, false);
			TBlock.update(element);
			element.setOpaque(false);
			queue.add(element);
			break;
		case(6):
			Z_Block ZBlock = new Z_Block(false);
			element = new HoldPanel(ZBlock, false);
			ZBlock.update(element);
			element.setOpaque(false);
			queue.add(element);
			break;
		}
		return element;
	}
	
	public Block pop(){
		Block temp = queue.get(0).getBlock();
		queue.remove(0);
		this.remove(queue.get(0));
		return temp;
	}
	
	public ArrayList<HoldPanel> getQueue(){
		return queue;
	}
	
	public boolean getOccupied() {
		return occupied;
	}
}
