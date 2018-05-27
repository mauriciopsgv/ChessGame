package Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import Chess.Board;
import Chess.King;
import Chess.Queen;
import Chess.Rook;
import Chess.Bishop;
import Chess.Knight;
import Chess.Pawn;
import Chess.Piece.Side;

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
		
		Side specialControl = Side.WHITE;
		Side pawnControl = Side.WHITE;
		Side kqControl = Side.WHITE;
		for(int i = 0; i < 16; i++) {
			
			if(i == 8) pawnControl = Side.BLACK;
			if(i == 2) specialControl = Side.BLACK;
			if(i == 1) kqControl = Side.BLACK;
			
			if(i < 4) {
				
				Rook r = new Rook(specialControl);
				Knight kn = new Knight(specialControl);
				Bishop b = new Bishop(specialControl);
				window.addComponentToCanvas(r);
				window.addComponentToCanvas(kn);
				window.addComponentToCanvas(b);
				if(i < 2) {
					
					King k = new King(kqControl);
					Queen q = new Queen(kqControl);
					window.addComponentToCanvas(k);
					window.addComponentToCanvas(q);	
				}
			}
			
			Pawn p = new Pawn(pawnControl);
			window.addComponentToCanvas(p);
		}
	}
}
