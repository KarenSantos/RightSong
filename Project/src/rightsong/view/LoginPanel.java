package rightsong.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import rightsong.client.Client;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private int port;
	private String host; 
	private Client client;

	private JPanel loginArea;
	private JPanel registerArea;

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
	public LoginPanel() {
		
		initialize();

		createLoginArea();
		createRegisterArea();
		createEvents();

		switchToLogin();
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	public Client getClient(){
		return client;
	}

	/**
	 * Switches the panel to the login panel.
	 */
	public void switchToLogin() {

		registerArea.setVisible(false);
		clearLoginForm();
		loginArea.setVisible(true);
	}

	/**
	 * Switches the panel to the register panel.
	 */
	public void switchToRegister() {

		loginArea.setVisible(false);
		clearRegisterForm();
		registerArea.setVisible(true);
	}
	
	public void setLabelErrorFromLogin(String msg) {
		lblErrorFromLogin.setText(msg);
		
	}

	public void setLabelSuccessFromLogin(String msg) {
		lblSuccessFromLogin.setText(msg);
	}

	public void setLabelErrorFromRegister(String msg) {
		lblErrorFromRegister.setText(msg);
	}
	

	public void clearLoginForm() {
		loginTextField.setText("");
		passwordField.setText("");
		lblErrorFromLogin.setText("");
		lblSuccessFromLogin.setText("");
	}

	public void clearRegisterForm() {
		emailRegisterTextField.setText("");
		usernameRegTextField.setText("");
		passwordReg1Field.setText("");
		passwordReg2Field.setText("");
		lblErrorFromRegister.setText("");
	}

	private void createEvents() {

		btnLoginFromLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String email = loginTextField.getText();
				String password = passwordField.getText();
				if (email.equals("")) {
					lblErrorFromLogin.setText("Please enter your login email.");
				} else if (password.equals("")){
					lblErrorFromLogin.setText("Please enter a password.");
				} else {
					try {
						client = new Client("#login " + email + " " + password, host, port);
						
					} catch (IOException e2) {
						lblErrorFromLogin
								.setText("Cannot open connection. Please check your internet and try again.");
					}
				}
			}
		});

		btnRegisterFromLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				clearRegisterForm();
				loginArea.setVisible(false);
				registerArea.setVisible(true);
			}
		});

		btnLoginFromRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				clearLoginForm();
				registerArea.setVisible(false);
				loginArea.setVisible(true);
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
					lblErrorFromRegister
							.setText("Please enter a valid username.");
				} else if (!password1.equals(password2)) {
					lblErrorFromRegister.setText("Passwords did not match.");
				} else {
					try {
						client = new Client("#register " + email + " " + username + " " + password1, host, port);
					} catch (IOException e2) {
						lblErrorFromRegister
								.setText("Cannot open connection. Please check your internet and try again.");
					}
				}
			}
		});

	}

	private void initialize() {
		setBounds(6, 6, 888, 597);
		setBackground(Color.WHITE);
		setLayout(null);

		JPanel logoPanel = new JPanel();
		logoPanel.setBounds(6, 6, 230, 90);
		add(logoPanel);
		logoPanel.setBackground(Color.WHITE);
		logoPanel.setLayout(null);

		JLabel lblRightsong = new JLabel("RightSong");
		lblRightsong.setBounds(6, 6, 230, 90);
		lblRightsong.setHorizontalAlignment(SwingConstants.CENTER);
		lblRightsong.setFont(new Font("Lucida Grande", Font.PLAIN, 27));
		lblRightsong.setIcon(new ImageIcon(Test.class
				.getResource("/rightsong/resources/musicalNote.png")));
		logoPanel.add(lblRightsong);

		JLabel lblDescription = new JLabel(getDescription());
		lblDescription.setBounds(10, 490, 868, 107);
		lblDescription.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblDescription.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblDescription);
	}

	private void createLoginArea() {

		loginArea = new JPanel();
		loginArea.setBounds(149, 105, 559, 303);
		add(loginArea);
		loginArea.setLayout(null);

		JPanel loginFormPanel = new JPanel();
		loginFormPanel.setBounds(84, 28, 374, 162);
		loginArea.add(loginFormPanel);
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
		loginArea.add(lblErrorFromLogin);
		lblErrorFromLogin.setForeground(Color.RED);
		lblErrorFromLogin.setHorizontalAlignment(SwingConstants.CENTER);

		lblSuccessFromLogin = new JLabel("");
		lblSuccessFromLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblSuccessFromLogin.setForeground(new Color(50, 205, 50));
		lblSuccessFromLogin.setBounds(25, 202, 513, 29);
		loginArea.add(lblSuccessFromLogin);

		JLabel lblRegisterToCreate = new JLabel(
				"Register to create a new account");
		lblRegisterToCreate.setBounds(115, 243, 252, 29);
		loginArea.add(lblRegisterToCreate);
		lblRegisterToCreate.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		btnRegisterFromLogin = new JButton("Register");
		btnRegisterFromLogin.setBounds(368, 244, 90, 29);
		loginArea.add(btnRegisterFromLogin);
		btnRegisterFromLogin.setFont(new Font("Lucida Grande", Font.PLAIN, 14));

	}

	private void createRegisterArea() {

		registerArea = new JPanel();
		registerArea.setBounds(149, 105, 589, 371);
		add(registerArea);
		registerArea.setLayout(null);

		JPanel registerFormPanel = new JPanel();
		registerFormPanel.setBounds(69, 28, 449, 254);
		registerArea.add(registerFormPanel);
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
		registerArea.add(lblErrorFromRegister);
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
		registerArea.add(lblImAlreadyRegistered);

		btnLoginFromRegister = new JButton("Login");
		btnLoginFromRegister.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnLoginFromRegister.setBounds(437, 329, 80, 33);
		registerArea.add(btnLoginFromRegister);
	}

	private String getDescription() {
		return "<html><center>With RightSong you can search for different kinds of song and see<br>"
				+ "its title, artists, lyrics and even chords! You can also upload<br>"
				+ "new songs and share with other users. Don't know how to find a song?<br>"
				+ "You can search by tags or genrers, or even the speed of song.<br>"
				+ "Try now! Register for free.</center></html>";
	}

}
