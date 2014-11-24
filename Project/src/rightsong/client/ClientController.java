package rightsong.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * This class constructs the UI for a chat client. It implements the chat
 * interface in order to activate the display() method. Warning: Some of the
 * code here is cloned in ServerConsole
 *
 * @author Fran&ccedil;ois B&eacute;langer
 * @author Dr Timothy C. Lethbridge
 * @author Dr Robert Lagani&egrave;re
 * @version July 2000
 */
public class ClientController {

	/**
	 * The default port to connect on.
	 */
	final public static int DEFAULT_PORT = 5555;

	/**
	 * The instance of the client that created this ConsoleChat.
	 */
	Client client;

	/**
	 * Constructs an instance of the ClientConsole UI.
	 *
	 * @param host
	 *            The host to connect to.
	 * @param port
	 *            The port to connect on.
	 * @param loginID
	 *            The login ID of the client.
	 */
	public ClientController(String login, String password, String host, int port) {
//		client = new Client(login, host, port);
	}

	/**
	 * This method waits for input from the console. Once it is received, it
	 * sends it to the client's message handler.
	 */
	public void accept() {
		try {
			BufferedReader fromConsole = new BufferedReader(
					new InputStreamReader(System.in));
			String message;

			while (true) {
				message = fromConsole.readLine();
				client.handleMessageFromClientUI(message);
			}
		} catch (Exception ex) {
			System.out.println("Unexpected error while reading from console!");
		}
	}


	/**
	 * This method is responsible for the creation of the Client UI.
	 *
	 * @param args
	 *            [0] The host to connect to.
	 */
	public static void main(String[] args) {
		
		
		String loginID = "";
		String host = "";
		int port = 0; // The port number

		// **** Changed for E51' - JI and KS
		try {
			loginID = args[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out
					.println("A login ID is required. System is terminating.");
			System.exit(0);
		}

		try {
			host = args[1];
		} catch (ArrayIndexOutOfBoundsException e) {
			host = "localhost";
		}

		try {
			port = Integer.parseInt(args[2]);
		} catch (Exception e) {
			port = DEFAULT_PORT;
		}

//		ClientController clientController = new ClientController(login, password, host, port);
//		clientController.accept(); // Wait for console data

	}
	
}
