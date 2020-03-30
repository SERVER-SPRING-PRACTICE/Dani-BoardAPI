package caredirection.boardapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
// 서버 -> 클라이언트
public class User {
    private int userIdx;
    private String userName;
    private String userId;
}
