import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class OfflineQuiz extends JFrame {
	static int i =0;
	private JPanel contentPane;
	
	static int size = 0;
	static String selectedOption = "";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OfflineQuiz frame = new OfflineQuiz();
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
	public static void displayQuestion(JRadioButton op1Label,JRadioButton op2Label,JRadioButton op3Label,JRadioButton op4Label,JLabel questionLabel, Question questions) {
			Question q = questions.questions.get(i);
			size = questions.questions.size();
			if(q instanceof MultipleChoice) {
				
				questionLabel.setText(q.getQuestion());
				op1Label.setText(((MultipleChoice)q).getOptions()[0]);
				op2Label.setText(((MultipleChoice)q).getOptions()[1]);
				op3Label.setText(((MultipleChoice)q).getOptions()[2]);
				op4Label.setText(((MultipleChoice)q).getOptions()[3]);
				
				op1Label.setVisible(true);
				op2Label.setVisible(true);
				op3Label.setVisible(true);
				op4Label.setVisible(true);
				
		}else {
			
			questionLabel.setText(q.getQuestion());
			op1Label.setVisible(false);
			op2Label.setText("True");
			op3Label.setText("False");
			
			questionLabel.setVisible(true);
			op2Label.setVisible(true);
			op3Label.setVisible(true);
			op4Label.setVisible(false);
			
			op1Label.setVisible(false);
			op4Label.setVisible(false);
		}

	}
	public OfflineQuiz() {
		Question questions = new Question();
		questions.loadQuestions();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);

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
		questionLabel.setForeground(Color.BLACK);
		questionLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		questionLabel.setBounds(0, 23, 684, 63);
		contentPane.add(questionLabel);
		
		JRadioButton op1Label = new JRadioButton("");
		op1Label.setBackground(new Color(255, 255, 255));
		op1Label.setBounds(129, 93, 434, 23);
		contentPane.add(op1Label);
		
		JRadioButton op2Label = new JRadioButton("");
		op2Label.setForeground(new Color(0, 0, 0));
		op2Label.setBackground(new Color(255, 255, 255));
		op2Label.setBounds(129, 152, 434, 23);
		contentPane.add(op2Label);
		
		JRadioButton op3Label = new JRadioButton("");
		op3Label.setBackground(new Color(255, 255, 255));
		op3Label.setForeground(new Color(0, 0, 0));
		op3Label.setBounds(129, 208, 434, 23);
		contentPane.add(op3Label);
		
		JRadioButton op4Label = new JRadioButton("");
		op4Label.setBackground(new Color(255, 255, 255));
		op4Label.setForeground(new Color(0, 0, 0));
		op4Label.setBounds(129, 261, 434, 23);
		contentPane.add(op4Label);
		
		JButton save = new JButton("Save");
		save.setBackground(Color.LIGHT_GRAY);
		save.setBounds(598, 11, 76, 23);
		contentPane.add(save);
		
		
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(op1Label);
		bg.add(op2Label);
		bg.add(op3Label);
		bg.add(op4Label);
		
		JLabel savedLabel = new JLabel("");
		savedLabel.setForeground(Color.GREEN);
		savedLabel.setBounds(542, 15, 46, 14);
		
		displayQuestion(op1Label,op2Label,op3Label,op4Label,questionLabel,questions);
		pageLabel.setText((i+1)+"/"+questions.questions.size());
		JButton next = new JButton(">");
		next.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(i<size-1) {
					savedLabel.setText("");
					i++;
					pageLabel.setText((i+1)+"/"+questions.questions.size());
					displayQuestion(op1Label,op2Label,op3Label,op4Label,questionLabel,questions);
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
					pageLabel.setText((i+1)+"/"+questions.questions.size());
					displayQuestion(op1Label,op2Label,op3Label,op4Label,questionLabel,questions);
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
				for(int i =0;i<size;i++) {
					if(questions.questions.get(i).isCorrect())
						score += questions.questions.get(i).getPoints();
				}
				
				scoreLabel.setText("Your Score: "+score+"/"+questions.getTotalPoints());
				save.setVisible(false);
				questionLabel.setVisible(false);
				op1Label.setVisible(false);
				op2Label.setVisible(false);
				op3Label.setVisible(false);
				op4Label.setVisible(false);
				previous.setVisible(false);
				savedLabel.setText("");
				next.setVisible(false);
				submit.setVisible(false);
				again.setVisible(true);
			}
		});
		submit.setBackground(Color.GREEN);
		submit.setBounds(589, 328, 84, 23);
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
				
				if(questions.getQuestions().get(i).getRightAnswer().equalsIgnoreCase(selectedOption)) {
					questions.getQuestions().get(i).setCorrect(true);
				}else {
					questions.getQuestions().get(i).setCorrect(false);
				}
				
				savedLabel.setText("saved!");
			}
		});
		
		
		
		contentPane.add(savedLabel);
		
		JLabel savedLabel_1 = new JLabel("");
		savedLabel_1.setForeground(Color.GREEN);
		savedLabel_1.setBounds(321, 337, 46, 14);
		contentPane.add(savedLabel_1);
		again.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				for(int i =0;i<size;i++) {
					questions.questions.get(i).setCorrect(false);
				}
				i =0;
				savedLabel.setText("");
				scoreLabel.setText("");
				displayQuestion(op1Label,op2Label,op3Label,op4Label,questionLabel,questions);
				previous.setVisible(true);
				next.setVisible(true);
				submit.setVisible(true);
				again.setVisible(false);
				save.setVisible(true);
				bg.clearSelection();
			}
		});
		
	}
}
