package caredirection.boardapi.service;

import caredirection.boardapi.mapper.UserMapper;
import caredirection.boardapi.model.DefaultRes;
import caredirection.boardapi.model.SignupReq;
import caredirection.boardapi.utils.ResponseMessage;
import caredirection.boardapi.utils.SHA256Util;
import caredirection.boardapi.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(final UserMapper userMapper){ this.userMapper = userMapper;}

    public DefaultRes signup(final SignupReq signupReq){
        try{
            String salt = SHA256Util.generateSalt();
            signupReq.setUserSalt(salt);
            String password = signupReq.getUserPw();
            password = SHA256Util.getEncrypt(password,salt);
            signupReq.setUserPw(password);
            userMapper.signup(signupReq);
            return DefaultRes.res(StatusCode.CREATED, ResponseMessage.SIGN_UP_INSERT_SUCCESS);
        } catch(Exception e){
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }
}