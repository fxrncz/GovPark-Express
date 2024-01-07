package Notification;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout.Alignment;

public class DLSuccess extends JPanel {
	
	Image check;
	Font liv1;
	
	public void paint(Graphics g) {
		super.paint(g); 
        Graphics2D g2d = (Graphics2D) g;
        check = new ImageIcon("img/Check.png").getImage();
        g.drawImage(check, 10, 25, 50, 50, null);
	}
	
	protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        g2d.dispose();
        super.paintComponent(g);
    }

	public DLSuccess() {
		
		try {
			liv1 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-Medium.ttf")).deriveFont(13f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")));
				
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		this.setOpaque(false);
		
		Design_Libraries.Container panel = new Design_Libraries.Container();
		panel.setBackground(Color.WHITE);
		panel.setRoundBotLeft(15);
        panel.setRoundBotRight(15);
        panel.setRoundTopLeft(15);
        panel.setRoundTopRight(15);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
		);
		
		JLabel message1 = new JLabel("Your barcode was downloaded successfully");
		message1.setBounds(63, 23, 280, 50);
		message1.setForeground(new Color(0, 201, 70));
		message1.setFont(liv1);
		panel.add(message1);

		
		panel.setLayout(null);
		setLayout(groupLayout);

	}

}
