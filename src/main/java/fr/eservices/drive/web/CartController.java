package fr.eservices.drive.web;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.taglibs.standard.tag.common.core.ForEachSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import fr.eservices.drive.dao.ArticleDao;
import fr.eservices.drive.dao.CartDao;
import fr.eservices.drive.dao.DataException;
import fr.eservices.drive.dao.OrderDao;
import fr.eservices.drive.model.Article;
import fr.eservices.drive.model.Cart;
import fr.eservices.drive.model.Order;
import fr.eservices.drive.repository.ArticleRepository;
import fr.eservices.drive.repository.CartRepository;
import fr.eservices.drive.repository.OrderRepository;
import fr.eservices.drive.web.dto.CartEntry;
import fr.eservices.drive.web.dto.SimpleResponse;
import fr.eservices.drive.web.dto.SimpleResponse.Status;


@Controller
@RequestMapping(path="/cart")
public class CartController {
	
	@Autowired
	CartRepository cartRepository;
	//CartDao daoCart;
	@Autowired
	ArticleRepository articleRepository;
	//ArticleDao daoArticle;
	
	@Autowired
	OrderRepository orderRepository;
	
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
	
	@GetMapping(path="/{id}.html", produces="text/html")
	public String getCart(@PathVariable(name="id") Long id, Model model) throws DataException {
		if(id<=0)throw new DataException("error");
		// get cart from dao
		Cart cart = cartRepository.findOne(id);
		
		// assign to model var "cart"
		model.addAttribute("cart", cart);
		// return view name to display content of /WEB-INF/views/_cart_header.jsp

		return "_cart_header";
	}

	@ResponseBody
	@PostMapping(path="/{id}/add.json",consumes="application/json")
	public SimpleResponse add(@PathVariable(name="id") long id, @RequestBody CartEntry art,Model model) throws DataException {
		SimpleResponse res = new SimpleResponse();
		
	 Article article = articleRepository.findOne(art.getId());
	if (article==null|art.getQty()<0){
		res.status = Status.ERROR;
		res.message = "article non ajouteajoute";
		return res;
	 }
	Cart cart = cartRepository.findOne(id);
	if(cart==null) {
		Cart newCart = new Cart();
		newCart.setCreatedOn(new Date());
		List <Article>articles =new ArrayList<>();
		articles.add(article);
		newCart.setArticles(articles);
		cart=cartRepository.save(newCart);
		model.addAttribute("cart", cart);
		
		
	}
	else {
	 List <Article>articles =cart.getArticles();
	 articles.add(article);
	 cart.setArticles(articles);}
		
		/*
		System.out.println(
			"********************\n"
			+ "***** " + String.format("Add Article %d x [%s] to cart", art.getQty(), art.getId()) + "\n" 
			+ "********************"
		);
		*/
		
		res.status = Status.OK;
		res.message = "article ajoute";
		return res;
		
		
	}
	
	@RequestMapping("/{id}/validate.html")
	public String validateCart(@PathVariable(name="id") long id, Model model) throws DataException {
		
		// get cart by its id
		Cart cart = cartRepository.findOne(id);
		List <Article>articles =cart.getArticles();
		int amount=0;
		List<String> orderArticles=new ArrayList<>();
		for(Article article:articles) {
			orderArticles.add(article.getName());
			amount+=article.getPrice();
		}
		// create an order
		Order order = new Order();
		order.setCustomerId("chuckNorris");
		order.setArticles(orderArticles);
		order.setCreatedOn(new Date());
		order.setAmount(amount);
		order.setCurrentStatus(fr.eservices.drive.dao.Status.ORDERED);
		
		// for each article, add it to the order
		// set order date
		// set order amount (sum of each articles' price)
		// persist everything
		// redirect user to list of orders
		orderRepository.save(order);
		
		return "redirect:/order/ofCustomer/chuckNorris.html";
	}
}
