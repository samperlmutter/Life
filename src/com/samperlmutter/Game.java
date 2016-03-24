package com.samperlmutter;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game {
	
	public static JFrame window;
	public static JPanel mainPanel;
	public static Grid grid;
	public static Game game;
	public static GenerationButton progressionButton, regressionButton;
	
	public Game() {
		window = new JFrame(Constants.WINDOW_TITLE);
		mainPanel = new JPanel();
		grid = new Grid(Constants.X_CELLS, Constants.Y_CELLS);
		progressionButton = new GenerationButton(GenerationButton.Progression.PROGRESSION);
		regressionButton = new GenerationButton(GenerationButton.Progression.REGRESSION);
		
		window.setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.setBackground(Constants.BACKGROUND_COLOR);
		mainPanel.add(grid);
		mainPanel.add(regressionButton);
		mainPanel.add(progressionButton);
		window.add(mainPanel);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

	public static void main(String[] args) {
		game = new Game();
	}
	
}
