import java.util.*;
public class Settlement extends Point {
	public List<Card> roll(int num){
		List<Card> ans = new LinkedList<Card>() ;
		for (Resource r : resources){
			if( r.getNumber() == num )
				ans.add(r.produceSettlementCard());
				
		}
		return ans;
	}
}
