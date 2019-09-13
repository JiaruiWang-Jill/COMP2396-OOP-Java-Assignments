
/**The BigTwoCard class is a subclass of the Card class, and is used to model a card used in a Big
 * Two card game. It should override the compareTo() method it inherited from the Card class to
 * reflect the ordering of cards used in a Big Two card game. Below is a detailed description for the BigTwoCard class.
 * @author Jerry
 *
 */
public class BigTwoCard extends Card {
	


	private static final long serialVersionUID = -5822018182891811204L;

	/**  a constructor for building a card with the specified
		suit and rank. suit is an integer between 0 and 3, and rank is an integer between 0 and 12.
	 * @param suit
	 * @param rank
	 */
	public BigTwoCard(int suit, int rank) {
		super(suit, rank);
	}
	

	/* (non-Javadoc)
	 * a method for comparing this card with the specified card for
		order. Returns a negative integer, zero, or a positive integer as this card is less than, equal
		to, or greater than the specified card.
	 * @see Card#compareTo(Card)
	 */
	public int compareTo(Card card) {
	
		int rank=this.getRank();
		if(rank == 1) {
			rank =14;
		}else if(rank ==0) {
			rank=13;
		}
		
		int rank2=card.getRank();
		if(rank2 == 1) {
			rank2 =14;
		}else if(rank2 ==0) {
			rank2=13;
		}
		
		if (rank > rank2) {
			return 1;
		} else if (rank < rank2) {
			return -1;
		} else if (this.suit > card.suit) {
			return 1;
		} else if (this.suit < card.suit) {
			return -1;
		} else {
			return 0;
		}
	}
	

}
