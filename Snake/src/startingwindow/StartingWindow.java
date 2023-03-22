package startingwindow;

import gamewindow.GamePanel;
import player.Player;

import javax.swing.*;

public class StartingWindow extends JFrame {

    private final String FRAME_NAME = "Snake";
    private final String GET_PLAYER_NAME_TEXT = "Enter your name:";
    private final String SAMPLE_PLAYER = "Player1";
    private final String START_GAME = "Start game";

    public StartingWindow(){
        JFrame frame = creteAndInitializeJFrame(FRAME_NAME);

        JPanel panel = new JPanel();
        frame.add(panel);

        JLabel label = new JLabel(GET_PLAYER_NAME_TEXT);
        panel.add(label);

        JTextField textField = new JTextField(SAMPLE_PLAYER, 10);
        panel.add(textField);

        JButton button = new JButton(START_GAME);
        button.addActionListener(e -> {
            JPanel gamePanel = new GamePanel(frame, Player.builder()
                    .name(textField.getText())
                    .build());
            frame.add(gamePanel);
            frame.pack();
            gamePanel.setEnabled(true);
            gamePanel.setVisible(true);
            gamePanel.requestFocus();
        });
        panel.add(button);
        button.removeAll();
        frame.pack();
        frame.repaint();
    }

    public JFrame creteAndInitializeJFrame(String name){
        JFrame frame = new JFrame(name);
        frame.setVisible(true);
        frame.setSize(400, 100);
        frame.setLocationRelativeTo(null);
        return frame;
    }

}
