package rightsong.client;

import ocsf.client.*;

import java.awt.EventQueue;
import java.io.*;

import javax.swing.JFrame;

import rightsong.view.IndexPanel;
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
	
	/**
	 * The login ID used by the client to be identified by other clients and the
	 * server.
	 */
	private String loginID;

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
		sendToServer(initMessage);
	}

	/**
	 * Returns the login ID of the client.
	 * 
	 * @return The login ID of the client.
	 */
	public String getLoginID() {
		return loginID;
	}

	/**
	 * Sets the login ID of the client.
	 */
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg
	 *            The message from the server.
	 */
	public void handleMessageFromServer(Object msg) {
		
		if (msg.equals("#success")) {

			window.getIndexPanel().setLabelSuccessFromLogin((String) msg);
			window.getIndexPanel();//RESET THE RIGHT PANELS AND ALL
			System.out.println("user registered with success");
			
		} else {
			
			window.getIndexPanel().setLabelErrorFromRegister((String) msg);
			
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
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MainFrame();
					window.setVisible(true);
					window.getIndexPanel().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
