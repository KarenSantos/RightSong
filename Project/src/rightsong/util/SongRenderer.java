package rightsong.util;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import rightsong.model.Song;

/**
 * Song Renderer to display songs in a list.
 * 
 * @author karensantos
 * @author Jolayemioluwa Ilori
 *
 */
public class SongRenderer extends JLabel implements ListCellRenderer<Song> {

	private static final long serialVersionUID = 1L;
	final private int MAX_CHAR = 20;

	public SongRenderer() {

		setOpaque(true);
		setHorizontalAlignment(LEADING);
		setVerticalAlignment(CENTER);
	}

	public Component getListCellRendererComponent(JList<? extends Song> list,
			Song song, int index, boolean isSelected, boolean cellHasFocus) {

		String text = "<html>&nbsp;&nbsp;<font size='4'>";
		if (song.getTitle().length() > MAX_CHAR) {
			text += song.getTitle().substring(0, MAX_CHAR) + "...";
		} else {
			text += song.getTitle();
		}
		text += "</font><br>" + "<font size='2'>&nbsp;&nbsp;from "
				+ song.getUser() + "</font></html>";

		setText(text);
		setFont(list.getFont());

		if (isSelected) {
			setBackground(list.getSelectionBackground());
			setForeground(list.getSelectionForeground());
		} else {
			setBackground(list.getBackground());
			setForeground(list.getForeground());
		}

		return this;
	}
}
