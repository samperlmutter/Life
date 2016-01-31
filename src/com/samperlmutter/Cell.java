package com.samperlmutter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

@SuppressWarnings("serial")
public class Cell extends JButton {
	
	public boolean cellState;
	private Cell cell;
	
	public Cell() {
		cell = this;
		cellState = false;
		cell.addActionListener(new ButtonListener());
		cell.setPreferredSize(new Dimension(Constants.CELL_DIMENSION, Constants.CELL_DIMENSION));
		cell.setBackground(Color.BLACK);
		cell.setBorder(new LineBorder(Color.WHITE, 0));
	}
	
	private void changeState() {
		cellState = !cellState;
		cell.setBackground(getState());
	}
	
	private Color getState() {
		return cellState ? Color.WHITE : Color.BLACK;
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() instanceof Cell) {
				((Cell) e.getSource()).changeState();
			}
		}
		
	}

}
