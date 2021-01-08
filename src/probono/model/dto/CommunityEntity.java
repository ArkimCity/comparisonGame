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
@Entity(name="community")
@SequenceGenerator(name = "community_id_gen", sequenceName = "community_id_seq", initialValue = 1, allocationSize=1)
public class CommunityEntity {
	@Id
	@GeneratedValue(generator="community_id_gen")
	private int postId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="userId")
	private UserEntity userId;
	
	@Column
	private String word;
	
	@Column
	private int thumbsUp;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CommunityEntity [postId=");
		builder.append(postId);
		builder.append(", userId=");
		builder.append(userId.getUserId());
		builder.append(", word=");
		builder.append(word);
		builder.append(", thumbsUp=");
		builder.append(thumbsUp);
		builder.append("]");
		return builder.toString();
	}
}
