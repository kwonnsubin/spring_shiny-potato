package kh.spring.s02.board.model.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardFileVo {
	private int boardNum;
	private String originalFilename;
	private String renameFilename;
}
