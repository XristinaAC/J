package Model.Cards;
import Model.Player.Player;

/**
 * This class represents the cards of the big cities(bonus cards)
 */

public class BigCitiesCards extends PointCards{
    private Cities city;
    private int visitedByP1;
    private int visitedByP2;
 
    /**
    * Constructor.
    * <b>Postcondition</b>Creates a new big city card
    */
    public BigCitiesCards(){
        super();
        visitedByP1 = 0;
        visitedByP2 = 0;
    }
    
    /**
    * <b>Transformer:</b> sets the city that each card belongs to 
    * <b>Postcondition:</b> card's city has been set
    * @param c:it represents the city of the card
    */
    public void setCity(Cities c){
       this.city = c;
    }
    
    /**
    * <b>Transformer:</b> sets the number of visits of a city(card) by a player
    * <b>Precondition:</b> the counter visitedC musth not be more than 3
    * <b>Postcondition:</b> the counter visitedC has been increased
    */
    public void beingVisited(Player p){
        if(p.getID() == 0){
            if(visitedByP1 < 3){
                ++visitedByP1;
            }
        }
        else if(p.getID() != 1){
            if(visitedByP2 < 3){
                ++visitedByP2;
            }
        }
    }
   
    /**
    * <b>Accessor</b>:Returns the city the card belongs to
    * <p><b>Postcondition:</b> the city of a card has been returned</p>
    * @return the city of a card
    */
    public Cities getCity(){
       return this.city;
    }
    
    /**
    * <b>Accessor</b>:Returns the number a city card has been visited
    * <p><b>Postcondition:</b> the counter visitedC has been returned</p>
    * @return the number of visitations of a card
    */
    public int getVisitations(Player p){
        if(p.getID() == 0){
            return visitedByP1;
        }else{
            return visitedByP2;
        }
    }
}