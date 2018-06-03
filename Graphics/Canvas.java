package Graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import Chess.Board;

public class Canvas extends JPanel implements MouseListener {
	
	private Board background = null;
	private ScreenComponent components[];
	private int numberOfComponents;
	
	int cellHeight = 100; 
	int cellWidth = 100;
	
	private static int maxNumberOfComponents = 33;
	
	public Canvas() {
		components = new ScreenComponent[maxNumberOfComponents];
		numberOfComponents = 0;
		setPreferredSize(new Dimension(8*cellWidth,8*cellHeight));
		addMouseListener(this);
	}
	
	public boolean addComponent(ScreenComponent component) {
		if (numberOfComponents == maxNumberOfComponents) {
			return false;
		}
		components[numberOfComponents] = component;
		numberOfComponents++;
		return true;
	}
	
	public boolean addBoard(Board background) {
		if (this.background != null) {
			return false;
		}
		this.background = background;
		return true;
	}
	
	private int pixelXToColumn(int pixelX) {
		return pixelX/cellWidth;
	}
	
	private int pixelYToRow(int pixelY) {
		return pixelY/cellHeight;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			background.draw(g, cellHeight, cellWidth);
		}
		for (int componentIndex = 0; componentIndex < numberOfComponents; componentIndex++) {
			components[componentIndex].draw(g, cellHeight, cellWidth);
		}
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if (background != null) {
			background.clickOnCell(pixelYToRow(event.getY()), pixelXToColumn(event.getX()));
			this.repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	public void printMouseInfo(MouseEvent arg0) {
		System.out.println("getX = " + arg0.getX());
		System.out.println("getY = " + arg0.getY());
	}

}
