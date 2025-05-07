package com.wiseai.reservemeetingroom.reservation.domain.repository;

import com.wiseai.reservemeetingroom.reservation.domain.ReserveSlot;
import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 이 클래스는 예약 슬롯 저장소를 담당합니다.
 */
public interface ReserveSlotRepository extends JpaRepository<ReserveSlot, Long> {
	@Query("""
    SELECT COUNT(rs) > 0
    FROM ReserveSlot rs
    WHERE rs.meetingRoom.id = :meetingRoomId
      AND rs.startTime >= :startTime
      AND rs.endTime <= :endTime
      AND rs.isReserved = true
    """)
	boolean existsReservedSlotInRange(
		@Param("meetingRoomId") Long meetingRoomId,
		@Param("startTime") Instant startTime,
		@Param("endTime") Instant endTime
	);

	@Modifying
	@Query("UPDATE ReserveSlot rs SET rs.isReserved = true WHERE rs.id IN :slotIds")
	int updateIsReservedTrueByIds(@Param("slotIds") List<Long> slotIds);
}
