package rightsong.view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;

import rightsong.model.Tag;

import java.awt.Font;
import java.awt.FlowLayout;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class IndexPanel extends JPanel {
	
	private List<List> data;
	
	private JPanel searchArea;
	private JPanel menuArea;
	private JScrollPane listArea;
	private JPanel songArea;
	
	private JPanel tagsPanel;
	private JPanel genrePanel;
	private JPanel speedPanel;
	
	private JTextField searchTextField;
	private JLabel lblTest;

	/**
	 * Create the panel.
	 */
	public IndexPanel() {
		
		initialize();
		createSearch();

	}
	
	public void setData(List<List> data){
		this.data = data;
		
		lblTest.setText(data.get(3).size() + "");
		List<Tag> tags = data.get(3);
		JButton[] tagButtons = new JButton[tags.size()];
		
		JButton btn = new JButton("button");
		btn.setBounds(0, 0, 20, 20);
		btn.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		tagsPanel.add(btn);
		btn.setVisible(true);
		
		
		for (int i = 0; i < tags.size(); i++){
			
			tagButtons[i] = new JButton(tags.get(i).getName());
			tagButtons[i].setBounds(0, 0, 10, 10);
			tagButtons[i].setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			tagsPanel.add(tagButtons[i]);
			
		}
		
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
		
		lblTest = new JLabel("");
		lblTest.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		songArea.add(lblTest);
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
		
		tagsPanel = new JPanel();
		tagsPanel.setBackground(Color.WHITE);
		tagsPanel.setBounds(6, 50, 399, 73);
		searchArea.add(tagsPanel);
		tagsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		genrePanel = new JPanel();
		genrePanel.setBackground(Color.WHITE);
		genrePanel.setBounds(412, 50, 253, 73);
		searchArea.add(genrePanel);
		
		speedPanel = new JPanel();
		speedPanel.setBackground(Color.WHITE);
		speedPanel.setBounds(670, 50, 200, 73);
		searchArea.add(speedPanel);
		
	}
	
	private void createMenu(){
		
	}
}
