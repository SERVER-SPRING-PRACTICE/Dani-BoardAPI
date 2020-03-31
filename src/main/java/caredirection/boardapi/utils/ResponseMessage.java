package caredirection.boardapi.utils;

public class ResponseMessage {
    public static final String NULL_VALUE = "필요한 값이 없습니다.";
    public static final String INVALID_TOKEN ="유효하지 않은 token 입니다.";
    public static final String EMPTY_TOKEN = "토큰값이 존재하지 않습니다.";
    public static final String EXPIRED_TOKEN = "만료된 토큰입니다.";
    public static final String EMPTY_REFRESH_TOKEN = "재발급 토큰이 존재하지 않습니다.";
    public static final String CREATE_TOKEN = "토큰 발급 완료.";
    public static final String REFRESH_TOKEN = "토큰 재발급 완료.";
    public static final String NO_SELECT_AUTHORITY = "조회 권한 없음";
    public static final String USER_SELECTED = "회원 조회 성공.";
    public static final String PRODUCT_INSERT_SUCCESS = "제품 등록 성공";
    public static final String SIGN_UP_INSERT_SUCCESS = "회원가입 성공";
    public static final String SIGN_IN_SUCCESS = "로그인 성공";
    public static final String SIGN_IN_ERROR = "잘못된 아이디 또는 비밀번호 입니다.";
    public static final String VALID_ID = "이용가능한 아이디";
    public static final String INVALID_ID = "중복되는 아이디";
    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String SUCCESS = "success";
    public static final String DUPLICATED = "duplicated";
    public static final String DB_ERROR = "데이터베이스 에러";
    public static final String BOARD_REGISTER_SUCCESS = "게시판 글 쓰기 성공";
    public static final String BOARD_LIST_SUCCESS ="게시판 글 목록 가져오기 성공";

}
