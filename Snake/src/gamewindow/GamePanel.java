package gamewindow;

import player.Player;
import scoreboardwindow.ScoreBoard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {

    private static final int GAME_PANEL_WIDTH = 400;
    private static final int GAME_PANEL_HEIGHT = 400;
    private static final int GRID_SIZE = 25;
    private static final int GAME_DELAY = 185;
    private static final int GRID_AMOUNT = (GAME_PANEL_WIDTH * GAME_PANEL_HEIGHT) / GRID_SIZE;

    private JFrame frame;
    private Timer timer;
    private Apple apple = new Apple();
    private Snake snake = Snake.getSnakeInstance();
    private List<IObserver> observers = new ArrayList<>();
    private Player player;

    private boolean isWorking = false;

    public static int getGamePanelHeight(){
        return GAME_PANEL_HEIGHT;
    }

    public static int getGamePanelWidth(){
        return GAME_PANEL_WIDTH;
    }

    public static int getGridSize(){
        return GRID_SIZE;
    }

    public static int getGridAmount(){
        return GRID_AMOUNT;
    }

    public GamePanel(JFrame frame, Player player) {
        this.player = player;
        this.frame = frame;
        this.setVisible(true);
        this.setFocusable(true);
        this.addKeyListener(new myKeyAdapter());
        this.setPreferredSize(new Dimension(GAME_PANEL_WIDTH, GAME_PANEL_HEIGHT));
        this.setBackground(Color.darkGray);
        start();
    }

    public void start() {
        observers.add(snake);
        observers.add(apple);
        isWorking = true;
        apple.setAppleX(new Random().nextInt((GAME_PANEL_WIDTH / GRID_SIZE)) * GRID_SIZE);
        apple.setAppleY(new Random().nextInt((GAME_PANEL_HEIGHT / GRID_SIZE)) * GRID_SIZE);
        timer = new Timer(GAME_DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }

    public void draw(Graphics graphics) {
        if (isWorking) {
            for (int counter = 0; counter < GAME_PANEL_HEIGHT / GRID_SIZE; counter++) {
                graphics.drawLine(counter * GRID_SIZE, 0, counter * GRID_SIZE, GAME_PANEL_HEIGHT);
                graphics.drawLine(0, counter * GRID_SIZE, GAME_PANEL_WIDTH, counter * GRID_SIZE);
            }
            snake.draw(graphics);
            apple.draw(graphics);
        }
    }

    public void setIsWorkingIfCollision() {
        for (int counter = snake.getSnakeLength(); counter > 0; counter--) {
            if ((snake.getSnakeX() == snake.getCertainSnakeX(counter)) && (snake.getSnakeY() == snake.getCertainSnakeY(counter))) {
                isWorking = false;
            }
        }
        if (snake.getSnakeX() < 0) {
            isWorking = false;
        }
        if (snake.getSnakeX() > GAME_PANEL_WIDTH - GRID_SIZE) {
            isWorking = false;
        }
        if (snake.getSnakeY() < 0) {
            isWorking = false;
        }
        if (snake.getSnakeY() > GAME_PANEL_HEIGHT - GRID_SIZE) {
            isWorking = false;
        }
        if (!isWorking) {
            timer.stop();
        }
        if (!isWorking){
            player.setScore(snake.getSnakeLength());
            new ScoreBoard(frame, player);
        }


    }

    public class myKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (snake.getDirection() != 'R') {
                        snake.setDirection('L');
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (snake.getDirection() != 'L') {
                        snake.setDirection('R');
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (snake.getDirection() != 'D') {
                        snake.setDirection('U');
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (snake.getDirection() != 'U') {
                        snake.setDirection('D');
                    }
                    break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isWorking) {
            snake.move();
            checkIfAppleEaten();
            setIsWorkingIfCollision();
        }
        repaint();
    }

    public void addObserver(IObserver observer){
        observers.add(observer);
    }

    public void removeObserver(IObserver observer){
        for (IObserver iObserver : observers){
            if (observer == iObserver){
                observers.remove(observer);
            }
        }
    }

    private void checkIfAppleEaten(){
        if (snake.getSnakeX() == apple.getAppleX() && snake.getSnakeY() == apple.getAppleY()){
            for (IObserver iObserver : observers){
                iObserver.performAction();
            }
        }
    }
}