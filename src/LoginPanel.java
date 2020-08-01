import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LoginPanel extends JPanel {
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JTextPane communicat;

    public LoginPanel(){
        PasswdMap loginData = new PasswdMap();

        setFocusable(true);
        setBackground(Color.WHITE);
        setLayout(new GridBagLayout());

        KeyListener keyListener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if(keyEvent.getKeyCode() == KeyEvent.VK_ENTER) loginButton.doClick();
                if(keyEvent.getKeyCode() == KeyEvent.VK_DOWN) passwordField.grabFocus();
                if(keyEvent.getKeyCode() == KeyEvent.VK_UP) usernameField.grabFocus();
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        };

        usernameLabel = new JLabel("Nazwa Uzytkownika");
        passwordLabel = new JLabel("Haslo");
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Zaloguj");
        communicat= fixJTextPane();

        communicat.setText("Podana nazwa uzytkownika lub haslo sa nieprawidlowe.\nSprawdz poprawnosc swojej nazwy " +
                "uzytkownika oraz hasla i sprobuj ponownie.");
        StyledDocument doc = communicat.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center,false);
        communicat.setEditable(false);
        communicat.setFont(new Font(Font.SANS_SERIF, Font.BOLD,10));
        communicat.setVisible(false);
        communicat.setBorder(
                BorderFactory.createLineBorder(Color.RED, 2, true)
        );

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        gbc.gridy = 0;
        gbc.gridx = 0;
        usernameLabel.setPreferredSize(new Dimension(150, 25));
        add(usernameLabel, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 1;
        usernameField.setPreferredSize(new Dimension(225,25));
        usernameField.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK));
        add(usernameField, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        passwordLabel.setPreferredSize(new Dimension(150, 25));
        add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        passwordField.setPreferredSize(new Dimension(225, 25));
        passwordField.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        add(passwordField, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        Component comp1 = Box.createRigidArea(new Dimension(0, 20));
        add(comp1, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        communicat.setPreferredSize(new Dimension(300, 50));
        add(communicat, gbc);


        gbc.gridy = 5;
        gbc.gridx = 0;
        Component comp2 = Box.createRigidArea(new Dimension(0, 20));
        add(comp2, gbc);

        loginButton.addActionListener((e)->{
            char[] passwdTab = passwordField.getPassword();
            StringBuilder password = new StringBuilder();

            for(char ch : passwdTab){
                password.append(ch);
            }

            if(loginData.getObj().containsKey(usernameField.getText())){
                if(loginData.getObj().get(usernameField.getText()).equals(password.toString())){
                    this.getRootPane().getParent().setVisible(false);
                    new WarehouseFrame();
                }
                else communicat.setVisible(true);
            }
            else communicat.setVisible(true);
        });

        gbc.gridy = 6;
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        add(loginButton, gbc);

        usernameField.addKeyListener(keyListener);
        passwordField.addKeyListener(keyListener);
        addKeyListener(keyListener);

        usernameField.grabFocus();
    }

    private JTextPane fixJTextPane() {
        /* fix buga z ustawieniem koloru tla w komponencie przy zmienieniu UI na nimbus */
        //https://stackoverflow.com/questions/58618209/fixing-background-bug-color-of-jtextpane-when-nimbus-or-gtk-lookandfeeel-is-used

        JTextPane area = new JTextPane();

        Color bgColor = new Color(234, 234, 250);
        UIDefaults defaults = new UIDefaults();
        defaults.put("TextPane[Enabled].backgroundPainter", bgColor);
        area.putClientProperty("Nimbus.Overrides", defaults);
        area.putClientProperty("Nimbus.Overrides.InheritDefaults", true);
        area.setBackground(bgColor);

        return area;
    }

    public void setFocusOnStart(){
        usernameField.grabFocus();
    }

}
