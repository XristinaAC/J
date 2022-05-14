package Model.PPanels;
import java.util.Stack;
import Model.Cards.TrainCards;

public class RailyardPos {
    private int pos;
    private Stack<TrainCards> trainCStack;
    private TrainCards.colors color;
    
    public RailyardPos(int pos,TrainCards.colors color){
        trainCStack = new Stack<>();
        this.color = color;
        this.pos = pos;
    }
    
    public void setStackCards(Stack<TrainCards> cards){
        this.trainCStack = cards;
    }
    
    public int getPosition(){
        return pos;
    }
    
    public Stack<TrainCards> getStack(){
        return trainCStack;
    }
    
    public TrainCards.colors getColor(){
        return color;
    }
}
