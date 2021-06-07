package com.mm.market.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/comment/**")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@GetMapping("commentInsert")
	public ModelAndView commentInsert() throws Exception {
		ModelAndView mv = new ModelAndView();
		CommentVO commentVO = new CommentVO();
		
		mv.addObject("vo", commentVO);
		mv.setViewName("social/select");
		
		return mv;
	}

	@PostMapping("commentInsert")
	public ModelAndView setInsert(CommentVO commentVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		int result = commentService.setInsert(commentVO);
		
		mv.setViewName("redirect:../social/list");
		
		return mv;
	}
	@GetMapping("commentUpdate") //댓글 수정
	public ModelAndView commentUpdate(CommentVO commentVO) throws Exception {
		ModelAndView mv = new ModelAndView();

		commentVO = commentService.getSelect(commentVO);

		mv.addObject("vo", commentVO);
		mv.setViewName("social/select");

		return mv;
	}
	
	@PostMapping("socialUpdate")
	public ModelAndView setUpdate(CommentVO commentVO, ModelAndView mv) throws Exception {
		int result = commentService.setUpdate(commentVO);
		System.out.println("result : "+result);

		if(result>0) {
			System.out.println("수정 완료!");
			mv.setViewName("redirect:./list");
		} else {
			System.out.println("수정 실패");
			mv.setViewName("redirect:./list");
		}
		
		return mv;
	}

	@GetMapping("commentDelete")
	public ModelAndView commentDelete(CommentVO commentVO) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		int result = commentService.setDelete(commentVO);
		String message = "삭제 실패";
		String path = "../social/list";
		
		if(result>0) {
			message = "삭제 성공";
		}
		
		mv.addObject("msg", message);
		mv.addObject("path", path);
		
		mv.setViewName("common/commonResult");
		
		return mv;
	}

}
