import java.util.*;

public class State {
	private int turn;
	private int points;
	private HashMap<Card, Integer> hand;
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
		hand = new HashMap<Card, Integer>();
		hand.put(new Card( Card.Type.ORE ), 0);
		hand.put(new Card( Card.Type.SHEEP ), 0);
		hand.put(new Card( Card.Type.WHEAT ), 0);
		hand.put(new Card( Card.Type.WOOD ), 0);
		hand.put(new Card( Card.Type.BRICK ), 0);
		
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
	public State( int t, int p, HashMap<Card, Integer> H, List<City> C, List<Settlement> S, List<Road> R, Board m, float pr ){
		turn = t;
		points = p;
		hand = H;
		cities = C;
		settlements = S;
		roads = R;
		map = m;
		probability = pr;
	}
	public State( int t, double pr, State state, HashMap<Card, Integer> h ){
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
		List<State> ans = new ArrayList<State>();
		for( int i = 2; i<=12; i++ ){
			HashMap<Card, Integer> newHand = (HashMap<Card, Integer>) hand.clone();
			for( Settlement s : settlements){
				for( Card c : s.roll(i) ){
					newHand.put(c, newHand.get(c) + 1);
				}
			}
			for( City cit : cities){
				for( Card c : cit.roll(i) ){
					newHand.put(c, newHand.get(c) + 1);
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
