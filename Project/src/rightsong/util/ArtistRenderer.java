package rightsong.util;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import rightsong.model.Artist;

public class ArtistRenderer extends JLabel implements ListCellRenderer<Artist> {

	private static final long serialVersionUID = 1L;
	final private int MAX_CHAR = 20;

	public ArtistRenderer() {

		setOpaque(true);
		setHorizontalAlignment(LEADING);
		setVerticalAlignment(CENTER);
	}

	public Component getListCellRendererComponent(JList<? extends Artist> list,
			Artist artist, int index, boolean isSelected, boolean cellHasFocus) {

		String text = "<html>&nbsp;&nbsp;<font size='4'>";
		if (artist.getName().length() > MAX_CHAR){
			text += artist.getName().substring(0, MAX_CHAR) + "...";
		} else {
			text += artist.getName();
		}

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
