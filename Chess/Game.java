package Chess;

import java.util.HashMap;

import Graphics.MainWindow;

public class Game {
	
	private Board chessBoard;
	
	private HashMap<Integer, Piece> pieces;
	
	public Game() {
		clearGame();
	}
	
	private void clearGame() {
		chessBoard = new Board();
		pieces = new HashMap<Integer, Piece>();
	}
	
	public void newGame(MainWindow window) {
		clearGame();
		
		// TODO: Make this function not ugly
		
		// Adding board
		window.addComponentToCanvas(chessBoard);
		
		// Black side
		
		// Initialize
		for (int i = 0; i < 8; i++) {
			Pawn p = new Pawn(Side.BLACK, 1, i);
			pieces.put(p.getId(), p);
			window.addComponentToCanvas(p);
		}
		
		Rook r1w = new Rook(Side.BLACK, 0, 0);
		Rook r2w = new Rook(Side.BLACK, 0, 7);
		pieces.put(r1w.getId(), r1w);
		pieces.put(r2w.getId(), r2w);
		window.addComponentToCanvas(r1w);
		window.addComponentToCanvas(r2w);
		
		Knight k1w = new Knight(Side.BLACK, 0, 1);
		Knight k2w = new Knight(Side.BLACK, 0, 6);
		pieces.put(k1w.getId(), k1w);
		pieces.put(k2w.getId(), k2w);
		window.addComponentToCanvas(k1w);
		window.addComponentToCanvas(k2w);
		
		Bishop b1w = new Bishop(Side.BLACK, 0, 2);
		Bishop b2w = new Bishop(Side.BLACK, 0, 5);
		pieces.put(b1w.getId(), b1w);
		pieces.put(b2w.getId(), b2w);
		window.addComponentToCanvas(b1w);
		window.addComponentToCanvas(b2w);
		
		Queen qw = new Queen(Side.BLACK, 0, 3);
		pieces.put(qw.getId(), qw);
		window.addComponentToCanvas(qw);
		
		King kw = new King(Side.BLACK, 0, 4);
		pieces.put(kw.getId(), kw);
		window.addComponentToCanvas(kw);
		
		// White side
		
		// Pawn Row
		for (int i = 0; i < 8; i++) {
			Pawn p = new Pawn(Side.WHITE, 6, i);
			pieces.put(p.getId(), p);
			window.addComponentToCanvas(p);
		}
		
		Rook r1b = new Rook(Side.WHITE, 7, 0);
		Rook r2b = new Rook(Side.WHITE, 7, 7);
		pieces.put(r1b.getId(), r1b);
		pieces.put(r2b.getId(), r2b);
		window.addComponentToCanvas(r1b);
		window.addComponentToCanvas(r2b);
		
		Knight k1b = new Knight(Side.WHITE, 7, 1);
		Knight k2b = new Knight(Side.WHITE, 7, 6);
		pieces.put(k1b.getId(), k1b);
		pieces.put(k2b.getId(), k2b);
		window.addComponentToCanvas(k1b);
		window.addComponentToCanvas(k2b);
		
		Bishop b1b = new Bishop(Side.WHITE, 7, 2);
		Bishop b2b = new Bishop(Side.WHITE, 7, 5);
		pieces.put(b1b.getId(), b1b);
		pieces.put(b2b.getId(), b2b);
		window.addComponentToCanvas(b1b);
		window.addComponentToCanvas(b2b);
		
		Queen qb = new Queen(Side.WHITE, 7, 3);
		pieces.put(qb.getId(), qb);
		window.addComponentToCanvas(qb);
		
		King kb = new King(Side.WHITE, 7, 4);
		pieces.put(kb.getId(), kb);
		window.addComponentToCanvas(kb);
		
		for (Piece currentPiece : pieces.values()) {
	        chessBoard.putPieceAt(currentPiece.getId(), new Position(currentPiece.getRow(), currentPiece.getColumn()));
		}		
	}
	
	public void clickOnCell(Position newPosition) {
		if (chessBoard.isAnyCellSelected()) {
			int selectedPieceId = chessBoard.getSelectedPieceId();
			if (selectedPieceId != -1) {
				Piece pieceToBeMoved = pieces.get(selectedPieceId);
				if (pieceToBeMoved.movePiece(newPosition)) {
					chessBoard.movePieceTo(chessBoard.getSelectedPosition(), newPosition);	
				}
			}
			chessBoard.deselectCell();
		} else {
			this.chessBoard.selectCell(newPosition);
		}
	}
}
