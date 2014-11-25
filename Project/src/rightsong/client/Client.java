package rightsong.client;

import java.awt.EventQueue;
import java.io.IOException;
import java.util.List;

import ocsf.client.AbstractClient;
import rightsong.view.MainFrame;

/**
 * This class extends AbstractClient from the OCSF framework and overrides some
 * of its methods defined in order to give more functionality to the client.
 *
 * @author Karen Santos
 * @version November 2014
 */
public class Client extends AbstractClient {

	private static MainFrame window;

	private String username;
	private String email;

	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param host
	 *            The server to connect to.
	 * @param port
	 *            The port number to connect on.
	 * @param clientUI
	 *            The interface type variable.
	 * @param initMessage
	 *            The initial message from the client.
	 * @throws IOException
	 */
	public Client(String initMessage, String host, int port) throws IOException {
		super(host, port);
		openConnection();
		System.out.println("opening conection");
		sendToServer(initMessage);
		getInfo(initMessage);
	}

	/**
	 * Returns the username of the client.
	 * 
	 * @return The username of the client.
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username of the client.
	 * 
	 * @param username
	 *            The new username of the client.
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Returns the email of the client.
	 * 
	 * @return The email of the client.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the client.
	 * 
	 * @param username
	 *            The new email of the client.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg
	 *            The message from the server.
	 */
	public void handleMessageFromServer(Object msg) {

		if (msg instanceof String) {
			String[] messages = ((String) msg).split(" ");
			String command = messages[0];

			switch (command) {
			case "#success":
				window.getLoginPanel().switchToLogin();
				window.getLoginPanel().setLabelSuccessFromLogin(
						getUsername() + " registered with success!");
				
				break;

			case "#invalidEmail":
				window.getLoginPanel().setLabelErrorFromRegister(
						"This email is already registered.");
				break;

			case "#invalidUsername":
				window.getLoginPanel().setLabelErrorFromRegister(
						"This username is already registered.");
				clearInfo();
				
			case "#login":
				
				if(messages[1].equals("ok")){
					
					window.getLoginPanel().setVisible(false);
					window.getIndexPanel().setVisible(true);
					
					try {
						sendToServer("#data");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				} else {
					window.getLoginPanel().setLabelErrorFromLogin("Email or password incorret.");
				}

			default:
				break;
			}
		} else {
			
			window.getIndexPanel().setData((List<List>) msg);
			
		}
	}

	/**
	 * This method handles all data coming from the UI
	 *
	 * @param message
	 *            The message from the UI.
	 * @throws IOException
	 *             If the message could not be sent to the server.
	 */
	public void handleMessageFromClientUI(String message) throws IOException {

		if (message.startsWith("#login")) {

			sendToServer(message);
			System.out
					.println("Could not send message to server. Terminating client.");
			quit();
		} else {

		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		if (isConnected()) {
			try {
				sendToServer("#logoff");
				closeConnection();
			} catch (IOException e1) {
			}
		}
		System.out.println("Terminating client.");
		System.exit(0);
	}

	/**
	 * This method displays a message if the server has shutdown and terminates
	 * the client.
	 */
	@Override
	protected void connectionException(Exception exception) {
		System.out
				.println("Server has shutdown. Abnormal termination of connection.");
	}

	/**
	 * This method displays a message if the connection is closed.
	 */
	protected void connectionClosed() {
		System.out.println("You have been logged off.");
	}

	private void clearInfo() {
		setUsername("");
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MainFrame();
					window.setVisible(true);
					window.getLoginPanel().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void getInfo(String msg){
		if (msg.startsWith("#register")){
			username = msg.split(" ")[2];
		}
	}
}
