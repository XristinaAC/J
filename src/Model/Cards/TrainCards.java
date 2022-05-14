package Model.Cards;

/**
 * This class represents the cards of train cards that 
 * the player uses to buy tickets.
 */

public class TrainCards implements Card {
    private String Type;
    private String Image;
    static public enum colors { red , black , blue , green , purple , white , yellow , orange }
    private colors Color;
    private int idIndex;
    private int cPos;
    
    /**
    * Constructor.
    * <b>Postcondition</b>Creates a new train card with her type being either colored or balander
    * @param typr
    */
    public TrainCards(String type){
        this.Type = type;
    }
    
    public void setImage(String Image){
        this.Image = Image;
    }
    
    /**
    * <b>Transformer:</b> sets the card's color
    * <b>Postcondition:</b> card's color has been set
    * @param color:it represents the color of the card
    */
    public void setColor(colors color){
        this.Color = color;
    }
    
    public void setIdIndex(int id){
        this.idIndex = id;
    }
    
    public void setPos(int pos){
        this.cPos = pos;
    }
    
    /**
    * <b>Accessor</b>:Returns the type of a card <br />
    * <p><b>Postcondition:</b> the point of a card has been returned</p>
    * @return the card type
    */
    public String getType(){
        return this.Type;
    }
    
    public String getImage(){
        return this.Image;
    }
    
    /**
    * <b>Accessor</b>:Returns the color of a card <br />
    * <p><b>Postcondition:</b> the color of a card has been returned</p>
    * @return the card color 
    */
    public colors getColor(){
        return this.Color;
    }
    
    public int getIdIndex(){
        return idIndex;
    }
    
    public int getPos(){
        return this.cPos;
    }
}