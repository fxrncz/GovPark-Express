package project;

import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import raven.glasspanepopup.GlassPanePopup;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.List;

public class HomePage extends JFrame {

	private JPanel contentPane;
	private int xOffset, yOffset;
	private JLabel title;
	private Design_Libraries.Button login;
	private Design_Libraries.Button reg;
	Image image1;
	Image sen;
	Font livvic;
	Font btnLivvic;
	
	public HomePage() {
		
		GlassPanePopup.install(this);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(1440, 800);
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(new Color(245, 245, 245));
		contentPane.add(Holder());
	}
	
	protected JPanel Holder() {
		return new Holder();
	}
	
	private class Holder extends JPanel {
		
		public void paint(Graphics g) {
		    super.paint(g);
		    Graphics2D g2d = (Graphics2D) g;
		    image1 = new ImageIcon("img/Icon.png").getImage();
		    sen = new ImageIcon("img/Sen.png").getImage();
		    g2d.drawImage(image1, 503, 103, 400, 400, null);
		    g2d.drawImage(sen, 275, 510, 865, 200, null);
		    g2d.setStroke(new BasicStroke(2));
		    g2d.drawLine(-200, 110, 1440, 110);
		}
		
		public Holder() {
			this.setBounds(0, 0, 1440, 936);
	        this.setBackground(new Color(245, 245, 245));
	        this.setLayout(null);
	        
	        try {
				livvic = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")).deriveFont(32f);
				btnLivvic = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")).deriveFont(20f);
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("fonts/KulimPark-SemiBold.ttf")));
				
			} catch (IOException | FontFormatException e) {
				e.printStackTrace();
			}
			
			
			undercoratedMoveableFrame();
			
			title = new JLabel("GovPark Express");
			this.add(title);
			title.setForeground(Color.BLACK);
			title.setBounds(85, 43, 500, 50);
			title.setFont(livvic);
			
			login = new Design_Libraries.Button();
			this.add(login);
			login.setBounds(1010, 48, 160, 40);
			login.setColor(new Color(245, 245, 245));
			login.setBorderColor(Color.BLACK);
			login.setForeground(Color.BLACK);
			login.setText("Log In");
			login.setFocusable(false);
			login.setFont(btnLivvic);
			login.setRadius(20);
			login.setBorderPainted(false);
			
			login.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					login.setColor(Color.BLACK);
					login.setForeground(Color.WHITE);
				}
				
				public void mouseExited(MouseEvent e) {
					login.setColor(new Color(245, 245, 245));
					login.setForeground(Color.BLACK);
				}
				
				public void mouseClicked(MouseEvent e) {
					loadingScreenLP();
				}
			});
			
			reg = new Design_Libraries.Button();
			this.add(reg);
			reg.setBounds(1210, 48, 160, 40);
			reg.setColor(Color.BLACK);
			//reg.setBorderColor(Color.BLACK);
			reg.setForeground(Color.WHITE);
			reg.setText("Register");
			reg.setFocusable(false);
			reg.setFont(btnLivvic);
			reg.setRadius(20);
			reg.setBorderPainted(false);
			
			reg.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					reg.setColor(new Color(91, 117, 255));
					reg.setForeground(Color.WHITE);
				}
				
				public void mouseExited(MouseEvent e) {
					reg.setColor(Color.BLACK);
					reg.setForeground(Color.WHITE);
				}
				
			});
			
			reg.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() == reg) {
						loadingScreenRP();
					}
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

	public void loadingScreenLP() {
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
	
	public void loadingScreenRP() {
		SwingWorker<Void, Integer> worker = new SwingWorker<Void, Integer>() {
            Design_Libraries.Loading sc;

            @Override
            protected Void doInBackground() throws Exception {
                sc = new Design_Libraries.Loading();
                sc.setVisible(true);

                for (int x = 0; x <= 100; x++) {
                    Thread.sleep(30);
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
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomePage frame = new HomePage();
					frame.setUndecorated(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
