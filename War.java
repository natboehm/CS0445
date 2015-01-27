/*
 * Natalie Boehm, CS0445, Assignment 1: card game of War
 */

public class War {
	static MultiDS<Card> cardDeck 	= new MultiDS<Card>(52);
	static MultiDS<Card> p1Cards  	= new MultiDS<Card>(52); 
	static MultiDS<Card> p2Cards 	= new MultiDS<Card>(52);
	static MultiDS<Card> p1Discard 	= new MultiDS<Card>(52);
	static MultiDS<Card> p2Discard 	= new MultiDS<Card>(52);
	
	
	public static void main(String [] args) { 
		
		greeting();
		fillDeck();
		dealCards();
		displayContents(); 
		
		System.out.println("Starting War");
		
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
	}
	
	public static void displayContents() {
		System.out.println("Player 1's Hand:");
		System.out.println(p1Cards.toString() + "\n");
		System.out.println("Player 2's Hand: ");
		System.out.println(p2Cards.toString() + "\n");
	}
	
	public Boolean p1CardsLeft() {
		boolean p1CardsLeft = false;
		Card p1CardToCompare;
		
		if (! p1Cards.empty()) {
			p1CardToCompare = getP1Cards(); 
			p1CardsLeft = true; 
		} else if (p1Cards.empty() && !p1Discard.empty()) {
			System.out.println("Getting and shuffling the pile for player 1");
			for (int i = 0; i < p1Discard.size(); i++) {
				Card removeCard = p1Discard.removeItem();
				p1Cards.addItem(removeCard); 
			}
			p1Cards.shuffle(); 
			getP1Cards();
		} else {
			// Player 1 loses 
			System.out.println("Player 1 is out of cards");
			p1CardsLeft = false; 
			endGame(); 
		}
		
		return p1CardsLeft; 
	}
	
	public Boolean p2CardsLeft() {
		boolean p2CardsLeft = false; 
		Card p2CardToCompare;
		
		if (! p2Cards.empty()) {
			p2CardToCompare = getP2Cards();
			p2CardsLeft = true; 
		} else if (p2Cards.empty() && !p2Discard.empty()) {
			System.out.println("Getting and shuffling cards for player 2");
			for (int i = 0; i < p2Discard.size(); i++) {
				Card removeCard = p1Discard.removeItem();
				p2Cards.addItem(removeCard);
			}
			p2Cards.shuffle();
			getP2Cards(); 
		} else { 
			// Player 2 loses
			System.out.println("Player 2 is out of Cards.");
			p2CardsLeft = false;
			endGame(); 
			
		}
		 
		return p2CardsLeft; 
	}
	
	// TODO separate into 2 different methods, one boolean for telling
	//		if cards left, the other card type for returning card 
	public static Card getP1Cards() {
		Card p1Card;
		p1Card = p1Cards.removeItem(); 
		return p1Card;
	}
	
	public static Card getP2Cards() {
		Card p2Card; 
		p2Card = p2Cards.removeItem();
		return p2Card; 
	}
	
	public static void compareCards(Card p1CardToCompare, Card p2CardToCompare) {
		
		if (p1CardToCompare.equals(p2CardToCompare)) {
			System.out.println("WAR: " + p1CardToCompare + " ties " + p2CardToCompare);
			cardWar();
			
		} else {
			// cards not equal, need to compare
			int result = p1CardToCompare.compareTo(p2CardToCompare);
		
			if (result > 0) {
				// x beats y
				// add cards to x's discard pile
				System.out.println("Player 1 Wins: " + p1CardToCompare + " beats " + p2CardToCompare);
				p1Discard.addItem(p1CardToCompare);
				p1Discard.addItem(p2CardToCompare);
			} else if (result < 0) {
				// x loses to y
				// add cards to y's discard pile
				System.out.println("Player 2 Wins: " + p1CardToCompare + "loses to " + p2CardToCompare);
				p2Discard.addItem(p1CardToCompare);
				p2Discard.addItem(p2CardToCompare); 
			} 
		}
	}
	
	public static void cardWar() {
		// each player plays card, doesn't compare
		// each player plays another card, compares
		// if tie again repeat process
		// winner takes all 6+ cards
		Card uncomparedP1;
		Card uncomparedP2;
		Card comparedP1;
		Card comparedP2; 
		
		boolean war = true; 
		
		while (war) {
			uncomparedP1 = p1Cards.removeItem();
			uncomparedP2 = p2Cards.removeItem();
			
			comparedP1 = p1Cards.removeItem();
			comparedP2 = p2Cards.removeItem();
			
			if (comparedP1.equals(comparedP2)) {
				war = true; 
				cardWar();
			} else {
				int result = comparedP1.compareTo(comparedP2);
				
				if (result > 0) {
					System.out.println("Player 1 wins: " + comparedP1 + " beats " + comparedP2); 
					p1Discard.addItem(comparedP1);
					p1Discard.addItem(comparedP2);
					p1Discard.addItem(uncomparedP1);
					p1Discard.addItem(uncomparedP2);
					// TODO: add p1 and p2CardToCompare, pass as parameter? 
				} else if (result < 0) {
					System.out.println("Player 2 wins: " + comparedP1 + " loses to " + comparedP2);
					p2Discard.addItem(comparedP1);
					p2Discard.addItem(comparedP2);
					p2Discard.addItem(uncomparedP1);
					p2Discard.addItem(uncomparedP2); 
					// TODO: add p1 and p2CardToCompare
				}
			}
		}
	}
	
	public static void endGame() {
		int p1NumCards = p1Cards.size() + p2Discard.size();
		int p2NumCards = p2Cards.size() + p2Discard.size();
		
		if (p1NumCards > p2NumCards) {
			System.out.println("Player 1 ")
		}
		
	}
	
	public void displayResults() {
		
	}

}
