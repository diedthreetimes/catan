
public class Point {
	protected int x;
	protected int y;
	protected Resource [] resources;
	
	public Point(int x, int y, Resource... hexes){
		 this.x = x;
		 this.y = y;
		 resources = hexes;
	}
}
