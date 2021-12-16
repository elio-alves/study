/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elio.alves;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 *
 * @author elio_alves
 */
public class PokerHand {
    private List<Card> listHand = new LinkedList<>();
    private Integer handValue = null;
    private Integer straightFlushValue = null;
    private Integer fourEOfKind = null;
    private Integer fullHouse3 = null;
    private Integer flushValue = null;
    private Integer straightValue = null;
    private Integer threeOfKindValue = null;
    private Integer twoPairValue1 = null;
    private Integer twoPairValue2 = null;
    private Integer onePairValue = null;
    
    public enum Result {
        TIE, WIN, LOSS
    }
    
    class Card {
        public Integer value;
        public String suit;

        public Card(Integer value, String suit) {
            this.value = value;
            this.suit = suit;
        }
    }

    /**
     * Compare the major card card by card
     * 
     * @param me
     * @param other
     * @return 
     */
    public Result compareCardByCard(PokerHand me, PokerHand other) {
        List<Integer> listMe = new LinkedList<>();
        List<Integer> listOther = new LinkedList<>();
        
        for(Card c: me.listHand){
            listMe.add(c.value);
        }
        for(Card c: other.listHand){
            listOther.add(c.value);
        }
        
        // order and invert
        Collections.sort(listMe);
        Collections.sort(listOther);
        Collections.reverse(listMe);
        Collections.reverse(listOther);
        
        for(int i=0;i<5;i++){
            if(listMe.get(i)>listOther.get(i)){
                return Result.WIN;
            }else if(listMe.get(i)<listOther.get(i)){
                return Result.LOSS;
            }
        }
        
        return Result.TIE;
    }
    
    /**
     * The the hand is Tree Of kind, check who have the major value
     * 
     * @param aThis
     * @param threeOfKindValue
     * @return 
     */
    public int getMaxOtherValue(PokerHand aThis, Integer threeOfKindValue) {
        int max =0;
        
        for(Card c: aThis.listHand){
            if(c.value!=threeOfKindValue){
                if(c.value>max){
                    max = c.value;
                }
            }
        }
        
        return max;
    }

    public Integer getHandValue() {
        return handValue;
    }

    public Integer getStraightFlushValue() {
        return straightFlushValue;
    }

    public Integer getFourEOfKind() {
        return fourEOfKind;
    }

    public Integer getFullHouse3() {
        return fullHouse3;
    }
    
    public Integer getFullHouse2() {
        return fullHouse3;
    }

    public Integer getFlushValue() {
        return flushValue;
    }

    public Integer getStraightValue() {
        return straightValue;
    }

    public Integer getThreeOfKindValue() {
        return threeOfKindValue;
    }

    public Integer getTwoPairValue1() {
        return twoPairValue1;
    }

    public Integer getTwoPairValue2() {
        return twoPairValue2;
    }

    public Integer getOnePairValue() {
        return onePairValue;
    }

    public void isStraightFlush() {
        // dont need check again
        if (handValue != null) {
            return;
        }

        // check if is "Straight Flush"
        String suit = null;
        // suit equals
        boolean suitDifferents = false;
        List<Integer> listTmp = new LinkedList<>();
        for (Card c : listHand) {
            listTmp.add(c.value);
            if (suit == null) {
                suit = c.suit;
            } else {
                if (!Objects.equals(c.suit, suit)) {
                    suitDifferents = true;
                }
            }
        }
        if (suitDifferents == false) {
            Collections.sort(listTmp);

            // check if in sequencial order
            int maxTemp = 0;
            int sequencialNumber = 1;
            for (int i = 0; i < listTmp.size(); i++) {
                if (i > 0) {
                    if (Objects.equals(listTmp.get(i), listTmp.get(i - 1) + 1)) {
                        sequencialNumber++;
                        maxTemp = listTmp.get(i);
                    }
                }
            }

            //if five cards are sequencial
            if (sequencialNumber == 5) {
                handValue = 1;
                straightFlushValue = maxTemp;
            }
        }
    }

    public void isFourOfKind() {
        if (handValue != null) {
            return;
        }

        Map<Integer, Integer> cardCount = new HashMap<>();

        for (Card c : listHand) {
            Integer count = cardCount.get(c.value);
            if (count == null) {
                count = 0;
            }
            count++;
            cardCount.put(c.value, count);
            if (count == 4) {
                fourEOfKind = c.value;
                handValue = 2;
                break;
            }
        }

    }

    public void isFullHouse() {
        if (handValue != null) {
            return;
        }

        Map<Integer, Integer> cardCount = new HashMap<>();

        for (Card c : listHand) {
            Integer count = cardCount.get(c.value);
            if (count == null) {
                count = 0;
            }
            count++;
            cardCount.put(c.value, count);
        }

        Integer math3 = null;
        Integer math2 = null;
        for (Entry<Integer, Integer> entry : cardCount.entrySet()) {
            if (entry.getValue() == 3) {
                math3 = entry.getKey();
            } else if (entry.getValue() == 2) {
                math2 = entry.getKey();

            }
        }

        // full house
        if (math3 != null && math2 != null) {
            handValue = 3;
            
            fullHouse3 = math3;
        }
    }

    public void isFlush() {
        if (handValue != null) {
            return;
        }

        Map<String, Integer> cardCount = new HashMap<>();

        for (Card c : listHand) {
            Integer count = cardCount.get(c.suit);
            if (count == null) {
                count = 0;
            }
            count++;
            cardCount.put(c.suit, count);
        }

        // check if is a flush
        for (Entry<String, Integer> entry : cardCount.entrySet()) {
            if (entry.getValue() == 5) {
                handValue = 4;

                // greatest value
                for (Card c : listHand) {
                    if (c.suit == null ? entry.getKey() == null : c.suit.equals(entry.getKey())) {
                        if (flushValue == null || c.value > flushValue) {
                            flushValue = c.value;
                        }
                    }
                }
            }
        }

    }

    public void isStraight() {
        // dont need check again
        if (handValue != null) {
            return;
        }

        List<Integer> listTmp = new LinkedList<>();
        for (Card c : listHand) {
            listTmp.add(c.value);
        }
        Collections.sort(listTmp);

        // check if in sequencial order
        int sequencialNumber = 1;
        for (int i = 0; i < listTmp.size(); i++) {
            if (i > 0) {
                if (Objects.equals(listTmp.get(i), listTmp.get(i - 1) + 1)) {
                    sequencialNumber++;
                }
            }
        }

        //if five cards are sequencial
        if (sequencialNumber == 5) {
            handValue = 5;

            // greatest value
            for (Card c : listHand) {
                if (straightValue == null || c.value > straightValue) {
                    this.straightValue = c.value;
                }
            }
        }
    }

    public void isThreeOfKind() {
        if (handValue != null) {
            return;
        }

        Map<Integer, Integer> cardCount = new HashMap<>();

        for (Card c : listHand) {
            Integer count = cardCount.get(c.value);
            if (count == null) {
                count = 0;
            }
            count++;
            cardCount.put(c.value, count);
        }

        Integer math3 = null;
        Integer math2 = null;
        for (Entry<Integer, Integer> entry : cardCount.entrySet()) {
            if (entry.getValue() == 3) {
                math3 = entry.getKey();
            } else if (entry.getValue() == 2) {
                math2 = entry.getKey();

            }
        }

        // full house
        if (math3 != null && math2 == null) {
            handValue = 6;
            threeOfKindValue = math3;
        }
    }

    public void isTwoPair() {
        if (handValue != null) {
            return;
        }

        Map<Integer, Integer> cardCount = new HashMap<>();

        for (Card c : listHand) {
            Integer count = cardCount.get(c.value);
            if (count == null) {
                count = 0;
            }
            count++;
            cardCount.put(c.value, count);
        }

        Integer pair1 = null;
        Integer pair2 = null;
        for (Entry<Integer, Integer> entry : cardCount.entrySet()) {
            if (entry.getValue() == 2 && pair1 == null) {
                pair1 = entry.getKey();
                twoPairValue1 = entry.getKey();
            } else if (entry.getValue() == 2) {
                pair2 = entry.getKey();
                twoPairValue2 = entry.getKey();

            }
        }

        // full house
        if (pair1 != null && pair2 != null) {
            handValue = 7;
        }
    }

    public void isOnePair() {
        if (handValue != null) {
            return;
        }

        Map<Integer, Integer> cardCount = new HashMap<>();

        for (Card c : listHand) {
            Integer count = cardCount.get(c.value);
            if (count == null) {
                count = 0;
            }
            count++;
            cardCount.put(c.value, count);
        }

        Integer pair1 = null;
        Integer pair2 = null;
        for (Entry<Integer, Integer> entry : cardCount.entrySet()) {
            if (entry.getValue() == 2 && pair1 == null) {
                pair1 = entry.getKey();
                onePairValue = entry.getKey();
            }
        }

        // full house
        if (pair1 != null) {
            handValue = 8;
        }
    }

    public void isHighCard() {
        if (handValue != null) {
            return;
        }
        handValue = 9;
    }

    PokerHand(String hand) {
        for (String s : hand.split(" ")) {
            String card = s.substring(0, 1);
            String suit = s.substring(1, 2);

            switch (card) {
                case "2": {
                    listHand.add(new Card(2, suit));
                    break;
                }
                case "3": {
                    listHand.add(new Card(3, suit));
                    break;
                }
                case "4": {
                    listHand.add(new Card(4, suit));
                    break;
                }
                case "5": {
                    listHand.add(new Card(5, suit));
                    break;
                }
                case "6": {
                    listHand.add(new Card(6, suit));
                    break;
                }
                case "7": {
                    listHand.add(new Card(7, suit));
                    break;
                }
                case "8": {
                    listHand.add(new Card(8, suit));
                    break;
                }
                case "9": {
                    listHand.add(new Card(9, suit));
                    break;
                }
                case "T": {
                    listHand.add(new Card(10, suit));
                    break;
                }
                case "J": {
                    listHand.add(new Card(11, suit));
                    break;
                }
                case "Q": {
                    listHand.add(new Card(12, suit));
                    break;
                }
                case "K": {
                    listHand.add(new Card(13, suit));
                    break;
                }
                case "A": {
                    listHand.add(new Card(14, suit));
                    break;
                }
            }
        }

        // Check the hands in the specific order
        isStraightFlush();
        isFourOfKind();
        isFullHouse();
        isFlush();
        isStraight();
        isThreeOfKind();
        isTwoPair();
        isOnePair();
        isHighCard();
    }

    public Integer getMax(Integer n1, Integer n2) {
        if (n1 > n2) {
            return n1;
        } else {
            return n2;
        }
    }

    public Integer getMin(Integer n1, Integer n2) {
        if (n1 < n2) {
            return n1;
        } else {
            return n2;
        }
    }

    public Result compareWith(PokerHand hand) {
        if (this.getHandValue() < hand.getHandValue()) {
            return Result.WIN;
        } else if (this.getHandValue() > hand.getHandValue()) {
            return Result.LOSS;
        } else {
            // Straight Flush
            if (this.getHandValue() == 1) {
                if (this.straightFlushValue > hand.getStraightFlushValue()) {
                    return Result.WIN;
                } else if (this.straightFlushValue < hand.getStraightFlushValue()) {
                    return Result.LOSS;
                } else {
                    return Result.TIE;
                }
            }

            // Four-of-a-Kind
            if (this.getHandValue() == 2) {
                if (this.fourEOfKind > hand.getFourEOfKind()) {
                    return Result.WIN;
                } else if (this.fourEOfKind < hand.getFourEOfKind()) {
                    return Result.LOSS;
                } else {
                    return compareCardByCard(this, hand);
                }
            }

            // Full House
            if (this.getHandValue() == 3) {
                if (this.fullHouse3 > hand.getFullHouse3()) {
                    return Result.WIN;
                } else if (this.fullHouse3 < hand.getFullHouse3()) {
                    return Result.LOSS;
                }else{
                    return compareCardByCard(this, hand);
                }
            }

            // Flush
            if (this.getHandValue() == 4) {
                if (this.flushValue > hand.getFlushValue()) {
                    return Result.WIN;
                } else if (this.flushValue < hand.getFlushValue()) {
                    return Result.LOSS;
                } else {
                    return compareCardByCard(this, hand);
                }
            }

            // Straight
            if (this.getHandValue() == 5) {
                if (this.straightValue > hand.getStraightValue()) {
                    return Result.WIN;
                } else if (this.straightValue < hand.getStraightValue()) {
                    return Result.LOSS;
                } else {
                    return Result.TIE;
                }
            }

            // Three-of-a-Kind
            if (this.getHandValue() == 6) {
                if (this.getThreeOfKindValue() > hand.getThreeOfKindValue()) {
                    return Result.WIN;
                } else if (this.getThreeOfKindValue() < hand.getThreeOfKindValue()) {
                    return Result.LOSS;
                } else {
                    int me = getMaxOtherValue(this, this.threeOfKindValue);                        
                    int other = getMaxOtherValue(hand, hand.threeOfKindValue);
                    
                    if(me>other){
                        return Result.WIN;
                    }else if(me<other){
                        return Result.LOSS;
                    }
                    
                    return Result.TIE;
                }
            }

            // Two Pair
            if (this.getHandValue() == 7) {
                if (getMax(this.getTwoPairValue1(), this.getTwoPairValue2()) > getMax(hand.getTwoPairValue1(), hand.getTwoPairValue2())) {
                    return Result.WIN;
                } else if (getMax(this.getTwoPairValue1(), this.getTwoPairValue2()) < getMax(hand.getTwoPairValue1(), hand.getTwoPairValue2())) {
                    return Result.LOSS;
                } else {
                    if (getMin(this.getTwoPairValue1(), this.getTwoPairValue2()) > getMin(hand.getTwoPairValue1(), hand.getTwoPairValue2())) {
                        return Result.WIN;
                    } else if (getMin(this.getTwoPairValue1(), this.getTwoPairValue2()) < getMin(hand.getTwoPairValue1(), hand.getTwoPairValue2())) {
                        return Result.LOSS;
                    } else {
                        return compareCardByCard(this, hand);
                    }
                }
            }

            // One Pair
            if (this.getHandValue() == 8) {
                if (this.getOnePairValue() > hand.getOnePairValue()) {
                    return Result.WIN;
                } else if (this.getOnePairValue() < hand.getOnePairValue()) {
                    return Result.LOSS;
                } else {
                    //High Card
                    List<Integer> listMe = new LinkedList<>();
                    for(Card me: listHand){
                        listMe.add(me.value);
                    }
                    Collections.sort(listMe);
                    List<Integer> listOther = new LinkedList<>();

                    for(Card me: hand.listHand){
                        listOther.add(me.value);
                    }
                    Collections.sort(listOther);

                    // comparte one to one
                    for(int i=0;i<5;i++){
                        Integer m = listMe.get(i);
                        Integer o = listOther.get(i);

                        if(m>o){
                            return Result.WIN;
                        }else if(m<o){
                            return Result.LOSS;
                        }
                    }

                    return Result.TIE;
                }
            }
        }
        
        //High Card
        return compareCardByCard(this, hand);
    }

    /**
     * Full poker game API to check the hands 
     * 
     * Cards: 2,3,4,5,6,7,8,9,T (Ten),J(ack),Q(ueen),K(ing),A(ce)
     * Suits: C(lubs), D(iamonds), H(earts), S(pades)
     * 
     * 
     * @param args 
     */
    public static void main(String args[]) {
        System.out.println(compareHand("KH KC 3S 3H 3D", "KH KC 3S 3H 3D", Result.TIE));
        System.out.println(compareHand("3C KH 5D 5S KH", "5S 5D 2C KH KH", Result.WIN));
        System.out.println(compareHand("2H 2C 3S 3H 3D", "KH KC 3S 3H 3D", Result.LOSS));
        
    }
    
    /**
     * Method to compare hands, convert text card notation to PokerHand and check who wins
     * 
     * @param s1
     * @param s2
     * @param res
     * @return 
     */
    public static boolean compareHand(String s1, String s2, Result res){
        PokerHand h1 = new PokerHand(s1);
        PokerHand h2 = new PokerHand(s2);
        
        return Objects.equals(h1.compareWith(h2),res);
    }
}
