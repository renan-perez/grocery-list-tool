package net.grocerylist.core.conf;

import java.text.SimpleDateFormat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import net.grocerylist.core.business.GroceryListBusiness;
import net.grocerylist.core.business.ItemBusiness;
import net.grocerylist.core.controller.GroceryListController;
import net.grocerylist.core.controller.ItemController;
import net.grocerylist.core.dao.GroceryListDAO;
import net.grocerylist.core.dao.ItemDAO;
import net.grocerylist.core.dao.SelectedItemDAO;

@EnableWebMvc
@ComponentScan(basePackageClasses = {GroceryListBusiness.class, ItemBusiness.class, ItemDAO.class,
		GroceryListDAO.class, SelectedItemDAO.class, GroceryListController.class, ItemController.class})
public class AppWebConfiguration extends WebMvcConfigurerAdapter {
	
	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
		Jackson2ObjectMapperBuilder b = new Jackson2ObjectMapperBuilder();
		b.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy,MM,dd"));
		return b;
	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**");
	}
}
