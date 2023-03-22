package entity;

import org.hibernate.boot.archive.scan.spi.Scanner;
import player.Player;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SCORE_BOARD", schema = "bazawonsz", catalog = "")
public class ScoreBoardEntity {
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "score")
    private String score;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreBoardEntity that = (ScoreBoardEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(score, that.score);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, score, id);
    }

    public static void savePlayer(Player player) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("default").createEntityManager();
        try {
            entityManager.getTransaction().begin();
            ScoreBoardEntity scoreBoardEntity = new ScoreBoardEntity();
            scoreBoardEntity.setName(player.getName());
            scoreBoardEntity.setScore(String.valueOf(player.getScore()));
            entityManager.persist(scoreBoardEntity);
            entityManager.getTransaction().commit();
        }catch (Exception exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            exception.printStackTrace();
        }
    }

}
