import java.util.HashMap;
import java.util.List;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Board board = new Board();
		Settlement s1 = new Settlement( board.getPoint( 0, 0 ) );
		Settlement s2 = new Settlement( board.getPoint( 5, 5 ) );
		State start = new State ( s1, s2, new Road(s1, board.getPoint(0, 1)), new Road(s2, board.getPoint(5,6)), null );
		
		System.out.println(start);
		for( State s : start.generateRollStates() )
			System.out.println(s);
	}

}
