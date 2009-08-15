import java.util.*;

// enumerates structure names and costs
public enum Structure {
    ROAD {
        boolean canBuild(final Map<Card.Type, Integer> hand) {
            return hand.get(Card.Type.WOOD) >= 1 && hand.get(Card.Type.BRICK) >= 1;
        }

        EnumMap<Card.Type, Integer> payFrom(final EnumMap<Card.Type, Integer> hand) {
            final EnumMap<Card.Type, Integer> newHand = hand.clone();
            newHand.put(Card.Type.WOOD, hand.get(Card.Type.WOOD) - 1);
            newHand.put(Card.Type.BRICK, hand.get(Card.Type.BRICK) - 1);
            return newHand;
        }
    },
    SETTLEMENT {
        boolean canBuild(final Map<Card.Type, Integer> hand) {
            return hand.get(Card.Type.WOOD) >= 1 && hand.get(Card.Type.BRICK) >= 1
                && hand.get(Card.Type.WHEAT) >= 1 && hand.get(Card.Type.SHEEP) >= 1;
        }

        EnumMap<Card.Type, Integer> payFrom(final EnumMap<Card.Type, Integer> hand) {
            final EnumMap<Card.Type, Integer> newHand = hand.clone();
            newHand.put(Card.Type.WOOD, hand.get(Card.Type.WOOD) - 1);
            newHand.put(Card.Type.BRICK, hand.get(Card.Type.BRICK) - 1);
            newHand.put(Card.Type.WHEAT, hand.get(Card.Type.WHEAT) - 1);
            newHand.put(Card.Type.SHEEP, hand.get(Card.Type.SHEEP) - 1);
            return newHand;
        }
    },
    CITY {
        boolean canBuild(final Map<Card.Type, Integer> hand) {
            return hand.get(Card.Type.WHEAT) >= 2 && hand.get(Card.Type.ORE) >= 3;
        }

        EnumMap<Card.Type, Integer> payFrom(final EnumMap<Card.Type, Integer> hand) {
            final EnumMap<Card.Type, Integer> newHand = hand.clone();
            newHand.put(Card.Type.WHEAT, hand.get(Card.Type.WHEAT) - 2);
            newHand.put(Card.Type.ORE, hand.get(Card.Type.ORE) - 3);
            return newHand;
        }
    };

    /**
     * @return true if given hand includes enough resources to build 
     * given structure
     */
    abstract boolean canBuild(final Map<Card.Type, Integer> hand);

    abstract EnumMap<Card.Type, Integer> payFrom(final EnumMap<Card.Type, Integer> hand);

    public static Set<Structure> canBuildOptions(final Map<Card.Type, Integer> hand) {
        final Set<Structure> options = EnumSet.noneOf(Structure.class); // awesomely efficient, backed by a bitmap!
        for (final Structure s : Structure.values()) if (s.canBuild(hand)) {
            options.add(s);
        }
        return options;
    }
}

