package caredirection.boardapi.service;

import caredirection.boardapi.dto.Board;
import caredirection.boardapi.mapper.BoardMapper;
import caredirection.boardapi.mapper.ImageMapper;
import caredirection.boardapi.model.BoardReq;
import caredirection.boardapi.model.DefaultRes;
import caredirection.boardapi.utils.ResponseMessage;
import caredirection.boardapi.utils.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class BoardService {

    private final BoardMapper boardMapper;
    private final ImageMapper imageMapper;
    private final S3FileUploadService s3FileUploadService;
    public BoardService(final BoardMapper boardMapper, final ImageMapper imageMapper, final S3FileUploadService s3FileUploadService){
        this.boardMapper = boardMapper;
        this.imageMapper = imageMapper;
        this.s3FileUploadService = s3FileUploadService;
    }

    @Transactional
    public DefaultRes postBoard(final BoardReq boardReq){
        try{
            boardMapper.postBoard(boardReq.getUserIdx(), boardReq.getBoardTitle(), boardReq.getBoardContent());
            int boardIdx = boardMapper.getBoardIdx(boardReq.getUserIdx(), boardReq.getBoardTitle(), boardReq.getBoardContent());
            // 이미지 있을 경우
            if(boardReq.getBoardImg() != null) {
                for(MultipartFile file : boardReq.getBoardImg()){
                    imageMapper.registerImg(boardIdx,s3FileUploadService.upload(file));
                }
            }
            return DefaultRes.res(StatusCode.CREATED, ResponseMessage.BOARD_REGISTER_SUCCESS);
        } catch(Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    @Transactional
    public DefaultRes getBoards(Board board){
        try{
            Board[] boardArr = boardMapper.getBoards(board.getUserIdx());
            for(Board b : boardArr){
                b.setBoardImgURL(imageMapper.getImgByBoardIdx(b.getBoardIdx()));
            }
            return DefaultRes.res(StatusCode.OK, ResponseMessage.BOARD_LIST_SUCCESS, boardArr );
        } catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }

    @Transactional
    public DefaultRes getSpecificBoard(Board board){
        try{
            board = boardMapper.getSpecificBoard(board.getUserIdx(), board.getBoardIdx());
            board.setBoardImgURL(imageMapper.getSpecificBoardImg(board.getBoardIdx()));
            return DefaultRes.res(StatusCode.OK, ResponseMessage.GET_SPECIFIC_BOARD_INFO_SUCCESS, board);
        } catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            log.error(e.getMessage());
            return DefaultRes.res(StatusCode.DB_ERROR, ResponseMessage.DB_ERROR);
        }
    }
}
