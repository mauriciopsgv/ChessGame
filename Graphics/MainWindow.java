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
        this.setContentPane(canvas);
        super.pack();
        super.setVisible(true);
	}
	
	public boolean addComponentToCanvas(ScreenComponent component) {
		 return this.canvas.addComponent(component);
	}

	public static void main(String[] args) {
		
		MainWindow window = new MainWindow();
		Board chessBoard = new Board();
		window.addComponentToCanvas(chessBoard);
		Game chessGame = new Game();
		chessGame.newGame(window);
	}
}
