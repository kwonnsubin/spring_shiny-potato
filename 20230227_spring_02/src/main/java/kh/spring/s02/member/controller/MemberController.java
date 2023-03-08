package kh.spring.s02.member.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kh.spring.s02.member.model.service.MemberService;
import kh.spring.s02.member.model.vo.MemberVo;

@Controller
@RequestMapping("/member")
public class MemberController {
	@Autowired
	private MemberService service;

	@GetMapping("/signUp")
	public ModelAndView viewInsert(ModelAndView mv)   throws Exception{
		mv.setViewName("member/signUp");
		return mv;
	}
	@PostMapping("/signUp")
	public ModelAndView insert(ModelAndView mv
			, MemberVo vo
			, String bbb			
			, String id
			, RedirectAttributes rttr
			)   throws Exception {
		int result = -1;
//		try {
			result = service.insert(vo);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		if(result > 0) {
			// 회원가입성공
//			 방법 1  - 사용불가방법
//			mv.setViewName("redirect:/?msg=회원가입성공");
//			// 방법 2
//			mv.addObject("msg", "회원가입성공");
//			mv.setViewName("error/errorFailure");
			// 방법 3 - Spring에서만
			rttr.addFlashAttribute("msg", "회원가입성공2");
			mv.setViewName("redirect:/");
		} else {
			// 회원가입실패
			// 방법 3 - Spring에서만
			rttr.addFlashAttribute("msg", "회원가입실패");
			mv.setViewName("redirect:/member/signUp");
		}
		return mv;
	}
	@GetMapping("/update")
	public ModelAndView viewUpdate(ModelAndView mv
			// String id :   id 라는 이름의 Parameter 없어도 됨. 없는 경우  null 값이 들어감. String id
			// , String id
			// @RequestParam("id") String id: id 라는 이름의 Parameter 꼭 있어야 함. required=true 임
			, @RequestParam("id") String id     // String id =  request.getParameter("id");
//			, @RequestParam("aaa") int bbb   // String bbb =  request.getParameter("aaa");
//			, @RequestParam(name="ccc", required = false, defaultValue = "100") int ccc   // String ccc =  request.getParameter("ccc");
			)   throws Exception{
		MemberVo vo = service.selectOne(id);
		mv.addObject("membervo", vo);
		mv.setViewName("/member/update");
		return mv;
	}
	@PostMapping("/update")
	public ModelAndView update(ModelAndView mv	
			, MemberVo vo)   throws Exception{
		service.update(vo);
		return mv;
	}
	@GetMapping("/delete")
	public ModelAndView delete(ModelAndView mv
			, String id
			)   throws Exception{
		service.delete(id);
		return mv;
	}
	@GetMapping("/info")
	public ModelAndView selectOne(ModelAndView mv
			, String id   // request.getParameter("id")  
			// url " /member/info?id=user3
			// url " /member/info/user3
			)   throws Exception{
		if(id == null) {
			mv.setViewName("redirect:/");
			return mv;
		}
		MemberVo result = service.selectOne(id);
		mv.addObject("membervo", result);
		mv.setViewName("member/info");
		return mv;
	}
	@GetMapping("/list")
	public ModelAndView selectList(ModelAndView mv)   throws Exception{
		List<MemberVo> result = service.selectList();
		mv.addObject("memberlist", result);
		return mv;
	}
	
	// 프로젝트 중 후반에 작성하기  -- 그 전에는 각각 try-catch
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView memberNullPointExceptionHandler( NullPointerException e
			// 오류 발생함. ModelAndView mv
			) {
		e.printStackTrace();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", e.getMessage()+" 오류가 발생했습니다. 다시 시도해 주세요.");
		mv.setViewName("error/error500");
		return mv;
	}
	@ExceptionHandler(NumberFormatException.class)
	public ModelAndView memberNumberFormatExceptionHandler( NumberFormatException e
			// 오류 발생함. ModelAndView mv
			) {
		e.printStackTrace();
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", e.getMessage()+" 오류가 발생했습니다. 다시 시도해 주세요.");
		mv.setViewName("error/error500");
		return mv;
	}
	@ExceptionHandler(SQLException.class)
	public ModelAndView memberSQLExceptionHandler( SQLException e
			// 오류 발생함. ModelAndView mv
			) {
		e.printStackTrace();
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", e.getMessage()+" 오류가 발생했습니다. 다시 시도해 주세요.");
		mv.setViewName("error/error500");
		return mv;
	}
//	@ExceptionHandler
	@ExceptionHandler(Exception.class)
	public ModelAndView memberExceptionHandler( Exception e
			// 오류 발생함. ModelAndView mv
			) {
		e.printStackTrace();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", e.getMessage()+" 오류가 발생했습니다. 다시 시도해 주세요.");
		mv.setViewName("error/error500");
		return mv;
	}
}
