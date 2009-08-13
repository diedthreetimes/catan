import java.util.*;
public class Settlement extends Point {
	public Settlement( Point p ){
		super( p.x, p.y, p.resources );
	}
	
	public List<Card> roll(int num){
		List<Card> ans = new LinkedList<Card>() ;
		for (Resource r : resources){
			if( r.getNumber() == num )
				ans.add(r.produceSettlementCard());
				
		}
		return ans;
	}
}
