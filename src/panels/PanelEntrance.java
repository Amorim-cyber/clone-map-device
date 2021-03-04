package panels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import web.Web;


public class PanelEntrance extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField textFrom;
	private JTextField textGoTo;
	
	private JLabel mainImage;
	
	private String pathImage = "/images/main.PNG";
	
	private JButton btnOk;
	private JButton btnSend;
	private JButton btnRecords;
	
	
	public PanelEntrance(JPanel contentPane,JFrame frame) {
		
		final JPanel panel = this;

		setBounds(0, 0, 1280, 720);
		setLayout(null);
		
		textFrom = new JTextField();
		textFrom.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textFrom.setBounds(709, 106, 456, 38);
		add(textFrom);
		textFrom.setColumns(10);
		textFrom.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		textGoTo = new JTextField();
		textGoTo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		textGoTo.setColumns(10);
		textGoTo.setBounds(709, 180, 456, 38);
		textGoTo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER){

					launch(contentPane, panel,frame);
					
		        }
			}
		});
		textGoTo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		add(textGoTo);
		
		btnSend = new JButton("");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				launch(contentPane, panel,frame);
				
			}
		});
		btnSend.setBounds(971, 247, 174, 62);
		btnSend.setFocusPainted(false);
		btnSend.setContentAreaFilled(false);
		btnSend.setBorderPainted(false);
		add(btnSend);
		
		btnRecords = new JButton("");
		btnRecords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Web web = new Web();
				if(web.getRecordSize()==0) {
					pathImage = "/images/mainNone.PNG";
					mainImage.setIcon(new ImageIcon(PanelEntrance.class.getResource(pathImage)));
					textFrom.setVisible(false);
					textGoTo.setVisible(false);
					btnSend.setVisible(false);
					btnRecords.setVisible(false);
					btnOk.setVisible(true);
				}else {
					setVisible(false);
					contentPane.remove(panel);
					contentPane.add(new PanelRecords(contentPane, frame, web, false));
				}
			}
		});
		btnRecords.setBounds(37, 629, 213, 62);
		btnRecords.setFocusPainted(false);
		btnRecords.setContentAreaFilled(false);
		btnRecords.setBorderPainted(false);
		add(btnRecords);
		
		btnOk = new JButton("");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pathImage = "/images/main.PNG";
				mainImage.setIcon(new ImageIcon(PanelEntrance.class.getResource(pathImage)));
				textFrom.setVisible(true);
				textGoTo.setVisible(true);
				btnSend.setVisible(true);
				btnRecords.setVisible(true);
				btnOk.setVisible(false);
			}
		});
		btnOk.setBounds(540, 393, 174, 62);
		btnOk.setFocusPainted(false);
		btnOk.setContentAreaFilled(false);
		btnOk.setBorderPainted(false);
		btnOk.setVisible(false);
		add(btnOk);
		
		mainImage = new JLabel("");
		mainImage.setIcon(new ImageIcon(PanelEntrance.class.getResource(pathImage)));
		mainImage.setBounds(0, 0, 1280, 720);
		add(mainImage);
		
	}
	
	private void launch(JPanel contentPane, JPanel panel,JFrame frame) {
		
		boolean fromInvalid = textFrom.getText().equals("");
		boolean goToInvalid = textGoTo.getText().equals("");
		
		if(fromInvalid || goToInvalid) {
			pathImage = "/images/mainError.PNG";
			mainImage.setIcon(new ImageIcon(PanelEntrance.class.getResource(pathImage)));
			textFrom.setVisible(false);
			textGoTo.setVisible(false);
			btnSend.setVisible(false);
			btnRecords.setVisible(false);
			btnOk.setVisible(true);
		}else {
			setVisible(false);
			contentPane.remove(panel);
			frame.setVisible(false);
			Web web = new Web();
			if(web.start(textFrom.getText(), textGoTo.getText(), web.getRecordSize())) 
				contentPane.add(new PanelRecords(contentPane, frame, web, false));
			else 
				contentPane.add(new PanelRecords(contentPane, frame, web, true));
			
			frame.setVisible(true);
			
		}
	}

}
