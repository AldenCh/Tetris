package TetrisGUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class EndFrame extends JFrame implements ActionListener{
	ImageIcon logo = new ImageIcon("tetris.png");
	JLayeredPane layeredPane = new JLayeredPane();
	JLabel prompt;
	JTextField textField;
	JButton submit;
	int score;
	ScorePanel scoreDisplay;
	ArrayList<String> names;
	ArrayList<String> scores;
	JLabel topTen;
	JLabel yourScore;
	int place;
	
	EndFrame(int score){
		this.score = score;
		layeredPane.setBounds(0, 0, 1200, 695);
		layeredPane.setOpaque(true);
		
		this.setBounds(350, 150, 1200, 695);
		this.setIconImage(logo.getImage());
		this.setLayout(null);
		this.setTitle("Tetris");
		
		BackgroundPanel background = new BackgroundPanel("endbackgroundalt.jpg");
		background.setBounds(0, 0, 1200, 695);
		
		prompt = new JLabel("<html><center>You got a score of " + score + " points!<br/> What is your name?</center></html>");
		prompt.setForeground(Color.red);
		prompt.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		prompt.setOpaque(false);
		int spacing = 0;
		int temp = score;
		while(temp%10 == 0) {
			temp /= 10;
			spacing+=12;
		}
		prompt.setBounds(480-spacing, 200, 300+spacing, 200);
		prompt.setVisible(true);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(JTextField.CENTER);
		textField.setForeground(Color.black);
		textField.setFont(new Font("Onyx", Font.BOLD, 40));
		textField.setOpaque(false);
		textField.setBounds(455, 360, 300, 90);
		textField.setVisible(true);
		
		submit = new JButton("Submit");
		submit.setBounds(560, 460, 100, 25);
		submit.addActionListener(this);
		submit.setFocusable(false);
		
		layeredPane.add(background, Integer.valueOf(0));
		layeredPane.add(prompt, Integer.valueOf(1));
		layeredPane.add(textField, Integer.valueOf(1));
		layeredPane.add(submit, Integer.valueOf(1));
		this.add(layeredPane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			if (textField.getText().length() > 25) {
				prompt.setText("<html><center>Please enter a name that is<br/>25 characters or less.<center/></html>");
				prompt.setForeground(Color.red);
				prompt.setBounds(440, 260, 350, 100);
				textField.setText("");
			}
			else if (textField.getText().length() == 0) {
				prompt.setText("Please enter a name.");
				prompt.setForeground(Color.red);
				prompt.setBounds(480, 260, 600, 100);
				textField.setText("");
			}
			else {
				try {
					names = new ArrayList<String>();
					scores = new ArrayList<String>();
					URL url = getClass().getClassLoader().getResource("scores.txt");
					BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
					String line;
					
					while ((line = br.readLine()) != null) {
						for (int i = 0; i < line.length(); i++) {
							if (line.charAt(i) == ',') {
								names.add(line.substring(0,i));
								scores.add(line.substring(i+1));
								break;
							}
						}
					}
					
					br.close();
					names.add(textField.getText());
					scores.add(Integer.toString(score));
					place = names.size();
					for (int i = 0; i < names.size(); i++) {
						System.out.println(names.get(i));
						System.out.println(scores.get(i));
					}
					ArrayList<ArrayList<String>> temp = sort(scores, names);
					names = temp.get(0);
					scores = temp.get(1);
					
					place++;
					
					topTen = new JLabel("Top Ten Scores");
					topTen.setOpaque(false);
					topTen.setBounds(505, 50, 300, 50);
					topTen.setForeground(Color.green);
					topTen.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
					
					if (place%10 == 1) {
						yourScore = new JLabel("You are in "+place+"st place with "+score+" points!");
					}
					else if (place%10 == 2) {
						yourScore = new JLabel("You are in "+place+"nd place with "+score+" points!");
					}
					else {
						yourScore = new JLabel("You are in "+place+"th place with "+score+" points!");
					}
					yourScore.setOpaque(false);
					yourScore.setBounds(380-(score%100), 575, 600+(score%100), 50);
					yourScore.setForeground(Color.green);
					yourScore.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
					
					scoreDisplay = new ScorePanel(names, scores);
					scoreDisplay.setOpaque(false);
					scoreDisplay.setBounds(200, 100, 800, 550);
					scoreDisplay.setVisible(true);
					
					layeredPane.remove(prompt);
					layeredPane.remove(textField);
					layeredPane.remove(submit);
					layeredPane.add(scoreDisplay, Integer.valueOf(2));
					layeredPane.add(topTen, Integer.valueOf(2));
					layeredPane.add(yourScore, Integer.valueOf(2));
					
					FileWriter writer = new FileWriter("resources/scores.txt");
					String outputString = "";
					outputString = names.get(0)+","+scores.get(0);
					writer.write(outputString);
					for (int i = 1; i < names.size(); i++) {
						outputString = "";
						outputString += "\n"+names.get(i)+","+scores.get(i);
						writer.append(outputString);
					}
					writer.close();
				} catch (IOException e1) { 
					e1.printStackTrace();
				}
			}
		}
	}
	
	public ArrayList<ArrayList<String>> sort(ArrayList<String> scores, ArrayList<String> names){
		ArrayList<String> tempScores = scores;
		ArrayList<String> tempNames = names;
		int[] score = new int[tempScores.size()];
		for (int i = 0; i < tempScores.size(); i++) {
			score[i] = Integer.valueOf(tempScores.get(i));
		}
		
		for (int i = 1; i < tempScores.size(); i++) {
			int temp = score[i];
			int counter = i-1;
			while (counter >= 0) {
				if (i == 10) {
					System.out.println(temp + "," + score[counter]);
				}
				if (temp > score[counter]) {
					int temps = score[counter];
					score[counter] = temp;
					score[counter+1] = temps;
					String tempss = tempNames.get(counter);
					tempNames.set(counter, tempNames.get(counter+1));
					tempNames.set(counter+1, tempss);
				}
				else if (temp <= score[counter]) {
					place = counter+1;
					break;
				}
				counter--;
			}
		}
		
		tempScores.clear();
		for (int num : score) {
			tempScores.add(Integer.toString(num));
		}
		
		ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();
		temp.add(tempNames);
		temp.add(tempScores);
		return temp;
	}
}
