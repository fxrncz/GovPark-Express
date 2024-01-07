package project;

import javax.swing.border.EmptyBorder;

import raven.glasspanepopup.GlassPanePopup;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class IDPopUp extends JFrame {

	private JPanel contentPane;
	private int xOffset, yOffset;
	private Design_Libraries.Container confirmation;
	
	public IDPopUp() {
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
		GlassPanePopup.install(this);
		
	}
	
	private void initComponent() {
		contentPane.add(con());
		
		
	}
	
	protected JPanel con() {
		return new InvisibleContainer();
	}
	
	private class InvisibleContainer extends Design_Libraries.Container {
		
		Font kPark1;
		Font kPark2;
		Font livvic1;
		Font livvic2;
		Font livvic3;
		private JLabel confirm = new JLabel("");
		private JLabel typeID = new JLabel("Type of ID");
		String confirmU = "<HTML><u>Confirmation</u></HTML>";
		private Design_Libraries.ComboBoxSuggestions combobox = new Design_Libraries.ComboBoxSuggestions();
		private JLabel idNum = new JLabel("ID Number");
		private JLabel note = new JLabel("Note: Bring this document for verification");
		private Design_Libraries.IDPopUpTxt txtid = new Design_Libraries.IDPopUpTxt();
		private JLabel lbl1 = new JLabel("I certify that all information on this form are true and correct. I");
		private JLabel lbl2 = new JLabel("understand that any incorrect, false, or misleading statement is");
		private JLabel lbl3 = new JLabel("punishable by law.");
		private Design_Libraries.IDPopUpbtn agree = new Design_Libraries.IDPopUpbtn();
		private Design_Libraries.IDPopUpbtn cancel = new Design_Libraries.IDPopUpbtn();
		
		public void paint(Graphics g) {
	        super.paint(g); 
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setStroke(new BasicStroke(3));
	        g2d.setColor(new Color(97, 97, 97));
	        g2d.drawLine(0, 70, getWidth(), 70); 
	    }

	    public InvisibleContainer() {
	    	
	    	try {
				kPark1 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")).deriveFont(28f);
				kPark2 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")).deriveFont(14f);
				livvic1 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-SemiBold.ttf")).deriveFont(18f);
				livvic2 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-SemiBold.ttf")).deriveFont(15f);
				livvic3 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-Italic.ttf")).deriveFont(11f);
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")));
				
			} catch (IOException | FontFormatException e) {
				e.printStackTrace();
			}
	    	
	        this.setBounds(403, 97, 600, 523);
	        this.setBackground(new Color(245, 245, 245));
	        this.setLayout(null);
	        this.setBorder(BorderFactory.createLineBorder(new Color(97, 97, 97), 3));
	        
	        this.add(confirm);
	        confirm.setText(confirmU);
	        confirm.setForeground(Color.BLACK);
	        confirm.setFont(kPark1);
	        confirm.setBounds(212, 10, 200, 50);
	        
	        this.add(typeID);
	        typeID.setForeground(new Color(40, 40, 40));
	        typeID.setFont(livvic1);
	        typeID.setBounds(39, 90, 100, 50);
	        
	        this.add(combobox);
	        combobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "UMID (SSS and GSIS)", "PASSPORT", "PHILHEALTH", "VOTER'S ID or CERTIFICATE OF REGISTRATION", "BIR", "PRC LICENSE", "BIRTH CERTIFICATE AUTHENTICATED BY PSA", "DRIVER'S LICENSE", "PAG-IBIG ID", "POSTAL ID", "CERTIFICATION FROM LOCAL CIVIL REGISTRAR", "COMPANY ID - GOVERNMENT EMPLOYEES", "SEAMAN'S BOOK and SIRV", "SENIOR CITIZEN ID", "MARINA" }));
	        combobox.setBounds(39, 133, 500, 50);
	        combobox.setFont(livvic2);
	        
	        this.add(idNum);
	        idNum.setBounds(39, 205, 100, 50);
	        idNum.setFont(livvic1);
	        idNum.setForeground(new Color(40, 40, 40));
	        
	        this.add(note);
	        note.setBounds(335, 207, 200, 50);
	        note.setFont(livvic3);
	        note.setForeground(new Color(40, 40, 40));
	        
	        this.add(txtid);
	        txtid.setForeground(new Color(40, 40, 40));
	        txtid.setFont(livvic2);
	        txtid.setBounds(39, 248, 500, 50);
	        txtid.setBorder(new EmptyBorder(0, 20, 0, 20));
	        
	        this.add(lbl1);
	        lbl1.setBounds(41, 320, 450, 50);
	        lbl1.setFont(kPark2);
	        lbl1.setForeground(Color.BLACK);
	        this.add(lbl2);
	        lbl2.setBounds(41, 335, 450, 50);
	        lbl2.setFont(kPark2);
	        lbl2.setForeground(Color.BLACK);
	        this.add(lbl3);
	        lbl3.setBounds(41, 350, 450, 50);
	        lbl3.setFont(kPark2);
	        lbl3.setForeground(Color.BLACK);
	        
	        this.add(agree);
	        agree.setBounds(200, 415, 160, 45);
	        agree.setColor(new Color(245, 245, 245));
	        agree.setBorderColor(Color.BLACK);
	        agree.setForeground(Color.BLACK);
	        agree.setText("I agree");
	        agree.setFocusable(false);
	        agree.setFont(livvic1);
	        agree.setRadius(5);
	        agree.setBorderPainted(false);
	        
	        agree.addActionListener(new ActionListener() {
	        	
	        	public void actionPerformed(ActionEvent e) {
	        		if(txtid.getText().isEmpty()) {
	        			GlassPanePopup.showPopup(new Notification.IncompleteText());
	        		} else {
	        			loadingScreen();
	        		}
	        	}
	        	
	        });
	        
	        agree.addMouseListener(new MouseAdapter() {
	        	
	        	public void mouseEntered(MouseEvent e) {
	        		agree.setColor(Color.BLACK);
	        		agree.setForeground(Color.WHITE);
				}
				
				public void mouseExited(MouseEvent e) {
					agree.setColor(new Color(245, 245, 245));
					agree.setForeground(Color.BLACK);
				}
	        });
	        
	        this.add(cancel);
	        cancel.setBounds(375, 415, 160, 45);
	        cancel.setColor(new Color(245, 245, 245));
	        cancel.setBorderColor(Color.BLACK);
	        cancel.setForeground(Color.BLACK);
	        cancel.setText("Close");
	        cancel.setFocusable(false);
	        cancel.setFont(livvic1);
	        cancel.setRadius(5);
	        cancel.setBorderPainted(false);
	        
	        cancel.addMouseListener(new MouseAdapter() {
	        	
	        	public void mouseEntered(MouseEvent e) {
	        		cancel.setColor(Color.BLACK);
	        		cancel.setForeground(Color.WHITE);
				}
				
				public void mouseExited(MouseEvent e) {
					cancel.setColor(new Color(245, 245, 245));
					cancel.setForeground(Color.BLACK);
				}
				
				public void mouseClicked(MouseEvent e) {
					loadingScreenHP();
				}
	        });
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

	public void loadingScreen() {
		SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            Design_Libraries.Loading sc;

            @Override
            protected Void doInBackground() throws Exception {
                sc = new Design_Libraries.Loading();
                sc.setVisible(true);

                for (int x = 0; x <= 100; x++) {
                    Thread.sleep(18);
                    publish(x);
                }

                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                int x = chunks.get(chunks.size() - 1);
                sc.progressBar.setValue(x);
                sc.loading.setText(Integer.toString(x));
            }

            @Override
            protected void done() {
                new Design_Libraries.Loading().setVisible(false);
                new ReservationPage().setVisible(true);
                sc.dispose(); // Close the Load window
            }
        };

        worker.execute();
        dispose();
	}
	
	public void loadingScreenHP() {
		SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            Design_Libraries.Loading sc;

            @Override
            protected Void doInBackground() throws Exception {
                sc = new Design_Libraries.Loading();
                sc.setVisible(true);

                for (int x = 0; x <= 100; x++) {
                    Thread.sleep(40);
                    publish(x);
                }

                return null;
            }

            @Override
            protected void process(List<Integer> chunks) {
                int x = chunks.get(chunks.size() - 1);
                sc.progressBar.setValue(x);
                sc.loading.setText(Integer.toString(x));
            }

            @Override
            protected void done() {
                new Design_Libraries.Loading().setVisible(false);
                new HomePage().setVisible(true);
                sc.dispose(); // Close the Load window
            }
        };

        worker.execute();
        dispose();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IDPopUp frame = new IDPopUp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
