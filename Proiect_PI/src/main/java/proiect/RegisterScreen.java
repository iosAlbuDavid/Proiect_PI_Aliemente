package proiect;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * Clasa RegisterScreen se ocupa de inregistrarea utilizatorilor.
 */
public class RegisterScreen implements ActionListener {
	IDandPasswords idandPasswords = new IDandPasswords(); 
	
    JFrame frmRegister = new JFrame();
    JButton registerButton = new JButton("Register");
    JButton exitButton = new JButton("Exit");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JPasswordField confirmPasswordField = new JPasswordField();
    JLabel userIDLable = new JLabel("userID:");
    JLabel userPasswordLable = new JLabel("password:");
    JLabel confirmPasswordLable = new JLabel("confirm password:");
    JLabel messageLable = new JLabel();
    JLabel TitleText = new JLabel("Register");
    
    List<List<String>> loginInfoOriginal;

    /**
     * Se ocupa cu inregistrarea utilizatorilor
     * @param logininfo
     */
    public RegisterScreen(List<List<String>> logininfo) {
    	loginInfoOriginal =logininfo;
    	
        userIDLable.setBounds(50, 100, 100, 25);
        userPasswordLable.setBounds(50, 150, 100, 25);
        confirmPasswordLable.setBounds(50, 200, 150, 25);

        messageLable.setForeground(UIManager.getColor("Button.foreground"));
        messageLable.setBounds(100, 300, 300, 35);
        messageLable.setFont(new Font(null, Font.ITALIC, 25));

        userIDField.setBounds(200, 100, 200, 25);
        userPasswordField.setBounds(200, 150, 200, 25);
        confirmPasswordField.setBounds(200, 200, 200, 25);

        registerButton.setBounds(50, 250, 100, 25);
        registerButton.setFocusable(false);
        registerButton.addActionListener(this);

        exitButton.setBounds(300, 250, 100, 25);
        exitButton.setFocusable(false);
        exitButton.addActionListener(this);

        frmRegister.getContentPane().add(userIDLable);
        frmRegister.getContentPane().add(userPasswordLable);
        frmRegister.getContentPane().add(confirmPasswordLable);
        frmRegister.getContentPane().add(messageLable);
        frmRegister.getContentPane().add(userIDField);
        frmRegister.getContentPane().add(userPasswordField);
        frmRegister.getContentPane().add(confirmPasswordField);
        frmRegister.getContentPane().add(registerButton);
        frmRegister.getContentPane().add(exitButton);
        frmRegister.setTitle("Register");

        frmRegister.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmRegister.setSize(500, 400);
        frmRegister.getContentPane().setLayout(null);

        TitleText.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
        TitleText.setBounds(200, 40, 100, 36);
        frmRegister.getContentPane().add(TitleText);

        frmRegister.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exitButton) {
            frmRegister.dispose();
            try {
				new LoginScreen(idandPasswords.getLoginInfo());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
        }
        if (e.getSource() == registerButton) {
            String userID = userIDField.getText();
            String password = String.valueOf(userPasswordField.getPassword());
            String confirmPassword = String.valueOf(confirmPasswordField.getPassword());

            if (!password.equals(confirmPassword)) {
                messageLable.setForeground(Color.red);
                messageLable.setText("Passwords do not match");
            } else if (userID.isEmpty() || password.isEmpty()) {
                messageLable.setForeground(Color.red);
                messageLable.setText("Fields cannot be empty");
            } else {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/useriSiParole.txt", true))) {
                    writer.write(userID + " " + password);
                    writer.newLine();
                    messageLable.setForeground(Color.green);
                    messageLable.setText("Registration Successful");
                } catch (IOException ex) {
                    messageLable.setForeground(Color.red);
                    messageLable.setText("Error saving user");
                    ex.printStackTrace();
                }
            }
        }
    }
}