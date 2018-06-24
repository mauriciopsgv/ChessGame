package Chess;

public class TurnManager {

	private static Side currentPlayerTurn = Side.WHITE;
	
	public static void newGame() {
		currentPlayerTurn = Side.WHITE;
	}
	
	public static Side getCurrentSide() {
		return currentPlayerTurn;
	}
	
	public static Side getCurrentOpponentSide() {
		return (currentPlayerTurn == Side.WHITE) ? Side.BLACK : Side.WHITE;
	}
	
	public static void finishTurn() {
		currentPlayerTurn = getCurrentOpponentSide();
	}
	
	public static boolean isPieceAllowedToMove(Piece piece) {
		return piece.getSide() == currentPlayerTurn;
	}
	
	public static void setCurrentSide(Side side) {
		currentPlayerTurn = side;
	}
}
