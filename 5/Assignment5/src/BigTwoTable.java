import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


/**
 * The BigTwoTable class implements the CardGameTable interface. It is used to build a GUI
 * for the Big Two card game and handle all user actions. 
 * @author Jerry
 *
 */
public class BigTwoTable implements CardGameTable{
	/**a constructor for creating a BigTwoTable. 
	 * @param game
	 * The parameter game is a reference to a card game associates with this table
	 */
	public BigTwoTable(BigTwoClient game){
		this.game=game;
		    // prompt the user to enter their name
		    name = JOptionPane.showInputDialog(frame, "What's your name?");
		    if(name !=null) {
				frame = new JFrame();
				frame.setSize(1100,900);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				msgArea = new JTextArea(100,30);
				msgArea.setFont(msgArea.getFont().deriveFont(15.0f)); 
				JScrollPane scroll = new JScrollPane (msgArea);
						
				msgArea.setOpaque(true);
				cardImages = new Image[4][13];
				avatars=new Image[4];
				bigTwoPanel=new BigTwoPanel();
				
				bigTwoPanel.setOpaque(true);
				bigTwoPanel.setBackground(Color.GREEN);  
				bigTwoPanel.addMouseListener(new BigTwoPanel());
				selected = new boolean[13];
				
				Dimension prefSize = bigTwoPanel.getPreferredSize();
				bigTwoPanel.setPreferredSize(prefSize);
				bigTwoPanel.setMaximumSize(prefSize);
				JPanel southPanel = new JPanel();
				playButton = new JButton("play");
				passButton = new JButton("pass");
				playButton.addActionListener(new PlayButtonListener());
				passButton.addActionListener(new PassButtonListener());
				southPanel.add(passButton);
				southPanel.add(playButton);
				bigTwoPanel.setLayout(new BorderLayout());
				bigTwoPanel.add(southPanel,BorderLayout.SOUTH);
				MenuBar mb = new MenuBar();
				Menu m = new Menu("File");
				MenuItem quit = new MenuItem("Quit");
				MenuItem connect = new MenuItem("Connect");
				quit.addActionListener(new QuitMenuItemListener());
				connect.addActionListener(new ConnectMenuItemListener());
				m.add(quit);
				m.add(connect);
				mb.add(m);
				
				incoming = new JTextArea(50,2);
				incoming.setLineWrap(true);
				incoming.setWrapStyleWord(true);
				incoming.setEditable(false);
				JScrollPane qScroller = new JScrollPane(incoming);
				qScroller
						.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				qScroller
						.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

				outgoing = new JTextField(20);
				outgoing.addActionListener(new FieldActionListenser());
				JPanel eastone = new JPanel();
				eastone.setLayout(new BoxLayout(eastone,BoxLayout.PAGE_AXIS));
				eastone.add(scroll);
				eastone.add(incoming);
				eastone.add(outgoing);
				frame.add(eastone,BorderLayout.EAST);
				frame.add(bigTwoPanel,BorderLayout.CENTER);

				frame.setMenuBar(mb);
				frame.setMinimumSize(new Dimension(950,850));
				frame.setVisible(true);
				

				game.setPlayerName(name);
		    	game.setServerIP("127.0.0.1");
		    	game.setServerPort(2396);
		    	enable();
		    	
		    }else {
			    System.exit(0);
			    
		    }
		    
		
	
	}
	
	private BigTwoClient game;
	private boolean[] selected;
	private int activePlayer;
	private JPanel bigTwoPanel;
	private JButton playButton;
	private JButton passButton;
	private JTextArea msgArea;
	private JTextArea incoming;
	private JTextField outgoing;
	private Image[][] cardImages;
	private Image cardBackImage;
	private Image[] avatars;
	public String name;
	public JFrame frame ;
	public JFrame login;
	public int numOfPlayers=0;
	private ArrayList<Card> cardsOnTable = new ArrayList<Card>();
	
	
	public void out() {
		frame.setVisible(false); //you can't see me!
		frame.dispose();
	}
	/* (non-Javadoc)
	 * @see CardGameTable#setActivePlayer(int)
	 */
	public void setActivePlayer(int activePlayer) {
		this.activePlayer =activePlayer;
	}
	
	/* (non-Javadoc)
	 * @see CardGameTable#getSelected()
	 */
	public int[] getSelected() {

		int[] cardIdx = null;
		int count = 0;
		for (int j = 0; j < selected.length; j++) {
			if (selected[j]) {
				count++;
			}
		}

		if (count != 0) {
			cardIdx = new int[count];
			count = 0;
			for (int j = 0; j < selected.length; j++) {
				if (selected[j]) {
					cardIdx[count] = j;
					count++;
				}
			}
		}
		return cardIdx;
		
	}
	
	
	/* (non-Javadoc)
	 * @see CardGameTable#resetSelected()
	 */
	public void resetSelected() {
		for(int i=0;i<selected.length;i++) {
			selected[i]=false;
		}
	}
	
	/**
	 * Resets the list of  cards on Table.
	 */
	public void resetCardsOnTable() {
			cardsOnTable=new ArrayList<Card>();
		
	}
	
	
	/* (non-Javadoc)
	 * @see CardGameTable#repaint()
	 */
	public void repaint() {
		frame.repaint();
	}
	
	
	/* (non-Javadoc)
	 * @see CardGameTable#printMsg(java.lang.String)
	 */
	public void printMsg(String msg) {
	        msgArea.append(msg);
	}
	
	public void printChat(String msg) {
		incoming.append(msg);
	}
	
	/* (non-Javadoc)
	 * @see CardGameTable#clearMsgArea()
	 */
	public void clearMsgArea() {
		msgArea.setText("");
	}
	
	
	/* (non-Javadoc)
	 * @see CardGameTable#reset()
	 */
	public void reset() {
		resetSelected();
		clearMsgArea();
		resetCardsOnTable();
		enable();
	}
	
	/* (non-Javadoc)
	 * @see CardGameTable#enable()
	 */
	public void enable() {

		playButton.setEnabled(true);
		passButton.setEnabled(true);
		bigTwoPanel.setEnabled(true);
	}
	
	/* (non-Javadoc)
	 * @see CardGameTable#disable()
	 */
	public void disable() {
		playButton.setEnabled(false);
		passButton.setEnabled(false);
		bigTwoPanel.setEnabled(false);
	}
	
	
	
	/**
	 * @param c The card
	 * @return a string contains the name of card, which is used to find the image of the card
	 */
	public String getImageName(Card c) {
		String cardname=null;
		int suit=c.getSuit();
		int rank=c.getRank();
		for(int i=0;i<4;i++) {
			for(int j=0;j<13;j++) {
				if(suit==i && rank==j) {
					if(i==0) {
						cardname =Integer.toString(j+1)+ "d.gif";
							
					}else if(i==1) {
						cardname =Integer.toString(j+1)+ "c.gif";
						
					}else if(i==2) {
						cardname =Integer.toString(j+1)+ "h.gif";
							
					}else if(i==3) {
						cardname =Integer.toString(j+1)+ "s.gif";
					}
				}	
			}
		}
		return cardname;
	}
	
	/**
	 * an inner class that extends the JPanel class and implements the
	 * MouseListener interface. Overrides the paintComponent() method inherited from the
	 * JPanel class to draw the card game table. Implements the mouseClicked() method from
	 * the MouseListener interface to handle mouse click events
	 * @author Jerry
	 *
	 */
	class BigTwoPanel extends JPanel implements MouseListener{
		private static final long serialVersionUID = 1L;
		/* (non-Javadoc)
		 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
		 */
		public void paintComponent(Graphics g) {
			int framex=frame.getWidth();
			int framey=frame.getHeight();
			g.drawImage(new ImageIcon("background.jpg").getImage(), 0, 0, framex, framey, this);
		    Font font = g.getFont().deriveFont( 15.0f );
		    g.setFont( font );
		    if(game.getStartOrNot()==1) {
		    	for(int i=0;i<4;i++) {
		    		if(i==game.getPlayerID()) {
		    			g.drawString("YOU,baby", framex/100, framey/50+i*(framey/6));
		    		}else {
		    			if(game.getPlayerList().get(i).getName()!=null) {

							g.drawString(game.getPlayerList().get(i).getName(), framex/100, framey/50+i*(framey/6));
		    			}/*else {
		    				g.drawString("没有名字呀", framex/100, framey/50+i*(framey/6));
		    			}*/
		    			
		    		}
				}
		    }else {
		    	for(int i=0;i<4;i++) {
					g.drawString("player"+i, framex/100, framey/50+i*(framey/6));
				}
		    }
			
			
			if(game.getHandsOnTable().size()!= 0) {
				g.drawString("played by player "+game.getHandsOnTable().get((game.getHandsOnTable().size())-1).getPlayer().getName(), framex/100, framey/50+4*(framey/6));
			}
			int number=game.getPlayerID();
			avatars[0]=new ImageIcon("0.png").getImage();
			avatars[1]=new ImageIcon("1.png").getImage();
			avatars[2]=new ImageIcon("2.png").getImage();
			avatars[3]=new ImageIcon("3.png").getImage();
			
			CardList current_list=new CardList();
			if(number!=-1) {
			CardGamePlayer palyer=game.getPlayerList().get(number);
			palyer.sortCardsInHand();
			current_list=palyer.getCardsInHand();
		
			cardBackImage=new ImageIcon("b.gif").getImage();
			String cardname=null;
			for(int i=0;i<4;i++) {
					g.drawImage(avatars[i], framex/100, framey/50+i*(framey/6)+framey/50, this); 

					if(i==game.getPlayerID()) {
						if(i!=game.getCurrentIdx()) {
							
							for(int j=0;j<game.getPlayerList().get(i).getNumOfCards();j++) {
								Card current =current_list.getCard(j);
								cardname=getImageName(current);
								cardImages[i][j] = new ImageIcon(cardname).getImage();
								g.drawImage(cardImages[i][j],framex/10+j*framex/50,framey/50+i*(framey/6),this);
								}
							}else {
								
								for(int j=0;j<game.getPlayerList().get(i).getNumOfCards();j++) {
									Card current =current_list.getCard(j);
									cardname=getImageName(current);
									cardImages[i][j] = new ImageIcon(cardname).getImage();
								
										if(selected[j]==true) {
											g.drawImage(cardImages[i][j],framex/10+j*framex/50,framey/50+i*(framey/6)-framey/50,this);
										}else {
										g.drawImage(cardImages[i][j],framex/10+j*framex/50,framey/50+i*(framey/6),this);
										}
									}
								
							}
				
					}else {
						for(int j=0;j<game.getPlayerList().get(i).getNumOfCards();j++) {
							g.drawImage(cardBackImage,framex/10+j*framex/50,framey/50+i*(framey/6),this);
						}
					if(i!=0) {
						g.drawLine(0,framey/50+i*(framey/6)-framey/20, framex, framey/50+i*(framey/6)-framey/20);
					}
					}
			}
			g.drawLine(0,framey/50+4*(framey/6)-framey/20, framex, framey/50+4*(framey/6)-framey/20);
			if(game.getHandsOnTable().size()!= 0) {
				if(game.getHandsOnTable().size()>0) {
				for(int i=0;i<game.getHandsOnTable().get((game.getHandsOnTable().size())-1).size();i++) {
					cardsOnTable.add(game.getHandsOnTable().get((game.getHandsOnTable().size())-1).getCard(i));
				}
				
				for(int i=0;i<cardsOnTable.size();i++) {
					String newName=getImageName(cardsOnTable.get(i));
					Image handCard=new ImageIcon(newName).getImage();
					g.drawImage(handCard,framex/50+i*framex/50,framey/20+4*(framey/6),this);
					}
				cardsOnTable.clear();
				}
			}
	
			}
		}
		
		
		/* (non-Javadoc)
		 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
		 */
		public void mouseClicked(MouseEvent e) {
			int framex=frame.getWidth();
			int framey=frame.getHeight();
			int xm = e.getX();
			int ym = e.getY();
			int number=game.getCurrentIdx();
				int height=cardImages[number][0].getHeight(null);
				int width=cardImages[number][0].getWidth(null); 
				for(int j=game.getPlayerList().get(number).getNumOfCards()-1;j>-1;j--) {
					int y0		=framey/50+number*(framey/6);
					int x0      =framex/10+j*framex/50;
					int x1_width=x0+ width;
					int y1		=y0+height;
					if(selected[j]) {
						if(xm>x0&& (xm<x1_width )&& ym>y0-framey/50 && ym<y1-framey/50) {
								
								selected[j]=false;
								frame.repaint();
								return;
							}
					}else { 
						if(xm>x0&& (xm<x1_width )&& ym>y0 && ym<y1) {
							selected[j]=true;
							frame.repaint();
							return;
						}
					}
				}
			frame.repaint();}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			
		}
		@Override
		public void mouseExited(MouseEvent arg0) {
		}
		@Override
		public void mousePressed(MouseEvent arg0) {
		}
		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
		}
	
	/**an inner class that implements the ActionListener
	 * interface. Implements the actionPerformed() method from the ActionListener interface
	 * to handle button-click events for the “Play” button. When the “Play” button is clicked,
	 * you should call the makeMove() method of your CardGame object to make a move.
	 * @author Jerry
	 *
	 */
	public class PlayButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int[] cardIdx=getSelected();
			game.makeMove(activePlayer, cardIdx);
		}
	}
	
	
	/**
	 * an inner class that implements the ActionListener
	 * interface. Implements the actionPerformed() method from the ActionListener interface
	 * to handle button-click events for the “Pass” button. When the “Pass” button is clicked,
	 * you should call the makeMove() method of your CardGame object to make a move
	 * @author Jerry
	 *
	 */
	public class PassButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int[] cardIdx = getSelected();
			game.makeMove(activePlayer, cardIdx);        
		}}
	
	
	/**an inner class that implements the ActionListener
	 * interface. Implements the actionPerformed() method from the ActionListener interface
	 * to handle menu-item-click events for the “Quit” menu item. When the “Quit” menu
	 * item is selected, you should terminate your application.
	 * @author Jerry
	 *
	 */
	public class QuitMenuItemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.exit(0);
		}
		
	}
	
	/**an inner class that implements the ActionListener
	 * interface. Implements the actionPerformed() method from the ActionListener interface
	 * to handle menu-item-click events for the “connect” menu item. When the “connect” menu
	 * item is selected, it will connect with server again.
	 * @author Jerry
	 *
	 */
	public class ConnectMenuItemListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			game.makeConnection();
		}
		
	}
	
	
	/**an inner class that implements the ActionListener
	 * interface. Implements the actionPerformed() method from the ActionListener interface
	 * to let client send message in the outcoming text filed to communicate with each other
	 * @author Jerry
	 *
	 */
	public class FieldActionListenser implements ActionListener {
		public void actionPerformed(ActionEvent event) {
				
			try {
				game.getObjectOutputStream().writeObject(new CardGameMessage(CardGameMessage.MSG,-1, outgoing.getText()));
				game.getObjectOutputStream().flush();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			// resets the text field
			outgoing.setText("");
			outgoing.requestFocus();
		}
	} // SendButtonListener
	
	
	
}
	
	
	