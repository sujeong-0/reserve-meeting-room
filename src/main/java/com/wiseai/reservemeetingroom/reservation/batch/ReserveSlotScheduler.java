package com.wiseai.reservemeetingroom.reservation.batch;

import com.wiseai.reservemeetingroom.meeting_room.domain.MeetingRoom;
import com.wiseai.reservemeetingroom.meeting_room.domain.repository.MeetingRoomRepository;
import com.wiseai.reservemeetingroom.reservation.domain.ReserveSlot;
import com.wiseai.reservemeetingroom.reservation.domain.repository.ReserveSlotRepository;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 이 클래스는 []를 담당합니다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ReserveSlotScheduler {
	private final ReserveSlotRepository reserveSlotRepository;
	private final MeetingRoomRepository meetingRoomRepository;

	@Scheduled(cron = "0 0 0 * * *") // todo 분리하기
	@Transactional
	public void createNextDaySlots() {
		LocalDate tomorrow = LocalDate.now().plusDays(1);
		LocalDateTime startOfDay = tomorrow.atStartOfDay();
		LocalDateTime endOfDay = startOfDay.plusDays(1);

		List<ReserveSlot> slotsToCreate = new ArrayList<>();

		List<MeetingRoom> rooms = meetingRoomRepository.searchActiveMeetingRooms();

		for (MeetingRoom room : rooms) {
			LocalDateTime time = startOfDay;
			while (!time.isAfter(endOfDay)) {
				Instant start = time.atZone(ZoneId.of("Asia/Seoul")).toInstant();
				Instant end = time.plusMinutes(30).atZone(ZoneId.of("Asia/Seoul")).toInstant();
				slotsToCreate.add(ReserveSlot.of(room, start, end));
				time = time.plusMinutes(30);
			}
		}

		reserveSlotRepository.saveAll(slotsToCreate);

		log.info("Created {} slots for {}", slotsToCreate.size(), tomorrow);
	}
}
