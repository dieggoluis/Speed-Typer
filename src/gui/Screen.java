package gui;
import users.*;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.String;
import java.util.*;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.applet.*;
import java.net.*;

public abstract class Screen implements ActionListener, Runnable, Component {
    public static String username;
    public static String password;
    protected static int bestScore;
    public static JPanel wrapper;
    public static JFrame frame;
    public static Login login;
    public static Display display;

    public static final String fontString = "Kongtext";
    public static final Color fontColor = new Color(0, 255, 255);
    public static final Color backgroundColor = new Color(32, 32, 32);
    public static final Font font = new Font(fontString, Font.BOLD, 16);

    protected static DataUsers dataUsers;
    private static final String SOUND_FILENAME = "../music/mario.wav";
    private static AudioInputStream musicInputStream;
    private Clip clip;

    public Screen() {
        bestScore = 0;
        try {
            dataUsers = new DataUsers();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    protected void print(Object arg) {
        System.out.println(arg);
    }

    //button listener
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == login.buttonLogin) {
            username = login.txtUsername.getText();
            password = login.txtPassword.getText();
            try {
                if (dataUsers.verifyCredentials(username, password)) {
                    bestScore = dataUsers.getHighScore(username);
                    System.out.println(bestScore);

                    //save in the database
                    try {
                        dataUsers.printDatabase();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                    //put song
                    try {
                        clip = AudioSystem.getClip();
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                        musicInputStream = AudioSystem.getAudioInputStream(new File(SOUND_FILENAME).getAbsoluteFile());
                        clip.open(musicInputStream);
                        clip.start();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }

                    //build next screen
                    wrapper.removeAll();
                    wrapper.add(display.createComponents());
                    wrapper.revalidate();
                    wrapper.repaint();


                } else {
                    login.txtUsername.setText("");
                    login.txtPassword.setText("");
                }
            } catch (NoSuchAlgorithmException ex) {
                System.out.println(ex.getMessage());
            } catch (InvalidKeySpecException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    protected JLabel setFont(String s, Font font) {
        JLabel label = new JLabel(s);
        label.setFont(font);
        label.setForeground(fontColor);
        label.setBackground(backgroundColor);
        label.setOpaque(true);
        return label;
    }

    protected GridBagConstraints createGbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;

        gbc.fill = (x == 0) ? GridBagConstraints.BOTH
            : GridBagConstraints.HORIZONTAL;

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = (x == 0) ? 0.1 : 1.0;
        gbc.weighty = 1.0;
        return gbc;
    }

    public void run () {
        //wrapper panel
        wrapper = new JPanel(new GridBagLayout());
        wrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        wrapper.setBackground(backgroundColor);
        
        //main frame
        frame = new JFrame("SpeedTyper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(wrapper);
        
        JPanel content = login.createComponents();
        frame.add(content);
        frame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height * 9 / 10;
        int width = screenSize.width * 3 / 4;
        frame.setSize(new Dimension(width, height));
        frame.setLocationRelativeTo(null); //center of screen
        frame.setVisible(true);
    }
}
