package com.samperlmutter;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Grid extends JPanel {
	private List<List<Cell>> cells;
	
	
	public Grid(int rows, int columns) {
		cells = new ArrayList<List<Cell>>();
		setLayout(new GridLayout(rows, columns, 1, 1));
		populateGrid(rows, columns);
	}
	
	public List<List<Cell>> getGrid() {
		return cells;
	}

	private void populateGrid(int rows, int columns) {
		for (int i = 0; i < rows; i++) {
			cells.add(new ArrayList<Cell>());
			for (int j = 0; j < columns; j++) {
				cells.get(i).add(new Cell());
				add(cells.get(i).get(j));
			}
		}
		
		for (List<Cell> row : cells) {
			for (Cell cell : row) {
				setCellNeighbors(cell);
			}
		}
	}
	
//	public void applyGrid(Grid grid) {
//		for ()
//	}
	
	private void setCellNeighbors(Cell cell) {
		int cellX = getCellCoordinate(cell)[0];
		int cellY = getCellCoordinate(cell)[1];
		int[][] neighborCoordinate = { { -1, 1 }, { 0, 1 }, { 1, 1 }, { -1, 0 }, { 1, 0 }, { -1, -1 }, { 0, -1 },
				{ 1, -1 } };
		List<Cell> neighbors = new ArrayList<Cell>();
		
		for (int i = 0; i < neighborCoordinate.length; i++) {
			try {
				int neighborX = cellX + neighborCoordinate[i][0];
				int neighborY = cellY + neighborCoordinate[i][1];
				Cell neighbor = getCell(neighborX, neighborY);
				neighbors.add(neighbor);
			} catch (IndexOutOfBoundsException e) {
				continue;
			}
		}
		
		cell.setNeighbors(neighbors);
	}
	
	public int[] getCellCoordinate(Cell cell) {
		int[] coordinate = {0, 0};
		end:
		for (int i = 0; i < cells.size(); i++) {
			for (int j = 0; j < cells.get(i).size(); j++) {
				if (cells.get(i).get(j) == cell) {
					coordinate[0] = j;
					coordinate[1] = i;
					break end;
				} 
			}
		}
		return coordinate;
	}
	
	public void printCellCoordinate(Cell cell, boolean inline) {
		if (inline) {
			System.out.print("Cell: (" + getCellCoordinate(cell)[0] + ", " + getCellCoordinate(cell)[1] + ")");
		} else {
			System.out.println("Cell: (" + getCellCoordinate(cell)[0] + ", " + getCellCoordinate(cell)[1] + ")");
		}
	}
	
	public Cell getCell(int x, int y) {
		return cells.get(y).get(x);
	}
}
