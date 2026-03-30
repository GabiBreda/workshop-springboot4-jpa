package com.educandoweb.course.config;

import com.educandoweb.course.entities.*;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");

        Product p1 = new Product(null, "Clock", "Texto generico", 90.5, "");
        Product p2 = new Product(null, "Tv", "Texto generico", 2290.5, "");

        categoryRepository.saveAll(Arrays.asList(cat1,cat2));
        productRepository.saveAll(Arrays.asList(p1,p2));

        p1.getCategories().add(cat2);
        p2.getCategories().add(cat1);

        productRepository.saveAll(Arrays.asList(p1,p2));

        User u1 = new User(null, "Maria Brown","maria@gmail.com","88888888","123456");
        User u2 = new User(null, "Alex Green","alex@gmail.com","77777777","123456");

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID,u1);
        Order o2 = new Order(null, Instant.parse("2019-07-20T20:07:56Z"), OrderStatus.DELIVERED,u2);

        userRepository.saveAll(Arrays.asList(u1,u2));
        orderRepository.saveAll(Arrays.asList(o1,o2));

        OrderItem oi1 = new OrderItem(o1,p1,2,p1.getPrice());
        OrderItem oi2 = new OrderItem(o2,p2,1,p2.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1,oi2));

        Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
        o1.setPayment(pay1);

        orderRepository.save(o1);
    }
}
