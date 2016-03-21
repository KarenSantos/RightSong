package rightsong.server;

import java.io.IOException;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import rightsong.model.Song;

/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * @author Karen Santos
 * @author Jolayemioluwa Ilori
 * @version November 2014
 */
public class Server extends AbstractServer {

	private ServerController controller;

	/**
	 * Constructs an instance of the server.
	 *
	 * @param port
	 *            The port number to connect on.
	 * @param serverUI
	 *            The interface type variable.
	 */
	public Server(int port) {
		super(port);

		controller = new ServerController();
	}

	/**
	 * Returns the server controller.
	 * 
	 * @return The server controller.
	 */
	public ServerController getController() {
		return controller;
	}

	/**
	 * This method handles any messages received from the client.
	 *
	 * @param msg
	 *            The message received from the client.
	 * @param client
	 *            The connection from which the message originated.
	 */
	public void handleMessageFromClient(Object msg, ConnectionToClient client) {

		if (msg instanceof String) {

			String[] info = ((String) msg).split(" ");
			String command = info[0];

			switch (command) {
			case "#register":

				String email = info[1];
				String username = info[2];
				String password = info[3];

				String registered = controller.isRegistered(email, username);

				if (registered.equals("")) {

					controller.addUser(email, username, password);

					try {
						client.sendToClient("#register success");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if (registered.equals("email")) {
					try {
						client.sendToClient("#register invalidEmail");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					try {
						client.sendToClient("#register invalidUsername");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				break;

			case "#login":

				email = info[1];
				password = info[2];

				if (controller.isUser(email, password)) {
					try {
						client.setInfo("email", email);
						client.sendToClient("#login ok");
					} catch (IOException e) {
						e.printStackTrace();
					}

				} else {
					try {
						client.sendToClient("#login fail");
						client.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				break;

			case "#data":
				try {
					Object data = controller.getData((String) client
							.getInfo("email"));
					client.sendToClient(data);

				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
				
			case "#quit":
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			default:
				break;
			}
		} else {

			if (msg instanceof Song) {
				boolean success = controller.addSong(
						(String) client.getInfo("email"), (Song) msg);
				if (success) {
					try {
						client.sendToClient("#song added");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					try {
						client.sendToClient("#song fail");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

		}
	}

	/**
	 * This method handles all data coming from the UI
	 *
	 * @param message
	 *            The message from the UI.
	 */
	public void handleMessageFromAdmin(String message) {
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		System.out.println("Server listening for connections on port "
				+ getPort());
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * stops listening for connections.
	 */
	protected void serverStopped() {
		sendToAllClients("WARNING - The server has stopped listening for connections");
		System.out.println("Server has stopped listening for connections.");
	}

	/**
	 * This method is called each time a new client connection is accepted.
	 * 
	 * @param client
	 *            the connection connected to the client.
	 */
	@Override
	protected void clientConnected(ConnectionToClient client) {
		System.out.println("A client has connected.");

	}

	/**
	 * This method is called each time a client is disconnection from the
	 * server.
	 *
	 * @param client
	 *            the connection with the client.
	 */
	@Override
	synchronized protected void clientDisconnected(ConnectionToClient client) {
		System.out.println("A client has disconnected.");
	}

}
