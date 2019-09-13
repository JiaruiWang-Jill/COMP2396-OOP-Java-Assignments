import java.util.ArrayList;

/**
 * The BigTwo class implements the CardGame interface. It is used to model a Big
 * Two card game.
 * 
 * @author Jerry
 *
 */
public class BigTwo implements CardGame {

	/**
	 * a constructor for creating a Big Two card game. You should (i) create 4
	 * players and add them to the list of players; and (ii) create a Big Two table
	 * which builds the GUI for the game and handles user actions.
	 * 
	 */
	public BigTwo() {
		playerList = new ArrayList<CardGamePlayer>();
		handsOnTable = new ArrayList<Hand>();
		table = new BigTwoTable(this);
		CardGamePlayer player1 = new CardGamePlayer();
		playerList.add(player1);
		CardGamePlayer player2 = new CardGamePlayer();
		playerList.add(player2);
		CardGamePlayer player3 = new CardGamePlayer();
		playerList.add(player3);
		CardGamePlayer player4 = new CardGamePlayer();
		playerList.add(player4);

		currentIdx = -1;

	}

	private Deck deck;
	private ArrayList<CardGamePlayer> playerList;
	private ArrayList<Hand> handsOnTable;
	private int currentIdx;
	private BigTwoTable table;


	/*
	 * (non-Javadoc)
	 * 
	 * @see CardGame#getNumOfPlayers()
	 */
	public int getNumOfPlayers() {
		return playerList.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CardGame#getDeck()
	 */
	public Deck getDeck() {
		return deck;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CardGame#getPlayerList()
	 */
	public ArrayList<CardGamePlayer> getPlayerList() {
		return playerList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CardGame#getHandsOnTable()
	 */
	public ArrayList<Hand> getHandsOnTable() {
		return handsOnTable;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CardGame#getCurrentIdx()
	 */
	public int getCurrentIdx() {
		return currentIdx;
	}

	/**
	 * @param cardList The cardList to be printed
	 */
	public void print(CardList cardList) {
		print(cardList, true, false);
	}

	/**
	 * Prints the cards in this list to message area.
	 * @param cardList the card to be printed
	 * @param printFront
	 *            a boolean value specifying whether to print the face (true) or
	 *            the black (false) of the cards
	 * @param printIndex
	 *            a boolean value specifying whether to print the index in front
	 *            of each card
	 */
	public void print(CardList cardList, boolean printFront, boolean printIndex) {
		ArrayList<Card> cards = new ArrayList<Card>();
		for (int i = 0; i < cardList.size(); i++) {
			cards.add(cardList.getCard(i));
		}
		if (cards.size() > 0) {
			for (int i = 0; i < cards.size(); i++) {
				String string = "";
				if (printIndex) {
					string = i + " ";
				}
				if (printFront) {
					string = string + "[" + cards.get(i) + "]";
				} else {
					string = string + "[  ]";
				}
				if (i % 13 != 0) {
					string = " " + string;
				}
				table.printMsg(string);
				if (i % 13 == 12 || i == cards.size() - 1) {
					table.printMsg("");
				}
			}
		} else {
			table.printMsg("\n[Empty]");
		}
	}

	/* (non-Javadoc)
	 * @see CardGame#start(Deck)
	 */
	public void start(Deck deck) {
		for (int i = 0; i < playerList.size(); i++) {
			playerList.get(i).removeAllCards();
		}

		handsOnTable.clear();
		table.clearMsgArea();
		table.reset();
		BigTwoDeck bigTwoDeck = new BigTwoDeck();
		bigTwoDeck.shuffle();
		for (int i = 0; i < playerList.size(); i++) {
			for (int j = 0; j < 52 / playerList.size(); j++) {
				playerList.get(i).addCard(deck.getCard(0));
				if (deck.getCard(0).getRank() == 2 && deck.getCard(0).getSuit() == 3) {
					currentIdx = i;
				}
				deck.removeCard(0);
			}
			playerList.get(i).sortCardsInHand();
		}

		table.printMsg("\n"+playerList.get(currentIdx).getName() + "'s turn: ");
		table.setActivePlayer(currentIdx);
		table.repaint();
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see CardGame#makeMove(int, int[])
	 */
	public void makeMove(int playerID, int[] cardIdx) {
		checkMove(playerID, cardIdx);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CardGame#checkMove(int, int[])
	 */
	public void checkMove(int playerID, int[] cardIdx) {
		currentIdx=playerID;
		if(handsOnTable.size() !=0) {
			if(playerList.get(currentIdx).getName() == handsOnTable.get(handsOnTable.size() - 1).getPlayer().getName()){
			
				int [] b = cardIdx;
				if(b == null) {
					table.printMsg("\nPass");
					table.printMsg("\nNot legal move, you must cout some hand");
					
					return;
				}
				else{

					CardList current_list=playerList.get(currentIdx).play(b);
					if(composeHand(playerList.get(currentIdx),current_list) ==null ) {  
						print(current_list);
						table.printMsg("\nNot legal move ");
						
					}else {
						Hand current_hand =  composeHand(playerList.get(currentIdx),current_list);
						current_hand.sort();
						table.printMsg("\n"+current_hand.getType());
						print(current_hand);
						playerList.get(currentIdx).removeCards(current_hand);
						handsOnTable.add(current_hand);
						if(endOfGame()) {
							table.printMsg("\nGame Ends");
							for(int i=0;i<4;i++) {
								table.printMsg("\n"+playerList.get(i).getName()+" has "+playerList.get(i).getNumOfCards()+" cards in hand.");
							}	
							table.disable();
							return;
						}
						if(currentIdx<3) 
						{
							currentIdx++;
							table.setActivePlayer(currentIdx);
						}
						else 
						{
							currentIdx=0;
							table.setActivePlayer(currentIdx);
							
						}
	
						table.resetSelected();
						table.repaint();
						//repaint();
						return;
					
				}
			}
			}
		}
	
		
			int [] a = cardIdx;
			if(a == null) {
				table.printMsg("\nPass");
				if(endOfGame()) {
					table.printMsg("\nGame Ends");
					for(int i=0;i<4;i++) {
						table.printMsg("\n"+playerList.get(i).getName()+" has "+playerList.get(i).getNumOfCards()+" cards in hand.");
					}	
					table.disable();
					return;
				}
				if(currentIdx<3) {
					currentIdx++;
					table.setActivePlayer(currentIdx);
				
				}
				else {
					currentIdx=0;
					table.setActivePlayer(currentIdx);
					
				}

				table.resetSelected();
				table.repaint();
			}
			else 
			{
				CardList current_list=playerList.get(currentIdx).play(a);
				if( composeHand(playerList.get(currentIdx),current_list) == null) 
				{
					print(current_list);
					table.printMsg("\nNot legal move");
				}
				else 
				{ 	 	
					Hand current_hand =  composeHand(playerList.get(currentIdx),current_list);
					current_hand.sort();
					if(handsOnTable.size()==0) 
					{	
						//table.printMsg("\n"+player.getName() + "'s turn: ");
						table.printMsg("\n"+current_hand.getType());
						print(current_hand);
						playerList.get(currentIdx).removeCards(current_hand);
						handsOnTable.add(current_hand);
						if(endOfGame()) {
							table.printMsg("\nGame Ends");
							for(int i=0;i<4;i++) {
								table.printMsg("\n"+playerList.get(i).getName()+" has "+playerList.get(i).getNumOfCards()+" cards in hand.");
							}	
							table.disable();
							return;
						}
						if(currentIdx<3) 
						{
							currentIdx++;
							table.setActivePlayer(currentIdx);
					
						}
						else 
						{
							currentIdx=0;
							table.setActivePlayer(currentIdx);
							
						}

						table.resetSelected();
						table.repaint();
						//repaint();
						
					}
					else { 
						if(composeHand(playerList.get(currentIdx) , current_list).beats(handsOnTable.get(handsOnTable.size() - 1))) 
						{   
							table.printMsg("\n"+current_hand.getType());
							print(current_hand);
							playerList.get(currentIdx).removeCards(current_hand);
							
							handsOnTable.add(current_hand);
							if(endOfGame()) {
								table.printMsg("\nGame Ends");
								for(int i=0;i<4;i++) {
									table.printMsg("\n"+playerList.get(i).getName()+" has "+playerList.get(i).getNumOfCards()+" cards in hand.");
								}	
								table.disable();
								return;
							}
							if(currentIdx<3) 
							{
								currentIdx++;
								table.setActivePlayer(currentIdx);
							}
							else 
							{
								currentIdx=0;
								table.setActivePlayer(currentIdx);
							}

							table.resetSelected();
							table.repaint();
							//repaint();
							
						}
						else 
							{
							print(current_list);
							table.printMsg("\nNot legal move ");
							}	
					}
			}
		}
			table.printMsg("\n"+playerList.get(currentIdx).getName() + "'s turn: ");
			table.resetSelected();
			table.repaint();
	} 

	/*
	 * (non-Javadoc)
	 * 
	 * @see CardGame#endOfGame()
	 */
	public boolean endOfGame() {
		if (playerList.get(0).getNumOfCards() != 0 && playerList.get(1).getNumOfCards() != 0
				&& playerList.get(2).getNumOfCards() != 0 && playerList.get(3).getNumOfCards() != 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * a method for starting a Big Two card game. It should (i) create a Big Two
	 * card game; (ii) create and shuffle a deck of cards; and (iii) start the game
	 * with the deck of cards.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		BigTwo bigTwo = new BigTwo();
		BigTwoDeck deck = new BigTwoDeck();
		deck.shuffle();
		bigTwo.start(deck);

	}

	/**
	 * a method for
	 * 
	 * @param player
	 * @param cards
	 * @return returning a valid hand from the specified list of cards of the
	 *         player. Returns null is no valid hand can be composed from the
	 *         specified list of cards.
	 */
	public Hand composeHand(CardGamePlayer player, CardList cards) {
		if (cards.size() == 1) {
			Hand s = new Single(player, cards);
			if (s.isValid()) {
				return s;
			}
		} else if (cards.size() == 2) {
			Hand a = new Pair(player, cards);
			if (a.isValid()) {
				return a;
			}
		} else if (cards.size() == 3) {
			Hand b = new Triple(player, cards);
			if (b.isValid()) {
				return b;
			}
		} else if (cards.size() == 5) {
			Hand e = new Straight(player, cards);
			Hand f = new Flush(player, cards);
			Hand g = new FullHouse(player, cards);
			Hand h = new Quad(player, cards);
			Hand i = new StraightFlush(player, cards);

			if (e.isValid()) {
				return e;
			} else if (f.isValid()) {
				return f;
			} else if (g.isValid()) {
				return g;
			} else if (h.isValid()) {
				return h;
			} else if (i.isValid()) {
				return i;
			}

		}

		return null;
	}

}
