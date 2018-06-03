package Chess;

public class IdGenerator {
	private static int currentId = 0;
	
	public static int generateId() {
		return currentId++;
	}
}
