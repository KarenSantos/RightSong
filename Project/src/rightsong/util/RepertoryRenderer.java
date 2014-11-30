package rightsong.util;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import rightsong.model.Repertory;

public class RepertoryRenderer extends JLabel implements ListCellRenderer<Repertory> {

	private static final long serialVersionUID = 1L;
	final private int MAX_CHAR = 20;

	public RepertoryRenderer() {

		setOpaque(true);
		setHorizontalAlignment(LEADING);
		setVerticalAlignment(CENTER);
	}

	public Component getListCellRendererComponent(JList<? extends Repertory> list,
			Repertory repertory, int index, boolean isSelected, boolean cellHasFocus) {

		String text = "<html>&nbsp;&nbsp;<font size='4'>";
		if (repertory.getEventName().length() > MAX_CHAR){
			text += repertory.getEventName().substring(0, MAX_CHAR) + "...";
		} else {
			text += repertory.getEventName();
		}
		text +=  "</font><br>" + "<font size='2'>&nbsp;&nbsp;Date: " + repertory.getDate() + "</font></html>";

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
