package caredirection.boardapi.mapper;

import caredirection.boardapi.model.SignupReq;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO User(userName, userId, userPw, userSalt) VALUES(#{signupReq.userName},#{signupReq.userId},#{signupReq.userPw},#{signupReq.userSalt})")
        // @Options(useGeneratedKeys = true, keyColumn = "user.userIdx")
    int signup(@Param("signupReq") final SignupReq signupReq);

}
