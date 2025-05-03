package com.wiseai.reservemeetingroom.user.domain;

import com.wiseai.reservemeetingroom.core.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 이 클래스는 유저 엔티티를 담당합니다.
 */

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User extends BaseTimeEntity {

	/** 유저 아이디 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** 이름 */
	@Column(name = "name", nullable = false)
	private String name;

	/** 알림을 받을 이메일 */
	@Column(name = "email", nullable = false, unique = true)
	private String email;

	/** 삭제 여부 */
	@Column(name = "is_deleted", nullable = false)
	private boolean deleted = false;

	public void updateFrom(User source) {
		if (source.getName() != null) this.name = source.getName();
		if (source.getEmail() != null) this.email = source.getEmail();
	}


	public void markAsDeleted() {
		this.deleted = true;
	}
}
