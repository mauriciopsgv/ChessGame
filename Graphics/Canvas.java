package Graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Canvas extends JPanel {
	
	private ScreenComponent components[];
	private int numberOfComponents;
	
	int cellHeight = 100; 
	int cellWidth = 100;
	
	private static int maxNumberOfComponents = 33;
	
	public Canvas() {
		components = new ScreenComponent[maxNumberOfComponents];
		numberOfComponents = 0;
		setPreferredSize(new Dimension(800,800));
	}
	
	public boolean addComponent(ScreenComponent component) {
		if (numberOfComponents == maxNumberOfComponents) {
			return false;
		}
		components[numberOfComponents] = component;
		numberOfComponents++;
		return true;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int componentIndex = 0; componentIndex < numberOfComponents; componentIndex++) {
			components[componentIndex].draw(g, cellHeight, cellWidth);
		}
	}

}
