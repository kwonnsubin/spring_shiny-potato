package kh.spring.s02.board.controller;

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

import kh.spring.s02.board.model.service.BoardService;
import kh.spring.s02.board.model.vo.BoardVo;

@Controller
//@WebServlet("/boardinsert")
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void viewListBoard( Model model) {
		model.addAttribute("boardlist", service.selectList());
	}
	
//	@RequestMapping(value = "/board/update", method = RequestMethod.GET)
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void viewUpdateBoard() {
		
	}
//	@RequestMapping(value = "/board/delete", method = RequestMethod.GET)
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public void viewDeleteBoard() {
		
	}
	
//	@RequestMapping(value = "/board/read", method = RequestMethod.GET)
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void viewReadBoard() {
		
	}
//	@RequestMapping(value = "/board/insert", method = RequestMethod.GET)
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	//@GetMapping("/boardinsert")
//	public ModelAndView viewInsertBoard(
//	public Stromg viewInsertBoard(
	public void viewInsertBoard(
			ModelAndView mv
			, HttpServletRequest req
			, HttpSession session
			, BoardVo vo
			) {
//		HttpSession session2 = req.getSession();
//		session2.setAttribute("lgnss", "값");
//		
//		ModelAndView mv1 = new ModelAndView();
//		mv1.setViewName("insert");
		
//		mv.addObject("test", "test value");
//		mv.setViewName("boardinsert");
//		return mv;
//		return "boardinsert";
		return;
	}
//	@RequestMapping(value = "/board/insert", method = RequestMethod.POST)
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	//@PostMapping("/boardinsert")
	public ModelAndView doInsertBoard(ModelAndView mv) {
		
		return mv;
	}
	
//	@RequestMapping(value = "/boardinsert")
	@RequestMapping("/test")
	public ModelAndView test(ModelAndView mv) {

		return mv;
	}
	
}
