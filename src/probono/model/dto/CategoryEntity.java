package comparisonGame.model.dto;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder
@Entity(name = "category")
public class CategoryEntity {
    @Id
    private String category;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CategoryEntity{");
        sb.append("category='").append(category).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
