package Model.PPanels;
import Model.Cards.*;
import java.util.Stack;
import Model.Player.Player;
import java.util.ArrayList;

/**
 * This class represents the deck of the board that all the cards,
 * such as destination cards,train cards e.t.c, the player
 * can take and continue with the game.
 */

public class Deck {
    private Stack<TrainCards> tCardsStack;//8a xreiastw me8odous gia na pairnw kai ta stack kai ta positiosn gia update tou deck
    private Stack<DestinationCards> ticketsStack;
    ArrayList<TrainCards> tPositions;
    ArrayList<BigCitiesCards> bonusPositions;
    
    /**
    * Constructor.
    * <b>Postcondition</b>Creates a new deck
    * @param typr
    */
    public Deck(){
        tPositions = new ArrayList<>();
        bonusPositions = new ArrayList<>();
    }
    
    /**
    * <b>Transformer:</b> fills the train card stack
    * <b>Postcondition:</b> the stack card is filled
    */
    public void initStacks(Stack<TrainCards> stackT,Stack<DestinationCards> stackD){
        this.tCardsStack = stackT;
        this.ticketsStack = stackD;
    }
    
    //Otan o paixths pathsei to new game koubi
    public void initTPositions(Stack<TrainCards> stack){
        for(int i = 0; i < 5 ; ++i){
            tPositions.add(stack.pop());
            tPositions.get(i).setPos(i);
        }
    }
    
    public void initBPositions(ArrayList<BigCitiesCards> bC){
        for(int i = 0;i<6;++i){
            bonusPositions.add(bC.get(i));
        }
    }
   
    /**
    * <b>Transformer:</b> the player takes the card on the position pos
    * <b>Precondition:</b> the number of visits from the player must be more than 3
    * <b>Postcondition:</b> a card is given to the player
    * @param pos:it represents the position of the card
    * @param p:represents the player that wants to take the card
    */
    public void takeBonusCard(int pos,Player p){
        if(bonusPositions.get(pos) != null){
            p.takeBonusCard(bonusPositions.get(pos));
            bonusPositions.remove(pos);
        }
    }
   
    /**
    * <b>Transformer:</b> the player takes the card on the position pos and then 
    * the position of this card is filled again from the train card's stack
    * <b>Precondition:</b> the position must not be empty and also the stack
    * <b>Postcondition:</b> a card is given to the player
    * @param pos:it represents the position of the card
    * @param p:represents the player that wants to take the card
    * @param stack
    */
    public void TakeAndUpdatetTrainCPosition(Stack<TrainCards> stack,Player p,int pos){
       p.takeTrainCards(tPositions.get(pos));
       if(stack.isEmpty() == false){
           tPositions.add(pos, stack.pop());
       }
   }
   
   /**
   * <b>Transformer:</b> the player takes tickets from the destination card stack
   * <b>Precondition:</b> The stack must not be empty
   * <b>Postcondition:</b> a card is given to the player
   * @param p:represents the player that wants to take the card
   */
   public void TakeTickets(Player p){
       if( ticketsStack.isEmpty() == false){
           DestinationCards toBeTakenCard = ticketsStack.pop() ;
           p.updateTickets(toBeTakenCard);
       }
   }
   
   /**
   * <b>Transformer:</b> the player takes a train card from the train card stack
   * <b>Precondition:</b> The stack must not be empty
   * <b>Postcondition:</b> a card is given to the player
   * @param p:represents the player that wants to take the card
   */
   public void takeFromTrainSCard(Player p){
       if( tCardsStack.isEmpty() == false){
           TrainCards toBeTakenCard = tCardsStack.pop() ;
           p.takeTrainCards(toBeTakenCard);
       }
   }
   
   public Stack<TrainCards> getTrainCardsStack(){
       return this.tCardsStack;
   }
   
   public Stack<DestinationCards> getDestinationCardStack(){
       return this.ticketsStack;
   }
   
   public ArrayList<TrainCards> getTrainCardsPos(){
       return this.tPositions;
   }
   
   public ArrayList<BigCitiesCards> getBCCPositions(){
       return this.bonusPositions;
   }
}
