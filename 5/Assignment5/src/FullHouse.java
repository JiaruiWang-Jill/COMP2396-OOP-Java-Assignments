
/** a method for returning a string specifying the type HullHouse
 * @author Jerry
 *
 */
public class FullHouse extends Hand {
	private static final long serialVersionUID = -3711761437629470849L;
	public FullHouse(CardGamePlayer player, CardList cards) {
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
				if(hand.getType()=="Straight" || hand.getType()=="Flush") {
					return true;
				}else {
					return false;
				}
			}	
		}
	}
	
	public	Card getTopCard() {
		sort();
		Card a= getCard(2);
		CardList triple=new CardList();
		for(int i=0;i<5;i++) {
			if(getCard(i).getRank()==a.getRank()) {
				triple.addCard(getCard(i));
			}
		}
		int max=triple.getCard(0).getSuit();
		int max_index=0;
		for(int i=0;i<triple.size();i++) {
			if(triple.getCard(i).getSuit() > max) {
				max=triple.getCard(i).getSuit();
				max_index=i;
			}
			
		}
		
		return triple.getCard(max_index);
	}
	

	boolean isValid() {
		sort();
		if(size() == 5) {
			Card a=getCard(0);
			Card b = getCard(1);
			int a_equal=0;
			int b_equal=0;
			for(int i=1;i<5;i++) {
				if(getCard(i).getRank()!=a.getRank()) {
					b=getCard(i);
				}
			}
			
			for(int i=0;i<5;i++) {
				if(getCard(i).getRank()==a.getRank()) {
					a_equal++;
				}
				else if(getCard(i).getRank()==b.getRank()) {
					b_equal++;
				}
				else {
					return false;
				}
			}
			if((a_equal==3&&b_equal==2) || (a_equal==2&&b_equal==3)) {
				return true;
			}
			
		}
		return false;
	}
			
	
	String getType() {
		return "FullHouse";
	}
}
