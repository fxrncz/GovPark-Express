package Notification;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout.Alignment;

public class WrongCredentials extends JPanel {
	
	Image errorlogo;
	Font liv1;
	
	public void paint(Graphics g) {
		super.paint(g); 
        Graphics2D g2d = (Graphics2D) g;
        errorlogo = new ImageIcon("img/Cancel.png").getImage();
        g.drawImage(errorlogo, 18, 15, 50, 50, null);
	}
	
	protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        g2d.dispose();
        super.paintComponent(g);
    }

	public WrongCredentials() {
		
		try {
			liv1 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-Medium.ttf")).deriveFont(13f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")));
				
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		setBackground(new Color(255, 255, 255));
		this.setOpaque(false);
		
		Design_Libraries.Container holder = new Design_Libraries.Container();
		holder.setBackground(new Color(255, 255, 255));
		holder.setRoundBotLeft(15);
		holder.setRoundBotRight(15);
		holder.setRoundTopLeft(15);
		holder.setRoundTopRight(15);
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(holder, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(holder, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
		);
		holder.setLayout(null);
		
		JLabel message = new JLabel("Wrong Credentials!");
		message.setBounds(80, 25, 300, 29);
		message.setFont(liv1);
		message.setForeground(Color.RED);
		holder.add(message);
		
		setLayout(groupLayout);
	}
}
