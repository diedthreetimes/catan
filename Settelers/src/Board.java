public class Board {
	private Point[][] spaces = new Point[12][11];
	
	{
		spaces[0] = new Point[] {
			null,
			null,
			null,
			new Point(Card.WOOD),
			null,
			new Point(Card.SHEEP),
			null,
			new Point(Card.WHEAT),
			null,
			null,
			null};
		spaces[1] = new Point[] {
			null,
			null,
			new Point(Card.WOOD),
			null,
			new Point(Card.WOOD, Card.SHEEP),
			null,
			new Point(Card.SHEEP, Card.WHEAT),
			null,
			new Point(Card.WHEAT),
			null,
			null};
		spaces[2] = new Point[] {
			null,
			null,
			new Point(Card.WOOD, Card.BRICK),
			null,
			new Point(Card.WOOD, Card.SHEEP, Card.ORE),
			null,
			new Point(Card.SHEEP, Card.BRICK, Card.WHEAT),
			null,
			new Point(Card.SHEEP, Card.WHEAT),
			null,
			null};
		spaces[3] = new Point[] {
			null,
			new Point(Card.BRICK),
			null,
			new Point(Card.WOOD, Card.BRICK, Card.ORE),
			null,
			new Point(Card.SHEEP, Card.BRICK, Card.ORE),
			null,
			new Point(Card.BRICK, Card.SHEEP, Card.WHEAT),
			null,
			new Point(Card.WHEAT),
			null};
	}
}
