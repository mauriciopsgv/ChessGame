package Chess;

import Chess.Side;

public class Pawn extends Piece {

	public Pawn(Side side, int row, int column) {
		super(IdGenerator.generateId(), side, row, column);
		if (side == Side.WHITE) {
			super.loadImage("src/CyanP.png");
		}
		else {
			super.loadImage("src/PurpleP.png");
		}
	}
	
	private boolean isAbove(Position newPosition) {
		return newPosition.row - getRow() < 0;
	}
	
	private boolean isBelow(Position newPosition) {
		return newPosition.row - getRow() > 0;
	}
	
	private boolean isRightDirection(Position newPosition) {
		if (side == Side.WHITE) {
			return isAbove(newPosition);
		}
		return isBelow(newPosition);
		
	}
	
	private boolean isInStartingPosition() {
		if (side == Side.WHITE) {
			return getRow() == 6;
		}
		return getRow() == 1;
	}
	
	@Override
	protected boolean canMoveTo(Position newPosition) {
		//TODO: finish peao movement
		int numberOfSquaresItCanMove = 1;
		if (!isSamePosition(newPosition)) {
			if (isInStartingPosition()) {
				numberOfSquaresItCanMove = 2;
			}
			return isVertical(newPosition) && isRightDirection(newPosition) && isNear(newPosition, numberOfSquaresItCanMove);
		}
		return false;
	}
	
	@Override
	protected boolean canCapturePiece(Position newPosition) {
		return isNear(newPosition, 1) && isRightDirection(newPosition) && isDiagonal(newPosition);
	}
}
