import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Login extends JPanel{


    private JLabel intro = new JLabel("ClickerApp"),
        user = new JLabel("Username"),
        pass = new JLabel("Password");
    private JTextField userField = new JTextField ();
    private JTextField passField = new JTextField ();
    public JButton login = new JButton ("Login"),
            createAcc = new JButton ("Create Account");

    private JPanel userPassPanel = new JPanel(),
        loginCreate = new JPanel();
    public Login(){
        setLayout(new GridLayout(3,1));
        intro.setHorizontalAlignment(SwingConstants.CENTER);
        add (intro);

        userPassPanel.setLayout(new GridLayout(2,2));
        userPassPanel.add (user);
        userPassPanel.add (userField);
        userPassPanel.add (pass);
        userPassPanel.add (passField);
        user.setHorizontalAlignment(SwingConstants.RIGHT);
        pass.setHorizontalAlignment(SwingConstants.RIGHT);
        add(userPassPanel);

        loginCreate.setLayout (new GridLayout(1,2));
        loginCreate.add(login);
        loginCreate.add(createAcc);
        add(loginCreate);
    }

    public User checkLogin (User[] users) {
        int numUsers = users.length;
        for (int i = 0; i < numUsers; i++) {
            if (userField.getText().equals(users[i].getID()) && passField.getText().equals(users[i].getPass())) {
                return users[i];
            }
        }
        return null;
    }

    public void setActionListeners(ActionListener main){
        login.addActionListener(main);
        createAcc.addActionListener(main);
    }

}
