package rightsong.view;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

import rightsong.client.Client;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	final private int DEFAULT_PORT = 7777;
	final private String DEFAULT_HOST = "localhost";
	private String host;
	private Client client;

	private JMenuItem setHostMenu;
	private JMenuItem exitMenu;

	private LoginPanel loginPanel;
	private IndexPanel indexPanel;
	private SetHostFrame setHostFrame;
	private MainFrame mainFrame = this;
	private JDesktopPane desktopPane1;
	private JDesktopPane desktopPane2;

	/**
	 * Create the frame.
	 */
	public MainFrame() {

		this.host = DEFAULT_HOST;

		initialize();

		createLoginPanel();
		createIndexPanel();
		createEvents();
		switchToLoginPanel();
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
		loginPanel.setVisible(true);
	}

	public void switchToIndexPanel() {
		loginPanel.setVisible(false);
		setHostMenu.setVisible(false);

		indexPanel.setVisible(true);
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	public JDesktopPane getDesktopPane1(){
		return desktopPane1;
	}
	
	public JDesktopPane getDesktopPane2(){
		return desktopPane2;
	}

	private void initialize() {
		setResizable(false);
		setTitle("RightSong");
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 900, 650);
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
		menu.setText("file");
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

				if (setHostFrame == null || setHostFrame.isClosed()) {

					setHostFrame = new SetHostFrame(desktopPane1);
					setHostFrame.setCurrentHost(host, mainFrame);
				}
			}
		});

		exitMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

	private void createLoginPanel() {

		loginPanel = new LoginPanel();
		loginPanel.setBounds(6, 6, 888, 610);
		getContentPane().add(loginPanel);
		loginPanel.setVisible(true);
		loginPanel.setPort(DEFAULT_PORT);
		loginPanel.setHost(host);
	}

	private void createIndexPanel() {

		indexPanel = new IndexPanel();
		indexPanel.setBounds(0, 0, 888, 616);
		getContentPane().add(indexPanel);
		indexPanel.setVisible(true);
	}
}
