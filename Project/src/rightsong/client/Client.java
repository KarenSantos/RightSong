package rightsong.client;

import java.io.IOException;

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

	/**
	 * The UI main frame.
	 */
	private MainFrame window;

	private String username;
	private String email;

	private boolean sync;

	/**
	 * Constructs an instance of the right song client.
	 *
	 * @param frame
	 *            The main frame from the client UI.
	 * @param initMessage
	 *            The initial message from the client.
	 * @param host
	 *            The server to connect to.
	 * @param port
	 *            The port number to connect on.
	 * 
	 * @throws IOException
	 */
	public Client(MainFrame frame, String initMessage, String host, int port)
			throws IOException {
		super(host, port);
		this.window = frame;
		openConnection();
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
	 * Sets the control variable sync.
	 * 
	 * @param sync
	 *            The control variable sync.
	 */
	public void setSync(boolean sync) {
		this.sync = sync;
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
			case "#register":

				if (messages[1].equals("success")) {

					window.getLoginPanel().switchToLogin();
					window.getLoginPanel().setLabelSuccessFromLogin(
							getUsername() + " registered with success!");
				} else if(messages[1].equals("invalidEmail")){
					window.getLoginPanel().setLabelErrorFromRegister(
							"This email is already registered.");
				} else if(messages[1].equals("invalidUsername")){
					window.getLoginPanel().setLabelErrorFromRegister(
							"This username is already registered.");
					clearInfo();
				}
				try {
					sendToServer("#quit");
					closeConnection();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case "#login":

				if (messages[1].equals("ok")) {

					try {
						sendToServer("#data");
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					window.getLoginPanel().setLabelErrorFromLogin(
							"Email or password incorret.");
				}
			case "#song":
				if (messages[1].equals("added")) {
					sync = true;
					try {
						sendToServer("#data");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
				}

			default:
				break;
			}
		} else {
			if (msg instanceof Object) {
				if (window.getController().setData(msg)) {
					if (sync) {
						window.clearIndexPanel();
						sync = false;
					} else {
						window.switchToIndexPanel();
					}
				}
			}
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
		System.out.println("Server has terminated connection.");
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

	private void getInfo(String msg) {
		if (msg.startsWith("#register")) {
			username = msg.split(" ")[2];
		}
	}
}
