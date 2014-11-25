package rightsong.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MessageFrame extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private final int MAX_CHAR = 20;
	private JLabel lblMessage;

	/**
	 * Create the frame.
	 */
	public MessageFrame(JDesktopPane frame) {
		
		getContentPane().setBackground(Color.WHITE);
		getContentPane().setLayout(null);
		
		lblMessage = new JLabel("");
		lblMessage.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setBounds(24, 18, 281, 86);
		getContentPane().add(lblMessage);
		
		JButton btnOk = new JButton("ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				dispose();
			}
		});
		btnOk.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnOk.setBounds(123, 117, 83, 29);
		getContentPane().add(btnOk);
		setBounds(100, 100, 350, 200);
		
		setVisible(true);
		frame.setVisible(true);
		frame.add(this);
	}
	
	public void setMessage(String msg){
		String message = "<html><center>";
		
		int charInLine = MAX_CHAR;
		for (int i = 0; i < msg.length(); i++){
		    message += msg.charAt(i);
		    charInLine--;
		    if(charInLine <= 0){
		    	if (i < msg.length()-2 && msg.charAt(i+1) == ' '){
		    		charInLine = MAX_CHAR;
		    		message += "<br>";
		    	}
		    }
		}
		message+= "</center></html>";
		
		lblMessage.setText(message);
	}

}
