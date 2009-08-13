import java.util.*;

public class Point {
    protected final int x;
    protected final int y;
    protected final Resource [] resources;

    public Point(int x, int y, Resource... hexes){
        this.x = x;
        this.y = y;
        resources = hexes;
    }

    /**
     * NOTE: this logic explicitly assumes single player.
     */
    public boolean canPlaceSettlement(final Board b, final Collection<City> cities, final Collection<Settlement> settlements) {
        for (final City city : cities) if (equals(city)) {
            return false;
        }
        for (final Settlement settlement : settlements) if (equals(settlement)) {
            return false;
        }
        for (final Point neighbor : b.getNeighbors(this)) {
            for (final City city : cities) if (neighbor.equals(city)) {
                return false;
            }
            for (final Settlement settlement : settlements) if (neighbor.equals(settlement)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(final Object o) {
        return (o instanceof Point)
            && (((Point)o).x == x)
            && (((Point)o).y == y);
    }
}
