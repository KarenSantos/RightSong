package rightsong.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import junit.framework.Test;
import rightsong.client.Client;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private int port;
	private String host;
	private Client client;
	private String loginMsg;

	private MainFrame mainFrame;

	private JPanel loginArea;
	private JPanel registerArea;
	private JPanel setHostArea;

	private JTextField loginTextField;
	private JTextField passwordTextField;
	private JTextField emailRegisterTextField;
	private JTextField usernameRegTextField;
	private JTextField passwordReg1TextField;
	private JTextField passwordReg2TextField;
	private JTextField setHostTextField;

	private JLabel lblErrorFromLogin;
	private JLabel lblErrorFromRegister;
	private JLabel lblSuccessFromLogin;
	private JLabel lblShowingCurrentHost;
	private JLabel lblErrorFromSetHost;

	private JButton btnLoginFromLogin;
	private JButton btnRegisterFromLogin;
	private JButton btnLoginFromRegister;
	private JButton btnRegisterFromRegister;
	private JButton btnOkFromSetHost;
	private JButton btnCancelFromSetHost;

	private MessageFrame alert;

	/**
	 * Create the panel.
	 */
	public LoginPanel(MainFrame mainFrame, int port, String host) {

		this.mainFrame = mainFrame;
		this.port = port;
		this.host = host;

		initialize();

		createLoginArea();
		createRegisterArea();
		createSetHostArea();
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

	public Client getClient() {
		return client;
	}

	public JTextField getLoginTextField() {
		return loginTextField;
	}

	/**
	 * Switches the panel to the login panel.
	 */
	public void switchToLogin() {
		registerArea.setVisible(false);
		setHostArea.setVisible(false);

		clearLoginForm();
		loginArea.setVisible(true);
	}

	/**
	 * Switches the panel to the register panel.
	 */
	public void switchToRegister() {
		loginArea.setVisible(false);
		setHostArea.setVisible(false);

		clearRegisterForm();
		registerArea.setVisible(true);
	}

	public void switchToSetHost() {
		loginArea.setVisible(false);
		registerArea.setVisible(false);

		clearSetHostForm();
		lblShowingCurrentHost.setText("\"" + host + "\"");
		setHostArea.setVisible(true);
	}

	public void setLabelErrorFromLogin(String msg) {
		lblSuccessFromLogin.setText("");
		lblErrorFromLogin.setText(msg);
	}

	public void setLabelSuccessFromLogin(String msg) {
		lblErrorFromLogin.setText("");
		lblSuccessFromLogin.setText(msg);
	}

	public void setLabelErrorFromRegister(String msg) {
		lblErrorFromRegister.setText(msg);
	}

	public void clearLoginForm() {
		loginTextField.setText("");
		passwordTextField.setText("");
		lblErrorFromLogin.setText("");
		lblSuccessFromLogin.setText("");
	}

	public void clearRegisterForm() {
		emailRegisterTextField.setText("");
		usernameRegTextField.setText("");
		passwordReg1TextField.setText("");
		passwordReg2TextField.setText("");
		lblErrorFromRegister.setText("");
	}

	public void clearSetHostForm() {
		setHostTextField.setText("");
		lblErrorFromSetHost.setText("");
	}

	public void reconnect() throws IOException {
		client = new Client(mainFrame, loginMsg, host, port);
		mainFrame.setClient(client);
	}

	private void createEvents() {

		btnLoginFromLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String email = loginTextField.getText();
				String password = passwordTextField.getText();
				if (email.equals("")) {
					setLabelErrorFromLogin("Please enter your login email.");
				} else if (password.equals("")) {
					setLabelErrorFromLogin("Please enter a password.");
				} else {
					try {
						loginMsg = "#login " + email + " " + password;
						client = new Client(mainFrame, loginMsg, host, port);
						mainFrame.setClient(client);

					} catch (IOException e2) {
						setLabelErrorFromLogin("Cannot open connection. Check your internet or host name and try again.");
					}
				}
			}
		});

		loginTextField.addKeyListener((new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					passwordTextField.requestFocusInWindow();
				}
			}
		}));

		passwordTextField.addKeyListener((new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					btnLoginFromLogin.doClick();
				}
			}
		}));

		btnRegisterFromLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToRegister();
			}
		});

		btnLoginFromRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToLogin();
			}
		});

		btnRegisterFromRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String email = emailRegisterTextField.getText();
				String username = usernameRegTextField.getText();
				String password1 = passwordReg1TextField.getText();
				String password2 = passwordReg2TextField.getText();

				if (email.equals("")) {
					lblErrorFromRegister.setText("Please enter a valid email.");
				} else if (username.equals("")) {
					lblErrorFromRegister
							.setText("Please enter a valid username.");
				} else if (!password1.equals(password2)) {
					lblErrorFromRegister.setText("Passwords did not match.");
				} else {
					try {
						client = new Client(mainFrame, "#register " + email
								+ " " + username + " " + password1, host, port);
					} catch (IOException e2) {
						lblErrorFromRegister
								.setText("Cannot open connection. Check your internet or host name and try again.");
					}
				}
			}
		});

		emailRegisterTextField.addKeyListener((new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					usernameRegTextField.requestFocusInWindow();
				}
			}
		}));

		usernameRegTextField.addKeyListener((new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					passwordReg1TextField.requestFocusInWindow();
				}
			}
		}));

		passwordReg1TextField.addKeyListener((new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					passwordReg2TextField.requestFocusInWindow();
				}
			}
		}));

		passwordReg2TextField.addKeyListener((new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					btnRegisterFromRegister.doClick();
				}
			}
		}));

		btnOkFromSetHost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String newHost = setHostTextField.getText();
				if (newHost.equals("")) {
					lblErrorFromSetHost
							.setText("Please enter a valid host name.");
				} else {

					host = newHost;
					mainFrame.setHost(host);
					switchToLogin();

					if (alert == null || alert.isClosed()) {
						alert = new MessageFrame(mainFrame.getDesktopPane1());
						alert.setMessage("New host set with success. Current host: \""
								+ "<i>" + mainFrame.getHost() + "</i>\"");
					}
				}
			}
		});

		btnCancelFromSetHost.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToLogin();
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

		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(99, 87, 245, 34);
		loginFormPanel.add(passwordTextField);

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

		passwordReg1TextField = new JPasswordField();
		passwordReg1TextField.setBounds(155, 125, 283, 40);
		registerFormPanel.add(passwordReg1TextField);

		JLabel lblRegisterForFree = new JLabel("Register for Free!");
		lblRegisterForFree.setBounds(149, 12, 136, 19);
		lblRegisterForFree.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		registerFormPanel.add(lblRegisterForFree);

		JLabel lblPassword2 = new JLabel("Repeat password:");
		lblPassword2.setBounds(22, 177, 121, 17);
		lblPassword2.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		registerFormPanel.add(lblPassword2);

		passwordReg2TextField = new JPasswordField();
		passwordReg2TextField.setBounds(155, 166, 283, 40);
		registerFormPanel.add(passwordReg2TextField);

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

	private void createSetHostArea() {
		setHostArea = new JPanel();
		setHostArea.setBounds(149, 105, 560, 271);
		add(setHostArea);
		setHostArea.setLayout(null);

		JPanel setHostFormPanel = new JPanel();
		setHostFormPanel.setBounds(93, 28, 374, 187);
		setHostArea.add(setHostFormPanel);
		setHostFormPanel.setBackground(Color.WHITE);
		setHostFormPanel.setLayout(null);

		JLabel lblSetHost = new JLabel("Set Host");
		lblSetHost.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblSetHost.setBackground(new Color(238, 238, 238));
		lblSetHost.setBounds(158, 6, 69, 29);
		setHostFormPanel.add(lblSetHost);

		JLabel lblCurrentHost = new JLabel("Current Host:");
		lblCurrentHost.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblCurrentHost.setBounds(28, 47, 98, 22);
		setHostFormPanel.add(lblCurrentHost);

		lblShowingCurrentHost = new JLabel("");
		lblShowingCurrentHost
				.setFont(new Font("Lucida Grande", Font.ITALIC, 14));
		lblShowingCurrentHost.setBounds(126, 47, 226, 22);
		setHostFormPanel.add(lblShowingCurrentHost);

		JLabel lblSetTo = new JLabel("Set Host to:");
		lblSetTo.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblSetTo.setBounds(28, 91, 98, 27);
		setHostFormPanel.add(lblSetTo);

		setHostTextField = new JTextField();
		setHostTextField.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		setHostTextField.setBounds(113, 91, 241, 27);
		setHostFormPanel.add(setHostTextField);
		setHostTextField.setColumns(10);

		btnOkFromSetHost = new JButton("Ok");
		btnOkFromSetHost.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnOkFromSetHost.setBounds(163, 137, 98, 27);
		setHostFormPanel.add(btnOkFromSetHost);

		btnCancelFromSetHost = new JButton("Cancel");
		btnCancelFromSetHost.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnCancelFromSetHost.setBounds(261, 137, 91, 27);
		setHostFormPanel.add(btnCancelFromSetHost);

		lblErrorFromSetHost = new JLabel("");
		lblErrorFromSetHost.setBounds(24, 222, 513, 29);
		setHostArea.add(lblErrorFromSetHost);
		lblErrorFromSetHost.setForeground(Color.RED);
		lblErrorFromSetHost.setHorizontalAlignment(SwingConstants.CENTER);

	}

	private String getDescription() {
		return "<html><center>With RightSong you can search for different kinds of song and see<br>"
				+ "its title, artists, lyrics and even chords! You can also upload<br>"
				+ "new songs and share with other users. Don't know how to find a song?<br>"
				+ "You can search by tags or genrers, or even the speed of song.<br>"
				+ "Try now! Register for free.</center></html>";
	}

}
