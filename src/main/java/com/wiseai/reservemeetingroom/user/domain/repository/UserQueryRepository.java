package com.wiseai.reservemeetingroom.user.domain.repository;

import com.wiseai.reservemeetingroom.user.domain.User;
import java.util.List;
import java.util.Optional;

/**
 * 이 클래스는 []를 담당합니다.
 */
public interface UserQueryRepository {

	List<User> searchActiveUsers(String keyword);
	Optional<User> findActiveById(Long id);
	Optional<User> findActiveByEmail(String email);
}
