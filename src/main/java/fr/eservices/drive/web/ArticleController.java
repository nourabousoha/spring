package fr.eservices.drive.web;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import fr.eservices.drive.dao.DataException;
import fr.eservices.drive.model.Article;
import fr.eservices.drive.model.Category;
import fr.eservices.drive.repository.ArticleRepository;
import fr.eservices.drive.repository.CategoryRepository;

@Controller
@RequestMapping(path="/article")
public class ArticleController {
	
	@ExceptionHandler(DataException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public String dataExceptionHandler(DataException ex) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		PrintWriter w = new PrintWriter( out );
		ex.printStackTrace(w);
		w.close();
		return 
			"ERROR"
			+ "<!--\n" + out.toString() + "\n-->";
	}
		
@Autowired	
Validator validator;
@Autowired	
CategoryRepository 	categoryRepository;
@Autowired
ArticleRepository articleRepository;

@RequestMapping(path="/add.html",method=RequestMethod.GET)	
public String addCategory(Model model) {
	List<Category> categories=null;
	List<Article> articles=null;
	try {
	categories =(List<Category>) categoryRepository.findAll();
	//if(categories==null) return "admin/catgory/addCategory";
	articles = (List<Article>) articleRepository.findAll();
		
	}
	catch(NullPointerException e) {
		return "admin/article/addCategory";
		
	}
	model.addAttribute("categories", categories);
	model.addAttribute("articles", articles);
	return"admin/article/addArticle";
}
@RequestMapping(path="/add.html" ,method=RequestMethod.POST)	
public String addCategory(@RequestParam  String name,@RequestParam String scategories) throws DataException{
	Article article = new Article();
	article.setName(name);
	List<Category> categories=new ArrayList<>();
	
		categories.add(categoryRepository.findByName(scategories));
	
	article.setCategories(categories);
	try {
	validator.validate(article);
	}
	catch(DataIntegrityViolationException e) {
		throw new DataException("error");
	}
	Article savedarticle =articleRepository.save(article);
	return"redirect:add.html";
}
}
