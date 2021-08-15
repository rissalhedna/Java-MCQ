import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddMultipleChoice extends JFrame {

	private JPanel contentPane;
	private JTextField op1;
	private JTextField op2;
	private JTextField op3;
	private JTextField op4;
	private JTextField correct;
	private JTextField question;
	private JTextField points;
	private JButton add;
	private JButton back;
	private JLabel pointsError;
	private JLabel fieldsError;
	private JLabel answerError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMultipleChoice frame = new AddMultipleChoice();
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
	public AddMultipleChoice() {
		Question questions = new Question();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600,500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane.setLayout(null);
		
		fieldsError = new JLabel("");
		fieldsError.setBounds(151, 338, 302, 20);
		fieldsError.setForeground(Color.RED);
		fieldsError.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contentPane.add(fieldsError);
		
		pointsError = new JLabel("");
		pointsError.setBounds(473, 52, 89, 14);
		pointsError.setFont(new Font("Tahoma", Font.PLAIN, 9));
		pointsError.setForeground(Color.RED);
		contentPane.add(pointsError);
		
		op1 = new JTextField();
		op1.setBounds(151, 156, 302, 36);
		contentPane.add(op1);
		op1.setColumns(10);
		
		op2 = new JTextField();
		op2.setBounds(151, 203, 302, 36);
		op2.setColumns(10);
		contentPane.add(op2);
		
		op3 = new JTextField();
		op3.setBounds(151, 250, 302, 36);
		op3.setColumns(10);
		contentPane.add(op3);
		
		op4 = new JTextField();
		op4.setBounds(151, 297, 302, 39);
		op4.setColumns(10);
		contentPane.add(op4);
		
		correct = new JTextField();
		correct.setBounds(151, 391, 302, 36);
		correct.setColumns(10);
		contentPane.add(correct);
		
		question = new JTextField();
		question.setBounds(151, 76, 302, 36);
		question.setColumns(10);
		contentPane.add(question);
		
		JLabel questionLabel = new JLabel("Question:");
		questionLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		questionLabel.setBounds(60, 78, 89, 33);
		questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(questionLabel);
		
		JLabel op1Label = new JLabel("A)");
		op1Label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		op1Label.setBounds(29, 156, 89, 36);
		op1Label.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(op1Label);
		
		JLabel op2Label = new JLabel("B)");
		op2Label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		op2Label.setBounds(29, 204, 89, 35);
		op2Label.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(op2Label);
		
		JLabel op3Label = new JLabel("C)");
		op3Label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		op3Label.setBounds(29, 250, 89, 36);
		op3Label.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(op3Label);
		
		JLabel op4Label = new JLabel("D)");
		op4Label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		op4Label.setBounds(29, 297, 89, 39);
		op4Label.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(op4Label);
		
		JLabel lblCorrectAnswer = new JLabel("Correct Answer:");
		lblCorrectAnswer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCorrectAnswer.setBounds(29, 391, 112, 36);
		lblCorrectAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblCorrectAnswer);
		
		JLabel lblPoints = new JLabel("Points:");
		lblPoints.setHorizontalAlignment(SwingConstants.CENTER);
		lblPoints.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPoints.setBounds(448, 30, 89, 20);
		contentPane.add(lblPoints);
		
		points = new JTextField();
		points.setBounds(531, 30, 25, 22);
		points.setColumns(10);
		contentPane.add(points);
		
		add = new JButton("Add");
		add.setFont(new Font("Tahoma", Font.BOLD, 11));
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add.setBounds(489, 385, 73, 49);
		add.setBackground(new Color(0, 255, 0));
		add.addMouseListener(new MouseAdapter() {
			
			
			
			public void mouseClicked(MouseEvent e) {
				
				questions.loadQuestions();
				
				String questionStatement = question.getText();
				String[] options = new String[4];
				options[0] = op1.getText();
				options[1] = op2.getText();
				options[2] = op3.getText();
				options[3] = op4.getText();
				int pts = 1;
				
				if(!points.getText().equals("")) {
					try {
						pts = Integer.parseInt(points.getText());
						pointsError.setText("");
					}catch(NumberFormatException e1) {
						pointsError.setText("Incorrect input");
					}
					
				}
				int answerValid = -1;
				
				MultipleChoice q = new MultipleChoice();
				
				try {
					String rightAnswer = correct.getText();
					for(String option: options) {
						if(rightAnswer.equalsIgnoreCase(option)) {
							answerValid = 1;
						}
					}
					if(answerValid!=1) {
						throw new InvalidChoiceException();
					}
					
					try {
						q.setQuestion(questionStatement);
						q.setPoints(pts);
						fieldsError.setText("");
						answerError.setText("");
						pointsError.setText("");
						q.setRightAnswer(rightAnswer);
						q.setOptions(options);
						
						questions.addQuestion(q);
						questions.saveQuestions();
						question.setText("");
						op1.setText("");
						op2.setText("");
						op3.setText("");
						op4.setText("");
						correct.setText("");
						points.setText("");
					} catch (EmptyFieldException e1) {
						fieldsError.setText("Fields cannot be empty");
					}
					
				}catch(InvalidChoiceException e1) {
					answerError.setText("Answer does not match");
				}
				
				
				
				
				
			}
		});
		contentPane.add(add);
		
		back = new JButton("<<");
		back.setBounds(0, 0, 48, 20);
		back.setBackground(Color.LIGHT_GRAY);
		back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				QuestionType type = new QuestionType();
				type.setVisible(true);
				setVisible(false);
				
			}
		});
		back.setFont(new Font("Tahoma", Font.PLAIN, 7));
		contentPane.add(back);
		
		answerError = new JLabel("");
		answerError.setBounds(151, 422, 302, 27);
		answerError.setForeground(Color.RED);
		answerError.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contentPane.add(answerError);
		


		
		
	}
}
