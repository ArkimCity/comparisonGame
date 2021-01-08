package probono.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Builder
@Entity(name="userword")
@SequenceGenerator(name = "userword_id_gen", sequenceName = "userword_id_seq", initialValue = 1, allocationSize=1)
public class UserWordEntity {
	@Id
	@GeneratedValue(generator="userword_id_gen")
	private String saveId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	private UserEntity userId;
	
	@Column 
	private String word;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserWordEntity [saveId=");
		builder.append(saveId);
		builder.append(", userId=");
		builder.append(userId.getUserId());
		builder.append(", word=");
		builder.append(word);
		builder.append("]");
		return builder.toString();
	}
}
