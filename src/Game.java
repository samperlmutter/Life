import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Game {

	public static void main(String[] args) {
		JFrame window = new JFrame("The Game of Life");
		JLabel label = new JLabel("Time to divide some cells!", SwingConstants.CENTER);
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		label.setPreferredSize(new Dimension(300,  100));
		window.getContentPane().add(label, BorderLayout.CENTER);
		
		window.setLocationRelativeTo(null);
		window.pack();
		window.setVisible(true);
	}

}
