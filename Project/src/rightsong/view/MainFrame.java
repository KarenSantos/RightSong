package rightsong.view;

import java.awt.Color;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private IndexPanel indexPanel;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		initialize();
		
		createIndexPanel();
	}
	
	public IndexPanel getIndexPanel(){
		return indexPanel;
	}
	
	private void initialize(){
		setResizable(false);
		setTitle("RightSong");
		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 900, 650);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
	}
	
	private void createIndexPanel(){
		indexPanel = new IndexPanel();
		add(indexPanel);
	}
}
