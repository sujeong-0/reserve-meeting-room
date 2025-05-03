package com.wiseai.reservemeetingroom.user.domain.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.wiseai.reservemeetingroom.user.domain.QUser;
import com.wiseai.reservemeetingroom.user.domain.User;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

/**
 * 이 클래스는 사용자에 대한 커스텀 조회 로직을 담당합니다.
 */
@RequiredArgsConstructor
public class UserQueryRepositoryImpl implements UserQueryRepository {

	private final JPAQueryFactory queryFactory;

	QUser user = QUser.user;

	@Override
	public List<User> searchActiveUsers(String keyword) {
		BooleanBuilder condition = new BooleanBuilder();
		condition.and(user.deleted.isFalse());

		if (null != keyword && !keyword.isBlank()) {
			condition.and(
				user.name.containsIgnoreCase(keyword)
					.or(user.email.containsIgnoreCase(keyword))
			);
		}

		return queryFactory
			.selectFrom(user)
			.where(condition)
			.fetch();
	}

	@Override
	public Optional<User> findActiveById(Long id) {
		return Optional.ofNullable(
			queryFactory
				.selectFrom(user)
				.where(user.id.eq(id).and(user.deleted.isFalse()))
				.fetchOne()
		);
	}

	@Override
	public Optional<User> findActiveByEmail(String email) {
		return Optional.ofNullable(
			queryFactory
				.selectFrom(user)
				.where(user.email.eq(email).and(user.deleted.isFalse()))
				.fetchOne()
		);
	}
}
