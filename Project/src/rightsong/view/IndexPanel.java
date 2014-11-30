package rightsong.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;

import rightsong.client.ClientController;
import rightsong.model.*;
import rightsong.util.*;

import java.awt.SystemColor;

import javax.swing.JList;
import javax.swing.SwingConstants;

public class IndexPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private ClientController controller;
	
	private JPanel searchArea;
	private JPanel menuArea;
	private JPanel songArea;
	private JScrollPane songsPane;
	private JScrollPane mySongsPane;
	private JScrollPane artistsPane;
	private JScrollPane myRepertoriesPane;
	private JPanel emptyListPane;
	
	private JPanel tagsPanel;
	private JPanel genrePanel;
	private JPanel speedPanel;
	
	private JTextField searchTextField;
	
	private JButton btnAllSongs;
	private JButton btnMySongs;
	private JButton btnUploadSong;
	private JButton btnMyRepertories;
	private JButton btnNewRepertory;
	private JButton btnArtists;
	private JButton btnSync;
	private JButton btnLogout;
	
	private JList<Song> songsList;
	private JList<Song> mySongsList;
	private JList<Artist> artistsList;
	private JList<Repertory> myRepertoriesList;
	private DefaultListModel<Song> songsListModel;
	private DefaultListModel<Song> mySongsListModel;
	private DefaultListModel<Artist> artistsListModel;
	private DefaultListModel<Repertory> myRepertoriesListModel;
	private JLabel lblEmptyList;

	/**
	 * Create the panel.
	 */
	public IndexPanel(ClientController controller) {
		this.controller = controller;
		
		initialize();
		organizeSearchArea();
		organizeMenuArea();
		createEvents();

	}
	
	public void setData(){
		
		createTagButtons();
		createGenreButtons();
		createSpeedButtons();
		createModelLists();
		createLists();
		songsPane.setVisible(true);
		revalidate();
		repaint();
		
	}
	
	private void createTagButtons() {
		JButton[] tagButtons = new JButton[controller.getTags().size()];
		
		for (int i = 0; i < controller.getTags().size(); i++){
			
			tagButtons[i] = new JButton(controller.getTags().get(i).getName());
			tagButtons[i].setBounds(0, 0, 10, 10);
			tagButtons[i].setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			tagsPanel.add(tagButtons[i]);
			tagsPanel.revalidate();
			tagsPanel.repaint();
			
		}
	}
	
	private void createGenreButtons() {
		JButton[] genreButtons = new JButton[controller.getGenres().size()];
		
		for (int i = 0; i < controller.getGenres().size(); i++){
			
			genreButtons[i] = new JButton(controller.getGenres().get(i).getName());
			genreButtons[i].setBounds(0, 0, 10, 10);
			genreButtons[i].setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			genrePanel.add(genreButtons[i]);
			genrePanel.revalidate();
			genrePanel.repaint();
			
		}
	}
	
	private void createSpeedButtons() {
		JButton[] speedButtons = new JButton[controller.getSpeeds().size()];
		
		for (int i = 0; i < controller.getSpeeds().size(); i++){
			
			speedButtons[i] = new JButton(controller.getSpeeds().get(i).getName());
			speedButtons[i].setBounds(0, 0, 10, 10);
			speedButtons[i].setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			speedPanel.add(speedButtons[i]);
			speedPanel.revalidate();
			speedPanel.repaint();
			
		}
	}
	
	private void createModelLists(){
		
		songsListModel = new DefaultListModel<Song>();
		for (Song s : controller.getSongs()){
			songsListModel.addElement(s);
		}
		
		mySongsListModel = new DefaultListModel<Song>();
		for (Song s : controller.getMySongs()){
			mySongsListModel.addElement(s);
		}
		
		artistsListModel = new DefaultListModel<Artist>();
		for (Artist a : controller.getArtists()){
			artistsListModel.addElement(a);
		}
		
		myRepertoriesListModel = new DefaultListModel<Repertory>();
		for (Repertory r : controller.getMyRepertories()){
			myRepertoriesListModel.addElement(r);
		}
		
	}
	
	private void createLists(){
		
		songsList = new JList<Song>(songsListModel);
		SongRenderer songRenderer = new SongRenderer();
		songsList.setCellRenderer(songRenderer);
		songsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		songsList.setLayoutOrientation(JList.VERTICAL);
		songsList.setLayout(null);
		songsList.setSelectionBackground(Color.LIGHT_GRAY);
		songsList.setBackground(Color.WHITE);
		songsList.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		songsList.setFixedCellHeight(37);
		songsList.setFixedCellWidth(227);
		songsList.setVisibleRowCount(-1);
		songsPane.setRowHeaderView(songsList);
		
		mySongsList = new JList<Song>(mySongsListModel);
		mySongsList.setCellRenderer(songRenderer);
		mySongsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		mySongsList.setLayoutOrientation(JList.VERTICAL);
		mySongsList.setLayout(null);
		mySongsList.setSelectionBackground(Color.LIGHT_GRAY);
		mySongsList.setBackground(Color.WHITE);
		mySongsList.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		mySongsList.setFixedCellHeight(37);
		mySongsList.setFixedCellWidth(227);
		mySongsList.setVisibleRowCount(-1);
		mySongsPane.setRowHeaderView(mySongsList);
		
		artistsList = new JList<Artist>(artistsListModel);
		ArtistRenderer artistRenderer = new ArtistRenderer();
		artistsList.setCellRenderer(artistRenderer);
		artistsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		artistsList.setLayoutOrientation(JList.VERTICAL);
		artistsList.setLayout(null);
		artistsList.setSelectionBackground(Color.LIGHT_GRAY);
		artistsList.setBackground(Color.WHITE);
		artistsList.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		artistsList.setFixedCellHeight(37);
		artistsList.setFixedCellWidth(227);
		artistsList.setVisibleRowCount(-1);
		artistsPane.setRowHeaderView(artistsList);
		
		myRepertoriesList = new JList<Repertory>(myRepertoriesListModel);
		RepertoryRenderer repertoryRenderer = new RepertoryRenderer();
		myRepertoriesList.setCellRenderer(repertoryRenderer);
		myRepertoriesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		myRepertoriesList.setLayoutOrientation(JList.VERTICAL);
		myRepertoriesList.setLayout(null);
		myRepertoriesList.setSelectionBackground(Color.LIGHT_GRAY);
		myRepertoriesList.setBackground(Color.WHITE);
		myRepertoriesList.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		myRepertoriesList.setFixedCellHeight(37);
		myRepertoriesList.setFixedCellWidth(227);
		myRepertoriesList.setVisibleRowCount(-1);
		myRepertoriesPane.setRowHeaderView(myRepertoriesList);
		
	}
	
	private void createEvents(){
		btnAllSongs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(songsListModel.isEmpty()){
					lblEmptyList.setText("There are no uploaded songs.");
					switchToEmptyList();
				} else {
					switchToSongs();
				}
			}
		});
		
		btnMySongs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(mySongsListModel.isEmpty()){
					lblEmptyList.setText("You have no uploaded songs.");
					switchToEmptyList();
				} else {
					switchToMySongs();
				}
			}
		});
		
		btnUploadSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnMyRepertories.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(myRepertoriesListModel.isEmpty()){
					lblEmptyList.setText("You have no repertories.");
					switchToEmptyList();
				} else {
					switchToMyRepertories();
				}
			}
		});
		
		btnNewRepertory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnArtists.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(artistsListModel.isEmpty()){
					lblEmptyList.setText("There are no uploaded artists.");
					switchToEmptyList();
				} else {
					switchToArtists();
				}
			}
		});
		
		btnSync.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
	}
	
	private void initialize(){
		
		setBounds(6, 6, 888, 616);
		setBackground(Color.WHITE);
		setLayout(null);
		
		searchArea = new JPanel();
		searchArea.setBounds(6, 29, 876, 159);
		searchArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		searchArea.setBackground(SystemColor.window);
		add(searchArea);
		searchArea.setLayout(null);
		
		menuArea = new JPanel();
		menuArea.setBounds(6, 194, 146, 416);
		menuArea.setBorder(null);
		menuArea.setBackground(Color.WHITE);
		add(menuArea);
		menuArea.setLayout(null);
		
		songsPane = new JScrollPane();
		songsPane.setBounds(159, 193, 228, 416);
		songsPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		songsPane.setBackground(SystemColor.window);
		add(songsPane);
		
		mySongsPane = new JScrollPane();
		mySongsPane.setBounds(159, 193, 228, 416);
		mySongsPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(mySongsPane);
		
		artistsPane = new JScrollPane();
		artistsPane.setBounds(159, 193, 228, 416);
		artistsPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(artistsPane);
		
		myRepertoriesPane = new JScrollPane();
		myRepertoriesPane.setBounds(159, 193, 228, 416);
		myRepertoriesPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		add(myRepertoriesPane);
		
		emptyListPane = new JPanel();
		emptyListPane.setLayout(null);
		emptyListPane.setBounds(159, 193, 228, 416);
		emptyListPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		emptyListPane.setBackground(SystemColor.window);
		add(emptyListPane);
		
		lblEmptyList = new JLabel("");
		lblEmptyList.setBounds(0, 170, 228, 20);
		lblEmptyList.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmptyList.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		emptyListPane.add(lblEmptyList);
		
		songArea = new JPanel();
		songArea.setBounds(395, 194, 487, 416);
		add(songArea);
		
	}
	
	private void organizeSearchArea(){
		
		JLabel iconSearch = new JLabel("");
		iconSearch.setIcon(new ImageIcon(IndexPanel.class.getResource("/rightsong/resources/searchIcon.png")));
		iconSearch.setBounds(6, 0, 57, 50);
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
	
	private void organizeMenuArea(){
		
		btnAllSongs = new JButton("All Songs");
		btnAllSongs.setBounds(6, 6, 133, 33);
		btnAllSongs.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		menuArea.add(btnAllSongs);
		
		btnMySongs = new JButton("My Songs");
		btnMySongs.setBounds(6, 47, 133, 33);
		btnMySongs.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		menuArea.add(btnMySongs);
		
		btnUploadSong = new JButton("Upload Song");
		btnUploadSong.setBounds(6, 88, 133, 33);
		btnUploadSong.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		menuArea.add(btnUploadSong);
		
		btnMyRepertories = new JButton("My Repertories");
		btnMyRepertories.setBounds(6, 129, 133, 33);
		btnMyRepertories.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		menuArea.add(btnMyRepertories);
		
		btnNewRepertory = new JButton("New Repertory");
		btnNewRepertory.setBounds(6, 170, 133, 33);
		btnNewRepertory.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		menuArea.add(btnNewRepertory);
		
		btnArtists = new JButton("Artists");
		btnArtists.setBounds(6, 211, 133, 33);
		btnArtists.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		menuArea.add(btnArtists);
		
		btnSync = new JButton("Sync");
		btnSync.setBounds(6, 252, 133, 33);
		btnSync.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		menuArea.add(btnSync);
		
		btnLogout = new JButton("Logout");
		btnLogout.setBounds(6, 293, 133, 33);
		btnLogout.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		menuArea.add(btnLogout);
	}
	
	private void switchToSongs(){
		mySongsPane.setVisible(false);
		artistsPane.setVisible(false);
		myRepertoriesPane.setVisible(false);
		emptyListPane.setVisible(false);
		
		songsPane.setVisible(true);
	}
	
	private void switchToMySongs(){
		songsPane.setVisible(false);
		artistsPane.setVisible(false);
		myRepertoriesPane.setVisible(false);
		emptyListPane.setVisible(false);
		
		mySongsPane.setVisible(true);
	}
	
	private void switchToArtists(){
		songsPane.setVisible(false);
		mySongsPane.setVisible(false);
		myRepertoriesPane.setVisible(false);
		emptyListPane.setVisible(false);
		
		artistsPane.setVisible(true);
	}
	
	private void switchToMyRepertories(){
		songsPane.setVisible(false);
		mySongsPane.setVisible(false);
		artistsPane.setVisible(false);
		emptyListPane.setVisible(false);
		
		myRepertoriesPane.setVisible(true);
	}
	
	private void switchToEmptyList(){
		songsPane.setVisible(false);
		mySongsPane.setVisible(false);
		artistsPane.setVisible(false);
		myRepertoriesPane.setVisible(false);
		
		emptyListPane.setVisible(true);
	}
}
