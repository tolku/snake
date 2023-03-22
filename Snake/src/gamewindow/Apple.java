package gamewindow;

import java.awt.*;
import java.util.Random;

public class Apple implements IObserver {
    private int appleX;
    private int appleY;

    public int getAppleX() {
        return appleX;
    }

    public int getAppleY() {
        return appleY;
    }

    public void setAppleX(int appleX) {
        this.appleX = appleX;
    }

    public void setAppleY(int appleY) {
        this.appleY = appleY;
    }

    public void draw(Graphics graphics) {
            graphics.setColor(Color.red);
            graphics.fillOval(appleX, appleY, GamePanel.getGridSize(), GamePanel.getGridSize());
    }

    @Override
    public void performAction() {
        this.setAppleX(new Random().nextInt((GamePanel.getGamePanelWidth() / GamePanel.getGridSize())) * GamePanel.getGridSize());
        this.setAppleY(new Random().nextInt((GamePanel.getGamePanelHeight() / GamePanel.getGridSize())) * GamePanel.getGridSize());
    }
}
