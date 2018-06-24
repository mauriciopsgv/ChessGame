package Chess;

import java.util.ArrayList;
import java.util.HashMap;

import Graphics.MainWindow;
import Graphics.ScreenComponent;

public class Game {
	
	private Board chessBoard;
	
	private PieceManager pieces;
	
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
		pieces = new PieceManager();
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
			pieces.put(p);
			initialPieces.add(p);
		}
		
		Rook r1b = new Rook(Side.BLACK, 0, 0);
		Rook r2b = new Rook(Side.BLACK, 0, 7);
		pieces.put(r1b);
		pieces.put(r2b);
		initialPieces.add(r1b);
		initialPieces.add(r2b);
		
		Knight k1b = new Knight(Side.BLACK, 0, 1);
		Knight k2b = new Knight(Side.BLACK, 0, 6);
		pieces.put(k1b);
		pieces.put(k2b);
		initialPieces.add(k1b);
		initialPieces.add(k2b);
		
		Bishop b1b = new Bishop(Side.BLACK, 0, 2);
		Bishop b2b = new Bishop(Side.BLACK, 0, 5);
		pieces.put(b1b);
		pieces.put(b2b);
		initialPieces.add(b1b);
		initialPieces.add(b2b);
		
		Queen qb = new Queen(Side.BLACK, 0, 3);
		pieces.put(qb);
		initialPieces.add(qb);

		King kb = new King(Side.BLACK, 0, 4);
		pieces.putBlackKing(kb);
		initialPieces.add(kb);
		
		// White side
		
		// Pawn Row
		for (int i = 0; i < 8; i++) {
			Pawn p = new Pawn(Side.WHITE, 6, i);
			pieces.put(p);
			initialPieces.add(p);
		}
		
		Rook r1w = new Rook(Side.WHITE, 7, 0);
		Rook r2w = new Rook(Side.WHITE, 7, 7);
		pieces.put(r1w);
		pieces.put(r2w);
		initialPieces.add(r1w);
		initialPieces.add(r2w);
		
		Knight k1w = new Knight(Side.WHITE, 7, 1);
		Knight k2w = new Knight(Side.WHITE, 7, 6);
		pieces.put(k1w);
		pieces.put(k2w);
		initialPieces.add(k1w);
		initialPieces.add(k2w);
		
		Bishop b1w = new Bishop(Side.WHITE, 7, 2);
		Bishop b2w = new Bishop(Side.WHITE, 7, 5);
		pieces.put(b1w);
		pieces.put(b2w);
		initialPieces.add(b1w);
		initialPieces.add(b2w);
		
		Queen qw = new Queen(Side.WHITE, 7, 3);
		pieces.put(qw);
		initialPieces.add(qw);
		
		King kw = new King(Side.WHITE, 7, 4);
		pieces.putWhiteKing(kw);
		initialPieces.add(kw);
		
		for (Piece currentPiece : pieces.whitePieces()) {
	        chessBoard.putPieceAt(currentPiece.getId(), new Position(currentPiece.getRow(), currentPiece.getColumn()));
		}
		
		for (Piece currentPiece : pieces.blackPieces()) {
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
			Position startingPosition = new Position(startingPositionOriginal);
			Position endPosition = new Position(endPositionOriginal);
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
			Position startingPosition = new Position(startingPositionOriginal);
			Position endPosition = new Position(endPositionOriginal);
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
			Position startingPosition = new Position(startingPositionOriginal);
			Position endPosition = new Position(endPositionOriginal);

			if (endPosition.row < startingPosition.row) {
				Position swapPosition = startingPosition;
				startingPosition = endPosition;
				endPosition = swapPosition;
			}
			startingPosition.row++;

			if (startingPosition.column > endPosition.column) {
				startingPosition.column--;
				for (Position currentPosition = startingPosition; !Position.isSamePosition(currentPosition, endPosition); currentPosition.row++, currentPosition.column--) {
					if (chessBoard.isCellOccupied(currentPosition)) {
						return true;
					}
				}
			} else {
				startingPosition.column++;
				for (Position currentPosition = startingPosition; !Position.isSamePosition(currentPosition, endPosition); currentPosition.row++, currentPosition.column++) {
					if (chessBoard.isCellOccupied(currentPosition)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private boolean isPossibleToMoveToCell(Piece pieceToBeMoved, Position startingPosition, Position endPosition) {
		if (chessBoard.isCellOccupied(endPosition) ) {
			return pieceToBeMoved.canCapturePiece(endPosition) &&
					areEnemiePieces(chessBoard.getSelectedPieceId(), chessBoard.getCellPieceId(endPosition));
		}
		return pieceToBeMoved.canMoveTo(endPosition) && !isAnyPieceOnTheWay(startingPosition, endPosition);
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
			// Insira highlight do menino Roque aqui	
		}
	}
	
	private boolean isKingOnCheck(Side side) {
		Side opponentSide = (side == Side.WHITE) ? Side.BLACK : Side.WHITE;
		Piece king = pieces.getKing(side);
		for (Piece opponentPiece : pieces.getPieces(opponentSide)) {
			if (!isAnyPieceOnTheWay(opponentPiece.getPosition(), king.getPosition()) &&
				opponentPiece.canCapturePiece(king.getPosition())) {
				return true;
			}
		}
		return false;
	}
	
	//TODO: Missing highlight for possible castling
	//TODO: Not checking for xeque after movement
	private boolean canDoCastling(Piece king, Piece rook) {
		if (!(king instanceof King) || !(rook instanceof Rook)) {
			return false;
		}
		return !king.hasMove() && !rook.hasMove() && !isKingOnCheck(king.getSide()) &&
			   !isAnyPieceOnTheWay(king.getPosition(), rook.getPosition());
	}

	private void doCastling(Piece king, Piece rook) {
		Position kingOriginalPosition = new Position(king.getPosition());
		Position rookOriginalPosition = new Position(rook.getPosition());
		if (king.getPosition().column < rook.getPosition().column) {
			king.movePiece(new Position(king.getRow(), king.getColumn() + 1));
			king.movePiece(new Position(king.getRow(), king.getColumn() + 1));
			rook.movePiece(new Position(king.getRow(), king.getColumn() - 1));
		} else {
			king.movePiece(new Position(king.getRow(), king.getColumn() - 1));
			king.movePiece(new Position(king.getRow(), king.getColumn() - 1));
			rook.movePiece(new Position(king.getRow(), king.getColumn() + 1));
		}
		chessBoard.movePieceTo(kingOriginalPosition, king.getPosition());
		chessBoard.movePieceToAbsolute(rookOriginalPosition, rook.getPosition());
	}
	
	private boolean shouldPromotePawn(Piece pawn) {
		if (!(pawn instanceof Pawn)) {
			return false;
		}

		Position pawnNewPosition = pawn.getPosition();
		if (pawn.getSide() == Side.WHITE) {
			return pawnNewPosition.row == 0;
		} else if (pawn.getSide() == Side.BLACK) {
			return pawnNewPosition.row == 7;
		}
		return false;
	}

	public void clickOnCell(Position newPosition) {
		Side currentOpponentSideTurn = TurnManager.getCurrentOpponentSide();
		if (chessBoard.isAnyCellSelected()) {
			if (chessBoard.isSelectedCellOccupied()) { // There is a piece to be moved in the selected cell
				Piece pieceToBeMoved = pieces.get(chessBoard.getSelectedPieceId());
				if (TurnManager.isPieceAllowedToMove(pieceToBeMoved)) {
					if (chessBoard.isCellOccupied(newPosition)) { // There is a piece in the position where you want to move
						int selectedPieceId = chessBoard.getSelectedPieceId();
						int targetPieceId = chessBoard.getCellPieceId(newPosition);
						if (areEnemiePieces(selectedPieceId, targetPieceId) && pieceToBeMoved.canCapturePiece(newPosition)) { // Tenta comer a peça
							pieceToBeMoved.capturePiece(newPosition);
							Piece pieceToBeRemoved = pieces.remove(targetPieceId);
							window.removeComponentFromCanvas(pieceToBeRemoved);
							chessBoard.movePieceTo(chessBoard.getSelectedPosition(), newPosition);
							if (shouldPromotePawn(pieceToBeMoved)) {

							}
							TurnManager.finishTurn();
						}
						else {
							Piece targetPiece = pieces.get(targetPieceId);
							if (canDoCastling(pieceToBeMoved, targetPiece)) {
								doCastling(pieceToBeMoved, targetPiece);
								TurnManager.finishTurn();
							}
						}
						
					} else {
						if (!isAnyPieceOnTheWay(pieceToBeMoved.getPosition(), newPosition) && pieceToBeMoved.movePiece(newPosition)) {
							chessBoard.movePieceTo(chessBoard.getSelectedPosition(), newPosition);
							if (shouldPromotePawn(pieceToBeMoved)) {

							}
							TurnManager.finishTurn();
						}
					}
				}
			}
			chessBoard.deselectCell();
			//TODO: xeque only makes sense to one side each turn
			if (this.isKingOnCheck(currentOpponentSideTurn)) {
				window.triggerAlert("CHEQUE");
			}
		} else {
			Piece possiblePieceToSelect = pieces.get(chessBoard.getCellPieceId(newPosition));
			if (possiblePieceToSelect != null && TurnManager.isPieceAllowedToMove(possiblePieceToSelect)) {
				chessBoard.selectCell(newPosition);
				highlightPossibleMovements(newPosition);
			}
		}
	}
}
