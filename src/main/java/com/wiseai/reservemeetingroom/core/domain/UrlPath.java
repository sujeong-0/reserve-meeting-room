package com.wiseai.reservemeetingroom.core.domain;

/**
 * 이 클래스는 URL Path 정의을 담당합니다.
 */
public class UrlPath {
	public static final String REST_PREFIX = "/api";
	public static final String REST_VERSION = "/v1";

	public static final class User {

		public static final String ROOT = REST_PREFIX + REST_VERSION + "/users";

	}
	public static final class Reservation {
		public static final String ROOT = REST_PREFIX + REST_VERSION + "/reservations";

	}
	public static final class MeetingRoom {
		public static final String ROOT = REST_PREFIX + REST_VERSION + "/meeting-rooms";

	}
}
