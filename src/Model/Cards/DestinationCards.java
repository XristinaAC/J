package Model.Cards;
import java.util.ArrayList;

/**
 * This class represents the cards of the destination cards(tickets)
 * and is a subclass of the PointCards class since the destination
 * cards are ways of the player to get points.
 */

public class DestinationCards extends PointCards{
    private String ArrivalCity;
    private String DepartureCity;
    private ArrayList<String> Colors;
    private String id;
    private Boolean drawFlag;//An 8a krathsei thn karta h oxi
    
    /**
    * Constructor.
    * <b>Postcondition</b>Creates a new destination card that it's not
    * purchased yet and neither is taken by a player
    */
    public DestinationCards(){
        super();
        this.drawFlag = false;
    }
    
    /**
    * <b>Transformer:</b> sets the city of arrival of the card
    * <b>Postcondition:</b> the arrival city has been set
    * @param to:represents the arrival city
    */
    public void setArrivalCity(String to){
        this.ArrivalCity = to;
    }
    
    /**
    * <b>Transformer:</b> sets the city of departure of the card
    * <b>Postcondition:</b> the departure city has been set
    * @param from:represents the departure city
    */
    public void setDepartureCity(String from){
        this.DepartureCity = from;
    }
    
    /**
    * <b>Transformer:</b> sets the colors of the card
    * <b>Postcondition:</b> the colors has been set
    * @param colors:represents the colors of every card
    */
    public void setColors(ArrayList colors){
        this.Colors = colors;
    }
    
    /**
    * <b>Transformer:</b> sets the id of the card
    * <b>Postcondition:</b> the id has been set
    * @param id:represents the id of the city
    */
    public void setId(String id){
        this.id = id;
    }
    
    /**
    * <b>Transformer:</b> sets whether or not a card has been chosen from a player
    * from the deck
    * <b>Postcondition:</b> the attribute drawFlag has taken a value
    * @param choice: represents the choice of the player to take a ticket or not from the deck
    */
    public void setDrawFlag(Boolean choice){
        this.drawFlag = choice;
    }
    
    /**
    * <b>Accessor</b>:Returns the city of arrival of a card <br />
    * <p><b>Postcondition:</b> the city of arrival has been returned</p>
    * @return the city of arrival
    */
    public String getArrivalCity(){
        return this.ArrivalCity;
    }
    
    /**
    * <b>Accessor</b>:Returns the departure city of a card <br />
    * <p><b>Postcondition:</b> The departure city has been returned </p>
    * @return the departure city
    */
    public String getDepartureCity(){
        return this.DepartureCity;
    }
    
    /**
    * <b>Accessor</b>:Returns the colors of a card <br />
    * <p><b>Postcondition:</b> the color list has been returned</p>
    * @return the colors of a card
    */
    public ArrayList getColors(){
        return this.Colors;
    }
    
    /**
    * <b>Accessor</b>:Returns the id of a card <br />
    * <p><b>Postcondition:</b> the id of card has been returned</p>
    * @return the card id 
    */
    public String getId(){
        return this.id;
    }
    
    /**
    * <b>Accessor</b>:Returns the flag that represents whether or not a destination card 
    * has been chosen by the player<br />
    * <p><b>Postcondition:</b> the flag drawFlag has been returned</p>
    * @return the flag drawFlag
    */
    public Boolean getDrawFlag(){
        return this.drawFlag;
    }
    
}
