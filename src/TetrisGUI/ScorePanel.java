package TetrisGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel{
	JLabel nameLabel = new JLabel("Names:");
	JLabel scoreLabel = new JLabel("Scores:");
	ArrayList<String> names;
	ArrayList<String> scores;
	ArrayList<JLabel> nameLabels = new ArrayList<JLabel>();
	ArrayList<JLabel> scoreLabels = new ArrayList<JLabel>();
	int counter = 0;
	int x = 50;
	int y = 75;
	
	ScorePanel(ArrayList<String> names, ArrayList<String> scores){
		this.names = names;
		this.scores = scores;
		this.setLayout(null);
		this.setBorder(BorderFactory.createLineBorder(Color.white));
		
		nameLabel.setBounds(210, 50, 100, 25);
		nameLabel.setOpaque(false);
		nameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		nameLabel.setVisible(true);
		nameLabel.setForeground(Color.green);
		
		scoreLabel.setBounds(510, 50, 100, 25);
		scoreLabel.setOpaque(false);
		scoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		scoreLabel.setVisible(true);
		scoreLabel.setForeground(Color.green);
		
		this.add(nameLabel);
		this.add(scoreLabel);
		
		if (names.size() > 10) {
			int counter = 1;
			for (int i = 0; i < 10; i++) {
				JLabel temp = new JLabel(Integer.toString(counter)+".");
				temp.setBounds(x+120, y, 50, 50);
				temp.setOpaque(false);
				temp.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
				temp.setHorizontalAlignment(JLabel.LEFT);
				temp.setVisible(true);
				temp.setForeground(Color.green);
				scoreLabels.add(temp);
				this.add(temp);
				y += 40;
				counter++;
			}
			
			y = 75;
			
			for (int i = 0; i < 10; i++) {
				JLabel temp = new JLabel(names.get(i));
				temp.setBounds(x, y, 400, 50);
				temp.setOpaque(false);
				temp.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
				temp.setHorizontalAlignment(JLabel.CENTER);
				temp.setVisible(true);
				temp.setForeground(Color.gray.brighter().brighter());
				nameLabels.add(temp);
				this.add(temp);
				y += 40;
			}
			
			x = 350;
			y = 75;
			
			for (int i = 0; i < 10; i++) {
				JLabel temp = new JLabel(scores.get(i));
				temp.setBounds(x, y, 400, 50);
				temp.setOpaque(false);
				temp.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
				temp.setHorizontalAlignment(JLabel.CENTER);
				temp.setVisible(true);
				temp.setForeground(Color.gray.brighter().brighter());
				scoreLabels.add(temp);
				this.add(temp);
				y += 40;
			}
		}
		else {
			for (int i = 0; i < names.size(); i++) {
				JLabel temp = new JLabel(names.get(i));
				temp.setBounds(x, y, 400, 50);
				temp.setOpaque(false);
				temp.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
				temp.setHorizontalAlignment(JLabel.CENTER);
				temp.setVisible(true);
				temp.setForeground(Color.gray.brighter().brighter());
				nameLabels.add(temp);
				this.add(temp);
				y += 40;
			}
			
			x = 350;
			y = 75;
			
			for (int i = 0; i < scores.size(); i++) {
				JLabel temp = new JLabel(scores.get(i));
				temp.setBounds(x, y, 400, 50);
				temp.setOpaque(false);
				temp.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
				temp.setHorizontalAlignment(JLabel.CENTER);
				temp.setVisible(true);
				temp.setForeground(Color.gray.brighter().brighter());
				scoreLabels.add(temp);
				this.add(temp);
				y += 40;
			}
		}
	}
	
	public void nextTen() {
		counter++;
		for (int i = counter*10; i < nameLabels.size(); i++) {
			nameLabels.get(i).setText(names.get(i));
		}
		for (int i = counter*10; i < scoreLabels.size(); i++) {
			scoreLabels.get(i).setText(scores.get(i));
		}
	}
	
	public void lastTen() {
		counter--;
		for (int i = counter*10; i < nameLabels.size(); i++) {
			nameLabels.get(i).setText(names.get(i));
		}
		for (int i = counter*10; i < scoreLabels.size(); i++) {
			scoreLabels.get(i).setText(scores.get(i));
		}
	}
	
	public int getCounter() {
		return counter;
	}
}
