package TetrisGUI;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundPanel extends JPanel{
	ImageIcon background;
	
	BackgroundPanel(String fileName){
//		background = new ImageIcon("resources/"+fileName);
		background = new ImageIcon(getClass().getClassLoader().getResource(fileName));
		this.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.drawImage(background.getImage(), 0, 0, null);
	}
}
