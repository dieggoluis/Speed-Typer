package gui;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.String;

public abstract class Screen implements ActionListener, Runnable, Component {
    public static String username;
    public static String password;
    public static JPanel wrapper;
    public static JFrame frame;
    public static Login login;
    public static Display display;

    public static final String fontString = "Kongtext";
    public static final Color fontColor = new Color(0, 255, 255);
    public static final Color backgroundColor = new Color(32, 32, 32);
    public static final Font font = new Font(fontString, Font.BOLD, 16);

    //button listener
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == login.buttonLogin) {
            // Autenticar aqui
            // if autenticar
            String username = login.txtUsername.getText();
            display.setUserName(username); 
            wrapper.removeAll();
            wrapper.add(display.createComponents());
            wrapper.revalidate();
            wrapper.repaint();
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
        int height = screenSize.height * 6 / 6;
        int width = screenSize.width * 3 / 3;
        frame.setSize(new Dimension(width, height));
        frame.setLocationRelativeTo(null); //center of screen
        frame.setVisible(true);
    }
}
