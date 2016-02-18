package com.samperlmutter;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Grid extends JPanel {
	public int rows;
	public int columns;
	public List<List<Cell>> cells;
	public GridLayout layout;
	
	public Grid(int rows, int columns) {
		this.rows = rows;
		this.columns = rows;
		cells = new ArrayList<List<Cell>>();
		setLayout(new GridLayout(rows, columns, 1, 1));
		populateGrid(rows, columns);
	}

	private void populateGrid(int rows, int columns) {
		for (int i = 0; i < rows; i++) {
			cells.add(new ArrayList<Cell>());
			for (int j = 0; j < columns; j++) {
				cells.get(i).add(new Cell());
				add(cells.get(i).get(j));
			}
		}
	}
	
	public void update() {
		for (List<Cell> row : cells) {
			for (Cell cell : row) {
				System.out.println("(" + (cells.indexOf(row) + 1) + ", " + (row.indexOf(cell) + 1) + ")");
			}
		}
	}
	
	public int[] getCellCoordinate(Cell cell) {
		int[] coordinate = {0, 0};
		
		end:
		for (int i = 0; i < cells.size(); i++) {
			for (int j = 0; j < cells.get(i).size(); j++) {
				if (cells.get(i).get(j) == cell) {
					coordinate[0] = i;
					coordinate[1] = j;
					break end;
				} 
			}
		}
		return coordinate;
	}
	
	public void printCellCoordinate(Cell cell) {
		System.out.println("Cell: (" + Game.grid.getCellCoordinate(cell)[0] + ", " + Game.grid.getCellCoordinate(cell)[1] + ")");
	}
	
	public Cell getCell(int x, int y) {
		return cells.get(x).get(y);
	}
	
	public void decideFate(Cell cell) {
		int liveNeighbors = 0;
		int x = getCellCoordinate(cell)[0];
		int y = getCellCoordinate(cell)[1];
		int[][] neighborCoordinate = {{-1, 1}, {0, 1}, {1, 1}, {-1, 0}, {1, 0}, {-1, -1}, {0, 1}, {1, -1}};
		
		for (int i = 0; i < neighborCoordinate.length; i++) {
			if (getCell(x, y) != null) {
				if (getCell(x, y).getMortality()) {
					liveNeighbors++;
				}
			}
		}
		
		if (liveNeighbors == 3 && !cell.getMortality()) {
			cell.reincarnate();
		} else if (liveNeighbors <= 1 || liveNeighbors >= 4) {
			cell.die();
		}
	}
}
