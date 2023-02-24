package kh.spring.s01;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kh.spring.s01.board.model.vo.BoardVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
   //필드위치에 적어줄 것
   @Autowired
   private SqlSession sqlSession;
   
   
   private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
   
   /**
    * Simply selects the home view to render by returning its name.
    */
   @RequestMapping(value = "/aaaa", method = RequestMethod.GET)
   public String home(Locale locale, Model model) {
      
      List<String> boardvolist =  sqlSession.selectList("board.list");
      //sqlSession 앞에 new 안붙여도 돼... 
      model.addAttribute("boardlist", boardvolist);
      
      
      
      
      
      logger.info("Welcome home! The client locale is {}.", locale);
      
      Date date = new Date();
      DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
      
      String formattedDate = dateFormat.format(date);
      
      
      //model : Controller ------전달데이터를 담는 객체------> jsp
      model.addAttribute("serverTime", formattedDate );
      //get,setAttribute와 같은 역할.. 
      //model 이라는 새로운 자료형.. 
      
      
      return "home";
   }
   
}