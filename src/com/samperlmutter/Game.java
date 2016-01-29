package com.samperlmutter;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Game {
	
	public static JFrame window = new JFrame(Constants.WINDOW_TITLE);
	public static JLabel label = new JLabel("Time to divide some cells!", SwingConstants.CENTER);
	public static JPanel mainPanel = new JPanel();
	public static JPanel gridPanel = new JPanel();
	public static GridLayout layout = new GridLayout(Constants.X_CELLS, Constants.Y_CELLS);
	public static boolean[][] cellState = new boolean[Constants.X_CELLS][Constants.Y_CELLS];
	public static JButton[][] cells = new JButton[Constants.X_CELLS][Constants.Y_CELLS];
	
	public Game() {
		System.out.println("Cells: " + Constants.X_CELLS);
		System.out.println("Cell width: " + Constants.CELL_DIMENSION);
		window.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		gridPanel.setLayout(layout);
		
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				cells[i][j] = new JButton();
				cells[i][j].addActionListener(new ButtonListener());
				cells[i][j].setPreferredSize(new Dimension(Constants.CELL_DIMENSION, Constants.CELL_DIMENSION));
				cells[i][j].setBackground(Color.WHITE);
				cells[i][j].setBorder(new LineBorder(Color.BLACK, 1));
				cells[i][j].setBorderPainted(true);
				cellState[i][j] = false;
			}
		}
		
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				gridPanel.add(cells[i][j]);
			}
		}
		
		mainPanel.add(gridPanel);
		
		window.add(mainPanel);
//		window.pack();
		window.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		Game game = new Game();
		Game.window.setVisible(true);
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton cell;
			if(e.getSource() instanceof JButton) {
				cell = ((JButton)e.getSource());
				System.out.println(getCellLocation(cell)[0] + ", " + getCellLocation(cell)[1]);
			}
		}
		
		private int[] getCellLocation(JButton cell) {
			int[] coordinate = new int[2];
			
			for (int i = 0; i < cells.length; i++) {
				if (Arrays.asList(cells[i]).indexOf(cell) != -1) {
					coordinate[0] = i;
				}
			}
			coordinate[1] = Arrays.asList(cells[coordinate[0]]).indexOf(cell);
			
			return coordinate;
		}

	}
	
}
