import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Problem54 {

    /**
     * Class variables to denote string values for hands. Useful for avoiding confusion over case-sensitivity of letters
     */
    private static final String HIGH_CARD = "highCard";
    private static final String ONE_PAIR = "onePair";
    private static final String TWO_PAIRS = "twoPairs";
    private static final String THREE_OF_A_KIND = "threeOfAKind";
    private static final String STRAIGHT = "straight";
    private static final String FLUSH = "flush";
    private static final String FULL_HOUSE = "fullHouse";
    private static final String FOUR_OF_A_KIND = "fourOfAKind";
    private static final String STRAIGHT_FLUSH = "straightFlush";
    private static final String ROYAL_FLUSH = "royalFlush";

    /**
     * Class variable to store map of hand vs their score
     */
    private static Map<String, Integer> scoreMap = new HashMap<>();

    static {
        scoreMap.put(HIGH_CARD, 1);
        scoreMap.put(ONE_PAIR, 2);
        scoreMap.put(TWO_PAIRS, 3);
        scoreMap.put(THREE_OF_A_KIND, 4);
        scoreMap.put(STRAIGHT, 5);
        scoreMap.put(FLUSH, 6);
        scoreMap.put(FULL_HOUSE, 7);
        scoreMap.put(FOUR_OF_A_KIND, 8);
        scoreMap.put(STRAIGHT_FLUSH, 9);
        scoreMap.put(ROYAL_FLUSH, 10);
    }

    /**
     * Inner class to denote each Card as an object
     */
    private static class Card implements Comparable {

        /**
         * Variables to store card's value and suit
         */
        private int val;
        private char suit;

        public Card(String card) {
            this.val = assignVal(card.charAt(0));
            this.suit = card.charAt(1);
        }

        /**
         * Method to assign value in integer to the card value given in string
         *
         * @param v
         */
        private int assignVal(char v) {
            switch (v) {
                case 'T':
                    return 10;
                case 'J':
                    return 11;
                case 'Q':
                    return 12;
                case 'K':
                    return 13;
                case 'A':
                    return 14;
                default:
                    return v - '0';
            }
        }

        /**
         * Method to return card's value
         */
        public int getVal() { return this.val; }

        /**
         * Method to return card's suit
         */
        public char getSuit() { return this.suit; }

        /**
         * Override the equals method for specific implementation of Card class
         *
         * @param o
         */
        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Card)) {
                return false;
            }
            if (o == this) {
                return true;
            }
            return ((Card) o).getVal() == this.val && ((Card) o).getSuit() == this.suit;
        }

        /**
         * String representation of Card
         */
        @Override
        public String toString() {
            return "Card : " + this.val + ", " + this.suit;
        }

        /**
         * Method to compare two cards only based on their values
         *
         * @param o
         */
        @Override
        public int compareTo(Object o) {
            if (!(o instanceof Card)) {
                throw new IllegalArgumentException();
            }
            return this.val - ((Card) o).getVal();
        }
    }

    /**
     * Method to determine if a given set of cards represent a ROYAL_FLUSH hand
     *
     * @param cards
     */
    private static boolean isRoyalFlush(List<Card> cards) {
        if (cards.size() != 5) { throw new IllegalArgumentException("Cards size not equal to 5 : " + cards.size()); }
        Set<Integer> values = new HashSet<>();
        Set<Character> suits = new HashSet<>();

        for (Card card : cards) {
            values.add(card.getVal());
            suits.add(card.getSuit());
        }

        if (suits.size() != 1) { return false; }
        return values.contains(10) && values.contains(11) && values.contains(12) && values.contains(13) && values
                .contains(14);
    }

    /**
     * Method to determine if a given set of cards represent a STRAIGHT_FLUSH hand
     *
     * @param cards
     */
    private static boolean isStraightFlush(List<Card> cards) {
        if (cards.size() != 5) { throw new IllegalArgumentException("Cards size not equal to 5 : " + cards.size()); }

        cards.sort(Comparator.comparing(Card::getVal));

        Set<Character> suits = new HashSet<>();
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i + 1).getVal() - cards.get(i).getVal() != 1) { return false; }
            suits.add(cards.get(i + 1).getSuit());
            suits.add(cards.get(i).getSuit());
        }
        return suits.size() != 1;
    }

    /**
     * Method to determine if a given set of cards represent a FOUR_OF_A_KIND hand
     *
     * @param cards
     */
    private static boolean isFourOfAKind(List<Card> cards) {
        if (cards.size() != 5) { throw new IllegalArgumentException("Cards size not equal to 5 : " + cards.size()); }

        Map<Integer, Integer> countedSet = new HashMap<>();
        for (Card card : cards) {
            if (countedSet.containsKey(card.getVal())) {
                countedSet.put(card.getVal(), countedSet.get(card.getVal()) + 1);
            } else {
                countedSet.put(card.getVal(), 1);
            }
        }
        if (countedSet.size() != 2) { return false; }
        for (int val : countedSet.keySet()) {
            if (countedSet.get(val) == 4 || countedSet.get(val) == 1) {
                continue;
            }
            return false;
        }
        return true;
    }

    /**
     * Method to determine if a given set of cards represent a FULL_HOUSE hand
     *
     * @param cards
     */
    private static boolean isFullHouse(List<Card> cards) {
        if (cards.size() != 5) { throw new IllegalArgumentException("Cards size not equal to 5 : " + cards.size()); }

        Map<Integer, Integer> countedSet = new HashMap<>();
        for (Card card : cards) {
            if (countedSet.containsKey(card.getVal())) {
                countedSet.put(card.getVal(), countedSet.get(card.getVal()) + 1);
            } else {
                countedSet.put(card.getVal(), 1);
            }
        }
        if (countedSet.size() != 2) { return false; }
        for (int val : countedSet.keySet()) {
            if (countedSet.get(val) == 3 || countedSet.get(val) == 2) {
                continue;
            }
            return false;
        }
        return true;
    }

    /**
     * Method to determine if a given set of cards represent a FLUSH hand
     *
     * @param cards
     */
    private static boolean isFlush(List<Card> cards) {
        if (cards.size() != 5) { throw new IllegalArgumentException("Cards size not equal to 5 : " + cards.size()); }

        Set<Character> suits = new HashSet<>();
        for (Card card : cards) {
            suits.add(card.getSuit());
        }
        return suits.size() == 1;
    }

    /**
     * Method to determine if a given set of cards represent a STRAIGHT hand
     *
     * @param cards
     */
    private static boolean isStraight(List<Card> cards) {
        if (cards.size() != 5) { throw new IllegalArgumentException("Cards size not equal to 5 : " + cards.size()); }

        cards.sort(Comparator.comparing(Card::getVal));

        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i + 1).getVal() - cards.get(i).getVal() != 1) { return false; }
        }
        return true;
    }

    /**
     * Method to determine if a given set of cards represent a THREE_OF_A_KIND hand
     *
     * @param cards
     */
    private static boolean isThreeOfAKind(List<Card> cards) {
        if (cards.size() != 5) { throw new IllegalArgumentException("Cards size not equal to 5 : " + cards.size()); }

        Map<Integer, Integer> countedSet = new HashMap<>();
        for (Card card : cards) {
            if (countedSet.containsKey(card.getVal())) {
                countedSet.put(card.getVal(), countedSet.get(card.getVal()) + 1);
            } else {
                countedSet.put(card.getVal(), 1);
            }
        }
        for (int val : countedSet.keySet()) {
            if (countedSet.get(val) == 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method to determine if a given set of cards represent a TWO_PAIRS hand
     *
     * @param cards
     */
    private static boolean isTwoPairs(List<Card> cards) {
        if (cards.size() != 5) { throw new IllegalArgumentException("Cards size not equal to 5 : " + cards.size()); }

        Map<Integer, Integer> countedSet = new HashMap<>();
        for (Card card : cards) {
            if (countedSet.containsKey(card.getVal())) {
                countedSet.put(card.getVal(), countedSet.get(card.getVal()) + 1);
            } else {
                countedSet.put(card.getVal(), 1);
            }
        }
        int countOfPairs = 0;
        for (int val : countedSet.keySet()) {
            if (countedSet.get(val) == 2) {
                countOfPairs++;
            }
        }
        return countOfPairs == 2;
    }

    /**
     * Method to determine if a given set of cards represent a ONE_PAIR hand
     *
     * @param cards
     */
    private static boolean isOnePair(List<Card> cards) {
        if (cards.size() != 5) { throw new IllegalArgumentException("Cards size not equal to 5 : " + cards.size()); }

        Map<Integer, Integer> countedSet = new HashMap<>();
        for (Card card : cards) {
            if (countedSet.containsKey(card.getVal())) {
                countedSet.put(card.getVal(), countedSet.get(card.getVal()) + 1);
            } else {
                countedSet.put(card.getVal(), 1);
            }
        }
        int countOfPairs = 0;
        for (int val : countedSet.keySet()) {
            if (countedSet.get(val) == 2) {
                countOfPairs++;
            }
        }
        return countOfPairs == 1;
    }

    /**
     * Method to return a rank for a given set of cards
     *
     * @param cards
     */
    private static String getRank(List<Card> cards) {
        if (isRoyalFlush(cards)) { return ROYAL_FLUSH; }
        if (isStraightFlush(cards)) { return STRAIGHT_FLUSH; }
        if (isFourOfAKind(cards)) { return FOUR_OF_A_KIND; }
        if (isFullHouse(cards)) { return FULL_HOUSE; }
        if (isFlush(cards)) { return FLUSH; }
        if (isStraight(cards)) { return STRAIGHT; }
        if (isThreeOfAKind(cards)) { return THREE_OF_A_KIND; }
        if (isTwoPairs(cards)) { return TWO_PAIRS; }
        if (isOnePair(cards)) { return ONE_PAIR; }
        return HIGH_CARD;
    }

    /**
     * Method to check if the player 1 wins the given draw of cards vs player two
     *
     * @param playerOne
     * @param playerTwo
     */
    private static boolean playerOneWins(List<Card> playerOne, List<Card> playerTwo) {
        String rankPlayerOne = getRank(playerOne);
        String rankPlayerTwo = getRank(playerTwo);

        if (!rankPlayerOne.equals(rankPlayerTwo)) {
            return scoreMap.get(rankPlayerOne) > scoreMap.get(rankPlayerTwo);
        }
        return sameRankResult(playerOne, playerTwo, rankPlayerOne);
    }

    private static boolean sameRankResult(List<Card> playerOne, List<Card> playerTwo, String rank) {
        switch (rank) {
            case FOUR_OF_A_KIND:
                int[] playerOneVals = getFourOfAKind(playerOne);
                int[] playerTwoVals = getFourOfAKind(playerTwo);
                if (playerOneVals[0] == playerTwoVals[0]) {
                    return playerOneVals[1] > playerTwoVals[1];
                }
                return playerOneVals[0] > playerTwoVals[1];
            case FULL_HOUSE:
                int[] player1Vals = getFullHouse(playerOne);
                int[] player2Vals = getFullHouse(playerTwo);
                if (player1Vals[0] == player2Vals[0]) {
                    return player1Vals[1] > player2Vals[1];
                }
                return player1Vals[0] > player2Vals[1];
            case THREE_OF_A_KIND:
                int[] vals1 = getThreeOfAKind(playerOne);
                int[] vals2 = getThreeOfAKind(playerTwo);
                if (vals1[0] == vals2[0]) {
                    if (vals1[1] == vals2[1]) {
                        return vals1[2] > vals2[2];
                    }
                    return vals1[1] > vals2[1];
                }
                return vals1[0] > vals2[0];
            case TWO_PAIRS:
                int[] player1 = getTwoPairs(playerOne);
                int[] player2 = getTwoPairs(playerTwo);
                if (player1[0] == player2[0]) {
                    if (player1[1] == player2[1]) {
                        return player1[2] > player2[2];
                    }
                    return player1[1] > player2[1];
                }
                return player1[0] > player2[0];
            case ONE_PAIR:
                int card1 = getOnePair(playerOne);
                int card2 = getOnePair(playerTwo);
                if (card1 != card2) {
                    return card1 > card2;
                }
                return playerOneHasHigherSequence(playerOne, playerTwo);
            case FLUSH:
            case STRAIGHT_FLUSH:
            case STRAIGHT:
            default:
                return playerOneHasHigherSequence(playerOne, playerTwo);
        }
    }

    private static boolean playerOneHasHigherSequence(List<Card> playerOne, List<Card> playerTwo) {
        playerOne.sort(Comparator.comparing(Card::getVal));
        playerTwo.sort(Comparator.comparing(Card::getVal));
        for (int i = 4; i >= 0; i--) {
            if (playerOne.get(i).getVal() != playerTwo.get(i).getVal()) {
                return playerOne.get(i).getVal() > playerTwo.get(i).getVal();
            }
        }
        return false;
    }

    private static int[] getFourOfAKind(List<Card> cards) {
        int[] vals = new int[2];

        Map<Integer, Integer> countedSet = new HashMap<>();
        for (Card card : cards) {
            if (countedSet.containsKey(card.getVal())) {
                countedSet.put(card.getVal(), countedSet.get(card.getVal()) + 1);
            } else {
                countedSet.put(card.getVal(), 1);
            }
        }

        for (int cardVal : countedSet.keySet()) {
            if (countedSet.get(cardVal) == 4) {
                vals[0] = cardVal;
            }
            if (countedSet.get(cardVal) == 1) {
                vals[1] = cardVal;
            }
        }
        return vals;
    }

    private static int[] getFullHouse(List<Card> cards) {
        int[] vals = new int[2];

        Map<Integer, Integer> countedSet = new HashMap<>();
        for (Card card : cards) {
            if (countedSet.containsKey(card.getVal())) {
                countedSet.put(card.getVal(), countedSet.get(card.getVal()) + 1);
            } else {
                countedSet.put(card.getVal(), 1);
            }
        }

        for (int cardVal : countedSet.keySet()) {
            if (countedSet.get(cardVal) == 3) {
                vals[0] = cardVal;
            }
            if (countedSet.get(cardVal) == 2) {
                vals[1] = cardVal;
            }
        }
        return vals;
    }

    private static int[] getThreeOfAKind(List<Card> cards) {
        int[] vals = new int[3];

        Map<Integer, Integer> countedSet = new HashMap<>();
        for (Card card : cards) {
            if (countedSet.containsKey(card.getVal())) {
                countedSet.put(card.getVal(), countedSet.get(card.getVal()) + 1);
            } else {
                countedSet.put(card.getVal(), 1);
            }
        }

        for (int cardVal : countedSet.keySet()) {
            if (countedSet.get(cardVal) == 3) {
                vals[0] = cardVal;
                continue;
            }
            if (vals[1] == 0) {
                vals[1] = cardVal;
            } else {
                vals[2] = cardVal;
            }
        }
        if (vals[1] < vals[2]) {
            int temp = vals[1];
            vals[1] = vals[2];
            vals[2] = temp;
        }
        return vals;
    }

    private static int[] getTwoPairs(List<Card> cards) {
        int[] vals = new int[3];

        Map<Integer, Integer> countedSet = new HashMap<>();
        for (Card card : cards) {
            if (countedSet.containsKey(card.getVal())) {
                countedSet.put(card.getVal(), countedSet.get(card.getVal()) + 1);
            } else {
                countedSet.put(card.getVal(), 1);
            }
        }

        for (int cardVal : countedSet.keySet()) {
            if (countedSet.get(cardVal) == 2) {
                if (vals[0] == 0) {
                    vals[0] = cardVal;
                } else {
                    vals[1] = cardVal;
                }
                continue;
            }
            vals[2] = cardVal;
        }
        if (vals[0] < vals[1]) {
            int temp = vals[0];
            vals[0] = vals[1];
            vals[1] = temp;
        }
        return vals;
    }

    private static int getOnePair(List<Card> cards) {
        Map<Integer, Integer> countedSet = new HashMap<>();
        for (Card card : cards) {
            if (countedSet.containsKey(card.getVal())) {
                countedSet.put(card.getVal(), countedSet.get(card.getVal()) + 1);
            } else {
                countedSet.put(card.getVal(), 1);
            }
        }
        for (int cardVal : countedSet.keySet()) {
            if (countedSet.get(cardVal) == 2) {
                return cardVal;
            }
        }
        return -1;
    }

    /**
     * It has got to be the stupidiest code i've written to solve this problem
     * TODO: Optimize the code to write fewer lines
     *
     * @param args
     *
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        long startTime = System.currentTimeMillis();
        String path = "Problem 54/poker.txt";
        Scanner sc = new Scanner(new File(path));

        int count = 0;
        while (sc.hasNextLine()) {
            String[] cards = sc.nextLine().split("\\s");
            if (cards.length != 10) { throw new IllegalArgumentException("Cards should be 10!"); }
            List<Card> playerOne = new ArrayList<>();
            List<Card> playerTwo = new ArrayList<>();

            for (int i = 0; i < 5; i++) { playerOne.add(new Card(cards[i])); }
            for (int i = 5; i < 10; i++) { playerTwo.add(new Card(cards[i])); }

            if (playerOneWins(playerOne, playerTwo)) {
                count++;
            }
        }
        System.out.println(count);
        long endTime = System.currentTimeMillis();
        System.out.println("Time taken : " + (endTime - startTime));
    }
}