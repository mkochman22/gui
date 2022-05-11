//Mary Kochmanski, CIS 221, Tuesdays and Thursdays at 2:10 PM
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterPage implements ActionListener {
	
	private static JFrame frame;
	private static JPanel panel;
	private static JLabel userLabel;
	private static JTextField userText;
	private static JLabel passwordLabel;
	private static JPasswordField passwordText;
	private static JLabel confirmLabel;
	private static JPasswordField confirmText;
	private static JButton button;
	private static JLabel success;
	private static File userFile;
	private static JButton login;
	public static void main(String[] args) {
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                RegisterPage window = new RegisterPage();
	                window.frame.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}
	public RegisterPage(){
		//setting frame and panel, other aspects of GUI
		panel = new JPanel();
		
		//set frame
		frame = new JFrame();	
		//set panel
		
		
		//setting up the frame
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setTitle("Register Now");
		frame.add(panel);
		panel.setLayout(null);
		
		//username label
		userLabel = new JLabel("User");
		userLabel.setBounds(10, 20, 80, 25);
		panel.add(userLabel);
			
		//username input
		userText = new JTextField(20);
		userText.setBounds(100, 20 ,165, 25);
		panel.add(userText);
		
		//password label
		passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(10, 50, 80, 25);
		panel.add(passwordLabel);
		
		//password input
		passwordText = new JPasswordField();
		passwordText.setBounds (100, 50, 165, 25);
		panel.add(passwordText);
		
		//confirm password label
		confirmLabel = new JLabel("Confirm Password");
		confirmLabel.setBounds(10, 80, 80, 25);
		panel.add(confirmLabel);
		
		//confirm password text input
		confirmText = new JPasswordField();
		confirmText.setBounds(100, 80, 165, 25);
		panel.add(confirmText);
		
		//register button
		button = new JButton("Register"); 
		button.setBounds(10, 110, 100, 25);
		button.addActionListener(this);
		panel.add(button);
		
		//login button
		login = new JButton("Back to Login");
		login.setBounds(10, 170, 120,25);
		login.addActionListener(this);
		panel.add(login);
		
		//success label
		success = new JLabel("");
		success.setBounds(10, 140, 300, 25);
		panel.add(success);
		
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//if the register button is clicked, gets the text from the text fields
		if(e.getSource() == button) {
			//get text from text fields
			String user = userText.getText();
			String password = passwordText.getText();
			String passconfirm = confirmText.getText();

			//if the username and password text fields are empty
			if(user.equals("") || password.equals("")) {
				//display message
				success.setText("Username and password must be filled in");
			}else {
				//if the password matches the confirm password field
				if(password.equals(passconfirm)) {
					//sets result string to match text file format
					String result = "username:"+user+"|";
					//if the username was not found in the text file
					if(check(result) == true) {
						//display message
						success.setText("Registration successful");
						Writer output = null;
						//append the username and password in the correct format in the text file
						//append new line
						try {
							output = new BufferedWriter(new FileWriter("Username.txt", true));
							output.append("username:"+user+"|password:"+password);
							output.append('\n');
							output.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							System.out.println("There was an error");
						}
					//if the username was found in the text file
					}else {
						success.setText("That username is taken");
					}
				//if the password doesn't match the confirm password field
				}else {
					//display message
					success.setText("Passwords do not match, please try again");
				}
			}
		//if the login button is clicked, closes the registration page
		}else if(e.getSource() == login){
			GUI gui = new GUI();
			frame.setVisible(false);
		}
	}
	
	//function checks file to see if the username is already in the text file and return a boolean
	boolean check(String result) {
		
		//setting up file and scanner to read text file
		userFile= new File("Username.txt");
		
		Scanner scanner = null;
		try {
			scanner = new Scanner(userFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR: Try again later");
		}
		//initialize variable
		boolean check = true;
		//while the text file has a next line,
		while (scanner.hasNextLine()) {
			//sets the String, line, to the next line of the text file
			String line = scanner.nextLine();
			//if the username is in the next line of the file
			if(line.contains(result)) {
				//sets check to false and breaks the while loop
				check = false;
				break;
			}
		}
		scanner.close();
		return check;
	}
}
