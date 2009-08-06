
public class Card implements Cloneable{
	public static final int WOOD = 0;
	public static final int BRICK = 1;
	public static final int ORE = 2;
	public static final int WHEAT = 3;
	public static final int SHEEP = 4;
	
	private int val;
	
	public Card( int v ){
		if (v < 5)
			val = v;
		else
			throw new IllegalArgumentException("Invalid Resource");
	}
	public int hashCode(){
		return val;
	}
	public Card clone(){
		return new Card(val);
	}
	
	public String toString(){
		switch (val){
		case WOOD: return "[Wood]";
		case BRICK:return "[Brick]";
		case ORE: return "[Ore]";
		case WHEAT: return "[Wheat]";
		case SHEEP: return "[Sheep]";
		}	
		return null;
	}
}
