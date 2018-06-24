package Chess;

import java.util.Collection;
import java.util.HashMap;

public class PieceManager {

	private HashMap<Integer, Piece> whitePieces;
	private HashMap<Integer, Piece> blackPieces;
	
	private int blackKingId = -1;
	private int whiteKingId = -1;
	private int lastMovedPiece = -1;

	public PieceManager() {
		whitePieces = new HashMap<Integer, Piece>();
		blackPieces = new HashMap<Integer, Piece>();
	}
	
	public PieceManager(PieceManager copy) {
		whitePieces = new HashMap<Integer, Piece>();
		blackPieces = new HashMap<Integer, Piece>();
		for (Piece piece: copy.whitePieces.values()) {
			Piece pieceToAdd = getRightPieceClass(piece);
			whitePieces.put(piece.getId(), pieceToAdd);
		}
		for (Piece piece: copy.blackPieces.values()) {
			Piece pieceToAdd = getRightPieceClass(piece);
			blackPieces.put(piece.getId(), pieceToAdd);
		}
		blackKingId = copy.getBlackKing().getId();
		whiteKingId = copy.getWhiteKing().getId();
		Piece lastMovedPieceCopy = copy.getLastMovedPiece();
		if (lastMovedPieceCopy == null) {
			lastMovedPiece = -1;
		} else {
			lastMovedPiece = lastMovedPieceCopy.getId();
		}
	}
	
	private Piece getRightPieceClass (Piece piece) {
		if (piece instanceof Bishop) {
			return new Bishop(piece.getSide(), piece.getRow(), piece.getColumn());
		} else if (piece instanceof King) {
			return new King(piece.getSide(), piece.getRow(), piece.getColumn());
		} else if (piece instanceof Knight) {
			return new Knight(piece.getSide(), piece.getRow(), piece.getColumn());
		} else if (piece instanceof Pawn) {
			return new Pawn(piece.getSide(), piece.getRow(), piece.getColumn());
		} else if (piece instanceof Queen) {
			return new Queen(piece.getSide(), piece.getRow(), piece.getColumn());
		} else if (piece instanceof Rook) {
			return new Rook(piece.getSide(), piece.getRow(), piece.getColumn());
		}
		return new Piece(piece.getId() ,piece.getSide(), piece.getRow(), piece.getColumn());
	}

	public boolean put(Piece p) {
		if (p.getSide() == Side.WHITE) {
			whitePieces.put(p.getId(), p);
			return true;
		}
		else if (p.getSide() == Side.BLACK){
			blackPieces.put(p.getId(), p);
			return true;
		}
		return false;
	}
	
	public void putBlackKing(Piece p) {
		blackKingId = p.getId();
		blackPieces.put(blackKingId, p);
	}
	
	private Piece getBlackKing() {
		return blackPieces.get(blackKingId);
	}
	
	public void putWhiteKing(Piece p) {
		whiteKingId = p.getId();
		whitePieces.put(whiteKingId, p);
	}
	
	private Piece getWhiteKing() {
		return whitePieces.get(whiteKingId);
	}
	
	public Piece getKing(Side side) {
		if (side == Side.WHITE) {
			return getWhiteKing();
		}
		return getBlackKing();
	}

	public Piece get(int id) {
		Piece pieceToReturn = whitePieces.get(id);
		if (pieceToReturn == null) {
			return blackPieces.get(id);
		}
		return pieceToReturn;
	}

	public Piece remove(int id) {
		Piece pieceToReturn = whitePieces.remove(id);
		if (pieceToReturn == null) {
			return blackPieces.remove(id);
		}
		return pieceToReturn;
	}

	public Collection<Piece> whitePieces() {
		return whitePieces.values();
	}

	public Collection<Piece> blackPieces() {
		return blackPieces.values();
	}
	
	public Collection<Piece> getPieces(Side side) {
		if (side == Side.WHITE) {
			return whitePieces();
		}
		return blackPieces();
	}
	
	public void recordLastMovedPiece(Piece p) {
		lastMovedPiece = p.getId();
	}
	
	public Piece getLastMovedPiece() {
		return get(lastMovedPiece);
	}
}
