
/** a method for returning a string specifying the type Flush
 * @author Jerry
 *
 */
public class Flush extends Hand{
	private static final long serialVersionUID = -3711761437629470849L;
	public Flush(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}
	
	public boolean beats(Hand hand) {
		sort();
		hand.sort();
		Card one = hand.getTopCard();
		Card two = getTopCard();
		
		if(size()!=hand.size()) {
			return false;
		}
		else {	
			if(getType() == hand.getType()) {
				if(one.compareTo(two)==1) {
					return true;
				}else {
					return false;
				}
			}
			else {
				if(hand.getType()=="Straight") {
					return true;
				}else {
					return false;
				}
			}	
		}
	}
	


	boolean isValid() {
		sort();
		if(size() == 5) {
			for(int i=1;i<5;i++) {
				if(getCard(i).getSuit()!=getCard(i-1).getSuit()) {
					return false;
				}
			}	
			return true;
		}
		return false;
	}
			
	
	String getType() {
		return "Flush";
	}

}
