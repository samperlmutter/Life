package com.samperlmutter;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {
	
	public JFrame window = new JFrame(Constants.WINDOW_TITLE);
	public static JPanel mainPanel = new JPanel();
	public static Grid grid;
	
	public Game() {
		grid = new Grid(Constants.X_CELLS, Constants.Y_CELLS);
		window.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainPanel.add(grid);
		
		window.add(mainPanel);
		window.setLocationRelativeTo(null);
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.window.setVisible(true);
	}
	
}
