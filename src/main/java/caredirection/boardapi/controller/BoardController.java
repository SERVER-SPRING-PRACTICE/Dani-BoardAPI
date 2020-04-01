package caredirection.boardapi.controller;

import caredirection.boardapi.dto.Board;
import caredirection.boardapi.model.BoardReq;
import caredirection.boardapi.service.BoardService;
import caredirection.boardapi.service.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static caredirection.boardapi.model.DefaultRes.FAIL_DEFAULT_RES;

@Slf4j
@Controller
public class BoardController {

    private final BoardService boardService;
    private final JWTService jwtService;

    public BoardController(final BoardService boardService, final JWTService jwtService){
        this.boardService = boardService;
        this.jwtService = jwtService;
    }

    @PostMapping("board")
    public ResponseEntity board(
            @RequestHeader(value = "token") final String token,
            BoardReq boardReq,
            @RequestPart(value="boardImg", required = false) final MultipartFile[] boardImg){
        try{
            final int userIdx = jwtService.decode(token).getUser_idx();
            boardReq.setUserIdx(userIdx);
            if(boardImg != null) boardReq.setBoardImg(boardImg);
            return new ResponseEntity<>(boardService.postBoard(boardReq), HttpStatus.CREATED);
        }catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("boards")
    public ResponseEntity getBoards(@RequestHeader(value = "token") String token){
        try{
            final int userIdx = jwtService.decode(token).getUser_idx();
            Board board = new Board();
            board.setUserIdx(userIdx);
            return new ResponseEntity<>(boardService.getBoards(board),HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("board/{board_idx}")
    public ResponseEntity getSpecificBoard(@RequestHeader(value = "token") final String token, @PathVariable(value = "board_idx") final int boardIdx){
        try{
            final int userIdx = jwtService.decode(token).getUser_idx();
            Board board = new Board();
            board.setUserIdx(userIdx);
            board.setBoardIdx(boardIdx);
            return new ResponseEntity<>(boardService.getSpecificBoard(board),HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
