public class Board {
	private Resource[][] resources = new Resource[5][9];
	private Point[][] spaces = new Point[12][11];
	
	{
		resources[0] = new Resource[] {
			null,
			null,
			new Resource(Card.WOOD, 11),
			null,
			new Resource(Card.SHEEP, 12),
			null,
			new Resource(Card.WHEAT, 9),
			null,
			null};
		resources[1] = new Resource[] {
			null,
			new Resource(Card.BRICK, 4),
			null,
			new Resource(Card.ORE, 6),
			null,
			new Resource(Card.BRICK, 5),
			null,
			new Resource(Card.SHEEP, 10),
			null};
		resources[2] = new Resource[] {
			new Resource(-1, 0),
			null,
			new Resource(Card.WOOD, 3),
			null,
			new Resource(Card.WHEAT, 11),
			null,
			new Resource(Card.WOOD, 4),
			null,
			new Resource(Card.WHEAT, 8)};
		resources[3] = new Resource[] {
			null,
			new Resource(Card.BRICK, 8),
			null,
			new Resource(Card.SHEEP, 10),
			null,
			new Resource(Card.SHEEP, 9),
			null,
			new Resource(Card.ORE, 3),
			null};
		resources[4] = new Resource[] {
			null,
			null,
			new Resource(Card.ORE, 5),
			null,
			new Resource(Card.WHEAT, 2),
			null,
			new Resource(Card.WOOD, 6),
			null,
			null};
		spaces[0] = new Point[] {
			null,
			null,
			null,
			new Point(0, 3, resources[0][2]),
			null,
			new Point(0, 5, resources[0][4]),
			null,
			new Point(0, 7, resources[0][6]),
			null,
			null,
			null};
		spaces[1] = new Point[] {
			null,
			null,
			new Point(1, 2, resources[0][2]),
			null,
			new Point(1, 4, resources[0][2], resources[0][4]),
			null,
			new Point(1, 6, resources[0][4], resources[0][6]),
			null,
			new Point(1, 8, resources[0][6]),
			null,
			null};
		spaces[2] = new Point[] {
			null,
			null,
			new Point(2, 2, resources[1][1], resources[0][2]),
			null,
			new Point(2, 4, resources[1][3], resources[0][2], resources[0][4]),
			null,
			new Point(2, 6, resources[1][5], resources[0][4], resources[0][6]),
			null,
			new Point(2, 8, resources[1][7], resources[0][6]),
			null,
			null};
		spaces[3] = new Point[] {
			null,
			new Point(3, 1, resources[1][1]),
			null,
			new Point(3, 3, resources[1][1], resources[0][2], resources[1][3]),
			null,
			new Point(3, 5, resources[1][3], resources[0][4], resources[1][5]),
			null,
			new Point(3, 7, resources[1][5], resources[0][6], resources[1][7]),
			null,
			new Point(3, 9, resources[1][7]),
			null};
		spaces[4] = new Point[] {
			null,
			new Point(4, 1, resources[2][0], resources[1][1]),
			null,
			new Point(4, 3, resources[1][1], resources[2][2], resources[1][3]),
			null,
			new Point(4, 5, resources[1][3], resources[2][4], resources[1][5]),
			null,
			new Point(4, 7, resources[1][5], resources[2][6], resources[1][7]),
			null,
			new Point(4, 9, resources[1][7], resources[2][8]),
			null};
		spaces[5] = new Point[] {
			new Point(5, 0, resources[2][0]),
			null,
			new Point(5, 2, resources[2][0], resources[1][1], resources[2][2]),
			null,
			new Point(5, 4, resources[2][2], resources[1][3], resources[2][4]),
			null,
			new Point(5, 6, resources[2][4], resources[1][5], resources[2][6]),
			null,
			new Point(5, 8, resources[2][6], resources[1][7], resources[2][8]),
			null,
			new Point(5, 10, resources[2][8])};
		spaces[6] = new Point[] {
			new Point(6, 0, resources[2][0]),
			null,
			new Point(6, 2, resources[2][0], resources[3][1], resources[2][2]),
			null,
			new Point(6, 4, resources[2][2], resources[3][3], resources[2][4]),
			null,
			new Point(6, 6, resources[2][4], resources[3][5], resources[2][6]),
			null,
			new Point(6, 8, resources[2][6], resources[3][7], resources[2][8]),
			null,
			new Point(6, 10, resources[2][8])};
		spaces[7] = new Point[] {
			null,
			new Point(7, 1, resources[2][0], resources[3][1]),
			null,
			new Point(7, 3, resources[3][1], resources[2][2], resources[3][3]),
			null,
			new Point(7, 5, resources[3][3], resources[2][4], resources[3][5]),
			null,
			new Point(7, 7, resources[3][5], resources[2][6], resources[3][7]),
			null,
			new Point(7, 9, resources[3][7], resources[2][8]),
			null};
		spaces[8] = new Point[] {
			null,
			new Point(8, 1, resources[3][1]),
			null,
			new Point(8, 3, resources[3][1], resources[2][2], resources[3][3]),
			null,
			new Point(8, 5, resources[3][3], resources[2][4], resources[3][5]),
			null,
			new Point(8, 7, resources[3][5], resources[2][6], resources[3][7]),
			null,
			new Point(8, 9, resources[3][7]),
			null};
		spaces[9] = new Point[] {
			null,
			null,
			new Point(9, 2, resources[3][1], resources[4][2]),
			null,
			new Point(9, 4, resources[3][3], resources[4][2], resources[3][4]),
			null,
			new Point(9, 6, resources[3][5], resources[4][4], resources[3][6]),
			null,
			new Point(9, 8, resources[3][7], resources[4][6]),
			null,
			null};
		spaces[10] = new Point[] {
			null,
			null,
			new Point(10, 2, resources[4][2]),
			null,
			new Point(10, 4, resources[4][2], resources[4][4]),
			null,
			new Point(10, 6, resources[4][4], resources[4][6]),
			null,
			new Point(10, 8, resources[4][6]),
			null,
			null};
		spaces[11] = new Point[] {
			null,
			null,
			null,
			new Point(11, 3, resources[4][2]),
			null,
			new Point(11, 5, resources[4][4]),
			null,
			new Point(11, 7, resources[4][6]),
			null,
			null,
			null};
	}
	
	// Return the point at (x,y)
	public Point getPoint(int x, int y){
		return spaces[x][y];
	}
}
