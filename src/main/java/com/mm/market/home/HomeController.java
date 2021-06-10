package com.mm.market.home;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mm.market.product.ProductService;
import com.mm.market.product.ProductVO;
import com.mm.market.util.Pager;
import com.mm.market.util.ProductPager;

@Controller
public class HomeController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	public ModelAndView home(ProductPager productPager, ModelAndView mv)throws Exception {
		
		List<ProductVO> list = productService.getList(productPager, 11L, 5L);
		
		mv.addObject("products", list);
		mv.setViewName("home");
		
		return mv;
	}
	
}
