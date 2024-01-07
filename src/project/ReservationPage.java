package project;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.raven.datechooser.*;
import raven.glasspanepopup.GlassPanePopup;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import com.barcodelib.barcode.Linear;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class ReservationPage extends JFrame {

	private JPanel contentPane;
	private int xOffset, yOffset;
	private JLabel title;
	private JLabel parkrev;
	private JLabel name;
	private JLabel platenum;
	private JLabel locnote;
	private JLabel veh;
	private JLabel vehnote;
	private JLabel date1;
	private JLabel date2;
	private JLabel loc;
	private Design_Libraries.Button confirm;
	private Design_Libraries.TextFields txtname;
	private Design_Libraries.TextFields gpnum;
	private Design_Libraries.TextFields txtdate1;
	private Design_Libraries.TextFields txtdate2;
	private Design_Libraries.ComboBoxSuggestions comboboxloc;
	private Design_Libraries.ComboBoxSuggestions vehicle;
	private com.raven.datechooser.DateChooser dateChooser1;
	private Design_Libraries.TickBtn logout;
	private com.raven.datechooser.DateChooser dateChooser2;
	private char[] letters;
	Font kPark1;
	Font kPark2;
	Font kPark3;
	Font liv1;
	Font liv2;
	Font liv3;
	
	
	
	public ReservationPage() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1440, 800);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setUndecorated(true);
		contentPane = new JPanel();
		this.setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(245, 245, 245));
		undercoratedMoveableFrame();
		initComponent();
		contentPane.add(holder());
		GlassPanePopup.install(this);
		
	}
	
	private void initComponent() {
		
		try {
			kPark1 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")).deriveFont(32f);
			kPark2 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-Bold.ttf")).deriveFont(25f);
			kPark3 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")).deriveFont(40f);
			liv1 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-SemiBold.ttf")).deriveFont(15.5f);
			liv2 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-SemiBold.ttf")).deriveFont(15f);
			liv3 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-Italic.ttf")).deriveFont(11f);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		title = new JLabel("GovPark Express");
		contentPane.add(title);
		title.setForeground(Color.BLACK);
		title.setBounds(85, 43, 500, 50);
		title.setFont(kPark1);
		
		logout = new Design_Libraries.TickBtn();
		contentPane.add(logout);
		logout.setBounds(1300, 50, 110, 40);
		logout.setColor(Color.BLACK);
		logout.setForeground(Color.WHITE);
		logout.setText("Log out");
		logout.setFocusable(false);
		logout.setFont(liv1);
		logout.setRadius(10);
		logout.setBorderPainted(false);
		
		logout.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				GlassPanePopup.showPopup(new Notification.LogoutNotif());
			}
			
		});
		
		logout.addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e) {
				logout.setColor(new Color(245, 245, 245));
				logout.setForeground(Color.BLACK);
				logout.setBorderColor(Color.BLACK);
			}
			
			public void mouseExited(MouseEvent e) {
				logout.setColor(Color.BLACK);
				logout.setForeground(Color.WHITE);
			}
			
		});
		
		parkrev = new JLabel("Parking Reservation");
		contentPane.add(parkrev);
		parkrev.setForeground(Color.BLACK);
		parkrev.setBounds(525, 180, 500, 50);
		parkrev.setFont(kPark3);
		
		name = new JLabel("LAST NAME");
		contentPane.add(name);
		name.setForeground(Color.BLACK);
		name.setBounds(200, 265, 150, 50);
		name.setFont(liv1);
		
		txtname = new Design_Libraries.TextFields();
		contentPane.add(txtname);
		txtname.setBounds(200, 305, 475, 56);
		txtname.setFont(liv1);
		txtname.setStrokeWidth(1);
		
		String fname = txtname.getText();
		
		platenum = new JLabel("GOVERNMENT PLATE NUMBER");
		contentPane.add(platenum);
		platenum.setForeground(Color.BLACK);
		platenum.setBounds(720, 265, 300, 50);
		platenum.setFont(liv1);

		// PLATE NUMBER HERE
		gpnum = new Design_Libraries.TextFields();
		gpnum.setStrokeWidth(1);
		gpnum.setFont(liv1);
        gpnum.setBounds(720, 305, 475, 56);
        contentPane.add(gpnum);
        
        ((AbstractDocument) gpnum.getDocument()).setDocumentFilter(new DocumentFilter() {
            int maxCharacters = 8;

            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                int currentLength = fb.getDocument().getLength();
                int insertLength = text.length();
                if (currentLength + insertLength - length <= maxCharacters)
                	super.replace(fb, offset, length, text, attrs);
                else
                    super.replace(fb, offset, length, text.substring(0, maxCharacters - currentLength + length), attrs);
            }
        });
		
		loc = new JLabel("LOCATION");
		contentPane.add(loc);
		loc.setBounds(200, 375, 200, 50);
		loc.setForeground(Color.BLACK);
		loc.setFont(liv1);
		
		locnote = new JLabel("Please select a location");
		contentPane.add(locnote);
		locnote.setBounds(295, 375, 400, 50);
		locnote.setForeground(new Color(40, 40, 40));
		locnote.setFont(liv3);
		
		comboboxloc = new Design_Libraries.ComboBoxSuggestions();
		contentPane.add(comboboxloc);
		comboboxloc.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"ANTIQUE", "BUTUAN", "DUMAGUETE", "GALERIA ORTIGAS", "GAPAN", "GENERAL TRIAS", "ILIGAN", "ILOCOS", "ILOILO", "LAS PINAS", "LIPA", "LUCENA", "MALOLOS", "MANILA", "METRO EAST", "NAGA", "NUSTAR", "PAVIA", "SANTIAGO", "STARMILLS"}));
	    comboboxloc.setBounds(200, 415, 475, 56);
	    comboboxloc.setFont(liv2);
	    
	    String txtcombo = comboboxloc.getSelectedItem().toString();
	    
	    veh = new JLabel("VEHICLE");
	    contentPane.add(veh);
	    veh.setBounds(720, 375, 200, 50);
	    veh.setForeground(Color.BLACK);
	    veh.setFont(liv1);
	    
	    vehnote = new JLabel("Please select a vehicle type");
	    contentPane.add(vehnote);
		vehnote.setBounds(800, 375, 200, 50);
		vehnote.setForeground(new Color(40, 40, 40));
		vehnote.setFont(liv3);
		
		vehicle = new Design_Libraries.ComboBoxSuggestions();
		contentPane.add(vehicle);
		vehicle.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"4 - WHEEL DRIVE", "2 - WHEEL DRIVE"}));
		vehicle.setBounds(720, 415, 475, 56);
		vehicle.setFont(liv2);
		
		String txtveh = vehicle.getSelectedItem().toString();
		
		date1 = new JLabel("FROM");
		contentPane.add(date1);
		date1.setBounds(200, 485, 150, 56);
		date1.setForeground(Color.BLACK);
		date1.setFont(liv1);
		
		JLabel dNote = new JLabel("Select a starting date");
		contentPane.add(dNote);
		dNote.setBounds(260, 485, 200, 56);
		dNote.setForeground(new Color(40, 40, 40));
		dNote.setFont(liv3);
		
		txtdate1 = new Design_Libraries.TextFields();
		contentPane.add(txtdate1);
		txtdate1.setBounds(200, 525, 475, 56);
		txtdate1.setFont(liv1);
		txtdate1.setStrokeWidth(1);
		txtdate1.setBorder(new EmptyBorder(0, 20, 0, 20));
		
		dateChooser1 = new com.raven.datechooser.DateChooser();
		dateChooser1.setTextRefernce(txtdate1);
		dateChooser1.setDateFormat("MMMM - dd - yyyy");
		dateChooser1.addEventDateChooser(new EventDateChooser() {
			
			public void dateSelected(SelectedAction action, SelectedDate date) {
				if(action.getAction() == SelectedAction.DAY_SELECTED) {
					dateChooser1.hidePopup();
				}
			}
		});
		
		SelectedDate d1 = dateChooser1.getSelectedDate();
		String date1 = d1.getDay() + "-" + d1.getMonth() + "-" + d1.getYear();
		
		date2 = new JLabel("UNTIL");
		contentPane.add(date2);
		date2.setBounds(720, 485, 150, 56);
		date2.setForeground(Color.BLACK);
		date2.setFont(liv1);
		
		JLabel dNote2 = new JLabel("Select the end date");
		contentPane.add(dNote2);
		dNote2.setBounds(780, 485, 200, 56);
		dNote2.setForeground(new Color(40, 40, 40));
		dNote2.setFont(liv3);
		
		txtdate2 = new Design_Libraries.TextFields();
		contentPane.add(txtdate2);
		txtdate2.setBounds(720, 525, 475, 56);
		txtdate2.setFont(liv1);
		txtdate2.setStrokeWidth(1);
		txtdate2.setBorder(new EmptyBorder(0, 20, 0, 20));
		
		dateChooser2 = new com.raven.datechooser.DateChooser();
		dateChooser2.setTextRefernce(txtdate2);
		dateChooser2.setDateFormat("MMMM - dd - yyyy");
		dateChooser2.addEventDateChooser(new EventDateChooser() {
			
			public void dateSelected(SelectedAction action, SelectedDate date) {
				if(action.getAction() == SelectedAction.DAY_SELECTED) {
					dateChooser2.hidePopup();
				}
			}
		});
		
		SelectedDate d2 = dateChooser2.getSelectedDate();
		String date2 = d2.getYear() + "-" + d2.getMonth() + "-" + d2.getDay();
		
		confirm = new Design_Libraries.Button();
		contentPane.add(confirm);
		confirm.setBounds(440, 620, 500, 46);
		confirm.setColor(Color.BLACK);
		confirm.setForeground(Color.WHITE);
		confirm.setText("Confirm");
		confirm.setFocusable(false);
		confirm.setFont(liv1);
		confirm.setRadius(10);
		confirm.setBorderPainted(false);
		
		confirm.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(txtname.getText().isEmpty() || gpnum.getText().isEmpty()) {
					GlassPanePopup.showPopup(new Notification.IncompleteText());
				} else {
					GlassPanePopup.showPopup(new Ticket(txtname.getText().toUpperCase(), gpnum.getText(), comboboxloc.getSelectedItem().toString(), vehicle.getSelectedItem().toString(), txtdate1.getText(), txtdate2.getText()));
				}
			}
			
		});
		
		confirm.addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e) {
				confirm.setColor(new Color(245, 245, 245));
				confirm.setBorderColor(Color.BLACK);
				confirm.setForeground(Color.BLACK);
			}
			
			public void mouseExited(MouseEvent e) {
				confirm.setColor(Color.BLACK);
				confirm.setForeground(Color.WHITE);
			}
			
		});
	}
	
	protected JPanel holder() {
		return new gHolder();
	}
	
	private class gHolder extends JPanel {
		
		public void paint(Graphics g) {
		    super.paint(g);
		    Graphics2D g2d = (Graphics2D) g;
		    g2d.setStroke(new BasicStroke(2));
		    g2d.drawLine(-200, 110, 1440, 110);
		}
		
		public gHolder() {
			this.setBounds(0, 0, 1440, 800);
			this.setLayout(null);
		}
		
	}
	
	private void undercoratedMoveableFrame() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(70, 70, 70));
		panel.setBounds(0, 0, 1440, 30);
		contentPane.add(panel);
		panel.setLayout(null);
		
		panel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                xOffset = e.getX();
                yOffset = e.getY();
            }
        });

        panel.addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                setLocation(e.getXOnScreen() - xOffset, e.getYOnScreen() - yOffset);
            }
        });
		
		JLabel exit = new JLabel("×");
		exit.setBounds(1400, 3, 39, 26);
		exit.setForeground(Color.WHITE);
		exit.setFont(new Font("Arial", Font.PLAIN, 35));
		panel.add(exit);
		
		exit.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				GlassPanePopup.showPopup(new Notification.ExitMessage());
				//System.exit(0);
            }
			
			public void mouseEntered(MouseEvent e) {
				exit.setForeground(Color.RED);
			}
			
			public void mouseExited(MouseEvent e) {
				exit.setForeground(Color.WHITE);
			}
			
		});
		
		JLabel min = new JLabel("—");
		min.setBackground(new Color(255, 255, 255));
		min.setForeground(Color.WHITE);
		min.setFont(new Font("Arial", Font.PLAIN, 20));
		min.setBounds(1360, 7, 46, 14);
		panel.add(min);
		
		min.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
                setExtendedState(JFrame.ICONIFIED);
            }
			
			public void mouseEntered(MouseEvent e) {
				min.setForeground(Color.BLUE);
			}
			
			public void mouseExited(MouseEvent e) {
				min.setForeground(Color.WHITE);
			}
			
		});
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservationPage frame = new ReservationPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
