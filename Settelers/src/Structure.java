import java.util.*;

// enumerates structure names and costs
public enum Structure {
		ROAD, SETTLEMENT, CITY;
		
		// returns true if given hand includes enough resources to build 
		// given structure
		boolean canBuild(HashMap<Card, Integer> hand, Structure structure) {
			if (structure == Structure.ROAD)
				if (hand.get(Card.WOOD) < 1 || hand.get(Card.BRICK) < 1)
					return false;
			else if (structure == Structure.SETTLEMENT)
				if (hand.get(Card.WOOD) < 1 || hand.get(Card.BRICK) < 1
					|| (hand.get(Card.WHEAT) < 1 || hand.get(Card.SHEEP) < 1))
					return false;
			else if (structure == Structure.CITY)
				if (hand.get(Card.WHEAT) < 2 || hand.get(Card.ORE) < 3)
					return false;
			else
				throw new IllegalArgumentException("Invalid structure.");
			return true;
		}
}

