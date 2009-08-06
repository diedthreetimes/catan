import java.util.LinkedList;
import java.util.List;


public class City extends Settlement {
	public List<Card> roll(int num){
		List<Card> ans = new LinkedList<Card>() ;
		for (Resource r : resources){
			if( r.getNumber() == num ){
				ans.add(r.produceSettlementCard());
				ans.add(r.produceCityCard());
			}	
		}
		return ans;
	}
}
