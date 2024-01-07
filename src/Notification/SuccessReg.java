package Notification;

import javax.swing.JPanel;

import raven.glasspanepopup.GlassPanePopup;

import java.awt.*;
import java.awt.geom.*;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;
import javax.swing.*;

public class SuccessReg extends JPanel {
	
	Font kPark1;
	Font liv1;
	
	protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        g2d.dispose();
        super.paintComponent(g);
    }

	public SuccessReg() {
		initComponents();
		setOpaque(false);
	}
	
	
	private void initComponents() {
		
		try {
			kPark1 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")).deriveFont(35f);
			liv1 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-Medium.ttf")).deriveFont(14f);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}

        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        jButton1 = new Design_Libraries.Button();
        
        setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Congrats!");
        jLabel1.setFont(kPark1);
        
        jLabel2.setText("Your have been successfully registered!");
        jLabel2.setForeground(new Color(48, 48, 48));
        jLabel2.setFont(liv1);

        jButton1.setText("Continue to Login");
        jButton1.setColor(Color.BLACK);
        jButton1.setForeground(Color.WHITE);
        jButton1.setFocusable(false);
        jButton1.setFont(liv1);
        jButton1.setRadius(5);
        jButton1.setBorderPainted(false);
        
        jButton1.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		project.LoginPage obj = new project.LoginPage();
        		obj.setVisible(true);
        		
        	}
        });
        
        jButton1.addMouseListener(new MouseAdapter() {
        	
        	public void mouseEntered(MouseEvent e) {
        		jButton1.setColor(Color.WHITE);
        		jButton1.setForeground(Color.BLACK);
        		jButton1.setBorderColor(Color.BLACK);
        	}
        	
        	public void mouseExited(MouseEvent e) {
        		jButton1.setColor(Color.BLACK);
        		jButton1.setForeground(Color.WHITE);
        	}
        	
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        
    }
	
	
	
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private Design_Libraries.Button jButton1;

}
