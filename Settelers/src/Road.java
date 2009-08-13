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

    public Set<Point> getAllNeighbors(final Board b) {
        final Set<Point> neighbors = new HashSet<Point>();
        neighbors.addAll(b.getNeighbors(start));
        neighbors.addAll(b.getNeighbors(end));
        neighbors.remove(start);
        neighbors.remove(end);
        return neighbors;
    }
}
