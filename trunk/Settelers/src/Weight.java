public class Weight {
	public static final double [] PR = {
        1.0/36.0, 2.0/36, 3.0/36, 4.0/36,
        5.0/36, 6.0/36, 5.0/36, 4.0/36,
        3.0/36, 2.0/36 , 1.0/36 };
	
	private double timber 	= 1;
	private double ore 		= 1;
	private double wheat 	= 1;
	private double brick	= 1;
	private double sheep	= 1;
	
	public Weight (){
		
	}
	
	public Weight (double timber, double ore, double wheat, double brick, double sheep){
		this.timber = timber;
		this.ore = ore;
		this.wheat = wheat;
		this.brick = brick;
		this.sheep = sheep;
	}
	
	public double  getTimberWeight(){ return timber; }
	public double  getOreWeight(){ return ore; }
	public double  getWheatWeight(){ return wheat; }
	public double  getBrickWeight(){ return brick; }
	public double  getSheepWeight(){ return sheep; }
	
	public double calculatePointWeight(Point p){
		double t = 0, o = 0, w = 0, b = 0, s = 0;
		for ( Resource r : p.getResources() ){
			Card.Type type = r.produceCard().getType();
			if( type.equals(Card.Type.BRICK) )
				b += PR[r.getNumber()];
			if( type.equals(Card.Type.ORE) )
				o += PR[r.getNumber()];
			if( type.equals(Card.Type.WHEAT) )
				w += PR[r.getNumber()];
			if( type.equals(Card.Type.WOOD) )
				t += PR[r.getNumber()];
			if( type.equals(Card.Type.SHEEP) )
				s += PR[r.getNumber()];
		}
		return t * timber + o * ore + w * wheat + b * brick + s * sheep;		 
	}
}
