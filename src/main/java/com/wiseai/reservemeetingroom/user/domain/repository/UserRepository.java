package com.wiseai.reservemeetingroom.user.domain.repository;

import com.wiseai.reservemeetingroom.user.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 이 클래스는 유저 저장소를 담당합니다.
 */
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(String name, String email);

}
