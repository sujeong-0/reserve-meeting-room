package com.wiseai.reservemeetingroom.user.domain.repository;

import com.wiseai.reservemeetingroom.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 이 클래스는 유저 저장소를 담당합니다.
 */
public interface UserRepository extends JpaRepository<User, Long> {


	@Query("SELECT m FROM User m WHERE m.email = :email")
	Optional<User> findByEmail(String email);

}
