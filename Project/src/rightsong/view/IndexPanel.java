package rightsong.view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class IndexPanel extends JPanel {
	
	private JPanel searchArea;
	private JPanel menuArea;
	private JScrollPane listArea;
	private JPanel songArea;
	
	private JTextField searchTextField;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public IndexPanel() {
		
		initialize();
		createSearch();

	}
	
	private void initialize(){
		
		setBounds(6, 6, 888, 616);
		setBackground(Color.WHITE);
		setLayout(null);
		
		searchArea = new JPanel();
		searchArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		searchArea.setBackground(Color.WHITE);
		searchArea.setBounds(6, 29, 876, 129);
		add(searchArea);
		searchArea.setLayout(null);
		
		menuArea = new JPanel();
		menuArea.setBorder(null);
		menuArea.setBackground(Color.WHITE);
		menuArea.setBounds(6, 163, 180, 447);
		add(menuArea);
		menuArea.setLayout(null);
		
		listArea = new JScrollPane();
		listArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		listArea.setBackground(Color.WHITE);
		listArea.setBounds(194, 164, 212, 446);
		add(listArea);
		
		songArea = new JPanel();
		songArea.setBounds(414, 164, 468, 446);
		add(songArea);
	}
	
	private void createSearch(){
		
		JLabel iconSearch = new JLabel("");
		iconSearch.setIcon(new ImageIcon(IndexPanel.class.getResource("/rightsong/resources/searchIcon.png")));
		iconSearch.setBounds(6, 6, 57, 50);
		searchArea.add(iconSearch);
		
		searchTextField = new JTextField();
		searchTextField.setBounds(59, 9, 299, 34);
		searchArea.add(searchTextField);
		searchTextField.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnSearch.setBounds(358, 12, 90, 29);
		searchArea.add(btnSearch);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 438, 78);
		searchArea.add(scrollPane);
		
	}
	
	private void createMenu(){
		
	}
}
