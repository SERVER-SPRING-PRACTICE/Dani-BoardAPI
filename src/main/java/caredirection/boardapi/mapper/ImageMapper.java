package caredirection.boardapi.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ImageMapper {

    // 게시판 이미지 등록
    @Insert("INSERT INTO Image(boardIdx, imageURL) VALUES(#{boardIdx}, #{url})")
    int registerImg(@Param("boardIdx") final int boardIdx, @Param("url") final String url);

    // boardIdx 로 imageURL 가져오기
    @Select("SELECT imageURL FROM Image WHERE boardIdx = #{boardIdx}")
    String[] getImgByBoardIdx(@Param("boardIdx") final int boardIdx);
}
