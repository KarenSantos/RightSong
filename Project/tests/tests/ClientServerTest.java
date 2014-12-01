package tests;

import java.awt.EventQueue;
import java.io.IOException;

import rightsong.client.Client;
import rightsong.server.ServerConsole;
import rightsong.view.MainFrame;

public class ClientServerTest {

	final private static int PORT = 7779;

	private static ServerConsole serverConsole;
	private static Client client;
	private static Client client2;
	private static MainFrame frame;
	private static MainFrame frame2;

	public static void main(String[] args) {

		serverConsole = new ServerConsole(PORT);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainFrame();
					frame.setVisible(true);
					frame.getLoginPanel().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		try {
			client = new Client(frame, "#login admin admin", "localhost", PORT);
		} catch (IOException e) {
			System.out.println("Could not login as admin.");
			e.getStackTrace();
		}

		// Server has 2 clients, admin and the sample database client.
		System.out.println("Total clients: "
				+ serverConsole.getServer().getController().getUsers().size());

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame2 = new MainFrame();
					frame2.setVisible(true);
					frame2.getLoginPanel().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		//registering a new user
		try {
			client2 = new Client(frame2, "#register email username pass",
					"localhost", PORT);
			System.out.println("Client2 registered.");
		} catch (IOException e) {
			System.out.println("Could not register client2.");
		}

	}

}
