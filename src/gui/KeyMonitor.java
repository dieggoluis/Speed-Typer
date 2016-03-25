package gui;
import dict.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.*;


public class KeyMonitor implements KeyListener {
    private int currentScore;
    private Explorer exp;

    public KeyMonitor() {
        currentScore = 0;
        try {
            exp = new Explorer();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println("IO problem");
        }
    }

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
            currentScore = exp.explore(c);
            //getCurrentScore();
        } 
    }

    public int getCurrentScore() {
        //debug
        System.out.println(currentScore);
        return currentScore;
    }
}
