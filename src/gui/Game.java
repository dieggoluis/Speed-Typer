package gui;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.String;

public class Game {
    private Screen login;
    private Screen display;

    public Game() {
        //init pointer for each screen
        login = new Login();
        display = new Display();
    }

    public void init() {
        Thread t = new Thread(login);
        t.start();
    }
}
