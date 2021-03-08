package panels;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import web.Web;

public class PanelRecords extends JPanel {

	private static final long serialVersionUID = 1L;

	private String pathImage;
	
	private JLabel lblMap;
	private JLabel mainImage;
	private JLabel lblCar;
	private JLabel lblBike;
	private JLabel lblWalk;
	
	private int index;
	
	private JTextField textFrom;
	private JTextField textGoTo;
	private JButton btnCar,btnBike,btnWalk,btnBack,btnDelete,btnNext,btnNo,btnYes;
	
	public PanelRecords(JPanel contentPane, JFrame frame, Web web, boolean error) {
		
		final JPanel panel = this;
		
		setBounds(0, 0, 1280, 720);
		setLayout(null);
		
		if(error) {
			pathImage = "/images/resultError.PNG";
			
			JButton btnOk = new JButton("");
			btnOk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					contentPane.remove(panel);
					contentPane.add(new PanelEntrance(contentPane, frame));
				}
			});
			btnOk.setFocusPainted(false);
			btnOk.setContentAreaFilled(false);
			btnOk.setBorderPainted(false);
			btnOk.setBounds(564, 377, 152, 85);
			add(btnOk);
			
		}else {
			pathImage = "/images/resultCar.PNG";
			
			index = web.getRecordSize()-1;
			
			web.setDetails(index);
			
			textFrom = new JTextField(web.getDetails(6));
			textFrom.setForeground(SystemColor.windowBorder);
			textFrom.setFont(new Font("Tahoma", Font.PLAIN, 30));
			textFrom.setEditable(false);
			textFrom.setBackground(Color.white);
			textFrom.setBounds(743, 43, 476, 38);
			add(textFrom);
			textFrom.setColumns(10);
			textFrom.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			
			textGoTo = new JTextField(web.getDetails(7));
			textGoTo.setForeground(SystemColor.windowBorder);
			textGoTo.setFont(new Font("Tahoma", Font.PLAIN, 30));
			textGoTo.setEditable(false);
			textGoTo.setBackground(Color.white);
			textGoTo.setColumns(10);
			textGoTo.setBounds(688, 137, 531, 38);
			textGoTo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			add(textGoTo);
			
			lblCar = new JLabel(web.getDetails(0)+" / "+web.getDetails(1));
			lblCar.setForeground(Color.LIGHT_GRAY);
			lblCar.setFont(new Font("Tahoma", Font.BOLD, 30));
			lblCar.setBounds(199, 64, 345, 69);
			add(lblCar);
			
			lblBike = new JLabel("");
			lblBike.setForeground(Color.LIGHT_GRAY);
			lblBike.setFont(new Font("Tahoma", Font.BOLD, 30));
			lblBike.setBounds(199, 258, 345, 69);
			add(lblBike);
			
			lblWalk = new JLabel("");
			lblWalk.setForeground(Color.LIGHT_GRAY);
			lblWalk.setFont(new Font("Tahoma", Font.BOLD, 30));
			lblWalk.setBounds(199, 457, 345, 69);
			add(lblWalk);
			
			btnCar = new JButton("");
			btnCar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!pathImage.equals("/images/resultCar.PNG")) {
						pathImage = "/images/resultCar.PNG";
						refresh(web);
					}
				}
			});
			btnCar.setBounds(72, 38, 490, 130);
			btnCar.setFocusPainted(false);
			btnCar.setContentAreaFilled(false);
			btnCar.setBorderPainted(false);
			add(btnCar);
			
			btnBike = new JButton("");
			btnBike.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!pathImage.equals("/images/resultBike.PNG")) {
						pathImage = "/images/resultBike.PNG";
						refresh(web);
					}
				}
			});
			btnBike.setFocusPainted(false);
			btnBike.setContentAreaFilled(false);
			btnBike.setBorderPainted(false);
			btnBike.setBounds(72, 230, 490, 130);
			add(btnBike);
			
			btnWalk = new JButton("");
			btnWalk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!pathImage.equals("/images/resultWalk.PNG")) {
						pathImage = "/images/resultWalk.PNG";
						refresh(web);
					}
				}
			});
			btnWalk.setFocusPainted(false);
			btnWalk.setContentAreaFilled(false);
			btnWalk.setBorderPainted(false);
			btnWalk.setBounds(72, 424, 490, 130);
			add(btnWalk);
			
			btnBack = new JButton("");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					contentPane.remove(panel);
					contentPane.add(new PanelEntrance(contentPane, frame));
				}
			});
			btnBack.setFocusPainted(false);
			btnBack.setContentAreaFilled(false);
			btnBack.setBorderPainted(false);
			btnBack.setBounds(106, 578, 120, 116);
			add(btnBack);
			
			btnDelete = new JButton("");
			btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pathImage = "/images/resultDelete.PNG";
					mainImage.setIcon(new ImageIcon(PanelRecords.class.getResource(pathImage)));
					turnOn(false);
				}
			});
			btnDelete.setFocusPainted(false);
			btnDelete.setContentAreaFilled(false);
			btnDelete.setBorderPainted(false);
			btnDelete.setBounds(258, 578, 120, 116);
			add(btnDelete);
			
			btnNext = new JButton("");
			btnNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					index ++;
					if(index == web.getRecordSize()) {
						index = 0;
						web.setDetails(index);
					}else {
						web.setDetails(index);
					}
					
					refresh(web);
				}
			});
			btnNext.setFocusPainted(false);
			btnNext.setContentAreaFilled(false);
			btnNext.setBorderPainted(false);
			btnNext.setBounds(414, 578, 120, 116);
			add(btnNext);
			
			btnYes = new JButton("");
			btnYes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pathImage = "/images/resultCar.PNG";
					mainImage.setIcon(new ImageIcon(PanelRecords.class.getResource(pathImage)));
					web.delete(index);
					try {
						Runtime.getRuntime().exec("java -jar MapsClone.jar");
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					System.exit(0);
				}
			});
			btnYes.setFocusPainted(false);
			btnYes.setContentAreaFilled(false);
			btnYes.setBorderPainted(false);
			btnYes.setBounds(435, 378, 147, 74);
			btnYes.setVisible(false);
			add(btnYes);
			
			btnNo = new JButton("");
			btnNo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pathImage = "/images/resultCar.PNG";
					mainImage.setIcon(new ImageIcon(PanelRecords.class.getResource(pathImage)));
					web.setDetails(index);
					turnOn(true);
					refresh(web);
				}
			});
			btnNo.setFocusPainted(false);
			btnNo.setContentAreaFilled(false);
			btnNo.setBorderPainted(false);
			btnNo.setBounds(690, 378, 147, 74);
			btnNo.setVisible(false);
			add(btnNo);
			
			lblMap = new JLabel("",JLabel.CENTER);
			lblMap.setBounds(611, 230, 616, 464);
			lblMap.setIcon(new ImageIcon(System.getProperty("user.dir")+"/records/car/car"+index+".png"));
			add(lblMap);
		}

		mainImage = new JLabel("");
		mainImage.setIcon(new ImageIcon(PanelRecords.class.getResource(pathImage)));
		mainImage.setBounds(0, 0, 1280, 720);
		add(mainImage);
	}

	private void refresh(Web web) {
		
		textFrom.setText(web.getDetails(6));
		textGoTo.setText(web.getDetails(7));
		
		switch(pathImage) {
			case "/images/resultCar.PNG":{
				mainImage.setIcon(new ImageIcon(PanelRecords.class.getResource(pathImage)));
				lblCar.setText(web.getDetails(0)+" / "+web.getDetails(1));
				lblBike.setText("");
				lblWalk.setText("");
				lblMap.setIcon(new ImageIcon(System.getProperty("user.dir")+"/records/car/car"+index+".png"));
				break;
			}
			case "/images/resultBike.PNG":{
				mainImage.setIcon(new ImageIcon(PanelRecords.class.getResource(pathImage)));
				lblCar.setText("");
				lblBike.setText(web.getDetails(2)+" / "+web.getDetails(3));
				lblWalk.setText("");
				lblMap.setIcon(new ImageIcon(System.getProperty("user.dir")+"/records/bike/bike"+index+".png"));	
				break;
			}
			case "/images/resultWalk.PNG":{
				mainImage.setIcon(new ImageIcon(PanelRecords.class.getResource(pathImage)));
				lblCar.setText("");
				lblBike.setText("");
				lblWalk.setText(web.getDetails(4)+" / "+web.getDetails(5));
				lblMap.setIcon(new ImageIcon(System.getProperty("user.dir")+"/records/walk/walk"+index+".png"));	
				break;
			}
		}
	}
	
	private void turnOn(boolean on) {
		textFrom.setVisible(on);
		textGoTo.setVisible(on);
		lblCar.setVisible(on);
		lblBike.setVisible(on);
		btnWalk.setVisible(on);
		btnBack.setVisible(on);
		btnDelete.setVisible(on);
		btnNext.setVisible(on);
		lblMap.setVisible(on);
		btnYes.setVisible(!on);
		btnNo.setVisible(!on);
	}
	
}
