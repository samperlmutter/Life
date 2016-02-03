package com.samperlmutter;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {
	
	public JFrame window = new JFrame(Constants.WINDOW_TITLE);
	public static JPanel mainPanel = new JPanel();
	public static Grid grid;
	public static Game game;
	
	public Game() {
		grid = new Grid(Constants.X_CELLS, Constants.Y_CELLS);
		window.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.setBackground(Constants.BACKGROUND_COLOR);
		
		mainPanel.add(grid);
		
		window.add(mainPanel);
		window.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		game = new Game();
		game.window.setVisible(true);
	}
	
}
