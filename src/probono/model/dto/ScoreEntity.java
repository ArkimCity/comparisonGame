package comparisonGame.model.dto;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Builder

@Entity(name = "score")
@SequenceGenerator(
        name = "score_scoreId_gen",
        sequenceName = "score_scoreId_seq",
        initialValue = 1, allocationSize = 1
)
public class ScoreEntity implements Serializable {
    @Id
    @GeneratedValue(generator = "score_scoreId_gen")
    private int scoreId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UsersEntity userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    private CategoryEntity category;

    @Column
    private int score;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ScoreEntity{");
        sb.append("scoreId='").append(scoreId).append('\'');
        sb.append(", userId=").append(userId);
        sb.append(", category=").append(category);
        sb.append(", score='").append(score).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
