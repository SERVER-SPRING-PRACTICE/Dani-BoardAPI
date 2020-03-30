package caredirection.boardapi.service;

import caredirection.boardapi.dto.User;
import caredirection.boardapi.mapper.UserMapper;
import caredirection.boardapi.model.DefaultRes;
import caredirection.boardapi.model.SigninReq;
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
    private final JWTService jwtService;

    public UserService(final UserMapper userMapper, final JWTService jwtService){
        this.userMapper=userMapper;
        this.jwtService=jwtService;
    }

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

    public DefaultRes signin(final SigninReq signinReq){
        try{
            String salt = userMapper.getSaltById(signinReq.getUserId());
            String password = signinReq.getUserPw();
            password = SHA256Util.getEncrypt(password, salt);

            if(!password.equals(userMapper.getPwById(signinReq.getUserId())) || salt.equals(null))
                return DefaultRes.res(StatusCode.UNAUTHORIZED, ResponseMessage.SIGN_IN_ERROR);

            final User user = userMapper.findByUserIdAndPw(signinReq.getUserId(), password);
            if (user != null) {
                //토큰 생성
                final JWTService.TokenRes tokenDto = new JWTService.TokenRes(jwtService.create(user.getUserIdx()));
                return DefaultRes.res(StatusCode.OK, ResponseMessage.SIGN_IN_SUCCESS, tokenDto);
            }
            return DefaultRes.res(StatusCode.BAD_REQUEST, ResponseMessage.SIGN_IN_ERROR);
        } catch(Exception e){
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }
}