package gui;
import dict.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.String;
import javax.swing.Timer;
import java.text.SimpleDateFormat;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;
import javax.swing.text.BadLocationException;
import java.io.*;


public class Display extends Screen implements ActionListener, KeyListener {
    private final long duration = 90000; //1:30 min
    private final int timeRemaining = 5;
    private final Font fontName = new Font(fontString, Font.BOLD, 20);
    private final Font fontTitle = new Font(fontString, Font.BOLD, 22);
    private final Font fontTextArea = new Font(fontString, Font.PLAIN, 12);
    private final SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
    private final String stringStart = "Start";
    private final String stringQuit = "Quit";
    private final int shiftTime = 7;
    
    private JTextArea textArea;
    private JTextArea incorrectWords;
    private JButton button;
    private JLabel labelBestScore;
    private JLabel labelName;
    private JLabel labelTime;
    private JLabel labelYourScore;
    private JLabel labelIncorrectWords;
    private JLabel labelYourScoreNumber;
    private JLabel labelBestScoreNumber;
    private JLabel labelTitle;
    private Timer countdownTimer;

    private int score;
    private long currentDuration = duration;
    private Explorer exp;
    private final int DELETE = 127;
    private String incorrectWordsString;
    private boolean wasWrongWord;

    public Display() {
        //pointer used in the "mother" class Screen
        incorrectWordsString = "";
        wasWrongWord = false;
        display = this;
        try {
            exp = new Explorer();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        } catch (IOException ex) {
            System.out.println("IO problem");
        }
    }

    private String stringOfSpaces(int size) {
        String s = "";
        for (int i=0; i<size; i++) s += " ";
        return s;
    }

    public JPanel createComponents() {
        JPanel pane = new JPanel();
        pane.setLayout(new GridBagLayout());
        pane.setBackground(backgroundColor);

        pane.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(fontColor, 4),
                    BorderFactory.createEmptyBorder(20, 40, 20, 40)));

        GridBagConstraints gbc;
        // generate title
        gbc = createGbc(1, 0);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets.set(5, 5, 40, 5);
        labelTitle = setFont("SpeedTyper", fontTitle);
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        pane.add(labelTitle, gbc);

        // generate name
        gbc = createGbc(0, 1);
        gbc.insets.set(5, 5, 20, 5);
        labelName = setFont(username, fontName);
        pane.add(labelName, gbc);

        // generate timer
        gbc = createGbc(2, 1);
        countdownTimer = new Timer(1000, this);
        countdownTimer.setInitialDelay(0);
        labelTime = setFont(stringOfSpaces(shiftTime) + timeFormat.format(duration), fontName);
        pane.add(labelTime, gbc);

        // generate Score labels
        gbc = createGbc(1, 2);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        labelYourScore = setFont("Score", font);
        labelYourScore.setHorizontalAlignment(SwingConstants.CENTER);
        pane.add(labelYourScore, gbc);

        //gbcScore.gridy++;
        gbc = createGbc(1, 3);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        labelYourScoreNumber = setFont(Integer.toString(score), font);
        labelYourScoreNumber.setHorizontalAlignment(SwingConstants.CENTER);
        pane.add(labelYourScoreNumber, gbc);

        gbc = createGbc(1, 4);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        labelBestScore = setFont("Best Score", font);
        labelBestScore.setHorizontalAlignment(SwingConstants.CENTER);
        pane.add(labelBestScore, gbc);

        gbc = createGbc(1, 5);
        gbc.insets.set(5, 5, 20, 5);
        labelBestScoreNumber = setFont(Integer.toString(bestScore), font);
        labelBestScoreNumber.setHorizontalAlignment(SwingConstants.CENTER);
        pane.add(labelBestScoreNumber, gbc);

        // text Area
        gbc = createGbc(0, 6);
        gbc.insets.set(5, 5, 20, 5);
        gbc.gridwidth = 3;
        textArea = new JTextArea(9, 10);
        textArea.setLineWrap(true);        
        textArea.addKeyListener(this);
        textArea.setEditable(false);
        textArea.setFont(fontTextArea);
        textArea.setCaretColor(Color.white);
        textArea.setForeground(Color.white);
        textArea.setBackground(backgroundColor);
        textArea.setMargin(new Insets(5,5,5,5));
        textArea.setTransferHandler(null);

        JScrollPane scrollText = new JScrollPane(textArea);
        scrollText.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(fontColor, 2),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        scrollText.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollText.setBackground(backgroundColor);

        pane.add(scrollText, gbc);
        
        // incorrec words
        gbc = createGbc(0, 7);
        labelIncorrectWords = setFont("Incorrect words", font);
        labelIncorrectWords.setHorizontalAlignment(SwingConstants.LEFT);
        pane.add(labelIncorrectWords, gbc);

        gbc = createGbc(0, 8);
        gbc.insets.set(5, 5, 10, 5);
        gbc.gridwidth = 3;
        incorrectWords = new JTextArea(3, 10);
        incorrectWords.setEditable(false);
        incorrectWords.setLineWrap(true);        
        incorrectWords.setFont(fontTextArea);
        incorrectWords.setForeground(Color.white);
        incorrectWords.setBackground(backgroundColor);
        
        JScrollPane scrollInc = new JScrollPane(incorrectWords);
        scrollInc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollInc.setBackground(backgroundColor);
        scrollInc.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(fontColor, 2),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        pane.add(scrollInc, gbc);

        //Button start
        gbc = createGbc(1, 9);
        gbc.insets.set(10, 5, 5, 5);
        gbc.ipady = 10;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        button = new JButton(stringStart);
        button.setFont(font);
        button.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(fontColor, 2),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        button.setForeground(fontColor);
        button.setBackground(backgroundColor);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.addActionListener(this);
        pane.add(button, gbc);

        return pane;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == countdownTimer) {
            currentDuration -= 1000;
            labelTime.setText(stringOfSpaces(shiftTime) + timeFormat.format(currentDuration));
            if(currentDuration <= 0){
                countdownTimer.stop();
                if (score > bestScore) {
                    bestScore = score;
                    dataUsers.setHighScore(username, bestScore);
                    try {
                        dataUsers.printDatabase();
                    } catch (IOException ex) {
                        System.out.println("IO problem");
                    }
                    labelBestScoreNumber.setText(Integer.toString(bestScore));
                }
                textArea.setText("");
                textArea.setEditable(false);
                incorrectWords.setText("");
                labelYourScoreNumber.setText("0");
                currentDuration = duration;
                button.setText(stringStart);
            }
        }

        if (e.getSource() == button) {
            if (button.getText() == stringStart) {
                if (!countdownTimer.isRunning()) {
                    score = 0;
                    button.setText(stringQuit);
                    countdownTimer.start();
                    textArea.setEditable(true);
                    textArea.requestFocusInWindow();
                    incorrectWordsString = "";
                }
                    
            } else {
                if (score > bestScore) {
                    bestScore = score;
                    dataUsers.setHighScore(username, bestScore);
                    try {
                        dataUsers.printDatabase();
                    } catch (IOException ex) {
                        System.out.println("IO problem");
                    }
                    labelBestScoreNumber.setText(Integer.toString(bestScore));
                }
                exp.explore(' '); //reset explore
                textArea.setText("");
                incorrectWords.setText("");
                labelYourScoreNumber.setText("0");
                button.setText(stringStart);
                countdownTimer.stop();
                textArea.setEditable(false);
                currentDuration = duration;
                labelTime.setText(stringOfSpaces(shiftTime) + timeFormat.format(currentDuration));
            }
        }
    }

    // Handle the key typed event from the text field
    public void keyTyped(KeyEvent e) {
        if (countdownTimer.isRunning()) {
            int id = e.getID();
            if (id == KeyEvent.KEY_TYPED) {
                char c = e.getKeyChar();
                if (Character.isLetter(c)) c = Character.toLowerCase(c);
                int currentScore = exp.explore(c);
                if (currentScore != Integer.MAX_VALUE) {
                    score += currentScore;
                    labelYourScoreNumber.setText(Integer.toString(score));
                    textArea.getHighlighter().removeAllHighlights();
                    wasWrongWord = false;
                }
            } 
        }
    }
     
    public void keyPressed(KeyEvent e) {
        if (countdownTimer.isRunning()) {
            char c = e.getKeyChar();
            if ((c == ' ' || c == '\n') && !exp.isPossibleWord() ) {
                incorrectWordsString += exp.getWord() + " ";
                incorrectWords.setText(incorrectWordsString);
            }
        }
    }

    public void keyReleased(KeyEvent e) { 
        if (countdownTimer.isRunning()) {
            //print(textArea.getText());
            if (!exp.isPossibleWord()) {
                Highlighter highlighter = textArea.getHighlighter();
                HighlightPainter painter = new DefaultHighlighter.DefaultHighlightPainter(Color.red);
                int beginOfCurrentWord = textArea.getText().toLowerCase().lastIndexOf(exp.getWord());
                int endCurrentWord = beginOfCurrentWord + exp.getWord().length();

                //debug
                print(exp.getWord());
                print(textArea.getText());
                print((int)e.getKeyChar());
                print(beginOfCurrentWord);
                print(endCurrentWord);

                wasWrongWord = true;
                try {
                    highlighter.addHighlight(beginOfCurrentWord, endCurrentWord, painter);
                } catch (BadLocationException ex) {
                    System.out.println("bad location");
                }
            } else if(wasWrongWord){
                textArea.getHighlighter().removeAllHighlights();
                wasWrongWord = false;
            }
        }
    }
}
