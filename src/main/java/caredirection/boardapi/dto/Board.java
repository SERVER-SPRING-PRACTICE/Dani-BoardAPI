package caredirection.boardapi.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Board {
    private int boardIdx;
    private int userIdx;
    private String boardTitle;
    private String boardContent;
    private String[] boardImgURL;
}
