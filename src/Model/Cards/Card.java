package Model.Cards;

/**This source file represents the interface 
 *that contains the signatures of the basic methods needed to create the
 *classes for the train and point cards.
 */

public interface Card {
    
    /**
    * <b>Transformer:</b> sets the card's image
    * <b>Postcondition:</b> card's image has been set
    * @param image:it represents the image of every card
    */
    void setImage(String image);
    
    /**
    * <b>Accessor</b>:Returns the chosen image of a card <br />
    * <p><b>Postcondition:</b> the image of a card has been returned</p>
    * @return the image of a card
    */
    String getImage();
    
}
