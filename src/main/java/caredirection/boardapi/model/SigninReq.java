package caredirection.boardapi.model;

import lombok.Data;

@Data
public class SigninReq {
    private String userId;
    private String userPw;
    private String userSalt;
}
