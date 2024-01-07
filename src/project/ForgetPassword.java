package project;

import javax.swing.*;

import raven.glasspanepopup.GlassPanePopup;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;

public class ForgetPassword extends JFrame {
	
	private int xOffset, yOffset;
	Connection con;
	private JPanel contentPane;
	private JLabel title;
	private Design_Libraries.TickBtn rstbtn;
	private Design_Libraries.TextFields userName;
	private Design_Libraries.PasswordField txtpass;
	private Design_Libraries.PasswordField txtconfirmpass;
	private Design_Libraries.TickBtn toLog;
	
	Font liv1;
	Font liv2;
	Font kPark1;
	Font kPark2;
	Font kPark3;

	public ForgetPassword() {
		createConnection();
		GlassPanePopup.install(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1440, 800);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(Holder());
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
	        this.setLayout(null);
	        
	        undercoratedMoveableFrame();
	        
	        try {
				liv1 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")).deriveFont(32f);
				liv2 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")).deriveFont(25f);
				kPark1 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-Bold.ttf")).deriveFont(35f);
				kPark2 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-SemiBold.ttf")).deriveFont(15.5f);
				kPark3 = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Livvic-SemiBold.ttf")).deriveFont(14f);
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")));
				
			} catch (IOException | FontFormatException e) {
				e.printStackTrace();
			}
			
			title = new JLabel("GovPark Express");
			this.add(title);
			title.setForeground(Color.BLACK);
			title.setBounds(85, 43, 500, 50);
			title.setFont(liv1);
			
			JLabel lblLogin = new JLabel("Reset Password");
			lblLogin.setVerticalAlignment(SwingConstants.TOP);
			lblLogin.setBounds(455, 180, 300, 50);
			lblLogin.setForeground(Color.black);
			lblLogin.setFont(kPark1);
			this.add(lblLogin);
			
			JLabel lblFname = new JLabel("EXISTING EMAIL ADDRESS");
			lblFname.setVerticalAlignment(SwingConstants.TOP);
			lblFname.setForeground(Color.BLACK);
			lblFname.setBounds(455, 250, 500, 24);
			lblFname.setFont(kPark3);
			this.add(lblFname);
			
			userName = new Design_Libraries.TextFields();
			userName.setBounds(453, 275, 542, 56);
			userName.setFont(kPark2);
			userName.setForeground(Color.BLACK);
			userName.setStrokeWidth(1);
			this.add(userName);
			
			JLabel lblPass = new JLabel("NEW PASSWORD");
			lblPass.setVerticalAlignment(SwingConstants.TOP);
			lblPass.setForeground(Color.BLACK);
			lblPass.setBounds(455, 355, 500, 24);
			lblPass.setFont(kPark3);
			this.add(lblPass);
			
			txtpass = new Design_Libraries.PasswordField();
			txtpass.setBounds(453, 380, 542, 56);
			txtpass.setFont(kPark2);
			txtpass.setForeground(Color.BLACK);
			txtpass.setStrokeWidth(1);
			this.add(txtpass);
			
			JLabel lblconPass = new JLabel("CONFIRM PASSWORD");
			lblconPass.setVerticalAlignment(SwingConstants.TOP);
			lblconPass.setForeground(Color.BLACK);
			lblconPass.setBounds(455, 460, 500, 24);
			lblconPass.setFont(kPark3);
			this.add(lblconPass);
			
			txtconfirmpass = new Design_Libraries.PasswordField();
			txtconfirmpass.setBounds(453, 485, 542, 56);
			txtconfirmpass.setFont(kPark2);
			txtconfirmpass.setForeground(Color.BLACK);
			txtconfirmpass.setStrokeWidth(1);
			this.add(txtconfirmpass);
			
			rstbtn = new Design_Libraries.TickBtn();
			rstbtn.setBounds(453, 570, 544, 50);
			rstbtn.setColor(Color.BLACK);
			rstbtn.setForeground(Color.WHITE);
			rstbtn.setText("Reset Password");
			rstbtn.setFocusable(false);
			rstbtn.setFont(kPark2);
			rstbtn.setRadius(10);
			rstbtn.setBorderPainted(false);
			this.add(rstbtn);
			
			rstbtn.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					
					try {
						String selectQuery = "SELECT * FROM userinfo WHERE email = ?";
			            String updateQuery = "UPDATE userinfo SET pass = ? WHERE email = ?";
						
						if (userName.getText().isEmpty() || txtpass.getText().isEmpty() || txtconfirmpass.getText().isEmpty()) {
							GlassPanePopup.showPopup(new Notification.IncompleteText());
						} else if (!txtpass.getText().matches(txtconfirmpass.getText())) {
							GlassPanePopup.showPopup(new Notification.NotSamePass());
						} else {
							PreparedStatement selectStatement = con.prepareStatement(selectQuery);
			                PreparedStatement updateStatement = con.prepareStatement(updateQuery);
			                
			                String enteredName = userName.getText();
			                String newPass = new String(txtconfirmpass.getText());
			                
			                selectStatement.setString(1, enteredName);
			                ResultSet resultSet = selectStatement.executeQuery();
			                
			                if(resultSet.next()) {
			                	updateStatement.setString(2, enteredName);
			                    updateStatement.setString(1, newPass);
			                    int rowsAffected = updateStatement.executeUpdate();
			                    
			                    if (rowsAffected > 0) {
			                    	GlassPanePopup.showPopup(new Notification.ResetSuccess());
			                    } else {
			                    	GlassPanePopup.showPopup(new Notification.FailedReset());
			                    }
			                } else {
			                	GlassPanePopup.showPopup(new Notification.WrongCredentials());
			                }
						}
						
						
					} catch (SQLException sqle) {
						sqle.printStackTrace();
					}
					
				}
				
			});
			
			rstbtn.addMouseListener(new MouseAdapter() {
				
				public void mouseEntered(MouseEvent e) {
					rstbtn.setColor(new Color(245, 245, 245));
					rstbtn.setForeground(Color.BLACK);
					rstbtn.setBorderColor(Color.BLACK);
				}
				
				public void mouseExited(MouseEvent e) {
					rstbtn.setColor(Color.BLACK);
					rstbtn.setForeground(Color.WHITE);
				}
				
			});
			
			toLog = new Design_Libraries.TickBtn();
			this.add(toLog);
			toLog.setText("Login");
			toLog.setBounds(453, 635, 544, 50);
			toLog.setColor(new Color(245, 245, 245));
			toLog.setForeground(Color.BLACK);
			toLog.setBorderColor(Color.BLACK);
			toLog.setFocusable(false);
			toLog.setFont(kPark2);
			toLog.setRadius(10);
			toLog.setBorderPainted(false);
			
			toLog.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == toLog) {
						loadingScreen();
					}
				}
				
			});
			
			toLog.addMouseListener(new MouseAdapter() {
				
				public void mouseEntered(MouseEvent e) {
					toLog.setColor(Color.BLACK);
					toLog.setForeground(Color.WHITE);
				}
				
				public void mouseExited(MouseEvent e) {
					toLog.setColor(new Color(245, 245, 245));
					toLog.setForeground(Color.BLACK);
					toLog.setBorderColor(Color.BLACK);
				}
				
			});
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
	
	
	static String url = "jdbc:mysql://localhost:3306/parkingdb";
	static String user = "root";
	static String pass = "goldwood123";
	private JTextField txtname;
	private JTextField txt2;
			
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
					ForgetPassword frame = new ForgetPassword();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
