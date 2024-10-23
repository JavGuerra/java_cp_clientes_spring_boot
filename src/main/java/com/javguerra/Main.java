package com.javguerra;

import com.javguerra.model.Customer;
import com.javguerra.repository.CustomerRepository;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Aplicación CRUD Customer con Spring Boot
 *
 * @author Javier Guerra
 * @version 1.0.0
 * @since 2024-10-21
 */
@SpringBootApplication
public class Main {

    /**
     * Aplicación Spring Boot
     *
     * @param args Argumentos de la línea de comandos
     */
    public static void main(String[] args) {

        var context = SpringApplication.run(Main.class, args);

        CustomerRepository customerRepository =
                context.getBean(CustomerRepository.class);

        if (customerRepository.count() == 0) {

            List<Customer> customers = new ArrayList<>();

            customers.add(
                Customer.builder().
                        nombre("c1").
                        apellido("García").
                        email("c1@gmail.com").
                        edad(30).
                        build()
            );
            customers.add(
                Customer.builder().
                        nombre("c2").
                        apellido("Pérez").
                        email("c2@gmail.com").
                        edad(40).
                        build()
            );
            customers.add(
                Customer.builder().
                        nombre("c3").
                        apellido("Melgar").
                        email("c3@gmail.com").
                        edad(50).
                        build()
            );

            customerRepository.saveAll(customers);
        }

    }

}
