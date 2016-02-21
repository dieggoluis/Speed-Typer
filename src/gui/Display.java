package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.String;

public class Display extends JFrame {
    private JTextArea textArea;
    private JTextArea incorrectWords;
    private JButton button;
    private JLabel labelBestScore;
    private JLabel labelBestScoreNumber;
    private JLabel labelName;
    private JLabel labelTime;
    private JLabel labelYourScore;
    private JLabel labelTextArea;
    private JLabel labelIncorrectWords;
    private JTextField textField;
    private JLabel labelTimeNumber;
    private JLabel labelYourScoreNumber;
    private final JLabel labelEmpty = new JLabel("");

    private int score;
    private int bestScore;
    private int minutes;
    private int seconds;

    private final int sizeName = 50;
    private String buttonString;

    private String getTime() {
        return minutes + ":" + seconds;
    }

    public JPanel createComponents() {

        // generate Best Score "row"
        labelBestScore = new JLabel("Best score is ");
        labelBestScoreNumber = new JLabel(Integer.toString(bestScore));
        JPanel paneBestScore = new JPanel(new GridLayout(1, 2, 1, 1));
        paneBestScore.add(labelBestScore);
        paneBestScore.add(labelBestScoreNumber);

        // generate Login 
        labelName = new JLabel("Name ");
        textField = new JTextField(sizeName);
        JPanel paneName = new JPanel(new GridLayout(2, 2, 1, 1));
        paneName.add(labelName);
        paneName.add(textField);

        button = new JButton(buttonString);
        button.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                textArea.append(text);
            }
        });
        paneName.add(labelEmpty);
        paneName.add(button);

        labelTime = new JLabel("Timeout in");
        labelTimeNumber = new JLabel(getTime());
        JPanel paneTimeScore = new JPanel(new GridLayout(2, 2, 1, 1));
        paneTimeScore.add(labelTime);
        paneTimeScore.add(labelTimeNumber);

        labelYourScore = new JLabel("Your score is ");
        labelYourScoreNumber = new JLabel(Integer.toString(score));
        paneTimeScore.add(labelYourScore);
        paneTimeScore.add(labelYourScoreNumber);

        textArea = new JTextArea();
        JPanel paneTextArea = new JPanel(new GridLayout(1, 1));
        paneTextArea.add(textArea);

        labelIncorrectWords = new JLabel("Incorrect words");
        incorrectWords = new JTextArea();
        incorrectWords.setEditable(false);
        JPanel paneIncorrectWords = new JPanel(new GridLayout(2, 2, 1, 1));
        paneIncorrectWords.add(labelIncorrectWords);
        paneIncorrectWords.add(incorrectWords);

        JPanel wrapper = new JPanel(new GridLayout(0, 1));
        wrapper.add(paneBestScore);
        wrapper.add(paneName);
        wrapper.add(paneTimeScore);
        wrapper.add(paneTextArea);
        wrapper.add(paneIncorrectWords);
        wrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        return wrapper;
    }

    public void run () {
        buttonString = "Start";
        JPanel components = createComponents();
        JFrame frame = new JFrame("OSX >> Arch");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height * 5 / 6;
        int width = screenSize.width * 1 / 3;
        frame.setPreferredSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(components);
        frame.pack();
        frame.setVisible(true);
    }
}
