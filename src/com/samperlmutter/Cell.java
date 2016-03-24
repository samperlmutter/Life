package com.samperlmutter;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalButtonUI;

public class Cell extends JButton {
	
	private boolean hasPulse;
	private static List<Cell> draggedCells = new ArrayList<Cell>();
	private CellState cellState;
	private int liveNeighbors;
	private Cell[] neighbors;
	private Cell thisCell;
	
	public static enum CellState {
		ALIVE, DEAD
	}
	
	public Cell() {
		thisCell = this;
		hasPulse = false;
		addMouseListener(new ButtonListener());
		setPreferredSize(new Dimension(Constants.CELL_DIMENSION, Constants.CELL_DIMENSION));
		setBackground(Constants.DEAD_CELL_BACKGROUND_COLOR);
		setBorder(new LineBorder(Constants.CELL_BORDER_COLOR, 0));
		setUI(new UI());
		cellState = CellState.DEAD;
		neighbors = new Cell[8];
		updateLiveNeighbors();
	}
	
	public int getLiveNeighbors() {
		return liveNeighbors;
	}
	
	private void updateLiveNeighbors() {
		int[][] neighborCoordinate = { { -1, 1 }, { 0, 1 }, { 1, 1 }, { -1, 0 }, { 1, 0 }, { -1, -1 }, { 0, -1 },
				{ 1, -1 } };
		for (int i = 0; i < 8; i++) {
			try {
				System.out.println(Game.grid.getCellCoordinate(thisCell)[0]);
				int thisX = Game.grid.getCellCoordinate(thisCell)[0];
				int thisY = Game.grid.getCellCoordinate(thisCell)[1];
				int neighborX = thisX + neighborCoordinate[i][0];
				int neighborY = thisY + neighborCoordinate[i][1];
				Cell neighbor = Game.grid.getCell(neighborX, neighborY);
//				System.out.print("Cell: (" + Game.grid.getCellCoordinate(neighbor)[0] + ", "
//						+ Game.grid.getCellCoordinate(neighbor)[1] + ")");
//				System.out.print(" is neighbor of " + "Cell: (" + Game.grid.getCellCoordinate(cell)[0] + ", "
//						+ Game.grid.getCellCoordinate(cell)[1] + ")\n");
				neighbors[i] = neighbor;
			} catch (IndexOutOfBoundsException e) {
//				System.out.println("out of bounds");
				neighbors[i] = null;
				continue;
			}
		}
		
		liveNeighbors = 0;
		for (int i = 0; i < 8; i++) {
			if (neighbors[i] != null && getFate() == CellState.ALIVE) {
				liveNeighbors++;
			}
		}
	}
	
	public void setFate(CellState state) {
		cellState = state;
	}
	
	public CellState getFate() {
		return cellState;
	}
	
	public void printCellCoordinateNewLine() {
		System.out.println("Cell: (" + Game.grid.getCellCoordinate(this)[0] + ", " + Game.grid.getCellCoordinate(this)[1] + ")");
	}
	
	public void printCellCoordinateInline() {
		System.out.print("Cell: (" + Game.grid.getCellCoordinate(this)[0] + ", " + Game.grid.getCellCoordinate(this)[1] + ")");
	}
	
	public void changeMortality() {
		hasPulse = !hasPulse;
		setBackground((hasPulse ? Constants.LIVE_CELL_BACKGROUND_COLOR : Constants.DEAD_CELL_BACKGROUND_COLOR));
	}
	
	public void die() {
		hasPulse = false;
		setBackground(Constants.DEAD_CELL_BACKGROUND_COLOR);
	}
	
	public void reincarnate() {
		hasPulse = true;
		setBackground(Constants.LIVE_CELL_BACKGROUND_COLOR);
	}
	
	public boolean getMortality() {
		return hasPulse;
	}
	
	private class UI extends MetalButtonUI {
		@Override
		protected void paintButtonPressed(Graphics g, AbstractButton b) {}
	}
	
	private class ButtonListener implements MouseListener {
		
		@Override
		public void mouseClicked(MouseEvent e) {
			
		}
	
		@Override
		public void mouseEntered(MouseEvent e) {
			if((e.getSource() instanceof Cell) && ((e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK)) {
				Cell cell = ((Cell) e.getSource());
				if (!draggedCells.contains(cell)) {
					cell.changeMortality();
					draggedCells.add(cell);
				}
			}
		}
	
		@Override
		public void mouseExited(MouseEvent e) {
			
		}
	
		@Override
		public void mousePressed(MouseEvent e) {
			if ((e.getSource() instanceof Cell) && ((e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK)) {
				Cell cell = ((Cell) e.getSource());
				cell.changeMortality();
				cell.updateLiveNeighbors();
				System.out.println("Live neighbors" + cell.getLiveNeighbors());
				draggedCells.add(cell);
//				Game.grid.printCellCoordinateNewLine(cell);
			}
		}
	
		@Override
		public void mouseReleased(MouseEvent e) {
			draggedCells.removeAll(draggedCells);
		}
		
	}

}
