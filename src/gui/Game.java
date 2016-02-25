package gui;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.String;

public abstract class Game {
    protected String username;
    protected String password;
    protected JPanel wrapper;
    protected JFrame frame;

    protected static final String fontString = "Kongtext";
    protected static final Color fontColor = new Color(0, 255, 255);
    protected static final Color backgroundColor = new Color(32, 32, 32);
    protected static final Font font = new Font(fontString, Font.BOLD, 16);

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
}
