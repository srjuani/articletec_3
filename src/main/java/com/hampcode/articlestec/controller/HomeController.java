package com.hampcode.articlestec.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.hampcode.articlestec.common.PageInitPagination;
import com.hampcode.articlestec.service.ArticleService;

@Controller
@RequestMapping({"/","/home"})
public class HomeController {

	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private PageInitPagination pageInitPagination;
	
	protected static final String INDEX_VIEW="index";
	protected static final String ARTICLE_VIEW="articles/showArticle";
	
	@GetMapping
	public ModelAndView getIndex(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page) {
		ModelAndView mv=pageInitPagination.initPagination(pageSize, page,INDEX_VIEW);
		return mv;
	}
	
	@GetMapping("/{id}")
	public String getArticleById(@PathVariable(value="id") Long articleId, 
			Model model) {
		model.addAttribute("article",articleService.findById(articleId));
		return ARTICLE_VIEW;
	}
	
}



