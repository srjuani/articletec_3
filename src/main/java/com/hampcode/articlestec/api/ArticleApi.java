package com.hampcode.articlestec.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hampcode.articlestec.model.Article;
import com.hampcode.articlestec.service.ArticleService;

@RestController
@RequestMapping("/api/articles")
public class ArticleApi {
	
	@Autowired
	private ArticleService articleService;
	
	@GetMapping
	public ResponseEntity<List<Article>> getAllArticle(){
		List<Article>articles= articleService.findAll();
		if(articles.isEmpty()) {
			return new ResponseEntity<List<Article>>(articles,HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<List<Article>>(articles,HttpStatus.OK);
		}

	}
	
	@PostMapping
	public ResponseEntity<Article> SaveArticle(@Valid @RequestBody Article article){
		//si es complejo el json de respuesta es necesario un mapper
		Article newArticle = articleService.createArticle(article);
		return new ResponseEntity<Article>(newArticle,HttpStatus.OK);
	}
	
	@PutMapping("/{id")
	public ResponseEntity<Article> updateArticle(@PathVariable(value="id")Long id,@Valid @RequestBody Article article){
		//si es complejo el json de respuesta es necesario un mapper
		Article newArticle = articleService.updateArticle(id, article);
		return new ResponseEntity<Article>(newArticle,HttpStatus.OK);
	}	
	
	
}
