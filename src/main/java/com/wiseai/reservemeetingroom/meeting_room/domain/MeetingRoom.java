package com.wiseai.reservemeetingroom.meeting_room.domain;

import com.wiseai.reservemeetingroom.core.domain.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 이 클래스는 회의실 엔티티를 담당합니다.
 */
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id",callSuper = false)
@Table(name = "meeting_rooms")
public class MeetingRoom extends BaseTimeEntity {

	/**
	 * 회의실 아이디
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * 이름
	 */
	@Column(name = "name", nullable = false)
	private String name;

	/**
	 * 위치
	 */
	@Column(name = "location", nullable = false, unique = true)
	private String location;

	/**
	 * 삭제 여부
	 */
	@Column(name = "is_deleted", nullable = false)
	@Builder.Default
	private boolean deleted = false;

	public void updateFrom(MeetingRoom source) {
		if (source.getName() != null) {
			this.name = source.getName();
		}
		if (source.getLocation() != null) {
			this.location = source.getLocation();
		}
	}


	public void markAsDeleted() {
		this.deleted = true;
	}

}
