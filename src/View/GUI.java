package View;

import java.net.URL;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import Model.Player.Player;
import Model.Cards.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame{
    JMenuItem fileNewGame ;
    JMenuItem fileSaveGame ;
    JMenuItem fileContinueSavedGame ;
    JMenuItem fileExitGame ;
    JMenuItem instructions;
    JMenuBar menuBar;
    JMenu menuNew;
    JMenu menuSave;
    JMenu menuContinue;
    JMenu menuExit;
    JMenu inst;
    
    JFrame choiseWindow;
    
    //Neautral area components
    JButton deckTrainCards;
    JButton deckTicketsStack;
    JButton startGame;
    JButton finishMyRound;
    ArrayList<JButton> deckTrainPositions;
    ArrayList<JButton> deckBonusCPositions;
    ArrayList<JLabel>  deckLabels;
    
    //Players
    ArrayList<JLayeredPane> playersPanels;
    ArrayList<JLayeredPane> playersCardsPanels;
    ArrayList<JLayeredPane> playersOnTheTrackPanels;
    ArrayList<JLayeredPane> playersRailyardPanels;
    ArrayList<JLayeredPane> playersTicketsPanel;
    ArrayList<JLayeredPane> playersScoreBoards;
    ArrayList<JLabel> playerName;
    ArrayList<JLabel> playerScore;
    ArrayList<JLabel> playerTurn;
    ArrayList<JLabel>[] playersOnTheTrackLabels;
    ArrayList<JButton> playersPlayCardsB;
    ArrayList<JButton> playersMoveCardsB;
    ArrayList<JButton> playersBCCardsB;
    ArrayList<JButton> playersDestinationCB;
    ArrayList<JButton>[] playerTicketsButtons;
    ArrayList<JButton>[] playersTrainCButtons;
    ArrayList<ArrayList<JLabel>>[] playersRailyardLabels;
    
    
    ArrayList<JLabel> OnTheTrackCardLabels ;
    ArrayList<JLabel> OnTheTrackColorLabels;
    
    ArrayList<JFrame> scoreboardBigCitiesWin;
    ArrayList<JFrame> scoreboardTicketsWin;
    ArrayList<JLabel>[] myBigCitiesL;
    ArrayList<JLabel>[] visitedTimesL;
    
    Background board;
 
    private URL imageURL;
    private ClassLoader cldr;
    
    public GUI(){
        super("Ticket To Ride Card Game");
        cldr = this.getClass().getClassLoader();
        menuBar=new JMenuBar();
        this.setJMenuBar(menuBar);
        fileNewGame = new JMenuItem("New");
        fileSaveGame = new JMenuItem("Save");
        fileContinueSavedGame = new JMenuItem("Continue");
        fileExitGame = new JMenuItem("Exit");
        instructions = new JMenuItem("Instructions");
        menuNew = new JMenu("New Game");
        menuSave = new JMenu("Save Game");
        menuContinue = new JMenu("Continue Saved Game");
        menuExit=new JMenu("ExitGame");
        inst = new JMenu("Read Game Instructions");
        
        playersPanels = new ArrayList<>();
        playersCardsPanels = new ArrayList<>();
        playersOnTheTrackPanels = new ArrayList<>();
        playersRailyardPanels = new ArrayList<>();
        playersTicketsPanel = new ArrayList<>();
        playersScoreBoards = new ArrayList<>();
        deckLabels = new ArrayList<>();
        playerName = new ArrayList<>();
        playerScore = new ArrayList<>();
        playerTurn = new ArrayList<>();
        playersOnTheTrackLabels = new ArrayList[2];
        for (int p = 0; p < playersOnTheTrackLabels.length; p++) {
            playersOnTheTrackLabels[p] = new ArrayList<>();
        }
        
        playersPlayCardsB = new ArrayList<>();
        playersMoveCardsB = new ArrayList<>();
        playersBCCardsB = new ArrayList<>();
        playersDestinationCB = new ArrayList<>();
        
        scoreboardBigCitiesWin = new ArrayList<>();
        scoreboardTicketsWin = new ArrayList<>();
        myBigCitiesL = new ArrayList[2];
        for (int c = 0; c < myBigCitiesL.length; c++) {
            myBigCitiesL[c] = new ArrayList<>();
        }
        visitedTimesL = new ArrayList[2];
        for (int v = 0; v < visitedTimesL.length; v++) {
            visitedTimesL[v] = new ArrayList<>();
        }
        
        deckTrainPositions = new ArrayList<>();
        deckBonusCPositions = new ArrayList<>();
        
        playerTicketsButtons = new ArrayList[2];
        for (int t = 0; t < playerTicketsButtons.length; t++) {
            playerTicketsButtons[t] = new ArrayList<>();
        }
        
        playersTrainCButtons = new ArrayList[2];
        for (int t = 0; t < playersTrainCButtons.length; t++) {
            playersTrainCButtons[t] = new ArrayList<>();
        }
        
        playersRailyardLabels = new ArrayList[2];
        for (int r = 0; r < playersRailyardLabels.length; r++) {
            playersRailyardLabels[r] = new ArrayList<>();
            for(int i=0;i<8;++i){
                 playersRailyardLabels[r].add(new ArrayList());
            }
        }
        
        this.setSize(1400, 1030);
        this.setLocation(100, 0);
        this.setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        initComponents();
        
        this.setVisible(true);
    }
    
    
    
    public void initMenuBar(){
        menuBar.add(menuNew);
        menuBar.add(menuSave);
        menuBar.add(menuContinue);
        menuBar.add(menuExit);
        menuBar.add(inst);
        
        menuNew.add(this.fileNewGame);
        menuSave.add(this.fileSaveGame);
        menuContinue.add(this.fileContinueSavedGame);
        menuExit.add(this.fileExitGame);
        inst.add(this.instructions);
        instructions.addActionListener(new instructionsWinListener());
        this.fileSaveGame.addActionListener(new SaveGameHandler());
    }
 
    private void initComponents(){
        createScoreBoardWindows();
        initMenuBar();
        fillTheBackground();
        createPlayerPanels();
        createGameLabels();
        createPlayerButtons();
        createDeckButtons();
        initRailyardLabelPositions();
    }
    
    private void initInstWindow(){
        JFrame instructions = new JFrame("Instructions");
        instructions.setSize(500,200);
        instructions.setLocation(600,150);
        instructions.setResizable(false);
        instructions.setLayout(new BorderLayout());
        
        JLabel ins = new JLabel();
        ins.setText("<html>To start game you have to press the start game button. <br /> "
                + " <br /> Then you have to choose your next actions. For the first round you have four choises<br /> "
                + "<br /> You can take some more new train cards, you can place cards on your railyard area, you can take some<br /> "
                + "<br />more tickets or you can buy one but for the first round that is not possible.</html>");
        
       // "<html>I Finished<br />  My Round </html>"
        instructions.add(ins);
        instructions.setVisible(true);
    }
    
    private void fillTheBackground(){
        URL imageURL = cldr.getResource("background.jpg"); //image
        Image image = new ImageIcon(imageURL).getImage();
        board = new Background(image);
        this.add(board);
    }
    
    JLabel newLabel(String text){
        return new JLabel(text);
    }
    
    Image newImage(String url,int width,int height){
        URL imageURL = cldr.getResource(url);
        Image image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(width, height,java.awt.Image.SCALE_SMOOTH);
        
        return image;
    }
   
    public void createPlayerPanels(){
        
        JLayeredPane player1 = new JLayeredPane();
        JLayeredPane player2 = new JLayeredPane();
       
        JLayeredPane p1CardsTrack = new JLayeredPane();//component 0
        JLayeredPane p1RailYard = new JLayeredPane();//component 1
        JLayeredPane p1TicketsOnHand = new JLayeredPane();//component 2
        JLayeredPane p1OnTheTrackPanel = new JLayeredPane();//componet 3
        JLayeredPane p1Scoreboard = new JLayeredPane();// componet 4
        
        JLayeredPane p2CardsTrack = new JLayeredPane();//component 0
        JLayeredPane p2RailYard = new JLayeredPane();//component 1
        JLayeredPane p2TicketsOnHand = new JLayeredPane();//component 2
        JLayeredPane p2OnTheTrackPanel = new JLayeredPane();//componet 3
        JLayeredPane p2Scoreboard = new JLayeredPane();// componet 4
       
        player1.setBounds(7, 4, 1380,380);
        player1.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.GREEN));
        playersPanels.add(player1);
        player2.setBounds(7, 580, 1380,380);
        player2.setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.RED));
        playersPanels.add(player2);
        
        p1CardsTrack.setBounds(5,5,700,150);
        p2CardsTrack.setBounds(5,220,700,150);
        playersCardsPanels.add(p1CardsTrack);
        playersCardsPanels.add(p2CardsTrack);
        
        p1RailYard.setBounds(5,170,700,200);
        p2RailYard.setBounds(5,5,700,200);
        playersRailyardPanels.add(p1RailYard);
        playersRailyardPanels.add(p2RailYard);
       
        p1TicketsOnHand.setBounds(720,5,400,150);
        p2TicketsOnHand.setBounds(720,220,400,150);
        playersTicketsPanel.add(p1TicketsOnHand);
        playersTicketsPanel.add(p2TicketsOnHand);
        
        p1OnTheTrackPanel.setBounds(720,170,400,200);
        p2OnTheTrackPanel.setBounds(720,5,400,200);
        playersOnTheTrackPanels.add(p1OnTheTrackPanel);
        playersOnTheTrackPanels.add(p2OnTheTrackPanel);
       
        p1Scoreboard.setBounds(1150,5,220,370);
        p2Scoreboard.setBounds(1150,5,220,370);
        playersScoreBoards.add(p1Scoreboard);
        playersScoreBoards.add(p2Scoreboard);
        
        for(int i=0;i< 2;++i){
            playersPanels.get(i).setBackground(Color.WHITE);
            playersPanels.get(i).setOpaque(true);
            
            playersCardsPanels.get(i).setBackground(Color.WHITE);
            playersCardsPanels.get(i).setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
            playersCardsPanels.get(i).setOpaque(true);
            
            playersRailyardPanels.get(i).setBackground(Color.WHITE);
            playersRailyardPanels.get(i).setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
            playersRailyardPanels.get(i).setOpaque(true);
            
            playersTicketsPanel.get(i).setBackground(Color.WHITE);
            playersTicketsPanel.get(i).setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
            playersTicketsPanel.get(i).setOpaque(true);
            
            playersOnTheTrackPanels.get(i).setBackground(Color.WHITE);
            playersOnTheTrackPanels.get(i).setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
            playersOnTheTrackPanels.get(i).setOpaque(true);
            
            playersScoreBoards.get(i).setBackground(Color.WHITE);
            playersScoreBoards.get(i).setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
            playersScoreBoards.get(i).setOpaque(true);
            
            board.add(playersPanels.get(i),0);
            
            playersPanels.get(i).add(playersCardsPanels.get(i));
            playersPanels.get(i).add(playersRailyardPanels.get(i));
            playersPanels.get(i).add(playersTicketsPanel.get(i));
            playersPanels.get(i).add(playersOnTheTrackPanels.get(i));
            playersPanels.get(i).add(playersScoreBoards.get(i));
        }
       
    }
    
    
    public void createGameLabels(){
        JLabel TrainDeck = newLabel("Train Deck: 96 ");
        JLabel DDeck = newLabel("Destination Deck: 46 ");
        JLabel BCCards = newLabel("Available Big Cities Bonus Cards: 6");
        
        board.add(TrainDeck);
        TrainDeck.setBounds(20, 550, 180, 25);
        TrainDeck.setFont(new Font("Times New Roman",Font.BOLD,17));
        TrainDeck.setForeground(Color.yellow);
        deckLabels.add(TrainDeck);
        
        board.add(DDeck);
        DDeck.setBounds(200, 550, 180, 25);
        DDeck.setFont(new Font("Times New Roman",Font.BOLD,17));
        DDeck.setForeground(Color.yellow);
        deckLabels.add(DDeck);
        
        board.add(BCCards);
        BCCards.setBounds(980, 550, 300, 25);
        BCCards.setFont(new Font("Times New Roman",Font.BOLD,17));
        BCCards.setForeground(Color.yellow);
        
        for(int i=0;i<2;++i){
            JLabel name = newLabel("Player: Name");
            JLabel score = newLabel("Player Score: Num");
            JLabel turn = newLabel("Player Turn: yes/no");
            
            playersCardsPanels.get(i).add(newLabel("Train Cards On Hand"));
            playersCardsPanels.get(i).getComponent(0).setBounds(5, 130, 130, 20);
            
            playersRailyardPanels.get(i).add(newLabel("RailYard"));
            playersRailyardPanels.get(i).getComponent(0).setBounds(5, 180, 130, 20);
            
            playersTicketsPanel.get(i).add(newLabel("Destination Tickets On Hand"));
            playersTicketsPanel.get(i).getComponent(0).setBounds(5, 130, 180, 20);
            
            playersOnTheTrackPanels.get(i).add(newLabel("On-The-Track"));
            playersOnTheTrackPanels.get(i).getComponent(0).setBounds(5, 180, 130, 20);
            
            playersScoreBoards.get(i).add(name);//Fix when player in model finished
            name.setBounds(5, 5, 130, 20);
            playerName.add(name);
            playersScoreBoards.get(i).add(score);
            score.setBounds(5, 30, 130, 20);
            playerScore.add(score);
            playersScoreBoards.get(i).add(turn);
            turn.setBounds(5, 55, 130, 20);
            playerTurn.add(turn);
        }
        
        int comp ,boundOTT;
        for(int j=0;j<2;++j){
            comp = 1;
            boundOTT = 0;
            for (TrainCards.colors c : TrainCards.colors.values()){
                JLabel colorCards = newLabel(c.toString() + ": 0");
                playersRailyardPanels.get(j).add(newLabel(c.toString()));
                playersRailyardPanels.get(j).getComponent(comp).setSize(130, 20);
                playersRailyardPanels.get(j).getComponent(comp).setForeground(Color.MAGENTA);
               
                playersOnTheTrackPanels.get(j).add(colorCards);
                
                if(comp<=4){
                    colorCards.setBounds(5+boundOTT, 5, 130, 20);
                }
                else{
                    colorCards.setBounds(5+boundOTT, 5+60, 130, 20);
                }
                
                playersOnTheTrackLabels[j].add(colorCards);
                
                comp++;
                if(comp == 5){
                    boundOTT = 0;
                }else{
                    boundOTT = boundOTT + 100;
                }
            }
            JLabel balander = newLabel("Locomotive: 0");
            playersOnTheTrackPanels.get(j).add(balander);
            balander.setBounds(210, 150, 130, 20);
            playersOnTheTrackLabels[j].add(balander);
        }
        
        for(int i=0;i<2;++i){
            playersRailyardPanels.get(i).getComponent(1).setLocation(5, 5);
            playersRailyardPanels.get(i).getComponent(2).setLocation(90, 5);
            playersRailyardPanels.get(i).getComponent(3).setLocation(180, 5);
            playersRailyardPanels.get(i).getComponent(4).setLocation(260, 5);
            playersRailyardPanels.get(i).getComponent(5).setLocation(360, 5);
            playersRailyardPanels.get(i).getComponent(6).setLocation(460, 5);
            playersRailyardPanels.get(i).getComponent(7).setLocation(540, 5);
            playersRailyardPanels.get(i).getComponent(8).setLocation(620, 5);
        }
        
        int pos;
        int i=0;
        while(i<2){
            pos = 5;
            for(int j=1;j<=9;++j){
                JLabel cardLabel = new JLabel();
                playersOnTheTrackPanels.get(i).add(cardLabel);
                if(j<=4){
                    cardLabel.setBounds(pos,20, 80, 50);
                }
                else{
                    cardLabel.setBounds(pos,20+61, 80, 50);
                }
 
                cardLabel.setIcon(new ImageIcon(newImage("TrainCards/"+j+".jpg",80,50)));
                
                if(j == 4){
                    pos = 5;
                }else{
                    pos = pos + 100;
                }
            }
            JLabel balander = new JLabel();
            playersOnTheTrackPanels.get(i).add(balander);
            balander.setBounds(305,142, 80, 50);
            balander.setIcon(new ImageIcon(newImage("TrainCards/"+9+".jpg",80,50)));
            
            ++i;
        }         
    }
    
    public void createPlayerButtons(){
        for(int i = 0;i<2;++i){
            JButton playCards = new JButton("Play Cards");
            playCards.setBounds(590, 120, 100, 20);
            playersCardsPanels.get(i).add(playCards);
            playersPlayCardsB.add(playCards);
            
            JButton moveCards = new JButton("Move Cards to On-The-Track");
            moveCards.setBounds(490, 170, 200, 20);
            playersRailyardPanels.get(i).add(moveCards);
            playersMoveCardsB.add(moveCards);
            
            JButton destinationTickets = new JButton("<html>My Destination <br /> Tickets</html>");
            destinationTickets.setBounds(49, 130, 120, 60);
            destinationTickets.addActionListener(new ScoreboardButtonListeners(i, scoreboardTicketsWin));
            playersScoreBoards.get(i).add(destinationTickets);
            playersDestinationCB.add(destinationTickets);
            
            JButton BCCards = new JButton("<html>My Big Cities <br /> Bonus Cards</html>");
            BCCards.setBounds(49, 200, 120, 60);
            BCCards.addActionListener(new ScoreboardButtonListeners(i, scoreboardBigCitiesWin));
            playersScoreBoards.get(i).add(BCCards);
            playersBCCardsB.add(BCCards);
        }
    }
    
    public void createDeckButtons(){
        deckTrainCards = new JButton();
        deckTrainCards.setBounds(42,400,80,140);
        deckTrainCards.setIcon(new ImageIcon(newImage("TrainCards/"+0+".jpg",80,140)));
        board.add(deckTrainCards);
        
        deckTicketsStack = new JButton();
        deckTicketsStack.setBounds(242,400,80,140);
        deckTicketsStack.setIcon(new ImageIcon(newImage("destination_Tickets/desBackCard.jpg",80,140)));
        board.add(deckTicketsStack);
        
        startGame = new JButton("Start Game");
        startGame.setBounds(130,400,100,40);
        board.add(startGame);
        
        finishMyRound = new JButton("<html>I Finished<br />  My Round </html>");
        finishMyRound.setBounds(130,500,100,40);
        board.add(finishMyRound);
        
        int Pos=410;
        for(int i=0;i<5;++i){
            JButton trainPos = new JButton();
            trainPos.setBounds(Pos,400,80,140);
            trainPos.setContentAreaFilled(false);
            board.add(trainPos);
            deckTrainPositions.add(trainPos);
            
            Pos = Pos + 90;
        }
        
        Pos = 930;
        for(int i=0;i<6;++i){
            JButton bigCPos = new JButton();
            bigCPos.setBounds(Pos,415,60,110);
            bigCPos.setIcon(new ImageIcon(newImage("bigCitiesCards/"+i+".jpg",60,110)));
            board.add(bigCPos);
            deckBonusCPositions.add(bigCPos);
            
            Pos = Pos + 70;
        }
    }
    
    public void createScoreBoardWindows(){
        int pos;
        for(int i=0;i<2;++i){
            JFrame bigCWin = new JFrame("My Big Cities");
            bigCWin.setSize(905,290);
            bigCWin.setLocation(600,150);
            bigCWin.setResizable(false);
            bigCWin.setLayout(null);
            pos=0;
            
            for(int b=0;b<6;++b){
                JLabel bigCity = new JLabel();
                JLabel visitedTimes = new JLabel("Visited : 0 Times");
                
                visitedTimes.setBounds(pos+10,180,130,100);
                visitedTimes.setFont(new Font("Times New Roman",Font.BOLD,17));
                
                bigCity.setSize(150, 200);
                bigCity.setIcon(new ImageIcon(newImage("bigCitiesCards/"+b+".jpg",150,200)));
                bigCity.setLocation(pos,0);
                bigCity.setEnabled(false);
           
                bigCWin.add(bigCity);
                bigCWin.add(visitedTimes);
                
                myBigCitiesL[0].add(bigCity);
                visitedTimesL[0].add(visitedTimes);
                myBigCitiesL[1].add(bigCity);
                visitedTimesL[1].add(visitedTimes);
                pos = pos + 150;
            }
            scoreboardBigCitiesWin.add(bigCWin);
        }
       
        for(int i=0;i<2;++i){
            JFrame ticketsWin = new JFrame("Buyed Destination Tickets");
            ticketsWin.setSize(905,290);
            ticketsWin.setLocation(600,150);
            ticketsWin.setResizable(false);
            ticketsWin.setLayout(new GridLayout());
            scoreboardTicketsWin.add(ticketsWin);
        }
    }
    
    public void initDeckTrainCPositions(ArrayList<TrainCards> cards){
        for(int i =0 ; i<5;++i){
            deckTrainPositions.get(i).setIcon(new ImageIcon(newImage(cards.get(i).getImage(),80,140)));
        }
    }
    
    public void updateTrainCardLabel(int size){
        deckLabels.get(0).setText("Train Deck : "+size);
    }
    
    public void updateTicketsCardLabel(int size){
        deckLabels.get(1).setText("Train Deck : "+size);
    }
    
    public void updatePlayerInformation(Player p){
        if(playerName.get(p.getID()).getText() != p.getAlias()){
            playerName.get(p.getID()).setText("Player Name: "+ p.getAlias());
        }
        
        playerScore.get(p.getID()).setText("Player Score:"+p.getSkor());
        if(p.getTurn() == true){
            playerTurn.get(p.getID()).setText("Player Turn: Yes");
        }else{
            playerTurn.get(p.getID()).setText("Player Turn: No");
        }
        
    }
    
    public void initTickets(Player p){
        int pos = 5;
        JFrame ticketsWin = new JFrame("Pick the tickets you do not wish to keep");
        ticketsWin.setSize(800,290);
        ticketsWin.setLocation(600,150);
        ticketsWin.setResizable(false);
        ticketsWin.setLayout(null);
        //ticketsWin.setVisible(true);
        Boolean hasBeenChoosen = true;
        
        for(int i = 0; i < p.getTickets().size(); ++i){
            JButton ticket = new JButton();
            ticket.setBounds(pos,5,80,120);
            ticket.setIcon(new ImageIcon(newImage(p.getTickets().get(i).getImage(),80,120)));
            //ticketsWin.add(ticket);
            //ticket.addActionListener( new TicketsButtonListeners(i,p1,ticket,dStack,hasBeenChoosen));
            playersTicketsPanel.get(p.getID()).add(ticket);
            playerTicketsButtons[p.getID()].add(ticket);
        
            pos = pos + 50;
        }
    }
    
    public void initChoiseWindow(Player p){
        choiseWindow = new JFrame("CHOOSE YOUR NEXT MOVE");
        choiseWindow.setSize(200,290);
        choiseWindow.setLocation(600,150);
        choiseWindow.setResizable(false);
        choiseWindow.getContentPane().setLayout(new FlowLayout());
        choiseWindow.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        choiseWindow.setVisible(true);
        
        JButton trainCards = new JButton("Take train Cards");
        trainCards.addActionListener(new TrainCardChoiseButtonListener(p));
        choiseWindow.add(trainCards);
        
        JButton playCards = new JButton("Play Cards");
        playCards.addActionListener(new choisePlayCardsListener(p));
        choiseWindow.add(playCards);
        
        JButton buyTickets = new JButton("Buy Tickets");
        buyTickets.addActionListener(new choiseBuyTicketsListener(p));
        choiseWindow.add(buyTickets);
    }
    
    public void initTrainCards(Player p){
        int pos =5;
        for(int i = 0; i < p.getTrainCards().size() ; ++i){
            JButton trainC = new JButton();
            trainC.setBounds(pos,5,80,120);
            trainC.setIcon(new ImageIcon(newImage(p.getTrainCards().get(i).getImage(),80,120)));
            trainC.addActionListener(new PTrainCardsListener(p,p.getTrainCards().get(i)));
            playersCardsPanels.get(p.getID()).add(trainC);
            playersTrainCButtons[p.getID()].add(trainC);
            
            pos = pos + 50;
        }
    }
    
    public void initRailyardLabelPositions(){
        for(int i = 0; i <2;++i){
            for(int j=0;j<8;++j){
                JLabel trainC = new JLabel();
                //trainC.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GREEN));
                playersRailyardPanels.get(i).add(trainC);
                playersRailyardLabels[i].get(j).add(trainC);
            }
            playersRailyardLabels[i].get(0).get(0).setBounds(5, 80, 60, 100);
            playersRailyardLabels[i].get(1).get(0).setBounds(87, 80, 60, 100);
            playersRailyardLabels[i].get(2).get(0).setBounds(175, 80, 60, 100);
            playersRailyardLabels[i].get(3).get(0).setBounds(258, 80, 60, 100);
            playersRailyardLabels[i].get(4).get(0).setBounds(357, 80, 60, 100);
            playersRailyardLabels[i].get(5).get(0).setBounds(457, 80, 60, 100);
            playersRailyardLabels[i].get(6).get(0).setBounds(540, 80, 60, 100);
            playersRailyardLabels[i].get(7).get(0).setBounds(620, 80, 60, 100);
        }
    }
    
    public void disableAndEnablePlayer(boolean function,int p){
        Component[] comO = playersOnTheTrackPanels.get(p).getComponents();
        for (int a = 0; a < comO.length; a++) {
            comO[a].setEnabled(function);
        } 
       
        Component[] comR = playersRailyardPanels.get(p).getComponents();
        for (int a = 0; a < comR.length; a++) {
            comR[a].setEnabled(function);
        }
       
        Component[] comT = playersTicketsPanel.get(p).getComponents();
        for (int a = 0; a < comT.length; a++) {
            comT[a].setEnabled(function);
        }
        
        Component[] comTC = playersCardsPanels.get(p).getComponents();
        for (int a = 0; a < comTC.length; a++) {
            comTC[a].setEnabled(function);
        } 
    }
    
    public void moveTCardsFromTrainStackToTrainP(Player p,TrainCards card){
        JButton trainC = new JButton();
        trainC.setBounds(playersTrainCButtons[p.getID()].get(playersTrainCButtons[p.getID()].size()-1).getX()+50,5,80,120);
        trainC.setIcon(new ImageIcon(newImage(card.getImage(),80,120)));
        trainC.addActionListener(new PTrainCardsListener(p,p.getTrainCards().get(p.getTrainCards().size()-1)));
        playersTrainCButtons[p.getID()].add(trainC);
        playersCardsPanels.get(p.getID()).add(trainC);
    }
    
    public void moveTCardsFromTrainPosToTrainP(int pos,Player p,TrainCards toBeGiven,TrainCards replacement){
        JButton trainC = new JButton();
        trainC.setBounds(playersTrainCButtons[p.getID()].get(playersTrainCButtons[p.getID()].size()-1).getX()+50,5,80,120);
        trainC.setIcon(new ImageIcon(newImage(toBeGiven.getImage(),80,120)));
        trainC.addActionListener(new PTrainCardsListener(p,p.getTrainCards().get(p.getTrainCards().size()-1)));
        playersTrainCButtons[p.getID()].add(trainC);
        playersCardsPanels.get(p.getID()).add(trainC);
        
        deckTrainPositions.get(pos).setIcon(new ImageIcon(newImage(replacement.getImage(),80,140)));
    }

    public void messageWindow(String Text){
        JFrame messageWin = new JFrame("Message window");
        messageWin.setSize(400,100);
        messageWin.setLocation(800,300);
        messageWin.setResizable(false);
        messageWin.setLayout(null);
        
        JLabel message = new JLabel(Text);
        message.setBounds(50,-10, 300, 100);
        message.setFont(new Font("Times New Roman",Font.BOLD,17));
        message.setForeground(Color.RED);
        messageWin.add(message);
        
        messageWin.setVisible(true);
    }
    
    //Callde by the play cards Handler.
    public void moveCardsToRailYard(Player p){
        ArrayList<JButton> trainCards = new ArrayList<>();
        
        for(int i = 0; i < p.getRailyard().getTrainStacks().size();++i){
            if(p.getRailyard().getTrainStacks().get(i).getStack().empty() == false){
                
                int position = p.getRailyard().getTrainStacks().get(i).getStack().size();
                
                for(int j=0;j<p.getRailyard().getTrainStacks().get(i).getStack().size();++j){
                    
                    String image = p.getRailyard().getTrainStacks().get(i).getStack().get(j).getImage();
                    JLabel trainCard = new JLabel();
                    
                    int colorId = p.getRailyard().getTrainStacks().get(i).getStack().get(j).getIdIndex();
                    int positionX = playersRailyardLabels[p.getID()].get(colorId).get(playersRailyardLabels[p.getID()].get(colorId).size()-1).getX();
                    int positionY = playersRailyardLabels[p.getID()].get(colorId).get(playersRailyardLabels[p.getID()].get(colorId).size()-1).getY();
                    
                    trainCard.setBounds(positionX,positionY-10,60,100);
                    playersRailyardLabels[p.getID()].get(0).add(trainCard);
                    
                    trainCard.setIcon(new ImageIcon(newImage(image,60,100)));
                    playersRailyardPanels.get(p.getID()).add(trainCard);
                    
                    for(int c = 0;c < playersTrainCButtons[p.getID()].size();++c){
                        if( p.getRailyard().getTrainStacks().get(i).getStack().get(j).getPos() == c){
                            //trainCards.add(playersTrainCButtons[p.getID()].get(c));
                            playersTrainCButtons[p.getID()].get(c).setVisible(false);
                        }
                    }
                }
            }
        }
    }
    
    public void moveCardsOnTheTrack(Player p){
        String color;
        for(int i = 0;i < p.getOnTheTrack().getPositions().size();++i){
            if(p.getOnTheTrack().getPositions().get(i).getPosColor() == null){
                color = "Locomotive";
            }else{
                color = p.getOnTheTrack().getPositions().get(i).getPosColor().toString();
            }
            int cards = p.getOnTheTrack().getPositions().get(i).getAvailableCards();
            playersOnTheTrackLabels[p.getID()].get(i).setText(color +" : "+cards);
        }
    }
    
    public void moveCardsFromDeckToTicketPanel(){
        
    }
    
    public void moveCardsFromTicketPanelToTicketW(Player p,int ticket){
        playerTicketsButtons[p.getID()].get(ticket).setVisible(false);
        playerTicketsButtons[p.getID()].remove(ticket);
        
        String image = p.getTickets().get(ticket).getImage();
        
        JLabel ticketCard = new JLabel();
        ticketCard.setIcon(new ImageIcon(newImage(image,100,200)));
        
        scoreboardTicketsWin.get(p.getID()).add(ticketCard);
    }
    
    public ArrayList<JButton> getTrainDeckPosButtons(int id){
        return this.deckTrainPositions;
    }
    
    /*public ArrayList<JButton> getTicketsButtons(int id){
        
    }*/
    
    public ArrayList<JButton> getMoveCardsButtons(){
        return playersMoveCardsB;
    }
    
    public ArrayList<JButton> getTrainDeckPositions(){
        return deckTrainPositions;
    }
    
    public ArrayList<JButton> getPlayCardsButton(){
        return playersPlayCardsB;
    }
    
    public ArrayList<JButton> getPlayersTicketsButtons(int p){
        return playerTicketsButtons[p];
    }
    
    public JButton getDeckTrainStackButton(){
        return this.deckTrainCards;
    }
    
    public JMenuItem getNewGame(){
        return this.fileNewGame;
    }
    
    public JMenuItem getExitGame(){
        return this.fileExitGame;
    }
    
    public JButton getStartGameButton(){
        return this.startGame;
    }
    
    public JButton getFinishRoundButton(){
        return finishMyRound;
    }
 
    public void updateBonusCardW(){
        
    }
    
    public void updateTicketsW(){
        
    }
    
    class ScoreboardButtonListeners implements ActionListener {
        private int win;
        private ArrayList<JFrame> window;
        public ScoreboardButtonListeners(int index,ArrayList<JFrame> window){
            this.win = index;
            this.window = window;
        }
        public void actionPerformed(ActionEvent e) {
            this.window.get(this.win).setVisible(true);
        }

    }
    
    class TrainCardChoiseButtonListener implements ActionListener {
        private Player p;
        public TrainCardChoiseButtonListener(Player p){
            this.p = p;
        }
        public void actionPerformed(ActionEvent e) {
            deckTrainCards.setEnabled(true);
            for(int i=0; i<5;++i){
                deckTrainPositions.get(i).setEnabled(true);
            }
            finishMyRound.setEnabled(true);
            playersMoveCardsB.get(p.getID()).setEnabled(false);
            choiseWindow.setVisible(false);
        }
    }
            
    class PTrainCardsListener implements ActionListener {
        private Player player;
        private TrainCards card;
        public PTrainCardsListener(Player p,TrainCards c){
            this.player = p;
            this.card = c;
        }
        public void actionPerformed(ActionEvent e) {
            if(player.getChoosenCards().size() >= 3){
                messageWindow("<html>No more cards can be choosen <br />  The next step is to play your cards </html>");
            }else{
                player.setChoosenCards(card);
                System.out.println("Card choosen");
            }
        }
    }
    
    class choisePlayCardsListener implements ActionListener {
        private Player p;
        
        public choisePlayCardsListener(Player p){
            this.p = p;
        }
    
        public void actionPerformed(ActionEvent e) {
           playersPlayCardsB.get(p.getID()).setEnabled(true);
           playersMoveCardsB.get(p.getID()).setEnabled(false);
           choiseWindow.setVisible(false);
           finishMyRound.setEnabled(true);
        }
    }
    
    class  choiseBuyTicketsListener implements ActionListener {
        private Player p;
        
        public  choiseBuyTicketsListener(Player p){
            this.p = p;
        }
    
        public void actionPerformed(ActionEvent e) {
           for(int i = 0; i < playerTicketsButtons[p.getID()].size();++i){
               playerTicketsButtons[p.getID()].get(i).setEnabled(true);
           }
           choiseWindow.setVisible(false);
           finishMyRound.setEnabled(true);
        }
    }
    
    class instructionsWinListener implements ActionListener {
        
        public instructionsWinListener(){
        }
    
        public void actionPerformed(ActionEvent e) {
            initInstWindow();
        }
    }
    
    /*class TicketsButtonListeners implements ActionListener {
        private int cIndex;
        private Player p;
        private JButton butt;
        Stack<DestinationCards> S;
        private Boolean hasBeenChoosen;
        public TicketsButtonListeners(int card,Player p,JButton butt,Stack<DestinationCards> dStack,Boolean hasBeenChoosen){
            this.cIndex = card;
            this.p = p;
            this.butt = butt;
            this.S = dStack;
            this.hasBeenChoosen = hasBeenChoosen;
        }
        public void actionPerformed(ActionEvent e) {
           if(p.getTickets().size() == 1){
               return;
           }
           S.push(p.getTickets().remove(cIndex));
           butt.setVisible(false);
           updateTicketsCardLabel(S.size());
           hasBeenChoosen = false;
        }
    }*/
}

 
