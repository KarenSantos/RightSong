package rightsong.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import rightsong.client.Client;

public class IndexPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	final private int PORT = 7777;
	final private String HOST = "localhost";
	private Client client;

	private JPanel loginPanel;
	private JPanel registerPanel;

	private JTextField loginTextField;
	private JTextField passwordField;

	private JTextField emailRegisterTextField;
	private JTextField usernameRegTextField;
	private JTextField passwordReg1Field;
	private JTextField passwordReg2Field;

	private JLabel lblErrorFromLogin;
	private JLabel lblErrorFromRegister;
	private JLabel lblSuccessFromLogin;

	private JButton btnLoginFromLogin;
	private JButton btnRegisterFromLogin;
	private JButton btnLoginFromRegister;
	private JButton btnRegisterFromRegister;

	/**
	 * Create the panel.
	 */
	public IndexPanel() {

		initialize();

		createLoginPanel();
		loginPanel.setVisible(true);
		createRegisterPanel();
		registerPanel.setVisible(false);

		createEvents();
	}
	
	public void setLabelSuccessFromLogin(String msg){
		lblSuccessFromLogin.setText(msg);
	}
	
	public void setLabelErrorFromRegister(String msg){
		lblErrorFromRegister.setText(msg);
	}
	

	private void createEvents() {

		btnLoginFromLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String login = loginTextField.getText();
				String password = passwordField.getText();
				if (login.equals("")) {
					lblErrorFromLogin.setText("Please enter your login email.");
				} else {
					try {
						client = new Client("#login " + login + " " + password,
								HOST, PORT);
					} catch (IOException e2) {
						lblErrorFromLogin
								.setText("Cannot open connection. Please check your internet and try again.");
					}
				}
			}
		});

		btnRegisterFromLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				clearRegistrationForm();
				loginPanel.setVisible(false);
				registerPanel.setVisible(true);
			}
		});

		btnLoginFromRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clearLoginForm();
				registerPanel.setVisible(false);
				loginPanel.setVisible(true);
			}
		});

		btnRegisterFromRegister.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				String email = emailRegisterTextField.getText();
				String username = usernameRegTextField.getText();
				String password1 = passwordReg1Field.getText();
				String password2 = passwordReg2Field.getText();

				if (email.equals("")) {
					lblErrorFromRegister.setText("Please enter a valid email.");
				} else if (username.equals("")) {
					lblErrorFromRegister.setText("Please enter a valid username.");
				} else if (!password1.equals(password2)) {
					lblErrorFromRegister.setText("Passwords did not match.");
				} else {
					try {
						client = new Client("#register " + email + " "
								+ username + " " + password1, HOST, PORT);
					} catch (IOException e2) {
						lblErrorFromRegister
								.setText("Cannot open connection. Please check your internet and try again.");
					}
				}
			}
		});

	}

	private void initialize() {
		setBounds(6, 6, 888, 616);
		setBackground(Color.WHITE);
		setLayout(null);

		JPanel logoPanel = new JPanel();
		logoPanel.setBounds(6, 6, 175, 62);
		add(logoPanel);
		logoPanel.setBackground(Color.WHITE);
		logoPanel.setLayout(null);

		JLabel lblRightsong = new JLabel("RightSong");
		lblRightsong.setBounds(6, 6, 158, 40);
		lblRightsong.setHorizontalAlignment(SwingConstants.CENTER);
		lblRightsong.setFont(new Font("Lucida Grande", Font.PLAIN, 27));
		logoPanel.add(lblRightsong);
	}

	private void createLoginPanel() {

		loginPanel = new JPanel();
		loginPanel.setBounds(149, 105, 559, 303);
		add(loginPanel);
		loginPanel.setLayout(null);

		JPanel loginFormPanel = new JPanel();
		loginFormPanel.setBounds(84, 28, 374, 162);
		loginPanel.add(loginFormPanel);
		loginFormPanel.setBackground(Color.WHITE);
		loginFormPanel.setLayout(null);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblLogin.setBackground(new Color(238, 238, 238));
		lblLogin.setBounds(158, 6, 69, 29);
		loginFormPanel.add(lblLogin);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(50, 55, 41, 17);
		lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		loginFormPanel.add(lblEmail);

		loginTextField = new JTextField();
		loginTextField.setBounds(97, 47, 247, 34);
		loginFormPanel.add(loginTextField);
		loginTextField.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(23, 95, 69, 17);
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		loginFormPanel.add(lblPassword);

		passwordField = new JPasswordField();
		passwordField.setBounds(99, 87, 245, 34);
		loginFormPanel.add(passwordField);

		btnLoginFromLogin = new JButton("Login");
		btnLoginFromLogin.setBounds(262, 127, 82, 29);
		btnLoginFromLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		loginFormPanel.add(btnLoginFromLogin);

		lblErrorFromLogin = new JLabel("");
		lblErrorFromLogin.setBounds(25, 202, 513, 29);
		loginPanel.add(lblErrorFromLogin);
		lblErrorFromLogin.setForeground(Color.RED);
		lblErrorFromLogin.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblSuccessFromLogin = new JLabel("");
		lblSuccessFromLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuccessFromLogin.setForeground(new Color(50, 205, 50));
		lblSuccessFromLogin.setBounds(25, 202, 513, 29);
		loginPanel.add(lblSuccessFromLogin);

		JLabel lblRegisterToCreate = new JLabel(
				"Register to create a new account");
		lblRegisterToCreate.setBounds(115, 243, 252, 29);
		loginPanel.add(lblRegisterToCreate);
		lblRegisterToCreate.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		btnRegisterFromLogin = new JButton("Register");
		btnRegisterFromLogin.setBounds(368, 244, 90, 29);
		loginPanel.add(btnRegisterFromLogin);
		btnRegisterFromLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 14));

	}

	private void createRegisterPanel() {

		registerPanel = new JPanel();
		registerPanel.setBounds(149, 105, 589, 371);
		add(registerPanel);
		registerPanel.setLayout(null);

		JPanel registerFormPanel = new JPanel();
		registerFormPanel.setBounds(69, 28, 449, 254);
		registerPanel.add(registerFormPanel);
		registerFormPanel.setBackground(Color.WHITE);
		registerFormPanel.setLayout(null);

		JLabel lblEmail = new JLabel("Enter your email:");
		lblEmail.setBounds(27, 54, 116, 17);
		lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		registerFormPanel.add(lblEmail);

		emailRegisterTextField = new JTextField();
		emailRegisterTextField.setBounds(155, 43, 283, 40);
		registerFormPanel.add(emailRegisterTextField);
		emailRegisterTextField.setColumns(10);

		JLabel lblPassword1 = new JLabel("Create password:");
		lblPassword1.setBounds(24, 135, 119, 17);
		lblPassword1.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		registerFormPanel.add(lblPassword1);

		passwordReg1Field = new JPasswordField();
		passwordReg1Field.setBounds(155, 125, 283, 40);
		registerFormPanel.add(passwordReg1Field);

		JLabel lblRegisterForFree = new JLabel("Register for Free!");
		lblRegisterForFree.setBounds(149, 12, 136, 19);
		lblRegisterForFree.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		registerFormPanel.add(lblRegisterForFree);

		JLabel lblPassword2 = new JLabel("Repeat password:");
		lblPassword2.setBounds(22, 177, 121, 17);
		lblPassword2.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		registerFormPanel.add(lblPassword2);

		passwordReg2Field = new JPasswordField();
		passwordReg2Field.setBounds(155, 166, 283, 40);
		registerFormPanel.add(passwordReg2Field);

		lblErrorFromRegister = new JLabel("");
		lblErrorFromRegister.setBounds(25, 294, 534, 29);
		registerPanel.add(lblErrorFromRegister);
		lblErrorFromRegister.setForeground(Color.RED);
		lblErrorFromRegister.setHorizontalAlignment(SwingConstants.CENTER);

		btnRegisterFromRegister = new JButton("Register");
		btnRegisterFromRegister.setBounds(338, 215, 100, 33);
		btnRegisterFromRegister.setFont(new Font("Lucida Grande", Font.PLAIN,
				14));
		registerFormPanel.add(btnRegisterFromRegister);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblUsername.setBounds(65, 91, 78, 25);
		registerFormPanel.add(lblUsername);

		usernameRegTextField = new JTextField();
		usernameRegTextField.setBounds(155, 84, 283, 40);
		registerFormPanel.add(usernameRegTextField);
		usernameRegTextField.setColumns(10);
		
		JLabel lblImAlreadyRegistered = new JLabel("I'm already registered");
		lblImAlreadyRegistered
		.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblImAlreadyRegistered.setBounds(276, 335, 167, 19);
		registerPanel.add(lblImAlreadyRegistered);

		btnLoginFromRegister = new JButton("Login");
		btnLoginFromRegister.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnLoginFromRegister.setBounds(437, 329, 80, 33);
		registerPanel.add(btnLoginFromRegister);
	}

	private void clearLoginForm() {
		loginTextField.setText("");
		passwordField.setText("");
		lblErrorFromLogin.setText("");
		lblSuccessFromLogin.setText("");
	}
	
	private void clearRegistrationForm() {
		emailRegisterTextField.setText("");
		usernameRegTextField.setText("");
		passwordReg1Field.setText("");
		passwordReg2Field.setText("");
		lblErrorFromRegister.setText("");
	}
}
