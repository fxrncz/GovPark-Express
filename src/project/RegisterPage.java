package project;

import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import raven.glasspanepopup.GlassPanePopup;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;
import java.util.List;

public class RegisterPage extends JFrame {
	
	Connection con;

	private JPanel contentPane;
	private int xOffset, yOffset;
	private JLabel title;
	private Design_Libraries.TextFields txtfname;
	private Design_Libraries.TextFields txtmname;
	private Design_Libraries.TextFields txtlname;
	private Design_Libraries.TextFields txtadd;
	private Design_Libraries.TextFields txtmn;
	private Design_Libraries.TextFields txtemail;
	private Design_Libraries.TextFields gpnum;
	private char[] letters;
	private Design_Libraries.PasswordField txtpass;
	private Design_Libraries.PasswordField txtconPass;
	private Design_Libraries.Button con1;
	private Design_Libraries.LoginButton toLog;
	Font kPark;
	Font btnKpark;
	Font lblKpark;
	Font lbl2Kpark;
	Font lbl3Kpark;
	
	JLabel pNumber;
	
	
	
	public RegisterPage() {
		
		createConnection();
		
		try {
			kPark = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")).deriveFont(32f);
			btnKpark = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-Bold.ttf")).deriveFont(25f);
			lblKpark = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-Bold.ttf")).deriveFont(35f);
			lbl2Kpark = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-SemiBold.ttf")).deriveFont(14f);
			lbl3Kpark = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-SemiBold.ttf")).deriveFont(16f);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1440, 800);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(245, 245, 245));
		undercoratedMoveableFrame();
		
		title = new JLabel("GovPark Express");
		contentPane.add(title);
		title.setForeground(Color.BLACK);
		title.setBounds(85, 43, 500, 50);
		title.setFont(kPark);
		
		
		initComponents();
		GlassPanePopup.install(this);
		
		contentPane.add(Holder());
		
	}
	
	public void initComponents() {

		
		// PLATE NUMBER HERE
		gpnum = new Design_Libraries.TextFields();
		gpnum.setStrokeWidth(1);
		gpnum.setFont(lbl2Kpark);
        gpnum.setBounds(235, 535, 443, 50);
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
		
		con1 = new Design_Libraries.Button();
		con1.setBounds(727, 715, 443, 50);
		con1.setColor(Color.BLACK);
		con1.setForeground(Color.WHITE);
		con1.setText("Register");
		con1.setFocusable(false);
		con1.setFont(lbl3Kpark);
		con1.setRadius(10);
		con1.setBorderPainted(false);
		contentPane.add(con1);
		
		con1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				// ~ DATABASE TO
				
				try {
					String email = txtemail.getText();
					String firstName = txtfname.getText();
					String midName = txtmname.getText();
					String lastName = txtlname.getText();
					String address = txtadd.getText();
					String mobile = txtmn.getText();
					String plateNum = gpnum.getText();
					String pass = txtpass.getText(); 
					
					String selectQuery = "SELECT * FROM userinfo WHERE email = ?";
		            String insertQuery = "INSERT INTO userinfo (email, fname, mname, lname, address, mobnum, platnum, pass) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
					
					PreparedStatement selectStatement = con.prepareStatement(selectQuery);
	                PreparedStatement insertStatement = con.prepareStatement(insertQuery);
	                
	                selectStatement.setString(1, email);
	                ResultSet resultSet = selectStatement.executeQuery();
					
	                if (resultSet.next()) {
	                	GlassPanePopup.showPopup(new Notification.AlreadyExist());
					} else if (!txtpass.getText().matches(txtconPass.getText())) {
						GlassPanePopup.showPopup(new Notification.NotSamePass());
					} else if (txtemail.getText().isEmpty() || txtpass.getText().isEmpty() || txtconPass.getText().isEmpty() || txtfname.getText().isEmpty() || txtmname.getText().isEmpty() || txtlname.getText().isEmpty() || txtadd.getText().isEmpty() || txtmn.getText().isEmpty() || gpnum.getText().isEmpty()) {
						GlassPanePopup.showPopup(new Notification.IncompleteText());
					} else if(!txtemail.getText().matches("^[A-Za-z0-9]+@[A-Za-z]+\\.[A-Za-z]{2,4}$")) {
						GlassPanePopup.showPopup(new Notification.InvalidEmail());
					} else {
						
						insertStatement.setString(1, email);
						insertStatement.setString(2, firstName);
						insertStatement.setString(3, midName);
						insertStatement.setString(4, lastName);
						insertStatement.setString(5, address);
						insertStatement.setString(6, mobile);
						insertStatement.setString(7, plateNum);
						insertStatement.setString(8, pass);
						insertStatement.execute();
						System.out.println("Insertion Completed.");
						loadingScreen();
	                    
					}					
				} catch (SQLException ex) {
					ex.printStackTrace();
				} 
				
			}
			
		});
		
		con1.addMouseListener(new MouseAdapter() {
			public void mouseEntered(MouseEvent e) {
				con1.setColor(new Color(245, 245, 245));
				con1.setBorderColor(Color.BLACK);
				con1.setForeground(Color.BLACK);
			}
			
			public void mouseExited(MouseEvent e) {
				con1.setColor(Color.BLACK);
				con1.setForeground(Color.WHITE);
			}
			
		});
		
		toLog = new Design_Libraries.LoginButton();
		toLog.setBounds(235, 715, 443, 50);
		toLog.setText("Already have an account? Click Here");
		toLog.setFocusable(false);
		toLog.setFont(lbl3Kpark);
		toLog.setRadius(10);
		toLog.setBorderPainted(false);
		toLog.setColor(new Color(245, 245, 245));
		toLog.setForeground(Color.BLACK);
		toLog.setBorderColor(Color.BLACK);
		contentPane.add(toLog);
		
		toLog.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == toLog) {
					loadingScreentoLog();
				}
			}
			
		});
		
		toLog.addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e) {
				toLog.setForeground(Color.WHITE);
				toLog.setColor(Color.BLACK);
			}
			
			public void mouseExited(MouseEvent e) {
				toLog.setForeground(Color.BLACK);
				toLog.setColor(new Color(245, 245, 245));
				toLog.setBorderColor(Color.BLACK);
			}
			
		});
		
		JLabel regtitle = new JLabel("Register");
		regtitle.setVerticalAlignment(SwingConstants.TOP);
		regtitle.setBounds(235, 135, 198, 50);
		regtitle.setFont(lblKpark);
		regtitle.setForeground(Color.BLACK);
		contentPane.add(regtitle);
		
		JLabel lblFname = new JLabel("FIRST NAME");
		lblFname.setVerticalAlignment(SwingConstants.TOP);
		lblFname.setBounds(235, 195, 127, 24);
		lblFname.setFont(lbl2Kpark);
		lblFname.setForeground(Color.BLACK);
		contentPane.add(lblFname);
		
		txtfname = new Design_Libraries.TextFields();
		txtfname.setBounds(234, 220, 290, 50);
		txtfname.setFont(lbl2Kpark);
		txtfname.setStrokeWidth(1);
		contentPane.add(txtfname);
		
		JLabel lblMname = new JLabel("MIDDLE NAME");
		lblMname.setVerticalAlignment(SwingConstants.TOP);
		lblMname.setBounds(558, 195, 127, 24);
		lblMname.setFont(lbl2Kpark);
		lblMname.setForeground(Color.BLACK);
		contentPane.add(lblMname);
		
		txtmname = new Design_Libraries.TextFields();
		txtmname.setBounds(557, 220, 290, 50);
		txtmname.setFont(lbl2Kpark);
		txtmname.setStrokeWidth(1);
		contentPane.add(txtmname);
		
		JLabel lblLname = new JLabel("LAST NAME");
		lblLname.setVerticalAlignment(SwingConstants.TOP);
		lblLname.setBounds(881, 195, 127, 24);
		lblLname.setFont(lbl2Kpark);
		lblLname.setForeground(Color.BLACK);
		contentPane.add(lblLname);
		
		txtlname = new Design_Libraries.TextFields();
		txtlname.setBounds(880, 220, 290, 50);
		txtlname.setFont(lbl2Kpark);
		txtlname.setStrokeWidth(1);
		contentPane.add(txtlname);
		
		JLabel address = new JLabel("STREET / UNIT / BLDG / VILLAGE, BARANGAY, CITY / TOWN, PROVINCE ");
		address.setVerticalAlignment(SwingConstants.TOP);
		address.setBounds(235, 300, 800, 24);
		address.setFont(lbl2Kpark);
		address.setForeground(Color.BLACK);
		contentPane.add(address);
		
		txtadd = new Design_Libraries.TextFields();
		txtadd.setBounds(234, 325, 936, 50);
		txtadd.setFont(lbl2Kpark);
		txtadd.setStrokeWidth(1);
		contentPane.add(txtadd);
		
		JLabel mnum = new JLabel("MOBILE NUMBER");
		mnum.setVerticalAlignment(SwingConstants.TOP);
		mnum.setBounds(235, 405, 800, 24);
		mnum.setFont(lbl2Kpark);
		mnum.setForeground(Color.BLACK);
		contentPane.add(mnum);
		
		txtmn = new Design_Libraries.TextFields();
		txtmn.setBounds(234, 430, 443, 50);
		txtmn.setFont(lbl2Kpark);
		txtmn.setStrokeWidth(1);
		contentPane.add(txtmn);
		
		JLabel email = new JLabel("EMAIL ADDRESS");
		email.setVerticalAlignment(SwingConstants.TOP);
		email.setBounds(727, 405, 800, 24);
		email.setFont(lbl2Kpark);
		email.setForeground(Color.BLACK);
		contentPane.add(email);
		
		txtemail = new Design_Libraries.TextFields();
		txtemail.setBounds(726, 430, 443, 50);
		txtemail.setFont(lbl2Kpark);
		txtemail.setStrokeWidth(1);
		contentPane.add(txtemail);
		
		pNumber = new JLabel("GOVERNMENT PLATE NUMBER");
		pNumber.setVerticalAlignment(SwingConstants.TOP);
		pNumber.setBounds(235, 510, 800, 24);
		pNumber.setFont(lbl2Kpark);
		pNumber.setForeground(Color.BLACK);
		contentPane.add(pNumber);
		
		JLabel password = new JLabel("PASSWORD");
		password.setVerticalAlignment(SwingConstants.TOP);
		password.setBounds(235, 615, 800, 24);
		password.setFont(lbl2Kpark);
		password.setForeground(Color.BLACK);
		contentPane.add(password);
		
		txtpass = new Design_Libraries.PasswordField();
		txtpass.setBounds(235, 640, 443, 50);
		txtpass.setFont(lbl2Kpark);
		txtpass.setStrokeWidth(1);
		contentPane.add(txtpass);
		
		JLabel conPassword = new JLabel("CONFIRM PASSWORD");
		conPassword.setVerticalAlignment(SwingConstants.TOP);
		conPassword.setBounds(727, 615, 800, 24);
		conPassword.setFont(lbl2Kpark);
		conPassword.setForeground(Color.BLACK);
		contentPane.add(conPassword);
		
		txtconPass = new Design_Libraries.PasswordField();
		txtconPass.setBounds(727, 640, 443, 50);
		txtconPass.setFont(lbl2Kpark);
		txtconPass.setStrokeWidth(1);
		contentPane.add(txtconPass);
		
		
	}
	
	protected JPanel Holder() {
		return new Holder();
	}
	
	private class Holder extends JPanel {
		
		public void paint(Graphics g) {
		    super.paint(g);
		    Graphics2D g2d = (Graphics2D) g;
		    g2d.setStroke(new BasicStroke(2));
		    g2d.drawLine(-200, 110, 1440, 110);
		}
		
		public Holder() {
			this.setBounds(0, 0, 1440, 936);
			this.setBackground(new Color(245, 245, 245));
		}
	}
	
	public void loadingScreen() {
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
                new LoginPage().setVisible(true);
                sc.dispose(); // Close the Load window
            }
        };

        worker.execute();
        dispose();
	}
	
	public void loadingScreentoLog() {
		SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            Design_Libraries.Loading sc;

            @Override
            protected Void doInBackground() throws Exception {
                sc = new Design_Libraries.Loading();
                sc.setVisible(true);

                for (int x = 0; x <= 100; x++) {
                    Thread.sleep(10);
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
                new LoginPage().setVisible(true);
                sc.dispose(); // Close the Load window
            }
        };

        worker.execute();
        dispose();
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
					RegisterPage frame = new RegisterPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// ~ database connection 'to
	
	static String url = "jdbc:mysql://localhost:3306/parkingdb";
	static String user = "root";
	static String pass = "goldwood123";
	
	void createConnection() {	
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, user, pass);
			System.out.println("Connected Successfully");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
