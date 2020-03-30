package caredirection.boardapi.mapper;

import caredirection.boardapi.dto.User;
import caredirection.boardapi.model.SigninReq;
import caredirection.boardapi.model.SignupReq;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO User(userName, userId, userPw, userSalt) VALUES(#{signupReq.userName},#{signupReq.userId},#{signupReq.userPw},#{signupReq.userSalt})")
        // @Options(useGeneratedKeys = true, keyColumn = "user.userIdx")
    int signup(@Param("signupReq") final SignupReq signupReq);

    // userId로 userSalt 반환
    @Select("SELECT userSalt FROM practice.User WHERE userId = #{id}")
    String getSaltById(@Param("id") final String id);

    // userId로 userPw 반환
    @Select("SELECT userPw FROM practice.User WHERE userId = #{id}")
    String getPwById(@Param("id") final String id);


    // 아이디와 비밀번호로 조회
    @Select("SELECT * FROM User WHERE userId = #{userId} AND userPw = #{userPw}")
    User findByUserIdAndPw(@Param("userId") final String userId, @Param("userPw") final String userPw);

}
