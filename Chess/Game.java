package Chess;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Observable;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import Graphics.InitialWindow;
import Graphics.MainWindow;
import Graphics.ScreenComponent;
import Graphics.Threat;
import Interface.Operation;

public class Game extends Observable {
	
	private Board chessBoard;
	
	private PieceManager pieces;
	
	private TurnManager turnManager;
		
	private InitialWindow initWindow;
		
	private void clearGame() {
		chessBoard = new Board();
		pieces = new PieceManager();
		turnManager = TurnManager.getInstance();
	}
	
	public void newGame() {
		initWindow = new InitialWindow();
		initWindow.addWindowListener(new WindowAdapter() {
               public void windowClosing(WindowEvent we) {
            	   ArrayList<Piece> loadedPieces = initWindow.getLoadedPieces();
                   initGame(loadedPieces);
               }
           });
	}
	
	private void initGame(ArrayList<Piece> loadedPieces) {
		
		clearGame();
		new MainWindow(this);
		
		// Adding board
		this.setChanged();
		this.notifyObservers(new Operation("AddComponent", chessBoard));
		
		// Adding Pieces
		ArrayList<ScreenComponent> initialPieces = new ArrayList<ScreenComponent>();
		
 	   if(loadedPieces.size() != 0) {
 		   // Load game as it is
 		   for(Piece sc : loadedPieces)
 		   {
 			   if(sc instanceof King) {
 				   King k = (King) sc;
 				   if(k.getSide() == Side.BLACK) {
 					   pieces.putBlackKing(k);
 				   }
 				   else {
 					   pieces.putWhiteKing(k);
 				   }
 		 		   initialPieces.add(k);
 			   }
 			   else if(sc instanceof Queen) {
 				   Queen q = (Queen) sc;
		 		   pieces.put(q);
		 		   initialPieces.add(q);
 			   }
 			   else if(sc instanceof Rook) {
 				  Rook q = (Rook) sc;
		 		   pieces.put(q);
		 		   initialPieces.add(q);
 			   }
 			   else if(sc instanceof Bishop) {
 				  Bishop q = (Bishop) sc;
		 		   pieces.put(q);
		 		   initialPieces.add(q);
 			   }
 			   else if(sc instanceof Knight) {
 				  Knight q = (Knight) sc;
		 		   pieces.put(q);
		 		   initialPieces.add(q);
 			   }
 			   else if(sc instanceof Pawn) {
 				  Pawn q = (Pawn) sc;
		 		   pieces.put(q);
		 		   initialPieces.add(q);
 			   }
 		   }
 	   }
 	   
 	   else {
 			// Black side
 		  TurnManager.getInstance().setCurrentSide(Side.WHITE);
 		  
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
 	   }
 	   		
		for (Piece currentPiece : pieces.whitePieces()) {
	        chessBoard.putPieceAt(currentPiece.getId(), new Position(currentPiece.getRow(), currentPiece.getColumn()));
		}
		
		for (Piece currentPiece : pieces.blackPieces()) {
	        chessBoard.putPieceAt(currentPiece.getId(), new Position(currentPiece.getRow(), currentPiece.getColumn()));
		}
		
		this.setChanged();
		this.notifyObservers(new Operation("CopyComponent", initialPieces));
	}
	
	private boolean areEnemiePieces(int pieceId1, int pieceId2) {
		Piece piece1 = (Piece) pieces.get(pieceId1);
		Piece piece2 = (Piece) pieces.get(pieceId2);
		return piece1.getSide() != piece2.getSide();
	}
		
	private boolean isAnyPieceOnTheWay(Board chessBoard, Position startingPositionOriginal, Position endPositionOriginal) {
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
			return pieceToBeMoved.canCapturePiece(endPosition) && !isAnyPieceOnTheWay(chessBoard, startingPosition, endPosition) &&
					areEnemiePieces(chessBoard.getSelectedPieceId(), chessBoard.getCellPieceId(endPosition));
		}
		return pieceToBeMoved.canMoveTo(endPosition) && !isAnyPieceOnTheWay(chessBoard, startingPosition, endPosition);
	}
	
	private boolean isSafeToMoveToCell(Position startingPosition, Position endPosition) {
		Board possibleBoard = new Board(chessBoard);
		PieceManager possiblePieces = new PieceManager(pieces);
		Piece pieceToBeMoved = possiblePieces.get(possibleBoard.getCellPieceId(startingPosition));

		if (chessBoard.isCellOccupied(endPosition)) {
			pieceToBeMoved.capturePiece(endPosition);
			possiblePieces.remove(chessBoard.getCellPieceId(endPosition));
		} else {
			pieceToBeMoved.movePiece(endPosition);
			possiblePieces.recordLastMovedPiece(pieceToBeMoved);
		}
		possibleBoard.movePieceTo(startingPosition, endPosition);
		
		return !isKingOnCheck(turnManager.getCurrentSide(), possiblePieces, possibleBoard);
	}
			
	private void highlightPossibleMovements(Position selectedPosition) {
		if (chessBoard.isCellOccupied(selectedPosition)) {
			Piece selectedPiece = pieces.get(chessBoard.getSelectedPieceId());
			for (int i = 0; i < chessBoard.getBoardDimension(); i++) {
				for (int j = 0; j < chessBoard.getBoardDimension(); j++) {
					Position possibleHighlightedPosition = new Position(i,j);
					if (isPossibleToMoveToCell(selectedPiece, selectedPosition, possibleHighlightedPosition) &&
						isSafeToMoveToCell(selectedPosition, possibleHighlightedPosition)) {
						chessBoard.highlightCell(possibleHighlightedPosition);
					}
				}
			}
			// Insira highlight do menino Roque aqui	
		}
	}
	
	private boolean isKingOnCheck(Side side, PieceManager pieces, Board chessBoard) {
		Side opponentSide = (side == Side.WHITE) ? Side.BLACK : Side.WHITE;
		Piece king = pieces.getKing(side);
		for (Piece opponentPiece : pieces.getPieces(opponentSide)) {
			if (!isAnyPieceOnTheWay(chessBoard, opponentPiece.getPosition(), king.getPosition()) &&
				opponentPiece.canCapturePiece(king.getPosition())) {
				return true;
			}
		}
		return false;
	}
	
	private boolean isPossibleToMoveToCellMate(Piece pieceToBeMoved, Position startingPosition, Position endPosition) {
		if (pieceToBeMoved.canMoveTo(endPosition) && !isAnyPieceOnTheWay(chessBoard, startingPosition, endPosition)) {
			if (!chessBoard.isCellOccupied(endPosition) || 
				areEnemiePieces(chessBoard.getCellPieceId(pieceToBeMoved.getPosition()), chessBoard.getCellPieceId(endPosition))) {
				return true;
			}
		}
		if(startingPosition.row == endPosition.row &&
				startingPosition.column == endPosition.column) {
			return true;
		}
		return false;
	}
	
	private Threat isKingThreated(Side side) {
		
		Side opponentSide = (side == Side.WHITE) ? Side.BLACK : Side.WHITE;
		Piece king = pieces.getKing(side);
		Position kingPosition = king.getPosition();
		ArrayList<Position> possibleMovements = new ArrayList<Position>();
		ArrayList<Position> pMF = new ArrayList<Position>();
		HashMap<Position, Integer> riskPositions = new HashMap<Position,Integer>();
		
		possibleMovements.add(new Position(kingPosition));
		possibleMovements.add(new Position(kingPosition.row, kingPosition.column + 1));
		possibleMovements.add(new Position(kingPosition.row, kingPosition.column - 1));
		possibleMovements.add(new Position(kingPosition.row + 1, kingPosition.column));
		possibleMovements.add(new Position(kingPosition.row + 1, kingPosition.column + 1));
		possibleMovements.add(new Position(kingPosition.row + 1, kingPosition.column - 1));
		possibleMovements.add(new Position(kingPosition.row - 1, kingPosition.column));
		possibleMovements.add(new Position(kingPosition.row - 1, kingPosition.column + 1));
		possibleMovements.add(new Position(kingPosition.row - 1, kingPosition.column - 1));
		
		for(Position pos : possibleMovements) {
			if(pos.row >= 0 && pos.column >= 0 && pos.row < 8 && pos.column < 8
					&& isPossibleToMoveToCellMate(king, kingPosition, pos)) {
				pMF.add(pos);
			}
		}
		
		int pMS = pMF.size();
		int pMC = 0;
		int drown = 0;
		for (Piece opponentPiece : pieces.getPieces(opponentSide)) {
			for(Position pos : pMF) {
				if(pos.row >= 0 && pos.column >= 0 && pos.row < 8 && pos.column < 8) {
					if (isPossibleToMoveToCellMate(king, kingPosition, pos)) {
						if(!isAnyPieceOnTheWay(chessBoard, opponentPiece.getPosition(), pos) &&
								opponentPiece.canCapturePiece(pos)) {
							riskPositions.put(pos,0);
						}
					}
				}
			}
			
			if(!isAnyPieceOnTheWay(chessBoard, opponentPiece.getPosition(),kingPosition)
					&& opponentPiece.canCapturePiece(kingPosition)) {
					drown++;
				}
		}
		
		pMC = riskPositions.size();
		// Maior igual porque uma casa pode ser ameaçada por mais de uma peça
		if(pMC == (pMS-1) && pMC != 0 && drown == 0) return Threat.DROWNING;
		else if(pMC == pMS && drown > 0) return Threat.CHECKMATE;
		else if(pMC < pMS && pMC != 0 && drown > 0) return Threat.CHECK;
		return Threat.SAFE;
	}
	
	
	//TODO: Missing highlight for possible castling
	//TODO: Not checking for xeque after movement
	private boolean canDoCastling(Piece king, Piece rook) {
		if (!(king instanceof King) || !(rook instanceof Rook)) {
			return false;
		}
		return !king.hasMove() && !rook.hasMove() && !isKingOnCheck(king.getSide(), pieces, chessBoard) &&
			   !isAnyPieceOnTheWay(chessBoard, king.getPosition(), rook.getPosition());
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
	
	public boolean promotePawn(String promotedClass) {
		Piece lastMovedPiece = pieces.getLastMovedPiece();
		if (shouldPromotePawn(lastMovedPiece)) {
			Piece newPiece;
			pieces.remove(lastMovedPiece.getId());
			if (promotedClass == "Bishop") {
				newPiece = new Bishop(lastMovedPiece.getSide(), lastMovedPiece.getRow(), lastMovedPiece.getColumn());
			} else if (promotedClass == "Knight") {
				newPiece = new Knight(lastMovedPiece.getSide(), lastMovedPiece.getRow(), lastMovedPiece.getColumn());
			} else if (promotedClass == "Queen") {
				newPiece = new Queen(lastMovedPiece.getSide(), lastMovedPiece.getRow(), lastMovedPiece.getColumn());
			} else if (promotedClass == "Rook") {
				newPiece = new Rook(lastMovedPiece.getSide(), lastMovedPiece.getRow(), lastMovedPiece.getColumn());
			} else {
				newPiece = new Pawn(lastMovedPiece.getSide(), lastMovedPiece.getRow(), lastMovedPiece.getColumn());
			}
			pieces.put(newPiece);
			chessBoard.putPieceAt(newPiece.getId(), newPiece.getPosition());
			
			this.setChanged();
			this.notifyObservers(new Operation("RemoveComponent", lastMovedPiece));
			this.setChanged();
			this.notifyObservers(new Operation("AddComponent", newPiece));
			return true;
		}
		return false;
	}

	public void clickOnCell(Position newPosition) {
		Side currentOpponentSideTurn = turnManager.getCurrentOpponentSide();
		if (chessBoard.isAnyCellSelected()) {
			if (chessBoard.isSelectedCellOccupied()) { // There is a piece to be moved in the selected cell
				Piece pieceToBeMoved = pieces.get(chessBoard.getSelectedPieceId());
				if (turnManager.isPieceAllowedToMove(pieceToBeMoved) ) {
					if (chessBoard.isCellOccupied(newPosition)) { // There is a piece in the position where you want to move
						int selectedPieceId = chessBoard.getSelectedPieceId();
						int targetPieceId = chessBoard.getCellPieceId(newPosition);
						if (areEnemiePieces(selectedPieceId, targetPieceId) && 
								isSafeToMoveToCell(chessBoard.getSelectedPosition(), newPosition) && 
								pieceToBeMoved.canCapturePiece(newPosition)) { // Tenta comer a peça
							pieceToBeMoved.capturePiece(newPosition);
							Piece pieceToBeRemoved = pieces.remove(targetPieceId);
							this.setChanged();
							this.notifyObservers(new Operation("RemoveComponent", pieceToBeRemoved));
							chessBoard.movePieceTo(chessBoard.getSelectedPosition(), newPosition);
							pieces.recordLastMovedPiece(pieceToBeMoved);
							if (shouldPromotePawn(pieceToBeMoved)) {
								this.setChanged();
								this.notifyObservers(new Operation("showPromotePawnMenu", null));
							}
							turnManager.finishTurn();
						}
						else {
							Piece targetPiece = pieces.get(targetPieceId);
							if (canDoCastling(pieceToBeMoved, targetPiece)) {
								doCastling(pieceToBeMoved, targetPiece);
								pieces.recordLastMovedPiece(targetPiece);
								turnManager.finishTurn();
							}
						}
						
					} else {
						if (!isAnyPieceOnTheWay(chessBoard, pieceToBeMoved.getPosition(), newPosition) &&
								isSafeToMoveToCell(chessBoard.getSelectedPosition(), newPosition) &&
								pieceToBeMoved.movePiece(newPosition)) {
							chessBoard.movePieceTo(chessBoard.getSelectedPosition(), newPosition);
							pieces.recordLastMovedPiece(pieceToBeMoved);
							if (shouldPromotePawn(pieceToBeMoved)) {
								this.setChanged();
								this.notifyObservers(new Operation("showPromotePawnMenu", null));
							}
							turnManager.finishTurn();
						}
					}
				}
			}
			chessBoard.deselectCell();
			//TODO: xeque only makes sense to one side each turn
			Threat kingStatus = this.isKingThreated(currentOpponentSideTurn);
			if(kingStatus != Threat.SAFE) {
				if(kingStatus == Threat.CHECKMATE || kingStatus == Threat.DROWNING) {
					String side;
					String message;
					Side actualSide = turnManager.getCurrentOpponentSide();
					if(actualSide == Side.WHITE) side = "branco";
					else side = "preto";
					
					if(kingStatus == Threat.CHECKMATE)
						message = "Lado " + side + " é o vencedor! Deseja começar outra partida?";
					else
						message = "Empate por congelamento! Deseja começar outra partida?";
					
					int resp = JOptionPane.showConfirmDialog(null, message);
					if(resp == JOptionPane.YES_OPTION) {
						this.setChanged();
						this.notifyObservers(new Operation("dispose", null));
						initWindow.dispose();
						newGame();
					}
					else if(resp == JOptionPane.NO_OPTION || resp == JOptionPane.CANCEL_OPTION) {
						this.setChanged();
						this.notifyObservers(new Operation("dispose", null));
						initWindow.dispose();
					}
				}
				else if(kingStatus == Threat.CHECK){
					this.setChanged();
					this.notifyObservers(new Operation("triggerAlert", "Cheque"));
				}
			}
		} else {
			Piece possiblePieceToSelect = pieces.get(chessBoard.getCellPieceId(newPosition));
			if (possiblePieceToSelect != null && turnManager.isPieceAllowedToMove(possiblePieceToSelect)) {
				chessBoard.selectCell(newPosition);
				highlightPossibleMovements(newPosition);
			}
		}
	}
	
	public void saveGame() {
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showSaveDialog(null);
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
	        try(FileWriter fw = new FileWriter(fc.getSelectedFile()+".txt")){
	        	
	        	Collection<Piece> whitePieces = pieces.getPieces(Side.WHITE);
	        	Collection<Piece> blackPieces = pieces.getPieces(Side.BLACK);
	        	
	        	fw.write("Side: ");
	        	if(turnManager.getCurrentSide() == Side.WHITE) {
	        		fw.write("White");
	        	}
	        	else {
	        		fw.write("Black");
	        	}
	        	fw.write(System.lineSeparator());
	        	
	        	for(Piece whitepiece : whitePieces)
	        	{
	        		fw.write("White ");
	        		Position pos = whitepiece.getPosition();
	        		if(whitepiece instanceof King) {
	        			fw.write("King ");
	        		}
	        		else if(whitepiece instanceof Queen) {
	        			fw.write("Queen ");
	        		}
	        		else if(whitepiece instanceof Rook) {
	        			fw.write("Rook ");
	        		}
	        		else if(whitepiece instanceof Bishop) {
	        			fw.write("Bishop ");
	        		}
	        		else if(whitepiece instanceof Knight) {
	        			fw.write("Knight ");
	        		}
	        		else if(whitepiece instanceof Pawn) {
	        			fw.write("Pawn ");
	        		}
	        		fw.write(Integer.toString(pos.row) + " " + Integer.toString(pos.column));
	        		fw.write(System.lineSeparator());
	        	}
	        	for(Piece blackpiece : blackPieces)
	        	{
	        		fw.write("Black ");
	        		Position pos = blackpiece.getPosition();
	        		if(blackpiece instanceof King) {
	        			fw.write("King ");
	        		}
	        		else if(blackpiece instanceof Queen) {
	        			fw.write("Queen ");
	        		}
	        		else if(blackpiece instanceof Rook) {
	        			fw.write("Rook ");
	        		}
	        		else if(blackpiece instanceof Bishop) {
	        			fw.write("Bishop ");
	        		}
	        		else if(blackpiece instanceof Knight) {
	        			fw.write("Knight ");
	        		}
	        		else if(blackpiece instanceof Pawn) {
	        			fw.write("Pawn ");
	        		}
	        		fw.write(Integer.toString(pos.row) + " " + Integer.toString(pos.column));
	        		fw.write(System.lineSeparator());
	        	}
	        	
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	    }
	}
	
}
