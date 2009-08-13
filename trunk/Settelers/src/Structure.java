import java.util.*;

// enumerates structure names and costs
public enum Structure {
		ROAD {
			boolean canBuild(final Map<Card.Type, Integer> hand) {
				return hand.get(Card.Type.WOOD) >= 1 && hand.get(Card.Type.BRICK) >= 1;
			}
		},
		SETTLEMENT {
			boolean canBuild(final Map<Card.Type, Integer> hand) {
				return hand.get(Card.Type.WOOD) >= 1 && hand.get(Card.Type.BRICK) >= 1
					&& hand.get(Card.Type.WHEAT) >= 1 && hand.get(Card.Type.SHEEP) >= 1;
			}
		},
		CITY {
			boolean canBuild(final Map<Card.Type, Integer> hand) {
				return hand.get(Card.Type.WHEAT) >= 2 && hand.get(Card.Type.ORE) >= 3;
			}
		};

		/**
		 * @return true if given hand includes enough resources to build 
		 * given structure
		 */
		abstract boolean canBuild(final Map<Card.Type, Integer> hand);
}

