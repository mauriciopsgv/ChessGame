package Chess;

import Graphics.MainWindow;

public class Game {
	public void initializeGame(MainWindow window) {
		// White side
		
		// Pawn Row
		for (int i = 0; i < 8; i++) {
			Pawn p = new Pawn(Side.BLACK, 1, i);
			window.addComponentToCanvas(p);
		}
		
		Rook r1w = new Rook(Side.BLACK, 0, 0);
		Rook r2w = new Rook(Side.BLACK, 0, 7);
		window.addComponentToCanvas(r1w);
		window.addComponentToCanvas(r2w);
		
		Knight k1w = new Knight(Side.BLACK, 0, 1);
		Knight k2w = new Knight(Side.BLACK, 0, 6);
		window.addComponentToCanvas(k1w);
		window.addComponentToCanvas(k2w);
		
		Bishop b1w = new Bishop(Side.BLACK, 0, 2);
		Bishop b2w = new Bishop(Side.BLACK, 0, 5);
		window.addComponentToCanvas(b1w);
		window.addComponentToCanvas(b2w);
		
		Queen qw = new Queen(Side.BLACK, 0, 3);
		window.addComponentToCanvas(qw);
		
		King kw = new King(Side.BLACK, 0, 4);
		window.addComponentToCanvas(kw);
		
		// Black side
		
		// Pawn Row
		for (int i = 0; i < 8; i++) {
			Pawn p = new Pawn(Side.WHITE, 6, i);
			window.addComponentToCanvas(p);
		}
		
		Rook r1b = new Rook(Side.WHITE, 7, 0);
		Rook r2b = new Rook(Side.WHITE, 7, 7);
		window.addComponentToCanvas(r1b);
		window.addComponentToCanvas(r2b);
		
		Knight k1b = new Knight(Side.WHITE, 7, 1);
		Knight k2b = new Knight(Side.WHITE, 7, 6);
		window.addComponentToCanvas(k1b);
		window.addComponentToCanvas(k2b);
		
		Bishop b1b = new Bishop(Side.WHITE, 7, 2);
		Bishop b2b = new Bishop(Side.WHITE, 7, 5);
		window.addComponentToCanvas(b1b);
		window.addComponentToCanvas(b2b);
		
		Queen qb = new Queen(Side.WHITE, 7, 3);
		window.addComponentToCanvas(qb);
		
		King kb = new King(Side.WHITE, 7, 4);
		window.addComponentToCanvas(kb);
	}
}
