package com.javguerra.controllers;

import com.javguerra.entities.Customer;
import com.javguerra.services.CustomerService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerControllerUTest {

    @InjectMocks
    private CustomerController customerController;
    @Mock
    private CustomerService customerService;
    @Mock
    private Model model;

    @Test
    @DisplayName("findAll() utilizando mocks se prueba la interacción con el repositorio y el modelo.")
    void findAll() {
        Customer man1 = Customer.builder().id(1L).build();
        Customer man2 = Customer.builder().id(2L).build();
        List<Customer> customers = List.of(man1, man2);
        when(customerService.getAllCustomers()).thenReturn(customers);

        String view = customerController.findAll(model);

        assertEquals("/customer/list", view);
        verify(customerService).getAllCustomers();
        verify(model).addAttribute("customers", customers);
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