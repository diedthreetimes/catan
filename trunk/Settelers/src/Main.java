import java.util.*;


public class Main {
    private static final int maxTurns = 9;

    private static final Map<Integer, Double> winningProbabilites = new HashMap<Integer, Double>();

    private static Set<State> nextStates(final State s) {
		final Set<State> nextStates = new HashSet<State>();
		    if (s.getTurn() >= maxTurns) {
			// ignore
		    } else if (s.isWinning()) {
		    	record(s);
		    } else {
		    	List<State> rollStates = s.generateRollStates();
		    	
		    	for (final State rollState : rollStates) if (rollState.getTurn() < maxTurns) {
		    		List<State> playStates = rollState.generatePlayStates();
		    		if( playStates.isEmpty() )
		    			nextStates.add(rollState);
		    		while( !playStates.isEmpty() ){
		    			State next = playStates.remove(0);
		    			List<State> moreStates = next.generatePlayStates();
		    			if (moreStates.isEmpty())
		    				nextStates.add(next);
		    			
		    			playStates.addAll( moreStates );
		    		}
		    	}
		    }
		return nextStates;
    }

    public static void record(final State s) {
    	if (!winningProbabilites.containsKey(s.getTurn())) {
	    	winningProbabilites.put(s.getTurn(), s.getProbability());
		}
		winningProbabilites.put( s.getTurn(), winningProbabilites.get(s.getTurn()) + s.getProbability() );
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
		Board board = new Board();
		Settlement s1 = new Settlement( board.getPoint( 1, 4 ) );
		Settlement s2 = new Settlement( board.getPoint( 4, 4 ) );
		
		Weight weights = new Weight();
		
		State start = new State ( s1, s2, new Road(s1, board.getPoint(2, 4)), new Road(s2, board.getPoint(3,5)), board, weights);
	
		Stack<State> current = new Stack<State>();
		current.add(start);
	
		int i = 0;
		while (!current.isEmpty()) {
			State next = current.pop();
			if( (i % 100000) == 0 ){
				System.out.println(winningProbabilites);
				System.out.println(next);
			}
		    current.addAll( nextStates(next) );
		    i++;
		}
		
		System.out.println(winningProbabilites);
    }

}
