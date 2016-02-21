
package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.String;

public class Display extends JFrame implements Runnable {
    private JTextArea textArea;
    private JTextArea incorrectWords;
    private JButton button;
    private JLabel labelBestScore;
    private JLabel labelName;
    private JLabel labelTime;
    private JLabel labelYourScore;
    private JLabel labelTextArea;
    private JLabel labelIncorrectWords;
    private JTextField textField;

    private int score;
    private int bestScore;
    private int minutes;
    private int seconds;

    private String getTime () {
        return minutes + ":" + seconds;
    }
    public Component createComponents () {
        labelBestScore = new JLabel("Best score is " + bestScore);

        labelName = new JLabel("Name ");
        textField = new JTextField(20);

        button = new JButton("Submit");
        button.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                textArea.append(text);
            }
        });

        labelTime = new JLabel("Timeout in " + getTime());
        labelYourScore = new JLabel("Your score is " + score);

        labelTextArea = new JLabel("Typing area");
        textArea = new JTextArea();

        labelIncorrectWords = new JLabel("Incorrect words");
        incorrectWords = new JTextArea();

        //create panel with components
        JPanel pane = new JPanel(new GridLayout(0, 1));
        pane.add(labelBestScore);
        pane.add(labelName);
        pane.add(textField);
        pane.add(button);
        pane.add(labelTime);
        pane.add(labelYourScore);
        pane.add(textArea);
        pane.add(labelIncorrectWords);
        pane.add(incorrectWords);
        pane.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        return pane;
    }

    public void run () {
        Component components = createComponents();
        JFrame frame = new JFrame("OSX >> Arch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(components);
        frame.pack();
        frame.setVisible(true);
    }
}
