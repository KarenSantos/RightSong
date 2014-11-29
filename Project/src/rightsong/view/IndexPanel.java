package rightsong.view;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;

import rightsong.model.Genre;
import rightsong.model.SongSpeed;
import rightsong.model.Tag;

import java.awt.Font;
import java.awt.FlowLayout;
import java.util.List;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class IndexPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

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
	private JButton btnMySongs;
	private JButton btnUploadSong;
	private JButton btnRepertories;
	private JButton btnNewRepertory;
	private JButton btnArtists;
	private JButton btnLogout;

	/**
	 * Create the panel.
	 */
	public IndexPanel() {
		
		initialize();
		createSearch();

	}
	
	public void setData(List<List> data){
		this.data = data;
		
		createTagButtons(data.get(3));
		createGenreButtons(data.get(4));
		createSpeedButtons(data.get(5));
		
	}

	private void createTagButtons(List<Tag> tags) {
		JButton[] tagButtons = new JButton[tags.size()];
		
		for (int i = 0; i < tags.size(); i++){
			
			tagButtons[i] = new JButton(tags.get(i).getName());
			tagButtons[i].setBounds(0, 0, 10, 10);
			tagButtons[i].setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			tagsPanel.add(tagButtons[i]);
			tagsPanel.revalidate();
			tagsPanel.repaint();
			
		}
	}
	
	private void createGenreButtons(List<Genre> genres) {
		JButton[] genreButtons = new JButton[genres.size()];
		
		for (int i = 0; i < genres.size(); i++){
			
			genreButtons[i] = new JButton(genres.get(i).getName());
			genreButtons[i].setBounds(0, 0, 10, 10);
			genreButtons[i].setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			genrePanel.add(genreButtons[i]);
			genrePanel.revalidate();
			genrePanel.repaint();
			
		}
	}
	
	private void createSpeedButtons(List<SongSpeed> speeds) {
		JButton[] speedButtons = new JButton[speeds.size()];
		
		for (int i = 0; i < speeds.size(); i++){
			
			speedButtons[i] = new JButton(speeds.get(i).getName());
			speedButtons[i].setBounds(0, 0, 10, 10);
			speedButtons[i].setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			speedPanel.add(speedButtons[i]);
			speedPanel.revalidate();
			speedPanel.repaint();
			
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
		
		btnMySongs = new JButton("My Songs");
		
		btnUploadSong = new JButton("Upload Song");
		
		btnRepertories = new JButton("Repertories");
		
		btnNewRepertory = new JButton("New Repertory");
		
		btnArtists = new JButton("Artists");
		
		btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		GroupLayout gl_menuArea = new GroupLayout(menuArea);
		gl_menuArea.setHorizontalGroup(
			gl_menuArea.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuArea.createSequentialGroup()
					.addGroup(gl_menuArea.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_menuArea.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnNewRepertory, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
						.addGroup(gl_menuArea.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnUploadSong, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
						.addGroup(gl_menuArea.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnMySongs, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
						.addGroup(gl_menuArea.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnRepertories, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
						.addGroup(gl_menuArea.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnArtists, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE))
						.addComponent(btnLogout, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_menuArea.setVerticalGroup(
			gl_menuArea.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_menuArea.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnMySongs, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnUploadSong, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnRepertories, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnNewRepertory, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnArtists, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLogout)
					.addGap(210))
		);
		menuArea.setLayout(gl_menuArea);
		
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
