package caredirection.boardapi.mapper;

import caredirection.boardapi.dto.Board;
import caredirection.boardapi.model.BoardReq;
import org.apache.ibatis.annotations.*;

@Mapper
public interface BoardMapper {

    // 게시판 글 등록
    @Insert("INSERT INTO Board(userIdx, boardTitle, boardContent) VALUES(#{userIdx}, #{title}, #{content})")
    int postBoard(@Param("userIdx") final int userIdx, @Param("title") final String title, @Param("content") final String content);

    // boardIdx 반환
    @Select("SELECT boardIdx FROM Board WHERE userIdx = #{userIdx} AND boardTitle = #{title} AND boardContent = #{content}")
    int getBoardIdx(@Param("userIdx") final int userIdx, @Param("title") final String title, @Param("content") final String content);

    // 게시판 글 목록 조회
    @Select("SELECT boardIdx FROM Board WHERE userIdx = #{userIdx}")
    Board[] getBoards(@Param("userIdx") final int userIdx);

    // 특정 게시글 조회
    @Select("SELECT * FROM Board WHERE userIdx = #{userIdx} AND boardIdx = #{boardIdx}")
    Board getSpecificBoard(@Param("userIdx") final int userIdx, @Param("boardIdx") final int boardIdx);
}