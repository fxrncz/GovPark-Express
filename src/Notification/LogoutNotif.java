package Notification;

import javax.swing.JPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout.Alignment;

import raven.glasspanepopup.GlassPanePopup;

public class LogoutNotif extends JPanel {
	
	Font kPark1;
	Font kPark2;
	Font kPark3;
	Font liv1;
	Font liv2;
	Font liv3;
	private Design_Libraries.TickBtn exit;
	private Design_Libraries.TickBtn cancel;
	
	protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        g2d.dispose();
        super.paintComponent(g);
    }

	public LogoutNotif() {
		
		try {
			kPark1 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")).deriveFont(17f);
			liv1 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-Bold.ttf")).deriveFont(19f);
			liv2 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-Medium.ttf")).deriveFont(15f);
			liv3 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-SemiBold.ttf")).deriveFont(13f);
			kPark2 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")).deriveFont(25f);
			kPark3 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")).deriveFont(15f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")));
			
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		this.setOpaque(false);
		
		setBackground(new Color(255, 255, 255));
		Design_Libraries.Container panel = new Design_Libraries.Container();
		panel.setBackground(Color.WHITE);
		panel.setRoundTopRight(15);
		panel.setRoundTopLeft(15);
		panel.setRoundBotRight(15);
		panel.setRoundBotLeft(15);
		
		Design_Libraries.Container holder = new Design_Libraries.Container();
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
		);
		
		holder.setBounds(0, 0, 407, 50);
		panel.add(holder);
		holder.setBackground(Color.BLACK);
		holder.setRoundTopRight(15);
		holder.setRoundTopLeft(15);
		holder.setLayout(null);
		
		JLabel warning = new JLabel("WARNING");
		holder.add(warning);
		warning.setBounds(144, 2, 180, 50);
		warning.setForeground(Color.WHITE);
		warning.setFont(kPark2);
		
		JLabel note = new JLabel("Are you sure you want to logout?");
		panel.add(note);
		note.setBounds(90, 50, 300, 50);
		note.setForeground(Color.BLACK);
		note.setFont(liv2);
		
		exit = new Design_Libraries.TickBtn();
		panel.add(exit);
		exit.setBounds(40, 100, 150, 40);
		exit.setColor(Color.WHITE);
		exit.setForeground(Color.BLACK);
		exit.setBorderColor(Color.BLACK);
		exit.setText("Yes");
		exit.setFocusable(false);
		exit.setFont(kPark3);
		exit.setRadius(5);
		exit.setBorderPainted(false);
		
		exit.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
				
			}
			
			public void mouseEntered(MouseEvent e) {
				exit.setColor(Color.BLACK);
				exit.setForeground(Color.WHITE);
			}
			
			public void mouseExited(MouseEvent e) {
				exit.setColor(Color.WHITE);
				exit.setForeground(Color.BLACK);
				exit.setBorderColor(Color.BLACK);
			}
			
		});
		
		cancel = new Design_Libraries.TickBtn();
		panel.add(cancel);
		cancel.setBounds(220, 100, 150, 40);
		cancel.setColor(Color.BLACK);
		cancel.setForeground(Color.WHITE);
		cancel.setText("No");
		cancel.setFocusable(false);
		cancel.setFont(kPark3);
		cancel.setRadius(5);
		cancel.setBorderPainted(false);
		
		cancel.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				GlassPanePopup.closePopupLast();
			}
			
			public void mouseEntered(MouseEvent e) {
				cancel.setColor(Color.WHITE);
				cancel.setForeground(Color.BLACK);
				cancel.setBorderColor(Color.BLACK);
			}
			
			public void mouseExited(MouseEvent e) {
				cancel.setColor(Color.BLACK);
				cancel.setForeground(Color.WHITE);
			}
			
		});
		
		panel.setLayout(null);
		setLayout(groupLayout);

	}
}
