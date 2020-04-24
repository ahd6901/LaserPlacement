package lasers.backtracking;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 *  class to represent a single tile in the safe.
 *
 * @author Shubhang Mehrotra
 */
public class Tile {
    private String tile;
    private Set<Tile> neighbours;

    /**
     *  constructor method for a tile object
     * @param tile
     */
    public Tile(String tile){
        this.tile = tile;
        this.neighbours = new HashSet<Tile>();
    }

    /**
     *  method to get the tile
     * @return : tile
     */
    public String getName() {
        return this.tile;
    }

    /**
     *  method to get the adjacent tiles
     * @return : tiles adjacent to the tile
     */
    public Collection<Tile> getNeighbors() {
        return this.neighbours;
    }

    /**
     *  method to add a new adjacent tile
     * @param neighbour
     */
    public void addNeighbour(Tile neighbour) {
        this.neighbours.add(neighbour);
    }

    @Override
    public String toString() {
        return this.tile;
    }

    @Override
    public int hashCode() {
        return tile.hashCode();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        // two tiles are equal if their names are equal
        return this.tile.equals(tile.tile);
    }






}
