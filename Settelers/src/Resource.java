
public class Resource {
	private Card settleCard;
	private Card cityCard;
	private int number;
	
	public Resource ( int v, int p ){
		switch ( v ){
		case Card.WOOD : settleCard = new Card( Card.WOOD );
						 cityCard   = new Card( Card.WOOD );
						 break;
		case Card.BRICK : settleCard = new Card( Card.BRICK );
		 				 cityCard   = new Card( Card.BRICK );
		 				 break;
		case Card.ORE : settleCard = new Card( Card.ORE );
		 				 cityCard   = new Card( Card.ORE );
		 				 break;
		case Card.WHEAT : settleCard = new Card( Card.WHEAT );
						 cityCard   = new Card( Card.WHEAT );
						 break;
		case Card.SHEEP : settleCard = new Card( Card.SHEEP );
		 				 cityCard   = new Card( Card.SHEEP );
		 				 break;
		default : throw new IllegalArgumentException( "Invalid tile.\n" );
		}
	if ( p > 12 || p < 2)
		throw new IllegalArgumentException( "Invalid probability.\n");
	number = p;
	}
	
	public int getNumber() { return number; }
	public Card produceSettlementCard() { return settleCard; }
	public Card produceCityCard() { return cityCard; }
}
