package Graphics;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import Chess.Board;
import Chess.Game;

public class MainWindow extends JFrame {
	
	private Canvas canvas;
	
	public MainWindow() {
        super("Chess Game");
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setSize(800, 800);
        
        canvas = new Canvas();
		Game chessGame = new Game();
		newGame(chessGame);
		
        this.setContentPane(canvas);
        super.pack();
        super.setVisible(true);
	}
	
	public void newGame(Game game) {
		this.canvas.newGame(game);
		game.newGame(this);
	}
	
	public boolean addComponentToCanvas(ScreenComponent component) {
		 return this.canvas.addComponent(component);
	}
	
	public void repaint() {
		this.canvas.repaint();
	}

	public static void main(String[] args) {
		MainWindow window = new MainWindow();
		window.repaint();
	}
}
