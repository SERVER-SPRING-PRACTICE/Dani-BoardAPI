package caredirection.boardapi.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class BoardReq {
    private Integer userIdx;
    private String boardTitle;
    private String boardContent;
    private MultipartFile[] boardImg;
    private String[] boardImgURL;
}
