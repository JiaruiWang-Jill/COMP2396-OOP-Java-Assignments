
/**model a hand of type single
 * @author Jerry
 *
 */
public class Single extends Hand{
	private static final long serialVersionUID = -3711761437629470849L;
	public Single(CardGamePlayer player, CardList cards) {
		super(player, cards);
	}
	boolean isValid() {
		if(size()==1) {
			return true;
		}
		return false;
	}

	String getType() {
		return "Single";
	}
}
