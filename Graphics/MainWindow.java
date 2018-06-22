package Graphics;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import Chess.Game;

public class MainWindow extends JFrame {
	
	private Canvas canvas;
	
	private int mainWindowWidth = 800;
	private int mainWindowHeight = 800;

	public MainWindow() {
        super("Chess Game");
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setSize(mainWindowWidth, mainWindowHeight);
        
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
	
	public void triggerAlert(String alertMessage) {
		int alertWidth = 300;
		int alertHeight = 100;
		JFrame alertPanel = new JFrame();

		JLabel alertLabel = new JLabel(alertMessage);
		alertLabel.setHorizontalAlignment(SwingConstants.CENTER);
		alertLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		alertPanel.add(alertLabel);

		alertPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		alertPanel.setSize(alertWidth, alertHeight);
		alertPanel.setLocation((mainWindowWidth - alertWidth)/2, (mainWindowHeight - alertHeight)/2);
		alertPanel.setVisible(true);
	}

	public void repaint() {
		canvas.repaint();
	}
}
