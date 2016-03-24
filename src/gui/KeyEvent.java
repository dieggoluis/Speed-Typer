package gui;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyMonitor implements KeyListener {

    // Handle the key typed event from the text field
    public void keyTyped(KeyEvent e) {
        processCharacter(e);
    }
     
    public void keyPressed(KeyEvent e) {
     //   displayInfo(e, "KEY PRESSED: ");
    }
     
    public void keyReleased(KeyEvent e) {
       // displayInfo(e, "KEY RELEASED: ");
    }

    private void processCharacter(KeyEvent e) {
        int id = e.getID();
        String keyString;
        if (id == KeyEvent.KEY_TYPED) {
            char c = e.getKeyChar();
            keyString = "key character = '" + c + "'";
        } 
        int keyCode = e.getKeyCode();
        keyString = "key code = " + keyCode
            + " ("
            + KeyEvent.getKeyText(keyCode)
            + ")";
    }
}
