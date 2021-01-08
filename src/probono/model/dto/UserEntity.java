package probono.model.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity(name="users")
public class UserEntity {
	@Id
	private String userId;
	@Column
	private String password;
	@Column
	private String userName;
	@Column
	private String nickname;
	
	@OneToMany(mappedBy="userId") 
	private List<UserWordEntity> userwords;
	
	@OneToMany(mappedBy="userId") 
	private List<CommunityEntity> communities;

	@Builder
	public UserEntity(String userId, String password, String userName, String nickname) {
		super();
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.nickname = nickname;
	}
	
}
