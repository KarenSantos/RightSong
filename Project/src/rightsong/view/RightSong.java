package rightsong.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import rightsong.client.Client;

public class RightSong {

	final private int PORT = 7777;
	final private String HOST = "RightSong";
	private Client client;

	private JFrame frame;
	private JTextField loginTextField;
	private JTextField passwordField;

	private JPanel indexPanel;
	private JPanel loginPanel;
	private JPanel registerPanel;

	private JTextField emailRegisterTextField;
	private JTextField usernameRegTextField;
	private JTextField passwordReg1Field;
	private JTextField passwordReg2Field;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RightSong window = new RightSong();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RightSong() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 900, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		indexPanel();
		indexPanel.setVisible(true);

		loginPanel();
		loginPanel.setVisible(true);
		registerPanel();
		registerPanel.setVisible(false);
	}

	private void indexPanel() {

		indexPanel = new JPanel();
		indexPanel.setBounds(6, 6, 888, 616);
		frame.getContentPane().add(indexPanel);
		indexPanel.setBackground(Color.WHITE);
		indexPanel.setLayout(null);

		JPanel logoPanel = new JPanel();
		logoPanel.setBounds(6, 6, 175, 62);
		indexPanel.add(logoPanel);
		logoPanel.setBackground(Color.WHITE);
		logoPanel.setLayout(null);

		JLabel lblRightsong = new JLabel("RightSong");
		lblRightsong.setBounds(6, 6, 158, 40);
		lblRightsong.setHorizontalAlignment(SwingConstants.CENTER);
		lblRightsong.setFont(new Font("Lucida Grande", Font.PLAIN, 27));
		logoPanel.add(lblRightsong);
	}

	private void loginPanel() {

		loginPanel = new JPanel();
		loginPanel.setBounds(149, 105, 559, 303);
		indexPanel.add(loginPanel);
		loginPanel.setLayout(null);

		JPanel loginFormPanel = new JPanel();
		loginFormPanel.setBounds(84, 28, 374, 162);
		loginPanel.add(loginFormPanel);
		loginFormPanel.setBackground(Color.WHITE);
		loginFormPanel.setLayout(null);

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

		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(262, 127, 82, 29);
		btnLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		loginFormPanel.add(btnLogin);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblLogin.setBackground(new Color(238, 238, 238));
		lblLogin.setBounds(158, 6, 69, 29);
		loginFormPanel.add(lblLogin);

		JLabel lblError = new JLabel("");
		lblError.setBounds(25, 202, 513, 29);
		loginPanel.add(lblError);
		lblError.setForeground(Color.RED);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblRegisterToCreate = new JLabel(
				"Register to create a new account");
		lblRegisterToCreate.setBounds(115, 243, 252, 29);
		loginPanel.add(lblRegisterToCreate);
		lblRegisterToCreate.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(368, 244, 90, 29);
		loginPanel.add(btnRegister);
		btnRegister.setFont(new Font("Lucida Grande", Font.PLAIN, 14));

		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				emailRegisterTextField.setText("");
				usernameRegTextField.setText("");
				passwordReg1Field.setText("");
				passwordReg2Field.setText("");
				lblError.setText("");
				loginPanel.setVisible(false);
				registerPanel.setVisible(true);

			}
		});

		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lblError.setText("");

				String login = loginTextField.getText();
				String password = passwordField.getText();
				if (login.equals("")) {
					lblError.setText("Please enter your login email.");
				} else {
					try {
						client = new Client("#login " + login + " " + password,
								HOST, PORT);
					} catch (IOException e2) {
						lblError.setText("Cannot open connection. Please check your internet and try again.");
					}
				}
			}
		});
	}

	private void registerPanel() {

		registerPanel = new JPanel();
		registerPanel.setBounds(149, 105, 589, 371);
		indexPanel.add(registerPanel);
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

		JLabel lblNewLabel = new JLabel("Register for Free!");
		lblNewLabel.setBounds(149, 12, 136, 19);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		registerFormPanel.add(lblNewLabel);

		JLabel lblPassword2 = new JLabel("Repeat password:");
		lblPassword2.setBounds(22, 177, 121, 17);
		lblPassword2.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		registerFormPanel.add(lblPassword2);

		passwordReg2Field = new JPasswordField();
		passwordReg2Field.setBounds(155, 166, 283, 40);
		registerFormPanel.add(passwordReg2Field);

		JLabel lblError = new JLabel("");
		lblError.setBounds(25, 294, 534, 29);
		registerPanel.add(lblError);
		lblError.setForeground(Color.RED);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblImAlreadyRegistered = new JLabel("I'm already registered");
		lblImAlreadyRegistered
				.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblImAlreadyRegistered.setBounds(276, 335, 167, 19);
		registerPanel.add(lblImAlreadyRegistered);

		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(338, 215, 100, 33);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				lblError.setText("");

				String email = emailRegisterTextField.getText();
				String username = usernameRegTextField.getText();
				String password1 = passwordReg1Field.getText();
				String password2 = passwordReg2Field.getText();

				if (email.equals("")) {
					lblError.setText("Please enter a valid email.");
				} else if (username.equals("")) {
					lblError.setText("Please enter a valid username.");
				} else if (!password1.equals(password2)) {
					lblError.setText("Passwords did not match.");
				} else {
					try {
						client = new Client("#register " + email + " "
								+ username + " " + password1, HOST, PORT);
					} catch (IOException e2) {
						lblError.setText("Cannot open connection. Please check your internet and try again.");
					}
				}
			}
		});
		btnRegister.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		registerFormPanel.add(btnRegister);

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblUsername.setBounds(65, 91, 78, 25);
		registerFormPanel.add(lblUsername);

		usernameRegTextField = new JTextField();
		usernameRegTextField.setBounds(155, 84, 283, 40);
		registerFormPanel.add(usernameRegTextField);
		usernameRegTextField.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnLogin.setBounds(437, 329, 80, 33);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				loginTextField.setText("");
				passwordField.setText("");
				lblError.setText("");
				registerPanel.setVisible(false);
				loginPanel.setVisible(true);

			}
		});
		registerPanel.add(btnLogin);
	}
}
