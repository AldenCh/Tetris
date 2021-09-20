package TetrisGUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import Blocks.*;

public class TetrisFrame extends JFrame implements KeyListener{
	ImageIcon logo = new ImageIcon("tetris.png");
	JLayeredPane layeredPane = new JLayeredPane();
	GamePanel game;
	QueuePanel queue;
	QueuePanel hold;
	Block currentBlock;
	Timer timer = new Timer();
	TimerTask moveDown;
	int delay = 1000;
	long start = System.currentTimeMillis();
	boolean held = false;
	int score = 0;
	int level = 1;
	JLabel scoreLabel;
	JLabel levelLabel;
	JLabel holdLabel;
	JLabel queueLabel;
	
	TetrisFrame(){
		layeredPane.setBounds(0, 0, 810, 1000);
		layeredPane.setOpaque(true);
		
		game = new GamePanel();
		game.setBounds(170, 50, 430, 790);
		game.setOpaque(false);
		
		queue = new QueuePanel(false);
		queue.setBounds(620, 50, 150, 450);
		queue.setOpaque(false);
		
		hold = new QueuePanel(true);
		hold.setBounds(10, 50, 150, 125);
		hold.setOpaque(false);
		
		BackgroundPanel background = new BackgroundPanel("gamebackground.jpg");
		background.setBounds(0, 0, 810, 1000);
		
		scoreLabel = new JLabel("Score: " + score);
		scoreLabel.setFont(new Font("Onyx", Font.BOLD, 40));
		scoreLabel.setForeground(Color.white);
		scoreLabel.setOpaque(false);
		scoreLabel.setBounds(340, 850, 150, 50);
		
		levelLabel = new JLabel("Level: " + level);
		levelLabel.setFont(new Font("Onyx", Font.BOLD, 40));
		levelLabel.setForeground(Color.white);
		levelLabel.setOpaque(false);
		levelLabel.setBounds(340, 5, 150, 50);
		
		holdLabel = new JLabel("Hold: ");
		holdLabel.setFont(new Font("Onyx", Font.BOLD, 40));
		holdLabel.setForeground(Color.white);
		holdLabel.setOpaque(false);
		holdLabel.setBounds(60, 5, 150, 50);
		
		queueLabel = new JLabel("Queue");
		queueLabel.setFont(new Font("Onyx", Font.BOLD, 40));
		queueLabel.setForeground(Color.white);
		queueLabel.setOpaque(false);
		queueLabel.setBounds(660, 5, 150, 50);
		
		this.setBounds(550, 50, 810, 1000);
		this.setIconImage(logo.getImage());
		this.setLayout(null);
		this.setTitle("Tetris");

		layeredPane.add(background, Integer.valueOf(0));
		layeredPane.add(scoreLabel, Integer.valueOf(1));
		layeredPane.add(holdLabel, Integer.valueOf(1));
		layeredPane.add(queueLabel, Integer.valueOf(1));
		layeredPane.add(levelLabel, Integer.valueOf(1));
		layeredPane.add(game, Integer.valueOf(2));
		layeredPane.add(queue, Integer.valueOf(2));
		layeredPane.add(hold, Integer.valueOf(2));
		this.add(layeredPane);
		this.addKeyListener(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		
		currentBlock = new T_Block(true);
		currentBlock.update(game);
		
		moveDown = new TimerTask() {
			@Override
			public void run() {
				tick();
			}
		};
		
		timer.scheduleAtFixedRate(moveDown, 500, delay);
	}

	public void newBlock() {
		currentBlock = queue.pop();
		layeredPane.remove(queue);
		queue = new QueuePanel(queue.getQueue());
		queue.setBounds(620, 50, 150, 450);
		queue.setOpaque(false);
		layeredPane.add(queue, Integer.valueOf(2));
	}
	
	public boolean checkSpawn() {
		if (game.getTile(4, 1) == Color.black) {
			return false;
		}
		return true;
	}
	
	public void tick() {
		if (currentBlock.canMoveDown(game)) {
			currentBlock.clear(game);
			currentBlock.down();
			currentBlock.update(game);
		}
		else {
			for(int i = 0; i < 4; i++) {
				if (checkRow(currentBlock.getTiles().get(i))) {
					score += 1000;
					clearRow(currentBlock.getTiles().get(i)[1]);
				}
			}
			held = false;
			score += 100;
			long temp = System.currentTimeMillis();
			if (temp-start > 30000 && level != 10) {
				start = System.currentTimeMillis();
				level++;
				delay -= 100;
				levelLabel.setText("Level: " + level);
				timer.cancel();
				timer = new Timer();
				moveDown = new TimerTask() {
					@Override
					public void run() {
						tick();
					}
				};
				timer.scheduleAtFixedRate(moveDown, 500, delay);
			}
			newBlock();
			if (checkSpawn()) {
				currentBlock.resetPosition(true);
				currentBlock.update(game);
			}
			else {
				new EndFrame(score);
				timer.cancel();
				this.dispose();
			}
		}
		
		scoreLabel.setBounds(340-(score/1000), 850, 150+(score/1000), 50);
		scoreLabel.setText("Score: " + score);
	}
	
	public boolean checkRow(Integer[] tile) {
		boolean filled = true;
		for (int i = 0; i < 9; i++) {
			if (game.getTile(i, tile[1]) != Color.black) {
				filled = false;
			}
		}
		return filled;
	}
	
	public void clearRow(int row) {
		for (int i = 0; i < 9; i++) {
			game.clearTile(i, row);
		}
		
		for (int i = row; i > 1; i--) {
			for (int j = 0; j < 9; j++) {
				game.setTile(j, i, game.getTile(j, i-1));
				if (game.getTile(j, i) == Color.white) {
					game.clearTile(j, i);
				}
			}
		}
		
		for (int j = 0; j < 9; j++) {
			game.clearTile(j, 0);
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		switch(e.getKeyChar()) {
		case 'a': 
			if (currentBlock.canMoveLeft(game)) {
				currentBlock.clear(game);
				currentBlock.left();
				currentBlock.update(game);
			}
			break;
		case 's': 
			if (currentBlock.canMoveDown(game)) {
				currentBlock.clear(game);
				currentBlock.down();
				currentBlock.update(game);
			}
			break;
		case 'd': 
			if (currentBlock.canMoveRight(game)) {
				currentBlock.clear(game);
				currentBlock.right();
				currentBlock.update(game);
			}
			break;	
		case 'w': 
			if (currentBlock.canRotateRight(game)) {
				currentBlock.clear(game);
				currentBlock.rotateRight();
				currentBlock.update(game);
			}
			break;
		case 'q': 
			if (currentBlock.canRotateLeft(game)) {
				currentBlock.clear(game);
				currentBlock.rotateLeft();
				currentBlock.update(game);
			}
			break;
		case 'e': 
			if (!held) {
				if (!hold.getOccupied()) {
					currentBlock.clear(game);
					layeredPane.remove(hold);
					hold = new QueuePanel(currentBlock);
					hold.getQueue().get(0).getBlock().resetPosition(false);
					hold.getQueue().get(0).getBlock().update(hold.getQueue().get(0));
					hold.setBounds(10, 50, 150, 125);
					hold.setOpaque(false);
					layeredPane.add((hold), Integer.valueOf(2));
					newBlock();
					currentBlock.resetPosition(true);
					currentBlock.update(game);
				}
				else {
					currentBlock.clear(game);
					Block temp = hold.getQueue().get(0).getBlock();
					layeredPane.remove(hold);
					currentBlock.resetPosition(false);
					hold = new QueuePanel(currentBlock);
					hold.getQueue().get(0).getBlock().resetPosition(false);
					hold.getQueue().get(0).getBlock().update(hold.getQueue().get(0));
					hold.setBounds(10, 50, 150, 125);
					hold.setOpaque(false);
					layeredPane.add((hold), Integer.valueOf(2));
					currentBlock = temp;
					currentBlock.resetPosition(true);
					currentBlock.update(game);
				}
				held = true;
			}
			else {
				
			}
			break;
		case ' ':
			currentBlock.clear(game);
			while(currentBlock.canMoveDown(game)) {
				currentBlock.down();
			}
			currentBlock.update(game);
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
