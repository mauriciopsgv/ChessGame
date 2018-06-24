package Chess;

public class TurnManager {

	private static final TurnManager turnManager = new TurnManager();
	
	public static TurnManager getInstance() {
		return turnManager;
	}
	
	private Side currentPlayerTurn = Side.WHITE;
	
	public void newGame() {
		currentPlayerTurn = Side.WHITE;
	}
	
	public Side getCurrentSide() {
		return currentPlayerTurn;
	}
	
	public Side getCurrentOpponentSide() {
		return (currentPlayerTurn == Side.WHITE) ? Side.BLACK : Side.WHITE;
	}
	
	public void finishTurn() {
		currentPlayerTurn = getCurrentOpponentSide();
	}
	
	public boolean isPieceAllowedToMove(Piece piece) {
		return piece.getSide() == currentPlayerTurn;
	}
	
	public void setCurrentSide(Side side) {
		currentPlayerTurn = side;
	}
}
