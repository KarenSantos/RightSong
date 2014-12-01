package rightsong.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import rightsong.model.Song;

import javax.swing.JTextPane;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SongFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JLabel lblSongTitle;
	private JLabel lblSongArtist;
	private JLabel lblSongUser;
	private JLabel lblSongSpeed;
	
	private JScrollPane songLyricPane;
	private JTextPane songLyricText;
	
	private JScrollPane songChordsPane;
	private JTextPane songChordsText;
	
	private JPanel genrePanel;
	private JPanel tagsPanel;
	
	private JButton btnChords;
	private JButton btnLyrics;

	/**
	 * Create the frame.
	 */
	public SongFrame(Song song) {

		initialize();
		createEvents();
		btnLyrics.setVisible(false);
		songChordsPane.setVisible(false);
		
		showSong(song);
	}

	private void initialize() {

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 720, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblSongSpeed = new JLabel("");
		lblSongSpeed.setBounds(6, 60, 238, 23);
		contentPane.add(lblSongSpeed);

		lblSongTitle = new JLabel("");
		lblSongTitle.setBounds(0, 0, 426, 32);
		contentPane.add(lblSongTitle);
		lblSongTitle.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		lblSongTitle.setHorizontalAlignment(SwingConstants.CENTER);

		lblSongArtist = new JLabel("");
		lblSongArtist.setBounds(6, 31, 420, 23);
		lblSongArtist.setHorizontalAlignment(SwingConstants.RIGHT);
		lblSongArtist.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
		getContentPane().add(lblSongArtist);

		lblSongUser = new JLabel("");
		lblSongUser.setBounds(499, 6, 215, 23);
		lblSongUser.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		lblSongUser.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(lblSongUser);

		songLyricPane = new JScrollPane();
		songLyricPane.setBounds(6, 84, 420, 488);
		songLyricPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		getContentPane().add(songLyricPane);
		
		songLyricText = new JTextPane();
		songLyricText.setEditable(false);
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
		lblGenres.setBounds(438, 84, 276, 32);
		contentPane.add(lblGenres);
		
		JLabel lblTags = new JLabel("Tags");
		lblTags.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblTags.setHorizontalAlignment(SwingConstants.CENTER);
		lblTags.setBounds(438, 272, 276, 32);
		contentPane.add(lblTags);
		
		btnChords = new JButton("Chords");
		btnChords.setBounds(330, 58, 96, 29);
		contentPane.add(btnChords);
		
		btnLyrics = new JButton("Lyrics");
		btnLyrics.setBounds(330, 58, 96, 29);
		contentPane.add(btnLyrics);
	}
	
	private void createEvents(){
		
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
		
	}

	private void showSong(Song song) {

		lblSongTitle.setText(song.getTitle());
		if (!song.getArtists().isEmpty()) {
			String artists = "by ";
			for (int i = 0; i < song.getArtists().size(); i++) {
				if (i < song.getArtists().size() - 2) {
					artists += song.getArtists().get(i) + ", ";
				} else if (i < song.getArtists().size() - 1) {
					artists += song.getArtists().get(i) + " and ";
				} else {
					artists += song.getArtists().get(i) + ".";
				}
			}
			lblSongArtist.setText(artists);
		} else {
			lblSongArtist.setText("no artists uploaded");
		}
		lblSongUser.setText("Uploaded by " + song.getUser().getUsername());
		lblSongSpeed.setText("Speed: " + song.getSpeed().getName());
		
		String lyrics = "<html>";
		for(String line : song.getLyrics()){
			lyrics += "<p>" + line + "</p>";
		}
		lyrics += "</html>";
		songLyricText.setContentType("text/html");
		songLyricText.setText(lyrics);
		
		String chords = "<html>";
		if(song.getChordSheets().size() == 0){
			chords += "<br><br><br><center>No chords uploaded for this song.</center></html>";
		} else {
			
		}
		songChordsText.setContentType("text/html");
		songChordsText.setText(chords);
		
		JButton[] tagButtons = new JButton[song.getTags().size()];
		for (int i = 0; i < song.getTags().size(); i++) {

			tagButtons[i] = new JButton(song.getTags().get(i).getName());
			tagButtons[i].setBounds(0, 0, 10, 10);
			tagButtons[i].setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			tagsPanel.add(tagButtons[i]);
			tagsPanel.revalidate();
			tagsPanel.repaint();

		}
		
		JButton[] genreButtons = new JButton[song.getGenres().size()];
		for (int i = 0; i < song.getGenres().size(); i++) {

			genreButtons[i] = new JButton(song.getGenres().get(i)
					.getName());
			genreButtons[i].setBounds(0, 0, 10, 10);
			genreButtons[i].setFont(new Font("Lucida Grande", Font.PLAIN, 14));
			genrePanel.add(genreButtons[i]);
			genrePanel.revalidate();
			genrePanel.repaint();

		}
	}
	
	private void switchToLyrics(){
		
		btnLyrics.setVisible(false);
		songChordsPane.setVisible(false);
		
		songLyricPane.setVisible(true);
		btnChords.setVisible(true);
	}
	
	private void switchToChords(){
		btnChords.setVisible(false);
		songLyricPane.setVisible(false);
		
		songChordsPane.setVisible(true);
		btnLyrics.setVisible(true);
	}
}
