
public class Point {
	protected int x;
	protected int y;
	protected Resource [] resources;

	private final int[] cards;

	public Point(final int... cardTypes) {
		this.cards = cardTypes;
	}
}
