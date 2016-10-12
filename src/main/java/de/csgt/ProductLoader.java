package de.csgt;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class ProductLoader implements ApplicationListener<ContextRefreshedEvent> {

//    private ProductRepository productRepository;
//
//    private Logger log = Logger.getLogger(ProductLoader.class);

//    @Autowired
//    public void setProductRepository(ProductRepository productRepository) {
////        this.productRepository = productRepository;
//    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        
    }
}