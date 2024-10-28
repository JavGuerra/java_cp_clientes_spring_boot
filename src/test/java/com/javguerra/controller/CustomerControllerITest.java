package com.javguerra.controller;

import com.javguerra.model.Customer;
import com.javguerra.service.CustomerService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerITest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Test
    void findAll() throws Exception {
        when(customerService.getAllCustomers()).thenReturn(List.of(
                Customer.builder().id(1L).nombre("c1").build(),
                Customer.builder().id(2L).nombre("c2").build()
        ));

        mockMvc.perform(get("/customers"))
                .andExpect(view().name("customer/list"))
                .andExpect(model().attributeExists("customers"))
                .andExpect(model().attribute("customers", hasSize(2)))
                .andExpect(model().attribute("customers", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("nombre", is("c1"))
                        )
                )));
    }

    @Test
    void findById() {
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