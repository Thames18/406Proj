import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Frame extends JFrame implements ActionListener {

    private final int ACC_TYPE = 0, ID_NUM = 1, PASSWORD = 2, FULL_NAME = 3;
    private User[] users;
    private int numUsers;
    private User currentUser;

    Login login = new Login();
    LoggedIn loggedIn = new LoggedIn();
    CreateAccount createAcc = new CreateAccount();
    QuizGUI quiz = new QuizGUI();
    // MAKE YOUR GUI A SEPARATE CLASS THAT EXTENDS JPANEL
    // MAKE SURE BUTTONS AND THINGS LIKE THAT ARE PUBLIC IF YOU NEED TO ACCESS THEM IN HERE.

    public Frame() {
        setTitle("CLEEKER APP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation (300,300);
        setResizable(false);
        setVisible(true);

        switchGUITo(login);

        setActionListeners();

        try {
            getUsers();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        quiz.readQuiz("questinos.txt");
    }

    @Override
    // PUT YOUR BUTTONS IN HERE, ACTIONS WITH THEM.
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();


        if (src == login.login){
            currentUser = login.checkLogin(users);
            if (currentUser == null){
                JOptionPane.showMessageDialog(null, "Could not find user in database.");
            } else{
                switchGUITo(quiz);
            }
        }



        else if (src == login.createAcc){
            switchGUITo(createAcc);
        }



        else if (src == createAcc.student)
            createAcc.instructor.setSelected(false);


        else if (src == createAcc.instructor)
            createAcc.student.setSelected(false);



        else if (src == createAcc.cancel){
            switchGUITo(login);
        }


        else if (src == createAcc.create){
            if (createAcc.notEmpty()){
                createAcc.createUser(users);
                try {
                    getUsers();
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                switchGUITo(login);
            } else{
                JOptionPane.showMessageDialog(null, "A required field is empty.");
            }
        }


        else if (src == quiz.next){
            quiz.nextQuestion();
        }
        else if (src == quiz.previous){
            quiz.prevQuestion();
        }
        else if (src == quiz.submitAll){
            quiz.submitAll(currentUser.getID());
            switchGUITo(login);
        }
    }

    public void setActionListeners(){
        login.setActionListeners(this);
        createAcc.setActionListeners(this);
        loggedIn.setActionListeners(this);
        quiz.setActionListeners(this);
        // PUT YOUR GUI SCREEN ACTION LISTENERS HERE
    }

    public void switchGUITo(JPanel pane){
        setContentPane(pane);
        pack();
        if (pane instanceof QuizGUI)
            setSize(500,500);
        else
            setSize(300,300);
    }

    public void getUsers() throws FileNotFoundException{

        Scanner in = new Scanner(new File("accounts.txt"));
        numUsers = Integer.parseInt(in.nextLine());
        users = new User[numUsers];
        String[] args = new String[4];
        for (int i = 0; i < numUsers; i++){
            args[ACC_TYPE] = in.nextLine();
            args[ID_NUM] = in.nextLine();
            args[PASSWORD] = in.nextLine();
            args[FULL_NAME] = in.nextLine();
            if (args[ACC_TYPE].equals("S")){
                users[i] = new Student (args[FULL_NAME], args[ID_NUM], args[PASSWORD]);
            }
            users[i] = new Instructor(args[FULL_NAME], args[ID_NUM], args[PASSWORD]);
        }
    }
}
