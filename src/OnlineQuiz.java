import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
import java.util.Scanner;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OnlineQuiz extends JFrame {
	
	static int points = 0;
	static int i =0;
	static int size ;
	static String selectedOption;
	static LinkedList<MultipleChoice> questionList = new LinkedList<MultipleChoice>();
	//removes the added characters from the strings
	public static String replaceChars(String string) {
		string = string.replaceAll("&gt;", "").replaceAll("&it;", "").replaceAll("&rquot;", "").replaceAll("&quot;", "").replaceAll("&oacute;", "").replaceAll("&#039;", "").replaceAll("&uuml;", "").replaceAll("&amp;", "").replaceAll("&;", "").replaceAll("&aacute;", "").replaceAll("&eacute;", "").replaceAll("&iacute;", "").replaceAll("&ndash;", ""); 
			
		return string;
	}
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void displayQuestion(JRadioButton op1Label,JRadioButton op2Label,JRadioButton op3Label,JRadioButton op4Label,JLabel questionLabel) {
		MultipleChoice q = questionList.get(i);
		questionLabel.setText(q.getQuestion());
		op1Label.setText(q.getOptions()[0]);
		op2Label.setText(q.getOptions()[1]);
		op3Label.setText(q.getOptions()[2]);
		op4Label.setText(q.getOptions()[3]);
		
	}
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OnlineQuiz frame = new OnlineQuiz();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OnlineQuiz() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JButton save = new JButton("Save");
		save.setBackground(Color.LIGHT_GRAY);
		save.setBounds(589, 11, 76, 23);
		contentPane.add(save);

		JLabel savedLabel = new JLabel("");
		savedLabel.setForeground(Color.GREEN);
		savedLabel.setBounds(535, 15, 46, 14);
		contentPane.add(savedLabel);
		
		JLabel pageLabel = new JLabel("");
		pageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pageLabel.setBounds(321, 337, 46, 14);
		contentPane.add(pageLabel);
		
		JLabel scoreLabel = new JLabel("");
		scoreLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scoreLabel.setBounds(219, 81, 240, 94);
		contentPane.add(scoreLabel);
		
		JLabel questionLabel = new JLabel("");
		questionLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		questionLabel.setBounds(0, 23, 684, 63);
		contentPane.add(questionLabel);
		
		JRadioButton op1Label = new JRadioButton("");
		op1Label.setBackground(new Color(255, 255, 255));
		op1Label.setBounds(135, 93, 434, 23);
		contentPane.add(op1Label);
		
		JRadioButton op2Label = new JRadioButton("");
		op2Label.setBackground(new Color(255, 255, 255));
		op2Label.setBounds(135, 152, 434, 23);
		contentPane.add(op2Label);
		
		JRadioButton op3Label = new JRadioButton("");
		op3Label.setBackground(new Color(255, 255, 255));
		op3Label.setBounds(135, 208, 434, 23);
		contentPane.add(op3Label);
		
		
		JRadioButton op4Label = new JRadioButton("");
		op4Label.setBackground(new Color(255, 255, 255));
		op4Label.setBounds(135, 266, 434, 23);
		contentPane.add(op4Label);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(op1Label);
		bg.add(op2Label);
		bg.add(op3Label);
		bg.add(op4Label);
		MultipleChoice q;
		URL url;
		try {
			url = new URL("https://opentdb.com/api.php?amount=20&category=18&type=multiple");
			
			HttpURLConnection con;
			
			con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			
			int response = con.getResponseCode();
			String inline = "";
			if(response != 200)
				throw new RuntimeException("HttpResponseCode: " +response);
			else{
				
				Scanner sc = new Scanner(url.openStream());
				while(sc.hasNext())
				{
					
				inline+=sc.nextLine();
				}
		
				JSONParser parse = new JSONParser();
				JSONObject object = (JSONObject) parse.parse(inline);
				JSONArray resultsArray = (JSONArray) object.get("results");
				size = resultsArray.size();
				
				JSONObject wholeQuestionElement = new JSONObject();
				//get the answers into the array "options"
				
				for(int k=0; k<resultsArray.size();k++) {
					
					int randomIndex = new Random().nextInt(3);
					String[] allAnswersArray = new String[4];
					wholeQuestionElement= (JSONObject) resultsArray.get(k);
					
					String questionStatement = (String)wholeQuestionElement.get("question");
					questionStatement = replaceChars(questionStatement);
					
					String correctAnswer = (String) wholeQuestionElement.get("correct_answer");
					correctAnswer = replaceChars(correctAnswer);
					
					JSONArray wrongAnswersArray = (JSONArray) wholeQuestionElement.get("incorrect_answers");
					
					for(int j=0;j<wrongAnswersArray.size();j++) {
						allAnswersArray[j] = replaceChars((String) wrongAnswersArray.get(j));
					}

					allAnswersArray[3] =allAnswersArray[randomIndex]; 
					allAnswersArray[randomIndex] =(String)correctAnswer;
					
					q = new MultipleChoice();
					q.setOptions(allAnswersArray);
					q.setPoints(1);
					q.setRightAnswer(correctAnswer);
					q.setQuestion(questionStatement);
					questionList.add(q);
					
				}
				
				displayQuestion(op1Label,op2Label,op3Label,op4Label,questionLabel);
				pageLabel.setText("1/"+size);
				sc.close();
				
			}
		} catch (ProtocolException e) {
			e.printStackTrace();
		}catch (MalformedURLException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (EmptyFieldException e) {
			e.printStackTrace();
		}
		
		JButton next = new JButton(">");
		next.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				if(i<size-1) {
					savedLabel.setText("");
					i++;
					displayQuestion(op1Label,op2Label,op3Label,op4Label,questionLabel);
					pageLabel.setText((i+1)+"/"+size);
					bg.clearSelection();
				}
			}
		});
		next.setBackground(Color.LIGHT_GRAY);
		next.setBounds(625, 181, 49, 23);
		contentPane.add(next);
		
		JButton previous = new JButton("<");
		previous.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(i>0) {
					savedLabel.setText("");
					i--;
					displayQuestion(op1Label,op2Label,op3Label,op4Label,questionLabel);
					pageLabel.setText((i+1)+"/"+size);
					bg.clearSelection();
				}
			}
		});
		previous.setBackground(Color.LIGHT_GRAY);
		previous.setBounds(10, 181, 49, 23);
		contentPane.add(previous);
		
		JButton back = new JButton("<<");
		back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Menu main = new Menu();
				main.setVisible(true);
				setVisible(false);
			}
		});
		back.setBackground(Color.LIGHT_GRAY);
		back.setFont(new Font("Tahoma", Font.PLAIN, 7));
		back.setBounds(0, 0, 48, 20);
		contentPane.add(back);
		JButton again = new JButton("Play Again");
		again.setBackground(Color.LIGHT_GRAY);
		again.setBounds(281, 238, 116, 41);
		contentPane.add(again);
		again.setVisible(false);
		
		JButton submit = new JButton("Submit");
		submit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int score = 0;
				savedLabel.setText("");
				for(int i=0;i<questionList.size();i++) {
					if(questionList.get(i).isCorrect())
						score++;
				}
				save.setVisible(false);
				scoreLabel.setText("Your Score: "+score+"/10");
				i=0;
				
				questionLabel.setVisible(false);
				op1Label.setVisible(false);
				op2Label.setVisible(false);
				op3Label.setVisible(false);
				op4Label.setVisible(false);
				previous.setVisible(false);
				next.setVisible(false);
				submit.setVisible(false);
				again.setVisible(true);
				
				
			}
		});
		submit.setBackground(Color.GREEN);
		submit.setBounds(589, 319, 84, 32);
		contentPane.add(submit);
		

		
		save.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(op1Label.isSelected()) {
					selectedOption = op1Label.getText();
				}else if(op2Label.isSelected()) {
					selectedOption = op2Label.getText();
				}else if(op3Label.isSelected()) {
					selectedOption = op3Label.getText();
				}else if(op4Label.isSelected()) {
					selectedOption = op4Label.getText();
				}
				if(questionList.get(i).getRightAnswer().equalsIgnoreCase(selectedOption)) {
					questionList.get(i).setCorrect(true);
				}else {
					questionList.get(i).setCorrect(false);
				}
				
				savedLabel.setText("saved!");
			}
		});
		
		again.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				for(int i=0;i<questionList.size();i++) {
					questionList.get(i).setCorrect(false);;
						
				}
				save.setVisible(true);
				scoreLabel.setText("");
				questionLabel.setVisible(true);
				op1Label.setVisible(true);
				op2Label.setVisible(true);
				op3Label.setVisible(true);
				op4Label.setVisible(true);
				previous.setVisible(true);
				next.setVisible(true);
				submit.setVisible(true);
				again.setVisible(false);
				save.setVisible(true);
				i=0;
				OnlineQuiz oq = new OnlineQuiz();
				oq.setVisible(true);
				setVisible(false);
				
			}
		});
		
	}
}
