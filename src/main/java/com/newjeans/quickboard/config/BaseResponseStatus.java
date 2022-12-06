package com.newjeans.quickboard.config;

import lombok.Getter;

/**
 * 에러 코드 관리
 */
@Getter
public enum BaseResponseStatus {
    /**
     * 1000 : 요청 성공
     */
    SUCCESS(true, 1000, "요청에 성공하였습니다."),


    /**
     * 2000 : Request 오류
     */
    // Common
    REQUEST_ERROR(false, 2000, "입력값을 확인해주세요."),
    EMPTY_UUID(false, 2001, "UUID 토큰을 입력해주세요."),
    INVALID_UUID(false, 2002, "유효하지 않은 UUID 토큰입니다."),
    EMPTY_FCM(false, 2003, "FCM 토큰을 입력해주세요."),
    INVALID_FCM(false, 2004, "유효하지 않은 FCM 토큰입니다."),
    ALREADY_EXIST_USER(false, 2005, "이미 존재하는 유저입니다."),
    NOT_FOUND_USER(false,2006,"등록되지 않은 유저입니다."),

    INVALID_DEPARTMENT_ID(false,2010,"해당 학과가 존재하지 않습니다. 학과 아이디 값을 확인해주세요."),

    POST_POSTS_EMPTY_IMGURL(false,2019,"현재 게시글의 이미지가 없습니다."),
    POSTS_EMPTY_POST_ID(false, 2020, "게시글이 존재하지 않습니다. 게시글 아이디 값을 확인해주세요."),
    ALREADY_EXIST_BOOKMARK(false, 2021, "이미 등록된 북마크 입니다."),

    ALREADY_TRIED_DEPARTMENT(false, 2030,"이미 등록된 학과입니다."),


    /**
     * 3000 : Response 오류
     */
    // Common
    RESPONSE_ERROR(false, 3000, "값을 불러오는데 실패하였습니다."),

    //notice
    SURVEY_NOT_EXIST(false,3015,"게시글이 존재하지 않습니다."),
    SURVEY_NOT_VALID(false, 3016, "마감된 게시글입니다."),
    SURVEY_IS_DELETED(false, 3017, "삭제된 게시글입니다."),

    /**
     * 4000 : Database, Server 오류
     */
    DATABASE_ERROR(false, 4000, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 4001, "서버와의 연결에 실패하였습니다."),


    // 5000 : 필요시 만들어서 쓰세요
    DEPARTMENT_ID_RANGE_ERROR(false, 5000, "departmentId : 1~10 숭대전체, 11~ 전공학과"),


    // 6000 : 필요시 만들어서 쓰세요


    CHECK_ERROR(false,7777,"test중입니다");

    private final boolean isSuccess;
    private final int code;
    private final String message;

    private BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
}
