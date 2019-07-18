package org.sid.maaboecomv1;

import net.bytebuddy.utility.RandomString;
import org.sid.maaboecomv1.dao.CategoryRepository;
import org.sid.maaboecomv1.dao.ProductRepository;
import org.sid.maaboecomv1.entities.Category;
import org.sid.maaboecomv1.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.Random;

@SpringBootApplication
public class MaaboEcomV1Application implements CommandLineRunner {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RepositoryRestConfiguration repositoryRestConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(MaaboEcomV1Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        repositoryRestConfiguration.exposeIdsFor(Product.class,Category.class);

        categoryRepository.save(new Category(null,"Computer",null,null,null));
        categoryRepository.save(new Category(null,"Printer",null,null,null));

        Random rnd=new Random();
        categoryRepository.findAll().forEach(c->{
            for (int i = 0; i < 10; i++) {
                Product p=new Product();
                p.setName(RandomString.make(18));
                p.setCurrentPrice(100+rnd.nextInt(10000));
                p.setAvailable(rnd.nextBoolean());
                p.setPromotion(rnd.nextBoolean());
                p.setSelected(rnd.nextBoolean());
                p.setPhotoName("unknow.jpg");
                p.setCategory(c);
                productRepository.save(p);
            }
        });
    }
}
