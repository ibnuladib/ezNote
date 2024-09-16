import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;




public class Note {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        // Set frame properties
        frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.setOpacity(0.50f);
        frame.setSize(200, 100);

        Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(screenDimension.width - frame.getWidth(), screenDimension.height - frame.getHeight() - 40);

        JTextArea textArea = new JTextArea();

        textArea.setLineWrap(true);
        //textArea.setWrapStyleWord(true);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("Monospaced", Font.BOLD, 14));


        textArea.setBackground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        //    loadText(textArea);

        frame.addWindowStateListener(new WindowStateListener() {
            public void windowStateChanged(WindowEvent windowEvent) {
                if ((windowEvent.getNewState() & Frame.ICONIFIED) == Frame.ICONIFIED) {
                    frame.setState(Frame.NORMAL);
                }
            }
        });



            frame.add(scrollPane);


            frame.setVisible(true);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

