package gui;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyMonitor implements KeyListener {

    // Handle the key typed event from the text field
    public void keyTyped(KeyEvent e) {
        processCharacter(e);
    }
     
    public void keyPressed(KeyEvent e) { }
     
    public void keyReleased(KeyEvent e) { }

    private void processCharacter(KeyEvent e) {
        int id = e.getID();
        if (id == KeyEvent.KEY_TYPED) {
            char c = e.getKeyChar();
            System.out.println((int)c);
        } 
    }
}
