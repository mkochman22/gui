//Mary Kochmanski, CIS 221, Tuesdays and Thursdays at 2:10 PM

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sun.tools.javac.Main;

public class GUI implements ActionListener {
	private static JFrame frame;
	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passLabel;
	private static JPasswordField passText;
	private static JButton loginButton;
	private static JLabel success;
	private static JButton register;
	
	private int userLine;
	private int passLine; 

	public static void main(String[] args) {
		
		//setting up frame and panel - visual aspects of gui
		JPanel panel = new JPanel();
		
		
		frame = new JFrame();
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Login");
		frame.setVisible(true);
		frame.add(panel);
		panel.setLayout(null);
		
		userLabel = new JLabel("User");
		userLabel.setBounds(10, 20, 80, 25);
		panel.add(userLabel);
		
		userText = new JTextField(20);
		userText.setBounds(100, 20, 165, 25);
		panel.add(userText);
		
		passLabel = new JLabel("Password");
		passLabel.setBounds(10, 50, 80, 25);
		panel.add(passLabel);
		
		passText = new JPasswordField();
		passText.setBounds(100, 50, 165, 25);
		panel.add(passText);
		
		loginButton = new JButton("Login");
		loginButton.setBounds(10, 80, 80, 25);
		loginButton.addActionListener(new GUI());
		panel.add(loginButton);
		
		success = new JLabel("");
		success.setBounds(10, 110, 300, 25);
		panel.add(success);
		
		register = new JButton("Register");
		register.setBounds(120, 80, 100, 25);
		register.addActionListener(new GUI());
		panel.add(register);
		
		frame.setVisible(true);
	}
	
	
	//function checks against a text file that has the usernames and passwords stored. 
	//returns a string value
	//if the username is found in the file and the corresponding password is correct, returns true
	//if the username is found in the file but the corresponding password is incorrect, returns "password"
	//if the username is not found in the file, returns "username"
	public String checkuser(String result, String user) {
		File userFile = new File("Username.txt");
		
		//initializing scanner, line, and check variables
		Scanner scanner = null;
		String line = "";
		String check = "";
		
		try {
			scanner = new Scanner(userFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: Try again later");
		}
		
		
		//while text file has next line
		while (scanner.hasNextLine()) {
			
			//line is equal to the text of the next line
			line = scanner.nextLine();
			
			//if line contains the username, sets check == password and checks if the password is correct, breaks the while loop
			if(line.contains(user+"|")) {
				check ="password";
				if(line.equals(result)) {
					check = "true";
				}
				break;
			}
		}
		
		//if check is not equal to true and not equal to password
		if(check != "true") {
			if(check !="password") {
				check = "username";
			}
		}
		//close the scanner
		scanner.close();
		return check;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//if the login button was clicked, gets the text from text fields and checks to see if they are in the text file
		//if the username is found in the file with corresponding password and it matches what the user inputted in the text field, displays a message that says login successful
		//if the username is found but the password inputted does not match, displays a message that the password is incorrect
		//if the username is not found, displays a message that the username does not exist
		if(e.getSource()==loginButton) {
			
			//gets the text from the user and password text fields
			String user = userText.getText();
			String password = passText.getText();
			
			//string result puts user and password in format to check against text file
			String result = "username:"+user+"|password:"+password;
			
			//if user and password are found in the file
			if(checkuser(result, user) == "true"){
				//login is successful
				success.setText("Login successful");
			//if user if found but the inputted password does not match
			} else if(checkuser(result, user) == "password") {
				//password was incorrect
				success.setText("Login unsuccessful: Password incorrect");
			//if user is not found in text file
			}else if (checkuser(result, user) == "username") {
				//username does not exist
				success.setText("Login unsuccessful: Username not found");
			}else {
				System.out.println("There was an error");
			}
			
		//if register button was clicked, opens registration page
		}else if(e.getSource() == register) {
			RegisterPage rp = new RegisterPage();
			
		}
	}

}