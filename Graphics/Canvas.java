package Graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import Chess.Game;
import Chess.Position;
import Interface.Operation;

public class Canvas extends JPanel implements MouseListener {
	
	public Game game;
	private ArrayList<ScreenComponent> components;
	
	int cellHeight = 100; 
	int cellWidth = 100;
	
	private static int maxNumberOfComponents = 33;
	
	public Canvas() {
		components = new ArrayList<ScreenComponent>();
		setPreferredSize(new Dimension(8*cellWidth,8*cellHeight));
		addMouseListener(this);
	}
	
	public boolean copyComponents(ArrayList<ScreenComponent> newComponents) {
		if (components.size() + newComponents.size() > maxNumberOfComponents) {
			return false;
		}
		for (ScreenComponent newComponent : newComponents) {
			if (!components.add(newComponent)) {
				return false;
			}
		}
		return true;
	}

	public boolean addComponent(ScreenComponent component) {
		if (components.size() >= maxNumberOfComponents) {
			return false;
		}
		return components.add(component);
	}
	
	public boolean removeComponent(ScreenComponent componentToBeRemoved) {
		return components.remove(componentToBeRemoved);
	}

	public void newGame(Game game) {
		this.game = game;
		this.game.addObserver(new Observer() {
            @SuppressWarnings("unchecked")
			public void update(Observable obj, Object arg) {
                Operation op = (Operation) arg;
            	if (op.operationName.equals("AddComponent")) {
            		addComponent((ScreenComponent) op.arg);
            		repaint();
            	} else if (op.operationName.equals("CopyComponent")) {
            		copyComponents((ArrayList<ScreenComponent>) op.arg);
            		repaint();
            	} else if (op.operationName.equals("RemoveComponent")) {
            		removeComponent((ScreenComponent) op.arg);
            		repaint();
            	}
            }
        });
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
		for(ScreenComponent component : components) {
			component.draw(g, cellHeight, cellWidth);
		}
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if (game != null) {
			game.clickOnCell(new Position(pixelYToRow(event.getY()), pixelXToColumn(event.getX())));
			this.repaint();
		}
		if(event.getButton() == MouseEvent.BUTTON3)
		{
			game.saveGame();
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
}
