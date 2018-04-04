import javax.swing.*;
import java.awt.*;


public class QuizGUI {

	private JFrame frame;
	private JButton next, previous, submitAll;
	private JLabel question;
	private JPanel navigationPanel, usersPanel, multipleChoicePanel;

	QuizGUI() {
		frame = new JFrame();
		frame.setLayout(new GridLayout(2, 1));
	
		question = new JLabel("What is the correct answer to this question?");
		question.setHorizontalAlignment(JLabel.CENTER);
		question.setVerticalAlignment(JLabel.CENTER);
		
		next = new JButton("Next");
		submitAll = new JButton("Submit all responses");
		previous = new JButton("Previous");

		createNavigationPanel();
		createMultipleChoicePanel();
		
		frame.add(question);
		frame.add(createUserPanel());
		
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	public JPanel createNavigationPanel() {
		navigationPanel = new JPanel(new GridLayout(3,1));
		navigationPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		navigationPanel.add(next);
		navigationPanel.add(previous);
		navigationPanel.add(submitAll);
		
		return navigationPanel;
	}
	
	public JPanel createMultipleChoicePanel() {
	
		multipleChoicePanel = new JPanel(new GridLayout(4,1));
		multipleChoicePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		
		JRadioButton answer1button = new JRadioButton("Answer1");
		JRadioButton answer2button = new JRadioButton("Answer2");
		JRadioButton answer3button = new JRadioButton("Answer3");
		JRadioButton answer4button = new JRadioButton("Answer4");
	
		ButtonGroup answers = new ButtonGroup();
		answers.add(answer1button);
		answers.add(answer2button);
		answers.add(answer3button);
		answers.add(answer4button);
		
		multipleChoicePanel.add(answer1button);
		multipleChoicePanel.add(answer2button);
		multipleChoicePanel.add(answer3button);
		multipleChoicePanel.add(answer4button);
		
		return multipleChoicePanel;
	}
		
	
	public JPanel createUserPanel() {
	
		usersPanel = new JPanel(new BorderLayout());
		
		usersPanel.add(createMultipleChoicePanel(), BorderLayout.CENTER);
		usersPanel.add(createNavigationPanel(), BorderLayout.EAST);
		
		return usersPanel;
	}
		
	
	public static void main(String[] args) {	
		QuizGUI quiz = new QuizGUI();		
	}

}