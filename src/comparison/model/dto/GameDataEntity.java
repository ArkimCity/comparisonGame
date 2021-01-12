package comparison.model.dto;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder
@Entity(name = "gameData")
@SequenceGenerator(
        name = "gameData_gameDataId_gen",
        sequenceName = "gameData_gameDataId_seq",
        initialValue = 1, allocationSize = 1
)
public class GameDataEntity {
    @Id
    @GeneratedValue(generator = "gameData_gameDataId_gen")
    private int dataId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    private CategoryEntity category;

    @Column
    private String title;

    @Column
    private String imgsrc;

    @Column
    private String parameter;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GameDataEntity{");
        sb.append("dataId=").append(dataId);
        sb.append(", category=").append(category);
        sb.append(", title='").append(title).append('\'');
        sb.append(", imgsrc='").append(imgsrc).append('\'');
        sb.append(", parameter='").append(parameter).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
