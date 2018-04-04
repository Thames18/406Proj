import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;


public class QuizGUI extends JPanel {
    private LinkedList<quizClass> Qs = new LinkedList<>();
    ListIterator<quizClass> iter;
    private int qCount = 0, currentQ = 0;
    private int[] answers;
    public JButton next, previous, submitAll;
    private JLabel question;
    private JPanel navigationPanel, usersPanel, multipleChoicePanel;
    private JRadioButton answer1button = new JRadioButton("Answer1"),
            answer2button = new JRadioButton("Answer2"),
            answer3button = new JRadioButton("Answer3"),
            answer4button = new JRadioButton("Answer4");
    private char prev = 'p';

    private Scanner in = null;

    public QuizGUI() {
        setLayout(new GridLayout(2, 1));

        question = new JLabel("What is the correct answer to this question?");
        question.setHorizontalAlignment(JLabel.CENTER);
        question.setVerticalAlignment(JLabel.CENTER);

        next = new JButton("Next");
        submitAll = new JButton("Submit all responses");
        previous = new JButton("Previous");

        createNavigationPanel();
        createMultipleChoicePanel();

        add(question);
        add(createUserPanel());
    }

    public int getSizeX() {
        return 500;
    }

    public int getSizeY() {
        return 500;
    }

    public JPanel createNavigationPanel() {
        navigationPanel = new JPanel(new GridLayout(3, 1));
        navigationPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        navigationPanel.add(next);
        navigationPanel.add(previous);
        navigationPanel.add(submitAll);

        return navigationPanel;
    }

    public JPanel createMultipleChoicePanel() {

        multipleChoicePanel = new JPanel(new GridLayout(4, 1));
        multipleChoicePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

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

    public void readQuiz(String fileName) {

        in = readFile(fileName);
        while (in.hasNextLine()) {
            String question = getQuestion();
            String[] ans = getAnswers();
            Qs.add(new quizClass(question, ans));
            qCount++;
        }
        answers = new int[qCount];
        iter  = Qs.listIterator();
        setQuestion(Qs.getFirst());
    }

    public void setQuestion(quizClass q) {
        String[] ans = q.getAnswers();
        question.setText(q.getQuestion());

        answer1button.setText(ans[0]);
        answer2button.setText(ans[1]);
        answer3button.setText(ans[2]);
        answer4button.setText(ans[3]);
    }

    public Scanner readFile(String fileName) {
        try {
            return new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getQuestion() {
        return in.nextLine();
    }

    public String[] getAnswers() {
        String[] ans = new String[4];
        ans[0] = in.nextLine();
        ans[1] = in.nextLine();
        ans[2] = in.nextLine();
        ans[3] = in.nextLine();
        return ans;
    }

    public void setActionListeners(ActionListener main) {
        submitAll.addActionListener(main);
        next.addActionListener(main);
        previous.addActionListener(main);
    }

    public void nextQuestion() {
        if (iter.hasNext()) {
            if (prev == 'p')
                iter.next();
            setQuestion(iter.next());
            prev = 'n';
            collectAnswer();
            currentQ++;
            resetAnswer();
        }
    }
    public void prevQuestion() {
        if (iter.hasPrevious()) {
            if (prev == 'n')
                iter.previous();
            setQuestion(iter.previous());

            prev = 'p';
            collectAnswer();
            currentQ--;
            resetAnswer();
        }
    }
    public void collectAnswer(){
        if (answer1button.isSelected()){
            answers[currentQ] = 0;
        }
        if (answer2button.isSelected()){
            answers[currentQ] = 1;
        }
        if (answer3button.isSelected()){
            answers[currentQ] = 2;
        }
        if (answer4button.isSelected()){
            answers[currentQ] = 3;
        }
    }
    public void resetAnswer(){
        if (answers[currentQ] == 0){
            answer1button.setSelected(true);
        }
        if (answers[currentQ] == 1){
            answer2button.setSelected(true);
        }
        if (answers[currentQ] == 2){
            answer3button.setSelected(true);
        }
        if (answers[currentQ] == 3){
            answer4button.setSelected(true);
        }
    }
    public void submitAll(String ID){
        collectAnswer();
        PrintWriter out = null;
        boolean found = false;
        int answerNum = 0;
        File f = new File ("answers"+answerNum+".txt");
        while (f.exists()){
            answerNum++;
            f = new File("answers"+answerNum+".txt");
        }

        try {
            out = new PrintWriter("answers"+answerNum+".txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < qCount; i++){
            out.println(answers[i]);
        }
        out.close();
    }
}