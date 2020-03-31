package caredirection.boardapi.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ImageMapper {

    // 게시판 이미지 등록
    @Insert("INSERT INTO Image(boardIdx, imageURL) VALUES(#{boardIdx}, #{url})")
    int registerImg(@Param("boardIdx") final int boardIdx, @Param("url") final String url);

}
