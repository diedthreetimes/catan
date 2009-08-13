import java.util.*;

public class State {
	private int turn;
	private int points;
	private final Map<Card.Type, Integer> hand;
	private List<City> cities;
	private List<Settlement> settlements;
	private List<Road> roads;
	private Board map;
	private double probability;
	
	/* Chance of rolling x - 2 */
	static double [] pr = { 1.0/36.0, 2.0/36, 3.0/36, 4.0/36, 5.0/36, 6.0/36, 5.0/36, 4.0/36, 3.0/36, 2.0/36 , 1.0/36 };
	
	public State( Settlement s1, Settlement s2, Road r1, Road r2, Board m){
		turn = 0;
		points = 2;
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
	public State( int t, int p, Map<Card.Type, Integer> H, List<City> C, List<Settlement> S, List<Road> R, Board m, float pr ){
		turn = t;
		points = p;
		hand = H;
		cities = C;
		settlements = S;
		roads = R;
		map = m;
		probability = pr;
	}
	public State( int t, double pr, State state, Map<Card.Type, Integer> h ){
		cities = state.cities;
		settlements = state.settlements;
		roads = state.roads;
		map = state.map;
		points = state.points;
		
		turn = t;
		probability = pr;
		hand = h;
	}
	
	public String toString(){
		return 
		
		"Probability: " + this.probability + "\n\n" +
		"Turn: " + turn +"\n" +
		"Hand: " + hand + "\n";
	}

	public List<State> generateRollStates(){
            final List<State> ans = new ArrayList<State>();
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
                
                ans.add( new State( turn + 1, probability * pr[i - 2], this, newHand) );
            }
            return ans;
	}
	
	public List<State> generatePlayStates(){
		return null;
	}
}
