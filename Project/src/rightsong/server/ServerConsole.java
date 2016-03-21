package rightsong.server;


/**
 * This class constructs a Server.
 *
 * @author Karen Santos
 * @author Jolayemioluwa Ilori
 * @version November 2014
 */
public class ServerConsole {

	final public static int DEFAULT_PORT = 3777;

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
			ex.printStackTrace();
			System.out.println("ERROR - Could not listen for clients!");
		}
	}
	
	/**
	 * Returns the server that was initialized.
	 * @return The server that was initialized.
	 */
	public Server getServer(){
		return server;
	}

	/**
	 * This method is responsible for the creation of the server instance.
	 *
	 * @param args
	 *            [0] The port number to listen on. Defaults to 7777 if no
	 *            argument is entered.
	 */
	public static void main(String[] args) {

		@SuppressWarnings("unused")
		ServerConsole sc = new ServerConsole(DEFAULT_PORT);

	}
}
