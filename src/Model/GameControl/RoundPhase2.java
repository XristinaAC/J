package Model.GameControl;
import Model.PPanels.*;
import Model.Player.Player;

public class RoundPhase2 {
    //Choise 1_The player takes trainCards
    public static void c1_TakeCardFromStack(Deck deck,Player p){
        deck.takeFromTrainSCard(p);
    }
    
    public static void c1_TakeCardFromPosition(Deck deck,Player p,int pos){
        deck.TakeAndUpdatetTrainCPosition(deck.getTrainCardsStack(),p,pos);
    }
    
    //Choise 2_The player takes destination cards
    public static void c2_TakeDestinationCards(Deck deck,Player p){
        
    }
    
    //Choise 3_The player puts cards on the Railyard
    //It checks for the corresponding cases through the choosenCards player's field
    //and it also checks if the player can play these choosen cards with appropriate controls.
    public static void c3_placeCardsOnTheRailyard(Player p,Player pOpponent){
         int j = 0;
        p.getRailyard().playCards(p.getChoosenCards());
        
        for(int i = 0;i<p.getChoosenCards().size();++i){
            p.getTrainCards().remove(p.getChoosenCards().get(i));
            while( j < p.getTrainCards().size()){
                if( p.getChoosenCards().get(i).getIdIndex() == p.getTrainCards().get(j).getIdIndex() ){
                    p.getTrainCards().remove(p.getChoosenCards().get(i));
                }
                ++j;
            }
        }
        
        p.getChoosenCards().clear();
        
        for(int i = 0; i<p.getTrainCards().size();++i){
            p.getTrainCards().get(i).setPos(i);
        }
    }
    //
    
    //Choise_4_The player decides to buy a ticket
    public static boolean c4_buyTickets(Player p,int ticket){
        int[] colors = new int[p.getTickets().get(ticket).getColors().size()];
        boolean canBuy = true;
        for(int i = 0; i < p.getTickets().get(ticket).getColors().size();++i){
            colors[i] = 0;
        }
        
        for(int i = 0; i < p.getTickets().get(ticket).getColors().size();++i){
            for(int j = 0; j < p.getOnTheTrack().getPositions().size();++j){
                if(  p.getTickets().get(ticket).getColors().get(i) == p.getOnTheTrack().getPositions().get(j).getPosColor() ){
                    colors[i]++;
                }
            }
        }
        
        for(int i = 0; i < p.getTickets().get(ticket).getColors().size();++i){
            for(int j = 0; j < p.getOnTheTrack().getPositions().size();++j){
                if(  p.getTickets().get(ticket).getColors().get(i) == p.getOnTheTrack().getPositions().get(j).getPosColor() ){
                    if(colors[i] !=  p.getOnTheTrack().getPositions().get(j).getAvailableCards()){
                        canBuy = false;
                        return canBuy;
                    }
                }
            }
        }
        
        if(canBuy == true){
            p.updateBuyedTickets(p.getTickets().get(ticket));
            p.getTickets().remove(ticket);
        }
        
        return canBuy;
    }
    
    //Is called by the function: c4_buyTickets(Player p)
    private static void checkBigCitiesVisits(Player p){
        
    }
}