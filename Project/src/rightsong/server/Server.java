package rightsong.server;

import java.io.IOException;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;
import rightsong.controller.Controller;


/**
 * This class overrides some of the methods in the abstract superclass in order
 * to give more functionality to the server.
 *
 * @author Karen Santos
 * @version November 2014
 */
public class Server extends AbstractServer{
	
	private Controller controller;
	
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
		
		controller = new Controller();
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
		
		if (msg instanceof String){
			
			String[] info = ((String) msg).split(" ");
			if(info[0].equals("#register")){
				
				String email = info[1];
				String username = info[2];
				String password = info[3];
				
				String registered = controller.isRegistered(email, username);
				
				if(registered.equals("")){
					
					controller.addUser(email, username, password);
					
					try {
						client.sendToClient("#success");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else if(registered.equals("email")){
					try {
						client.sendToClient("#invalidEmail");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					try {
						client.sendToClient("#invalidUsername");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} else if(info[0].equals("#login")){
				
				String email = info[1];
				String password = info[2];
				
				if (controller.isUser(email, password)){
					try {
						client.sendToClient("#login ok");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				} else {
					try {
						client.sendToClient("#login fail");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}
		} else {
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
		System.out.println(client.getInfo("LoginID") + " has disconnected.");
	}

}
