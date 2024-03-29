import java.util.*;

/**
 * A State object is intended to be an immutable representation, suitable for a node
 * on a state diagram. Technicaly, this can be circumvented by manipulating the contents
 * of member Collection classes.
 */
public class State {

	private final int turn;
	private final int points;
	private final EnumMap<Card.Type, Integer> hand;
	private final List<City> cities;
	private final List<Settlement> settlements;
	private final List<Road> roads;
	private final Board map;
	private final double probability;
	private final Weight weights;
	
	
        /**
         * Should this take a Collection of settlements and a Collection of roads?
         * Theoretically it could, but any initial state according to the rules of Catan
         *  only has two settlements and two roads.
         */
	public State( Settlement s1, Settlement s2, Road r1, Road r2, Board m, Weight weights){
		this.weights = weights;
		
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
         * @see #nextTurn(double, Map) below.
         * @see #buildRoad(double, Road) below
         * @see #buidSettlement(double, Settlement) below
         * @see #buildCity(double, City) below
         */
	private State( int t, int p, EnumMap<Card.Type, Integer> H, List<City> C, List<Settlement> S, List<Road> R, Board m, double pr, Weight weights ){
		turn = t;
		points = p;
		hand = H;
		cities = C;
		settlements = S;
		roads = R;
		map = m;
		probability = pr;
		this.weights = weights;
	}

	public State keepWinning() {
	    return new State(turn + 1, points, hand, cities, settlements, roads, map, probability, weights);
	}

        /**
         * @param transitionProbability Probability of transitioning to this new state.
         * @param newHand Hand for the next stage.
         */
        private State nextTurn(final double transitionProbability, final EnumMap<Card.Type, Integer> newHand) {
            return new State(turn + 1, this.points, newHand, this.cities,
                    this.settlements, this.roads, this.map, probability * transitionProbability, weights);
        }

        private State trade(final double transitionProbability, EnumMap<Card.Type, Integer> newHand){
        	return new State(turn, this.points, newHand, this.cities,
        			this.settlements, this.roads, this.map, this.probability * transitionProbability, weights);
        	
        }
        /* Should we add validation to these methods? */
        private State buildRoad(final double transitionalProbability, final Road r) {
            final List<Road> newRoads = new ArrayList<Road>(roads);
            newRoads.add(r);
            final EnumMap<Card.Type, Integer> newHand = Structure.ROAD.payFrom(hand);
            return new State(turn , this.points, newHand, this.cities,
                    this.settlements, newRoads, this.map, probability * transitionalProbability, weights);
        }

        private State buildSettlement(final double transitionalProbability, final Point s) {
            final List<Settlement> newSettlements = new ArrayList<Settlement>(settlements);
            newSettlements.add(new Settlement (s));
            final EnumMap<Card.Type, Integer> newHand = Structure.SETTLEMENT.payFrom(hand);
            return new State(turn , this.points +1, newHand, this.cities,
                    newSettlements, this.roads, this.map, probability * transitionalProbability, weights);
        }

        private State buildCity(final double transitionalProbability, final Settlement s) {
            final List<Settlement> newSettlements = new ArrayList<Settlement>(settlements);
            newSettlements.remove(s);
            final List<City> newCities = new ArrayList<City>(cities);
            newCities.add(new City(s));
            final EnumMap<Card.Type, Integer> newHand = Structure.CITY.payFrom(hand);
            return new State(turn , this.points +1, newHand, newCities,
                    newSettlements, this.roads, this.map, probability * transitionalProbability, weights);
        }

        /**
         * Much easier in the single player-case: we qualify for "longest road" if our road has a non-branching length of 5.
         */
        public boolean isLongestRoad() {
            return longestRoadLength() >= 5;
        }

        /**
         * Of course, the longest-path problem for undirected graphs is NP-complete.
         */
        private int longestRoadLength() {
            final Map<Point, Set<Point>> transitions = buildTransitionMap();
            int max = 0;
            for (final Point start : transitions.keySet()) {
                max = Math.max(max, longestRoadFrom(start, new HashSet<Point>(), transitions));
            }
            return max;
        }

        /**
         * Not very efficient, but at least it detects cycles.
         */
        private int longestRoadFrom(final Point current, final Set<Point> visited, final Map<Point, Set<Point>> transitions) {
            visited.add(current);
            int max = -1;
            for (final Point next : transitions.get(current)) if (!visited.contains(next)) {
                max = Math.max(max, longestRoadFrom(next, new HashSet<Point>(visited), transitions));
            }
            return 1 + max; // 0 if no valid next, at least 1 otherwise
        }

        private Map<Point, Set<Point>> buildTransitionMap() {
            final Map<Point, Set<Point>> transitions = new HashMap<Point, Set<Point>>();
            for (final Road r: roads) {
                if (!transitions.containsKey(r.getStart())) {
                    transitions.put(r.getStart(), new HashSet<Point>());
                }
                if (!transitions.containsKey(r.getEnd())) {
                    transitions.put(r.getEnd(), new HashSet<Point>());
                }
                transitions.get(r.getStart()).add(r.getEnd());
                transitions.get(r.getEnd()).add(r.getStart());
            }
            return transitions;
        }

        public int getPoints() {
            return points;// + (isLongestRoad() ? 2 : 0);
        }

	public int getTurn() {
	    return turn;
	}
	
	public double getProbability(){
		return probability;
	}

	public boolean isWinning() {
	    return getPoints() >= 8;
	}
	
	public String toString(){
            return "Probability: " + this.probability + "\n"
		    + "Turn: " + turn +"\n"
		    + "Hand: " + hand + "\n"
		    + "Points: " + getPoints() + "\n" 
            + "Roads: " + roads.size() + "\n"
            + "Cities: " + cities.size() + "\n";
	}

        /**
         * @return possible states as outcomes of a die roll.
         */
	public List<State> generateRollStates(){
		
			//Return an empty list if we already won.
			if ( this.isWinning() )
				return new ArrayList<State>(0);
			
            final List<State> ans = new ArrayList<State>(11);
            
	    final Map<EnumMap<Card.Type, Integer>, Double> newHandPrs
			= new HashMap<EnumMap<Card.Type, Integer>, Double>();
	    newHandPrs.put(hand, 0.0);
            for( int i = 2; i<=12; i++ ){
                final EnumMap<Card.Type, Integer> newHand = hand.clone();
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
		if (!newHandPrs.containsKey(newHand)) {
		    newHandPrs.put(newHand, 0.0);
		}
		newHandPrs.put(newHand, newHandPrs.get(newHand) + Weight.PR[i - 2]);
            }
	    for (final Map.Entry<EnumMap<Card.Type, Integer>, Double> e : newHandPrs.entrySet()) if (e.getValue() != 0.0) {
		ans.add(nextTurn(e.getValue(), e.getKey()));
	    }
            return ans;
	}
	
        /**
         * @return possible states as outcomes of "game play"
         */
	public List<State> generatePlayStates(){
            /*
             Turn phase:
             resource production (see #generateRollStates)
             trade: // Trade should be after build
                domestic trade (out of scope)
                maritime trade (low priority?) //if we can trade we do simple Pr for now
             build
                road
                settlement
                city
                development card (out of scope?) //yes
            special cases
                robber (out of scope?) //yes
            */
            final Set<Road> potentialRoads = new HashSet<Road>();
            final Set<Settlement> potentialSettlements = new HashSet<Settlement>();
            final Set<Settlement> potentialCities = new HashSet<Settlement>();
            final Set<Structure> structureOptions = Structure.canBuildOptions(hand);
            if (structureOptions.contains(Structure.SETTLEMENT)) {
                potentialSettlements.addAll(getAllPotentialSettlements());
            }
            if (structureOptions.contains(Structure.CITY)) for (final Settlement s : settlements) {
                potentialCities.add(s);
            }
            
            
            int numProjects;
	    // only build roads when there is no other construction option
            if (structureOptions.contains(Structure.ROAD) && potentialSettlements.isEmpty() && potentialCities.isEmpty()) {
                potentialRoads.addAll(getAllPotentialRoads());
                numProjects = potentialRoads.size();
            }
            else
            	numProjects = potentialSettlements.size() + potentialCities.size();
            final List<State> playStates = new ArrayList<State>();
            if (numProjects > 0) {
               
            	double weightTotal = 0;
            	
            	for (final Settlement s : potentialSettlements) {
                    weightTotal += weights.calculatePointWeight(s);
                }
            	for (final Settlement s: potentialCities) {
                    weightTotal += weights.calculatePointWeight(s);
                }
            	
                for (final Settlement s : potentialSettlements) {
                    playStates.add(buildSettlement( weights.calculatePointWeight(s) / weightTotal, s));
                }
                for (final Settlement s: potentialCities) {
                    playStates.add(buildCity( weights.calculatePointWeight(s) / weightTotal, s));
                }
                
                for (final Road r : potentialRoads) {
                    playStates.add(buildRoad( 1.0 / (double)numProjects, r));
                }               
                // buid a wrapper list so we don't modify the collection under iteration
                //for (final State s : new ArrayList<State>(playStates)) {
                    // add all derivative states (other projects we can "buy" now
                    //playStates.addAll(s.generatePlayStates());
                //}
            }
            else{
            	Card.Type c = getLeastCard();
            	boolean changed = false;
            	EnumMap<Card.Type, Integer> newHand = hand.clone();
            	while( hand.get(Card.Type.BRICK) >= 4 && Card.Type.BRICK.equals(c) ){
            		newHand.put(c, newHand.get(c) + 1);
            		newHand.put(Card.Type.BRICK, newHand.get(Card.Type.BRICK) - 4);
            		changed = true;
            	}
            	while( newHand.get(Card.Type.ORE) >= 4 && Card.Type.ORE.equals(c) ){
            		newHand.put(c, newHand.get(c) + 1);
            		newHand.put(Card.Type.ORE, newHand.get(Card.Type.ORE) - 4);
            		changed = true;
            	}
            	while( newHand.get(Card.Type.WHEAT) >= 4 && Card.Type.WHEAT.equals(c) ){
            		newHand.put(c, newHand.get(c) + 1);
            		newHand.put(Card.Type.WHEAT, hand.get(Card.Type.WHEAT) - 4);
            		changed = true;
            	}
            	while( newHand.get(Card.Type.SHEEP) >= 4 && Card.Type.SHEEP.equals(c) ){
            		newHand.put(c, newHand.get(c) + 1);
            		newHand.put(Card.Type.SHEEP, newHand.get(Card.Type.SHEEP) - 4);
            		changed = true;
            	}
            	while( newHand.get(Card.Type.WOOD) >= 4 && Card.Type.WOOD.equals(c) ){
            		newHand.put(c, newHand.get(c) + 1);
            		newHand.put(Card.Type.WOOD, newHand.get(Card.Type.WOOD) - 4);
            		changed = true;
            	}
            	
            	if( changed )
            		playStates.add(trade(1, newHand));
            }
            
            
            return playStates;
	}

        private Set<Road> getAllPotentialRoads() {
            final Set<Road> potential = new HashSet<Road>();
            for (final Road road: roads) {
            	if( road.getStart().canPlaceSettlement(map, cities, settlements) || road.getEnd().canPlaceSettlement(map, cities, settlements) ){
            		return new HashSet<Road>();
            	}
                potential.addAll(road.getPotentialConnectors(map));
            }
            potential.removeAll(roads);
            return potential;
        }

        private Set<Settlement> getAllPotentialSettlements() {
            final Set<Point> potential = new HashSet<Point>();
            for (final Road road : roads) {
                potential.add(road.getStart());
                potential.add(road.getEnd());
            }
            final Set<Settlement> potentialSettlements = new HashSet<Settlement>();
            for (final Point point : potential) if (point.canPlaceSettlement(map, cities, settlements)) {
                potentialSettlements.add(new Settlement(point));
            }
            return potentialSettlements;
        }
        
        private Card.Type getLeastCard(){
        	int min = hand.get(Card.Type.WOOD);
        	Card.Type minC = Card.Type.WOOD;
        	
        	for( Card.Type x : Card.Type.values() ){
        		int n = hand.get(x);
        		if ( n < min ){
        			minC = x;
        			min = n;
        		}
        	}
        	return minC;
        }
}
