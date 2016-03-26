package gui;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.String;
import javax.crypto.*;

public class Login extends Screen {
    public JButton buttonLogin;
    public JTextField txtUsername;
    public JTextField txtPassword;
    private final Font fontTexField = new Font(fontString, Font.PLAIN, 12);

    public Login() {
        //pointer used in the "mother" class Screen
        login = this;
    }
    
    public JPanel createComponents() {
        JPanel pane = new JPanel();
        pane.setLayout(new GridBagLayout());
        pane.setBackground(backgroundColor);

        GridBagConstraints gbc = new GridBagConstraints();

        //all components

        //Label Username 
        gbc = createGbc(0, 0);
        JLabel labelUsername = new JLabel("Username");
        labelUsername.setFont(font);
        labelUsername.setForeground(fontColor);
        labelUsername.setHorizontalAlignment(SwingConstants.CENTER);
        pane.add(labelUsername, gbc);

        //Label Password
        gbc = createGbc(0, 2);
        JLabel labelPassword = new JLabel("Password");
        labelPassword.setFont(font);
        labelPassword.setForeground(fontColor);
        labelPassword.setHorizontalAlignment(SwingConstants.CENTER);
        pane.add(labelPassword, gbc);

        //Text Field username
        gbc = createGbc(0, 1);
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.ipady = 10;
        txtUsername = new JTextField(20);
        txtUsername.setHorizontalAlignment(JTextField.CENTER);
        txtUsername.setFont(fontTexField);
        txtUsername.setCaretColor(Color.white);
        txtUsername.setDocument(new JTextFieldLimit(10));
        txtUsername.setForeground(Color.white);
        txtUsername.setBackground(backgroundColor);
        txtUsername.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(fontColor, 2),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        pane.add(txtUsername, gbc);

        //Password field password
        gbc = createGbc(0, 3);
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.ipady = 10;
        txtPassword = new JPasswordField(20);
        txtPassword.setHorizontalAlignment(JTextField.CENTER);
        txtPassword.setCaretColor(Color.white);
        txtPassword.setForeground(Color.white);
        txtPassword.setBackground(backgroundColor);
        txtPassword.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(fontColor, 2),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        txtPassword.setTransferHandler(null);
        pane.add(txtPassword, gbc);

        //Button Login
        gbc = createGbc(0, 4);
        gbc.weightx = 0;
        gbc.ipady = 7;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        buttonLogin = new JButton("Login");
        buttonLogin.setHorizontalAlignment(SwingConstants.CENTER);
        buttonLogin.setFont(font);
        buttonLogin.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(fontColor, 2),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        buttonLogin.setForeground(fontColor);
        buttonLogin.setBackground(backgroundColor);
        buttonLogin.addActionListener(this);
        pane.add(buttonLogin, gbc);

        return pane;
    }
}

