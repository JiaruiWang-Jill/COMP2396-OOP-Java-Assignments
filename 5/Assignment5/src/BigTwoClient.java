import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.JOptionPane;

/**
 * @author Jerry
 *
 */
public class BigTwoClient implements CardGame, NetworkGame {

	/**
	 *  – a constructor for creating a Big Two client. You should (i) create 4
	*players and add them to the list of players; (ii) create a Big Two table which builds the
	*GUI for the game and handles user actions; and (iii) make a connection to the game
	*server by calling the makeConnection() method from the NetworkGame interface.
	 */
	public BigTwoClient() {

		try {
			sock=new Socket("127.0.0.1",2396);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		try {
			oos =new ObjectOutputStream(sock.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		playerList = new ArrayList<CardGamePlayer>();
		handsOnTable = new ArrayList<Hand>();
		for(int i=0;i<4;i++) {
			CardGamePlayer a = new CardGamePlayer();
			playerList.add(a);
		}
		
		table = new BigTwoTable(this);
		
		makeConnection();
		currentIdx=-1;
	}
	
	private int numOfPlayers;						 //– an integer specifying the number of players.
	private Deck deck;								// – a deck of cards.
	private ArrayList<CardGamePlayer> playerList;	// – a list of players.
	private ArrayList<Hand> handsOnTable;			// – a list of hands played on the table.
	private int playerID ;							//– an integer specifying the playerID (i.e., index) of the local player.
	private String playerName ;						//– a string specifying the name of the local player.
	private String serverIP ;						//– a string specifying the IP address of the game server.
	private int serverPort ;						//– an integer specifying the TCP port of the game server.
	private Socket sock ;							//– a socket connection to the game server.
	private ObjectOutputStream oos;					// – an ObjectOutputStream for sending messages to the server.
	private int currentIdx;							// – an integer specifying the index of the player for the current turn.
	private BigTwoTable table;						// – a Big Two table which builds the GUI for the game and handlesall user actions.
	private int startornot;
	
	
	/**
	 * getting the integer representing whether this game start or not
	 * @return integer startornot
	 */
	public int getStartOrNot() {
		return startornot;
	}
	
	/**
	 * getting the objectoutputstream oos
	 * @return objectoutputstream
	 */
	public ObjectOutputStream getObjectOutputStream() {
		return oos;
	}
	/**
	 * – a method for getting the number of players.
	
	 * @return
	 */
	public int getNumOfPlayers() {
		return numOfPlayers;
	}
	
	
	/**a method for setting the number of players
	 * @param i
	 */
	public void setNumOfPlayer(int i) {
		numOfPlayers=i;
	}
	
	/**
	 * – a method for getting the deck of cards being used.
	 * @return
	 */
	public Deck getDeck() {
		return deck;
	}
	
	/**
	 * – a method for getting the list of players.
	 * @return
	 */
	public ArrayList<CardGamePlayer> getPlayerList(){
		return playerList;
	}
	
	/**
	 * – a method for getting the list of hands played on
	 * the table.
	 * @return
	 */
	public ArrayList<Hand> getHandsOnTable() {
		return handsOnTable;
	}
	/**
	 * –a method for getting the index of the player for the current turn.
	
	 * @return
	 */
	public int getCurrentIdx() {
		return currentIdx;
	}
	
	/**
	 * 
	– a method for starting/restarting the game with a given
	shuffled deck of cards.
	 * @param deck
	 */
	public void start(Deck deck) {
		//table.reset();
		for(int i=0;i<4;i++) {
			playerList.get(i).removeAllCards();
		}
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
		
		table.setActivePlayer(currentIdx);

		if(playerID ==currentIdx) {
			table.enable();

		}else {
			table.disable();

		}
		table.printMsg("\n"+playerList.get(currentIdx).getName()+" 's turn: ");;
		table.repaint();
	}  
	/**
	 * – a method for making a move by a
	player with the specified playerID using the cards specified by the list of indices. This
	method should be called from the BigTwoTable when the local player presses either the
	“Play” or “Pass” button. 
	 * @param playerID
	 * @param cardIdx
	 */
	public void makeMove(int playerID, int[] cardIdx) {
		sendMessage(new CardGameMessage (CardGameMessage.MOVE,-1,cardIdx));
		
		//checkMove(playerID, cardIdx);
	}
	
	
	
	/**
	 * – a method for checking a move made by a player. 
	 * @param playerID
	 * @param cardIdx
	 */
	public void checkMove(int playerID, int[] cardIdx) {
		//table.printMsg("we check once!!");
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

						table.resetSelected();
						table.repaint();
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

				table.resetSelected();
				table.repaint();
				if(currentIdx<3) {
					currentIdx++;
					table.setActivePlayer(currentIdx);
				
				}
				else {
					currentIdx=0;
					table.setActivePlayer(currentIdx);
					
				}

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

						table.resetSelected();
						table.repaint();
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
								
								table.disable();
								return;
							}

							table.resetSelected();
							table.repaint();
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
			//table.printMsg("\ncurrentIdx testing should be" + currentIdx);
			table.printMsg("\n"+playerList.get(currentIdx).getName() + "'s turn: ");
			table.resetSelected();
			table.repaint();
		
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
	
	
	/**
	 * – a method for checking if the game ends.
	 * @return boolean value of whether game ends
	 */
	public boolean endOfGame() {

		if (playerList.get(0).getNumOfCards() != 0 && playerList.get(1).getNumOfCards() != 0
				&& playerList.get(2).getNumOfCards() != 0 && playerList.get(3).getNumOfCards() != 0) {
			return false;
		} else {
			int s=currentIdx;
			String outputMessage="Result is here:";
			outputMessage+="\n"+playerList.get(s).getName()+" WINS!!";
			for(int i=0;i<4;i++) {
				
				outputMessage +="\n"+playerList.get(i).getName()+" has "+playerList.get(i).getNumOfCards()+" cards in hand.";
			}	
			
			outputMessage +="\nBye BigTWo, HHHH!! \nGo back to review my fianl T_T";
		       ImageIcon icon = new ImageIcon("yeh.png");
		        JOptionPane.showMessageDialog(table.frame, outputMessage,
		                "Game Result", JOptionPane.INFORMATION_MESSAGE, icon);
			sendMessage(new CardGameMessage(CardGameMessage.READY,-1,null));
			return true;
		}
	}

	@Override
	public int getPlayerID() {
		return playerID;
	}

	@Override
	public void setPlayerID(int playerID) {
		this.playerID=playerID;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName=playerName;
	}

	@Override
	public String getServerIP() {
		return serverIP;
	}

	@Override
	public void setServerIP(String serverIP) {
		this.serverIP=serverIP;
	}

	@Override
	public int getServerPort() {
		return serverPort;
	}

	@Override
	public void setServerPort(int serverPort) {
		this.serverPort=serverPort;
	}

	@Override
	public void makeConnection() {
		
		
		try {
			

			Thread t = new Thread(new ServerHandler(sock));
			t.start();

			oos.writeObject(new CardGameMessage(
					CardGameMessage.JOIN, -1, playerName));
			oos.flush();

			oos.writeObject(new CardGameMessage(
					CardGameMessage.READY, -1, null));
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void parseMessage(GameMessage message) {
				// parses the message based on it type
				switch (message.getType()) {
				case CardGameMessage.PLAYER_LIST:
					
						playerID = message.getPlayerID();
						String[] namearray=(String []) message.getData();
						//namearray[playerID]=playerName;
						for(int i=0;i<4;i++) {
							if(namearray[i]!=null) {
								playerList.get(i).setName(namearray[i]);
							}
						}
						
					break;
				case CardGameMessage.JOIN:
					
					playerList.get(message.getPlayerID()).setName((String) message.getData());
					//sendMessage(message);
					break;
				case CardGameMessage.FULL:
					table.printMsg("\nclient is full and  cannt join game");
					//到底要不要exit(0)
					table.out();
					System.exit(0);
					break;
				case CardGameMessage.READY:
					table.printMsg("\n"+message.getPlayerID()+" is ready");
					//sendMessage(message);
					break;
				case CardGameMessage.QUIT:
					playerList.get(message.getPlayerID()).setName("");
					
					if(startornot == 1){
						//停止游戏？？？是这样吗？
						table.disable();
						sendMessage(new CardGameMessage(CardGameMessage.QUIT,-1,null));
					}
					try {
						oos.writeObject(new CardGameMessage(CardGameMessage.QUIT, -1, null));
					} catch (IOException e) {
						e.printStackTrace();
					}
						System.exit(0);
					//}
					break;
				case CardGameMessage.START:
					table.printMsg("\nGame Start!!");
					//sendMessage(message);
					deck = (BigTwoDeck)message.getData();
					startornot=1;
					start(deck);
					
					break;
				case CardGameMessage.MOVE:
					currentIdx = message.getPlayerID();
					int disable =currentIdx;
					if(currentIdx <3) {
						disable =currentIdx +1;
					}else {
						disable =0;
					}
					if(playerID==disable) {
						
						table.enable();
					}else {
						table.disable();
//
//						table.printMsg("I disable the "+playerID+" from line 551");
					}
					table.repaint();
					checkMove(message.getPlayerID(),(int[])message.getData());
					
					//sendMessage(message);
					break;
				case CardGameMessage.MSG:
					
					table.printChat("\n"+(String)message.getData());
					//table.repaint();
					//sendMessage(message);
					break;
				default:
					System.out.println("Wrong message type: " + message.getType());
					// invalid message
					break;
				}
	}

	@Override
	public  synchronized void sendMessage(GameMessage message) {

				try {
					oos.writeObject(message);
					oos.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		
	}

    /**
     * Inner class which is used for receiving incoming messages from a server
     * @author Jerry
     *
     */
    public class ServerHandler implements Runnable{
		private ObjectInputStream oistream; // ObjectInputStream of the client
		private Socket clientSocket;
         public ServerHandler(Socket socket) {
        	 	
        	 	clientSocket = socket;
        		try {
    				oistream = new ObjectInputStream(clientSocket.getInputStream());
    			} catch (IOException e1) {
    				e1.printStackTrace();
    			}
         }
    	
		@Override
		public void run() {
		
			CardGameMessage message;
			try {
				
				while ((message =  (CardGameMessage) oistream.readObject()) != null) {
					parseMessage(message);
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
    	
    }
	
    
    /**
     * a method for creating an instance of BigTwoClient
     * @param args
     */
    public static void main(String[] args) {
    	BigTwoClient bigTwoClient=new BigTwoClient();
    }
    
    /**
     * method for 
     * returning a valid hand from the specified list of cards of the player. Returns null is no
     * valid hand can be composed from the specified list of cards.
     * @param player
     * @param cards
     * @return
     */
    public static Hand composeHand(CardGamePlayer player, CardList cards) {
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
