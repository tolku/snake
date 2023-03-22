package gamewindow;

import java.awt.*;

public class Snake implements IObserver {
    private int snakeLength;
    private char direction = 'D';
    private int[] snakeX = new int[GamePanel.getGridAmount()];
    private int[] snakeY = new int[GamePanel.getGridAmount()];
    private static Snake snake = new Snake(2, 150, 150);

    private Snake(int snakeLength, int snakeX, int snakeY) {
        this.snakeLength = snakeLength;
        this.snakeX[0] = snakeX;
        this.snakeY[0] = snakeY;
    }

    public static Snake getSnakeInstance(){
        return snake;
    }

    public int getSnakeLength() {
        return snakeLength;
    }

    public void setSnakeLength(int snakeLength) {
        this.snakeLength = snakeLength;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public int getSnakeX() {
        return snakeX[0];
    }

    public int getCertainSnakeX(int whichOne) {
        return snakeX[whichOne];
    }

    public int getSnakeY() {
        return snakeY[0];
    }

    public int getCertainSnakeY(int whichOne) {
        return snakeY[whichOne];
    }

    public void draw(Graphics graphics) {
        for (int counter = 0; counter < snakeLength; counter++) {
            graphics.setColor(Color.green);
            graphics.fillRect(snakeX[counter], snakeY[counter], 
                    GamePanel.getGridSize(), GamePanel.getGridSize());
        }
    }
    
    public void move() {
        for (int counter = snakeLength; counter > 0; counter--) {
            snakeX[counter] = snakeX[counter - 1];
            snakeY[counter] = snakeY[counter - 1];
        }
        switch (direction) {
            case 'U':
                snakeY[0] = snakeY[0] - GamePanel.getGridSize();
                break;
            case 'D':
                snakeY[0] = snakeY[0] + GamePanel.getGridSize();
                break;
            case 'L':
                snakeX[0] = snakeX[0] - GamePanel.getGridSize();
                break;
            case 'R':
                snakeX[0] = snakeX[0] + GamePanel.getGridSize();
                break;
        }
    }

    @Override
    public void performAction() {
        this.setSnakeLength(this.getSnakeLength() + 1);
    }
}