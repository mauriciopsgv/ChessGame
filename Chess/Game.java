package Chess;

import java.util.ArrayList;
import java.util.HashMap;

import Graphics.MainWindow;
import Graphics.ScreenComponent;

public class Game {
	
	private Board chessBoard;
	
	private HashMap<Integer, Piece> pieces;
	
	private MainWindow window;
	
	public static void main(String[] args) {
		MainWindow window = new MainWindow();
		Game game = new Game(window);
		game.newGame();
	}
	
	public Game(MainWindow window) {
		this.window = window;
		clearGame();
	}
	
	private void clearGame() {
		chessBoard = new Board();
		pieces = new HashMap<Integer, Piece>();
	}
	
	public void newGame() {
		clearGame();
		window.newGame(this);
		
		// TODO: Make this function not ugly
		
		// Adding board
		window.addComponentToCanvas(chessBoard);
		
		// Adding Pieces
		ArrayList<ScreenComponent> initialPieces = new ArrayList<ScreenComponent>();

		// Black side
		
		// Initialize
		for (int i = 0; i < 8; i++) {
			Pawn p = new Pawn(Side.BLACK, 1, i);
			pieces.put(p.getId(), p);
			initialPieces.add(p);
		}
		
		Rook r1w = new Rook(Side.BLACK, 0, 0);
		Rook r2w = new Rook(Side.BLACK, 0, 7);
		pieces.put(r1w.getId(), r1w);
		pieces.put(r2w.getId(), r2w);
		initialPieces.add(r1w);
		initialPieces.add(r2w);
		
		Knight k1w = new Knight(Side.BLACK, 0, 1);
		Knight k2w = new Knight(Side.BLACK, 0, 6);
		pieces.put(k1w.getId(), k1w);
		pieces.put(k2w.getId(), k2w);
		initialPieces.add(k1w);
		initialPieces.add(k2w);
		
		Bishop b1w = new Bishop(Side.BLACK, 0, 2);
		Bishop b2w = new Bishop(Side.BLACK, 0, 5);
		pieces.put(b1w.getId(), b1w);
		pieces.put(b2w.getId(), b2w);
		initialPieces.add(b1w);
		initialPieces.add(b2w);
		
		Queen qw = new Queen(Side.BLACK, 0, 3);
		pieces.put(qw.getId(), qw);
		initialPieces.add(qw);

		King kw = new King(Side.BLACK, 0, 4);
		pieces.put(kw.getId(), kw);
		initialPieces.add(kw);
		
		// White side
		
		// Pawn Row
		for (int i = 0; i < 8; i++) {
			Pawn p = new Pawn(Side.WHITE, 6, i);
			pieces.put(p.getId(), p);
			initialPieces.add(p);
		}
		
		Rook r1b = new Rook(Side.WHITE, 7, 0);
		Rook r2b = new Rook(Side.WHITE, 7, 7);
		pieces.put(r1b.getId(), r1b);
		pieces.put(r2b.getId(), r2b);
		initialPieces.add(r1b);
		initialPieces.add(r2b);
		
		Knight k1b = new Knight(Side.WHITE, 7, 1);
		Knight k2b = new Knight(Side.WHITE, 7, 6);
		pieces.put(k1b.getId(), k1b);
		pieces.put(k2b.getId(), k2b);
		initialPieces.add(k1b);
		initialPieces.add(k2b);
		
		Bishop b1b = new Bishop(Side.WHITE, 7, 2);
		Bishop b2b = new Bishop(Side.WHITE, 7, 5);
		pieces.put(b1b.getId(), b1b);
		pieces.put(b2b.getId(), b2b);
		initialPieces.add(b1b);
		initialPieces.add(b2b);
		
		Queen qb = new Queen(Side.WHITE, 7, 3);
		pieces.put(qb.getId(), qb);
		initialPieces.add(qb);
		
		King kb = new King(Side.WHITE, 7, 4);
		pieces.put(kb.getId(), kb);
		initialPieces.add(kb);
		
		for (Piece currentPiece : pieces.values()) {
	        chessBoard.putPieceAt(currentPiece.getId(), new Position(currentPiece.getRow(), currentPiece.getColumn()));
		}
		
		window.copyComponentsToCanvas(initialPieces);
		window.repaint();
	}
	
	private boolean areEnemiePieces(int pieceId1, int pieceId2) {
		Piece piece1 = (Piece) pieces.get(pieceId1);
		Piece piece2 = (Piece) pieces.get(pieceId2);
		return piece1.getSide() != piece2.getSide();
	}
		
	private boolean isAnyPieceOnTheWay(Position startingPositionOriginal, Position endPositionOriginal) {
		if (Position.isSamePosition(startingPositionOriginal, endPositionOriginal)) {
			return false;
		} 
		if (Position.isVertical(startingPositionOriginal, endPositionOriginal)) {
			Position startingPosition = new Position(startingPositionOriginal.row, startingPositionOriginal.column);
			Position endPosition = new Position(endPositionOriginal.row, endPositionOriginal.column);
			if (endPosition.row < startingPosition.row) {
				Position swapPosition = startingPosition;
				startingPosition = endPosition;
				endPosition = swapPosition;
			}
			startingPosition.row++;
			for (Position currentPosition = startingPosition; !Position.isSamePosition(currentPosition, endPosition); currentPosition.row++) {
				if (chessBoard.isCellOccupied(currentPosition)) {
					return true;
				}
			}
								
		} 
		if (Position.isHorizontal(startingPositionOriginal, endPositionOriginal)) {
			Position startingPosition = new Position(startingPositionOriginal.row, startingPositionOriginal.column);
			Position endPosition = new Position(endPositionOriginal.row, endPositionOriginal.column);
			if (endPosition.column < startingPosition.column) {
				Position swapPosition = startingPosition;
				startingPosition = endPosition;
				endPosition = swapPosition;
			}
			startingPosition.column++;
			for (Position currentPosition = startingPosition; !Position.isSamePosition(currentPosition, endPosition); currentPosition.column++) {
				if (chessBoard.isCellOccupied(currentPosition)) {
					return true;
				}
			}
			
		} 
		if (Position.isDiagonal(startingPositionOriginal, endPositionOriginal)) {
			return false;
//			Create two different modes to define how to translate
//			if (endPosition.row < startingPosition.row) {
//				Position swapPosition = startingPosition;
//				startingPosition = endPosition;
//				endPosition = swapPosition;
//			}
//			startingPosition.row++;
//			startingPosition.column++;
//			for (Position currentPosition = startingPosition; currentPosition != endPosition; currentPosition.row++, currentPosition.column++) {
//				if (chessBoard.isCellOccupied(currentPosition)) {
//					return true;
//				}
//			}
		}
		return false;
	}
	
	private boolean isPossibleToMoveToCell(Piece pieceToBeMoved, Position startingPosition, Position endPosition) {
		if (pieceToBeMoved.canMoveTo(endPosition) && !isAnyPieceOnTheWay(startingPosition, endPosition)) {
			if (!chessBoard.isCellOccupied(endPosition) || 
				areEnemiePieces(chessBoard.getSelectedPieceId(), chessBoard.getCellPieceId(endPosition))) {
				return true;
			}
		}
		return false;
	}
	
	private void highlightPossibleMovements(Position selectedPosition) {
		if (chessBoard.isCellOccupied(selectedPosition)) {
			Piece selectedPiece = pieces.get(chessBoard.getSelectedPieceId());
			for (int i = 0; i < chessBoard.getBoardDimension(); i++) {
				for (int j = 0; j < chessBoard.getBoardDimension(); j++) {
					Position possibleHighlightedPosition = new Position(i,j);
					if (isPossibleToMoveToCell(selectedPiece, selectedPosition, possibleHighlightedPosition)) {
						chessBoard.highlightCell(possibleHighlightedPosition);
					}
				}
			}
		}
	}
	
	public void clickOnCell(Position newPosition) {
		if (chessBoard.isAnyCellSelected()) {
			if (chessBoard.isSelectedCellOccupied()) {
				Piece pieceToBeMoved = pieces.get(chessBoard.getSelectedPieceId());
				if (chessBoard.isCellOccupied(newPosition)) {
					int selectedPieceId = chessBoard.getSelectedPieceId();
					int targetPieceId = chessBoard.getCellPieceId(newPosition);
					if (areEnemiePieces(selectedPieceId, targetPieceId) && pieceToBeMoved.canCapturePiece(newPosition)) {
						pieceToBeMoved.capturePiece(newPosition);
						Piece pieceToBeRemoved = pieces.remove(targetPieceId);
						window.removeComponentFromCanvas(pieceToBeRemoved);
						chessBoard.movePieceTo(chessBoard.getSelectedPosition(), newPosition);	
					}
				} else {
					if (pieceToBeMoved.movePiece(newPosition)) {
						chessBoard.movePieceTo(chessBoard.getSelectedPosition(), newPosition);	
					}
				}
			}
			chessBoard.deselectCell();
		} else {
			chessBoard.selectCell(newPosition);
			highlightPossibleMovements(newPosition);
		}
	}
}
