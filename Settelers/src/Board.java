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
			null, // Desert
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
			new Point(Card.SHEEP),
			null,
			null, // Desert
			null,
			null};
	}
}
