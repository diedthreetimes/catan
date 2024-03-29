import java.util.LinkedList;
import java.util.List;


public class City extends Settlement {
	public City( Settlement s ){
		super( s );
	}
	public List<Card> roll(int num){
		List<Card> ans = new LinkedList<Card>() ;
		for (Resource r : resources){
			if( r.producesForRoll(num)) {
				ans.add(r.produceCard());
				ans.add(r.produceCard());
			}	
		}
		return ans;
	}
}
