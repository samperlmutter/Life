package com.samperlmutter;

import java.awt.Graphics;
import java.awt.event.InputEvent;
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
				progressionName = "Next generation";
				break;
			case REGRESSION:
				progressionName = "Previous generation";
				break;
		}
		setUI(new UI());
		setText(progressionName);
		addMouseListener(new ButtonListener());
		updatedCells = new ArrayList<Cell>();
	}

	public String getName() {
		return progressionName;
	}

	private class UI extends MetalButtonUI {
		@Override
		protected void paintButtonPressed(Graphics g, AbstractButton b) {
		}
	}

	public void update() {
		for (int i = 0; i < Game.grid.getGrid().size(); i++) {
			for (int j = 0; j < Game.grid.getGrid().get(i).size(); j++) {
//				System.out.println(j + ", " + i);
//				System.out.println("Cell: (" + Game.grid.getCellCoordinate(Game.grid.getGrid().get(i).get(j))[0] + ", "
//						+ Game.grid.getCellCoordinate(Game.grid.getGrid().get(i).get(j))[1] + ")\n");
				decideFate(Game.grid.getCell(j, i));
			}
		}
//		for (Cell cell : updatedCells) {
//			Game.grid.printCellCoordinateNewLine(cell);
//		}
		updateCells();
	}
	
	private void updateCells() {
//		System.out.println(updatedCells.size());
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
	}

	private void decideFate(Cell cell) {
		int liveNeighbors = 0;
		int x;
		int y;
		int[][] neighborCoordinate = { { -1, 1 }, { 0, 1 }, { 1, 1 }, { -1, 0 }, { 1, 0 }, { -1, -1 }, { 0, -1 },
				{ 1, -1 } };
		Cell neighbor;
		List<Cell> neighbors = new ArrayList<Cell>();

		for (int i = 0; i < neighborCoordinate.length; i++) {
			try {
				neighbor = Game.grid.getCell(Game.grid.getCellCoordinate(cell)[0] + neighborCoordinate[i][0],
						Game.grid.getCellCoordinate(cell)[1] + neighborCoordinate[i][1]);
//				System.out.print("Cell: (" + Game.grid.getCellCoordinate(neighbor)[0] + ", "
//						+ Game.grid.getCellCoordinate(neighbor)[1] + ")");
//				System.out.print(" is neighbor of " + "Cell: (" + Game.grid.getCellCoordinate(cell)[0] + ", "
//						+ Game.grid.getCellCoordinate(cell)[1] + ")\n");
				neighbors.add(neighbor);
			} catch (IndexOutOfBoundsException e) {
//				System.out.println("out of bounds");
				continue;
			}
		}

		for (int i = 0; i < neighbors.size(); i++) {
			x = Game.grid.getCellCoordinate(neighbors.get(i))[0];
			y = Game.grid.getCellCoordinate(neighbors.get(i))[1];
			if (Game.grid.getCell(x, y).getMortality()) {
				liveNeighbors++;
			}
		}

//		System.out.println("Live neighbors: " + liveNeighbors);

		if (liveNeighbors == 3 && !cell.getMortality()) {
			if (cell.getFate() == CellState.DEAD) {
				cell.setFate(CellState.ALIVE);
				updatedCells.add(cell);
			}
		} else if (liveNeighbors <= 1 || liveNeighbors >= 4) {
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
}
