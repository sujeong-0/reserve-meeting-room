package com.wiseai.reservemeetingroom.user.domain.repository;

import com.wiseai.reservemeetingroom.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 이 클래스는 유저 저장소를 담당합니다.
 */
public interface UserRepository extends JpaRepository<User, Long>, UserQueryRepository {

}
