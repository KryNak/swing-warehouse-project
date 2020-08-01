import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class LoginFrame extends JFrame {
    LoginPanel logInPanel;
    final int WIDTH = 500;
    final int HEIGHT = 250;

    public LoginFrame() {

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        setIconImage(
                new ImageIcon(getClass().getResource("icon/mainicon.png")).getImage()
        );
        setLocation(dim.width / 2 - WIDTH / 2, dim.height / 2 - HEIGHT / 2);
        setResizable(false);
        setVisible(true);
        setTitle("Magazyn");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);

        logInPanel = new LoginPanel();
        getContentPane().add(logInPanel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                logInPanel.setFocusOnStart();
            }
        });
    }
}


