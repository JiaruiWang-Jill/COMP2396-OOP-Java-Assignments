
/**
 * The Hand class is a subclass of the CardList class, and is used to model a hand of cards. It has a
 * private instance variable for storing the player who plays this hand. It also has methods for
 * getting the player of this hand, checking if it is a valid hand, getting the type of this hand, getting
 * the top card of this hand, and checking if it beats a specified hand. Below is a detailed
 * description for the Hand class.
 * @author Jerry
 *
 */
abstract public class Hand extends CardList{
	private static final long serialVersionUID = -3711761437629470849L;
	/**
	 *   a constructor for building a hand with the specified player and list of cards.
	 * @param player
	 * 			a CardGamePlyer
	 * @param cards
	 * 			a Cardlist cards
	 */
	public Hand(CardGamePlayer player, CardList cards) {
		this.player=player;
		for(int i=0;i<cards.size();i++) {
			Card temp = cards.getCard(i);
			addCard(temp);
		}
	}
	private CardGamePlayer player;//  the player who plays this hand.

	/**  a method for retrieving the player of this hand.
	 * @return
	 * 		the CardGamePlayer
	 */
	public	CardGamePlayer getPlayer() {
		return player;
	}
	
	/**  a method for retrieving the top card of this hand.
	 * @return
	 * 			the top card
	 */
	public	Card getTopCard() {
		sort();
		return getCard(size()-1);
	}
	
		/** a method for checking if this hand beats a specified hand.
		 * @param hand
		 * 				the tested type
		 * @return
		 * 			a boolean
		 */
	public boolean beats(Hand hand) {
		sort();
		Card one = hand.getTopCard();
		Card two = getTopCard();
		
		if(size()!=hand.size()) {
			return false;
		}
		else {	
					if(one.compareTo(two)==-1) {
						return true;
					}else {
						return false;
					}
		}
	}
	
	
	/**
	 *  a method for checking if this is a valid hand.
	 * @return
	 * 		the boolean value
	 */
	abstract boolean isValid();
	
	
	/** a method for returning a string specifying the type of this hand.
	 * @return
	 * return the string
	 */
	abstract String getType();

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
