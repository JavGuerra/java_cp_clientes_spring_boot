package com.javguerra.controller;

import com.javguerra.model.Customer;
import com.javguerra.service.CustomerService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CustomerControllerITest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CustomerService customerService;

    @Test
    void findAll() throws Exception {

        customerService.saveAll(List.of(
                Customer.builder().id(1L).nombre("c1").build(),
                Customer.builder().id(2L).nombre("c2").build()
        ));
        System.out.println("findAll products: " + customerService.count());
        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/list"))
                .andExpect(model().attributeExists("customers"))
                .andExpect(model().attribute("customers", hasSize(2)));
    }

    @Test
    void findById() throws Exception {
        Customer customer = customerService.save(
                Customer.builder().nombre("c1").build()
        );

        System.out.println("findById products: " + customerService.count());
        System.out.println("findById producto guardado: " + customer.getId());

        mockMvc.perform(get("/customers/" + customer.getId()))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/detail"))
                .andExpect(model().attributeExists("customer"));
    }

    @Test
    void getFormToCreate() {
    }

    @Test
    void getFormToUpdate() {
    }

    @Test
    void save() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void deleteAll() {
    }

}