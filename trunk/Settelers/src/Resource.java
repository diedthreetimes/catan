
public class Resource {
	private Card settleCard;
	private Card cityCard;
	private int number;
	
	public Resource (final Card.Type type, final int p ){
		// nul represents "Robber"
		if (type != null) {
			settleCard = new Card(type);
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
	public Card produceSettlementCard() { return settleCard; }
	public Card produceCityCard() { return cityCard; }

}
