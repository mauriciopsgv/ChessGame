package Chess;

import Chess.Side;
import Chess.Position;

public class Bishop extends Piece {

	public Bishop(Side side, int row, int column) {
		super(IdGenerator.generateId(), side, row, column);
		if (side == Side.WHITE) {
			super.loadImage("src/CyanB.png");
		}
		else {
			super.loadImage("src/PurpleB.png");
		}
	}
	
	private boolean canMoveTo(Position newPosition) {
		if (!isSamePosition(newPosition)) {
			return isDiagonal(newPosition);
		}
		return false;
	}
	
	@Override
	protected boolean movePiece(Position newPosition) {
		if (this.canMoveTo(newPosition)) {
			return super.movePiece(newPosition);
		}
		return false;
	}
}
