package rightsong.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import rightsong.client.ClientController;
import rightsong.model.Artist;
import rightsong.model.Genre;
import rightsong.model.Song;
import rightsong.model.SongSpeed;
import rightsong.model.Tag;

public class UploadSongFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private IndexPanel indexPanel;
	private ClientController controller;
	private Song song;
	private List<String> lyrics;

	private JLabel lblTitle;
	private JLabel lblArtist;
	private JLabel lblSpeed;

	private JScrollPane songLyricPane;
	private JTextPane songLyricText;

	private JScrollPane songChordsPane;
	private JTextPane songChordsText;

	private JPanel genrePanel;
	private JPanel tagsPanel;

	private JButton btnChords;
	private JButton btnLyrics;
	private JTextField titleTextField;
	private JTextField artistTextField;

	private JButton btnSave;
	private JButton btnCancel;
	private JLabel lblError;

	private JComboBox<SongSpeed> speedComboBox;
	private JComboBox<Genre> genresComboBox;
	private JComboBox<Tag> tagsComboBox;
	
	private JDesktopPane desktopPane;
	private MessageFrame alert;

	/**
	 * Create the frame.
	 */
	public UploadSongFrame(IndexPanel indexPanel, ClientController controller) {

		this.indexPanel = indexPanel;
		this.controller = controller;
		lyrics = new ArrayList<String>();
		this.song = new Song("", "", lyrics, SongSpeed.MODERATE);

		initialize();
		createCombos();
		createEvents();
		btnLyrics.setVisible(false);
		songChordsPane.setVisible(false);

	}

	private void initialize() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 720, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBounds(getContentPane().getBounds());
		desktopPane.setOpaque(false);
		getContentPane().add(desktopPane);
		desktopPane.setVisible(false);

		lblSpeed = new JLabel("Speed:");
		lblSpeed.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSpeed.setBounds(6, 60, 51, 23);
		contentPane.add(lblSpeed);

		lblTitle = new JLabel("Title:");
		lblTitle.setBounds(0, 0, 57, 32);
		contentPane.add(lblTitle);
		lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);

		lblArtist = new JLabel("Artist:");
		lblArtist.setBounds(6, 33, 51, 23);
		lblArtist.setHorizontalAlignment(SwingConstants.RIGHT);
		lblArtist.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		getContentPane().add(lblArtist);

		songLyricPane = new JScrollPane();
		songLyricPane.setBounds(6, 84, 420, 488);
		songLyricPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(songLyricPane);

		songLyricText = new JTextPane();
		songLyricPane.setViewportView(songLyricText);

		songChordsPane = new JScrollPane();
		songChordsPane.setBounds(6, 84, 420, 488);
		songChordsPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(songChordsPane);

		songChordsText = new JTextPane();
		songChordsText.setEditable(false);
		songChordsPane.setViewportView(songChordsText);

		genrePanel = new JPanel();
		genrePanel.setBounds(438, 117, 276, 143);
		genrePanel.setBackground(Color.WHITE);
		contentPane.add(genrePanel);
		genrePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		tagsPanel = new JPanel();
		tagsPanel.setBounds(438, 304, 276, 268);
		tagsPanel.setBackground(Color.WHITE);
		contentPane.add(tagsPanel);
		tagsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblGenres = new JLabel("Genres");
		lblGenres.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblGenres.setHorizontalAlignment(SwingConstants.CENTER);
		lblGenres.setBounds(438, 84, 96, 32);
		contentPane.add(lblGenres);

		JLabel lblTags = new JLabel("Tags");
		lblTags.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblTags.setHorizontalAlignment(SwingConstants.CENTER);
		lblTags.setBounds(438, 272, 96, 32);
		contentPane.add(lblTags);

		btnChords = new JButton("Chords");
		btnChords.setBounds(330, 58, 96, 29);
		contentPane.add(btnChords);

		btnLyrics = new JButton("Lyrics");
		btnLyrics.setBounds(330, 58, 96, 29);
		contentPane.add(btnLyrics);

		btnSave = new JButton("Save");
		btnSave.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnSave.setBounds(520, 3, 100, 36);
		contentPane.add(btnSave);

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		btnCancel.setBounds(621, 3, 100, 36);
		contentPane.add(btnCancel);

		titleTextField = new JTextField();
		titleTextField.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		titleTextField.setBounds(69, 0, 357, 32);
		contentPane.add(titleTextField);
		titleTextField.setColumns(10);

		artistTextField = new JTextField();
		artistTextField.setBounds(69, 33, 357, 23);
		contentPane.add(artistTextField);
		artistTextField.setColumns(10);

	}

	private void createEvents() {

		btnChords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToChords();
			}
		});

		btnLyrics.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToLyrics();
			}
		});

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(titleTextField.getText().isEmpty()){
					lblError.setText("Insert a song title.");
				}
				else if(songLyricText.getText().isEmpty()){
					lblError.setText("Insert the lyrics of the song.");
				} else {
					if (createSong()){
						dispose();
					} else {
						if (alert == null || alert.isClosed()) {
							alert = new MessageFrame(desktopPane);
							alert.setMessage("Unable to connect to server, please try again later.");
						}
					}
				}
			}
		});

		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		genresComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				@SuppressWarnings("unchecked")
				JComboBox<Genre> comboBox = (JComboBox<Genre>) e.getSource();
				Genre selected = (Genre) comboBox.getSelectedItem();
				addGenre(selected);
			}
		});
		
		tagsComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				@SuppressWarnings("unchecked")
				JComboBox<Tag> comboBox = (JComboBox<Tag>) e.getSource();
				Tag selected = (Tag) comboBox.getSelectedItem();
				addTag(selected);
			}
		});
	}

	private void createCombos() {

		DefaultComboBoxModel<SongSpeed> speedsListModel = new DefaultComboBoxModel<SongSpeed>();
		for (SongSpeed speed : controller.getSpeeds()) {
			speedsListModel.addElement(speed);
		}

		DefaultComboBoxModel<Genre> genresListModel = new DefaultComboBoxModel<Genre>();
		for (Genre g : controller.getGenres()) {
			genresListModel.addElement(g);
		}

		DefaultComboBoxModel<Tag> tagsListModel = new DefaultComboBoxModel<Tag>();
		for (Tag t : controller.getTags()) {
			tagsListModel.addElement(t);
		}

		speedComboBox = new JComboBox<SongSpeed>();
		speedComboBox.setModel((ComboBoxModel<SongSpeed>) speedsListModel);
		speedComboBox.setBounds(69, 59, 132, 24);
		speedComboBox.setSelectedIndex(2);
		contentPane.add(speedComboBox);

		genresComboBox = new JComboBox<Genre>();
		genresComboBox.setModel(genresListModel);
		genresComboBox.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		genresComboBox.setBounds(530, 84, 184, 32);
		genresComboBox.setSelectedIndex(-1);
		contentPane.add(genresComboBox);

		tagsComboBox = new JComboBox<Tag>();
		tagsComboBox.setModel(tagsListModel);
		tagsComboBox.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		tagsComboBox.setBounds(530, 272, 184, 32);
		tagsComboBox.setSelectedIndex(-1);
		contentPane.add(tagsComboBox);
		
		lblError = new JLabel("");
		lblError.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setBounds(438, 37, 276, 23);
		lblError.setForeground(Color.RED);
		contentPane.add(lblError);

	}

	private void switchToLyrics() {

		btnLyrics.setVisible(false);
		songChordsPane.setVisible(false);

		songLyricPane.setVisible(true);
		btnChords.setVisible(true);
	}

	private void switchToChords() {
		btnChords.setVisible(false);
		songLyricPane.setVisible(false);

		songChordsPane.setVisible(true);
		btnLyrics.setVisible(true);
	}

	private void addGenre(Genre genre) {
		genrePanel.removeAll();
		song.addGenre(genre);

		JButton[] genreButtons = new JButton[song.getGenres().size()];
		for (int i = 0; i < song.getGenres().size(); i++) {

			genreButtons[i] = new JButton(song.getGenres().get(i).getName());
			genreButtons[i].setBounds(0, 0, 10, 10);
			genreButtons[i].setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			genrePanel.add(genreButtons[i]);
			genrePanel.revalidate();
			genrePanel.repaint();
		}
	}
	
	private void addTag(Tag tag) {
		tagsPanel.removeAll();
		song.addTag(tag);

		JButton[] tagButtons = new JButton[song.getTags().size()];
		for (int i = 0; i < song.getTags().size(); i++) {

			tagButtons[i] = new JButton(song.getTags().get(i).getName());
			tagButtons[i].setBounds(0, 0, 10, 10);
			tagButtons[i].setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			tagsPanel.add(tagButtons[i]);
			tagsPanel.revalidate();
			tagsPanel.repaint();
		}
	}
	
	private boolean createSong() {
		
		song.setTitle(titleTextField.getText());
		
		if(!artistTextField.getText().isEmpty()){
			song.addArtist(new Artist("id", artistTextField.getText()));
		}
		
		song.setSpeed((SongSpeed)speedComboBox.getSelectedItem());

		String[] lines = songLyricText.getText().split("\n");
		List<String> lyrics = new ArrayList<String>();
		for(int i = 0; i < lines.length; i++){
			lyrics.add(lines[i]);
		}
		song.setLyrics(lyrics);
		
		return indexPanel.addSong(song);
	}
}
