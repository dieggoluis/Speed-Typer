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
        content.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
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
        gbc.ipady = 10;
        pane.add(new JTextField(20), gbc);
        gbc.ipady = 2;
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
        //    frame.setVisible(false);
        //    Thread t = new Thread(new Display());
        //    t.start();
        //    frame.dispose();
           
            Container contentPane = frame.getContentPane();
            contentPane.setLayout(new GridLayout(0, 1));
            
            mainPanel.removeAll();
            mainPanel.add(new Display().createComponents());
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }
}

