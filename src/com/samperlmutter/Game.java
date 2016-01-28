package com.samperlmutter;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Game {

	public static void main(String[] args) {
		JFrame window = new JFrame(Constants.WINDOW_TITLE);
		JLabel label = new JLabel("Time to divide some cells!", SwingConstants.CENTER);
		JPanel panel = new JPanel();
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints layoutConstraints = new GridBagConstraints();
		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		label.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		window.setLayout(layout);
		
//		layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 0;
		panel.add(new JButton("Time to divide some cells!"), layoutConstraints);
		
//		layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
		layoutConstraints.gridx = 1;
		layoutConstraints.gridy = 0;
		panel.add(new JButton("Time to divide some cells!"), layoutConstraints);
		
		layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
		layoutConstraints.ipady = 20;
		layoutConstraints.gridx = 0;
		layoutConstraints.gridy = 3;
		panel.add(new JButton("Time to divide some cells!"), layoutConstraints);
		
		window.add(panel);
		window.setLocationRelativeTo(null);
		window.pack();
		window.setVisible(true);
	}

}
