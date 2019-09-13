
/** model a hand of the type Triple
 * @author Jerry
 *
 */
public class Triple extends Hand{
	private static final long serialVersionUID = -3711761437629470849L;
	public Triple(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}


	
	boolean isValid() {
		sort();
		if(size() == 3) {
			if(getCard(0).getRank()==getCard(1).getRank()) {
				if(getCard(1).getRank()==getCard(2).getRank()) {
					return true;
				}
				return false;
			}
			return false;
		}
		return false;
	}
			
	
	String getType() {
		return "Triple";
	}

}
