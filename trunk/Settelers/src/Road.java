import java.util.*;

public class Road {
    private final Point start;
    private final Point end;

    public Road ( Point start, Point end ){
	if (start == null) {
	    throw new IllegalArgumentException("start cannot be null");
	} else if (end == null) {
	    throw new IllegalArgumentException("end cannot be null");
	}
        this.start = start;
        this.end = end;
    }

    public boolean equals(final Object o) {
        return (o instanceof Road) &&
            ((((Road)o).start.equals(start) && ((Road)o).end.equals(end)) || (((Road)o).start.equals(end) && ((Road)o).end.equals(start)));
    }

    /**
     * This looks wrong, but it's intentional. Start and End must be interchangable.
     */
    public int hashCode() {
        return start.hashCode() + end.hashCode();
    }


    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public Set<Road> getPotentialConnectors(final Board board) {
	if (board == null) {
	    throw new IllegalArgumentException("board cannot be null");
	}
        final Set<Road> potential = new HashSet<Road>();
        for (final Point p : board.getNeighbors(start)) if (!end.equals(p)) {
            potential.add(new Road(start, p));
        }
        for (final Point p : board.getNeighbors(end)) if (!start.equals(p)) {
            potential.add(new Road(end, p));
        }
        return potential;
    }
}
