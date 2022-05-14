package Model.Cards;

/**
 * This class represents the cards with points
 */

public class PointCards implements Card{
    private int points;
    private String Image;
    
    /**
    * Constructor.
    * <b>Postcondition</b>Creates a new point card
    */
    public PointCards(){}
    
    
    /**
    * <b>Transformer:</b> sets the city of arrival of the card
    * <b>Postcondition:</b> the arrival city has been set
    * @param to:represents the arrival city
    */
    public void setPoints(int points){
        this.points = points;
    }
    
    public void setImage(String image){
        this.Image = image;
    }
   
    /**
    * <b>Accessor</b>:Returns the points of a card <br />
    * <p><b>Postcondition:</b> the points of a card has been returned</p>
    * @return the card points 
    */
    public int getPoints(){
        return points;
    }
    
    public String getImage(){
        return Image;
    }
}
