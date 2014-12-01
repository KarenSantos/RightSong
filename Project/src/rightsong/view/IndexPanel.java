package rightsong.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import rightsong.client.ClientController;
import rightsong.model.Repertory;
import rightsong.model.Song;
import rightsong.util.RepertoryRenderer;
import rightsong.util.SongRenderer;

public class IndexPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private IndexPanel indexPanel = this;
	private MainFrame mainFrame;
	private ClientController controller;
	private MessageFrame alert;

	private JPanel searchArea;
	private JPanel menuArea;
	private JScrollPane songsPane;
	private JScrollPane mySongsPane;
	private JScrollPane myRepertoriesPane;

	private JPanel tagsPanel;
	private JPanel genrePanel;
	private JPanel speedPanel;

	private JTextField searchTextField;
	private JButton btnUploadSong;
	private JButton btnNewRepertory;
	private JButton btnSync;
	private JButton btnLogout;

	private JList<Song> songsList;
	private JList<Song> mySongsList;
	private JList<Repertory> myRepertoriesList;
	private DefaultListModel<Song> songsListModel;
	private DefaultListModel<Song> mySongsListModel;
	private DefaultListModel<Repertory> myRepertoriesListModel;

	private JLabel lblNoSongs;
	private JLabel lblNoMySongs;
	private JLabel lblNoMyRepertories;

	/**
	 * Create the panel.
	 */
	public IndexPanel(MainFrame mainFrame, ClientController controller) {
		this.mainFrame = mainFrame;
		this.controller = controller;

		initialize();
		organizeSearchArea();
		organizeMenuArea();
		createEvents();

	}

	public void setData() {

		createTagButtons();
		createGenreButtons();
		createSpeedButtons();
		createModelLists();
		createLists();
		createListsEvents();
		revalidate();
		repaint();

	}
	
	public void resetData(){
		createModelLists();
		songsList.setModel(mySongsListModel);
		songsList.revalidate();
		songsList.repaint();
		
		mySongsList.setModel(mySongsListModel);
		mySongsList.revalidate();
		mySongsList.repaint();
		
		myRepertoriesList.setModel(myRepertoriesListModel);
		myRepertoriesList.revalidate();
		myRepertoriesList.repaint();
	}

	public boolean addSong(Song song) {
		boolean answer = mainFrame.addSong(song);
		if(answer){
			if (alert == null || alert.isClosed()) {
				alert = new MessageFrame(mainFrame.getDesktopPane1());
				alert.setMessage("Your song was uploaded with success.");
				alert.requestFocusInWindow();
			}
		}
		return answer;
	}
	
	public void logout(){
		btnLogout.doClick();
	}

	private void createTagButtons() {
		JButton[] tagButtons = new JButton[controller.getTags().size()];

		for (int i = 0; i < controller.getTags().size(); i++) {

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

		for (int i = 0; i < controller.getGenres().size(); i++) {

			genreButtons[i] = new JButton(controller.getGenres().get(i)
					.getName());
			genreButtons[i].setBounds(0, 0, 10, 10);
			genreButtons[i].setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			genrePanel.add(genreButtons[i]);
			genrePanel.revalidate();
			genrePanel.repaint();

		}
	}

	private void createSpeedButtons() {
		JButton[] speedButtons = new JButton[controller.getSpeeds().size()];

		for (int i = 0; i < controller.getSpeeds().size(); i++) {

			speedButtons[i] = new JButton(controller.getSpeeds().get(i)
					.getName());
			speedButtons[i].setBounds(0, 0, 10, 10);
			speedButtons[i].setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			speedPanel.add(speedButtons[i]);
			speedPanel.revalidate();
			speedPanel.repaint();

		}
	}

	private void createModelLists() {

		songsListModel = new DefaultListModel<Song>();
		for (Song s : controller.getSongs()) {
			songsListModel.addElement(s);
		}

		mySongsListModel = new DefaultListModel<Song>();
		for (Song s : controller.getMySongs()) {
			mySongsListModel.addElement(s);
		}

		myRepertoriesListModel = new DefaultListModel<Repertory>();
		for (Repertory r : controller.getMyRepertories()) {
			myRepertoriesListModel.addElement(r);
		}

	}

	private void createLists() {

		lblNoSongs = new JLabel("There are no uploaded songs.");
		lblNoSongs.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoSongs.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		lblNoSongs.setBounds(164, 350, 228, 16);
		add(lblNoSongs);
		setVisible(false);

		lblNoMySongs = new JLabel("You have no uploaded songs.");
		lblNoMySongs.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoMySongs.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		lblNoMySongs.setBounds(404, 350, 228, 16);
		add(lblNoMySongs);
		setVisible(false);

		lblNoMyRepertories = new JLabel("You have no repertories.");
		lblNoMyRepertories.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoMyRepertories.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		lblNoMyRepertories.setBounds(643, 350, 228, 16);
		add(lblNoMyRepertories);
		setVisible(false);

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

		if (songsListModel.isEmpty()) {
			lblNoSongs.setVisible(true);
		}
		if (mySongsListModel.isEmpty()) {
			lblNoMySongs.setVisible(true);
		}
		if (myRepertoriesListModel.isEmpty()) {
			lblNoMyRepertories.setVisible(true);
		}
	}

	private void createListsEvents() {

		songsList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (!songsListModel.isEmpty()) {
					if (e.getClickCount() == 2) {
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									int songIndex = songsList
											.getSelectedIndex();
									SongFrame frame = new SongFrame(controller
											.getSongs().get(songIndex));
									frame.setVisible(true);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
				}
			}
		});

	}

	private void createEvents() {

		btnUploadSong.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							UploadSongFrame frame = new UploadSongFrame(
									indexPanel, controller);
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});

		btnNewRepertory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		btnSync.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mainFrame.getClient().setSync(true);
					mainFrame.getClient().sendToServer("data");
				} catch (IOException e1) {
					if (alert == null || alert.isClosed()) {
						alert = new MessageFrame(mainFrame.getDesktopPane1());
						alert.setMessage("Unable to connecto to the server at this time. Try again later.");
					}
				}
			}
		});

		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					mainFrame.getClient().closeConnection();
					mainFrame.switchToLoginPanel();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

	}

	private void initialize() {

		setBounds(6, 6, 888, 616);
		setBackground(Color.WHITE);
		setLayout(null);

		searchArea = new JPanel();
		searchArea.setBounds(6, 29, 876, 159);
		searchArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
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
		songsPane.setToolTipText("");
		songsPane.setBounds(164, 218, 228, 392);
		add(songsPane);
		songsPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		songsPane.setBackground(SystemColor.window);

		mySongsPane = new JScrollPane();
		mySongsPane.setBounds(404, 218, 228, 392);
		mySongsPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
				null, null));
		add(mySongsPane);

		myRepertoriesPane = new JScrollPane();
		myRepertoriesPane.setBounds(643, 218, 228, 416);
		myRepertoriesPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null,
				null, null, null));
		add(myRepertoriesPane);

		JLabel lblAllSongs = new JLabel("All Songs");
		lblAllSongs.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblAllSongs.setHorizontalAlignment(SwingConstants.CENTER);
		lblAllSongs.setBounds(164, 194, 228, 23);
		add(lblAllSongs);

		JLabel lblMySongs = new JLabel("My Songs");
		lblMySongs.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblMySongs.setHorizontalAlignment(SwingConstants.CENTER);
		lblMySongs.setBounds(404, 190, 228, 27);
		add(lblMySongs);

		JLabel lblMyRepertories = new JLabel("My Repertories");
		lblMyRepertories.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblMyRepertories.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyRepertories.setBounds(643, 190, 228, 27);
		add(lblMyRepertories);

	}

	private void organizeSearchArea() {

		JLabel iconSearch = new JLabel("");
		iconSearch.setIcon(new ImageIcon(IndexPanel.class
				.getResource("/rightsong/resources/searchIcon.png")));
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

	private void organizeMenuArea() {

		btnUploadSong = new JButton("Upload Song");
		btnUploadSong.setBounds(6, 10, 133, 33);
		btnUploadSong.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		menuArea.add(btnUploadSong);

		btnNewRepertory = new JButton("New Repertory");
		btnNewRepertory.setBounds(6, 55, 133, 33);
		btnNewRepertory.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		menuArea.add(btnNewRepertory);

		btnSync = new JButton("Sync");
		btnSync.setBounds(6, 100, 133, 33);
		btnSync.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		menuArea.add(btnSync);

		btnLogout = new JButton("Logout");
		btnLogout.setBounds(6, 377, 133, 33);
		btnLogout.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		menuArea.add(btnLogout);
	}
}
