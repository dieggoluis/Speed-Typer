package gui;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.String;

public class Login extends Screen {
    public JButton buttonLogin;
    public JTextField txtUsername;
    public JTextField txtPassword;

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
        pane.add(labelUsername, gbc);

        //Label Password
        gbc = createGbc(0, 1);
        JLabel labelPassword = new JLabel("Password");
        labelPassword.setFont(font);
        labelPassword.setForeground(fontColor);
        pane.add(labelPassword, gbc);

        //Text Field username
        gbc = createGbc(1, 0);
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.ipady = 10;
        txtUsername = new JTextField(20);
        pane.add(txtUsername, gbc);

        //Password field password
        gbc = createGbc(1, 1);
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.ipady = 10;
        txtPassword = new JPasswordField(20);
        pane.add(txtPassword, gbc);

        //Button Login
        gbc = createGbc(2, 2);
        gbc.weightx = 0;
        gbc.ipady = 5;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        buttonLogin = new JButton("Login");
        buttonLogin.setHorizontalAlignment(SwingConstants.CENTER);
        buttonLogin.setFont(font);
        buttonLogin.addActionListener(this);
        pane.add(buttonLogin, gbc);

        return pane;
    }
}

