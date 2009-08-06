import java.util.*;
public class State {
	private int turn;
	private int points;
	private HashMap<Card, Integer> Hand;
	private List<City> Cities;
	private List<Settlement> Settlements;
	private List<Road> Roads;
	private Board map;
	private float probability;
}
