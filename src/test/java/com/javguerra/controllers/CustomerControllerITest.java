package com.javguerra.controllers;

import com.javguerra.entities.Customer;
import com.javguerra.services.CustomerService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerControllerITest {

    @InjectMocks
    private CustomerController customerController;
    @Mock
    private CustomerService customerService;
    @Mock
    private Customer customer;

    @Test
    void findAll() {
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