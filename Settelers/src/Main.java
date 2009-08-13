import java.util.*;


public class Main {
    private static final int maxTurns = 6;

    private static final Map<Integer, Set<State>> turnStates = new HashMap<Integer, Set<State>>();

    private static Set<State> nextStates(final Set<State> current) {
	final Set<State> nextStates = new HashSet<State>();
	for (final State s : current) {
	    if (s.getTurn() >= maxTurns) {
		// ignore
	    } else if (s.isWinning()) {
		final State keeper = s.keepWinning();
		record(keeper);
		nextStates.add(keeper);
	    } else {
		for (final State rollState : s.generateRollStates()) if (rollState.getTurn() < maxTurns) {
		    record(rollState);
		    nextStates.add(rollState);
		    for (final State playState : rollState.generatePlayStates()) if (playState.getTurn() < maxTurns) {
			record(playState);
			nextStates.add(playState);
			nextStates.remove(rollState);
		    }
		}
	    }
	}
	return nextStates;
    }

    public static void record(final State s) {
	if (!turnStates.containsKey(s.getTurn())) {
	    turnStates.put(s.getTurn(), new HashSet<State>());
	}
	turnStates.get(s.getTurn()).add(s);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
	Board board = new Board();
	Settlement s1 = new Settlement( board.getPoint( 3, 3 ) );
	Settlement s2 = new Settlement( board.getPoint( 4, 5 ) );
	State start = new State ( s1, s2, new Road(s1, board.getPoint(2, 4)), new Road(s2, board.getPoint(3,5)), board);

	Set<State> current = new HashSet<State>();
	current.add(start);

	while (!current.isEmpty()) {
	    for (final State s: current) {
		System.out.println(s);
	    }
	    current = nextStates(current);
	}
    }

}
