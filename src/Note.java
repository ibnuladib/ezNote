import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Note {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.setOpacity(0.50f);
        frame.setSize(200, 100);

        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(screenDimension.width - frame.getWidth(), screenDimension.height - frame.getHeight() - 40);

        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("Monospaced", Font.BOLD, 12));
        textArea.setBackground(Color.BLACK);


        JPanel panel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        panel.add(scrollPane, BorderLayout.CENTER);
        //panel.add(clearButton, BorderLayout.SOUTH);

        frame.add(panel);

        frame.addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent windowEvent) {
                if ((windowEvent.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED) {
                    frame.setState(Frame.NORMAL);
                }
            }
        });

        if (SystemTray.isSupported()) {
            SystemTray tray = SystemTray.getSystemTray();
            // Use the correct path to the icon image
            Image trayImage = Toolkit.getDefaultToolkit().getImage("C:/Users/adib/Documents/Notes/src/icon.png");

            PopupMenu popup = new PopupMenu();
            MenuItem restoreItem = new MenuItem("Restore");
            MenuItem exitItem = new MenuItem("Exit");

            restoreItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.setVisible(true);
                    frame.setState(Frame.NORMAL);
                }
            });

            exitItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            popup.add(restoreItem);
            popup.add(exitItem);

            TrayIcon trayIcon = new TrayIcon(trayImage, "Note App", popup);
            trayIcon.setImageAutoSize(true);

            trayIcon.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        frame.setVisible(true);
                        frame.setState(Frame.NORMAL);
                    }
                }
            });

            try {
                tray.add(trayIcon);
            } catch (AWTException ex) {
                ex.printStackTrace();
            }

            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    frame.setVisible(false);
                }
            });
        }

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
}
