package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.String;

public class Login implements ActionListener, Runnable {
    private JButton buttonLogin;
    private JPanel mainPanel;
    private JFrame frame;

    public void run () {
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel content = new JPanel(new GridBagLayout());
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        frame.setContentPane(content);
        mainPanel = createComponents();
        frame.add(mainPanel);
        frame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height * 5 / 6;
        int width = screenSize.width * 1 / 3;
        frame.setSize(new Dimension(width, height));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    public JPanel createComponents() {
        JPanel pane = new JPanel();
        pane.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        pane.add(new JLabel("Username:"), gbc);
        gbc.gridy++;
        pane.add(new JLabel("Password:"), gbc);

        gbc.gridx++;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        pane.add(new JTextField(20), gbc);
        gbc.gridy++;
        pane.add(new JTextField(20), gbc);

        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx++;
        buttonLogin = new JButton("Login");
        buttonLogin.addActionListener(this);
        pane.add(buttonLogin, gbc);

        return pane;
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonLogin) {
            //frame.setVisible(false);
            //frame.dispose();
            
            mainPanel.removeAll();
            mainPanel.setLayout(new GridLayout(0, 1));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            mainPanel.add(new Display().createComponents());
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }
}

