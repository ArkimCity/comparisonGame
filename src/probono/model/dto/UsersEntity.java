package comparisonGame.model.dto;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity(name = "users")
public class UsersEntity implements Serializable {
    @Id
    private String userId;

    @Column
    private String password;

    @Column
    private String userName;

    @Column
    private String nickName;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UsersEntity{");
        sb.append("userId='").append(userId).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", nickName='").append(nickName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
