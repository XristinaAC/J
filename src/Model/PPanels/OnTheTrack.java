package Model.PPanels;
import java.util.ArrayList;
import java.util.List;
import Model.Cards.*;
import java.util.Stack;

/**
 * This class represents the On_the_track area of a player
 */

public class OnTheTrack {
    private List<OnTheTrackPos> positions;
    
    /**
    * Constructor.
    * <b>Postcondition</b>Creates a new on the track area with 9 train card positions
    */
    public OnTheTrack(){
        positions = new ArrayList<>();
        int p = 0;
        
        for (TrainCards.colors c : TrainCards.colors.values()) {
            OnTheTrackPos pos = new OnTheTrackPos("TrainCard",c,p);
            positions.add(pos);
            p++;
        }
        
        OnTheTrackPos pos = new OnTheTrackPos("Balader",null,p);
        positions.add(pos);
    }
    
    /**
   * <b>Transformer:</b> the counter of every train card according to their color is 
   * incremented after the cards from the railyard come on the track
   * <b>Postcondition:</b> the number of some cards has been incremented
   * @param cards:it represents the set of cards coming from the railyard
   */
    public void updateAvailableCards(ArrayList<TrainCards> cards){
        for(int i = 0; i < cards.size();++i){
            positions.get(cards.get(i).getIdIndex()).addCardOnTheTrack();
        }
    }
    
 
    /*public Boolean checkForDCPurchase(DestinationCards card){
        Boolean purchase;
        int cardCounter = 0;
        int[] colorPos = new int[card.getColors().size()];
        for(int i = 0 ; i < card.getColors().size(); ++i){
            for(int j = 0; j < 8;++j){
                if(positions.get(j).getPosColor().toString() == card.getColors().get(i)){
                    if(positions.get(j).getAvailableCards() > 0){
                        colorPos[i] = j;
                        ++cardCounter;
                        break;
                    }
                }
            }
        }
        
        if(cardCounter == card.getColors().size()){
            for(int i = 0; i < card.getColors().size();++i){
                positions.get(colorPos[i]).useACardFromOnTheTrack();
            }
            purchase = true;
        }else{
            purchase = false;
        }
        
        return purchase;
    }*/
    
    public List<OnTheTrackPos> getPositions(){
        return this.positions;
    }
}

