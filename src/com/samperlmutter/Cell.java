package com.samperlmutter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

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
		cell.setBackground(getState());
		cell.setBorder(new LineBorder(Color.BLACK, 1));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	private void changeState() {
		cellState = !cellState;
		cell.repaint();
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
