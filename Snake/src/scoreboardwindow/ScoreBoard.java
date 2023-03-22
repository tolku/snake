package scoreboardwindow;

import entity.ScoreBoardEntity;
import player.Player;

import javax.swing.*;
import java.awt.*;

public class ScoreBoard {
    public ScoreBoard(JFrame frame, Player player){
        ScoreBoardEntity.savePlayer(player);
        JPanel jPanel = new JPanel();
        jPanel.setVisible(true);
        frame.add(jPanel);
        TextField textField = new TextField("text", 50);
        jPanel.add(textField);
        textField.setVisible(true);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setEditable(false);
        textField.setSize(300, 700);
    }


}
