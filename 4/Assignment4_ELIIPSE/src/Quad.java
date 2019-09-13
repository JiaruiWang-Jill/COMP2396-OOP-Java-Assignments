
/**model a hand of the type Quad
 * @author Jerry
 *
 */
public class Quad extends Hand {
	private static final long serialVersionUID = -3711761437629470849L;
	public Quad(CardGamePlayer player, CardList cards) {
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
						if(hand.getType()=="StraightFlush") {
							return false;
						}else {
							return true;
						}
						}	
		}
	}
	
	public	Card getTopCard() {
		sort();
		Card a=getCard(2);
		Card b =new Card(3,a.getRank());
		return b;
	}
	
	
	boolean isValid() {
		sort();
		if(size()==5) {
			Card a =getCard(0);
			int rank_equal =0;
			int rank_inequal=0;
			for(int i=0;i<5;i++)
			{  Card b = getCard(i);
				if(a.getRank()==b.getRank()) {
					rank_equal++;
				}
				else {
					rank_inequal++;
				}
			}
			
			if((rank_equal == 4  && rank_inequal == 1 )|| (rank_equal == 1  && rank_inequal == 4)) {
					return true;
			}
		}
		return false;
	}

	
	String getType() {
		return "Quad";
	}

}
