import java.util.*;

/**
 * A State object is intended to be an immutable representation, suitable for a node
 * on a state diagram. Technicaly, this can be circumvented by manipulating the contents
 * of member Collection classes.
 */
public class State {
	/* Chance of rolling x - 2 */
	private static final double [] PR = {
                1.0/36.0, 2.0/36, 3.0/36, 4.0/36,
                5.0/36, 6.0/36, 5.0/36, 4.0/36,
                3.0/36, 2.0/36 , 1.0/36 };

	private final int turn;
	private final int points;
	private final Map<Card.Type, Integer> hand;
	private final List<City> cities;
	private final List<Settlement> settlements;
	private final List<Road> roads;
	private final Board map;
	private final double probability;
	
	
        /**
         * Should this take a Collection of settlements and a Collection of roads?
         */
	public State( Settlement s1, Settlement s2, Road r1, Road r2, Board m){
		turn = 0;
		points = 2;

                // ridiculously efficient, backed by an array
		hand = new EnumMap<Card.Type, Integer>(Card.Type.class);
		hand.put(Card.Type.ORE, 0);
		hand.put(Card.Type.SHEEP, 0);
		hand.put(Card.Type.WHEAT, 0);
		hand.put(Card.Type.WOOD, 0);
		hand.put(Card.Type.BRICK, 0);
		
		// LinkedList should only be used when frequent reordering is expected
		cities = new ArrayList<City>();
		
		// Should check validity of set
		settlements = new ArrayList<Settlement>();
		settlements.add( s1 );
		settlements.add( s2 );
		
		// and of the roads
		roads = new ArrayList<Road>();
		roads.add(r1);
		roads.add(r2);
		
		map = m;
		
		probability = 1;
	}

        /**
         * @see #nextTurn(double, Map) below for the only caller of this contructor.
         */
	private State( int t, int p, Map<Card.Type, Integer> H, List<City> C, List<Settlement> S, List<Road> R, Board m, double pr ){
		turn = t;
		points = p;
		hand = H;
		cities = C;
		settlements = S;
		roads = R;
		map = m;
		probability = pr;
	}

        /**
         * @param transitionProbability Probability of transitioning to this new state.
         * @param newHand Hand for the next stage.
         */
        private State nextTurn(final double transitionProbability, final Map<Card.Type, Integer> newHand) {
            return new State(turn + 1, this.points, newHand, this.cities,
                    this.settlements, this.roads, this.map, probability * transitionProbability);
        }
	
	public String toString(){
            return "Probability: " + this.probability + "\n\n" +
            "Turn: " + turn +"\n" +
            "Hand: " + hand + "\n";
	}

        /**
         * @return possible states as outcomes of a die roll.
         */
	public List<State> generateRollStates(){
            final List<State> ans = new ArrayList<State>(11);
            for( int i = 2; i<=12; i++ ){
                final Map<Card.Type, Integer> newHand = new EnumMap<Card.Type, Integer>(hand);
                for( final Settlement s : settlements){
                    for (final Card c : s.roll(i)) {
                        newHand.put(c.getType(), newHand.get(c.getType()) + 1);
                    }
                }
                for( final City cit : cities){
                    for (final Card c : cit.roll(i)) {
                        newHand.put(c.getType(), newHand.get(c.getType()) + 1);
                    }
                }
                
                ans.add(nextTurn(PR[i - 2], newHand));
            }
            return ans;
	}
	
        /**
         * @todo: implement
         * @return possible states as outcomes of "game play"
         */
	public List<State> generatePlayStates(){
		return null;
	}
}
