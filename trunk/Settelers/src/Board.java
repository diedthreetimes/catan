public class Board {
	private Point[][] spaces = new Point[6][11];
	
	{
		spaces[0] = new Point[] {
			null,
			null,
			new Point(0, 2, new Resource(Card.ORE, 5)),
			new Point(0, 3, new Resource(Card.ORE, 5)),
			new Point(0, 4, new Resource(Card.ORE, 5), new Resource(Card.BRICK, 8)),
			new Point(0, 5, new Resource(Card.BRICK, 8)),
			new Point(0, 6, new Resource(Card.BRICK, 8)),
			new Point(0, 7),
			new Point(0, 8),
			null,
			null
			};
		spaces[1] = new Point[] {
			null,
			new Point(1, 1, new Resource(Card.WHEAT, 2)),
			new Point(1, 2, new Resource(Card.WHEAT, 2), new Resource(Card.ORE, 5)),
			new Point(1, 3, new Resource(Card.WHEAT, 2), new Resource(Card.ORE, 5), new Resource(Card.SHEEP, 10)),
			new Point(1, 4, new Resource(Card.SHEEP, 10), new Resource(Card.ORE, 5), new Resource(Card.BRICK, 8)),
			new Point(1, 5, new Resource(Card.SHEEP, 10), new Resource(Card.BRICK, 8), new Resource(Card.WOOD, 3)),
			new Point(1, 6, new Resource(Card.WOOD, 3), new Resource(Card.BRICK, 8)),
			new Point(1, 7, new Resource(Card.WOOD, 3), new Resource(Card.BRICK, 4)),
			new Point(1, 8, new Resource(Card.BRICK, 4)),
			new Point(1, 9, new Resource(Card.BRICK, 4)),
			null
			};
		spaces[2] = new Point[] {
			new Point(2, 0, new Resource(Card.WOOD, 6)),
			new Point(2, 1, new Resource(Card.WOOD, 6), new Resource(Card.WHEAT, 2)),
			new Point(2, 2, new Resource(Card.WOOD, 6), new Resource(Card.WHEAT, 2), new Resource(Card.SHEEP, 9)),
			new Point(2, 3, new Resource(Card.WHEAT, 2), new Resource(Card.SHEEP, 9), new Resource(Card.SHEEP, 10)),
			new Point(2, 4, new Resource(Card.SHEEP, 9), new Resource(Card.SHEEP, 10), new Resource(Card.WHEAT,11)),
			new Point(2, 5, new Resource(Card.SHEEP, 10), new Resource(Card.WHEAT, 11), new Resource(Card.WOOD, 3)),
			new Point(2, 6, new Resource(Card.WHEAT, 11), new Resource(Card.WOOD, 3), new Resource(Card.ORE, 6)),
			new Point(2, 7, new Resource(Card.WOOD, 3), new Resource(Card.ORE, 6), new Resource(Card.BRICK, 4)),
			new Point(2, 8, new Resource(Card.ORE, 6), new Resource(Card.BRICK, 4), new Resource(Card.WOOD, 11)),
			new Point(2, 9, new Resource(Card.BRICK, 4), new Resource(Card.WOOD, 11)),
			new Point(2, 10, new Resource(Card.WOOD, 11))
			};
		spaces[3] = new Point[] {
			new Point(3, 0, new Resource(Card.WOOD, 6)),
			new Point(3, 1, new Resource(Card.WOOD, 6), new Resource(Card.ORE, 3)),
			new Point(3, 2, new Resource(Card.WOOD, 6), new Resource(Card.ORE, 3), new Resource(Card.SHEEP, 9)),
			new Point(3, 3, new Resource(Card.ORE, 3), new Resource(Card.SHEEP, 9), new Resource(Card.WOOD, 4)),
			new Point(3, 4, new Resource(Card.WHEAT, 11), new Resource(Card.SHEEP, 9), new Resource(Card.WOOD, 4)),
			new Point(3, 5, new Resource(Card.BRICK, 5), new Resource(Card.WOOD, 4), new Resource(Card.WHEAT, 11)),
			new Point(3, 6, new Resource(Card.BRICK, 5), new Resource(Card.WHEAT, 11), new Resource(Card.ORE, 6)),
			new Point(3, 7, new Resource(Card.ORE, 6), new Resource(Card.SHEEP, 12), new Resource(Card.BRICK, 5)),
			new Point(3, 8, new Resource(Card.WOOD, 11), new Resource(Card.ORE, 6), new Resource(Card.SHEEP, 12)),
			new Point(3, 9, new Resource(Card.SHEEP, 12), new Resource(Card.WOOD, 11)),
			new Point(3, 10, new Resource(Card.WOOD, 11))
			};
		spaces[4] = new Point[] {
			null,
			new Point(4, 1, new Resource(Card.ORE, 3)),
			new Point(4, 2, new Resource(Card.WHEAT, 8), new Resource(Card.ORE, 3)),
			new Point(4, 3, new Resource(Card.WHEAT, 8), new Resource(Card.ORE, 3), new Resource(Card.WOOD, 4)),
			new Point(4, 4, new Resource(Card.WHEAT, 8), new Resource(Card.WOOD, 4), new Resource(Card.SHEEP, 10)),
			new Point(4, 5, new Resource(Card.SHEEP, 10), new Resource(Card.WOOD, 4), new Resource(Card.BRICK, 5)),
			new Point(4, 6, new Resource(Card.SHEEP, 10), new Resource(Card.BRICK, 5), new Resource(Card.WHEAT, 9)),
			new Point(4, 7, new Resource(Card.BRICK, 5), new Resource(Card.WHEAT, 9), new Resource(Card.SHEEP, 12)),
			new Point(4, 8, new Resource(Card.WHEAT, 9), new Resource(Card.SHEEP, 12)),
			new Point(4, 9, new Resource(Card.SHEEP, 12)),
			null
			};
		spaces[5] = new Point[] {
			null,
			null,
			new Point(5, 2, new Resource(Card.WHEAT, 8)),
			new Point(5, 3, new Resource(Card.WHEAT, 8)),
			new Point(5, 4, new Resource(Card.WHEAT, 8), new Resource(Card.SHEEP, 10)),
			new Point(5, 5, new Resource(Card.SHEEP, 10)),
			new Point(5, 6, new Resource(Card.SHEEP, 10), new Resource(Card.WHEAT, 9)),
			new Point(5, 7, new Resource(Card.WHEAT, 9)),
			new Point(5, 8, new Resource(Card.WHEAT, 9)),
			null,
			null
			};
	}
	
	public Point getPoint(int x, int y) {
		if (x < 0 || y < 0 || x >= 6 || y >= 11)
			return null;
		return spaces[x][y];
	}
	
	// given a point, returns an ArrayList of neighboring points
	public List<Point> getNeighbors (Point p) {
		ArrayList<Point> neighbors = new ArrayList<Point>();
		
		// obtain points in all directions
		Point north = getPoint(p.x, p.y + 1);
		Point south = getPoint(p.x, p.y - 1);
		Point west = getPoint(p.x - 1, p.y);
		Point east = getPoint(p.x + 1, p.y);
		
		// make sure all points are valid
		if (north != null)
			neighbors.add(north);
		if (south != null)
			neighbors.add(south);
		if (p.x % 2 == 0 && east != null)
			neighbors.add(east);
		if (p.x % 2 == 1 && west != null)
			neighbors.add(west);
		
		return neighbors;
	}
}
