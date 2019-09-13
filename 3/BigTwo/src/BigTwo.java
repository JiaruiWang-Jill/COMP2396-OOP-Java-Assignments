import java.util.ArrayList;


/**
 * @author Jerry
 *The BigTwo class is used to model a Big Two card game. It has private instance variables for
 *storing a deck of cards, a list of players, a list of hands played on the table, an index of the
 *current player, and a console for providing the user interface.
 */
public class BigTwo {
	/**
	 * a constructor for creating a Big Two card game.
	 */
	public BigTwo() {
		playerList=new ArrayList<CardGamePlayer>();
		handsOnTable=new ArrayList<Hand>();
		
		CardGamePlayer player1 = new CardGamePlayer();
		playerList.add(player1);
		CardGamePlayer player2 = new CardGamePlayer();		
		playerList.add(player2);
		CardGamePlayer player3 = new CardGamePlayer();
		playerList.add(player3);
		CardGamePlayer player4 = new CardGamePlayer();	
		playerList.add(player4);
		bigTwoConsole = new BigTwoConsole(this);
		currentIdx=-1;
		
	} 
	
	
	
	private Deck deck ;//– a deck of cards.
	private ArrayList<CardGamePlayer> playerList;//– a list of players.
	private ArrayList<Hand> handsOnTable;//– a list of hands played on the table.
	private int currentIdx;// – an integer specifying the index of the current player.
	private BigTwoConsole bigTwoConsole;// – a BigTwoConsole object for providing the user interface


	/**– a method for retrieving the deck of cards being used.
	 * @return
	 *       a deck type
	 */
	public Deck getDeck() {
		return deck;		
	}
	
	public void  setCurrentIndex(int currentIdx) {
		this.currentIdx = currentIdx;		
	}
	
	public void  setBigTwoCosnole(BigTwoConsole bigTwoConsole) {
		this.bigTwoConsole = bigTwoConsole;		
	}
	
	
	/**– a method for retrieving the list of players.
	 * @return
	 * a list of players
	 */
	public ArrayList<CardGamePlayer> getPlayerList(){
		return playerList;
	}
	
	
	/** a method for retrieving the list of hands played on	the table.
	 * @return
	 * a list of hands played on the table 
	 */
	public ArrayList<Hand> getHandsOnTable(){
		
		return handsOnTable;
	}
	
	
	
	
	/**– a method for retrieving the index of the current player.
	 * @return
	 * the index of current player
	 */
	public int getCurrentIdx() {
		return currentIdx;
		
	}
	/**  a method for starting the game with a (shuffled) deck of
	 * cards supplied as the argument. It implements the Big Two game logics.
	 * @param deck
	 */
	public void start(BigTwoDeck deck) {
		this.deck=deck;
		for (int i=0;i<4;i++) {
			for(int j=0; j<13;j++) {
			playerList.get(i).addCard(deck.getCard(0));
			if(deck.getCard(0).getRank()==2 && deck.getCard(0).getSuit()==3) {
				currentIdx=i;
			}
			deck.removeCard(0);
			}
			playerList.get(i).sortCardsInHand();
		}
		
		bigTwoConsole.setActivePlayer(currentIdx);
		bigTwoConsole.repaint();
		while(playerList.get(0).getNumOfCards()!=0 && playerList.get(1).getNumOfCards()!=0 && playerList.get(2).getNumOfCards()!=0 && playerList.get(3).getNumOfCards()!=0 ) {
			if(handsOnTable.size() !=0) {
				
			if(playerList.get(currentIdx).getName() == handsOnTable.get(handsOnTable.size() - 1).getPlayer().getName()){
			
				int [] b = bigTwoConsole.getSelected();
				if(b == null) {
					System.out.println("Not legal move, you must cout some hand");
					continue;
				}
				CardList current_list=playerList.get(currentIdx).play(b);
				if(composeHand(playerList.get(currentIdx),current_list) ==null ) {
					System.out.println("Not legal move");
					continue;
				}
				else{
					Hand current_hand =  composeHand(playerList.get(currentIdx),current_list);
					current_hand.sort();
					System.out.println(current_hand.getType());
					current_hand.print();
					playerList.get(currentIdx).removeCards(current_hand);
					handsOnTable.add(current_hand);
					if(currentIdx<3) 
					{
						currentIdx++;
						bigTwoConsole.setActivePlayer(currentIdx);
					}
					else 
					{
						currentIdx=0;
						bigTwoConsole.setActivePlayer(currentIdx);
						
					}
					bigTwoConsole.repaint();
					continue;
				}
			}
			}
	
		
			int [] a = bigTwoConsole.getSelected();
			if(a == null) {
				System.out.println("Pass");
				if(currentIdx<3) {
					currentIdx++;
					bigTwoConsole.setActivePlayer(currentIdx);
				
				}
				else {
					currentIdx=0;
					bigTwoConsole.setActivePlayer(currentIdx);
					
				}
				bigTwoConsole.repaint();
			}
			else 
			{
				CardList current_list=playerList.get(currentIdx).play(a);
				if( composeHand(playerList.get(currentIdx),current_list) == null) 
				{
					System.out.println("Not legal move");
				}
				else 
				{ 	 	
					Hand current_hand =  composeHand(playerList.get(currentIdx),current_list);
					current_hand.sort();
					if(handsOnTable.size()==0) 
					{	
						System.out.println(current_hand.getType());
						current_hand.print();
						playerList.get(currentIdx).removeCards(current_hand);
						handsOnTable.add(current_hand);
						if(currentIdx<3) 
						{
							currentIdx++;
							bigTwoConsole.setActivePlayer(currentIdx);
					
						}
						else 
						{
							currentIdx=0;
							bigTwoConsole.setActivePlayer(currentIdx);
							
						}
						bigTwoConsole.repaint();
						
					}
					else { 
						if(composeHand(playerList.get(currentIdx) , current_list).beats(handsOnTable.get(handsOnTable.size() - 1))) 
						{   
							System.out.println(current_hand.getType());
							current_hand.print();
							
							playerList.get(currentIdx).removeCards(current_hand);
							handsOnTable.add(current_hand);
							if(currentIdx<3) 
							{
								currentIdx++;
								bigTwoConsole.setActivePlayer(currentIdx);
							}
							else 
							{
								currentIdx=0;
								bigTwoConsole.setActivePlayer(currentIdx);
							}
							
						}
						else 
							{
							System.out.println("Not legal move");
							}	
					}
			}
		}
		}
		System.out.println("Game Ends");
		for(int i=0;i<4;i++) {
			System.out.println(playerList.get(i).getName()+" has "+playerList.get(i).getNumOfCards()+" cards in hand.");
		}	
		
		
	}


	
	/**  a method for returning a valid hand from the specified list of cards of the player. 
	 * Returns null is no valid hand can be composed from the specified list of cards.
	 * @param player
	 * 			a cardGamePlayer
	 * @param cards
	 *  		a cardlist
	 *  
	 * @return
	 * 			a valid hand from the specified list of cards of the palyer
	 */
	public static Hand composeHand(CardGamePlayer player, CardList cards) {
		if(cards.size() == 1) {
			Hand s = new Single(player,cards);
			if(s.isValid()) {
				return s;
			}
		}
		else if(cards.size()==2) {
			Hand a = new Pair(player,cards);
			if(a.isValid()) {
				return a;
			}
		}
		else if(cards.size()==3) {
			Hand b = new Triple(player,cards);
			if(b.isValid()) {
				return b;
			}
		}
		else if(cards.size()==5) {
			Hand e = new Straight(player,cards);
			Hand f = new Flush(player,cards);
			Hand g = new FullHouse(player,cards);
			Hand h= new Quad(player,cards);
			Hand i = new StraightFlush(player,cards);
			
			if(e.isValid()) {
				return e;
			}else if(f.isValid()) {
				return f;
			}else if(g.isValid()) {
				return g;
			}else if(h.isValid()) {
				return h;
			}else if(i.isValid()) {
				return i;
			}
			
			
		}
		
		return null;
		
	}


	/** – a method for starting a Big Two card game. It should create a
		Big Two card game, create and shuffle a deck of cards, and start the game with the deck of
		cards.
	 * @param args
	 */
	public static void main(String[] args) {
		BigTwo game = new BigTwo();
		BigTwoDeck bigTwoDeck = new BigTwoDeck();
		bigTwoDeck.shuffle();
		game.start(bigTwoDeck);
		
	}

}

