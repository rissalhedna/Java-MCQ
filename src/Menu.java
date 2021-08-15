import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Menu extends JFrame {
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		
		Question question = new Question();
		question.loadQuestions();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);

		setResizable(false);
		
		
		JButton addDirect = new JButton("Add Question");
		addDirect.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				QuestionType type = new QuestionType();
				type.setVisible(true);
				setVisible(false);
			}
		});
		
		JButton offlineDirect = new JButton("Offline Quiz");
		offlineDirect.setFont(new Font("Century Gothic", Font.BOLD, 14));
		offlineDirect.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(!question.questions.isEmpty()) {
					
					OfflineQuiz lq = new OfflineQuiz();
					lq.setVisible(true);
					setVisible(false);
					
				}else {
					JOptionPane.showMessageDialog(null, "Quiz List is empty");
				}
				
			}
		});
		
		JButton onlineDirect = new JButton("Online Quiz");
		onlineDirect.setFont(new Font("Century Gothic", Font.BOLD, 14));
		onlineDirect.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				OnlineQuiz oq = new OnlineQuiz();
				oq.setVisible(true);
				setVisible(false);
			}
		});
		
		
		
		
		
		
		
	
		
		
		
		
		
		
		
		
		
		onlineDirect.setBackground(new Color(248, 248, 255));
		JLabel lblNewLabel = new JLabel("Welcome To Quiz App");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Century Gothic", Font.BOLD, 24));
		offlineDirect.setBackground(new Color(248, 248, 255));
		
		addDirect.setForeground(Color.BLACK);
		addDirect.setBackground(new Color(248, 248, 255));
		addDirect.setFont(new Font("Century Gothic", Font.BOLD, 14));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(addDirect, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(offlineDirect, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(onlineDirect, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE))
					.addContainerGap(32, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(139)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
					.addGap(126))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(77)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addComponent(onlineDirect, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(offlineDirect, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(addDirect, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(71, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
