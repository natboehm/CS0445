/*
 * Natalie Boehm, CS0445, Assignment 1: card game of War
 */

public class War {
	static MultiDS<Card> cardDeck 	= new MultiDS<Card>(52);
	static MultiDS<Card> p1Cards  	= new MultiDS<Card>(52); 
	static MultiDS<Card> p2Cards 	= new MultiDS<Card>(52);
	static MultiDS<Card> p1Discard 	= new MultiDS<Card>(52);
	static MultiDS<Card> p2Discard 	= new MultiDS<Card>(52);
	
	static int rounds; 
	
	public static void main(String[] args) { 
		
		rounds = Integer.parseInt(args[0]);
		
		greeting();
		fillDeck();
		dealCards();
		displayContents(); 
		
		System.out.println("Starting War");
		
			for (int i = 0; i < rounds; i++) {
				
				boolean cP1 = p1HasCards();
				boolean cP2 = p2HasCards(); 
				
				if (cP1 && cP2) {
					Card compareCardP1 = getP1Card();
					Card compareCardP2 = getP2Card();
					compareCards(compareCardP1, compareCardP2);
					
				} else if (!cP1 && cP2) {
					endGame();
					
				} else if (cP1 && !cP2) {
					endGame();
					
				} 
			}
			
			endGame();
	}
	
	public static void greeting() {
		System.out.println("Welcome to the game of War!");
		System.out.println("Dealing to Players...\n");
	}
	
	public static void fillDeck() {
		Card card; 
		
		for (Card.Ranks r: Card.Ranks.values()) {
			for (Card.Suits s: Card.Suits.values()) {
				card = new Card(s, r);
				cardDeck.addItem(card);
			}
		}
		cardDeck.shuffle(); 
	}
		
	public static void dealCards() {
		Card removedCard; 
	
		for (int i = 0; i < 26; i++) {
			removedCard = cardDeck.removeItem();
			p1Cards.addItem(removedCard);
		} for (int i = 0; i < 26; i++) {
			removedCard = cardDeck.removeItem();
			p2Cards.addItem(removedCard);
		}
		
		p1Cards.shuffle();
		p2Cards.shuffle();
	}
	
	public static void displayContents() {
		System.out.println("Player 1's Hand:");
		System.out.println(p1Cards.toString() + "\n");
		System.out.println("Player 2's Hand: ");
		System.out.println(p2Cards.toString() + "\n");
	}
	
	public static boolean p1HasCards() {
		boolean p1CardsLeft = false;
	
		if (!p1Cards.empty()) {
			p1CardsLeft = true; 
		
		} else if (p1Cards.empty() && !p1Discard.empty()) {
			System.out.println("Getting and shuffling the pile for player 1");
			for (int i = 0; i < p1Discard.size(); i++) {
				Card removeCard = p1Discard.removeItem();
				p1Cards.addItem(removeCard); 
			}
			p1Cards.shuffle(); 
			p1CardsLeft = true;
		} else {
			// Player 1 loses 
			p1CardsLeft = false; 
		}
		
		return p1CardsLeft; 
	}
	
	public static boolean p2HasCards() {
		boolean p2CardsLeft = false; 
		
		if (!p2Cards.empty()) {
			p2CardsLeft = true;
		
		} else if (p2Cards.empty() && !p2Discard.empty()) {
			System.out.println("Getting and shuffling the pile for player 2");
			for (int i = 0; i < p2Discard.size(); i++) {
				Card removeCard = p1Discard.removeItem();
				p2Cards.addItem(removeCard);
			}
			p2Cards.shuffle();
			p2CardsLeft = true; 
			
		} else { 
			// Player 2 loses
			p2CardsLeft = false;
		}
		 
		return p2CardsLeft; 
	}
	
	// TODO separate into 2 different methods, one boolean for telling
	//		if cards left, the other card type for returning card 
	public static Card getP1Card() {
		Card compareCardP1;
		compareCardP1 = p1Cards.removeItem();
		if (compareCardP1 == null) {
			endGame();
		}
		return compareCardP1;
	}
	
	public static Card getP2Card() {
		Card compareCardP2; 
		compareCardP2 = p2Cards.removeItem();
		if (compareCardP2 == null) {
			endGame();
		}
		return compareCardP2; 
	}
	
	public static void compareCards(Card compareCardP1, Card compareCardP2) {
		/*
		if (compareCardP1.equals(compareCardP2)) {
			System.out.println("inside equals if statement");

		} else {*/
			// cards not equal, need to compare
			int result = 0;
			try {
				result = compareCardP1.compareTo(compareCardP2);
			} catch (NullPointerException e) {
				
				System.out.println(compareCardP1);
				System.out.println(compareCardP2);				
			}
			
			
			if (result > 0) {
				// x beats y
				// add cards to x's discard pile
				System.out.println("Player 1 Wins: " + compareCardP1 + " beats " + compareCardP2);
				p1Discard.addItem(compareCardP1);
				p1Discard.addItem(compareCardP2);
			} else if (result < 0) {
				// x loses to y
				// add cards to y's discard pile
				System.out.println("Player 2 Wins: " + compareCardP1 + " loses to " + compareCardP2);
				p2Discard.addItem(compareCardP1);
				p2Discard.addItem(compareCardP2); 
			} else {
				System.out.println("WAR: " + compareCardP1 + " ties " + compareCardP2);
				int cardWinner = cardWar();
				
				if (cardWinner == 1) {
					p1Discard.addItem(compareCardP1);
					p1Discard.addItem(compareCardP2);
				} 
				
				if (cardWinner == 2){
					p2Discard.addItem(compareCardP1);
					p2Discard.addItem(compareCardP2); 
				}
			}
		//}
	}
	
	public static int cardWar() {
		// each player plays card, doesn't compare
		// each player plays another card, compares
		// if tie again repeat process
		// winner takes all 6+ cards
		// System.out.println("reached cardWar method");
		
		Card uncomparedP1 = null;
		Card uncomparedP2 = null;
		Card comparedP1 = null;
		Card comparedP2 = null; 
		
		int cardWinner = 0;
		
		boolean war = true; 
		
		while (war) {
			
			boolean cP1 = p1HasCards();
			boolean cP2 = p2HasCards();
			
			if (cP1 && cP2) {
				uncomparedP1 = p1Cards.removeItem();
				uncomparedP2 = p2Cards.removeItem();
			
				comparedP1 = p1Cards.removeItem();
				comparedP2 = p2Cards.removeItem();
			} else if (!cP1 && cP2) {
				endGame();
			} else if (cP1 && !cP2) {
				endGame();
			} 
			/*
			if (comparedP1.equals(comparedP2)) {
				war = true; 
				
			} else { */
				int result = comparedP1.compareTo(comparedP2);
				
				if (result > 0) {
					System.out.println("Player 1 wins: " + comparedP1 + " beats " + comparedP2); 
					p1Discard.addItem(comparedP1);
					p1Discard.addItem(comparedP2);
					p1Discard.addItem(uncomparedP1);
					p1Discard.addItem(uncomparedP2);
					
					cardWinner = 1; 
					war = false; 
					break;
				} else if (result < 0) {
					System.out.println("Player 2 wins: " + comparedP1 + " loses to " + comparedP2);
					p2Discard.addItem(comparedP1);
					p2Discard.addItem(comparedP2);
					p2Discard.addItem(uncomparedP1);
					p2Discard.addItem(uncomparedP2); 
					
					cardWinner = 2; 
					war = false;
					break;
				} else {
					war = true; 
				}
			//}
		}
		return cardWinner; 
	}
	
	public static void endGame() {
		System.out.println();
		System.out.println("After " + rounds + " rounds:" );
		
		/*
		System.out.println();
		System.out.println(p1Cards);
		System.out.println(p2Cards);
		System.out.println(p1Discard);
		System.out.println(p2Discard);
		System.out.println();
		*/
		int p1NumCards = (p1Cards.size() + p1Discard.size());
		int p2NumCards = (p2Cards.size() + p2Discard.size());
		
		if (p1NumCards != 0 && p1NumCards != 0) {
			System.out.println("Player 1 has " + p1NumCards + " cards");
			System.out.println("Player 2 has " + p2NumCards + " cards");
		} else if (p1NumCards == 0) {
			System.out.println("Player 1 is out of cards");
			System.out.println("Player 2 wins");
		} else if (p2NumCards == 0) {
			System.out.println("Player 2 is out of cards");
			System.out.println("Player 1 wins");
		} else {
			System.out.println("error");
		}
		
		if (p1NumCards > p2NumCards) {
			System.out.println("Player 1 wins");
		} else if (p1NumCards < p2NumCards) {
			System.out.println("Player 2 wins");
		} else {
			System.out.println("It is a stalemate");
		}
		System.exit(0);
	}
}
