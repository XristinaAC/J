package Model.Player;
import java.util.ArrayList;
import Model.Cards.*;
import Model.PPanels.*;

/*In this source file I create the class that contains
the attributes that the two players will have in the game
and the methods that are connected with them. 
*/
public class Player {
   private int Score;
   private String Alias;
   private int ID;
   private ArrayList<TrainCards> trainCards;
   private ArrayList<BigCitiesCards> bonusCards;
   private ArrayList<DestinationCards> Tickets;
   private boolean hasPlayed;
   private ArrayList<DestinationCards> buyedTickets;
   private Railyard pRailyard;
   private OnTheTrack pOnTheTrack;
   private boolean turn = false;
   private ArrayList<TrainCards> choosenCards;//For phase2: choise 3
   
   public Player(String name,int id){
       this.Alias = name;
       this.Score = 0;
       this.bonusCards = new ArrayList<>();
       this.buyedTickets = new ArrayList();
       this.pRailyard = new Railyard();
       this.pOnTheTrack =  new OnTheTrack();
       this.ID = id;
       this.choosenCards = new ArrayList<>();
   }
   
   //It's being used for placing the cards to the railyard from the train card track.
   //Also, here, the cards that are choosen will be removed form the player's train cards list.
   public void setChoosenCards(TrainCards card){
       choosenCards.add(card);
   }
   
   public void setTurn(boolean t){
       this.turn = t;
   }
   
   public void updateBuyedTickets(DestinationCards ticket){
       buyedTickets.add(ticket);
   }
   
   public void updateSkor(int points){
       this.Score = this.Score + points;
   }
   
   public void setTrainCards(ArrayList<TrainCards> tCards){
       trainCards = tCards;
   }
   
   public void takeTrainCards(TrainCards tCard){
       trainCards.add(tCard);
   }
   
   public void setTickets(ArrayList<DestinationCards> dCards){
       Tickets = dCards;
   }
   
   public void takeBonusCard(BigCitiesCards card){
       bonusCards.add(card);
   }
   
   //The appropriate checks are made in updateSkor
   public void updateTickets(DestinationCards dCard){
      if(Tickets.size() < 6){
          Tickets.add(dCard);
      }else if(Tickets.size() == 6){
          System.out.println("You already have enough tickets");
      }
   }
   
   //The appropriate checks are made in updateSkor
   public void useTicket(DestinationCards dCard,Player p){
       int points;
       points = dCard.getPoints();
       
       p.updateSkor(points);
   }
   
   public void setGameState(boolean gState){
       hasPlayed = gState;
   }
   
   public Railyard getRailyard(){
       return pRailyard;
   }
   
   public OnTheTrack getOnTheTrack(){
       return pOnTheTrack;
   }
   public boolean getTurn(){
       return turn;
   }
   
   public ArrayList<TrainCards> getChoosenCards(){
       return choosenCards;
   }
   
   public ArrayList<TrainCards> getTrainCards(){
       return trainCards;
   }
    
   public ArrayList<DestinationCards> getTickets(){
       return Tickets;
   }
   
   public ArrayList<BigCitiesCards> getBonusCards(){
       return bonusCards;
   }
   
   public boolean getHasPlayed(){
       return hasPlayed;
   }
   
   public int getSkor(){
       return Score;
   }
    
   public String getAlias(){
       return Alias;
   }
   
   public ArrayList<DestinationCards> getBuyedTickets(){
       return buyedTickets;
   }
   
   public int getID(){
       return ID;
   }
}
