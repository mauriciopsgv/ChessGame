package Graphics;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import Chess.Game;

public class MainWindow extends JFrame {
	
	private Canvas canvas;
	
	public MainWindow() {
        super("Chess Game");
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setSize(800, 800);
        
        canvas = new Canvas();
		
        this.setContentPane(canvas);
        super.pack();
        super.setVisible(true);
	}
	
	public void newGame(Game game) {
		canvas.newGame(game);
	}

	public boolean copyComponentsToCanvas(ArrayList<ScreenComponent> newComponents) {
		return canvas.copyComponents(newComponents);
	}

	public boolean addComponentToCanvas(ScreenComponent component) {
		 return canvas.addComponent(component);
	}
	
	public boolean removeComponentFromCanvas(ScreenComponent component) {
		return canvas.removeComponent(component);
	}
	
	public void repaint() {
		canvas.repaint();
	}
}
