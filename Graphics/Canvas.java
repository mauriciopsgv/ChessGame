package Graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import Chess.Game;
import Chess.Position;

public class Canvas extends JPanel implements MouseListener {
	
	private Game game;
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
	
	public void newGame(Game game) {
		this.game = game;
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
		for (int componentIndex = 0; componentIndex < numberOfComponents; componentIndex++) {
			components[componentIndex].draw(g, cellHeight, cellWidth);
		}
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if (game != null) {
			game.clickOnCell(new Position(pixelYToRow(event.getY()), pixelXToColumn(event.getX())));
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
