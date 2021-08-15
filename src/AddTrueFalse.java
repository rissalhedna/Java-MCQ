import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class AddTrueFalse extends JFrame {

	private JPanel contentPane;
	private JTextField question;
	private JTextField points;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddTrueFalse frame = new AddTrueFalse();
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
	public AddTrueFalse() {
		Question questions = new Question();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600,500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLabel questionLabel = new JLabel("Question:");
		questionLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		questionLabel.setBounds(49, 156, 93, 39);
		contentPane.add(questionLabel);
		
		JLabel questionError = new JLabel("");
		questionError.setFont(new Font("Tahoma", Font.PLAIN, 9));
		questionError.setForeground(new Color(255, 0, 0));
		questionError.setBounds(152, 192, 314, 23);
		contentPane.add(questionError);
		
		JLabel pointsError = new JLabel("");
		pointsError.setFont(new Font("Tahoma", Font.PLAIN, 9));
		pointsError.setForeground(Color.RED);
		pointsError.setBounds(484, 86, 93, 14);
		contentPane.add(pointsError);
		
		question = new JTextField();
		question.setColumns(10);
		question.setBounds(152, 161, 314, 32);
		contentPane.add(question);
		
		JRadioButton btnTrue = new JRadioButton("True");
		btnTrue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTrue.setBounds(228, 258, 109, 23);
		contentPane.add(btnTrue);
		
		JRadioButton btnFalse = new JRadioButton("False");
		btnFalse.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFalse.setBounds(228, 293, 109, 23);
		contentPane.add(btnFalse);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(btnTrue);
		bg.add(btnFalse);
		
		JLabel lblCorrectAnswer = new JLabel("Correct Answer:");
		lblCorrectAnswer.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCorrectAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		lblCorrectAnswer.setBounds(49, 253, 136, 32);
		contentPane.add(lblCorrectAnswer);
		
		JButton add = new JButton("Add");
		add.setFont(new Font("Tahoma", Font.BOLD, 11));
		add.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				questions.loadQuestions();	
				
				TrueFalse q = new TrueFalse();
				
				String rightAnswer = "True";
				int pts = 1;
				String questionStatement = question.getText();
				
				if(!points.getText().equals("")) {
					try {
						pts = Integer.parseInt(points.getText());
						pointsError.setText("");
					}catch(NumberFormatException e1) {
						pointsError.setText("Incorrect input");
						
					}
				}				
				if(btnTrue.isSelected()) {
					rightAnswer = "True";
				}
				if(btnFalse.isSelected()) {
					rightAnswer = "False";
				}
				
				try {
					
					q.setQuestion(questionStatement);
					questionError.setText("");
					q.setRightAnswer(rightAnswer);
					q.setPoints(pts);
					pointsError.setText("");
					questions.addQuestion(q);
					questions.saveQuestions();
					question.setText("");
					points.setText("");
					
				}catch (EmptyFieldException e1) {
					questionError.setText("Field cannot be empty");
				}
				
				
			}
		});
		add.setBackground(new Color(0, 255, 0));
		add.setBounds(504, 405, 73, 39);
		contentPane.add(add);
		
		JLabel lblPoints = new JLabel("Points:");
		lblPoints.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPoints.setBounds(482, 58, 55, 17);
		contentPane.add(lblPoints);
		
		points = new JTextField();
		points.setColumns(10);
		points.setBounds(528, 56, 25, 23);
		contentPane.add(points);
		
		JButton back = new JButton("<<");
		back.setBackground(Color.LIGHT_GRAY);
		back.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				QuestionType type = new QuestionType();
				type.setVisible(true);
				setVisible(false);
			}
		});
		back.setFont(new Font("Tahoma", Font.PLAIN, 7));
		back.setBounds(0, 0, 48, 20);
		contentPane.add(back);
		
		
	}
}
