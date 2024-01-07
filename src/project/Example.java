package project;

import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import Design_Libraries.Loading;
import raven.glasspanepopup.GlassPanePopup;

import javax.swing.GroupLayout.Alignment;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class Example extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Example frame = new Example();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Example() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(850, 600);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		GlassPanePopup.install(this);
		
		JButton btn = new JButton("New button");
		btn.setBounds(162, 109, 89, 23);
		contentPane.add(btn);
		
		btn.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				GlassPanePopup.showPopup(new Notification.AlreadyExist());
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
}
