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
	
	private static List<Cell> draggedCells = new ArrayList<Cell>();
	private CellState cellState;
	private int liveNeighbors;
	private List<Cell> neighbors;
	
	public static enum CellState {
		ALIVE, DEAD
	}
	
	public Cell() {
		addMouseListener(new ButtonListener());
		setPreferredSize(new Dimension(Constants.CELL_DIMENSION, Constants.CELL_DIMENSION));
		setBackground(Constants.DEAD_CELL_BACKGROUND_COLOR);
		setBorder(new LineBorder(Constants.CELL_BORDER_COLOR, 0));
		setUI(new UI());
		cellState = CellState.DEAD;
		neighbors = new ArrayList<Cell>();
		liveNeighbors = 0;
	}
	
	public void printInfo() {
		System.out.println();
		System.out.println("Coordinate: (" + Game.grid.getCellCoordinate(this)[0] + ", " 
				+ Game.grid.getCellCoordinate(this)[1] + ")");
		System.out.println("Mortality: " + getMortality().toString());
		System.out.println("Fate: " + getFate().toString());
		System.out.println("Live neighbors: " + liveNeighbors);
		System.out.println("Number of neighbors: " + neighbors.size());
		System.out.println();
	}
	
	private void updateLiveNeighbors() {
		liveNeighbors = 0;
		for (int i = 0; i < neighbors.size(); i++) {
			if (neighbors.get(i).getMortality() == CellState.ALIVE) {
				liveNeighbors++;
			}
		}
	}
	
	public void updateSurroundingNeighbors() {
		for (Cell neighbor : neighbors) {
			neighbor.updateLiveNeighbors();
		}
	}
	
	public void setNeighbors(List<Cell> neighbors) {
		this.neighbors.clear();
		for (Cell neighbor : neighbors) {
			this.neighbors.add(neighbor);
		}
	}
	
	public int getLiveNeighbors() {
		return liveNeighbors;
	}
	
	public void setFate(CellState state) {
		cellState = state;
	}
	
	public CellState getFate() {
		return cellState;
	}
	
	public void changeMortality() {
		switch (cellState) {
			case ALIVE:
				cellState = CellState.DEAD;
				setBackground(Constants.DEAD_CELL_BACKGROUND_COLOR);
				break;
			case DEAD:
				cellState = CellState.ALIVE;
				setBackground(Constants.LIVE_CELL_BACKGROUND_COLOR);
				break;
		}
		updateLiveNeighbors();
		updateSurroundingNeighbors();
	}
	
	public void die() {
		cellState = CellState.DEAD;
		setBackground(Constants.DEAD_CELL_BACKGROUND_COLOR);
	}
	
	public void reincarnate() {
		cellState = CellState.ALIVE;
		setBackground(Constants.LIVE_CELL_BACKGROUND_COLOR);
	}
	
	public CellState getMortality() {
		return cellState;
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
					changeCell(cell);
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
				changeCell(cell);
			}
			
			if ((e.getSource() instanceof Cell) && ((e.getModifiers() & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK)) {
				Cell cell = ((Cell) e.getSource());
				cell.printInfo();
			}
		}
	
		@Override
		public void mouseReleased(MouseEvent e) {
			draggedCells.removeAll(draggedCells);
		}
		
		private void changeCell(Cell cell) {
			cell.changeMortality();
			draggedCells.add(cell);
		}
		
	}
	
	private class UI extends MetalButtonUI {
		@Override
		protected void paintButtonPressed(Graphics g, AbstractButton b) {}
	}
}
