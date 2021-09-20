package TetrisGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.BevelBorder;

public class StartFrame extends JFrame implements ActionListener{
	ImageIcon logo = new ImageIcon("tetris.png");
	JLayeredPane layeredPane = new JLayeredPane();
	JLabel WKey = new JLabel("W");
	JLabel AKey = new JLabel("A");
	JLabel SKey = new JLabel("S");
	JLabel DKey = new JLabel("D");
	JLabel QKey = new JLabel("Q");
	JLabel EKey = new JLabel("E");
	JLabel SpaceKey = new JLabel();
	JLabel controls = new JLabel();
	JLabel info = new JLabel();
	JButton start = new JButton("Start");
	
	StartFrame(){
		this.setBounds(350, 150, 1200, 800);
		this.setIconImage(logo.getImage());
		this.setLayout(null);
		this.setTitle("Tetris");
		
		layeredPane.setBounds(0, 0, 1200, 800);
		layeredPane.setOpaque(true);
		
		BackgroundPanel background = new BackgroundPanel("startbackground.jpeg");
		background.setBounds(0, 0, 1200, 800);
		
		WKey.setForeground(Color.white);
		WKey.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.white, Color.gray));
		WKey.setHorizontalAlignment(JLabel.CENTER);
		WKey.setFont(new Font("Helvetica", Font.PLAIN, 25));
		WKey.setBounds(560, 290, 40, 40);
		WKey.setOpaque(false);
		
		AKey.setForeground(Color.white);
		AKey.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.white, Color.gray));
		AKey.setHorizontalAlignment(JLabel.CENTER);
		AKey.setFont(new Font("Helvetica", Font.PLAIN, 25));
		AKey.setBounds(520, 330, 40, 40);
		AKey.setOpaque(false);
		
		SKey.setForeground(Color.white);
		SKey.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.white, Color.gray));
		SKey.setHorizontalAlignment(JLabel.CENTER);
		SKey.setFont(new Font("Helvetica", Font.PLAIN, 25));
		SKey.setBounds(560, 330, 40, 40);
		SKey.setOpaque(false);
		
		DKey.setForeground(Color.white);
		DKey.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.white, Color.gray));
		DKey.setHorizontalAlignment(JLabel.CENTER);
		DKey.setFont(new Font("Helvetica", Font.PLAIN, 25));
		DKey.setBounds(600, 330, 40, 40);
		DKey.setOpaque(false);
		
		QKey.setForeground(Color.white);
		QKey.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.white, Color.gray));
		QKey.setHorizontalAlignment(JLabel.CENTER);
		QKey.setFont(new Font("Helvetica", Font.PLAIN, 25));
		QKey.setBounds(520, 290, 40, 40);
		QKey.setOpaque(false);
		
		EKey.setForeground(Color.white);
		EKey.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.white, Color.gray));
		EKey.setHorizontalAlignment(JLabel.CENTER);
		EKey.setFont(new Font("Helvetica", Font.PLAIN, 25));
		EKey.setBounds(600, 290, 40, 40);
		EKey.setOpaque(false);
		
		SpaceKey.setForeground(Color.white);
		SpaceKey.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.white, Color.gray));
		SpaceKey.setHorizontalAlignment(JLabel.CENTER);
		SpaceKey.setFont(new Font("Helvetica", Font.PLAIN, 25));
		SpaceKey.setBounds(520, 370, 120, 40);
		SpaceKey.setOpaque(false);
		
		controls.setText("<html>Q is to rotate left.<br/>"
					   + "W is to rotate right.<br/>"
					   + "A is to move left.<br/>"
					   + "S is to move down.<br/>"
					   + "D is to move right.<br/>"
					   + "E is to hold a block.<br/>"
					   + "SPACE is to drop a block.<html/>");
		controls.setBounds(650, 200, 300, 300);
		controls.setHorizontalAlignment(JLabel.CENTER);
		controls.setVisible(true);
		controls.setForeground(Color.white);
		controls.setOpaque(false);
		controls.setFont(new Font("Onyx", Font.PLAIN, 25));
		
		start.setFont(new Font("Onyx", Font.PLAIN, 25));
		start.setBounds(530, 450, 100, 50);
		start.setFocusable(false);
		start.addActionListener(this);
		
		info.setOpaque(false);
		info.setForeground(Color.white);
		info.setHorizontalAlignment(JLabel.CENTER);
		info.setFont(new Font("Onyx", Font.PLAIN, 25));
		info.setText("<html><center>I'm gonna assume you know how to play the game,<br/>"
				   + "if you don't you shouldn't even be here.<br/>"
				   + "- Each block placed will earn you 100 points<br/>"
				   + "- Each row cleared will earn you 1000 points<br/>(no bonuses bite me)<br/>"
				   + "- Every 30 seconds the level will increase and the delay<br/>"
				   + "between automatic drops will decrease,<br/>the maximum level will be 10.</center><html/>");
		info.setBounds(100, 100, 400, 500);
		
		layeredPane.add(WKey, Integer.valueOf(1));
		layeredPane.add(AKey, Integer.valueOf(1));
		layeredPane.add(SKey, Integer.valueOf(1));
		layeredPane.add(DKey, Integer.valueOf(1));
		layeredPane.add(QKey, Integer.valueOf(1));
		layeredPane.add(EKey, Integer.valueOf(1));
		layeredPane.add(SpaceKey, Integer.valueOf(1));
		layeredPane.add(controls, Integer.valueOf(1));
		layeredPane.add(start, Integer.valueOf(1));
		layeredPane.add(info, Integer.valueOf(1));
		layeredPane.add(background, Integer.valueOf(0));
		this.add(layeredPane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == start) {
			new TetrisFrame();
			this.dispose();
		}
	}
}
