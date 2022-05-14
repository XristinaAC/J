package Controler;
import Model.Board.Board;
import Model.Player.Player;
import Model.Cards.*;
import Model.GameControl.*;
import View.GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Controler {
    ArrayList<Player> Players;
    Board Tablo;
    GUI gameSpace;
    private boolean tCstackButtonPushed;
    private boolean dpositionTCButtonPushed;
    private boolean dTicketButtonPushed;
    //private boolean running = false;
    private int takeCardCounter = 0;
    private boolean phase2Choise1_isOver = false;
     private boolean hasStarted = false;
    private boolean roundHasEnded;
    private boolean playerHasPlayed;
    private int turn = 0;
    private int playersPlayed;
    private int round;
    
    public Controler(){
        gameSpace = new GUI(); 
        gameSpace.getNewGame().addActionListener(new NewGameHandler());
        gameSpace.getExitGame().addActionListener(new ExitGameHandler());
        gameSpace.getStartGameButton().addActionListener(new StartGameHandler());
        gameSpace.getFinishRoundButton().addActionListener(new FinishRoundHandler());
        
        initGameComponets();
    }
    
    public void addHandlerToDeckTrainCardButtons(){
        for(int i = 0; i<5;++i){
            gameSpace.getTrainDeckPositions().get(i).addActionListener(new DeckTainPositionsListener(i));
        }
    }
    
    public void addHandlerToPlayCardsButton(){
        for(int i=0;i<2;++i){
            gameSpace.getPlayCardsButton().get(i).addActionListener(new PlayCardsListener());
        }
    }
    
    public void addHandlerToMoveCardsButton(){
        for(int i=0;i<2;++i){
            gameSpace.getMoveCardsButtons().get(i).addActionListener(new MoveCardsdHandler());
        }
    }
    
    public void addHanlderToTheTickets(){
        for(int j = 0; j < 2; ++j){
            for(int i = 0; i < gameSpace.getPlayersTicketsButtons(j).size();++i){
                gameSpace.getPlayersTicketsButtons(j).get(i).addActionListener(new buyTicketsHandler(i));
            }
        }
    }
 
    public void initGameComponets(){
       Players = new ArrayList<>();
       Players.add(new Player("Uther",0));
       Players.add(new Player("Morgana",1));
       Tablo = new Board();
       Tablo.createTrainCards();
        try {
            String filePath = new File("").getAbsolutePath();
            Tablo.readAndCreateDestinationCards(filePath + "/src/file/destinationCards.csv");
        } catch (IOException ex) {
            Logger.getLogger(DestinationCards.class.getName()).log(Level.SEVERE, null, ex);
        }
        Tablo.createBonusCards();
    }
    
    public void startGame(){
        round = 0;
        Tablo.initPlayerTrainCards(Players.get(0), Players.get(1));
        Tablo.givePlayerTickets(Players.get(0));
        Tablo.givePlayerTickets(Players.get(1));
        Tablo.initDeckCards();
        Tablo.getDeck().initTPositions(Tablo.getDeck().getTrainCardsStack());
        gameSpace.getDeckTrainStackButton().setEnabled(false);
        gameSpace.getDeckTrainStackButton().addActionListener(new TainStackListener());
        gameSpace.initDeckTrainCPositions(Tablo.getDeck().getTrainCardsPos());
        addHandlerToDeckTrainCardButtons();
        gameSpace.updateTrainCardLabel(Tablo.getDeck().getTrainCardsStack().size());
        gameSpace.updateTicketsCardLabel(Tablo.getDeck().getDestinationCardStack().size());
        
        computePlayerScore();
        
        addHandlerToMoveCardsButton();
        
        gameSpace.getMoveCardsButtons().get(0).setEnabled(false);
        gameSpace.getMoveCardsButtons().get(0).setEnabled(false);
        
        for(int i = 0;i<2;++i){
            gameSpace.initTrainCards(Players.get(i));
            gameSpace.initTickets(Players.get(i));
        }
        addHandlerToPlayCardsButton();
        addHanlderToTheTickets();
         
        turn = 0;
        Players.get(0).setTurn(true);
        Players.get(1).setTurn(false);
        
        for(int i = 0;i < gameSpace.getPlayersTicketsButtons(turn).size();++i){
             gameSpace.getPlayersTicketsButtons(turn).get(i).setEnabled(false);
        }
        
        gameSpace.updatePlayerInformation(Players.get(0));
        gameSpace.updatePlayerInformation(Players.get(1));
        
        playerHasPlayed = false;
        gameSpace.disableAndEnablePlayer(false, 1);
        gameSpace.initChoiseWindow(Players.get(turn));//Ston handler tou play cards button
    }
    
    public void computePlayerScore(){
        ArrayList<DestinationCards> tickets;
        for(int i =0 ;i <2;++i){
            int newScore = 0 ;
            int currentScore;
            tickets = Players.get(i).getTickets();
            for(int j =0; j < tickets.size();++j){
                newScore = newScore + tickets.get(j).getPoints();
            }
            currentScore = Players.get(i).getSkor();
            currentScore = currentScore - newScore;
            Players.get(i).updateSkor(currentScore);
        }
    }
    
    void nextPlayer(){
       if(turn == 0){
            Players.get(turn).setTurn(false);
            gameSpace.updatePlayerInformation(Players.get(turn));
            turn = 1;
            Players.get(turn).setTurn(true);
            gameSpace.updatePlayerInformation(Players.get(turn));
        }else if(turn == 1){
            Players.get(turn).setTurn(false);
            gameSpace.updatePlayerInformation(Players.get(turn));
            turn = 0;
            Players.get(turn).setTurn(true);
            gameSpace.updatePlayerInformation(Players.get(turn));
        }
    }
    
    public void newPlayerRound(){
        if(playerHasPlayed == false){
            System.out.println("You have to finish you round");
        }else{
            if(playersPlayed == 2){
                playersPlayed = 0;
                round++;
            }
            gameSpace.disableAndEnablePlayer(false, turn);
            nextPlayer();
            if(round == 0){
                gameSpace.initChoiseWindow(Players.get(turn));
            }else{
                gameSpace.getMoveCardsButtons().get(turn).setEnabled(true);
            }
            playerHasPlayed = false;
            gameSpace.disableAndEnablePlayer(true, turn);
            for(int i = 0;i < gameSpace.getPlayersTicketsButtons(turn).size();++i){
                gameSpace.getPlayersTicketsButtons(turn).get(i).setEnabled(false);
            }
            gameSpace.getPlayCardsButton().get(Players.get(turn).getID()).setEnabled(false);
            gameSpace.getFinishRoundButton().setEnabled(false);
        }
    }
    
    public void roundPhase2_choise1(){
        if(takeCardCounter >= 2){
            for(int i =0;i<5;++i){
                gameSpace.getTrainDeckPositions().get(i).setEnabled(false);
            }
            gameSpace.getDeckTrainStackButton().setEnabled(false);
            takeCardCounter = 0;
            playerHasPlayed = true;
            playersPlayed++;
       }
    }
    
    public void roundPhase2_choise2(){
        
    }
    
    public void roundPhase2_choise3(){
        
    }
    
    public void roundPhase2_choise4(){
        
    }
    
    public boolean getHasStarted(){
        return hasStarted;
    }
    
    
    
    class TainStackListener implements ActionListener {
        public TainStackListener(){
        }
        
        public void actionPerformed(ActionEvent e) {
                System.out.println("Pop and give");
                System.out.println(Players.get(turn).getTrainCards().size());
                RoundPhase2.c1_TakeCardFromStack(Tablo.getDeck(), Players.get(turn));
                gameSpace.moveTCardsFromTrainStackToTrainP(Players.get(turn),Players.get(turn).getTrainCards().get(Players.get(turn).getTrainCards().size()-1));
                gameSpace.updateTrainCardLabel(Tablo.getDeck().getTrainCardsStack().size());
                System.out.println(Players.get(turn).getTrainCards().size());
                takeCardCounter++;
                roundPhase2_choise1();
        }
    }
    
    class DeckTainPositionsListener implements ActionListener {
        private int pos;
        public DeckTainPositionsListener(int pos){
            this.pos = pos;
        }
        
        public void actionPerformed(ActionEvent e) {
                System.out.println("Pop and give");
                System.out.println(Players.get(turn).getTrainCards().size());
                RoundPhase2.c1_TakeCardFromPosition(Tablo.getDeck(),Players.get(turn) ,pos);
                gameSpace.moveTCardsFromTrainPosToTrainP(pos,Players.get(turn),Players.get(turn).getTrainCards().get(Players.get(turn).getTrainCards().size()-1),Tablo.getDeck().getTrainCardsPos().get(pos));
                gameSpace.updateTrainCardLabel(Tablo.getDeck().getTrainCardsStack().size());
                System.out.println(Players.get(turn).getTrainCards().size());
                takeCardCounter++;
                roundPhase2_choise1();
        }
    }
    
     class PlayCardsListener implements ActionListener {
        private int railyard;
        public PlayCardsListener(){
        }
        
        public void actionPerformed(ActionEvent e) {
            if(Players.get(turn).getChoosenCards().size() != 0){
                int op;
                if(turn == 0){
                    op = 1;
                }else{
                    op = 0;
                }
                RoundPhase2.c3_placeCardsOnTheRailyard(Players.get(turn),Players.get(op));
                gameSpace.moveCardsToRailYard(Players.get(turn));
                gameSpace.getPlayCardsButton().get(Players.get(turn).getID()).setEnabled(false);
                playerHasPlayed = true;
                playersPlayed++;
            }else{
                System.out.println("You haven't choosen any cards");
            }
        }
    }
     
    public class MoveCardsdHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            boolean emptyRailyard;
            
            emptyRailyard = RoundPhase1.moveCardsFromRailyard(Players.get(turn));
            
            if( emptyRailyard == true ){
                gameSpace.initChoiseWindow(Players.get(turn));
            }
            else{
                gameSpace.moveCardsOnTheTrack(Players.get(turn));
                gameSpace.initChoiseWindow(Players.get(turn));
            } 
        }
    }
    
     public class buyTicketsHandler implements ActionListener{
        private int dCard;
        
        public buyTicketsHandler(int dCard){
            this.dCard = dCard;
        }
        public void actionPerformed(ActionEvent e){
            boolean canBuy ;
            
            canBuy = RoundPhase2.c4_buyTickets(Players.get(turn),dCard);
            
            if(canBuy == true){
                gameSpace.moveCardsFromTicketPanelToTicketW(Players.get(turn),dCard);
            }else{
                System.out.println("You cannot buy tis ticket");
                gameSpace.messageWindow("You cannot buy tis ticket");
            }
          
            playerHasPlayed = true;
        }
    }
    
    public class FinishRoundHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
             newPlayerRound();
        }
    }
    
    public class NewGameHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String [] options=new String[]{"YES","NO"};
       
            int response=JOptionPane.showOptionDialog(null,"Dou you really want a new Game","NEW GAME",
                 JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
            
            if(response == JOptionPane.YES_OPTION){
                gameSpace.setVisible(false);
                new Controler();
            }else if(response == JOptionPane.NO_OPTION){
                return;
            }
        }
    }
    
    public class ExitGameHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
       
        System.err.println("Exiting normally.");
        System.exit(0);
    }
}
    
   public class StartGameHandler implements ActionListener{
       public void actionPerformed(ActionEvent e){
           hasStarted = true;
           startGame();
           gameSpace.getStartGameButton().setEnabled(false);
           gameSpace.getFinishRoundButton().setEnabled(false);
       }
   }
}