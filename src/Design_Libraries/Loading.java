package Design_Libraries;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

public class Loading extends JFrame {

	private JPanel contentPane;
	public static JLabel loading;
	public static JProgressBar progressBar;
	Font liv1;
	Font liv2;
	Font kPark1;

	public Loading() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1440, 800);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setResizable(false);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.setLayout(null);
		
		initComponents();
	}
	
	public void initComponents() {
		
		try {
			liv1 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-Bold.ttf")).deriveFont(70f);
			liv2 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-Bold.ttf")).deriveFont(80f);
			kPark1 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")).deriveFont(20f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")));
			
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		JLabel title = new JLabel("Welcome to");
		JLabel title2 = new JLabel("GovPark Express");
		loading = new JLabel("0%");
		
		contentPane.add(title);
		title.setBounds(500, 200, 600, 100);
		title.setFont(liv1);
		title.setForeground(Color.BLACK);
		
		contentPane.add(title2);
		title2.setBounds(380, 300, 650, 100);
		title2.setFont(liv2);
		title2.setForeground(Color.BLACK);
		
		contentPane.add(loading);
		loading.setBounds(690, 739, 200, 50);
		loading.setFont(kPark1);
		loading.setForeground(new Color(99, 99, 99));
		
		progressBar = new JProgressBar();
		progressBar.setBackground(new Color(255, 255, 255));
		progressBar.setForeground(new Color(0, 0, 0));
		progressBar.setBounds(0, 779, 1440, 21);
		contentPane.add(progressBar);
		
	}
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loading frame = new Loading();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
