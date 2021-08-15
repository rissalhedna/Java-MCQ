import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.JTextField;

public class QuestionType extends JFrame {

	private JPanel contentPane;
	private JTextField questionNumber;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuestionType frame = new QuestionType();
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
	public QuestionType() {
		
		Question questions = new Question();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setResizable(false);
		
		JLabel wrongInput = new JLabel("");
		wrongInput.setFont(new Font("Tahoma", Font.PLAIN, 12));
		wrongInput.setForeground(Color.RED);
		wrongInput.setBounds(186, 418, 243, 29);
		contentPane.add(wrongInput);
		
		JLabel lblNewLabel = new JLabel("Question Type:");
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 24));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(117, 73, 351, 34);
		contentPane.add(lblNewLabel);
		
		JButton btnMultipleChoice = new JButton("Multiple Choice");
		btnMultipleChoice.setFont(new Font("Century Gothic", Font.BOLD, 16));
		btnMultipleChoice.setBackground(new Color(248, 248, 255));
		btnMultipleChoice.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				
				AddMultipleChoice mc = new AddMultipleChoice();
				mc.setVisible(true);
				setVisible(false);
				
				
			}
		});
		btnMultipleChoice.setBounds(117, 167, 356, 59);
		contentPane.add(btnMultipleChoice);
		
		JButton btnTruefalse = new JButton("True/False");
		btnTruefalse.setFont(new Font("Century Gothic", Font.BOLD, 16));
		btnTruefalse.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
				AddTrueFalse tf = new AddTrueFalse();
				tf.setVisible(true);
				setVisible(false);
			}
		});
		btnTruefalse.setBackground(new Color(248, 248, 255));
		btnTruefalse.setBounds(116, 265, 357, 59);
		contentPane.add(btnTruefalse);
		
		JButton back = new JButton("<<");
		back.setBackground(Color.LIGHT_GRAY);
		back.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				Menu main = new Menu();
				main.setVisible(true);
				setVisible(false);
			}
		});
		back.setFont(new Font("Tahoma", Font.PLAIN, 7));
		back.setBounds(0, 0, 48, 20);
		contentPane.add(back);
		
		JButton btnClearQuestions = new JButton("Clear Questions");
		btnClearQuestions.setForeground(new Color(255, 255, 255));
		btnClearQuestions.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnClearQuestions.setBackground(new Color(255, 0, 0));
		btnClearQuestions.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				questions.clearQuestions();
				questionNumber.setText("");
				wrongInput.setText("");
				JOptionPane.showMessageDialog(null, "List Cleared!");
				
				
			}
		});
		btnClearQuestions.setBounds(429, 418, 137, 29);
		contentPane.add(btnClearQuestions);
		
		JButton btnRemoveQuestion = new JButton("Remove Question");
		btnRemoveQuestion.addMouseListener(new MouseAdapter() {
			
			
			public void mouseClicked(MouseEvent e) {
				questions.loadQuestions();
				int number = 0;
				
				if(!questionNumber.getText().strip().equals("")) {
					try {
						number = Integer.parseInt(questionNumber.getText());
						wrongInput.setText("");
						if(number>0 && number<=questions.getQuestions().size()) {
							
							questions.removeQuestion(number-1);
							wrongInput.setText("deleted");
							wrongInput.setForeground(Color.GREEN);
							
						}else {
							wrongInput.setText(questions.getQuestions().size()+" question(s) in list");
							wrongInput.setForeground(Color.RED);
						}
					}catch(NumberFormatException e1) {
						wrongInput.setText("Wrong input");
						wrongInput.setForeground(Color.RED);
					}
					
				}
				
				questionNumber.setText("");
				
				
				
			}
		});
		btnRemoveQuestion.setBackground(Color.LIGHT_GRAY);
		btnRemoveQuestion.setBounds(10, 418, 137, 29);
		contentPane.add(btnRemoveQuestion);
		
		questionNumber = new JTextField();
		questionNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		questionNumber.setBounds(148, 418, 28, 29);
		contentPane.add(questionNumber);
		questionNumber.setColumns(10);
		
	
	}
}
