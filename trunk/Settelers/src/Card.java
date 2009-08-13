
public class Card implements Cloneable{
	/**
	 * enum builds in the validation logic we need.
	 */
	public static enum Type {WOOD, BRICK, ORE, WHEAT, SHEEP};
	
	private final Type type;
	
	public Card( final Type type ){
		this.type = type;
	}

	public Card clone(){
		return new Card(type);
	}

	public Card.Type getType() {
	    return type;
        }
	
	public String toString(){
		return "[" + type + "]";
	}
	
	public boolean equals(Object o){
		if (!(o instanceof Card))
			return false;
		return ((Card)o).type.equals(this.type);
	}
}
