package fr.eservices.drive.web;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.sql.DataSource;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

@Configuration
@EnableJpaRepositories(basePackages="fr.eservices.drive.repository")
@EnableTransactionManagement
@ComponentScan(basePackages={"fr.eservices.drive.web","fr.eservices.drive.model"})
@EnableWebMvc
public class AppConfig implements WebApplicationInitializer {
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan("fr.eservices.drive.model");
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());
		return em;
	}
	@Bean
	   public DataSource dataSource(){
	      DriverManagerDataSource dataSource = new DriverManagerDataSource();
	      dataSource.setDriverClassName("org.h2.Driver");
	      dataSource.setUrl("jdbc:h2:./db");
	    //  dataSource.setUsername( "tutorialuser" );
	    //  dataSource.setPassword( "tutorialmy5ql" );
	      return dataSource;
	   }
	@Bean
	public Validator validator()	{
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		return validator;
	}
	 
	   @Bean
	   public PlatformTransactionManager transactionManager(
	     EntityManagerFactory emf){
	       JpaTransactionManager transactionManager = new JpaTransactionManager();
	       transactionManager.setEntityManagerFactory(emf);
	 
	       return transactionManager;
	   }
	 
	   @Bean
	   public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
	       return new PersistenceExceptionTranslationPostProcessor();
	   }
	 
	   Properties additionalProperties() {
	       Properties properties = new Properties();
	       properties.setProperty("hibernate.hbm2ddl.auto", "update");
	       properties.setProperty(
	         "hibernate.dialect", "org.hibernate.dialect.H2Dialect");
	        
	       return properties;
	   }

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.register(AppConfig.class);
		ServletRegistration.Dynamic registration = container.addServlet("dispatcher", new DispatcherServlet(ctx));
		registration.setLoadOnStartup(1);
		registration.addMapping("*.html", "*.json");
		
		
	}
	
	@Bean
	public ViewResolver viewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass( JstlView.class );
		
		return resolver;
	}

}
