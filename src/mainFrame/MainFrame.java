package mainFrame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import panels.PanelEntrance;
import java.awt.Toolkit;

public class MainFrame extends JFrame {
	
	final JFrame mainFrame = this;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public MainFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainFrame.class.getResource("/images/logo.PNG")));
		setTitle("MapsClone");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 760);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		contentPane.add(new PanelEntrance(contentPane,mainFrame));
	}

}
