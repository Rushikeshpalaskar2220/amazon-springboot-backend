package net.javaguides.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.javaguides.springboot.repository.CategoryRepository;
import net.javaguides.springboot.repository.ProductsRepository;
import net.javaguides.springboot.repository.SubCategoryRepository;

@SpringBootApplication
public class SpringbootBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBackendApplication.class, args);
	}

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private ProductsRepository productsRepository;

	@Override
	public void run(String... args) throws Exception {
//		// Create Category
//		Category electronics = new Category();
//		electronics.setName("Electronics");
//		categoryRepository.save(electronics);
//
//		// Create SubCategory
//		SubCategory mobiles = new SubCategory();
//		mobiles.setName("Mobiles");
//		mobiles.setCategory(electronics);
//		subCategoryRepository.save(mobiles);
//
//		// Create Product
//		Product iphone16 = new Product();
//		iphone16.setName("iPhone 16");
//		iphone16.setDescription("Latest iPhone model");
//		iphone16.setSubcategory(mobiles);
//		productsRepository.save(iphone16);
//
//		Product iphone12 = new Product();
//		iphone12.setName("iPhone 12");
//		iphone12.setDescription("2012 iPhone model");
//		iphone12.setSubcategory(mobiles);
//		productsRepository.save(iphone12);
	}
}
