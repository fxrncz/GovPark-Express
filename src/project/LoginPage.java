package project;

import java.awt.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.*;
import raven.glasspanepopup.GlassPanePopup;
import java.awt.event.*;

public class LoginPage extends JFrame {

	Connection con;
	
	private JPanel contentPane;
	private int xOffset, yOffset;
	private JLabel title;
	private Design_Libraries.TextFields userName;
	private Design_Libraries.PasswordField txtpass;
	private Design_Libraries.Button con1;
	private	Design_Libraries.LoginButton toReg;
	Font kPark;
	Font btnKpark;
	Font kPark1;
	Font kPark2;
	Font kPark3;
	Font kPark4;
	
	public LoginPage() {
		
		createConnection();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1440, 800);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		this.setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(245, 245, 245));
		undercoratedMoveableFrame();
		GlassPanePopup.install(this);
		
		initComponents();
		contentPane.add(Holder());
		
		
	}
	
	public void initComponents() {
		try {
			kPark = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")).deriveFont(32f);
			btnKpark = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-Bold.ttf")).deriveFont(25f);
			kPark1 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-Bold.ttf")).deriveFont(35f);
			kPark2 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-SemiBold.ttf")).deriveFont(15.5f);
			kPark3 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-SemiBold.ttf")).deriveFont(14f);
			kPark4 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-Bold.ttf")).deriveFont(14f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")));
			
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		title = new JLabel("GovPark Express");
		contentPane.add(title);
		title.setForeground(Color.BLACK);
		title.setBounds(85, 43, 500, 50);
		title.setFont(kPark);
		
		JLabel lblLogin = new JLabel("Log In");
		lblLogin.setVerticalAlignment(SwingConstants.TOP);
		lblLogin.setBounds(455, 200, 184, 50);
		lblLogin.setForeground(Color.black);
		lblLogin.setFont(kPark1);
		contentPane.add(lblLogin);
		
		JLabel lblFname = new JLabel("EMAIL ADDRESS");
		lblFname.setVerticalAlignment(SwingConstants.TOP);
		lblFname.setBounds(455, 270, 500, 24);
		lblFname.setForeground(Color.BLACK);
		lblFname.setFont(kPark3);
		contentPane.add(lblFname);
		
		userName = new Design_Libraries.TextFields();
		userName.setBounds(453, 295, 542, 50);
		userName.setFont(kPark3);
		userName.setForeground(Color.BLACK);
		userName.setStrokeWidth(1);
		contentPane.add(userName);
		
		JLabel lblPass = new JLabel("PASSWORD");
		lblPass.setVerticalAlignment(SwingConstants.TOP);
		lblPass.setForeground(Color.BLACK);
		lblPass.setBounds(455, 375, 500, 24);
		lblPass.setFont(kPark3);
		contentPane.add(lblPass);
		
		txtpass = new Design_Libraries.PasswordField();
		txtpass.setBounds(453, 400, 542, 50);
		txtpass.setFont(kPark3);
		txtpass.setForeground(Color.BLACK);
		txtpass.setStrokeWidth(1);
		contentPane.add(txtpass);
		
		con1 = new Design_Libraries.Button();
		con1.setBounds(455, 500, 542, 50);
		con1.setColor(Color.BLACK);
		con1.setForeground(Color.WHITE);
		con1.setText("Login");
		con1.setFocusable(false);
		con1.setFont(kPark2);
		con1.setRadius(10);
		con1.setBorderPainted(false);
		contentPane.add(con1);
		
		con1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				try {
					String email = userName.getText();
					String password = txtpass.getText(); 
					
					if (userName.getText().isBlank() && txtpass.getText().isBlank()) {
						GlassPanePopup.showPopup(new Notification.IncompleteText());
					} else {
						Statement stmt = con.createStatement();
						String sql = "select * from userinfo where email='"+ email +"' and pass='"+ password +"'";
						ResultSet rs = stmt.executeQuery(sql);
						
						if(rs.next()) {
							loadingScreen();
						} else  {
							GlassPanePopup.showPopup(new Notification.WrongCredentials());
						}
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
		
		JLabel btnforgot = new JLabel("Forgot Password?");
		btnforgot.setVerticalAlignment(SwingConstants.TOP);
		btnforgot.setBounds(875, 453, 150, 23);
		btnforgot.setFont(kPark3);
		contentPane.add(btnforgot);
		
		btnforgot.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				loadingScreenFP();
			}
			
			public void mouseEntered(MouseEvent e) {
				btnforgot.setFont(kPark4);
			}
			
			public void mouseExited(MouseEvent e) {
				btnforgot.setFont(kPark3);
			}
		});
		
		toReg = new Design_Libraries.LoginButton();
		contentPane.add(toReg);
		toReg.setText("Want to Register? Click Here");
		toReg.setBounds(455, 565, 542, 50);
		toReg.setColor(new Color(245, 245, 245));
		toReg.setForeground(Color.BLACK);
		toReg.setBorderColor(Color.BLACK);
		toReg.setFocusable(false);
		toReg.setFont(kPark2);
		toReg.setRadius(10);
		toReg.setBorderPainted(false);
		
		toReg.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == toReg) {
					loadingScreentoReg();
				}
			}
			
		});
		
		toReg.addMouseListener(new MouseAdapter() {
			
			public void mouseEntered(MouseEvent e) {
				toReg.setForeground(Color.WHITE);
				toReg.setColor(Color.BLACK);
			}
			
			public void mouseExited(MouseEvent e) {
				toReg.setForeground(Color.BLACK);
				toReg.setColor(new Color(245, 245, 245));
				toReg.setBorderColor(Color.BLACK);
			}
			
		});
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
	
	public void loadingScreenFP() {
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
                new ForgetPassword().setVisible(true);
                sc.dispose(); // Close the Load window
            }
        };

        worker.execute();
        dispose();
	}
	
	public void loadingScreentoReg() {
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
                new RegisterPage().setVisible(true);
                sc.dispose(); // Close the Load window
            }
        };

        worker.execute();
        dispose();
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
                new IDPopUp().setVisible(true);
                sc.dispose(); // Close the Load window
            }
        };

        worker.execute();
        dispose();
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


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
