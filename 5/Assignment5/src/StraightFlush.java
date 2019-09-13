
/**model a hand of type StraightFlush
 * @author Jerry
 *
 */
public class StraightFlush extends Hand {
	private static final long serialVersionUID = -3711761437629470849L;
	public StraightFlush(CardGamePlayer player, CardList cards) {
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
						return true;
			}	
		}
	}

	
	boolean isValid() {
		sort();
		if(size()==5) {
			for(int i=1;i<5;i++) {
				int rank1=getCard(i).getRank();
				if(rank1 == 1) {
					rank1 =14;
				}else if(rank1 ==0) {
					rank1=13;
				}
				
				int rank2=getCard(i-1).getRank();
				if(rank2 == 1) {
					rank2 =14;
				}else if(rank2 ==0) {
					rank2=13;
				}
				
				
				if(rank1-rank2 != 1) {
					return false;
				}
			}
			if(getCard(0).getSuit()==getCard(1).getSuit() && getCard(1).getSuit()==getCard(2).getSuit() && getCard(2).getSuit()==getCard(3).getSuit() && getCard(3).getSuit()==getCard(4).getSuit()) {
				return true;
			}
			return false;
		}
		return false;
	}

	
	String getType() {
		return "StraightFlush";
	}
}
