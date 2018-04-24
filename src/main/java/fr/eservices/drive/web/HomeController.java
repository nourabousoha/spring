package fr.eservices.drive.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.eservices.drive.model.Article;
import fr.eservices.drive.repository.ArticleRepository;

@Controller
public class HomeController {
	@Autowired
	ArticleRepository articleRepository;	
@RequestMapping( method = RequestMethod.GET)
public  String getHome(Model model) {
	
	List<Article>articles =(List<Article>) articleRepository.findAll();
	model.addAttribute("articles", articles);
	return "sample_products";
}
}
