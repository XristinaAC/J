package View;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

class SaveGameHandler extends AbstractAction{

        @Override
        public void actionPerformed(ActionEvent e) {
            Frame frame=new Frame();
            FileDialog dialog=new FileDialog(frame,"Save",FileDialog.SAVE);
            dialog.setVisible(true);
            
            String path=dialog.getDirectory();
            File f = new File(path);
            try {
                f.createNewFile();
               
            } catch (IOException ex) {
                Logger.getLogger(SaveGameHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            JOptionPane.showMessageDialog(null, "File Created");  
         }
    }
