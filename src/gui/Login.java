package gui;
import java.awt.Color;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.String;

public class Login implements ActionListener, Runnable {
    private JButton buttonLogin;
    private JTextField username;
    private JPasswordField password;
    private JPanel wrapper;
    private JFrame frame;

    private final Color backgroundColor = new Color(173, 234, 234);

    public void run () {
        //wrapper panel
        wrapper = new JPanel(new GridBagLayout());
        wrapper.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        wrapper.setBackground(backgroundColor);
        
        //main frame
        frame = new JFrame("Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(wrapper);
        
        JPanel content = createComponents();
        frame.add(content);
        frame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height * 5 / 6;
        int width = screenSize.width * 1 / 3;
        frame.setSize(new Dimension(width, height));
        frame.setLocationRelativeTo(null); //center of screen
        frame.setVisible(true);
    }
    
    public JPanel createComponents() {
        JPanel pane = new JPanel();
        pane.setLayout(new GridBagLayout());
        pane.setBackground(backgroundColor);

        GridBagConstraints gbc = new GridBagConstraints();

        //all components
        gbc.ipadx = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,0,0,0);

        //Label Username 
        gbc.gridx = 0;
        gbc.gridy = 0;
        pane.add(new JLabel("Username"), gbc);

        //Label Password
        gbc.gridy++;
        pane.add(new JLabel("Password"), gbc);

        //Text Field username
        gbc.gridx++;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.ipady = 8;
        username = new JTextField(15);
        pane.add(username, gbc);

        //Password field password
        gbc.gridy++;
        password = new JPasswordField(15);
        pane.add(password, gbc);

        //Button Login
        gbc.gridx = 1;
        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.ipady = 5;
        gbc.gridx++;
        gbc.fill = GridBagConstraints.NONE;
        buttonLogin = new JButton("Login");
        buttonLogin.addActionListener(this);
        pane.add(buttonLogin, gbc);

        return pane;
    }

    //button listener
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonLogin) {
            wrapper.removeAll();
            wrapper.add(new Display().createComponents());
            wrapper.revalidate();
            wrapper.repaint();
        }
    }
}

