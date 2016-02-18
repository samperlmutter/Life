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
	private Cell cell;
	private static List<Cell> draggedCells = new ArrayList<Cell>();
	
	public Cell() {
		cell = this;
		hasPulse = false;
		cell.addMouseListener(new ButtonListener());
		cell.setPreferredSize(new Dimension(Constants.CELL_DIMENSION, Constants.CELL_DIMENSION));
		cell.setBackground(Constants.DEAD_CELL_BACKGROUND_COLOR);
		cell.setBorder(new LineBorder(Constants.CELL_BORDER_COLOR, 0));
		cell.setUI(new UI());
	}
	
	public void changeMortality() {
		hasPulse = !hasPulse;
		cell.setBackground((hasPulse ? Constants.LIVE_CELL_BACKGROUND_COLOR : Constants.DEAD_CELL_BACKGROUND_COLOR));
	}
	
	public void die() {
		hasPulse = false;
		cell.setBackground(Constants.DEAD_CELL_BACKGROUND_COLOR);
	}
	
	public void reincarnate() {
		hasPulse = true;
		cell.setBackground(Constants.LIVE_CELL_BACKGROUND_COLOR);
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
				draggedCells.add(cell);
			}
		}
	
		@Override
		public void mouseReleased(MouseEvent e) {
			draggedCells.removeAll(draggedCells);
		}
		
	}

}
