package proiect;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;



/**
 * Clasa LoginScreen se ocupa de autentificarea utilizatorilor.
 */
public class LoginScreen implements ActionListener{
	JFrame frmLogin = new JFrame();
	JButton loginButton = new JButton("Login");
	JButton resetButton = new JButton("Reset");
	JTextField userIDField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	JLabel userIDLable = new JLabel("userID:");
	JLabel userPasswordLable = new JLabel("password:");
	JLabel messageLable = new JLabel();
	JLabel TitleText = new JLabel("Login");
	JButton registerButton = new JButton("Register");
	List<List<String>> logininfo;
	
	/**
	 * Constructor implicit
	 */
	public LoginScreen(List<List<String>> loginInfoOriginal) {
		logininfo = loginInfoOriginal;
		
		userIDLable.setBounds(50,100,75,25);
		userPasswordLable.setBounds(50,150,75,25);
		
		messageLable.setForeground(UIManager.getColor("Button.foreground"));
		messageLable.setBounds(101,270,324,35);
		messageLable.setFont(new Font(null,Font.ITALIC,25));
		
		userIDField.setBounds(164,100,200,25);
		userPasswordField.setBounds(164,150,200,25);
		
		loginButton.setBounds(50,220,100,25);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);
		
		resetButton.setBounds(197,220,100,25);
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);


		frmLogin.getContentPane().add(userIDLable);
		frmLogin.getContentPane().add(userPasswordLable);
		frmLogin.getContentPane().add(messageLable);
		frmLogin.getContentPane().add(userIDField);
		frmLogin.getContentPane().add(userPasswordField);
		frmLogin.getContentPane().add(loginButton);
		frmLogin.getContentPane().add(resetButton);
		frmLogin.setTitle("Login");
		
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.setSize(500,400);
		frmLogin.getContentPane().setLayout(null);
		
		
		TitleText.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
		TitleText.setBounds(222, 40, 75, 36);
		frmLogin.getContentPane().add(TitleText);
		
		
		registerButton.setBounds(345, 220, 100, 25);
		registerButton.addActionListener(this);
		frmLogin.getContentPane().add(registerButton);
		frmLogin.setVisible(true);
		
		
		
	}
	
	boolean isValidLogin(String userID, String password) {
        for (List<String> credentials : logininfo) {
            if (credentials.get(0).equals(userID) && credentials.get(1).equals(password)) {
                return true;
            }
        }
        return false;
    }

    boolean userExists(String userID) {
        for (List<String> credentials : logininfo) {
            if (credentials.get(0).equals(userID)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
		if (e.getSource()==resetButton) {
			userIDField.setText("");
			userPasswordField.setText("");
			messageLable.setText("");
		}
		if(e.getSource()==loginButton) {
			
			String userID = userIDField.getText();
			String password = String.valueOf(userPasswordField.getPassword());
			
			 if (userExists(userID)) {
	                if (isValidLogin(userID, password)) {
	                	messageLable.setForeground(Color.green);
	                	messageLable.setText("Login Successful");
	                    frmLogin.dispose(); //sterge pagina
	                    new MainScreen(userID);
	                } else {
	                	messageLable.setForeground(Color.red);
	                	messageLable.setText("Wrong Password");
	                }
	            } else {
	            	messageLable.setForeground(Color.red);
	            	messageLable.setText("Username not found");
	            }
		}
		if(e.getSource() == registerButton) {
			frmLogin.dispose();
			new RegisterScreen(logininfo);
		}
	}
}

