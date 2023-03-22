package player;

import lombok.Builder;

@Builder
public class Player {

    private int score;
    private String name;



    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
