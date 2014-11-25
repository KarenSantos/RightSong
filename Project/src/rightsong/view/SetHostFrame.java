package rightsong.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class SetHostFrame extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	private JTextField hostTextField;
	private JLabel lblShowCurrentHost;
	private MainFrame mainFrame;

	private String host;
	private MessageFrame alert;

	/**
	 * Create the frame.
	 */
	public SetHostFrame(JDesktopPane frm) {
		
		JDesktopPane frame = frm;
		
		getContentPane().setBackground(Color.WHITE);
		setTitle("Set Host");
		setBounds(100, 100, 400, 200);
		getContentPane().setLayout(null);

		JLabel lblCurrentHost = new JLabel("Current Host:");
		lblCurrentHost.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblCurrentHost.setBounds(26, 19, 98, 22);
		getContentPane().add(lblCurrentHost);

		JLabel lblSetTo = new JLabel("Set Host to:");
		lblSetTo.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblSetTo.setBounds(26, 53, 98, 27);
		getContentPane().add(lblSetTo);

		lblShowCurrentHost = new JLabel("");
		lblShowCurrentHost.setBounds(126, 20, 228, 22);
		getContentPane().add(lblShowCurrentHost);

		hostTextField = new JTextField();
		hostTextField.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		hostTextField.setBounds(113, 53, 241, 27);
		getContentPane().add(hostTextField);
		hostTextField.setColumns(10);

		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				host = hostTextField.getText();
				if (host.equals("")) {
					if (alert == null || alert.isClosed()) {

						alert = new MessageFrame(mainFrame.getDesktopPane2());
						alert.setMessage("Please enter a valid host name.");
					}
				} else {
					if (alert == null || alert.isClosed()) {
						host = hostTextField.getText();
						mainFrame.setHost(host);
						alert = new MessageFrame(mainFrame.getDesktopPane2());
						alert.setMessage("New host set with success. Current host: " + host);
						frame.setVisible(false);
						dispose();
					}
				}
			}
		});
		btnOk.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnOk.setBounds(164, 105, 98, 27);
		getContentPane().add(btnOk);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				dispose();
			}
		});
		btnCancel.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnCancel.setBounds(262, 105, 91, 27);
		getContentPane().add(btnCancel);
		
		setVisible(true);
		frame.add(this);
		frame.setVisible(true);
	}
	
	public String getHost(){
		return host;
	}
	
	public void setCurrentHost(String host, MainFrame frame){
		this.mainFrame = frame;
		this.host = host;
		lblShowCurrentHost.setText(host);
	}
	
}
