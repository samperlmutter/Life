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
	
	public void printCellCoordinateNewLine(Cell cell) {
		System.out.println("Cell: (" + Game.grid.getCellCoordinate(cell)[0] + ", " + Game.grid.getCellCoordinate(cell)[1] + ")");
	}
	
	public void printCellCoordinateInline(Cell cell) {
		System.out.print("Cell: (" + Game.grid.getCellCoordinate(cell)[0] + ", " + Game.grid.getCellCoordinate(cell)[1] + ")");
	}
	
	public Cell getCell(int x, int y) {
		return cells.get(y).get(x);
	}
}
