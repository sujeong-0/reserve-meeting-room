package com.wiseai.reservemeetingroom.reservation.domain.repository;

import com.wiseai.reservemeetingroom.reservation.domain.ReserveSlot;
import jakarta.persistence.LockModeType;
import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 이 클래스는 예약 슬롯 저장소를 담당합니다.
 */
public interface ReserveSlotRepository extends JpaRepository<ReserveSlot, Long> {
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT s FROM ReserveSlot s WHERE s.id IN :ids")
	List<ReserveSlot> findAllByIdWithLock(@Param("ids") List<Long> ids);

	@Modifying
	@Query("UPDATE ReserveSlot rs SET rs.isReserved = true WHERE rs.id IN :slotIds")
	int updateIsReservedTrueByIds(@Param("slotIds") List<Long> slotIds);

	@Modifying
	@Query("UPDATE ReserveSlot rs SET rs.isReserved = false WHERE rs.id IN :slotIds")
	void unreserveSlotsByIds(@Param("slotIds") List<Long> slotIds);
}
