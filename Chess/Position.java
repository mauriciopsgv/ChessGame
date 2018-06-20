package Chess;

public class Position {
	public int row;
	public int column;
	
	public Position(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public static boolean isSamePosition(Position pos1, Position pos2) {
		return pos1.row == pos2.row && pos1.column == pos2.column;
	}
	
	public static boolean isNear(Position pos1, Position pos2, int numSquares) {
		return Math.abs(pos1.column - pos2.column) <= numSquares &&
			Math.abs(pos1.row - pos2.row) <= numSquares;
	}
	
	public static boolean isVertical(Position pos1, Position pos2) {
		return (pos1.column - pos2.column) == 0;
	}
	
	public static boolean isHorizontal(Position pos1, Position pos2) {
		return (pos1.row - pos2.row) == 0;
	}
	
	public static boolean isDiagonal(Position pos1, Position pos2) {
		return Math.abs(pos1.row - pos2.row) == Math.abs(pos1.column - pos2.column);
	}
}
