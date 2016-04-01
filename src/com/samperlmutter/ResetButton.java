package com.samperlmutter;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.plaf.metal.MetalButtonUI;


public class ResetButton extends JButton {
	public ResetButton() {
		setUI(new UI());
		addMouseListener(new ButtonListener());
		setText(Constants.RESET_BUTTON_NAME);
	}
	
	private void resetGrid() {
		for (List<Cell> row : Game.grid.getGrid()) {
			for (Cell cell : row) {
				cell.die();
			}
		}
	}
	
	private class ButtonListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
				
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			resetGrid();
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}

	}
	
	private class UI extends MetalButtonUI {
		@Override
		protected void paintButtonPressed(Graphics g, AbstractButton b) {
			
		}
		
	}
}
