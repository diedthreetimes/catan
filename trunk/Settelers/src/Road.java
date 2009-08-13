import java.util.*;

public class Road {
	private Point start;
	private Point end;

	public Road ( Point start, Point end ){
		this.start = start;
		this.end = end;
	}

	public Point getStart() {
		return start;
	}

	public Point getEnd() {
		return end;
	}
}
