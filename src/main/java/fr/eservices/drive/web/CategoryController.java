package fr.eservices.drive.web;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
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
import fr.eservices.drive.model.Category;
import fr.eservices.drive.repository.CategoryRepository;


@Controller
@RequestMapping(path="/category")
public class CategoryController {
	
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

@RequestMapping(path="/add.html",method=RequestMethod.GET)	
public String addCategory(Model model) {
	List<Category> categories=null;
	try {
	categories =(List<Category>) categoryRepository.findAll();
	//if(categories==null) return "admin/catgory/addCategory";
	
		
	}
	catch(NullPointerException e) {
		return "admin/catgory/addCategory";
	}
	model.addAttribute("categories", categories);
	return"admin/catgory/addCategory";
}
@RequestMapping(path="/add.html" ,method=RequestMethod.POST)	
public String addCategory(@RequestParam  String name) throws DataException{
	Category cat = new Category();
	cat.setName(name);
	cat.setOrderIdx(2);
	try {
	validator.validate(cat);
	}
	catch(DataIntegrityViolationException e) {
		throw new DataException("error");
	}
	Category savedCat =categoryRepository.save(cat);
	return"redirect:add.html";
}
}
