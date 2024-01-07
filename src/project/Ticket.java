package project;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileSystemView;

import raven.glasspanepopup.GlassPanePopup;
import com.barcodelib.barcode.Linear;

import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.*;

public class Ticket extends javax.swing.JPanel {
	
	private JLabel name;
	private JLabel location;
	private JLabel lblloc;
	private JLabel vtype;
	private JLabel lbltype;
	private JLabel from;
	private JLabel lblfrom;
	private JLabel until;
	private JLabel lbluntil;
	private Design_Libraries.Button dlbtn;
	private Design_Libraries.TickBtn cnclbtn;
	Font kPark1;
	Font liv1;
	Font liv2;
	Font liv3;
	Font kPark2;
	Image logo;

	public void paint(Graphics g) {
		super.paint(g); 
        Graphics2D g2d = (Graphics2D) g;
        logo = new ImageIcon("img/Icon.png").getImage();
        g.drawImage(logo, 13, 50, 128, 128, null);
	}
	
	protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 15, 15));
        g2d.dispose();
        super.paintComponent(g);
    }
	
    public Ticket(String input1, String input2, String input3, String input4, String input5, String input6) {
        setOpaque(false);
        try {
			kPark1 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")).deriveFont(17f);
			liv1 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-Bold.ttf")).deriveFont(19f);
			liv2 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-Medium.ttf")).deriveFont(15f);
			liv3 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-SemiBold.ttf")).deriveFont(15f);
			kPark2 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")).deriveFont(13f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")));
			
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}

        setBackground(new java.awt.Color(255, 255, 255));
        
        Design_Libraries.Container panel = new Design_Libraries.Container();
        panel.setBackground(new Color(0, 0, 0));
        panel.setRoundBotLeft(0);
        panel.setRoundBotRight(0);
        panel.setRoundTopLeft(15);
        panel.setRoundTopRight(15);
        
        Design_Libraries.Container body = new Design_Libraries.Container();
        body.setBackground(new Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        		.addComponent(body, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addComponent(panel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addComponent(body, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap())
        );
        
        dlbtn = new Design_Libraries.Button();
        dlbtn.setBounds(17, 127, 190, 35);
        dlbtn.setColor(Color.BLACK);
        dlbtn.setForeground(Color.WHITE);
        dlbtn.setText("Download Barcode");
        dlbtn.setFocusable(false);
        dlbtn.setFont(kPark2);
        dlbtn.setRadius(5);
        dlbtn.setBorderPainted(false);
        body.add(dlbtn);
        
        cnclbtn = new Design_Libraries.TickBtn();
        cnclbtn.setBounds(220, 127, 190, 35);
        cnclbtn.setColor(Color.WHITE);
        cnclbtn.setForeground(Color.BLACK);
        cnclbtn.setBorderColor(Color.BLACK);
        cnclbtn.setText("Cancel");
        cnclbtn.setFocusable(false);
        cnclbtn.setFont(kPark2);
        cnclbtn.setRadius(5);
        cnclbtn.setBorderPainted(false);
        body.add(cnclbtn);
        
        dlbtn.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		try {

        			JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        			fileChooser.setDialogTitle("Select Download Folder");
        	        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        	        int returnValue = fileChooser.showDialog(null, "Select");
        			
					Linear barcode = new Linear();
					String name = input1;
					String platenumber = input2;
					barcode.setType(Linear.CODE128B);
					barcode.setData(platenumber);
					barcode.setI(11.0f);
					
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						String downloadPath = fileChooser.getSelectedFile().getPath();
						barcode.renderBarcode(downloadPath + name + ".png");
						GlassPanePopup.showPopup(new Notification.DLSuccess());
					} else {
						JOptionPane.showMessageDialog(null, "Please select location.", "Message", JOptionPane.CLOSED_OPTION);
					}
					
				} catch(Exception ex) {
					
				}
        	}
        	
        });
        
        dlbtn.addMouseListener(new MouseAdapter() {
        	
        	public void mouseEntered(MouseEvent e) {
        		dlbtn.setForeground(Color.BLACK);
        		dlbtn.setColor(Color.WHITE);
        		dlbtn.setBorderColor(Color.BLACK);
        	}
        	
        	public void mouseExited(MouseEvent e) {
        		dlbtn.setForeground(Color.WHITE);
        		dlbtn.setColor(Color.BLACK);
        	}
        	
        });
        
        cnclbtn.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		GlassPanePopup.closePopupLast();
        	}
        	
        });
        
        cnclbtn.addMouseListener(new MouseAdapter() {
        	
        	public void mouseEntered(MouseEvent e) {
        		cnclbtn.setForeground(Color.WHITE);
        		cnclbtn.setColor(Color.BLACK);
        		
        	}
        	
        	public void mouseExited(MouseEvent e) {
        		cnclbtn.setForeground(Color.BLACK);
        		cnclbtn.setColor(Color.WHITE);
        		cnclbtn.setBorderColor(Color.BLACK);
        	}
        	
        });
        
        name = new JLabel("LAST NAME");
        name.setBounds(144, -5, 300, 50);
        name.setFont(liv1);
        name.setForeground(Color.BLACK);
        body.add(name);
        
        location = new JLabel("Location:");
        location.setBounds(144, 18, 100, 50);
        location.setFont(liv2);
        location.setForeground(new Color(108, 108, 108));
        body.add(location);
        
        lblloc = new JLabel("None");
        lblloc.setBounds(212, 18, 300, 50);
        lblloc.setFont(liv3);
        lblloc.setForeground(new Color(72, 72, 72));
        body.add(lblloc);
        
        vtype = new JLabel("Vehicle Type:");
        vtype.setBounds(144, 38, 100, 50);
        vtype.setFont(liv2);
        vtype.setForeground(new Color(108, 108, 108));
        body.add(vtype);
        
        lbltype = new JLabel("4 - Wheel Drive");
        lbltype.setBounds(237, 38, 150, 50);
        lbltype.setFont(liv3);
        lbltype.setForeground(new Color(72, 72, 72));
        body.add(lbltype);
        
        from = new JLabel("From:");
        from.setBounds(144, 58, 100, 50);
        from.setFont(liv2);
        from.setForeground(new Color(108, 108, 108));
        body.add(from);
        
        lblfrom = new JLabel("N / A");
        lblfrom.setBounds(186, 58, 200, 50);
        lblfrom.setFont(liv3);
        lblfrom.setForeground(new Color(72, 72, 72));
        body.add(lblfrom);
        
        until = new JLabel("Until:");
        until.setBounds(144, 78, 100, 50);
        until.setFont(liv2);
        until.setForeground(new Color(108, 108, 108));
        body.add(until);
        
        lbluntil = new JLabel("N / A");
        lbluntil.setBounds(185, 78, 200, 50);
        lbluntil.setFont(liv3);
        lbluntil.setForeground(new Color(72, 72, 72));
        body.add(lbluntil);
        
        JLabel header = new JLabel("This serves as your reservation ticket");
        header.setBounds(58, 11, 350, 28);
        header.setForeground(new Color(255, 255, 255));
        header.setFont(kPark1);
        panel.add(header);
        
        name.setText(input1 + " - " + input2);
        lblloc.setText(input3);
        lbltype.setText(input4);
        lblfrom.setText(input5);
        lbluntil.setText(input6);
        
        body.setLayout(null);
        panel.setLayout(null);
        this.setLayout(layout);
    }
                       
         
}
