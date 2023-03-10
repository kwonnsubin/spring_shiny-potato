package kh.spring.s03.board.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kh.spring.s03.board.model.service.BoardService;
import kh.spring.s03.board.model.vo.BoardVo;

@Controller
//@WebServlet("/boardinsert")
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	private final static int BOARD_LIMIT = 5;  // 한 페이지에 보여줄 게시글 갯수
	private final static int PAGE_LIMIT = 3; // 한 화면에 출력할 게시판 페이지 수
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView viewListBoard( ModelAndView mv) {
		
		// TODO
		// 검색단어는 제목, 내용, 작성자에서 포함되어있으면 찾기
		// null 또는 "" 은 검색하지 않음.(약속)
//		String searchWord = null;
//		String searchWord = "";
		String searchWord = "답";
		
		// TODO
		int currentPage = 2; // 현재 페이지
		int totalCnt = service.selectOneCount(); // 총 글 갯수
		int totalPage = (totalCnt%BOARD_LIMIT==0)? // 총 페이지 수 -> 나눠서 나머지가 0이면
				(totalCnt/BOARD_LIMIT) : 
				(totalCnt/BOARD_LIMIT) + 1; // 0이 아니면 + 1Page
		int startPage = (currentPage%PAGE_LIMIT==0) ? //한 화면에 출력되는 게시판 페이지의 처음 수:  현재페이지 % 한 화면에 출력할 게시판 페이지 수
				(currentPage/PAGE_LIMIT -1)*PAGE_LIMIT + 1 : //  
				(currentPage/PAGE_LIMIT   )*PAGE_LIMIT + 1; // (현재페이지 / 한 화면에 페이지 수)*페이지수 + 1;
		int endPage = (startPage + PAGE_LIMIT > totalPage) ? // 한 화면에 출력되는 게시판 페이지의 마지막 수 : 
				totalPage : 
				(startPage + PAGE_LIMIT);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("totalPage", totalPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("currentPage", currentPage);
		mv.addObject("pageInfo", map);
		
//		mv.addObject("totalPage", totalPage);
//		mv.addObject("startPage", startPage);
//		mv.addObject("endPage", endPage);
//		mv.addObject("currentPage", currentPage);
		
		mv.addObject("boardlist", service.selectList(currentPage, BOARD_LIMIT, searchWord));
		mv.setViewName("board/list");
		return mv;
	}
	
	@GetMapping("/update")
	public void viewUpdateBoard() {
		
	}
//	@PostMapping("/update")
	@GetMapping("/updatePostTest")
	public void updateBoard() {
		// TODO
		int boardNum = 1;
		String boardTitle = "수정제목";
		String boardContent = "수정내용";
		String boardOriginalFilename = "";  // "" 파일없음
		String boardRenameFilename = "";  // "" 파일없음
		
		BoardVo vo = new BoardVo();
		vo.setBoardTitle(boardTitle);
		vo.setBoardContent(boardContent);
		vo.setBoardOriginalFilename(boardOriginalFilename);
		vo.setBoardRenameFilename(boardRenameFilename);
		int result = service.update(vo);
		
	}
	
	
	@GetMapping("/delete")
	public void viewDeleteBoard() {
		//TODO
		int boardNum = 10;
		int result = service.delete(boardNum);
	}
	
	@GetMapping("/read")
	public void viewReadBoard() {
		//TODO
		int boardNum = 1;
		String writer = "user22";
		BoardVo vo = service.selectOne(boardNum, writer);
	}
	
	// 원글 작성페이지 이동
	@GetMapping("/insert")
	public ModelAndView viewInsertBoard(
			ModelAndView mv
			, HttpServletRequest req
			, HttpSession session
			, BoardVo vo
			) {
		mv.setViewName("board/insert");
		return mv;
	}
	
	// 원글 작성 
//	@PostMapping("/insert")
	// TODO
	@GetMapping("/insertPostTest")
	public ModelAndView doInsertBoard(ModelAndView mv
			, BoardVo vo
			) {
		vo.setBoardContent("임시내용");
		vo.setBoardTitle("임시제목");
		vo.setBoardWriter("user22");
		int result = service.insert(vo);
		return mv;
	}
	
	// 답글작성 페이지이동
	@GetMapping("/insertReply")
	public ModelAndView viewInsertReply(ModelAndView mv
			, int boardNum // 몇번글에 답글인지 
			) {
		mv.setViewName("insertReply");
		return mv;
	}
	// 답글작성
//	TODO
//	@PostMapping("/insertReply")
	@GetMapping("/insertReplyPostTest")
	public ModelAndView viewInsertReply(ModelAndView mv
			, BoardVo vo
		) {
		// TODO
		int boardNum = 6;
		vo.setBoardNum(boardNum);
		
		vo.setBoardContent("임시6답내용");
		vo.setBoardTitle("임시6답제목");
		vo.setBoardWriter("user22");
		
		service.insert(vo);
		
		return mv;
	}
	
//	@RequestMapping(value = "/boardinsert")
	@RequestMapping("/test")
	public ModelAndView test(ModelAndView mv) {

		return mv;
	}
	
}
