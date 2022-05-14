package Model.PPanels;
import java.util.Stack;
import Model.Cards.*;
import java.util.List;
import java.util.ArrayList;
import Model.Player.Player;

/**
 * This class represents the railyard area of a player
 */

public class Railyard {
   List<RailyardPos> trainCStacks;
    
    /**
    * Constructor.
    * <b>Postcondition</b>Creates a new railyard area with 8 stack positions
    */
    public Railyard(){
        int p = 0;
        trainCStacks = new ArrayList<>();
         for (TrainCards.colors c : TrainCards.colors.values()) {
            RailyardPos pos = new RailyardPos(p,c);
            trainCStacks.add(pos);
            p++;
        }
    }
    
      /**
   * <b>Transformer:</b> the player moves every first card from each color to on the
   * track area.
   * <b>Postcondition:</b> the cards have been moved
   */
    public void MoveCards(OnTheTrack oTt){//Borei na alla3ei se onTheTrack
        ArrayList<TrainCards> cardsToBeMoved = new ArrayList<>();
        
        for(int i =0 ; i < 8;++i){
            if(trainCStacks.get(i).getStack().isEmpty() == false){
                cardsToBeMoved.add(trainCStacks.get(i).getStack().pop());
            }
        }
        
        oTt.updateAvailableCards(cardsToBeMoved);
    }
     
   /**
   * <b>Transformer:</b> the player places new train cards on the railyard area
   * <b>Precondition:<b>
   * <b>Postcondition:</b> if the preconditions are met the player has placed 
   * the cards he wanted on the railyard
   */
    //The appropirate checks are being done in the game control package
    public void playCards(ArrayList<TrainCards> choosenCards){
         for(int i=0;i<choosenCards.size();++i){
             if(choosenCards.get(i).getType().equals("Balander")){
                choosenCards.get(i).setColor(TrainCards.colors.red);
                trainCStacks.get(0).getStack().push(choosenCards.get(i));
             }
             else{
                trainCStacks.get(choosenCards.get(i).getIdIndex()).getStack().push(choosenCards.get(i));
             }
         }
    }
    
    public void stealCards(ArrayList<TrainCards> cardsOfTheSameColor,Player opponentP){
        for(int s = 0 ; s < cardsOfTheSameColor.size(); ++s){
            opponentP.getRailyard().getTrainStacks().get(cardsOfTheSameColor.get(s).getIdIndex()).getStack().clear();
        }
    }
    
    public Boolean checkForTrainRobbing(ArrayList<TrainCards> cards,Player opponentP){
        return false;
    }
    
    public List<RailyardPos> getTrainStacks(){
        return this.trainCStacks;
    }
}
