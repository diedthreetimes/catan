
public class Resource {
	private Card settleCardPrototype;
	private Card cityCard;
	private int number;
	
	public Resource (final Card.Type type, final int p ){
		// nul represents "Robber"
		if (type != null) {
			settleCardPrototype = new Card(type);
			if ( p > 12 || p < 2) {
				throw new IllegalArgumentException( "Invalid probability.\n");
			}
		}
		number = p;
	}

	public boolean producesForRoll(final int roll) {
		return (roll == number);
	}
	
	public int getNumber() { return number; }

        /**
         * TODO: this method does not simulate resource limits from the board game. Do we care?
         */
	public Card produceCard() {
            return settleCardPrototype.clone();
        }

}
