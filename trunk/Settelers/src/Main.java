import java.util.*;


public class Main {
    private static final int maxTurns = 6;

    private static final Map<Integer, Double> turnStates = new HashMap<Integer, Double>();

    private static Set<State> nextStates(final Queue<State> current) {
		final Set<State> nextStates = new HashSet<State>();
		for (final State s : current) {
		    if (s.getTurn() >= maxTurns) {
			// ignore
		    } else if (s.isWinning()) {
		    	record(s);
		    } else {
				for (final State rollState : s.generateRollStates()) if (rollState.getTurn() < maxTurns) {
				    for (final State playState : rollState.generatePlayStates()) if (playState.getTurn() < maxTurns) {
					nextStates.add(playState);
				    }
				}
		    }
		}
		return nextStates;
    }

    public static void record(final State s) {
	if (!turnStates.containsKey(s.getTurn())) {
	    turnStates.put(s.getTurn(), s.getProbability());
	}
	turnStates.put( s.getTurn(), turnStates.get(s.getTurn()) + s.getProbability() );
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
		Board board = new Board();
		Settlement s1 = new Settlement( board.getPoint( 3, 3 ) );
		Settlement s2 = new Settlement( board.getPoint( 4, 5 ) );
		State start = new State ( s1, s2, new Road(s1, board.getPoint(2, 4)), new Road(s2, board.getPoint(3,5)), board);
	
		Queue<State> current = new LinkedList<State>();
		current.add(start);
	
		while (!current.isEmpty()) {
		    for (final State s: current) {
		    	System.out.println(s);
		    }
		    current.addAll(current);
		}
		
		System.out.println(turnStates);
    }

}
