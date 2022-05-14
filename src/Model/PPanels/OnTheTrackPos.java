package Model.PPanels;
import Model.Cards.TrainCards;

/**
 * This class represents the On_the_track area positions
 */

public class OnTheTrackPos {
    private TrainCards.colors posColor;
    private int cardCounter;
    private String type;
    private int pos;
    
    /**
    * Constructor.
    * <b>Postcondition</b>Creates a new on the track position
    * @param type
    * @param color
    * @param pos
    */
    public OnTheTrackPos(String Type,TrainCards.colors color,int pos){
        this.cardCounter = 0;
        this.type = Type;
        this.posColor = color;
        this.pos = pos;
    }
    
   /**
   * <b>Transformer:</b> the counter cardCounter is incremented
   * <b>Postcondition:</b> the cardCounter has been incremented
   */
    public void addCardOnTheTrack(){
        ++this.cardCounter;
    }
    
    public void useACardFromOnTheTrack(){
        --this.cardCounter;
    }
    
   /**
   * <b>Accessor:</b> it returns the cardCounter
   * <b>Postcondition:</b> the cardCounter is returned
   * @return the cardCounter
   */
    public int getAvailableCards(){
        return this.cardCounter;
    }
    
   /**
   * <b>Accessor:</b> it returns the position color
   * <b>Postcondition:</b> the position color is returned
   * @return the position color
   */
    public TrainCards.colors getPosColor(){
            return this.posColor;
    }
    
   /**
   * <b>Accessor:</b> it returns the position
   * <b>Postcondition:</b> the position is returned
   * @return the position
   */
    public int getPos(){
        return this.pos;
    }
    
    public String getType(){
        return this.type;
    }
}