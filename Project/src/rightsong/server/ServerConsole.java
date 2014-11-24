package rightsong.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * This class constructs the UI for a Server.
 *
 * @author Karen Santos
 * @version November 2014
 */
public class ServerConsole {

	final public static int DEFAULT_PORT = 7777;

	private Server server;

	/**
	 * Constructs an instance of the ServerConsole UI.
	 *
	 * @param port
	 *            The port to connect to.
	 */
	public ServerConsole(int port) {
		server = new Server(port);

		try {
			server.listen(); 
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!");
		}
	}

	/**
	 * This method waits for input from the console. Once it is received, it
	 * sends it to the server's message handler.
	 */
	public void accept() {
		try {
			BufferedReader fromConsole = new BufferedReader(
					new InputStreamReader(System.in));
			String message;

			while (true) {
				message = fromConsole.readLine();
//				server.handleMessageFromServerUI(message);
			}
		} catch (Exception ex) {
			System.out
					.println("Unexpected error while reading from console in the server!");
		}
	}

	/**
	 * This method is responsible for the creation of the server instance.
	 *
	 * @param args
	 *            [0] The port number to listen on. Defaults to 7777 if no
	 *            argument is entered.
	 */
	public static void main(String[] args) {
		int port = 0; // Port to listen on

		try {
			port = Integer.parseInt(args[0]); // Get port from command line
		} catch (Throwable t) {
			port = DEFAULT_PORT; // Set port to 7777
		}

		ServerConsole sc = new ServerConsole(port);
		sc.accept(); // Wait for console data

	}
}
