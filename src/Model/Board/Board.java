package Model.Board;

import java.util.ArrayList;
import java.util.Stack;
import Model.Cards.*;
import Model.Player.Player;
import Model.PPanels.*;
import java.util.Collections;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

public class Board {
    private Stack<TrainCards> trainCards;
    private Stack<DestinationCards> Tickets;
    private ArrayList<BigCitiesCards> BonusCards;
    private Deck deck;
    private int[] BCSkorTable = {8,8,10,12,12,15};
    
    public Board(){
        trainCards = new Stack<>();
        Tickets = new Stack<>();
        BonusCards = new ArrayList<>();
        deck = new Deck();
        
    }
    
    public void createTrainCards(){
        int cImage = 1;
        int cIndex = 0;
        for (TrainCards.colors c : TrainCards.colors.values()){
           for(int t=0;t<10;++t){
               TrainCards tC = new TrainCards("Color Card");
               tC.setColor(c);
               tC.setImage("TrainCards/"+cImage+".jpg");
               tC.setIdIndex(cIndex);
               trainCards.push(tC);
           }
           cImage++;
           cIndex++;
       }
       
       for(int b =0; b < 16; ++b){
           TrainCards balander = new TrainCards("Balander");
           balander.setColor(null);
           balander.setImage("TrainCards/"+cImage+".jpg");
           balander.setIdIndex(0);
           trainCards.push(balander);
       }
    }
    
    public void readAndCreateDestinationCards(String file) throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(file));
        String sCurrentLine = "";
        int i = -1;
        while ((sCurrentLine = br.readLine()) != null) {
            if (i == -1) {
                i = 0;
                continue;
            }
            String[] splitLine = sCurrentLine.split(",");
            String id = splitLine[0];
            String from = splitLine[1];
            String to = splitLine[2];
            int points = Integer.parseInt(splitLine[3]);
            String colorsList = splitLine[4];
            String[] splitColors = colorsList.split("-");
            ArrayList<String> colors = new ArrayList<String>();
            colors.addAll(Arrays.asList(splitColors));
            String imagePath = splitLine[5];
          
            DestinationCards dC = new DestinationCards();
            
            dC.setId(id);
            dC.setArrivalCity(to);
            dC.setDepartureCity(from);
            dC.setPoints(points);
            dC.setColors(colors);
            dC.setImage("destination_Tickets/"+imagePath);
            Tickets.push(dC);
            
            System.out.println("#####\nCard with ID: " + dC.getId());
            System.out.println("From City: " + dC.getDepartureCity());
            System.out.println("To City: " + dC.getArrivalCity());
            System.out.println("Points: " + dC.getPoints());
            System.out.println("Colors: " + dC.getColors());
            System.out.println("Card Image Path: " + dC.getImage());
            System.out.println("#####\n");
        }
    }
    
    public void createBonusCards(){
        int s = 0;
        for (Cities c : Cities.values()){
            BigCitiesCards bCC = new BigCitiesCards();
            bCC.setCity(c);
            bCC.setPoints(BCSkorTable[s]);
            bCC.setImage("bigCitiesCards/"+s+".jpg");
            
            ++s;
            
            BonusCards.add(bCC);
        }
    }
    
    public void initDeckCards(){
        deck.initStacks(trainCards, Tickets);
        deck.initBPositions(BonusCards);
    }
    
    public void initPlayerTrainCards(Player p1,Player p2){
        ArrayList<TrainCards> tCardsP1 = new ArrayList<>();
        ArrayList<TrainCards> tCardsP2 = new ArrayList<>();
        
        tCardsP1.add(trainCards.pop());
        tCardsP2.add(trainCards.pop());
        
        Collections.shuffle(trainCards);
        
        for(int i = 0; i < 7 ; ++i){
            TrainCards card = trainCards.pop();
            card.setPos(i);
            tCardsP1.add(card);
        }
        
         
        for(int i = 0; i < 7 ; ++i){
            TrainCards card = trainCards.pop();
            card.setPos(i);
            tCardsP2.add(card);
        }
        
        p1.setTrainCards(tCardsP1);
        p2.setTrainCards(tCardsP2);
    }
    
    public void givePlayerTickets(Player p){
       Collections.shuffle(Tickets);
       
       ArrayList<DestinationCards> dCardsP = new ArrayList<>();
       for(int i = 0; i < 6 ;++i){
            DestinationCards dC = Tickets.pop();
            dCardsP.add(dC);
        }
        
        p.setTickets(dCardsP);
    }
    
    public void updateDeck(){
        
    }
    
    public Stack<TrainCards> getTrainCards(){
        return this.trainCards;
    }
    
   /* public ArrayList<DestinationCards> getTickets(){
        
    }
    
    public ArrayList<BigCitiesCards> getBonusCards(){
        
    }*/
    
    public Deck getDeck(){
        return this.deck;
    }
}
