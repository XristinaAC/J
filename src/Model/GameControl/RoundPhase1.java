package Model.GameControl;
import Model.Player.Player;

public class RoundPhase1 {
  
    //Some checks are missing
    public static boolean moveCardsFromRailyard(Player p){
        boolean isEmpty = true;
        for(int i = 0; i < p.getRailyard().getTrainStacks().size();++i){
            if(p.getRailyard().getTrainStacks().get(i).getStack().empty() == false){
                isEmpty = false;
                break;
            }
        }
        if(isEmpty == false){
            p.getRailyard().MoveCards(p.getOnTheTrack());
        }
        
        return isEmpty;
    }
}