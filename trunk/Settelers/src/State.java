import java.util.*;
public class State {
	private int turn;
	private int points;
	private HashMap<Card, Integer> hand;
	private List<City> cities;
	private List<Settlement> settlements;
	private List<Road> roads;
	private Board map;
	private float probability;
	
	/* Chance of rolling x - 2 */
	static float [] pr = { 1/36, 2/36, 3/36, 4/36, 5/36, 6/36, 5/36, 4/36, 3/36, 2/36 ,1/36 };
	
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
	public State( int t, float pr, State state, HashMap<Card, Integer> h ){
		cities = state.cities;
		settlements = state.settlements;
		roads = state.roads;
		map = state.map;
		points = state.points;
		
		turn = t;
		probability = pr;
		hand = h;
	}
	public List<State> generateRollStates(){
		List<State> ans = new LinkedList<State>();
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
