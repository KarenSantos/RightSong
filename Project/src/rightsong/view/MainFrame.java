package rightsong.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import rightsong.client.Client;
import rightsong.client.ClientController;
import rightsong.model.Song;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	final private int DEFAULT_PORT = 3777;
	final private String DEFAULT_HOST = "localhost";
	private String host;
	private Client client;
	
	private ClientController controller;

	private JMenuItem setHostMenu;
	private JMenuItem exitMenu;

	private LoginPanel loginPanel;
	private IndexPanel indexPanel;
	private JDesktopPane desktopPane1;
	private JDesktopPane desktopPane2;

	/**
	 * Create the frame.
	 */
	public MainFrame() {

		this.host = DEFAULT_HOST;
		controller = new ClientController();

		initialize();

		createLoginPanel();
		createIndexPanel();
		createEvents();
		switchToLoginPanel();
	}
	
	public ClientController getController() {
		return controller;
	}

	public LoginPanel getLoginPanel() {
		return loginPanel;
	}

	public IndexPanel getIndexPanel() {
		return indexPanel;
	}

	public void switchToLoginPanel() {

		indexPanel.setVisible(false);

		setHostMenu.setVisible(true);
		loginPanel.clearLoginForm();
		loginPanel.setVisible(true);
	}

	public void switchToIndexPanel() {
		loginPanel.setVisible(false);
		setHostMenu.setVisible(false);

		indexPanel.setData();
		indexPanel.setVisible(true);
	}

	public String getHost(){
		return host;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public Client getClient(){
		return client;
	}
	
	public void setClient(Client client){
		this.client = client;
		controller.setClient(client);
	}
	
	public JDesktopPane getDesktopPane1(){
		return desktopPane1;
	}
	
	public JDesktopPane getDesktopPane2(){
		return desktopPane2;
	}
	
	public boolean addSong(Song song){
		boolean answer = false;
		try {
			client.sendToServer(song);
			answer = true;
		} catch (IOException e) {
			answer = false;
			e.printStackTrace();
		}
		return answer;
	}
	
	public void clearIndexPanel(){
		indexPanel.logout();
		try {
			loginPanel.reconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initialize() {
		setResizable(false);
		setTitle("RightSong");
		getContentPane().setBackground(Color.WHITE);
		setBounds(200, 100, 900, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		desktopPane2 = new JDesktopPane();
		desktopPane2.setBounds(10, 0, 894, 622);
		desktopPane2.setOpaque(false);
		getContentPane().add(desktopPane2);
		desktopPane2.setVisible(false);
		
		desktopPane1 = new JDesktopPane();
		desktopPane1.setBounds(10, 0, 894, 622);
		desktopPane1.setOpaque(false);
		getContentPane().add(desktopPane1);
		desktopPane1.setVisible(false);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 894, 22);
		getContentPane().add(menuBar);

		JMenu menu = new JMenu();
		menu.setText("File");
		menuBar.add(menu);

		setHostMenu = new JMenuItem();
		setHostMenu.setText("Set host");
		menu.add(setHostMenu);

		exitMenu = new JMenuItem();
		exitMenu.setText("Exit");
		menu.add(exitMenu);

	}

	private void createEvents() {
		setHostMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getLoginPanel().switchToSetHost();
			}
		});

		exitMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	private void createLoginPanel() {

		loginPanel = new LoginPanel(this, DEFAULT_PORT, DEFAULT_HOST);
		loginPanel.setBounds(6, 6, 888, 610);
		getContentPane().add(loginPanel);
		loginPanel.setVisible(true);
	}

	private void createIndexPanel() {

		indexPanel = new IndexPanel(this, controller);
		indexPanel.setBounds(0, 0, 888, 616);
		getContentPane().add(indexPanel);
		indexPanel.setVisible(true);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.setVisible(true);
					window.getLoginPanel().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
