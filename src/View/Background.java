package View;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JLayeredPane;

 /**
 * This class represents the initialization of the background image of the board
 * @version 1.0
 * @author Christina Papadodimitraki
 */
public class Background extends JLayeredPane {
    Image image;

        public Background(Image img) {
            image=img;
            
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, 1400, 1030, this);
        }
    
}