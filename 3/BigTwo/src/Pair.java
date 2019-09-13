
/**model a hand of type pair
 * @author Jerry
 *
 */
public class Pair extends Hand {
	private static final long serialVersionUID = -3711761437629470849L;
	public Pair(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}

	boolean isValid() {
		if(size()==2) {
			Card a =getCard(0);
			Card b =getCard(1);
			if(a.getRank()==b.getRank()) {
				return true;
			}
		}
		return false;
	}

	
	String getType() {
		return "Pair";
	}

}
