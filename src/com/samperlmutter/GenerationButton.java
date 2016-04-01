package com.samperlmutter;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.plaf.metal.MetalButtonUI;

import com.samperlmutter.Cell.CellState;

public class GenerationButton extends JButton {
	
	public static enum Progression {
		REGRESSION, PROGRESSION
	}

	private String progressionName;
	private List<Cell> updatedCells;

	public GenerationButton(Progression progression) {
		switch (progression) {
			case PROGRESSION:
				setText(Constants.PROGRESSION_BUTTON_NAME);
				break;
			case REGRESSION:
				setText(Constants.REGRESSION_BUTTON_NAME);
				break;
		}
		setUI(new UI());
		addMouseListener(new ButtonListener());
		updatedCells = new ArrayList<Cell>();
	}

	public void update() {
		for (List<Cell> row : Game.grid.getGrid()) {
			for (Cell cell : row) {
				decideFate(cell);
			}
		}
		updateCells();
		for (List<Cell> row : Game.grid.getGrid()) {
			for (Cell cell : row) {
				cell.updateSurroundingNeighbors();
			}
		}
	}
	
	private void updateCells() {
		for (Cell cell : updatedCells) {
			switch (cell.getFate()) {
				case ALIVE:
					cell.reincarnate();
					break;
				case DEAD:
					cell.die();
					break;
			}
		}
		updatedCells.clear();
	}

	private void decideFate(Cell cell) {
		if (cell.getLiveNeighbors() == 3 && cell.getMortality() == CellState.DEAD) {
			if (cell.getFate() == CellState.DEAD) {
				cell.setFate(CellState.ALIVE);
				updatedCells.add(cell);
			}
		} else if (cell.getLiveNeighbors() <= 1 || cell.getLiveNeighbors() >= 4) {
			if (cell.getFate() == CellState.ALIVE) {
				cell.setFate(CellState.DEAD);
				updatedCells.add(cell);
			}
		}
	}
	
	private class ButtonListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			update();
		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

	}
	
	private class UI extends MetalButtonUI {
		@Override
		protected void paintButtonPressed(Graphics g, AbstractButton b) {
			
		}
	}
}
