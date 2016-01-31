package com.samperlmutter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.plaf.metal.MetalButtonUI;

public class Cell extends JButton {
	
	public boolean cellState;
	private Cell cell;
	
	public Cell() {
		cell = this;
		cellState = false;
		cell.addMouseListener(new ButtonListener());
		cell.setPreferredSize(new Dimension(Constants.CELL_DIMENSION, Constants.CELL_DIMENSION));
		cell.setBackground(Constants.DEAD_CELL_BACKGROUND_COLOR);
		cell.setBorder(new LineBorder(Constants.CELL_BORDER_COLOR, 0));
		cell.setUI(new UI());
	}
	
	private void changeState() {
		cellState = !cellState;
		cell.setBackground(getState());
	}
	
	private Color getState() {
		return cellState ? Constants.LIVE_CELL_BACKGROUND_COLOR : Constants.DEAD_CELL_BACKGROUND_COLOR;
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
//		if((e.getSource() instanceof Cell) && ((e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK)) {
//			((Cell) e.getSource()).changeState();
//		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getSource() instanceof Cell) {
			((Cell) e.getSource()).changeState();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
		
	}

}
